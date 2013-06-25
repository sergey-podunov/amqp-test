package org.amqptest.command.connection;

import org.amqptest.command.BaseCommandFactory;

public class ConnectionStartOkFactory extends BaseCommandFactory<ConnectionStartOk> {

    @Override
    protected ConnectionStartOk createCommand() {
        return new ConnectionStartOk();
    }
}
