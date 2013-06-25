package org.amqptest.command.connection;

import org.amqptest.ConnectionHandler;
import org.amqptest.command.AmqpCommand;
import org.amqptest.exception.ProtocolException;
import org.amqptest.types.ShortString;
import org.amqptest.types.ValueReader;

public class ConnectionOpen implements AmqpCommand {
    private ShortString path;

    @Override
    public short getCommandId() {
        return 10;
    }

    @Override
    public short getMethodId() {
        return 40;
    }

    @Override
    public AmqpCommand execute(ConnectionHandler connectionHandler) throws ProtocolException {
        connectionHandler.setPath(path.getContent());
        return new ConnectionOpenOk("/");
    }

    @Override
    public byte[] bytes() {
        return new byte[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void fillArguments(byte[] commandPayload) {
        ValueReader valueReader = new ValueReader(commandPayload);
        path = valueReader.readShortString();
    }
}
