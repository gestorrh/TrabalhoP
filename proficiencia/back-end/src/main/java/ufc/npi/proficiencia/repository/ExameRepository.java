package ufc.npi.proficiencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ufc.npi.proficiencia.model.Exame;

import java.util.List;
import java.util.Map;


@Repository
public interface ExameRepository extends JpaRepository<Exame, Integer> {

    @Query("SELECT e.id as id, e.nomeExame as nomeExame, e.dataExame as dataExame, e.statusExame as statusExame, e.descricao as descricao, e.crmMedico as crmMedico, e.cid as cid, e.cid as cid, e.avaliacaoMedica as avaliacaoMedica, e.diaProximoExame as diaProximoExame, e.diasAfastamento as diaAfastamento FROM Exame e WHERE e.id = :id")
    Map<String, Object> dadosExame(@Param("id") Integer id);

    @Query("SELECT e.id as id, e.nomeExame as nomeExame, e.dataExame as dataExame, e.descricao as descricao, e.crmMedico as crmMedico, e.cid as cid, e.cid as cid, e.avaliacaoMedica as avaliacaoMedica, e.diaProximoExame as diaProximoExame, e.diasAfastamento as diaAfastamento, e.colaborador.nome as colaborador_id, e.medico.nome as medico_id  FROM Exame e")
    List<Map<String, Object>> findAllExamesView();

    @Query("SELECT e.id as id, e.nomeExame as nomeExame, e.dataExame as dataExame, e.descricao as descricao, e.crmMedico as crmMedico, e.cid as cid, e.cid as cid, e.avaliacaoMedica as avaliacaoMedica, e.diaProximoExame as diaProximoExame, e.diasAfastamento as diaAfastamento, e.statusExame as statusExame, e.colaborador.nome as colaborador_id FROM Exame e WHERE e.medico.id = :medicoId")
    List<Map<String, Object>> findAllExamesMedicoView(@Param("medicoId") Integer medicoId);

    @Query("SELECT e.id as id, e.nomeExame as nomeExame, e.dataExame as dataExame, e.descricao as descricao, e.crmMedico as crmMedico, e.cid as cid, e.cid as cid, e.avaliacaoMedica as avaliacaoMedica, e.diaProximoExame as diaProximoExame, e.diasAfastamento as diaAfastamento, e.statusExame as statusExame, e.medico.nome as medico_id  FROM Exame e WHERE e.colaborador.id = :colaboradorId")
    List<Map<String, Object>> findAllExamesColaboradorView(@Param("colaboradorId") Integer colaboradorId);

}
