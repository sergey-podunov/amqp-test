package org.amqptest.command.connection;

import org.amqptest.ConnectionHandler;
import org.amqptest.command.AmqpCommand;
import org.amqptest.command.EmptyCommand;
import org.amqptest.types.LongString;

import java.nio.ByteBuffer;
import java.util.HashMap;

public class ConnectionStart implements AmqpCommand {

    private final int major;
    private final int minor;
    private final HashMap<String, Object> serverProperties;
    private final String securityMechanisms;
    private final String locales;

    public ConnectionStart(int major, int minor, HashMap<String, Object> serverProperties, String securityMechanisms, String locales) {
        this.major = major;
        this.minor = minor;
        this.serverProperties = serverProperties;
        this.securityMechanisms = securityMechanisms;
        this.locales = locales;
    }

    @Override
    public short getCommandId() {
        return 10;
    }

    @Override
    public short getMethodId() {
        return 10;
    }

    @Override
    public AmqpCommand execute(ConnectionHandler connectionHandler) {
        return new EmptyCommand();
    }

    @Override
    public byte[] bytes() {
        byte[] serverPropertiesBytes = new LongString("").bytes();
        byte[] securityMechBytes = new LongString(securityMechanisms).bytes();
        byte[] localesBytes = new LongString(locales).bytes();
        ByteBuffer commandBuffer = ByteBuffer.allocate(16 + serverPropertiesBytes.length + securityMechBytes.length + localesBytes.length);
        commandBuffer.put((byte) major);
        commandBuffer.put((byte) minor);
        commandBuffer.put(serverPropertiesBytes);
        commandBuffer.put(securityMechBytes);
        commandBuffer.put(localesBytes);
        return commandBuffer.array();
    }

    @Override
    public void fillArguments(byte[] commandPayload) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
