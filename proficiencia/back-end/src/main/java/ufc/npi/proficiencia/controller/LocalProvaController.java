package ufc.npi.proficiencia.controller;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import ufc.npi.proficiencia.exception.ProficienciaException;
import ufc.npi.proficiencia.model.Exame;
import ufc.npi.proficiencia.model.LocalProva;

import java.util.List;
import java.util.Map;

public interface LocalProvaController {

    @ApiOperation(
            value = "Lista todos os locais prova que estiverem associados a um exame",
            notes = "Este controller é responsável por listar todos os locais de prova que estejam associados a um exame"
    )
    ResponseEntity<List<Map<String, Object>>> listarLocais(Integer exameId);

    @ApiOperation(
            value = "Exibe os detalhes de um local de prova",
            notes = "Este controller é responsável por retornar as informações relacionadas a um local de prova"
    )
    ResponseEntity<Map<String,Object>> visualizarLocalProva(Integer localprovaId);

    @ApiOperation(
            value = "Adiciona um local de prova ao exame",
            notes = "Este controller é responável por adicionar um local de prova a um exame"
    )
    ResponseEntity<LocalProva> createLocalProva (LocalProva localProva, Exame exame) throws ProficienciaException;

    @ApiOperation(
            value = "Excluir um Local de Prova",
            notes = "Este controller é responsável por excluir um Local de Prova em que não haja inscritos"
    )
    ResponseEntity deletarLocalProva(Integer localId) throws ProficienciaException;

    @ApiOperation(
            value = "Mover candidato para outro local de prova",
            notes = "Este controller é responsável por mover candidato para outro local de prova"
    )
    ResponseEntity moverCandidatoLocalProva(Integer inscricao, Integer localProva) throws ProficienciaException;

    @ApiOperation(
            value = "Atualizar as informações do Local de Prova",
            notes = "Este controller é responsável por atualizar as informações de um Local de Prova"
    )
    ResponseEntity<LocalProva> atualizarLocalProva(LocalProva local) throws ProficienciaException;


}
