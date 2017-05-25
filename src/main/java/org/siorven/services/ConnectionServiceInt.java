package org.siorven.services;

import org.siorven.model.Machine;

import java.util.List;

/**
 * Created by Andoni on 22/05/2017.
 */
public interface ConnectionServiceInt {

    public int registerNewConnection(Machine m);

    public List<Machine> showConnections();

    public int responseConnectionOK(Machine m);

    public int disconnect(Machine m);

    public int editAlias(Machine m, String alias);

    public int responseMessage(Machine m, boolean response);
}
