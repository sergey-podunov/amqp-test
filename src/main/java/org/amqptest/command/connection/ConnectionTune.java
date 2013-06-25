package org.amqptest.command.connection;

import org.amqptest.AmqpServer;
import org.amqptest.command.AmqpResponseCommand;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.nio.ByteBuffer;

public class ConnectionTune implements AmqpResponseCommand {
    private final short chanelMax;
    private final int frameMax;
    private final short heartBeat;

    public ConnectionTune(short chanelMax, int frameMax, short heartBeat) {
        this.chanelMax = chanelMax;
        this.frameMax = frameMax;
        this.heartBeat = heartBeat;
    }

    @Override
    public short getCommandId() {
        return 10;
    }

    @Override
    public short getMethodId() {
        return 30;
    }

    @Override
    public byte[] bytes() {
        ByteBuffer payloadBuffer = ByteBuffer.allocate(8).order(AmqpServer.BYTE_ORDER);
        payloadBuffer.putShort(chanelMax);
        payloadBuffer.putInt(frameMax);
        payloadBuffer.putShort(heartBeat);
        return payloadBuffer.array();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).
                append("chanelMax", chanelMax).
                append("frameMax", frameMax).
                append("heartBeat", heartBeat).
                toString();
    }
}
