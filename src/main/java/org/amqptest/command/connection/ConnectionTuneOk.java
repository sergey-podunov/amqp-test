package org.amqptest.command.connection;

import org.amqptest.ConnectionHandler;
import org.amqptest.command.AmqpCommand;
import org.amqptest.command.EmptyCommand;
import org.amqptest.exception.ProtocolException;
import org.amqptest.types.ValueReader;

public class ConnectionTuneOk implements AmqpCommand {
    private short clientChannelMaxCount;
    private int clientFrameSize;
    private short clientHeartbeatTimeout;

    @Override
    public short getCommandId() {
        return 10;
    }

    @Override
    public short getMethodId() {
        return 31;
    }

    @Override
    public AmqpCommand execute(ConnectionHandler connectionHandler) throws ProtocolException {
        Short serverChanelMaxCount = (Short) connectionHandler.getServerSettings().get("chanelMaxCount");
        if (clientChannelMaxCount > serverChanelMaxCount) {
            throw new ProtocolException(String.format("Client ask too much channels : %d, server allow only %d", clientChannelMaxCount, serverChanelMaxCount));
        }
        connectionHandler.setChannelCount(clientChannelMaxCount);

        Integer serverFrameSize = (Integer) connectionHandler.getServerSettings().get("frameMaxSize");
        if (clientChannelMaxCount > serverFrameSize) {
            throw new ProtocolException(String.format("Client ask too big frame size : %d, server allow only %d", clientFrameSize, serverFrameSize));
        }
        connectionHandler.setFrameSize(clientFrameSize);

        connectionHandler.setHeartbeatTimeout(clientHeartbeatTimeout);


        return new EmptyCommand();
    }

    @Override
    public byte[] bytes() {
        throw new RuntimeException("ConnectionTuneOk can't be consumed by client");
    }

    @Override
    public void fillArguments(byte[] commandPayload) {
        ValueReader valueReader = new ValueReader(commandPayload);
        clientChannelMaxCount = valueReader.readShort();
        clientFrameSize = valueReader.readLong();
        clientHeartbeatTimeout = valueReader.readShort();
    }
}
