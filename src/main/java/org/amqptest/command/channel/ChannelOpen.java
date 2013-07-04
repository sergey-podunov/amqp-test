package org.amqptest.command.channel;

import org.amqptest.ConnectionHandler;
import org.amqptest.command.AmqpRequestCommand;
import org.amqptest.command.AmqpResponseCommand;
import org.amqptest.command.BaseAmqpCommand;
import org.amqptest.exception.ProtocolException;

public class ChannelOpen extends BaseAmqpCommand implements AmqpRequestCommand {

    protected ChannelOpen(short channel) {
        super(channel);
    }

    @Override
    public AmqpResponseCommand execute(ConnectionHandler connectionHandler) throws ProtocolException {
        connectionHandler.createChannel(getChannel());
        return new ChannelOpenOk(getChannel());
    }

    @Override
    public void fillArguments(byte[] commandPayload) {
        //reserved
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
