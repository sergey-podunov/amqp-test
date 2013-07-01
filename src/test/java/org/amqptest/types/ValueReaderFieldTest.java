package org.amqptest.types;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ValueReaderFieldTest {

    @Test
    public void testReadFieldTable() throws Exception {
        byte[] inputBytes = new byte[]{0, 0, 1, 27, 7, 112, 114, 111, 100, 117, 99, 116, 83, 0, 0, 0, 8, 82, 97, 98, 98, 105, 116, 77, 81, 11, 105, 110, 102, 111, 114, 109, 97, 116, 105, 111, 110, 83, 0, 0, 0, 52, 76, 105, 99, 101, 110, 115, 101, 100, 32, 117, 110, 100, 101, 114, 32, 116, 104, 101, 32, 77, 80, 76, 46, 32, 83, 101, 101, 32, 104, 116, 116, 112, 58, 47, 47, 119, 119, 119, 46, 114, 97, 98, 98, 105, 116, 109, 113, 46, 99, 111, 109, 47, 8, 112, 108, 97, 116, 102, 111, 114, 109, 83, 0, 0, 0, 4, 74, 97, 118, 97, 12, 99, 97, 112, 97, 98, 105, 108, 105, 116, 105, 101, 115, 70, 0, 0, 0, 88, 26, 101, 120, 99, 104, 97, 110, 103, 101, 95, 101, 120, 99, 104, 97, 110, 103, 101, 95, 98, 105, 110, 100, 105, 110, 103, 115, 116, 1, 22, 99, 111, 110, 115, 117, 109, 101, 114, 95, 99, 97, 110, 99, 101, 108, 95, 110, 111, 116, 105, 102, 121, 116, 1, 10, 98, 97, 115, 105, 99, 46, 110, 97, 99, 107, 116, 1, 18, 112, 117, 98, 108, 105, 115, 104, 101, 114, 95, 99, 111, 110, 102, 105, 114, 109, 115, 116, 1, 9, 99, 111, 112, 121, 114, 105, 103, 104, 116, 83, 0, 0, 0, 36, 67, 111, 112, 121, 114, 105, 103, 104, 116, 32, 40, 67, 41, 32, 50, 48, 48, 55, 45, 50, 48, 49, 51, 32, 86, 77, 119, 97, 114, 101, 44, 32, 73, 110, 99, 46, 7, 118, 101, 114, 115, 105, 111, 110, 83, 0, 0, 0, 5, 51, 46, 49, 46, 49,};

        ValueReader valueReader = new ValueReader(inputBytes);
        FieldTable fieldTable = valueReader.readFieldTable();
        Map<String, AmqpType> actualContent = fieldTable.getContent();
        assertEquals("RabbitMQ", actualContent.get("product").getContent());
        assertEquals("Java", actualContent.get("platform").getContent());
        assertEquals("Licensed under the MPL. See http://www.rabbitmq.com/", actualContent.get("information").getContent());
        assertEquals("Copyright (C) 2007-2013 VMware, Inc.", actualContent.get("copyright").getContent());
        assertEquals("3.1.1", actualContent.get("version").getContent());

        FieldTable internalFieldTable = (FieldTable) actualContent.get("capabilities");
        Map<String, AmqpType> internalContent = internalFieldTable.getContent();
        assertTrue((Boolean) internalContent.get("exchange_exchange_bindings").getContent());
        assertTrue((Boolean) internalContent.get("publisher_confirms").getContent());
        assertTrue((Boolean) internalContent.get("basic.nack").getContent());
        assertTrue((Boolean) internalContent.get("consumer_cancel_notify").getContent());

    }

    @Test
    public void testFieldTableShortShortInt() throws Exception {
        byte[] inputBytes = new byte[]{0, 0, 0, 10, 7, 116, 101, 115, 116, 75, 101, 121, 98, 23,};

        ValueReader valueReader = new ValueReader(inputBytes);
        FieldTable fieldTable = valueReader.readFieldTable();
        Map<String, AmqpType> actualContent = fieldTable.getContent();
        assertEquals((byte) 23, actualContent.get("testKey").getContent());
    }

    @Test
    public void testFieldTableShortShortUnsignedInt() throws Exception {
        byte[] inputBytes = new byte[]{0, 0, 0, 10, 7, 116, 101, 115, 116, 75, 101, 121, 66, -3,};

        ValueReader valueReader = new ValueReader(inputBytes);
        FieldTable fieldTable = valueReader.readFieldTable();
        Map<String, AmqpType> actualContent = fieldTable.getContent();
        assertEquals(253, actualContent.get("testKey").getContent());
    }

    @Test
    public void testFieldTableShortInt() throws Exception {
        byte[] inputBytes = new byte[]{0, 0, 0, 11, 7, 116, 101, 115, 116, 75, 101, 121, 85, 0, 45,};

        ValueReader valueReader = new ValueReader(inputBytes);
        FieldTable fieldTable = valueReader.readFieldTable();
        Map<String, AmqpType> actualContent = fieldTable.getContent();
        assertEquals((short) 45, actualContent.get("testKey").getContent());
    }

    @Test
    public void testFieldTableLongIntInt() throws Exception {
        byte[] inputBytes = new byte[]{0, 0, 0, 13, 7, 116, 101, 115, 116, 75, 101, 121, 73, 0, 0, 1, -59,};

        ValueReader valueReader = new ValueReader(inputBytes);
        FieldTable fieldTable = valueReader.readFieldTable();
        Map<String, AmqpType> actualContent = fieldTable.getContent();
        assertEquals(453, actualContent.get("testKey").getContent());
    }

    @Test
    public void testFieldTableLongLongInt() throws Exception {
        byte[] inputBytes = new byte[]{0, 0, 0, 17, 7, 116, 101, 115, 116, 75, 101, 121, 76, 0, 0, 0, 0, 0, 0, -79, 0,};

        ValueReader valueReader = new ValueReader(inputBytes);
        FieldTable fieldTable = valueReader.readFieldTable();
        Map<String, AmqpType> actualContent = fieldTable.getContent();
        assertEquals(45312l, actualContent.get("testKey").getContent());
    }

    @Test
    public void testFieldTableLongLongUnsignedInt() throws Exception {
        byte[] inputBytes = new byte[]{0, 0, 0, 17, 7, 116, 101, 115, 116, 75, 101, 121, 108, -1, -1, -1, -1, -1, -1, 79, 0,};

        ValueReader valueReader = new ValueReader(inputBytes);
        FieldTable fieldTable = valueReader.readFieldTable();
        Map<String, AmqpType> actualContent = fieldTable.getContent();
        assertEquals("27670116110564282112", actualContent.get("testKey").getContent().toString());
    }

    @Test
    public void testFieldTableFloat() throws Exception {
        byte[] inputBytes = new byte[]{0, 0, 0, 13, 7, 116, 101, 115, 116, 75, 101, 121, 102, 71, 49, 0, -113,};

        ValueReader valueReader = new ValueReader(inputBytes);
        FieldTable fieldTable = valueReader.readFieldTable();
        Map<String, AmqpType> actualContent = fieldTable.getContent();
        assertEquals(45312.56f, actualContent.get("testKey").getContent());
    }

    @Test
    public void testFieldTableDouble() throws Exception {
        byte[] inputBytes = new byte[]{0, 0, 0, 17, 7, 116, 101, 115, 116, 75, 101, 121, 100, 64, -13, 30, -123, 112, -93, -41, 10,};

        ValueReader valueReader = new ValueReader(inputBytes);
        FieldTable fieldTable = valueReader.readFieldTable();
        Map<String, AmqpType> actualContent = fieldTable.getContent();
        assertEquals(78312.34, actualContent.get("testKey").getContent());
    }

    @Test
    public void testFieldTableTimestamp() throws Exception {
        byte[] inputBytes = new byte[]{0, 0, 0, 17, 7, 116, 101, 115, 116, 75, 101, 121, 84, 0, 0, 0, 105, -89, -6, -86, -16,};

        ValueReader valueReader = new ValueReader(inputBytes);
        FieldTable fieldTable = valueReader.readFieldTable();
        Map<String, AmqpType> actualContent = fieldTable.getContent();
        assertEquals(453789788912l, actualContent.get("testKey").getContent());
    }
}
