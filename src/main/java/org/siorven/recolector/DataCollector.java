package org.siorven.recolector;

import Ice.Current;
import org.siorven.model.*;
import org.siorven.recolector.ice.generated._DataCollectorDisp;
import org.siorven.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DataCollector extends _DataCollectorDisp {

    private Map<String, Machine> collectorList = new HashMap<>();

    @Autowired
    private SaleService saleService;

    @Autowired
    private RecollectorService recollectorService;

    @Autowired
    private MachineProductService machineProductService;

    @Autowired
    private MachineService machineService;

    @Autowired
    private MachineResourceService machineResourceService;

    @Override
    public void shutdown(String alias, Current current) {

        Recollector r = recollectorService.findByAlias(alias);
        if (r != null) {
            r.setConnection(false);
            recollectorService.edit(r);
        }
        collectorList.remove(alias);
        //current.adapter.getCommunicator().shutdown();
    }

    @Override
    public void registerConnection(String alias, Current __current) {

        Recollector r = recollectorService.findByAlias(alias);
        if (r == null) {
            recollectorService.save(new Recollector(alias, true));
        } else if (!r.isConnection()) {
            r.setConnection(true);
            recollectorService.edit(r);
        }
        Machine m = (Machine) machineService.findAll().get(0);
        collectorList.put(alias, m);

    }

    @Override
    public void saleDone(String UID, String code, Current __current) {

        Recollector recollector = recollectorService.findByAlias(UID);
        Machine machine = recollector.getMachine();
        int pcode = Integer.parseInt(code);
        try {
            List<MachineProduct> mpList = machineProductService.findByMachine(machine);
            MachineProduct mp = mpList.get(pcode);
            Sale sale = new Sale(new Timestamp(new Date().getTime()), mp, 1);
            spendResources(mp);
            saleService.save(sale);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void spendResources(MachineProduct mp) {
        for (MachineIngredient mi : mp.getRecipe()) {
            MachineResource mr = mi.getResource();
            int quantity = mr.getQuantity() - mi.getQty();
            mr.setQuantity(quantity);
            machineResourceService.edit(mr);
        }
    }

    private void generateSaleFromCode(String code, Machine machine) {
        Map<String, Object> positionParam = new HashMap<>();
        String[] separatedCode = code.split("[:]");
        positionParam.put(separatedCode[0], Integer.parseInt(separatedCode[0]));
        positionParam.put(separatedCode[1], Integer.parseInt(separatedCode[1]));
        MachineModel model = machine.getMachineModel();
        Slot slot = null;
        for (Distribution d : model.getAviableDistributions()) {
            slot = d.findSlot(positionParam);
            if (slot != null) {
                break;
            }
        }

        if (slot != null) {
            MachineProduct mp = machineProductService.getMachineProductFromSlot(slot);
            Sale sale = new Sale(new Timestamp(new Date().getTime()), mp, 1);
            saleService.save(sale);
        }


    }

}
