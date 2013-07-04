package org.amqptest;

import org.amqptest.exception.AmqpServerException;

import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;

public class AmqpServer {
    public static final int DEFAULT_AMQP_PORT_NUMBER = 5672;
    public static final ByteOrder BYTE_ORDER = ByteOrder.BIG_ENDIAN;
    private Map<String, Object> serverSettings = new HashMap<String, Object>();

    private AmqpServerInstance instance;

    public AmqpServer() {
        this(DEFAULT_AMQP_PORT_NUMBER);
    }

    public AmqpServer(int portNumber) {
        serverSettings.put("chanelMaxCount", (short) 100);
        serverSettings.put("frameMaxSize", 1024);
        serverSettings.put("heartbeatTimeout", (short) 5000);
        instance = new AmqpServerInstance(portNumber, serverSettings);
    }

    public Map<String, Object> getServerSettings() {
        return serverSettings;
    }

    public void start() {
        new Thread(instance).start();

        try {
            instance.getStartLatch().await();
        } catch (InterruptedException e) {
            throw new AmqpServerException(e);
        }
    }

    public void stop() {
        instance.stop();
        try {
            instance.getStopLatch().await();
        } catch (InterruptedException e) {
            throw new AmqpServerException(e);
        }
    }

    public boolean isStopped() {
        return instance.getStopLatch().getCount() == 0;
    }
}
