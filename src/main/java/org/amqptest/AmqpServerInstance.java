package org.amqptest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class AmqpServerInstance implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(AmqpServerInstance.class);
    private int portNumber;
    private Map<String, Object> serverSettings;

    private CountDownLatch startLatch = new CountDownLatch(1);
    private CountDownLatch stopLatch = new CountDownLatch(1);
    private volatile boolean isInterrupted = false;
    private final ExecutorService executorService;
    private ServerSocket serverSocket;

    public AmqpServerInstance(int portNumber, Map<String, Object> serverSettings) {
        this.portNumber = portNumber;
        this.serverSettings = serverSettings;
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public CountDownLatch getStartLatch() {
        return startLatch;
    }

    public CountDownLatch getStopLatch() {
        return stopLatch;
    }

    @Override
    public void run() {
        serverSocket = null;
        try {
            try {
                serverSocket = new ServerSocket(portNumber);
            } catch (IOException e) {
                logger.error("Could not listen on port: " + portNumber);
                setStopState();
            }

            startLatch.countDown();

            while (!isInterrupted) {
                Socket clientSocket = null;
                try {
                    clientSocket = serverSocket.accept();
                } catch (IOException e) {
                    logger.error("Accept failed.");
                    setStopState();
                }

                executorService.execute(new ConnectionHandler(clientSocket, serverSettings));
            }
        } catch (Exception e) {
            logger.error("Exception " + e.getClass().getCanonicalName() + ":" + e.getMessage());
        } finally {
            stopLatch.countDown();
            if (serverSocket != null && !serverSocket.isClosed()) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void setStopState() {
        startLatch.countDown();
        stopLatch.countDown();
        isInterrupted = true;
    }

    public void stop() {
        isInterrupted = true;
        executorService.shutdownNow();
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

//        Thread.currentThread().interrupt();
    }
}
