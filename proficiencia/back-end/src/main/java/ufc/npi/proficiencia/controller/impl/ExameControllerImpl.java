package ufc.npi.proficiencia.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ufc.npi.proficiencia.controller.ExameController;
import ufc.npi.proficiencia.exception.ProficienciaException;
import ufc.npi.proficiencia.model.Exame;
import ufc.npi.proficiencia.model.Usuario;
import ufc.npi.proficiencia.service.ExameService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/exame")
public class ExameControllerImpl implements ExameController {

    @Autowired
    private ExameService exameService;

    @Override
    @PostMapping
    public ResponseEntity<Integer> cadastrarExame(@Valid @RequestBody Exame exame) throws ProficienciaException {
        Exame exame2 = this.exameService.cadastrarExame(exame);
        return new ResponseEntity<>(exame2.getId(), HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/todos")
    public ResponseEntity<List<Map<String, Object>>> listarExames() {
        List<Map<String, Object>> exames = this.exameService.buscarExames();
        return new ResponseEntity<>(exames, HttpStatus.OK);
    }

    @Override
    @GetMapping("/todos-candidato-nao-inscrito")
    public ResponseEntity<List<Exame>> listarExamesCandidatoNaoInscrito(@AuthenticationPrincipal Usuario usuario) {
        List<Map<String, Object>> exames = this.exameService.buscarExamesCandidatoNaoInscrito(usuario.getId());
        return new ResponseEntity(exames, HttpStatus.OK);
    }

    @Override
    @PutMapping
    public ResponseEntity atualizar(@RequestBody Exame exame) throws ProficienciaException {
        this.exameService.atualizarExame(exame);
        return new ResponseEntity(exame.getId(), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{exameId}")
    public ResponseEntity deletarExame(@PathVariable("exameId") Integer exameId) throws ProficienciaException {
        exameService.deleteExame(exameId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @GetMapping("/{exameId}")
    public ResponseEntity<Exame> informacaoExame(
            @PathVariable("exameId") Integer exameId) {
        return new ResponseEntity(exameService.buscarExamePorId(exameId), HttpStatus.OK);
    }

    @Override
    @PutMapping("/encerrarInscricao/{exameId}")
    public ResponseEntity<Map<String, Object>> encerrarFaseInscricao (@PathVariable("exameId") Integer exameId) throws ProficienciaException {
        this.exameService.encerrarFaseInscricao(exameId);
        return new ResponseEntity(exameService.buscarExamePorId(exameId), HttpStatus.OK);
    }

    @Override
    @PutMapping("/encerrarSelecao/{exameId}")
    public ResponseEntity<Map<String, Object>> encerrarFaseSelecao (@RequestBody Exame exame) throws ProficienciaException {
        this.exameService.encerrarFaseSelecao(exame);
        return new ResponseEntity(exameService.buscarExamePorId(exame.getId()), HttpStatus.OK);
    }
}
