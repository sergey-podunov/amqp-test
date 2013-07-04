package org.amqptest.command.channel;

import org.amqptest.command.AmqpResponseCommand;
import org.amqptest.command.BaseAmqpCommand;

public class ChannelCloseOk extends BaseAmqpCommand implements AmqpResponseCommand {

    protected ChannelCloseOk(short channel) {
        super(channel);
    }

    @Override
    public short getCommandId() {
        return 20;
    }

    @Override
    public short getMethodId() {
        return 41;
    }

    @Override
    public byte[] bytes() {
        return new byte[0];
    }

    @Override
    public String toString() {
        return "ChannelCloseOk";
    }
}
