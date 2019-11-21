package ufc.npi.proficiencia.service;

import org.springframework.web.multipart.MultipartFile;
import ufc.npi.proficiencia.exception.ProficienciaException;
import ufc.npi.proficiencia.model.Exame;
import ufc.npi.proficiencia.model.Arquivo;
import ufc.npi.proficiencia.model.Inscricao;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface InscricaoService {

    Inscricao realizarInscricao(Map<String, Object> informacoes, Integer id) throws ProficienciaException;

    List<Map<String, Object>> buscarInscricoesCandidato(Integer candidatoId);

    Map<String, Object> buscarInformacoesInscricaoCandidato(Integer inscricaoId);

    Map<String, Object> buscarInscricaoCoordenador(Integer inscricaoId);

    List<Map<String, Object>> buscarInscricoesPorExame(Integer exameId);

    Arquivo adicionarArquivoInscricao(MultipartFile file, String observacao, Integer idInscricao) throws ProficienciaException, IOException;

    boolean removerArquivoInscricao(Integer idInscricao, Integer idArquivo) throws ProficienciaException;

    Map analisarInscricao(Inscricao inscricao, Exame exame) throws ProficienciaException;

    Map<String, Object> buscarInscricaoPorExameCandidato(Integer inscricaoId);

}
