package org.amqptest.frame;

public class BodyFrameFactory implements FrameFactory<BodyFrame> {
    @Override
    public BodyFrame create(short chanel, byte[] payload) {
        return new BodyFrame(chanel, payload);
    }
}
