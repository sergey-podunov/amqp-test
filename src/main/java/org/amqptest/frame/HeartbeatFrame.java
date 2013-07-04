package org.amqptest.frame;

public class HeartbeatFrame extends Frame<Object> {
    HeartbeatFrame(short chanel, byte[] payload) {
        super(chanel, payload);
    }

    @Override
    public Object getPayload() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
