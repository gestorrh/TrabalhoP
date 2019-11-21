package ufc.npi.proficiencia.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import ufc.npi.proficiencia.exception.ProficienciaException;
import ufc.npi.proficiencia.model.Token;
import ufc.npi.proficiencia.model.Usuario;

public interface UsuarioPublicController {
    @ApiOperation(
            value = "Recuperação de senha: criação do token",
            notes = "Este controller é responsável por receber o email do usuario, verificar se ele existe no banco e criar um token, armazenar no banco e mandar um email para este endereço com o link de recuperação da senha"
    )
    ResponseEntity<Boolean> recupSenha(String email);

    @ApiOperation(
            value = "Verificação do token e mudança de senha",
            notes = "Este controller é responsável por receber um token do front end e verificar se ele está valido. Se estiver válido, ele manda um Ok para o front para ele chamar a página de mudança de senha. Caso contrário ele vai para página de token expirado"
    )
    ResponseEntity validarToken(String token);

    @ApiOperation(
            value = "Altera senha e exclui o token",
            notes = "Este controller é responsável por verificar se o token ainda está válido e alterar a senha do usuário. Se o token estiver válido, o controller salva a nova senha do usuario"
    )
    ResponseEntity<String> mudarSenha(Token token);

    @ApiOperation(
            value = "Adiciona um usuário",
            notes = "Este controller é responsável por cadastrar um usuário do papel 'CANDIDATO' no sistema")
    ResponseEntity<String> createUsuario(Usuario u) throws ProficienciaException;
}
