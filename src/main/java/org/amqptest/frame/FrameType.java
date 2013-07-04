package org.amqptest.frame;

import java.util.HashMap;
import java.util.Map;

/**
 * 0      1         3         7                 size+7      size+8
 * +------+---------+---------+ +-------------+ +-----------+
 * | type | channel | size    | | payload     | | frame-end |
 * +------+---------+---------+ +-------------+ +-----------+
 * octet   short       long       'size' octets    octet
 * <p/>
 * <p/>
 * AMQP defines these frame types:
 * Type = 1, "METHOD": method frame.
 * Type = 2, "HEADER": content header frame.
 * Type = 3, "BODY": content body frame.
 * Type = 4, "HEARTBEAT": heartbeat frame.
 * The channel number is 0 for all frames which are global to the connection and 1-65535 for frames that
 * refer to specific channels.
 * <p/>
 * The frame-end octet MUST always be the hexadecimal value %xCE.
 */
public enum FrameType {
    METHOD((byte) 1), HEADER((byte) 2), BODY((byte) 3), HEARTBEAT((byte) 4);
    private byte code;
    private static Map<Byte, FrameType> lookupMap = new HashMap<Byte, FrameType>();

    static {
        for (FrameType type : values()) {
            lookupMap.put(type.getCode(), type);
        }
    }

    public static FrameType find(Byte code) {
        return lookupMap.get(code);
    }

    private FrameType(byte code) {
        this.code = code;
    }

    public byte getCode() {
        return code;
    }
}
