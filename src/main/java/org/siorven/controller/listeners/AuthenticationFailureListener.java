package org.siorven.controller.listeners;

import org.siorven.services.LoginAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

/**
 * Listener that processes when a login attempt is not successful
 * @see LoginAttemptService
 */
@Component
public class AuthenticationFailureListener
        implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    @Autowired
    private LoginAttemptService loginAttemptService;

    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {
        WebAuthenticationDetails auth = (WebAuthenticationDetails)
                e.getAuthentication().getDetails();
        loginAttemptService.loginFailed(auth.getRemoteAddress());
    }
}
