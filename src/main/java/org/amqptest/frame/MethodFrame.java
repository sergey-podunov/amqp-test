package org.amqptest.frame;

import org.amqptest.AmqpServer;
import org.amqptest.command.CommandMethodId;

import java.nio.ByteBuffer;

public class MethodFrame extends Frame<MethodFrame.RawCommand> {

    private MethodFrame.RawCommand rawCommand;

    MethodFrame(short chanel, byte[] payload) {
        super(chanel, payload);
        ByteBuffer payloadBuffer = ByteBuffer.wrap(payload).order(AmqpServer.BYTE_ORDER);
        short commandId = payloadBuffer.getShort();
        short methodId = payloadBuffer.getShort();
        byte[] commandPayload = new byte[payload.length - 4];
        payloadBuffer.get(commandPayload);
        rawCommand = new MethodFrame.RawCommand(commandId, methodId, commandPayload);
    }

    public byte[] getCommandPayload() {
        return rawCommand.getCommandPayload();
    }

    @Override
    public MethodFrame.RawCommand getPayload() {
        return rawCommand;
    }

    public static class RawCommand {

        private CommandMethodId commandMethodId;
        private byte[] commandPayload;

        public RawCommand(short commandId, short methodId, byte[] commandPayload) {
            this.commandPayload = commandPayload;
            commandMethodId = new CommandMethodId(commandId, methodId);
        }

        public CommandMethodId getCommandMethodId() {
            return commandMethodId;
        }

        public byte[] getCommandPayload() {
            return commandPayload;
        }
    }
}
