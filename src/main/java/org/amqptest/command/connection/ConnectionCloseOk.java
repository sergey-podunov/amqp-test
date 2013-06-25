package org.amqptest.command.connection;

import org.amqptest.ConnectionHandler;
import org.amqptest.command.AmqpCommand;
import org.amqptest.exception.ProtocolException;

public class ConnectionCloseOk implements AmqpCommand {
    @Override
    public short getCommandId() {
        return 10;
    }

    @Override
    public short getMethodId() {
        return 51;
    }

    @Override
    public AmqpCommand execute(ConnectionHandler connectionHandler) throws ProtocolException {
        throw new RuntimeException(this.getClass().getSimpleName() + " can't be consumed by server");
    }

    @Override
    public byte[] bytes() {
        return new byte[0];
    }

    @Override
    public void fillArguments(byte[] commandPayload) {
        throw new RuntimeException(this.getClass().getSimpleName() + " can't be consumed by server");
    }
}
