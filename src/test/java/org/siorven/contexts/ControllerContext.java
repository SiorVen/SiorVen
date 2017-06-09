package org.siorven.contexts;

import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Created by Andoni on 08/06/2017.
 */
public class ControllerContext {
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("org.siorven.msgs.fields", "org.siorven.msgs.general", "org.siorven.msgs.pages", "ValidationMessages");
        return messageSource;
    }

    @Bean
    public ViewResolver viewResolver() throws Exception {
        ViewResolver viewResolver = Mockito.mock(ViewResolver.class);
        View view = Mockito.mock(View.class);
        Mockito.doNothing().when(view).render(Mockito.anyMap(), Mockito.any(HttpServletRequest.class), Mockito.any(HttpServletResponse.class));
        Mockito.when(viewResolver.resolveViewName(Mockito.anyString(), Mockito.any(Locale.class))).thenReturn(view);
        return viewResolver;
    }
}
