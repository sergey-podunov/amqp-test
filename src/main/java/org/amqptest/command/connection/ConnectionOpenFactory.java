package org.amqptest.command.connection;

import org.amqptest.command.BaseCommandFactory;

public class ConnectionOpenFactory extends BaseCommandFactory<ConnectionOpen> {

    @Override
    protected ConnectionOpen createCommand() {
        return new ConnectionOpen();
    }
}
