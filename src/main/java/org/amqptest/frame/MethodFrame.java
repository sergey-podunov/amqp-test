package org.amqptest.frame;

import org.amqptest.AmqpServer;
import org.amqptest.command.CommandSelector;

import java.nio.ByteBuffer;

public class MethodFrame extends Frame<MethodFrame.RawCommand> {

    private byte[] commandPayload;

    MethodFrame(short chanel, byte[] payload) {
        super(chanel, payload);
    }

    public byte[] getCommandPayload() {
        return commandPayload;
    }

    @Override
    public MethodFrame.RawCommand getPayload() {
        ByteBuffer payloadBuffer = ByteBuffer.wrap(payload).order(AmqpServer.BYTE_ORDER);
        short commandId = payloadBuffer.getShort();
        short methodId = payloadBuffer.getShort();
        byte[] commandPayload = new byte[payload.length - 4];
        payloadBuffer.get(commandPayload/*, 3, commandPayload.length*/);
        return new MethodFrame.RawCommand(commandId, methodId, commandPayload);
    }

    public static class RawCommand {

        private CommandSelector.CommandMethodId commandMethodId;
        private byte[] commandPayload;

        public RawCommand(short commandId, short methodId, byte[] commandPayload) {
            this.commandPayload = commandPayload;
            commandMethodId = new CommandSelector.CommandMethodId(commandId, methodId);
        }

        public CommandSelector.CommandMethodId getCommandMethodId() {
            return commandMethodId;
        }

        public byte[] getCommandPayload() {
            return commandPayload;
        }
    }
}
