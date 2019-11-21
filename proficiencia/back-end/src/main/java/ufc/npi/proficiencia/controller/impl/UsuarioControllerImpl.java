package ufc.npi.proficiencia.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ufc.npi.proficiencia.controller.UsuarioController;
import ufc.npi.proficiencia.exception.ProficienciaException;
import ufc.npi.proficiencia.model.Usuario;
import ufc.npi.proficiencia.service.UsuarioService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
public class UsuarioControllerImpl implements UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    @PostMapping
    public ResponseEntity createUsuario(@RequestBody Usuario u) throws ProficienciaException {
        usuarioService.salvarUsuario(u);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @GetMapping("/listarUsuarios")
    public ResponseEntity<List<Map<String, Object>>> listarUsuarios() {
        List<Map<String, Object>> usuario = this.usuarioService.buscarUsuarios();
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/excluir-usuario/{usuarioId}")
    public ResponseEntity deletarUsuario(@PathVariable("usuarioId") Integer usuarioId, @AuthenticationPrincipal Usuario auth) throws ProficienciaException {
        usuarioService.excluirUsuario(usuarioId, auth.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Override
    @PutMapping
    public ResponseEntity<String> atualizarUsuario(@RequestBody Usuario usuario) {
        usuarioService.atualizarUsuario(usuario);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @GetMapping("/authenticated")
    public ResponseEntity<Map<String, Object>> mostrarDadosUsuarioLogado(@AuthenticationPrincipal Usuario auth) {
        return new ResponseEntity<>(usuarioService.buscarUsuario(auth.getId()), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> mostrarDadosUsuario(@PathVariable Integer id) {
        return new ResponseEntity<>(usuarioService.buscarUsuario(id), HttpStatus.OK);
    }

    @Override
    @PutMapping("/alterarSenha")
    public ResponseEntity alterarSenha(@AuthenticationPrincipal Usuario auth, @RequestBody Map<String, String> map) throws ProficienciaException {
        usuarioService.alterarSenha(auth, map.get("senhaAtual"), map.get("novaSenha"));
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
