package org.amqptest.command.connection;

import org.amqptest.command.CommandFactory;

public class ConnectionStartOkFactory implements CommandFactory<ConnectionStartOk> {
    @Override
    public ConnectionStartOk createCommand(byte[] commandPayload) {
        ConnectionStartOk connectionStartOk = new ConnectionStartOk();
        connectionStartOk.fillArguments(commandPayload);
        return connectionStartOk;
    }
}
