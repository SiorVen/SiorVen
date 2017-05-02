package org.siorven.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by ander on 17/04/2017.
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        /*
        //Datos del formulario de login
        String usuario = authentication.getName();
        String contraseña = (String) authentication.getCredentials();

        Usuario user = (Usuario) customUserDetailsService.loadUserByUsername(usuario);

        if(user != null){
            if (passwordEncoder.matches(contraseña, user.getPassword())){
                user.setClave("");
                return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
            }
            else {
               throw new BadCredentialsException("Contraseña incorrecta");
            }
        }
        throw new BadCredentialsException("Usuario desconocido");
        */
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
