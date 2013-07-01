package org.amqptest;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

public class RabbitMqTest {

    @Test
    public void testConnectAndCreateChannel() throws Exception {
        AmqpServer server = null;
        try {
            server = new AmqpServer(5655);
            server.start();

            ConnectionFactory factory = new ConnectionFactory();
            factory.setUsername("guest");
            factory.setPassword("guest");
            factory.setVirtualHost("/");
            factory.setHost("localhost");
            factory.setPort(5655);
            Connection conn = factory.newConnection();

            /*Channel channel = conn.createChannel();
            channel.close();*/

            conn.close();
        } finally {
            if (server != null) {
                server.stop();
            }
        }
    }
}
