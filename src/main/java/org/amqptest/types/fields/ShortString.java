package org.amqptest.types.fields;

import org.amqptest.AmqpServer;
import org.amqptest.exception.AmqpServerException;
import org.amqptest.types.AmqpType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ShortString implements AmqpType<String> {
    private final String content;
    private static final Logger logger = LoggerFactory.getLogger(ShortString.class);

    public ShortString(String content) {
        this.content = content;
    }

    public ShortString(byte[] commandPayload) {
        try {
            ByteBuffer payloadBuffer = ByteBuffer.wrap(commandPayload).order(AmqpServer.BYTE_ORDER);
            short size = payloadBuffer.getShort();
            byte[] rawContent = new byte[size];
            payloadBuffer.get(rawContent);
            content = new String(rawContent, "utf-8");
        } catch (IOException e) {
            logger.error("Can't parse input stream", e);
            throw new AmqpServerException("Can't parse input stream");
        }
    }

    public String getContent() {
        return content;
    }

    public byte[] bytes() {
        int size = content.getBytes().length;
        ByteBuffer byteBuffer = ByteBuffer.allocate(1 + size);
        byteBuffer.put(ByteBuffer.allocate(1).order(AmqpServer.BYTE_ORDER).put((byte) size).array());
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
