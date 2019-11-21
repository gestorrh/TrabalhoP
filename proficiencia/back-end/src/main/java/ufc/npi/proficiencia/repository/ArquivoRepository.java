package ufc.npi.proficiencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ufc.npi.proficiencia.model.Arquivo;

import java.util.List;
import java.util.Map;


@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo, Integer> {


    Arquivo findByNome (String nome);

    Integer deleteByNome (String nome);

    @Query("SELECT a.nome AS nome, a.observacao AS observacao, a.id AS id FROM Arquivo a, Inscricao i LEFT JOIN i.arquivos d WHERE i.id = :id AND d.id = a.id")
    List<Map<String, Object>> exibirTodosArquivosPorInscricao(@Param("id") Integer id);

}
