package ufc.npi.proficiencia.service;


import ufc.npi.proficiencia.exception.ProficienciaException;
import ufc.npi.proficiencia.model.Token;
import ufc.npi.proficiencia.model.Usuario;

import java.util.List;
import java.util.Map;

public interface UsuarioService {
    Usuario buscaUsuarioPorEmail(String email);

    Map<String, Object> buscarUsuario(Integer id);

    List<Map<String, Object>> buscarUsuarios();

    void salvarUsuario(Usuario usuario) throws ProficienciaException;

    void atualizarUsuario(Usuario usuario);

    void excluirUsuario(Integer id, Integer userId) throws ProficienciaException;

    boolean recuperarSenha(String email);

    void constructResetTokenMail(Token token, Usuario usuario);

    void criarToken(Usuario usuario);

    boolean mudarSenha(Token token);

    Integer tokenInvalido(String hash);

    void alterarSenha(Usuario usuario, String senhaAtual, String novaSenha) throws ProficienciaException;
}