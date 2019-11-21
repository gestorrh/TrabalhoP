package ufc.npi.proficiencia.service;


import ufc.npi.proficiencia.exception.ProficienciaException;
import ufc.npi.proficiencia.model.Exame;

import java.util.List;
import java.util.Map;

public interface ExameService {
    Map<String, Object> buscarExamePorId(Integer exameId);

    List<Map<String, Object>> buscarExamesCandidatoNaoInscrito(Integer candidatoId);

    List<Map<String, Object>> buscarExames();

    Exame cadastrarExame(Exame exame) throws ProficienciaException;

    void deleteExame(Integer id) throws ProficienciaException;

    void atualizarExame(Exame exame) throws ProficienciaException;

    Exame encerrarFaseInscricao(Integer id) throws ProficienciaException;

    Exame encerrarFaseSelecao(Exame exame) throws ProficienciaException;


}
