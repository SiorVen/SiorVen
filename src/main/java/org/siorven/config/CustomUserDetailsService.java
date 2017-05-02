package org.siorven.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by ander on 17/04/2017.
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    //@Autowired
    //private UsuarioDao usuarioDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        //Usuario usuario = usuarioDao.findByUsername(s);
        UserDetails usuario = null;
        if(usuario != null){
            return usuario;
        }else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }
}
