package org.amqptest.command.connection;

import org.amqptest.ConnectionHandler;
import org.amqptest.command.AmqpCommand;
import org.amqptest.command.EmptyCommand;
import org.amqptest.types.LongString;

public class ConnectionSecure implements AmqpCommand {

    private final LongString securityData;

    public ConnectionSecure(LongString securityData) {
        this.securityData = securityData;
    }

    @Override
    public short getCommandId() {
        return 10;
    }

    @Override
    public short getMethodId() {
        return 20;
    }

    @Override
    public AmqpCommand execute(ConnectionHandler connectionHandler) {
        return new EmptyCommand();
    }

    @Override
    public byte[] bytes() {
        return securityData.bytes();
    }

    @Override
    public void fillArguments(byte[] commandPayload) {
        throw new RuntimeException("Command 'ConnectionSecure' can't parsed by server");
    }
}
