package org.siorven.recolector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Server {
    private static String SERVER_CONFIG = "--Ice.Config=/org/siorven/ice/config.server";

    Ice.Communicator ic = null;

    @Autowired
    private DataCollector dataCollector;

    /*public Server(String[] args){
        ic = Ice.Util.initialize(args);
    }*/

    public Server() {

    }

    public int run() {
        String[] args = new String[1];
        args[0] = SERVER_CONFIG;
        ic = Ice.Util.initialize(args);
        Ice.ObjectAdapter adapter = ic.createObjectAdapter("DataCollector");
        adapter.add(dataCollector, Ice.Util.stringToIdentity("dataCollector"));
        adapter.activate();
        System.out.println("Server running");
        ic.waitForShutdown();
        return 0;
    }
}
