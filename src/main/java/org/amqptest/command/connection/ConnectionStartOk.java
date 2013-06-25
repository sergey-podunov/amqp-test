package org.amqptest.command.connection;

import org.amqptest.ConnectionHandler;
import org.amqptest.command.AmqpRequestCommand;
import org.amqptest.command.AmqpResponseCommand;
import org.amqptest.types.LongString;
import org.amqptest.types.ShortString;
import org.amqptest.types.ValueReader;

public class ConnectionStartOk implements AmqpRequestCommand {

    private LongString clientProperties;     //todo use FieldTable
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
        clientProperties = valueReader.readLongString();
        mechanism = valueReader.readShortString();
        securityResponse = valueReader.readLongString();
        locale = valueReader.readShortString();
    }
}
