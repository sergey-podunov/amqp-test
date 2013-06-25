package org.amqptest.types;

import org.amqptest.AmqpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;

public class LongString {
    private final String content;
    private static final Logger logger = LoggerFactory.getLogger(LongString.class);

    public LongString(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public byte[] bytes() {
        int size = content.getBytes().length;
        ByteBuffer byteBuffer = ByteBuffer.allocate(4 + size);
        byteBuffer.put(ByteBuffer.allocate(4).order(AmqpServer.BYTE_ORDER).putInt(size).array());
        byteBuffer.put(content.getBytes());
        return byteBuffer.array();
    }

    @Override
    public String toString() {
        if (content == null) {
            return "null";
        }

        return content;
    }
}
