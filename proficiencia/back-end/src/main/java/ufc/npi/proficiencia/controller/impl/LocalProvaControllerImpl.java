package ufc.npi.proficiencia.controller.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufc.npi.proficiencia.controller.LocalProvaController;
import ufc.npi.proficiencia.exception.ProficienciaException;
import ufc.npi.proficiencia.model.Exame;
import ufc.npi.proficiencia.model.LocalProva;
import ufc.npi.proficiencia.service.LocalProvaService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/local")
public class LocalProvaControllerImpl implements LocalProvaController {

    @Autowired
    private LocalProvaService localProvaService;

    @PostMapping("/{idExame}")
    public ResponseEntity createLocalProva(
            @RequestBody LocalProva localProva, @PathVariable("idExame") Exame exame) throws ProficienciaException {
        return new ResponseEntity<>(localProvaService.salvarLocalProva(localProva, exame), HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/{localprovaId}")
    public ResponseEntity<Map<String,Object>> visualizarLocalProva(
            @PathVariable("localprovaId") Integer localprovaId){
        return new ResponseEntity(localProvaService.findLocalProvaByExameId(localprovaId), HttpStatus.OK);
    }

    @Override
    @GetMapping("/listarLocal/{localprovaId}")
    public ResponseEntity<List<Map<String, Object>>> listarLocais(
            @PathVariable Integer localprovaId){
        return new ResponseEntity(localProvaService.findLocaisByIdExame(localprovaId), HttpStatus.OK);
    }


    @DeleteMapping("/{localId}")
    public ResponseEntity deletarLocalProva(@PathVariable("localId") Integer localId) throws ProficienciaException {
        localProvaService.deleteLocalProva(localId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @PutMapping("/moverCandidato/{idInscricao}/{idLocalProva}")
    public ResponseEntity moverCandidatoLocalProva(
            @PathVariable("idInscricao") Integer inscricao,
            @PathVariable("idLocalProva")Integer localProva) throws ProficienciaException {
         localProvaService.moverCandidatoLocalProva(inscricao,localProva);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity atualizarLocalProva(@RequestBody LocalProva local) throws ProficienciaException {
        return new ResponseEntity<>(this.localProvaService.atualizarLocal(local), HttpStatus.OK);
    }

}

