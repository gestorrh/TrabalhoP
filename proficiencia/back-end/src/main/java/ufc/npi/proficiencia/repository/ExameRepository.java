package ufc.npi.proficiencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ufc.npi.proficiencia.model.Exame;
import ufc.npi.proficiencia.model.Inscricao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public interface ExameRepository extends JpaRepository<Exame, Integer> {

    @Query("SELECT e FROM Inscricao as e WHERE e.usuario.id = :idUsuario")
    List<Inscricao> buscarPorCandidato(@Param("idUsuario") Integer idUsuario);

    @Query("SELECT e.titulo AS titulo, e.descricao AS descricao, e.inicioInscricoes AS inicioInscricoes, e.terminoInscricoes AS terminoInscricoes, e.dataHoraProva as dataHoraProva, e.localProva AS localProva, e.numeroVagas AS numeroVagas, e.fase AS fase, e.dataResultado AS dataResultado, e.inicioReavaliacao AS inicioReavaliacao, e.terminoReavaliacao AS terminoReavaliacao, COUNT (i) AS numeroInscritos FROM Exame e LEFT JOIN e.inscricoes i WHERE e.id = :idExame GROUP BY e.id ")
    Map<String, Object> getExameById(@Param("idExame") Integer idExame);

    @Query("SELECT e.titulo AS titulo, e.fase AS fase, e.numeroVagas AS numeroVagas, COUNT(i) AS inscricoes, e.id AS id FROM Exame e LEFT JOIN e.inscricoes i GROUP BY e.id")
    List<Map<String, Object>> findAllCoordenadorView();

    @Query("SELECT e.titulo AS titulo, e.inicioInscricoes AS inicioInscricoes, e.terminoInscricoes AS terminoInscricoes, e.numeroVagas AS numeroVagas, COUNT(i) AS inscricoes, e.id AS id FROM Exame e  LEFT JOIN e.inscricoes i WHERE e.fase ='INSCRICAO' AND :dataHoraAtual BETWEEN e.inicioInscricoes AND e.terminoInscricoes AND NOT EXISTS (SELECT i FROM e.inscricoes i WHERE i.usuario.id = :idUsuario) GROUP BY e.id")
    List<Map<String, Object>> findAllByFaseInscricaoCandidatoNaoInscrito(@Param("idUsuario") Integer idUsuario, @Param("dataHoraAtual") LocalDateTime dataHoraAtual);


}


