package ufc.npi.proficiencia.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import ufc.npi.proficiencia.exception.ProficienciaException;

import java.util.List;
import java.util.Map;

public interface ArquivoController {
    @ApiOperation(
            value = "Realizar download",
            notes = "Este controller é responsável por realizar o " +
                    "download de um arquivos específico, passando o id desse arquivo"
    )
    ResponseEntity<Resource> downloadArquivo(Integer id) throws ProficienciaException;

    @ApiOperation(
            value = "Listar arquivos",
            notes = "Este controller é responsável por realizar a " +
                    "listagem de arquivos de acordo com a inscricao do candidato"
    )
    ResponseEntity<List<Map<String, Object>>> listarArquivosPorInscricao(Integer id);
}
