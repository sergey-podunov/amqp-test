package org.amqptest.command.connection;

import org.amqptest.command.AmqpResponseCommand;
import org.amqptest.types.fields.LongString;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ConnectionSecure implements AmqpResponseCommand {

    private final LongString securityData;

    public ConnectionSecure(LongString securityData) {
        this.securityData = securityData;
    }

    @Override
    public short getCommandId() {
        return 10;
    }

    @Override
    public short getMethodId() {
        return 20;
    }

    @Override
    public byte[] bytes() {
        return securityData.bytes();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).
                append("securityData", securityData.getContent()).
                toString();
    }
}
