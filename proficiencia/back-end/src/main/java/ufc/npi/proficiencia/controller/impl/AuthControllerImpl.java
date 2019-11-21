package ufc.npi.proficiencia.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ufc.npi.proficiencia.config.JwtTokenProvider;
import ufc.npi.proficiencia.controller.AuthController;
import ufc.npi.proficiencia.model.enums.Papel;
import ufc.npi.proficiencia.repository.UsuarioRepository;
import ufc.npi.proficiencia.util.AuthenticationRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping
public class AuthControllerImpl implements AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    @PostMapping("/login")
    public ResponseEntity signin(@RequestBody AuthenticationRequest data) {

        try {
            String username = data.getCpf();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            List<String> roles = new ArrayList<>();
            for (Papel papel : this.usuarioRepository.findByCpf(username).getPapeis()) {
                roles.add(papel.name());
            }
            String token = jwtTokenProvider.createToken(username, new ArrayList());


            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("papeis", roles);
            model.put("token", "Bearer " + token);
            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

}
