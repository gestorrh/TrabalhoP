package ufc.npi.proficiencia.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import ufc.npi.proficiencia.exception.ProficienciaException;
import ufc.npi.proficiencia.model.Exame;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface ExameController {

    @ApiOperation(
            value = "Lista todos exames de um medico",
            notes = "reponsavel por listar"
    )
    ResponseEntity<List<Exame>> listarExamesMedico(Integer medicoId);

    @ApiOperation(
            value = "Lista todos Exames",
            notes = "reponsavel por listar"
    )
    ResponseEntity<Collection<Exame>> findAll(UserDetails userDetails);

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
