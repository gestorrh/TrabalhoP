package ufc.npi.proficiencia.service;
import ufc.npi.proficiencia.exception.ProficienciaException;
import ufc.npi.proficiencia.model.Exame;
import ufc.npi.proficiencia.model.LocalProva;

import java.util.List;
import java.util.Map;

public interface LocalProvaService {


    List<Map<String, Object>> findLocaisByIdExame(Integer exameId);

    Map<String,Object> findLocalProvaByExameId(Integer id);

    Map<String, Object> salvarLocalProva(LocalProva localProva, Exame exame) throws ProficienciaException;

    void deleteLocalProva(Integer localId) throws ProficienciaException;

    void moverCandidatoLocalProva(Integer inscricao, Integer localProva) throws ProficienciaException;

    Map<String, Object> atualizarLocal(LocalProva local) throws ProficienciaException;

}
