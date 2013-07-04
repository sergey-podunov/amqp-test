package org.amqptest;

import org.junit.Test;

import java.net.Socket;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AmqpServerTest {
    @Test(expected = java.net.ConnectException.class)
    public void testStartStopServer() throws Exception {
        AmqpServer server = new AmqpServer(5655);
        server.start();
        try {
            Socket startSocket = new Socket("127.0.0.1", 5655);
            assertTrue(startSocket.isConnected());

            server.stop();

            Socket stopSocket = new Socket("127.0.0.1", 5655);
            assertFalse(stopSocket.isConnected());
        } finally {
            if (!server.isStopped()) {
                server.stop();
            }
        }
    }
}
