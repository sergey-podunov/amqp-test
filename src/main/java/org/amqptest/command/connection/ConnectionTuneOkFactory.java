package org.amqptest.command.connection;

import org.amqptest.command.BaseCommandFactory;

public class ConnectionTuneOkFactory extends BaseCommandFactory<ConnectionTuneOk> {

    @Override
    protected ConnectionTuneOk createCommand() {
        return new ConnectionTuneOk();
    }
}
