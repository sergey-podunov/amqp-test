package org.amqptest.types;

import org.amqptest.AmqpServer;
import org.junit.Test;

import java.nio.ByteBuffer;

import static org.junit.Assert.assertEquals;

public class ValueReaderTest {

    @Test
    public void testReadShortString() throws Exception {
        String shortString = "short String";
        byte[] shortStringBytes = ByteBuffer.allocate(1 + shortString.getBytes().length).order(AmqpServer.BYTE_ORDER)
                .put((byte) shortString.getBytes().length).put(shortString.getBytes()).array();

        ValueReader valueReader = new ValueReader(shortStringBytes);
        String actualString = valueReader.readShortString().getContent();

        assertEquals("short String", actualString);
    }

    @Test
    public void testReadLongString() throws Exception {
        String longString = "long String";

        byte[] longStringBytes = ByteBuffer.allocate(4 + longString.getBytes().length).order(AmqpServer.BYTE_ORDER)
                .putInt(longString.getBytes().length).put(longString.getBytes()).array();

        ValueReader valueReader = new ValueReader(longStringBytes);
        String actualString = valueReader.readLongString().getContent();

        assertEquals("long String", actualString);
    }

}
