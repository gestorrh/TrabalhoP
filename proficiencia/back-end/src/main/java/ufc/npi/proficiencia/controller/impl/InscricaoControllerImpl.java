package ufc.npi.proficiencia.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ufc.npi.proficiencia.controller.InscricaoController;
import ufc.npi.proficiencia.exception.ProficienciaException;
import ufc.npi.proficiencia.model.*;
import ufc.npi.proficiencia.service.InscricaoService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/inscricoes")
public class InscricaoControllerImpl implements InscricaoController {

    @Autowired
    private InscricaoService inscricaoService;

    @PutMapping("/analisar/{idExame}")
    public ResponseEntity<Map> analisarInscricao(
            @RequestBody Inscricao inscricao,
            @PathVariable ("idExame") Exame exame
    ) throws ProficienciaException{
        return new ResponseEntity<>(inscricaoService.analisarInscricao(inscricao, exame), HttpStatus.OK);
    }

    @GetMapping("/listaTeste/{inscricaoId}")
    public ResponseEntity<Map<String, Object>> teste(
            @PathVariable Integer inscricaoId
    ){
        Map<String, Object> inscricao = inscricaoService.buscarInscricaoPorExameCandidato(inscricaoId);
        if (inscricao != null) {
            return new ResponseEntity<>(inscricao, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{idInscricao}")
    public ResponseEntity<Arquivo> adicionarArquivo(
            @RequestParam MultipartFile file,
            @RequestParam String observacao,
            @PathVariable Integer idInscricao) throws IOException, ProficienciaException {
        Arquivo arq = this.inscricaoService.adicionarArquivoInscricao(file, observacao, idInscricao);
        return new ResponseEntity<>(arq, HttpStatus.OK);
    }

    @DeleteMapping("/{idArquivo}/{idInscricao}")
    public ResponseEntity deletarArquivo(
            @PathVariable Integer idInscricao,
            @PathVariable Integer idArquivo) throws ProficienciaException {
        inscricaoService.removerArquivoInscricao(idInscricao, idArquivo);
        return new ResponseEntity(HttpStatus.OK);

    }

    @Override
    @PostMapping
    public ResponseEntity<Integer> realizarInscricao(@RequestBody Map<String, Object> inscricao, @AuthenticationPrincipal Usuario usuarioAut) throws ProficienciaException {
        Inscricao insc = this.inscricaoService.realizarInscricao(inscricao, usuarioAut.getId());
        return new ResponseEntity<>(insc.getId(), HttpStatus.OK);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> listarInscricoesDoCandidato(@AuthenticationPrincipal Usuario usuario) {
        List<Map<String, Object>> inscricoes = inscricaoService.buscarInscricoesCandidato(usuario.getId());

        return new ResponseEntity<>(inscricoes, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{candidatoId}")
    public ResponseEntity<List<Map<String, Object>>> listarInscricoesDoCandidatoPorId(@PathVariable("candidatoId") Integer candidatoId) {
        List<Map<String, Object>> inscricoes = inscricaoService.buscarInscricoesCandidato(candidatoId);

        return new ResponseEntity<>(inscricoes, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{inscricaoId}/candidato")
    public ResponseEntity<Map<String, Object>> informacaoInscricaoCandidato(@PathVariable("inscricaoId") Integer inscricaoId) {
        Map<String, Object> inscricao = inscricaoService.buscarInformacoesInscricaoCandidato(inscricaoId);
        if (inscricao != null) {
            return new ResponseEntity<>(inscricao, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    @GetMapping("/{inscricaoId}/coordenador")
    public ResponseEntity<Map<String, Object>> informacaoInscricaoCoordenador(@PathVariable("inscricaoId") Integer inscricaoId) {
        Map<String, Object> inscricao = inscricaoService.buscarInscricaoCoordenador(inscricaoId);
        if (inscricao != null) {
            return new ResponseEntity<>(inscricao, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    @GetMapping("/exame/{id}")
    public ResponseEntity listarInscricoesPorExame(@PathVariable Integer id) {
        return new ResponseEntity(inscricaoService.buscarInscricoesPorExame(id), HttpStatus.OK);
    }
}
