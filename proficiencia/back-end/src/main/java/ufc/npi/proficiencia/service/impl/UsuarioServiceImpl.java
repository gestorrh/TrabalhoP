package ufc.npi.proficiencia.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ufc.npi.proficiencia.exception.ProficienciaException;
import ufc.npi.proficiencia.model.Token;
import ufc.npi.proficiencia.model.Usuario;
import ufc.npi.proficiencia.repository.UsuarioRepository;
import ufc.npi.proficiencia.service.NotificationService;
import ufc.npi.proficiencia.service.TokenService;
import ufc.npi.proficiencia.service.UsuarioService;
import ufc.npi.proficiencia.util.BeanUtil;
import ufc.npi.proficiencia.util.Messages;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UsuarioService usuarioService;

    @Value("${sistema.link}")
    private String endereco;

    @Override
    public Usuario buscaUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public Map<String, Object> buscarUsuario(Integer id) {
        return usuarioRepository.dadosUsuario(id);
    }

    public List<Map<String, Object>> buscarUsuarios() {
        return this.usuarioRepository.findAllUsuariosview();
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void salvarUsuario(Usuario usuario) throws ProficienciaException {
        if (usuario.getCpf() != null && usuarioRepository.findByCpf(usuario.getCpf()) != null) {
            throw new ProficienciaException(Messages.ERRO_CPF_JA_CADASTRADO);
        }

        if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
            usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getCpf()));

        } else {
            usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        }

        usuarioRepository.save(usuario);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void atualizarUsuario(Usuario usuario) {
        Usuario usuarioBd = usuarioRepository.getOne(usuario.getId());
        BeanUtils.copyProperties(usuario, usuarioBd, BeanUtil.getNullPropertyNames(usuario));
        usuarioRepository.save(usuarioBd);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void excluirUsuario(Integer id, Integer userId) throws ProficienciaException {
        Usuario usuario = this.usuarioRepository.findById(id).get();
        if (usuario.getId().equals(userId)) {
            throw new ProficienciaException(Messages.ERRO_AUTOEXCLUSAO);
        } else if (!(usuario.getInscricoes().isEmpty())) {
            throw new ProficienciaException(Messages.ERRO_USUARIO_INSCRICOES_ATIVAS);
        } else {
            this.usuarioRepository.deleteById(id);
        }
    }

    @Override
    public boolean recuperarSenha(String email) {
        Usuario usuario = buscaUsuarioPorEmail(email);
        if (usuario != null) {
            List<Token> tokens;
            tokens = tokenService.buscarPorUsuario(usuario);

            if (tokens.isEmpty()) {
                usuarioService.criarToken(usuario);
            } else {
                for (Token token : tokens) {
                    tokenService.excluirToken(token.getId());
                }
                usuarioService.criarToken(usuario);
            }
            return true;
        }
        return false;
    }

    @Override
    public void constructResetTokenMail(Token token, Usuario usuario) {
        String assunto = "Recuperação de senha";
        String corpo = "Olá, Foi solicitada a recuperação de senha para este usuário,"
                + " se não foi você quem solicitou desconsidere esta mensagem e continue"
                + " utilizando sua senha atual. Para recuperar sua senha acesse o link  "
                + endereco + "/novaSenha?" + "hash=" + token.getHash();
        String destinatario = usuario.getEmail();
        notificationService.enviarMensagem(assunto, corpo, destinatario);
    }

    @Override
    public void criarToken(Usuario usuario) {
        Token token = new Token();
        token.setHash(UUID.randomUUID().toString());
        token.setUsuario(usuario);
        token.setValidade(2880); //2880 = 2 dias
        tokenService.salvarToken(token);

        constructResetTokenMail(token, usuario);
    }

    @Override
    public boolean mudarSenha(Token token) {

        Token tokenDb = tokenService.buscarPorHash(token.getHash());

        if (!tokenDb.isExpired()) {
            Usuario usuario = tokenDb.getUsuario();
            usuario.setSenha(token.getUsuario().getSenha());
            usuarioRepository.save(usuario);
            tokenService.excluirToken(tokenDb.getId());
            return true;
        }
        return false;
    }

    @Override
    public Integer tokenInvalido(String hash) {
        Token token = tokenService.buscarPorHash(hash);
        if (token != null && !token.isExpired())
            return 1;
        return 0;
    }

    @Override
    public void alterarSenha(Usuario usuario, String senhaAtual, String novaSenha) throws ProficienciaException {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(senhaAtual, usuario.getSenha())) {
            throw new ProficienciaException("A senha atual informada está incorreta");
        }

        usuario.setSenha(new BCryptPasswordEncoder().encode(novaSenha));
        usuarioRepository.save(usuario);
    }
}