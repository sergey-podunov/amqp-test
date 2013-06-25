package org.amqptest.command.connection;

import org.amqptest.AmqpServer;
import org.amqptest.ConnectionHandler;
import org.amqptest.command.AmqpCommand;

import java.nio.ByteBuffer;

public class ConnectionTune implements AmqpCommand {
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
    public AmqpCommand execute(ConnectionHandler connectionHandler) {
        throw new RuntimeException(this.getClass().getSimpleName() + " can't be consumed by server");
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
    public void fillArguments(byte[] commandPayload) {
        throw new RuntimeException(this.getClass().getSimpleName() + " can't be consumed by server");
    }
}
