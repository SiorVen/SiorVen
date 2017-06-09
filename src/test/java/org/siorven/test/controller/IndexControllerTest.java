package org.siorven.test.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.siorven.contexts.ControllerContext;
import org.siorven.controller.webint.IndexController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class IndexControllerTest {

    @Configuration
    static class IndexControllerTestConfig extends ControllerContext {

        @Bean
        public IndexController indexController() {
            return new IndexController();
        }

    }

    @Autowired
    private IndexController indexController;
    @Autowired
    private ViewResolver viewResolver;

    private MockMvc mockMvc;


    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(indexController).setViewResolvers(viewResolver).build();
    }

    @Test
    public void tetsIndexPage() throws Exception {
        mockMvc
                .perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(result -> assertEquals("index", result.getModelAndView().getViewName()))
                .andExpect(model().size(0));
    }


    @Test
    public void tetsLoginPage() throws Exception {
        mockMvc
                .perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(result ->
                        assertEquals("login", result.getModelAndView().getViewName()))
                .andExpect(model().size(0));
    }
}
