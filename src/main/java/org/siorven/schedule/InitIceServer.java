package org.siorven.schedule;

import org.siorven.recolector.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by ander on 24/05/2017.
 */
@Component
public class InitIceServer {

    private static String SERVER_CONFIG = "--Ice.Config=/org/siorven/ice/config.server";

    @Autowired
    private Server server;

    @Scheduled(fixedRate = Long.MAX_VALUE)
    public void initServer() {
        /*String[] args = new String[1];
        args[0] = SERVER_CONFIG;
        Server app = new Server(args);
        */
        int status = server.run();
        System.exit(status);
    }
}
