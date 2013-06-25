package org.amqptest.frame;

public class HeaderFrameFactory implements FrameFactory<HeaderFrame> {
    @Override
    public HeaderFrame create(short chanel, byte[] payload) {
        return new HeaderFrame(chanel, payload);
    }
}
