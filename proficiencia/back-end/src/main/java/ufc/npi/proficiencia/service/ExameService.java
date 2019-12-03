package ufc.npi.proficiencia.service;

import ufc.npi.proficiencia.exception.ProficienciaException;
import ufc.npi.proficiencia.model.Exame;

import java.util.List;
import java.util.Map;

public interface ExameService {

//    Map<String, Object> buscarExame(Integer id);

//    List<Map<String, Object>> buscarExames();

    void cadastrarExame(Exame exame) throws ProficienciaException;

    void deleteExame(Integer id, Integer userId) throws ProficienciaException;

    List<Map<String, Object>> findAll();

    void atualizarExame(Exame exame);

    List<Map<String, Object>> buscarExameMedico(Integer medicoId);

    List<Map<String, Object>> buscarExameColaborador(Integer colaboradorId);

}
