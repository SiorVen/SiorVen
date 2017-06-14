package org.siorven.recolector;

import Ice.Current;
import org.siorven.model.Machine;
import org.siorven.model.MachineProduct;
import org.siorven.model.Recollector;
import org.siorven.model.Sale;
import org.siorven.recolector.ice.generated._DataCollectorDisp;
import org.siorven.services.MachineProductService;
import org.siorven.services.MachineService;
import org.siorven.services.RecollectorService;
import org.siorven.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DataCollector extends _DataCollectorDisp {

    @Autowired
    private SaleService saleService;

    @Autowired
    private RecollectorService recollectorService;

    @Autowired
    private MachineProductService machineProductService;

    @Override
    public void shutdown(String alias, Current current) {

        Recollector r = recollectorService.findByAlias(alias);
        if (r != null) {
            r.setConnection(false);
            recollectorService.edit(r);
        }
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
            saleService.generateSale(sale, mp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
