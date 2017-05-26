package org.siorven.logic;

import org.siorven.model.*;
import org.siorven.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Gorospe on 26/05/2017.
 */
@Component
public class Initializer {

    public static final boolean MATRIX_DISTRIBUTION = true;
    public static final boolean COMPARTIMENT_DISTRIBUTION = false;
    public static final int WEEK_IN_MILIS = 604800000;

    @Autowired
    private SlotService slotService;

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private DistributionService distributionService;

    @Autowired
    private MachineModelService machineModelService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private MachineResourceService machineResourceService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private MachineIngredientService machineIngredientService;

    @Autowired
    private ProductService productService;

    @Autowired
    private MachineProductService machineProductService;

    @Autowired
    private MachineService machineService;

    @Scheduled(fixedRate = 1000000)
    public void initExample(){
        System.out.println("Init DB");
        try {
            List<ProductType> productTypes = new ArrayList<>();
            productTypes.add(createProductType("PT1"));
            productTypes.add(createProductType("PT2"));

            List<Slot> slots = new ArrayList<>();
            slots.add(createSlot("S1", 1, 1));
            slots.add(createSlot("S2", 1, 1));
            slots.add(createSlot("S3", 1, 1));
            slots.add(createSlot("S4", 1, 1));
            slots.add(createSlot("S5", 1, 1));

            List<Distribution> distributions = new ArrayList<>();
            distributions.add(createDistribution(MATRIX_DISTRIBUTION, "D1", 3, 3, 0,slots));
            distributions.add(createDistribution(COMPARTIMENT_DISTRIBUTION, "D2", 0, 0, 4,slots));

            MachineModel machineModel = createMachineModel("M1", "man", distributions);

            Resource resource1 = createResource("R1", ResourceType.ITEM);
            Ingredient ingredient1 = createIngredient(resource1, 1);
            List<Ingredient> recipe1 = new ArrayList<>();
            recipe1.add(ingredient1);
            Product product1 = createProduct("ChocoBones", recipe1);

            Resource resource2 = createResource("R2", ResourceType.ITEM);
            Ingredient ingredient2 = createIngredient(resource2, 1);
            List<Ingredient> recipe2 = new ArrayList<>();
            recipe1.add(ingredient2);
            Product product2 = createProduct("Palmera", recipe2);

            Resource resource3 = createResource("R3", ResourceType.ITEM);
            Ingredient ingredient3 = createIngredient(resource3, 1);
            List<Ingredient> recipe3 = new ArrayList<>();
            recipe1.add(ingredient3);
            Product product3 = createProduct("Manzana", recipe3);

            Resource resource4 = createResource("R4", ResourceType.ITEM);
            Ingredient ingredient4 = createIngredient(resource4, 1);
            List<Ingredient> recipe4 = new ArrayList<>();
            recipe1.add(ingredient4);
            Product product4 = createProduct("Chaskys", recipe4);

            Resource resource5 = createResource("R5", ResourceType.ITEM);
            Ingredient ingredient5 = createIngredient(resource5, 1);
            List<Ingredient> recipe5 = new ArrayList<>();
            recipe1.add(ingredient5);
            Product product5 = createProduct("Pan de pipa", recipe5);

            Machine machine1 = new Machine("Machine1", machineModel);
            machineService.save(machine1);

            Machine machine2 = new Machine("Machine2", machineModel);
            machineService.save(machine2);

            List<MachineIngredient> machineRecipe1 = new ArrayList<>();
            List<MachineIngredient> machineRecipe2 = new ArrayList<>();
            List<MachineIngredient> machineRecipe3 = new ArrayList<>();

            MachineResource machineResource1 = createMachineResource(resource1, 1, slots.get(0));
            MachineResource machineResource2 = createMachineResource(resource2, 1, slots.get(1));
            MachineResource machineResource3 = createMachineResource(resource3, 1, slots.get(2));

            MachineIngredient machineIngredient1 = createMachineIngredient(machineResource1, 1);
            MachineIngredient machineIngredient2 = createMachineIngredient(machineResource2, 1);
            MachineIngredient machineIngredient3 = createMachineIngredient(machineResource3, 1);

            machineRecipe1.add(machineIngredient1);
            machineRecipe1.add(machineIngredient2);
            machineRecipe1.add(machineIngredient3);

            MachineProduct machineProduct1 = createMachineProduct(product1, (float) 0.70, machineRecipe1, machine1);
            MachineProduct machineProduct2 = createMachineProduct(product2, (float) 0.70, machineRecipe2, machine1);
            MachineProduct machineProduct3 = createMachineProduct(product3, (float) 0.70, machineRecipe3, machine1);

            List<MachineProduct> machineProducts = new ArrayList<>();
            List<MachineResource> resourceList = new ArrayList<>();

            machineProducts.add(machineProduct1);
            machineProducts.add(machineProduct2);
            machineProducts.add(machineProduct3);

            resourceList.add(machineResource1);
            resourceList.add(machineResource2);
            resourceList.add(machineResource3);

            createMachineWithObject(machine1, machineProducts, resourceList);

        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Database already created");
        }

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

    public Distribution createDistribution (boolean type, String description, int lines, int columns, int numCompartiments, List<Slot> slots){
        Distribution distribution;
        if (type == MATRIX_DISTRIBUTION){
            distribution = new MatrixDistribution(description,lines,columns);
        } else {
            distribution = new CompartimentDistribution(description, numCompartiments);
        }
        distribution.setSlots(slots);
        distributionService.save(distribution);
        return distribution;
    }

    public MachineModel createMachineModel(String description, String manufacturer, List<Distribution> distributionList) {
        MachineModel machineModel = new MachineModel(description, manufacturer, distributionList);
        machineModelService.save(machineModel);
        return machineModel;
    }

    public Resource createResource (String name, ResourceType resourceType){
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

    public Machine createMachineWithObject(Machine machine, List<MachineProduct> productList, List<MachineResource> resourceList){
        machine.setMachineProductList(productList);
        machine.setMachineResourceList(resourceList);
        machineService.edit(machine);
        return machine;
    }
}
