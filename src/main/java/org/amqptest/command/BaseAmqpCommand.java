package org.amqptest.command;

public abstract class BaseAmqpCommand {
    private short channel;

    protected BaseAmqpCommand(short channel) {
        this.channel = channel;
    }

    public short getChannel() {
        return channel;
    }
}
