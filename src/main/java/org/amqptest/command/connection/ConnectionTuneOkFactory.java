package org.amqptest.command.connection;

import org.amqptest.command.CommandFactory;

public class ConnectionTuneOkFactory implements CommandFactory<ConnectionTuneOk> {
    @Override
    public ConnectionTuneOk createCommand(byte[] commandPayload) {
        ConnectionTuneOk connectionTuneOk = new ConnectionTuneOk();
        connectionTuneOk.fillArguments(commandPayload);
        return connectionTuneOk;
    }
}
