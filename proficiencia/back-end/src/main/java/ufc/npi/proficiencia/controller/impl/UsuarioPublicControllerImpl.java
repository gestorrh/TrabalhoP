package ufc.npi.proficiencia.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufc.npi.proficiencia.controller.UsuarioPublicController;
import ufc.npi.proficiencia.exception.ProficienciaException;
import ufc.npi.proficiencia.model.Token;
import ufc.npi.proficiencia.model.Usuario;
import ufc.npi.proficiencia.service.UsuarioService;

@RestController
@RequestMapping("/public/usuario")
public class UsuarioPublicControllerImpl implements UsuarioPublicController {

    @Autowired
    private UsuarioService usuarioService;


    @Override
    @GetMapping("/recuperacao")
    public ResponseEntity<Boolean> recupSenha(@RequestParam("email") String email) {
        Boolean usuBoolean = usuarioService.recuperarSenha(email);
        return new ResponseEntity(usuBoolean, HttpStatus.OK);
    }


    @Override
    @GetMapping("/validar-token")
    public ResponseEntity validarToken(@RequestParam("token") String token) {
        if (usuarioService.tokenInvalido(token) == 0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(HttpStatus.OK);
    }


    @Override
    @PostMapping("/nova-senha")
    public ResponseEntity<String> mudarSenha(@RequestBody Token token) {
        if (usuarioService.mudarSenha(token)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    @PostMapping
    public ResponseEntity<String> createUsuario(@RequestBody Usuario u) throws ProficienciaException {
        usuarioService.salvarUsuario(u);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

