package org.siorven.utils;

import org.siorven.model.User;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ander on 22/05/2017.
 */
public class UserUtils {

    /**
     * Gets the permision codo to human readable internationalized text
     * @param permission
     * @param messageSource
     * @param localeResolver
     * @param request
     * @return
     */
    public static String permissionCodeToHuman(String permission, MessageSource messageSource, LocaleResolver localeResolver, HttpServletRequest request) {
        switch (permission) {
            case User.ROLE_ADMIN:
                return messageSource.getMessage("role.admin", null, localeResolver.resolveLocale(request));
            case User.ROLE_REPONEDOR:
                return messageSource.getMessage("role.admin", null, localeResolver.resolveLocale(request));
            default:
                return messageSource.getMessage("role.unknown", null, localeResolver.resolveLocale(request));
        }
    }

}
