package org.amqptest.command.channel;

import org.amqptest.command.BaseCommandFactory;

public class ChannelOpenFactory extends BaseCommandFactory<ChannelOpen> {
    @Override
    protected ChannelOpen createCommand(short channel) {
        return new ChannelOpen(channel);
    }
}
