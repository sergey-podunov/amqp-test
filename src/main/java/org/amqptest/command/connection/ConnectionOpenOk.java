package org.amqptest.command.connection;

import org.amqptest.command.AmqpResponseCommand;
import org.amqptest.types.fields.ShortString;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ConnectionOpenOk implements AmqpResponseCommand {
    private final String knownHosts;

    public ConnectionOpenOk(String knownHosts) {
        this.knownHosts = knownHosts;
    }

    @Override
    public short getCommandId() {
        return 10;
    }

    @Override
    public short getMethodId() {
        return 41;
    }

    @Override
    public byte[] bytes() {
        return new ShortString(knownHosts).bytes();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).
                append("knownHosts", knownHosts).
                toString();
    }
}
