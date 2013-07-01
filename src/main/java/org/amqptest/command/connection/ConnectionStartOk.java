package org.amqptest.command.connection;

import org.amqptest.ConnectionHandler;
import org.amqptest.command.AmqpRequestCommand;
import org.amqptest.command.AmqpResponseCommand;
import org.amqptest.types.FieldTable;
import org.amqptest.types.ValueReader;
import org.amqptest.types.fields.LongString;
import org.amqptest.types.fields.ShortString;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ConnectionStartOk implements AmqpRequestCommand {

    private FieldTable clientProperties;     //todo use FieldTable
    private ShortString mechanism;
    private LongString securityResponse;
    private ShortString locale;

    @Override
    public AmqpResponseCommand execute(ConnectionHandler connectionHandler) {
        connectionHandler.setClientProperties(clientProperties);
        connectionHandler.setLocale(locale);
        connectionHandler.handleSecurityResponse(mechanism, securityResponse);

        return new ConnectionTune((Short) connectionHandler.getServerSettings().get("chanelMaxCount"),
                (Integer) connectionHandler.getServerSettings().get("frameMaxSize"),
                (Short) connectionHandler.getServerSettings().get("heartbeatTimeout"));
    }

    @Override
    public void fillArguments(byte[] commandPayload) {
        ValueReader valueReader = new ValueReader(commandPayload);
        clientProperties = valueReader.readFieldTable();
        mechanism = valueReader.readShortString();
        securityResponse = valueReader.readLongString();
        locale = valueReader.readShortString();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).
                append("clientProperties", clientProperties).
                append("mechanism", mechanism).
                append("securityResponse", securityResponse).
                append("locale", locale).
                toString();
    }
}
