package org.amqptest.command.connection;

import org.amqptest.ConnectionHandler;
import org.amqptest.command.AmqpRequestCommand;
import org.amqptest.command.AmqpResponseCommand;
import org.amqptest.command.BaseAmqpCommand;
import org.amqptest.exception.ProtocolException;
import org.amqptest.types.ValueReader;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ConnectionOpen extends BaseAmqpCommand implements AmqpRequestCommand {
    private String path;

    protected ConnectionOpen(short channel) {
        super(channel);
    }

    @Override
    public AmqpResponseCommand execute(ConnectionHandler connectionHandler) throws ProtocolException {
        connectionHandler.setPath(path);
        return new ConnectionOpenOk("/", (short) 0);
    }

    @Override
    public void fillArguments(byte[] commandPayload) {
        ValueReader valueReader = new ValueReader(commandPayload);
        path = valueReader.readShortString().toString();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).
                append("path", path).
                toString();
    }
}
