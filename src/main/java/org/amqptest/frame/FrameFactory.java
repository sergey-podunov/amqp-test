package org.amqptest.frame;

public interface FrameFactory<T extends Frame> {
    T create(short chanel, byte[] payload);
}
