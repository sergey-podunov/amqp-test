package org.amqptest.types;

import org.amqptest.AmqpServer;
import org.amqptest.exception.AmqpServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ShortString {
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
        ByteBuffer byteBuffer = ByteBuffer.allocate(2 + size);
        byteBuffer.put(ByteBuffer.allocate(2).order(AmqpServer.BYTE_ORDER).putShort((short) size).array());
        byteBuffer.put(content.getBytes());
        return byteBuffer.array();
    }
}
