package ufc.npi.proficiencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ufc.npi.proficiencia.model.Exame;
import ufc.npi.proficiencia.model.Inscricao;

import java.util.List;
import java.util.Map;

@Repository

public interface InscricaoRepository extends JpaRepository<Inscricao, Integer> {

    List<Inscricao> findByExameId(Integer id);

    Boolean existsByExameAndAndUsuario_Id(Exame exame, Integer usuarioId);

    @Query("SELECT i.id as id, e.titulo as titulo, e.fase as fase, i.notaAvaliacao as notaAvaliacao, " +
            "i.reavaliacaoSolicitada as reavaliacaoSolicitada, i.notaReavaliacao as notaReavaliacao, " +
            "i.statusAvaliacao as statusAvaliacao FROM Inscricao i LEFT JOIN  i.exame e " +
            "WHERE i.usuario.id = :id ")
    List<Map<String, Object>> findInscricaoByCandidatoId(@Param("id") Integer idUsuario);

    @Query("SELECT COUNT(i) AS quantidadeInscricoes, MIN(i.dataHoraInscricao) AS menorData, MAX(i.dataHoraInscricao) AS maiorData from Inscricao i where i.exame.id = :id")
    Map<String, Object> findQuantidadeInscricoesAndMaxInscricaoDataAndMinInscricaoDataByExameId(@Param("id") Integer id);

    @Query("SELECT u.observacaoNecessidade as observacaoNecessidade, u.nome as nome, u.necessidadeEspecial as necessidadeEspecial, i.id as id, e.titulo as titulo, e.fase as fase, i.dataHoraInscricao as dataHoraInscricao, i.programaPos as programaPos, i.areaConcentracao as areaConcentracao, i.linhaPesquisa as linhaPesquisa, i.instituicao as instituicao, i.notaAvaliacao as notaAvaliacao, i.notaReavaliacao as notaReavaliacao, i.reavaliacaoSolicitada as reavaliacaoSolicitada, i.statusAvaliacao as statusAvaliacao, i.statusInscricao as statusInscricao, i.observacaoStatusInscricao as observacaoStatusInscricao, e.id as exameId FROM Inscricao i JOIN  i.exame e JOIN i.usuario u WHERE i.id = :id")
    Map<String, Object> findByCandidatoIdForCooodenador(@Param("id") Integer id);


    @Query("SELECT i.dataHoraInscricao as dataHoraInscricao, i.statusInscricao as statusInscricao, i.programaPos as programaPos, i.areaConcentracao as areaConcentracao, i.linhaPesquisa as linhaPesquisa, i.instituicao as instituicao, i.formacao as formacao, e.titulo as titulo, e.descricao as descricao, e.inicioInscricoes as inicioInscricoes, e.terminoInscricoes as terminoInscricoes, e.dataHoraProva as dataHoraProva, e.fase as fase FROM Inscricao i JOIN i.exame e WHERE i.id = :id ")
    Map<String, Object> findInscricaoByIdCandidato(@Param("id") Integer id);

    @Query("SELECT u.nome as nome, i.id as idInscricao, i.statusInscricao as statusInscricao, i.observacaoStatusInscricao as observacaoStatusInscricao,  i.statusAvaliacao as statusAvaliacao, i.notaAvaliacao as notaAvaliacao, i.notaReavaliacao AS notaReavaliacao, i.reavaliacaoSolicitada AS reavaliacaoSolicitada, i.prioridade AS prioridade FROM Inscricao i JOIN i.usuario u WHERE i.id = :id")
    Map<String, Object> findInscricoesByExameByCandidato(@Param("id") Integer id);

    @Query("SELECT u.nome AS nome, i.id AS idInscricao, i.statusInscricao AS statusInscricao, i.observacaoStatusInscricao as observacaoStatusInscricao, i.statusAvaliacao AS statusAvaliacao, i.notaAvaliacao AS notaAvaliacao, i.notaReavaliacao AS notaReavaliacao, i.reavaliacaoSolicitada AS reavaliacaoSolicitada, i.prioridade AS prioridade FROM Inscricao i JOIN i.usuario u WHERE i.exame.id = :exameId")
    List<Map<String, Object>> findInscricoesByExame(@Param("exameId") Integer exameId);


}


