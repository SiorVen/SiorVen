package org.siorven.utils;

import org.siorven.model.User;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;

/**
 * Some utilities for the user handling
 */
public class UserUtils {

    /**
     * Gets the permision codo to human readable internationalized text
     *
     * @param permission     The permision key
     * @param messageSource  The Message Source
     * @param localeResolver The locale Resolver
     * @param request        The request
     * @return The Internationalized representation of the permission type
     */
    public static String permissionCodeToHuman(String permission, MessageSource messageSource, LocaleResolver localeResolver, HttpServletRequest request) {
        switch (permission) {
            case User.ROLE_ADMIN:
                return messageSource.getMessage("role.admin", null, localeResolver.resolveLocale(request));
            case User.ROLE_REPONEDOR:
                return messageSource.getMessage("role.reponedor", null, localeResolver.resolveLocale(request));
            default:
                return messageSource.getMessage("role.unknown", null, localeResolver.resolveLocale(request));
        }
    }

    /**
     * Gets the currently logged in user
     *
     * @return The user
     */
    public static User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }


}
