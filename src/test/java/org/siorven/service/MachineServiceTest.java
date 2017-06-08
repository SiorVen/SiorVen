package org.siorven.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.siorven.dao.MachineDao;
import org.siorven.model.Machine;
import org.siorven.services.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Andoni on 08/06/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class MachineServiceTest {

    private Machine machine;

    @Configuration
    static class UserServiceTestConfiguration{

        @Bean
        public MachineService machineService() {
            return new MachineService();
        }

        @Bean
        public MachineDao machineDao(){
            return Mockito.mock(MachineDao.class);
        }

    }

    @Autowired
    private MachineDao machineDao;

    @Autowired
    private MachineService machineService;

    @Before
    public void setup(){
        machine = new Machine();
        machine.setId(1);
        machine.setAlias("Machine test");

        Machine machine1 = new Machine();
        machine1.setId(2);
        machine1.setAlias("Machine test 2");

        when(machineDao.findById(1)).thenReturn(machine);
        when(machineDao.findById(2)).thenReturn(machine1);

        List<Machine> machines = new ArrayList<>();
        machines.add(machine);
        machines.add(machine1);
        when(machineDao.findAll()).thenReturn(machines);
    }

    @Test
    public void testFindById(){
        Machine machine = machineService.findById(1);
        assertEquals(1, machine.getId());
        assertEquals("Machine test", machine.getAlias());
    }

    @Test
    public void testFindAll(){
        List<Machine> allmachines = machineService.findAll();
        assertEquals(2, allmachines.size());
        assertEquals(1, allmachines.get(0).getId());
        assertEquals(2, allmachines.get(1).getId());
    }

}
