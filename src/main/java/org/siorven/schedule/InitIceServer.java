package org.siorven.schedule;

import org.siorven.recolector.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Launches the Ice server on startup
 */
@Component
public class InitIceServer {

    @Autowired
    private Server server;

    @Scheduled(fixedRate = Long.MAX_VALUE)
    public void initServer() {
        server.run();
    }
}
