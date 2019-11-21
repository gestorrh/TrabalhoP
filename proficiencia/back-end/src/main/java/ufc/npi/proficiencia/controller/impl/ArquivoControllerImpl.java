package ufc.npi.proficiencia.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ufc.npi.proficiencia.exception.ProficienciaException;
import ufc.npi.proficiencia.model.Arquivo;
import ufc.npi.proficiencia.service.ArquivoService;

import java.io.InputStream;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/arquivos")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ArquivoControllerImpl {

    @Autowired
    private ArquivoService arquivoService;

    @GetMapping("download/{id}")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public ResponseEntity<Resource> downloadArquivo(@PathVariable("id") Integer id) throws ProficienciaException {
        Arquivo arquivo = arquivoService.buscarUmArquivo(id);

        InputStream objectStream =  arquivoService.buscarArquivoDownload(id);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + arquivo.getNome())
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "nomeArquivo")
                .header("nomeArquivo", arquivo.getNome())
                .body(new InputStreamResource(objectStream));

    }

    @GetMapping("exibir/{id}")
    public ResponseEntity<List<Map<String, Object>>> listarArquivosPorInscricao(@PathVariable Integer id) {
        List<Map<String, Object>> arquivos = this.arquivoService.buscarArquivosPorInscricao(id);
        return new ResponseEntity<>(arquivos, HttpStatus.OK);
    }

}





