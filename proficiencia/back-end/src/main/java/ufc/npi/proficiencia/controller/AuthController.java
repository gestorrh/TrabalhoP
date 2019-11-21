package ufc.npi.proficiencia.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import ufc.npi.proficiencia.util.AuthenticationRequest;


public interface AuthController {
    @ApiOperation(
            value = "Realizar login",
            notes = "Este controller é responsável por realizar o login do usuário no sistema"
    )
    ResponseEntity signin(AuthenticationRequest data);
}
