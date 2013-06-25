package org.amqptest;

import com.google.common.base.Preconditions;
import org.amqptest.command.AmqpRequestCommand;
import org.amqptest.command.AmqpResponseCommand;
import org.amqptest.command.CommandSelector;
import org.amqptest.command.connection.ConnectionStart;
import org.amqptest.exception.ProtocolException;
import org.amqptest.exception.UnsupportedProtocolException;
import org.amqptest.frame.Frame;
import org.amqptest.frame.FrameType;
import org.amqptest.frame.MethodFrame;
import org.amqptest.types.LongString;
import org.amqptest.types.ShortString;
import org.amqptest.utils.IntUtils;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class ConnectionHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionHandler.class);
    private CommandSelector commandSelector = new CommandSelector();

    private ProtocolVersion protocolVersion;
    private LongString clientProperties;

    private Map<String, Object> serverSettings;
    private short channelCount;
    private int frameSize;

    private short heartbeatTimeout;
    private ShortString locale;

    private String path;
    private volatile boolean isWork = true;

    private Socket clientSocket;

    public ConnectionHandler(Socket clientSocket, Map<String, Object> serverSettings) {
        this.serverSettings = serverSettings;
        Preconditions.checkNotNull(clientSocket);
        this.clientSocket = clientSocket;
    }

    public void setLocale(ShortString locale) {
        this.locale = locale;
    }

    public ShortString getLocale() {
        return locale;
    }

    public void setClientProperties(LongString clientProperties) {
        this.clientProperties = clientProperties;
    }

    public LongString getClientProperties() {
        return clientProperties;
    }

    public Map<String, Object> getServerSettings() {
        return serverSettings;
    }

    public short getChannelCount() {
        return channelCount;
    }

    public void setChannelCount(short channelCount) {
        this.channelCount = channelCount;
    }

    public int getFrameSize() {
        return frameSize;
    }

    public void setFrameSize(int frameSize) {
        this.frameSize = frameSize;
    }

    public short getHeartbeatTimeout() {
        return heartbeatTimeout;
    }

    public void setHeartbeatTimeout(short heartbeatTimeout) {
        this.heartbeatTimeout = heartbeatTimeout;
    }

    public boolean isWork() {
        return isWork;
    }

    public void setWork(boolean work) {
        isWork = work;
    }

    @Override
    public void run() {
        OutputStream out = null;
        DataInputStream in = null;
        try {
            out = clientSocket.getOutputStream();
            in = new DataInputStream(clientSocket.getInputStream());
            logger.debug("Ready to communicate with client");

            initConnection(in, out);

            while (isWork) {
                MethodFrame frame = (MethodFrame) Frame.readFrame(in);

                MethodFrame.RawCommand rawCommand = frame.getPayload();
                AmqpRequestCommand requestCommand = commandSelector.getCommand(rawCommand.getCommandMethodId(), rawCommand.getCommandPayload());
                logger.debug("Parsed request command {}", requestCommand);

                AmqpResponseCommand responseCommand = requestCommand.execute(this);
                if (responseCommand == null) {
                    logger.info("No response for {}", requestCommand);
                    continue;
                }

                logger.debug("Send {}", responseCommand);

                byte[] methodFramePayload = createMethodFramePayload(responseCommand);
                out.write(Frame.createFrame(FrameType.METHOD, (short) 0, methodFramePayload));

                logger.debug("{} was sent", responseCommand);
            }

        } catch (Exception e) {
            logger.error("Exception: ", e);
        } finally {
            if (out != null) {
                IOUtils.closeQuietly(out);
            }
            if (in != null) {
                IOUtils.closeQuietly(in);
            }
            if (clientSocket != null) {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    logger.error("Exception while close connection", e);
                }
            }
        }
    }

    private void initConnection(DataInputStream in, OutputStream out) throws IOException, ProtocolException {
        byte[] header = new byte[8];
        in.read(header, 0, 8);
        logger.trace("Got header {}", Hex.encodeHexString(header));

        protocolVersion = getVersion(header);

        byte[] methodFramePayload;

        ConnectionStart connectionStart = new ConnectionStart(protocolVersion.getMajor(), protocolVersion.getMinor(), new HashMap<String, Object>(), "PLAIN", "UTF-8");
        logger.debug("Send {}", connectionStart);

        methodFramePayload = createMethodFramePayload(connectionStart);
        out.write(Frame.createFrame(FrameType.METHOD, (short) 0, methodFramePayload));

        logger.debug("{} was sent", connectionStart);
    }

    private byte[] createMethodFramePayload(AmqpResponseCommand responseCommand) {
        //todo move to the MethodFrame class
        byte[] commandBytes;
        ByteBuffer methodPayload;
        commandBytes = responseCommand.bytes();
        methodPayload = ByteBuffer.allocate(4 + commandBytes.length);
        methodPayload.put(ByteBuffer.allocate(2).order(AmqpServer.BYTE_ORDER).putShort(responseCommand.getCommandId()).array());
        methodPayload.put(ByteBuffer.allocate(2).order(AmqpServer.BYTE_ORDER).putShort(responseCommand.getMethodId()).array());
        if (commandBytes.length != 0) {
            methodPayload.put(commandBytes);
        }
        return methodPayload.array();
    }

    private ProtocolVersion getVersion(byte[] header) throws UnsupportedProtocolException {
        char[] magic = new char[4];
        for (int i = 0; i < 4; i++) {
            magic[i] = (char) header[i];
        }

        String magicString = String.valueOf(magic);
        if (!"AMQP".equals(magicString) && header[4] != 0x0) {
            throw new UnsupportedProtocolException();
        }
        logger.trace("Client ask for AMQP protocol");

        int major, minor, revision;
        major = IntUtils.convertToUnsignedInt(header[5]);
        minor = IntUtils.convertToUnsignedInt(header[6]);
        revision = IntUtils.convertToUnsignedInt(header[7]);

        ProtocolVersion result = null;
        for (ProtocolVersion version : ProtocolVersion.values()) {
            if (version.equalsByParts(major, minor, revision)) {
                result = version;
                break;
            }
        }

        if (result == null) {
            throw new UnsupportedProtocolException();
        }

        logger.debug("Client ask for {} version", result);
        return result;
    }

    public boolean handleSecurityResponse(ShortString mechanism, LongString securityResponse) {
        return true;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
