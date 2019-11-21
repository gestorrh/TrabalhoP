package ufc.npi.proficiencia.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import ufc.npi.proficiencia.exception.ProficienciaException;
import ufc.npi.proficiencia.model.Usuario;

import java.util.List;
import java.util.Map;

public interface UsuarioController {
    @ApiOperation(
            value = "Adiciona um usuário",
            notes = "Este controller é responsável por cadastrar um usuário no sistema"
    )
    ResponseEntity<String> createUsuario(Usuario u) throws ProficienciaException;

    @ApiOperation(
            value = "Lista todos os Usuários",
            notes = "Este controller é responsável por listar todos os usuários do sistema"
    )
    ResponseEntity<List<Map<String, Object>>> listarUsuarios();

    @ApiOperation(
            value = "Exclui um Usuário",
            notes = "Este controller é responsável por deletar um usuário no sistema"
    )
    ResponseEntity deletarUsuario(Integer usuarioId, Usuario auth) throws ProficienciaException;


    @ApiOperation(
            value = "Atualizar informações do usuário",
            notes = "Este controller é responsável por atualizar as informações de um usuário"
    )
    ResponseEntity<String> atualizarUsuario(Usuario usuario);

    @ApiOperation(
            value = "Mostrar os dados do usuario logado",
            notes = "Este controller é responsável por exibir os dados do usuário que está logado no sistema"
    )
    ResponseEntity<Map<String, Object>> mostrarDadosUsuarioLogado(Usuario auth);

    @ApiOperation(
            value = "Mostrar dados de um usuário",
            notes = "Este controller é responsável por exibir os dados de um usuário a partir de seu id"
    )
    ResponseEntity<Map<String, Object>> mostrarDadosUsuario(Integer id);

    @ApiOperation(
            value = "Alterar a senha",
            notes = "Este controller é responsável por alterar a senha do usuário logado"
    )
    ResponseEntity alterarSenha(Usuario auth, Map<String, String> map) throws ProficienciaException;


}
