package ufc.npi.proficiencia.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import ufc.npi.proficiencia.exception.ProficienciaException;
import ufc.npi.proficiencia.model.Exame;
import ufc.npi.proficiencia.model.Usuario;

import java.util.List;
import java.util.Map;

public interface ExameController {
    @ApiOperation(
            value = "Cadastrar exame",
            notes = "Este controller é responsável por cadastrar um novo exame"
    )
    ResponseEntity<Integer> cadastrarExame(Exame exame) throws ProficienciaException;

    @ApiOperation(
            value = "Listar todos os exames",
            notes = "Este controller é responsável por listar todos os exames"
    )
    ResponseEntity<List<Map<String, Object>>> listarExames();

    @ApiOperation(
            value = "Listar exames em aberto para o candidato",
            notes = "Este controller é responsável por listar os exames em que o candidato não está inscrito"
    )
    ResponseEntity<List<Exame>> listarExamesCandidatoNaoInscrito(Usuario usuario);

    @ApiOperation(
            value = "Atualiza um exame",
            notes = "Este controller é responsável por atualizar os dados de um exame"
    )
    ResponseEntity atualizar(Exame exame) throws ProficienciaException;

    @ApiOperation(
            value = "Excluir um exame",
            notes = "Este controller é responsável por excluir um exame em que não haja inscritos"
    )
    ResponseEntity deletarExame(Integer exameId) throws ProficienciaException;

    @ApiOperation(
            value = "Visualizar informações do exame",
            notes = "Este controller é responsável por exibir as informações de um determinado exame, ao passar o ID do exame"
    )
    ResponseEntity<Exame> informacaoExame(Integer exameId);
    @ApiOperation(
            value = "Encerra a fase de inscrição em um exame",
            notes = "Este controller é responsável por encerrar as inscrições"
    )
    ResponseEntity<Map<String, Object>> encerrarFaseInscricao(Integer id) throws ProficienciaException;

    @ApiOperation(
            value = "Encerra a fase de seleção de um exame",
            notes = "Este controller é responsável por encerrar as seleções"
    )
    ResponseEntity<Map<String, Object>> encerrarFaseSelecao(Exame exame) throws ProficienciaException;
}
