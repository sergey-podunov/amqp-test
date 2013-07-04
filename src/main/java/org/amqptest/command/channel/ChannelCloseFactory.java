package org.amqptest.command.channel;

import org.amqptest.command.BaseCommandFactory;

public class ChannelCloseFactory extends BaseCommandFactory<ChannelClose> {
    @Override
    protected ChannelClose createCommand(short channel) {
        return new ChannelClose(channel);
    }
}
