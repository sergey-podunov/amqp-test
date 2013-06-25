package org.amqptest.frame;


import com.google.common.collect.ImmutableMap;
import org.amqptest.AmqpServer;
import org.amqptest.exception.ProtocolException;
import org.amqptest.exception.WrongFrameSize;
import org.amqptest.exception.WrongFrameTypeException;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
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
public abstract class Frame<P> {
    public static final byte FRAME_END = (byte) 206;

    protected short chanel;
    protected byte[] payload;

    private static Map<FrameType, FrameFactory> factories = ImmutableMap.<FrameType, FrameFactory>builder()
            .put(FrameType.METHOD, new MethodFrameFactory())
            .put(FrameType.HEADER, new HeaderFrameFactory())
            .put(FrameType.BODY, new BodyFrameFactory())
            .put(FrameType.HEARTBEAT, new HeartbeatFrameFactory())
            .build();

    protected Frame(short chanel, byte[] payload) {
        this.chanel = chanel;
        this.payload = payload;
    }

    public short getChanel() {
        return chanel;
    }

    public abstract P getPayload();

    public static byte[] createFrame(FrameType type, short chanel, byte[] payload) {
        int payloadSize = payload.length;
        ByteBuffer frameBuffer = ByteBuffer.allocate(8 + payloadSize);
        frameBuffer.put(type.getCode());
        frameBuffer.put(ByteBuffer.allocate(2).order(AmqpServer.BYTE_ORDER).putShort(chanel).array());
        frameBuffer.put(ByteBuffer.allocate(4).order(AmqpServer.BYTE_ORDER).putInt(payloadSize).array());
        if (payloadSize != 0) {
            frameBuffer.put(payload);
        }
        frameBuffer.put(FRAME_END);

        return frameBuffer.array();
    }

    public static Frame readFrame(DataInputStream in) throws ProtocolException {
        try {

            byte typeByte = in.readByte();

            FrameType type = FrameType.find(typeByte);
            if (type == null) {
                throw new WrongFrameTypeException();
            }

            short chanel = in.readShort();

            int payloadSize = in.readInt();
            byte[] payload = new byte[payloadSize];
            in.readFully(payload);

            byte frameEnd = in.readByte();
            if (frameEnd != FRAME_END) {
                throw new WrongFrameSize();
            }

            return factories.get(type).create(chanel, payload);
        } catch (IOException e) {
            throw new ProtocolException(e);
        }
    }
}
