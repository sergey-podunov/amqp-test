public class AmqpServer {
    public static final int DEFAULT_AMQP_PORT_NUMBER = 5672;

    private AmqpServerInstance instance;

    public AmqpServer() {
        instance = new AmqpServerInstance(DEFAULT_AMQP_PORT_NUMBER);
    }

    public AmqpServer(int portNumber) {
        instance = new AmqpServerInstance(portNumber);
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
