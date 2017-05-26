package org.siorven.logic;

import org.siorven.model.*;
import org.siorven.services.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Gorospe on 26/05/2017.
 */
public class Initializer {

    public static final boolean MATRIX_DISTRIBUTION = true;
    public static final boolean COMPARTIMENT_DISTRIBUTION = false;
    public static final int WEEK_IN_MILIS = 604800000;

    private SlotService slotService;

    private ProductTypeService productTypeService;

    private DistributionService distributionService;

    private MachineModelService machineModelService;

    private ResourceService resourceService;

    private MachineResourceService machineResourceService;

    private IngredientService ingredientService;

    private MachineIngredientService machineIngredientService;

    private ProductService productService;

    private MachineProductService machineProductService;

    private MachineService machineService;


    public void initExample(){
        List<ProductType> productTypes = new ArrayList<>();
        productTypes.add(createProductType("PT1"));
        productTypes.add(createProductType("PT1"));

        List<Distribution> distributions = new ArrayList<>();
        distributions.add(createDistribution(MATRIX_DISTRIBUTION,"D1",3,3,0));
        distributions.add(createDistribution(COMPARTIMENT_DISTRIBUTION,"D2",0,0,4));

        MachineModel machineModel = createMachineModel("M1","man",productTypes,distributions);

        List<Ingredient> recipe = new ArrayList<>();
        List<Ingredient> machineRecipe = new ArrayList<>();

        Resource resource = createResource("R1","1");
        MachineResource machineResource = createMachineResource(resource,1,createSlot("S1",1,1));

        Ingredient ingredient = createIngredient(resource,1);
        MachineIngredient machineIngredient = createMachineIngredient(machineResource, 1);

        recipe.add(ingredient);

        Product product = createProduct("P1",recipe);
        //MachineProduct machineProduct = createMachineProduct(product,0.70,)

        //List

    }

    public Slot createSlot (String name, int capacity, int unit){
        Slot slot = new Slot(name,capacity,unit);
        slotService.save(slot);
        return slot;
    }

    public ProductType createProductType (String type){
        ProductType pt = new ProductType(type);
        productTypeService.save(pt);
        return pt;
    }

    public Distribution createDistribution (boolean type, String description, int lines, int columns, int numCompartiments){
        Distribution distribution;
        if (type == MATRIX_DISTRIBUTION){
            distribution = new MatrixDistribution(description,lines,columns);
        } else {
            distribution = new CompartimentDistribution(description, numCompartiments);
        }
        distributionService.save(distribution);
        return distribution;
    }

    public MachineModel createMachineModel(String description, String manufacturer, List<ProductType> productTypes, List<Distribution> distributionList) {
        MachineModel machineModel = new MachineModel(description, manufacturer, productTypes, distributionList);
        machineModelService.save(machineModel);
        return machineModel;
    }

    public Resource createResource (String name, String resourceType){
        Resource resource = new Resource(name, resourceType);
        resourceService.save(resource);
        return  resource;
    }

    public MachineResource createMachineResource(Resource resource, int quantity, Slot slot){
        MachineResource machineResource = new MachineResource(resource,quantity,new Timestamp(new Date().getTime()),new Timestamp((new Date().getTime()) + WEEK_IN_MILIS),slot);
        machineResourceService.save(machineResource);
        return machineResource;
    }

    public Ingredient createIngredient (Resource resource, int quantity){
        Ingredient ingredient = new Ingredient(resource, quantity);
        ingredientService.save(ingredient);
        return ingredient;
    }

    public MachineIngredient createMachineIngredient (MachineResource resource, int qty) {
        MachineIngredient machineIngredient = new MachineIngredient(resource, qty);
        machineIngredientService.save(machineIngredient);
        return machineIngredient;
    }

    public Product createProduct(String name, List<Ingredient> recipe){
        Product product = new Product(name, recipe);
        productService.save(product);
        return product;
    }

    public MachineProduct createMachineProduct(Product product, float price, List<MachineIngredient> recipe, Machine machine){
        MachineProduct machineProduct = new MachineProduct(product,price,recipe,machine);
        machineProductService.save(machineProduct);
        return machineProduct;
    }

    public Machine createMachine(String alias, MachineModel model, List<MachineProduct> productList, List<MachineResource> resourceList){
        Machine machine = new Machine(alias,model,productList,resourceList);
        machineService.save(machine);
        return machine;
    }
}
