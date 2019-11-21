package ufc.npi.proficiencia.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ufc.npi.proficiencia.model.Exame;
import ufc.npi.proficiencia.model.LocalProva;

import java.util.List;
import java.util.Map;

@Repository
public interface LocalProvaRepository extends JpaRepository<LocalProva, Integer> {


    @Query("SELECT u.id AS idCandidato, u.nome AS nomeCandidato, u.email AS email, u.telefone AS telefone, u.necessidadeEspecial AS necessidadeEspecial, l.exame.id AS idExame, i.id AS idInscricao, i.exame.fase AS faseExame FROM LocalProva l LEFT JOIN l.inscricoes i JOIN i.usuario u WHERE l.id = :id")
    List<Map<String,Object>> findCandidatosAlocados(@Param("id") Integer id);

    @Query("SELECT l.id AS id, l.exame.id AS exameId, l.nome AS nome, l.capacidade AS capacidade, COUNT(i) AS candidatosAlocados FROM LocalProva l LEFT JOIN l.inscricoes i WHERE l.id = :localprovaId GROUP BY l.id")
    Map<String,Object> findLocalProvaByExameId(@Param("localprovaId") Integer localprovaId);

    @Query("SELECT l.id AS id, l.exame.titulo AS titulo, l.exame.fase AS fase, l.exame.id AS idExame from LocalProva l LEFT JOIN l.inscricoes i where l.id = :id")
    List<Map<String,Object>> findInformacoesExameLocalProva(@Param("id") Integer id);

    @Query("SELECT l.id AS id, l.nome AS nome, l.capacidade AS capacidade, COUNT(i) AS candidatosAlocados FROM LocalProva l LEFT JOIN l.inscricoes i GROUP BY l.id")
    List<Map<String, Object>> findAllLocaisProva();

    @Query("SELECT l.id AS id, l.nome AS nome, l.capacidade AS capacidade, COUNT(i) AS candidatosAlocados FROM LocalProva l LEFT JOIN l.inscricoes i JOIN l.exame e WHERE l.id = :localId GROUP BY l.id")
    Map<String, Object> findLocalById(@Param("localId") Integer localId);

    @Query("select count(i) from LocalProva  l LEFT JOIN l.inscricoes i  where l.id = :id")
    int countCandidatosAlocados(@Param("id") Integer id);

    @Query("SELECT l.id AS id, l.nome AS nome, l.capacidade AS capacidade, COUNT(i) AS candidatosAlocados FROM LocalProva l LEFT JOIN l.inscricoes i JOIN l.exame e WHERE e.id = :idExame GROUP BY l.id")
    List<Map<String, Object>> findLocaisByIdExame(@Param("idExame") Integer idExame);

    @Query("SELECT e FROM LocalProva as l LEFT JOIN l.exame e WHERE l.id = :id")
    Exame buscarExame(@Param("id") Integer id);

    @Query("SELECT l FROM LocalProva l WHERE l.exame.id = :idExame AND (SELECT COUNT(i) FROM Inscricao i WHERE i.localProva.id = l.id) < l.capacidade")
    List<LocalProva> findLocalProvaComVagaPorExame(Pageable pageable, @Param("idExame")Integer idExame);

    @Query("SELECT SUM(l.capacidade) from LocalProva l JOIN l.exame e WHERE e.id = :id")
    Integer countCapacidadeLocais(@Param("id") Integer id);
}




