package org.siorven.test.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.siorven.contexts.ControllerContext;
import org.siorven.controller.webint.MachineController;
import org.siorven.controller.webint.forms.MachineEditForm;
import org.siorven.controller.webint.forms.MachineModelForm;
import org.siorven.controller.webint.forms.MachineViewForm;
import org.siorven.dao.MachineDao;
import org.siorven.dao.MachineModelDao;
import org.siorven.model.Machine;
import org.siorven.model.MachineModel;
import org.siorven.services.MachineModelService;
import org.siorven.services.MachineService;
import org.siorven.services.XmlValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Andoni on 08/06/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({@ContextConfiguration}

)

@WebAppConfiguration
public class MachineControllerTest {

    @Configuration
    static class MachineControllerTestConfig extends ControllerContext {

        @Bean
        public MachineDao machineDao() {
            return mock(MachineDao.class);
        }
        @Bean
        public LocaleResolver localeResolver() {
            return mock(LocaleResolver.class);
        }

        @Bean
        public MachineController machineController(){
            return new MachineController();
        }
        @Bean
        public MachineService machineService(){
            return mock(MachineService.class);
        }
        @Bean
        public MachineModelService machineModelService() {
            return mock(MachineModelService.class);
        }

        @Bean
        public MachineModelDao machineModelDao() {
            return mock(MachineModelDao.class);
        }

        @Bean
        public XmlValidationService xmlValidationService(){
            return mock(XmlValidationService.class);
        }

    }

    private MockMvc mockMvc;

    @Autowired
    private MachineController machineController;

    @Autowired
    private MachineService machineService;

    @Autowired
    private MachineModelService machineModelService;

    @Autowired
    private ViewResolver viewResolver;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(machineController).setViewResolvers(viewResolver).build();

        MachineModel model = new MachineModel();
        model.setReference("testReference");
        model.setManufacturer("testManufacturer");
        model.setId(1);
        Machine machine = new Machine();
        machine.setAlias("Test machine");
        machine.setId(1);
        machine.setMachineModel(model);

        Machine machine1 = new Machine();
        machine.setAlias("Test machine1");
        machine.setId(2);

        Mockito.when(machineService.findById(1)).thenReturn(machine);

        ArrayList<Machine> machines = new ArrayList<>();
        machines.add(machine);
        machines.add(machine1);


        Mockito.when(machineService.findById(2)).thenReturn(machine1);
        Mockito.when(this.machineService.findAll()).thenReturn(machines);

    }

    @Test
    public void testViewExistingMachine() throws Exception {
        mockMvc.perform(get("/machine/1"))
                .andExpect(status().isOk())
                .andExpect(model().size(3))
                .andExpect(model().attributeExists("machineView"))
                .andExpect(model().attributeExists("reference"))
                .andExpect(model().attributeExists("manufacturer"))
                .andExpect(mvcResult -> assertEquals("machineView", mvcResult.getModelAndView().getViewName()));
    }
    @Test
    public void testViewMachineAdd() throws Exception {
        mockMvc
                .perform(get("/machine/register"))
                .andExpect(status().isOk())
                .andExpect(model().size(2))
                .andExpect(model().attributeExists("models"))
                .andExpect(model().attributeExists("machineModelRegister"))
                .andExpect(result ->
                        assertEquals("machineMachineRegister", result.getModelAndView().getViewName()));
    }

    @Test
    public void testViewMachineEdit() throws Exception {
        mockMvc
                .perform(get("/machine/edit/1"))
                .andExpect(status().isOk())
                .andExpect(model().size(2))
                .andExpect(model().attributeExists("models"))
                .andExpect(model().attributeExists("machine"))
                .andExpect(result ->
                        assertEquals("machineEdit", result.getModelAndView().getViewName()));
    }

    @Test
    public void testShowManager() throws Exception {
        mockMvc
                .perform(get("/machine/manager"))
                .andExpect(status().isOk())
                .andExpect(model().size(0))
                .andExpect(result ->
                        assertEquals("machineManager", result.getModelAndView().getViewName()));
    }

    @Test
    public void testEditMachine() throws Exception {
        Machine machine = machineService.findById(1);
        MachineEditForm m = new MachineEditForm();
        m.setId(1);
        m.setAlias("TestEdited");
        m.setMachineModelId(1);
        machine.setAlias("testEdited");
        mockMvc
                .perform(post("/machine/edit")
                        .param("alias",m.getAlias())
                        .param("id",String.valueOf(m.getId()))
                        .param("machineModelId", String.valueOf(m.getMachineModelId()))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(flash().attributeCount(0))
                .andExpect(result ->
                        assertEquals("machineManager", result.getModelAndView().getViewName()));
    }

    @Test
    public void testRegisterMachine() throws Exception {
        MachineEditForm machine = new MachineEditForm();
        machine.setAlias("testMAchine");
        machine.setId(3);
        machine.setMachineModelId(1);
        mockMvc
                .perform(post("/machine/register")
                        .param("alias", machine.getAlias())
                        .param("machineModelId", String.valueOf(machine.getMachineModelId()))
                        .param("id", String.valueOf(machine.getId()))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(flash().attributeCount(0))
                .andExpect(result ->
                        assertEquals("machineManager", result.getModelAndView().getViewName()));
    }


}
