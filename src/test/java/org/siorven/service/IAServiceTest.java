package org.siorven.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.siorven.dao.ConfigParamDao;
import org.siorven.dao.MachineDao;
import org.siorven.dao.MachineProductDao;
import org.siorven.dao.ProductDao;
import org.siorven.model.Suggestion;
import org.siorven.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import weka.associations.Apriori;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class IAServiceTest {

    @Configuration
    static class IAServiceTestConfig {
        @Bean public MachineService machineService(){ return mock(MachineService.class); }
        @Bean public MachineProductService machineProductService(){ return mock(MachineProductService.class); }
        @Bean public ProductService productService(){ return mock(ProductService.class); }
        @Bean public ConfigParamService configParamService(){ return mock(ConfigParamService.class); }
        @Bean public IAService iaService(){ return new IAService(); }
        @Bean public MachineDao machineDao() { return mock(MachineDao.class); }
        @Bean public MachineProductDao machineProductDao() { return mock(MachineProductDao.class); }
        @Bean public ProductDao productDao() { return mock(ProductDao.class); }
        @Bean public ConfigParamDao configParamDao(){ return mock(ConfigParamDao.class); }
    }

    @Autowired private IAService iaService;
    /*
    @Autowired private MachineProductService machineProductService;
    @Autowired private  MachineService machineService;
    @Autowired private ProductService productService;
    @Autowired private ConfigParamService configParamService;
    */

    @Before
    public void setup(){
        //Empty setup
    }

    @Test
    public void test() throws Exception {
        Apriori apriori = new Apriori();
        ArrayList<Attribute> attributes =  new ArrayList<>();
        ArrayList attVals = new ArrayList();
        attVals.add("t");
        Attribute attribute = new Attribute("Manzana", attVals);
        attributes.add(attribute);
        Instances instances = new Instances("test", attributes, 1);
        instances.add(new DenseInstance(1));
        apriori.buildAssociations(instances);
        List<Suggestion> suggestions = iaService.getSuggestionsFromAprioriRules(apriori, new Timestamp(new Date().getTime() - 604800000));
        assertEquals(suggestions.size(), 0);
    }



}
