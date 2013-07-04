package org.amqptest.types;

import org.amqptest.AmqpServer;
import org.amqptest.exception.AmqpServerException;
import org.amqptest.types.fields.*;
import org.amqptest.utils.IntUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class ValueReader {
    private final static Logger logger = LoggerFactory.getLogger(ValueReader.class);
    private ByteBuffer payloadBuffer;

    public ValueReader(byte[] rawPayload) {
        this.payloadBuffer = ByteBuffer.wrap(rawPayload).order(AmqpServer.BYTE_ORDER);
    }

    public LongString readLongString() {
//        try {
        int size = payloadBuffer.getInt();
        byte[] rawContent = new byte[size];
        payloadBuffer.get(rawContent);

        /*System.out.println("Long string raw content");

        ByteBuffer temp = ByteBuffer.allocate(4 + size).order(AmqpServer.BYTE_ORDER);
        temp.putInt(size);
        temp.put(rawContent);

        byte[] outArray = temp.array();

        System.out.print('[');
        for (byte c : outArray) {
            System.out.print(c + ", ");
        }
        System.out.println(']');*/

        return new LongString(rawContent);
        /*} catch (IOException e) {
            logger.error("Can't parse input stream", e);
            throw new AmqpServerException("Can't parse input stream");
        }*/
    }

    public ShortString readShortString() {
        try {
            if (!payloadBuffer.hasRemaining()) {
                return null;
            }

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
        byte[] fieldBytes = readLongString().getRawContent();
        ValueReader fieldReader = new ValueReader(fieldBytes);
        Map<String, AmqpType> content = new HashMap<String, AmqpType>();

        ShortString key;
        while ((key = fieldReader.readShortString()) != null) {
            AmqpType value = fieldReader.readField();
            content.put(key.getContent(), value);
        }

        return new FieldTable(content);
    }

    public AmqpType readField() {
        //todo implement D, A types
        char valueType = readChar();
        AmqpType value;
        switch (valueType) {
            case 'S':
                value = readLongString();
                break;
            case 's':
                value = readShortString();
                break;
            case 'F':
                value = readFieldTable();
                break;
            case 't':
                value = readBooleanType();
                break;
            case 'b':
                value = readShortShortInt();
                break;
            case 'B':
                value = readShortShortUnsignedInt();
                break;
            case 'U':
                value = readShortInt();
                break;
            case 'u':
                value = readShortInt();
                break;
            case 'I':
                value = readLongInt();
                break;
            case 'L':
                value = readLongLongInt();
                break;
            case 'l':
                value = readLongLongUnsignedInt();
                break;
            case 'f':
                value = readFloatType();
                break;
            case 'd':
                value = readDoubleType();
                break;
            case 'T':
                value = readTimestamp();
                break;

            default:
                throw new RuntimeException("Unknown FieldTable value type '" + valueType + "'");
        }
        return value;
    }

    private LongLongInt readLongLongInt() {
        return new LongLongInt(payloadBuffer.getLong());
    }

    private LongLongUnsignedInt readLongLongUnsignedInt() {
        return new LongLongUnsignedInt(payloadBuffer.getLong());
    }

    private ShortInt readShortInt() {
        return new ShortInt(payloadBuffer.getShort());
    }

    private ShortShortUnsignedInt readShortShortUnsignedInt() {
        return new ShortShortUnsignedInt(payloadBuffer.get());
    }

    private ShortShortInt readShortShortInt() {
        return new ShortShortInt(payloadBuffer.get());
    }

    private FloatType readFloatType() {
        return new FloatType(payloadBuffer.getFloat());
    }

    private DoubleType readDoubleType() {
        return new DoubleType(payloadBuffer.getDouble());
    }

    private TimestampType readTimestamp() {
        return new TimestampType(payloadBuffer.getLong());
    }

    public short readShort() {
        return payloadBuffer.getShort();
    }

    public int readLong() {
        return payloadBuffer.getInt();
    }

    public LongInt readLongInt() {
        return new LongInt(payloadBuffer.getInt());
    }

    public byte readByte() {
        return payloadBuffer.get();
    }

    public char readChar() {
        return (char) payloadBuffer.get();
    }

    public BooleanType readBooleanType() {
        byte raw = payloadBuffer.get();
        return new BooleanType(raw != 0);
    }
}
