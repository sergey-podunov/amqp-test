package org.amqptest.command.connection;

import org.amqptest.ConnectionHandler;
import org.amqptest.command.AmqpRequestCommand;
import org.amqptest.command.AmqpResponseCommand;
import org.amqptest.exception.ProtocolException;
import org.amqptest.types.ShortString;
import org.amqptest.types.ValueReader;

public class ConnectionOpen implements AmqpRequestCommand {
    private ShortString path;

    @Override
    public AmqpResponseCommand execute(ConnectionHandler connectionHandler) throws ProtocolException {
        connectionHandler.setPath(path.getContent());
        return new ConnectionOpenOk("/");
    }

    @Override
    public void fillArguments(byte[] commandPayload) {
        ValueReader valueReader = new ValueReader(commandPayload);
        path = valueReader.readShortString();
    }
}
