package ufc.npi.proficiencia.service;

import ufc.npi.proficiencia.model.Token;
import ufc.npi.proficiencia.model.Usuario;

import java.util.List;

public interface TokenService {
    void salvarToken(Token token);

    void excluirToken(Integer id);

    List<Token> buscarPorUsuario(Usuario usuario);

    Token buscarPorHash(String hash);
}
