package org.amqptest.utils;

public class IntUtils {
    private IntUtils() {
        //util class
    }

    public static int convertToUnsignedInt(byte b) {
        return b & 0xFF;
    }
}
