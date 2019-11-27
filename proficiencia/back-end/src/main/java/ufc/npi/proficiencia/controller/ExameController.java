package ufc.npi.proficiencia.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import ufc.npi.proficiencia.exception.ProficienciaException;
import ufc.npi.proficiencia.model.Exame;

import java.util.List;
import java.util.Map;

public interface ExameController {

    @ApiOperation(
            value = "Adiciona um exame",
            notes = "Este controller é responsável por cadastrar um exame no sistema"
    )
    ResponseEntity<String> createExame(Exame e) throws ProficienciaException;

//    @ApiOperation(
//            value = "Lista todos os Exames",
//            notes = "Este controller é responsável por listar todos os exames do sistema"
//    )
//    ResponseEntity<List<Map<String, Object>>> listarExames();

    @ApiOperation(
            value = "Exclui um Exame",
            notes = "Este controller é responsável por deletar um exame no sistema"
    )
    ResponseEntity deletarExame(Integer exameId, Exame auth) throws ProficienciaException;


    @ApiOperation(
            value = "Atualizar informações do exame",
            notes = "Este controller é responsável por atualizar as informações de um exame"
    )
    ResponseEntity<String> atualizarExame(Exame exame);

//    @ApiOperation(
//            value = "Mostrar dados de um exame",
//            notes = "Este controller é responsável por exibir os dados de um exame a partir de seu id"
//    )
//    ResponseEntity<Map<String, Object>> mostrarDadosExame(Integer id);

}
