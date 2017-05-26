package org.siorven.recommender;

import org.siorven.model.*;
import org.siorven.services.IngredientService;
import org.siorven.services.ProductService;
import org.siorven.services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import weka.core.Attribute;
import weka.core.Instances;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


/**
 * Created by joseb on 17/05/2017.
 */
@Component
public class CreateArff {

    @Autowired
    private ProductService productService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private IngredientService ingredientService;

    private static List<Product> productList;

    //@Scheduled(fixedRate = 10000)
    public void probe() {
        System.out.println("STARTING PROBE!!!!");
        ArrayList<Attribute> atts = new ArrayList();
        Vector attVals = new Vector();
        productList = productService.findAll();

        attVals.addElement("t");
        for (Product p : productList) {
            atts.add(new Attribute(p.getName(), attVals));
        }

        Instances data = new Instances("prueba", atts, 0);
        System.out.println(data);

    }


    /**
     * Create a {@link Product} with a unique {@link Ingredient} and a unique {@link Resource} and add them into the database
     *
     * @param name
     * @return The product created.
     */
    private Product createSolidProduct(String name) {
        Resource resource = new Resource(name, ResourceType.ITEM);
        resourceService.saveOrUpdate(resource);
        Ingredient ingredient = new Ingredient(resource, 1);
        ingredientService.saveOrUpdate(ingredient);
        List<Ingredient> list = new ArrayList<>();
        list.add(ingredient);
        Product product = new Product(name, list);
        productService.saveOrUpdate(product);
        return product;
    }
}
