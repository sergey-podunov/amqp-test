package org.amqptest.frame;

public class BodyFrame extends Frame<byte[]> {
    BodyFrame(short chanel, byte[] payload) {
        super(chanel, payload);
    }

    @Override
    public byte[] getPayload() {
        return new byte[0];  //To change body of implemented methods use File | Settings | File Templates.
    }
}
