package org.amqptest.types.fields;

import org.amqptest.AmqpServer;
import org.amqptest.types.AmqpType;

import java.nio.ByteBuffer;

public class LongString implements AmqpType<String> {
    private final String content;

    private final byte[] rawContent;

    public LongString(String content) {
        this.content = content;
        this.rawContent = content.getBytes();
    }

    public LongString(byte[] rawContent) {
        this.rawContent = rawContent;
        this.content = new String(rawContent);
    }

    public String getContent() {
        return content;
    }

    public byte[] getRawContent() {
        return rawContent;
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
