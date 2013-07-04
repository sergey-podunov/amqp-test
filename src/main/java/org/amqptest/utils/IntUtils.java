package org.amqptest.utils;

import java.math.BigInteger;

public class IntUtils {
    private IntUtils() {
        //util class
    }

    public static int convertToUnsignedInt(byte b) {
        return b & 0xFF;
    }

    public static int convertToUnsignedInt(short s) {
        return s & 0xFFFF;
    }

    public static BigInteger convertToUnsignedBigint(long l) {
        BigInteger bi = new BigInteger(Long.toString(l & ~(1L << 63)));
        if (l < 0) bi = bi.setBit(64);
        return bi;
    }
}
