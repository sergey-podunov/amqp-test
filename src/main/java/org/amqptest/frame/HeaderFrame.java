package org.amqptest.frame;

import java.util.Collections;
import java.util.Map;

public class HeaderFrame extends Frame<Map<String, Object>> {
    HeaderFrame(short chanel, byte[] payload) {
        super(chanel, payload);
    }

    @Override
    public Map<String, Object> getPayload() {
        return Collections.emptyMap();
    }
}
