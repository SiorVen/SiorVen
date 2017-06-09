package org.siorven.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.siorven.dao.MachineDao;
import org.siorven.dao.SuggestionDao;
import org.siorven.model.Machine;
import org.siorven.model.Suggestion;
import org.siorven.model.SuggestionAssociation;
import org.siorven.model.SuggestionStatistic;
import org.siorven.services.MachineService;
import org.siorven.services.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Andoni on 07/06/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SuggestionServiceTest {

    Suggestion suggestion;

    @Configuration
    static class SuggestionServiceTestConfiguration {

        @Bean
        public SuggestionService suggestionService() {
            return new SuggestionService();
        }

        @Bean
        public MachineService machineService() {
            return new MachineService();
        }

        @Bean
        public SuggestionDao suggestionDao() {
            return Mockito.mock(SuggestionDao.class);
        }

        @Bean
        public MachineDao machineDao() {
            return Mockito.mock(MachineDao.class);
        }

    }

    @Autowired
    private SuggestionDao suggestionDao;

    @Autowired
    SuggestionService suggestionService;

    @Before
    public void setup() {

        Machine machine = new Machine();
        machine.setId(1);
        Machine machine1 = new Machine();
        suggestion = new SuggestionStatistic();
        suggestion.setId(1);
        suggestion.setMachine(new Machine());
        suggestion.setWeight(10.0);
        suggestion.setGenerateDate(new Timestamp(new Date().getTime()));
        suggestion.setMachine(machine);

        Suggestion suggestion1 = new SuggestionAssociation();
        suggestion1.setId(2);
        suggestion1.setMachine(new Machine());
        suggestion1.setWeight(10.0);
        suggestion1.setGenerateDate(new Timestamp(new Date().getTime()));
        suggestion1.setMachine(machine1);

        when(suggestionDao.findById(1)).thenReturn(suggestion);
        when(suggestionDao.findById(2)).thenReturn(suggestion1);


        List<Suggestion> suggestions = new ArrayList<>();
        suggestions.add(suggestion);
        suggestions.add(suggestion1);
        when(suggestionDao.findAll()).thenReturn(suggestions);
    }

    @Test
    public void testFindById() {
        Suggestion suggestion = suggestionService.findById(1);
        assertEquals(1, suggestion.getId());
        assertEquals(10.0, suggestion.getWeight(), 0.1);
    }

    @Test
    public void testFindAll() {
        List<Suggestion> suggestions = suggestionService.findAll();
        assertEquals(2, suggestions.size());
        assertEquals(1, suggestions.get(0).getId());
        assertEquals(2, suggestions.get(1).getId());
    }
}
