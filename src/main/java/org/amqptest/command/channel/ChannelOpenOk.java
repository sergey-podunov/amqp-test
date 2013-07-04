package org.amqptest.command.channel;

import org.amqptest.command.AmqpResponseCommand;
import org.amqptest.command.BaseAmqpCommand;
import org.amqptest.types.fields.LongString;

public class ChannelOpenOk extends BaseAmqpCommand implements AmqpResponseCommand {

    protected ChannelOpenOk(short channel) {
        super(channel);
    }

    @Override
    public short getCommandId() {
        return 20;
    }

    @Override
    public short getMethodId() {
        return 11;
    }

    @Override
    public byte[] bytes() {
//        return new byte[0];
        return new LongString(String.valueOf(getChannel())).bytes();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
