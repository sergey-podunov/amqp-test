import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

class AmqpServerInstance implements Runnable {
    private int portNumber;

    private CountDownLatch startLatch = new CountDownLatch(1);
    private CountDownLatch stopLatch = new CountDownLatch(1);
    private volatile boolean isInterrupted = false;

    public AmqpServerInstance(int portNumber) {
        this.portNumber = portNumber;
    }

    public CountDownLatch getStartLatch() {
        return startLatch;
    }

    public CountDownLatch getStopLatch() {
        return stopLatch;
    }

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try {
            try {
                serverSocket = new ServerSocket(portNumber);
            } catch (IOException e) {
                System.out.println("Could not listen on port: " + portNumber);
                setStopState();
            }

            startLatch.countDown();

            while (!isInterrupted) {
                Socket clientSocket = null;
                try {
                    clientSocket = serverSocket.accept();
                } catch (IOException e) {
                    System.err.println("Accept failed.");
                    setStopState();
                }

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                out.println("connected!");

                out.close();
                in.close();
                clientSocket.close();
            }
        } catch (Exception e) {
            System.out.println("Exception " + e.getClass().getCanonicalName() + ":" + e.getMessage());
        } finally {
            stopLatch.countDown();
            if (serverSocket != null) {
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
    }
}
