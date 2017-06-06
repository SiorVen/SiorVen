package org.siorven.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.siorven.dao.UserDao;
import org.siorven.exceptions.EmailInUseException;
import org.siorven.exceptions.UsernameInUseException;
import org.siorven.model.User;
import org.siorven.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class UserServiceTest {

    private User user;

    @Configuration
    static class UserServiceTestConfiguration{

        @Bean
        public UserService userService() {
            return new UserService();
        }

        @Bean
        public UserDao userDao(){
            return Mockito.mock(UserDao.class);
        }

        @Bean
        public PasswordEncoder passwordEncoder(){
            return new SCryptPasswordEncoder();
        }

    }

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void setup(){
        user = new User();
        user.setUsername("test");
        user.setPassword("test");
        user.setEmail("test@test.eus");
        user.setPermission("ROLE_ADMIN");
        user.setId(1);

        User user1 =  new User();
        user1.setUsername("test2");
        user1.setPassword("test2");
        user1.setEmail("test2@test.eus");
        user1.setPermission("ROLE_REP");
        user1.setId(2);

        when(userDao.findById(1)).thenReturn(user);
        when(userDao.findById(2)).thenReturn(user1);

        List<User> admins = new ArrayList<>();
        admins.add(user);
        when(userDao.findByRole("ROLE_ADMIN")).thenReturn(admins);

        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user1);
        when(userDao.getAllUsers()).thenReturn(users);
    }

    @Test
    public void testFindById(){
        User user = userService.findById(1);
        assertEquals(1, user.getId());
        assertEquals("test", user.getUsername());
    }

    @Test
    public void testSaveUser() throws UsernameInUseException, EmailInUseException {
        userService.save(user);
        assertTrue(passwordEncoder.matches("test", user.getPassword()));
    }

    @Test
    public void testSaveOrUpdate() throws UsernameInUseException, EmailInUseException {
        user.setPassword("test");
        userService.saveOrUpdate(user);
        assertTrue(("test".equals(user.getPassword())));
    }

    @Test
    public void testFindAll(){
        List<User> users = userService.findAll();
        assertEquals(2, users.size());
        assertEquals(1, users.get(0).getId());
        assertEquals(2, users.get(1).getId());
    }

    @Test
    public void testFindAdmins(){
        List<User> users = userService.findbyRole("ROLE_ADMIN");
        assertEquals(1, users.get(0).getId());
    }

}
