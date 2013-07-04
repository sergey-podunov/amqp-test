package org.amqptest.command.connection;

import org.amqptest.command.AmqpResponseCommand;
import org.amqptest.command.BaseAmqpCommand;

public class ConnectionCloseOk extends BaseAmqpCommand implements AmqpResponseCommand {
    protected ConnectionCloseOk(short channel) {
        super(channel);
    }

    @Override
    public short getCommandId() {
        return 10;
    }

    @Override
    public short getMethodId() {
        return 51;
    }

    @Override
    public byte[] bytes() {
        return new byte[0];
    }

    @Override
    public String toString() {
        return "ConnectionCloseOk";
    }
}
