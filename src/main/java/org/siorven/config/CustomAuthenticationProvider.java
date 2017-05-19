package org.siorven.config;

import org.siorven.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Authentication provider that verifies that the credentials are correct
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {


        //Datos del formulario de login
        String usuario = authentication.getName();
        String contraseña = (String) authentication.getCredentials();

        User user = (User) customUserDetailsService.loadUserByUsername(usuario);

        if(user != null){
            if (passwordEncoder.matches(contraseña, user.getPassword())){
                user.setPassword("");
                return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
            }
            else {
               throw new BadCredentialsException("Contraseña incorrecta");
            }
        }
        throw new BadCredentialsException("Usuario desconocido");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
