package ufc.npi.proficiencia.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ufc.npi.proficiencia.controller.ExameController;
import ufc.npi.proficiencia.exception.ProficienciaException;
import ufc.npi.proficiencia.model.Exame;
import ufc.npi.proficiencia.service.ExameService;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/exame")
public class ExameControllerImpl implements ExameController {

    @Autowired
    private ExameService exameService;

    @Override
    @PostMapping
    public ResponseEntity createExame(@RequestBody Exame exame) throws ProficienciaException {
        exameService.cadastrarExame(exame);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @Override
//    @GetMapping("/listarExames")
//    public ResponseEntity<List<Map<String, Object>>> listarExames() {
//        List<Map<String, Object>> exame = this.exameService.buscarExames();
//        return new ResponseEntity<>(exame, HttpStatus.OK);
//    }

    @Override
    @DeleteMapping("/excluir-usuario/{usuarioId}")
    public ResponseEntity deletarExame(@PathVariable("exmaeId") Integer exameId, @AuthenticationPrincipal Exame auth) throws ProficienciaException {
        exameService.deleteExame(exameId, auth.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @PutMapping
    public ResponseEntity<String> atualizarExame(@RequestBody Exame exame) {
        exameService.atualizarExame(exame);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @Override
//    @GetMapping("/{id}")
//    public ResponseEntity<Map<String, Object>> mostrarDadosExame(@PathVariable Integer id) {
//        return new ResponseEntity<>(exameService.buscarExame(id), HttpStatus.OK);
//    }

}
