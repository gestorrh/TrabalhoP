package ufc.npi.proficiencia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ufc.npi.proficiencia.model.Usuario;
import ufc.npi.proficiencia.repository.UsuarioRepository;
import ufc.npi.proficiencia.util.Messages;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCpf(username);

        if (usuario == null) {
            throw new UsernameNotFoundException(Messages.ERRO_USUARIO_NAO_ENCONTRADO);
        }

        return usuario;
    }
}
