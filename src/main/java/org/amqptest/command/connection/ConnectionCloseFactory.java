package org.amqptest.command.connection;

import org.amqptest.command.BaseCommandFactory;

public class ConnectionCloseFactory extends BaseCommandFactory<ConnectionClose> {
    @Override
    protected ConnectionClose createCommand() {
        return new ConnectionClose();
    }
}
