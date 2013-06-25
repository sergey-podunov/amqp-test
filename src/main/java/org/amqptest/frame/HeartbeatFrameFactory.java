package org.amqptest.frame;

public class HeartbeatFrameFactory implements FrameFactory<HeartbeatFrame> {
    @Override
    public HeartbeatFrame create(short chanel, byte[] payload) {
        return new HeartbeatFrame(chanel, payload);
    }
}
