package org.amqptest.types;

import org.amqptest.AmqpServer;
import org.amqptest.exception.AmqpServerException;
import org.amqptest.utils.IntUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ValueReader {
    private final static Logger logger = LoggerFactory.getLogger(ValueReader.class);
    private ByteBuffer payloadBuffer;

    public ValueReader(byte[] rawPayload) {
        this.payloadBuffer = ByteBuffer.wrap(rawPayload).order(AmqpServer.BYTE_ORDER);
    }

    public LongString readLongString() {
        try {
            int size = payloadBuffer.getInt();
            byte[] rawContent = new byte[size];
            payloadBuffer.get(rawContent);
            return new LongString(new String(rawContent, "utf-8"));
        } catch (IOException e) {
            logger.error("Can't parse input stream", e);
            throw new AmqpServerException("Can't parse input stream");
        }
    }

    public ShortString readShortString() {
        try {
            int size = IntUtils.convertToUnsignedInt(payloadBuffer.get());
            byte[] rawContent = new byte[size];
            payloadBuffer.get(rawContent);
            return new ShortString(new String(rawContent, "utf-8"));
        } catch (IOException e) {
            logger.error("Can't parse input stream", e);
            throw new AmqpServerException("Can't parse input stream");
        }
    }

    public FieldTable readFieldTable() {
        return null;
    }

    public short readShort() {
        return payloadBuffer.getShort();
    }

    public int readLong() {
        return payloadBuffer.getInt();
    }
}
