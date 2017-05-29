package org.siorven.logic;

import org.siorven.model.*;
import org.siorven.services.*;
import org.springframework.beans.factory.annotation.Autowired;
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
    public static final float PRICE = (float) 0.70;

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

    private List<Resource> resourceList;
    private List<Product> productList;

    public void initExample() {
        System.out.println("Init DB");
        try {
            resourceList = new ArrayList<>();
            productList = new ArrayList<>();

            List<Distribution> distributions = new ArrayList<>();
            List<ProductType> productTypes = new ArrayList<>();

            productTypes.add(createProductType("PT1"));
            productTypes.add(createProductType("PT2"));

            List<Slot> slots = new ArrayList<>();

            for (int i = 1; i <= 5; i++) {
                slots.add(createSlot("Slot" + i, 1, Unit.U));
            }

            distributions.add(createDistribution(MATRIX_DISTRIBUTION, "D1", 3, 3, 0, slots));
            distributions.add(createDistribution(COMPARTIMENT_DISTRIBUTION, "D2", 0, 0, 4, slots));

            MachineModel machineModel = createMachineModel("M1", "man", distributions);

            generateProduct("R1", "Choco-Bones Blancos");
            generateProduct("R2", "Manzana");
            generateProduct("R3", "ChocCons Negros");
            generateProduct("R4", "Chaskis barbacoa");
            generateProduct("R5", "Doritos");
            generateProduct("R6", "Patatas fritas");
            generateProduct("R7", "Oreo Gold");
            generateProduct("R8", "Galleta integral");
            generateProduct("R9", "Palmera chocolate");
            generateProduct("R10", "Sandwich de pavo");
            generateProduct("R11", "Palmera Integral");
            generateProduct("R12", "Cuadrado trufa");
            generateProduct("R13", "Pantera rosa");
            generateProduct("R14", "Cafe con leche");
            generateProduct("R15", "Cafe cortado");
            generateProduct("R16", "Chocolate");
            generateProduct("R17", "Te verde");

            generateMachine(slots, machineModel, "Machine1");
            generateMachine(slots, machineModel, "Machine2");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Database already created");
        }

        System.out.println("DB created");

    }

    private void generateMachine(List<Slot> slots, MachineModel machineModel, String machineName) {
        Machine machine = new Machine(machineName, machineModel);
        machineService.save(machine);

        List<MachineProduct> machineProducts = new ArrayList<>();
        List<MachineResource> machineResourceList = new ArrayList<>();

        for (int i = 0; i < productList.size(); i++) {
            List<MachineIngredient> machineRecipe = new ArrayList<>();
            MachineResource machineResource = createMachineResource(resourceList.get(i), 1, slots.get(0));
            MachineIngredient machineIngredient = createMachineIngredient(machineResource, 1);
            machineRecipe.add(machineIngredient);
            MachineProduct machineProduct1 = createMachineProduct(productList.get(i), PRICE, machineRecipe, machine);
            machineProducts.add(machineProduct1);
            machineResourceList.add(machineResource);
        }
        createMachineWithObject(machine, machineProducts, machineResourceList);
    }

    private void generateProduct(String resName, String proName) {
        Resource resource = createResource(resName, ResourceType.ITEM);
        Ingredient ingredient = createIngredient(resource, 1);
        List<Ingredient> recipe = new ArrayList<>();
        recipe.add(ingredient);
        Product product = createProduct(proName, recipe);

        resourceList.add(resource);
        productList.add(product);
    }

    public Slot createSlot(String name, int capacity, Unit unit) {
        Slot slot = new Slot(name, capacity, unit);
        slotService.save(slot);
        return slot;
    }

    public ProductType createProductType(String type) {
        ProductType pt = new ProductType(type);
        productTypeService.save(pt);
        return pt;
    }

    public Distribution createDistribution(boolean type, String description, int lines, int columns, int numCompartiments, List<Slot> slots) {
        Distribution distribution;
        if (type == MATRIX_DISTRIBUTION) {
            distribution = new MatrixDistribution(description, lines, columns);
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

    public Resource createResource(String name, ResourceType resourceType) {
        Resource resource = new Resource(name, resourceType);
        resourceService.save(resource);
        return resource;
    }

    public MachineResource createMachineResource(Resource resource, int quantity, Slot slot) {
        MachineResource machineResource = new MachineResource(resource, quantity, new Timestamp(new Date().getTime()), new Timestamp((new Date().getTime()) + WEEK_IN_MILIS), slot);
        machineResourceService.save(machineResource);
        return machineResource;
    }

    public Ingredient createIngredient(Resource resource, int quantity) {
        Ingredient ingredient = new Ingredient(resource, quantity);
        ingredientService.save(ingredient);
        return ingredient;
    }

    public MachineIngredient createMachineIngredient(MachineResource resource, int qty) {
        MachineIngredient machineIngredient = new MachineIngredient(resource, qty);
        machineIngredientService.save(machineIngredient);
        return machineIngredient;
    }

    public Product createProduct(String name, List<Ingredient> recipe) {
        Product product = new Product(name, recipe);
        productService.save(product);
        return product;
    }

    public MachineProduct createMachineProduct(Product product, float price, List<MachineIngredient> recipe, Machine machine) {
        MachineProduct machineProduct = new MachineProduct(product, price, recipe, machine);
        machineProductService.save(machineProduct);
        return machineProduct;
    }

    public Machine createMachineWithObject(Machine machine, List<MachineProduct> productList, List<MachineResource> resourceList) {
        machine.setMachineProductList(productList);
        machine.setMachineResourceList(resourceList);
        machineService.edit(machine);
        return machine;
    }
}
