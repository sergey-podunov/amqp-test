package org.amqptest.frame;

public class MethodFrameFactory implements FrameFactory<MethodFrame> {
    @Override
    public MethodFrame create(short chanel, byte[] payload) {
        return new MethodFrame(chanel, payload);
    }
}
