package org.siorven.test.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.siorven.contexts.ControllerContext;
import org.siorven.controller.webint.UserController;
import org.siorven.dao.UserDao;
import org.siorven.model.User;
import org.siorven.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;

import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class UserControllerTest {

    @Configuration
    static class IndexControllerTestConfig extends ControllerContext {
        @Bean
        public UserDao userDao() {
            return Mockito.mock(UserDao.class);
        }

        @Bean
        public LocaleResolver localeResolver() {
            return Mockito.mock(LocaleResolver.class);
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new SCryptPasswordEncoder();
        }

        @Bean
        public UserController userController() {
            return new UserController();
        }

        @Bean
        public UserService userService() {
            return Mockito.mock(UserService.class);
        }

    }

    private MockMvc mockMvc;

    @Autowired
    private UserController userController;
    @Autowired
    private UserService userService;
    @Autowired
    private ViewResolver viewResolver;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).setViewResolvers(viewResolver).build();

        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        user.setEmail("test@test.eus");
        user.setPermission("ROLE_ADMIN");
        user.setId(1);

        Mockito.when(this.userService.findById(1)).thenReturn(user);

        user = new User();
        user.setUsername("test");
        user.setPassword("test");
        user.setEmail("test@test.eus");
        user.setPermission("ROLE_ADMIN");
        user.setId(2);

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication())
                .thenReturn(new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities()));
        SecurityContextHolder.setContext(securityContext);


    }

    @Test
    public void testViewExistingUser() throws Exception {
        mockMvc
                .perform(get("/user/1"))
                .andExpect(status().isOk())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("user"))
                .andExpect(result -> assertEquals("userView", result.getModelAndView().getViewName()));
    }

    @Test
    public void testViewRegister() throws Exception {
        mockMvc
                .perform(get("/user/register"))
                .andExpect(status().isOk())
                .andExpect(model().size(2))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attributeExists("userTypes"))
                .andExpect(result ->
                        assertEquals("register", result.getModelAndView().getViewName()));
    }

    @Test
    public void testViewEdit() throws Exception {
        mockMvc
                .perform(get("/user/edit/1"))
                .andExpect(status().isOk())
                .andExpect(model().size(2))
                .andExpect(model().attributeExists("user", "userTypes"))
                .andExpect(result ->
                        assertEquals("userEdit", result.getModelAndView().getViewName()));
    }

    @Test
    public void testShowManager() throws Exception {
        mockMvc
                .perform(get("/user/manager"))
                .andExpect(status().isOk())
                .andExpect(model().size(0))
                .andExpect(result ->
                        assertEquals("userManager", result.getModelAndView().getViewName()));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc
                .perform(get("/user/delete/1"))
                .andExpect(status().isOk())
                .andExpect(model().size(0))
                .andExpect(flash().attributeCount(1))
                .andExpect(flash().attributeExists("message"))
                .andExpect(result ->
                        assertEquals("redirect:/user/manager", result.getModelAndView().getViewName()));
    }

    @Test
    public void testEditUser() throws Exception {
        User user = userService.findById(1);
        user.setUsername("testEdited");
        mockMvc
                .perform(post("/user/edit")
                        .param("username", user.getUsername())
                        .param("email", user.getEmail())
                        .param("id", String.valueOf(user.getId()))
                        .param("permission", user.getPermission())
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(flash().attributeCount(1))
                .andExpect(flash().attributeExists("message"))
                .andExpect(result ->
                        assertEquals("redirect:/user/manager", result.getModelAndView().getViewName()));
    }

    @Test
    public void testRegisterUser() throws Exception {
        User user = userService.findById(1);
        mockMvc
                .perform(post("/user/register")
                        .param("username", user.getUsername())
                        .param("email", user.getEmail())
                        .param("password", user.getPassword())
                        .param("id", String.valueOf(user.getId()))
                        .param("permission", user.getPermission())
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(flash().attributeCount(1))
                .andExpect(flash().attributeExists("message"))
                .andExpect(result ->
                        assertEquals("redirect:/user/manager", result.getModelAndView().getViewName()));
    }


}
