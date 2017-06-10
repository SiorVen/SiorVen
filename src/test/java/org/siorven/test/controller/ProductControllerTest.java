package org.siorven.test.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.siorven.contexts.ControllerContext;
import org.siorven.controller.webint.ProductController;
import org.siorven.controller.webint.forms.IngredientForm;
import org.siorven.dao.IngredientDao;
import org.siorven.dao.MachineProductDao;
import org.siorven.dao.ProductDao;
import org.siorven.dao.ResourceDao;
import org.siorven.model.Ingredient;
import org.siorven.model.MachineProduct;
import org.siorven.model.Product;
import org.siorven.model.Resource;
import org.siorven.services.IngredientService;
import org.siorven.services.MachineProductService;
import org.siorven.services.ProductService;
import org.siorven.services.ResourceService;
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
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Andoni on 09/06/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({@ContextConfiguration}

)

@WebAppConfiguration
public class ProductControllerTest {

    @Configuration
    static class ProductControllerTestConfig extends ControllerContext {
        @Bean
        public ProductDao productDao() {
            return mock(ProductDao.class);
        }

        @Bean
        public LocaleResolver localeResolver() {
            return mock(LocaleResolver.class);
        }

        @Bean
        public ProductController productController() {
            return new ProductController();
        }

        @Bean
        public ProductService productService() {
            return mock(ProductService.class);
        }

        @Bean
        public MachineProductService machineProductService() {
            return mock(MachineProductService.class);
        }

        @Bean
        public MachineProductDao machineProductDao() {
            return mock(MachineProductDao.class);
        }

        @Bean
        public ResourceDao resourceDao() {
            return mock(ResourceDao.class);
        }

        @Bean
        public ResourceService resourceService() {
            return mock(ResourceService.class);
        }

        @Bean
        public IngredientDao ingredientDao() {
            return mock(IngredientDao.class);
        }

        @Bean
        public IngredientService igredientService() {
            return mock(IngredientService.class);
        }


    }

    private MockMvc mockMvc;

    @Autowired
    private ProductController productController;

    @Autowired
    private ProductService productService;

    @Autowired
    private ViewResolver viewResolver;

    @Autowired
    private LocaleResolver localeResolver;

    @Autowired
    MachineProductService machineProductService;

    @Autowired
    IngredientService ingredientService;

    @Autowired
    ResourceService resourceService;

    Product product;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).setViewResolvers(viewResolver).build();

        Resource resource = new Resource();
        resource.setId(1);
        resource.setName("testResource");

        Ingredient ingredient = new Ingredient();
        ingredient.setId(1);
        ingredient.setQuantity(5);
        ingredient.setResource(resource);


        List<Ingredient> recipe = new ArrayList<>();
        recipe.add(ingredient);

        product = new Product();
        product.setId(1);
        product.setName("test");
        product.setRecipe(recipe);
        ingredient.setProduct(product);

        Mockito.when(productService.findById(1)).thenReturn(product);

        ArrayList<Product> products = new ArrayList<>();

        products.add(product);

        Mockito.when(this.productService.findAll()).thenReturn(products);

        MachineProduct mp = new MachineProduct();
        mp.setId(1);
        mp.setPrice(10);
        mp.setProduct(product);

        ArrayList<MachineProduct> mps = new ArrayList<>();
        Mockito.when(machineProductService.findByProduct(product)).thenReturn(mps);
        Mockito.when(ingredientService.findById(1)).thenReturn(ingredient);


        Mockito.when(resourceService.findByName("Test")).thenReturn(resource);

    }

    @Test
    public void testShowManager() throws Exception {
        mockMvc
                .perform(get("/product/manager"))
                .andExpect(status().isOk())
                .andExpect(flash().attributeCount(0))
                .andExpect(result ->
                        assertEquals("productManager", result.getModelAndView().getViewName()));
    }

    @Test
    public void testRegisterProduct() throws Exception {
        mockMvc
                .perform(post("/product/new")
                        .param("name", "test")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(flash().attributeCount(1))
                .andExpect(flash().attributeExists("message"))
                .andExpect(result ->
                        assertEquals("redirect:/product/manager", result.getModelAndView().getViewName()));
    }

    @Test
    public void testViewExistingProduct() throws Exception {
        mockMvc.perform(get("/product/1"))
                .andExpect(status().isOk())
                .andExpect(model().size(2))
                .andExpect(model().attributeExists("product"))
                .andExpect(model().attributeExists("ingredientForm"))
                .andExpect(mvcResult -> assertEquals("viewProduct", mvcResult.getModelAndView().getViewName()));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc
                .perform(post("/product/delete/1"))
                .andExpect(status().isOk())
                .andExpect(model().size(1))
                .andExpect(flash().attributeCount(0))
                .andExpect(model().attributeExists("message"))
                .andExpect(result ->
                        assertEquals("redirect:/product/manager", result.getModelAndView().getViewName()));
    }

    @Test
    public void testDeleteIngredient() throws Exception {
        mockMvc
                .perform(post("/ingredient/delete/1"))
                .andExpect(status().isOk())
                .andExpect(model().size(0))
                .andExpect(flash().attributeCount(1))

                .andExpect(result ->
                        assertEquals("redirect:/product/1", result.getModelAndView().getViewName()));
    }

    @Test
    public void testRegisterMachine() throws Exception {
        IngredientForm ingredient = new IngredientForm();
        ingredient.setName("Test");
        ingredient.setQty(1);
        mockMvc
                .perform(post("/product/1/ingredients/add")
                        .param("name", ingredient.getName())
                        .param("qty", String.valueOf(ingredient.getQty()))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(flash().attributeCount(1))
                .andExpect(flash().attributeExists("message"))
                .andExpect(result ->
                        assertEquals("redirect:/product/1", result.getModelAndView().getViewName()));
    }

}
