package org.amqptest.command;

import org.amqptest.ConnectionHandler;

public class EmptyCommand implements AmqpCommand {
    @Override
    public short getCommandId() {
        return -1;
    }

    @Override
    public short getMethodId() {
        return -1;
    }

    @Override
    public byte[] bytes() {
        return new byte[0];
    }

    @Override
    public AmqpCommand execute(ConnectionHandler connectionHandler) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void fillArguments(byte[] commandPayload) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
