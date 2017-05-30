package org.siorven.recolector;

import Ice.Current;

import org.siorven.model.Machine;
import org.siorven.recolector.ice.generated._DataCollectorDisp;
import org.siorven.services.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DataCollector extends _DataCollectorDisp {

	private Map<String,Machine> collectorList = new HashMap<>();

	@Autowired
	private MachineService machineService;

    @Override
    public void shutdown(String UID, Current current)
    {
        System.out.println("Unregister recolector: " + UID);
		collectorList.remove(UID);
        //current.adapter.getCommunicator().shutdown();
    }

	@Override
	public void registerConnection(String UID, Current __current) {
		System.out.println("New machine registered with UID: " + UID);
		Machine machine = machineService.findById(Integer.parseInt(UID));
		collectorList.put(UID, machine);
		
	}

	@Override
	public void saleDone(String UID, String code, Current __current) {
		System.out.println("Sale acomplished, code: " + code);
		Machine machine = collectorList.get(UID);
		generateSaleFromCode(code,machine);
		
	}

	private void generateSaleFromCode(String code, Machine machine) {

	}

}
