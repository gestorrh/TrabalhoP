package ufc.npi.proficiencia.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ufc.npi.proficiencia.exception.ProficienciaException;
import ufc.npi.proficiencia.model.*;
import ufc.npi.proficiencia.model.enums.Fase;
import ufc.npi.proficiencia.model.enums.NecessidadeEspecial;
import ufc.npi.proficiencia.model.enums.StatusInscricao;
import ufc.npi.proficiencia.repository.*;
import ufc.npi.proficiencia.service.ArquivoService;
import ufc.npi.proficiencia.util.Messages;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class InscricaoServiceImpl implements ufc.npi.proficiencia.service.InscricaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private InscricaoRepository inscricaoRepository;

    @Autowired
    private ExameRepository exameRepository;

    @Autowired
    private ArquivoService arquivoService;

    @Autowired
    private ArquivoRepository arquivoRepository;

    @Autowired
    private LocalProvaRepository localProvaRepository;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Inscricao realizarInscricao(Map<String, Object> informacoes, Integer id) throws ProficienciaException {
        Exame exame = exameRepository.getOne((Integer)informacoes.get("idExame"));

        if(!exame.getFase().equals(Fase.INSCRICAO) || LocalDateTime.now().isBefore(exame.getInicioInscricoes()) || LocalDateTime.now().isAfter(exame.getTerminoInscricoes())) {
            throw new ProficienciaException(Messages.ERRO_EXAME_FORA_DO_PERIODO_DE_INSCRICAO);
        } else if(inscricaoRepository.existsByExameAndAndUsuario_Id(exame, id)) {
            throw new ProficienciaException(Messages.ERRO_USUARIO_JA_INSCRITO_NO_EXAME);
        } else if ((exame.getNumeroVagas()) == (exame.getQuantidadeCandidatos())){
            throw new ProficienciaException(Messages.ERRO_QUANTIDADE_DE_INSCRITOS_ESGOTADA);
        }

        Usuario usuario = usuarioRepository.getOne(id);

        usuario.setNome((String) informacoes.get("nome"));
        usuario.setEmail((String) informacoes.get("email"));
        usuario.setTelefone((String) informacoes.get("telefone"));
        String necessidadeEspecial = (String) informacoes.get("necessidadeEspecial");
        usuario.setNecessidadeEspecial(necessidadeEspecial != null ? (NecessidadeEspecial.valueOf(necessidadeEspecial)) : null);
        usuario.setObservacaoNecessidade((String) informacoes.get("observacaoNecessidade"));

        usuarioRepository.save(usuario);

        Inscricao inscricao = new Inscricao();

        inscricao.setDataHoraInscricao(LocalDateTime.now());
        inscricao.setExame(exame);
        inscricao.setStatusInscricao(StatusInscricao.INSCRITO);
        inscricao.setUsuario(usuario);
        inscricao.setProgramaPos((String) informacoes.get("programaPos"));
        inscricao.setAreaConcentracao((String) informacoes.get("areaConcentracao"));
        inscricao.setLinhaPesquisa((String) informacoes.get("linhaPesquisa"));
        inscricao.setInstituicao((String) informacoes.get("instituicao"));
        inscricao.setFormacao((String) informacoes.get("formacao"));
        inscricao.setPrioridade((Integer) informacoes.get("prioridade"));

        return inscricaoRepository.save(inscricao);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Arquivo adicionarArquivoInscricao(MultipartFile file, String observacao, Integer idInscricao) throws ProficienciaException, IOException {

        Inscricao inscricao = inscricaoRepository.getOne(idInscricao);

        if(inscricao.getExame().getFase() !=  Fase.INSCRICAO) throw new ProficienciaException(Messages.ERRO_ADICIONAR_ARQUIVO_FASE_EXAME);
        if (inscricao.getStatusInscricao() == StatusInscricao.DEFERIDO) throw new ProficienciaException(Messages.ERRO_ADICIONAR_ARQUIVO_STATUS_INSCRICAO_DEFERIDA);
        if (inscricao.getStatusInscricao() == StatusInscricao.INDEFERIDO) throw new ProficienciaException(Messages.ERRO_ADICIONAR_ARQUIVO_STATUS_INSCRICAO_INDEFERIDA);

        Arquivo arquivo = arquivoService.salvarArquivo(file.getOriginalFilename(), observacao, file.getInputStream(), file.getSize(), file.getContentType());

        inscricao.getArquivos().add(arquivo);
        inscricaoRepository.save(inscricao);
        return arquivo;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean removerArquivoInscricao(Integer idInscricao, Integer idArquivo) throws ProficienciaException {
        Inscricao inscricao = inscricaoRepository.getOne(idInscricao);

        if(inscricao.getExame().getFase() !=  Fase.INSCRICAO) throw new ProficienciaException(Messages.ERRO_EXCLUIR_ARQUIVO_FASE_EXAME);
        if (inscricao.getStatusInscricao() == StatusInscricao.DEFERIDO) throw new ProficienciaException(Messages.ERRO_EXCLUIR_ARQUIVO_STATUS_INSCRICAO_DEFERIDA);
        if (inscricao.getStatusInscricao() == StatusInscricao.INDEFERIDO) throw new ProficienciaException(Messages.ERRO_EXCLUIR_ARQUIVO_STATUS_INSCRICAO_INDEFERIDA);


        Arquivo arquivo = arquivoRepository.getOne(idArquivo);
        if((arquivo.getId() != null) && (inscricao.getId() != null)){
            inscricao.getArquivos().remove(arquivo);
            inscricaoRepository.save(inscricao);
            arquivoService.deletarArquivo(idArquivo);
            return true;
        }
        return false;
    }

    @Override
    public List<Map<String, Object>> buscarInscricoesCandidato(Integer candidatoId) {
        return inscricaoRepository.findInscricaoByCandidatoId(candidatoId);
    }

    @Override

    public Map<String, Object> buscarInscricaoCoordenador(Integer inscricaoId) {
        return inscricaoRepository.findByCandidatoIdForCooodenador(inscricaoId);
    }

    @Override
    public List<Map<String, Object>> buscarInscricoesPorExame(Integer exameId) {
        return inscricaoRepository.findInscricoesByExame(exameId);
    }

    public Map<String, Object> buscarInformacoesInscricaoCandidato(Integer inscricaoId) {
        return inscricaoRepository.findInscricaoByIdCandidato(inscricaoId);

    }

    @Override
    public Map buscarInscricaoPorExameCandidato(Integer inscricaoId){
        return inscricaoRepository.findInscricoesByExameByCandidato(inscricaoId);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Map analisarInscricao(Inscricao inscricao, Exame exame) throws ProficienciaException {

        Inscricao inscricaoBd = inscricaoRepository.getOne(inscricao.getId());
        List<LocalProva> locaisProva = localProvaRepository.findLocalProvaComVagaPorExame( PageRequest.of(0,1), exame.getId());

        if(!exame.getFase().equals(Fase.SELECAO)) {
            throw new ProficienciaException(Messages.ERRO_EXAME_FORA_DO_PERIODO_DE_SELECAO);
        }
        else if(locaisProva.isEmpty() && inscricao.getStatusInscricao().equals(StatusInscricao.DEFERIDO)){
            throw new ProficienciaException(Messages.ERRO_QUANTIDADE_DE_VAGAS_INSUFUCIENTE);
        }
        else if(inscricao.getStatusInscricao().equals(StatusInscricao.INDEFERIDO) || locaisProva.isEmpty()){
            inscricaoBd.setStatusInscricao(inscricao.getStatusInscricao());
            inscricaoBd.setObservacaoStatusInscricao(inscricao.getObservacaoStatusInscricao());
            inscricaoRepository.save(inscricaoBd);

            Map mapInscricao = new HashMap();
            mapInscricao.put("InscricaoCandidato", buscarInscricaoPorExameCandidato(inscricao.getId()));
            return mapInscricao;
        }

        else {
            inscricaoBd.setStatusInscricao(inscricao.getStatusInscricao());
            inscricaoBd.setObservacaoStatusInscricao(inscricao.getObservacaoStatusInscricao());
            inscricaoBd.setLocalProva(locaisProva.get(0));

        }
        inscricaoRepository.save(inscricaoBd);

        Map map = new HashMap();
        map.put("InscricaoCandidato", buscarInscricaoPorExameCandidato(inscricao.getId()));
        map.put("local", localProvaRepository.findLocalById(locaisProva.get(0).getId()));

        return map;
    }

}
