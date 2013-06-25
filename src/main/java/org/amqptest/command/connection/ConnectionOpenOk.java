package org.amqptest.command.connection;

import org.amqptest.ConnectionHandler;
import org.amqptest.command.AmqpCommand;
import org.amqptest.exception.ProtocolException;
import org.amqptest.types.ShortString;

public class ConnectionOpenOk implements AmqpCommand {
    private final String knownHosts;

    public ConnectionOpenOk(String knownHosts) {
        this.knownHosts = knownHosts;
    }

    @Override
    public short getCommandId() {
        return 10;
    }

    @Override
    public short getMethodId() {
        return 41;
    }

    @Override
    public AmqpCommand execute(ConnectionHandler connectionHandler) throws ProtocolException {
        throw new RuntimeException("ConnectionTuneOk can't be consumed by server");
    }

    @Override
    public byte[] bytes() {
        return new ShortString(knownHosts).bytes();
    }

    @Override
    public void fillArguments(byte[] commandPayload) {
        throw new RuntimeException("ConnectionTuneOk can't be consumed by server");
    }
}