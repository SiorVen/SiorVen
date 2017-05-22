package org.siorven.logic;

import java.util.UUID;

/**
 * Created by Andoni on 17/05/2017.
 */
public class Configuration {


    private String UID;

    private String alias;

    private String serverAddress;

    public Configuration(String UID, String alias, String serverAddress) {
        this.UID = UID;
        this.alias = alias;
        this.serverAddress = serverAddress;
    }

    public Configuration() {
    }

    public Configuration(String alias, String serverAddress) {
        this.UID = UUID.randomUUID().toString().replaceAll("-", "");
        this.alias = alias;
        this.serverAddress = serverAddress;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }
}
