package org.amqptest.types;

import org.amqptest.AmqpServer;
import org.amqptest.types.fields.ShortString;
import org.amqptest.utils.IntUtils;

import java.nio.ByteBuffer;

public class TestFieldTableWrite {

    public static void printShortShortInt() {
        byte[] keyBytes = new ShortString("testKey").bytes();

        ByteBuffer byteBuffer = ByteBuffer.allocate(keyBytes.length + 1 + 1).order(AmqpServer.BYTE_ORDER);
        byteBuffer.put(keyBytes);
        byteBuffer.put((byte) 'b');
        byteBuffer.put((byte) 23);
        byte[] resultBytes = byteBuffer.array();

        ByteBuffer resultBuffer = ByteBuffer.allocate(4 + resultBytes.length).order(AmqpServer.BYTE_ORDER);
        byte[] tableBytes = resultBuffer.put(ByteBuffer.allocate(4).order(AmqpServer.BYTE_ORDER).putInt(resultBytes.length).array()).put(resultBytes).array();

        System.out.print('{');
        for (byte c : tableBytes) {
            System.out.print(c + ", ");
        }
        System.out.println('}');
    }

    public static void printShortInt() {
        byte[] keyBytes = new ShortString("testKey").bytes();

        ByteBuffer byteBuffer = ByteBuffer.allocate(keyBytes.length + 1 + 2).order(AmqpServer.BYTE_ORDER);
        byteBuffer.put(keyBytes);
        byteBuffer.put((byte) 'U');
        byteBuffer.putShort((short) 45);
        byte[] resultBytes = byteBuffer.array();

        ByteBuffer resultBuffer = ByteBuffer.allocate(4 + resultBytes.length).order(AmqpServer.BYTE_ORDER);
        byte[] tableBytes = resultBuffer.put(ByteBuffer.allocate(4).order(AmqpServer.BYTE_ORDER).putInt(resultBytes.length).array()).put(resultBytes).array();

        System.out.print('{');
        for (byte c : tableBytes) {
            System.out.print(c + ", ");
        }
        System.out.println('}');
    }

    public static void printLongInt() {
        byte[] keyBytes = new ShortString("testKey").bytes();

        ByteBuffer byteBuffer = ByteBuffer.allocate(keyBytes.length + 1 + 4).order(AmqpServer.BYTE_ORDER);
        byteBuffer.put(keyBytes);
        byteBuffer.put((byte) 'I');
        byteBuffer.putInt(453);
        byte[] resultBytes = byteBuffer.array();

        ByteBuffer resultBuffer = ByteBuffer.allocate(4 + resultBytes.length).order(AmqpServer.BYTE_ORDER);
        byte[] tableBytes = resultBuffer.put(ByteBuffer.allocate(4).order(AmqpServer.BYTE_ORDER).putInt(resultBytes.length).array()).put(resultBytes).array();

        System.out.print('{');
        for (byte c : tableBytes) {
            System.out.print(c + ", ");
        }
        System.out.println('}');
    }

    public static void printShortShortUnsignedInt() {
        byte[] keyBytes = new ShortString("testKey").bytes();

        ByteBuffer byteBuffer = ByteBuffer.allocate(keyBytes.length + 1 + 1).order(AmqpServer.BYTE_ORDER);
        byteBuffer.put(keyBytes);
        byteBuffer.put((byte) 'B');
        byteBuffer.put((byte) -3);
        System.out.println(IntUtils.convertToUnsignedInt((byte) -3));    //253
        byte[] resultBytes = byteBuffer.array();

        ByteBuffer resultBuffer = ByteBuffer.allocate(4 + resultBytes.length).order(AmqpServer.BYTE_ORDER);
        byte[] tableBytes = resultBuffer.put(ByteBuffer.allocate(4).order(AmqpServer.BYTE_ORDER).putInt(resultBytes.length).array()).put(resultBytes).array();

        System.out.print('{');
        for (byte c : tableBytes) {
            System.out.print(c + ", ");
        }
        System.out.println('}');
    }

    public static void printLongLongInt() {
        byte[] keyBytes = new ShortString("testKey").bytes();

        ByteBuffer byteBuffer = ByteBuffer.allocate(keyBytes.length + 1 + 8).order(AmqpServer.BYTE_ORDER);
        byteBuffer.put(keyBytes);
        byteBuffer.put((byte) 'L');
        byteBuffer.putLong(45312l);
        byte[] resultBytes = byteBuffer.array();

        ByteBuffer resultBuffer = ByteBuffer.allocate(4 + resultBytes.length).order(AmqpServer.BYTE_ORDER);
        byte[] tableBytes = resultBuffer.put(ByteBuffer.allocate(4).order(AmqpServer.BYTE_ORDER).putInt(resultBytes.length).array()).put(resultBytes).array();

        System.out.print('{');
        for (byte c : tableBytes) {
            System.out.print(c + ", ");
        }
        System.out.println('}');
    }

    public static void printLongLongUnsignedInt() {
        byte[] keyBytes = new ShortString("testKey").bytes();

        ByteBuffer byteBuffer = ByteBuffer.allocate(keyBytes.length + 1 + 8).order(AmqpServer.BYTE_ORDER);
        byteBuffer.put(keyBytes);
        byteBuffer.put((byte) 'l');
        byteBuffer.putLong(-45312l);
        byte[] resultBytes = byteBuffer.array();

        ByteBuffer resultBuffer = ByteBuffer.allocate(4 + resultBytes.length).order(AmqpServer.BYTE_ORDER);
        byte[] tableBytes = resultBuffer.put(ByteBuffer.allocate(4).order(AmqpServer.BYTE_ORDER).putInt(resultBytes.length).array()).put(resultBytes).array();

        System.out.print('{');
        for (byte c : tableBytes) {
            System.out.print(c + ", ");
        }
        System.out.println('}');
    }

    public static void printTimestamp() {
        byte[] keyBytes = new ShortString("testKey").bytes();

        ByteBuffer byteBuffer = ByteBuffer.allocate(keyBytes.length + 1 + 8).order(AmqpServer.BYTE_ORDER);
        byteBuffer.put(keyBytes);
        byteBuffer.put((byte) 'T');
        byteBuffer.putLong(453789788912l);
        byte[] resultBytes = byteBuffer.array();

        ByteBuffer resultBuffer = ByteBuffer.allocate(4 + resultBytes.length).order(AmqpServer.BYTE_ORDER);
        byte[] tableBytes = resultBuffer.put(ByteBuffer.allocate(4).order(AmqpServer.BYTE_ORDER).putInt(resultBytes.length).array()).put(resultBytes).array();

        System.out.print('{');
        for (byte c : tableBytes) {
            System.out.print(c + ", ");
        }
        System.out.println('}');
    }

    public static void printFloat() {
        byte[] keyBytes = new ShortString("testKey").bytes();

        ByteBuffer byteBuffer = ByteBuffer.allocate(keyBytes.length + 1 + 4).order(AmqpServer.BYTE_ORDER);
        byteBuffer.put(keyBytes);
        byteBuffer.put((byte) 'f');
        byteBuffer.putFloat(45312.56f);
        byte[] resultBytes = byteBuffer.array();

        ByteBuffer resultBuffer = ByteBuffer.allocate(4 + resultBytes.length).order(AmqpServer.BYTE_ORDER);
        byte[] tableBytes = resultBuffer.put(ByteBuffer.allocate(4).order(AmqpServer.BYTE_ORDER).putInt(resultBytes.length).array()).put(resultBytes).array();

        System.out.print('{');
        for (byte c : tableBytes) {
            System.out.print(c + ", ");
        }
        System.out.println('}');
    }

    public static void printDouble() {
        byte[] keyBytes = new ShortString("testKey").bytes();

        ByteBuffer byteBuffer = ByteBuffer.allocate(keyBytes.length + 1 + 8).order(AmqpServer.BYTE_ORDER);
        byteBuffer.put(keyBytes);
        byteBuffer.put((byte) 'd');
        byteBuffer.putDouble(78312.34);
        byte[] resultBytes = byteBuffer.array();

        ByteBuffer resultBuffer = ByteBuffer.allocate(4 + resultBytes.length).order(AmqpServer.BYTE_ORDER);
        byte[] tableBytes = resultBuffer.put(ByteBuffer.allocate(4).order(AmqpServer.BYTE_ORDER).putInt(resultBytes.length).array()).put(resultBytes).array();

        System.out.print('{');
        for (byte c : tableBytes) {
            System.out.print(c + ", ");
        }
        System.out.println('}');
    }

    public static void main(String[] args) {
        printLongLongUnsignedInt();
    }
}
