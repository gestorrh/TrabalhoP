package ufc.npi.proficiencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ufc.npi.proficiencia.model.Token;
import ufc.npi.proficiencia.model.Usuario;

import java.util.List;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {

    List<Token> findByUsuario(Usuario usuario);

    Token findByHash(String hash);
}
