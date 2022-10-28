package org.example.Task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Consumer<T> extends Thread {
    private final ThreadSafeQueue<T> queue;

    public Consumer(ThreadSafeQueue<T> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {

                T elem = queue.pop();

                Socket socket = (Socket) elem;

                BufferedReader input = new BufferedReader(
                        new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));


                HttpRequest request = HttpRequest.parse(input);

                if (elem == null) {
                    return;
                }

                Processor proc = new Processor(socket, request);
                proc.start();
            }
        }
        catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}