package org.example.Task1;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * Task1.Processor of HTTP request.
 */
public class Processor {
    private final Socket socket;
    private final org.example.Task1.HttpRequest request;

    public Processor(Socket socket, org.example.Task1.HttpRequest request) {
        this.socket = socket;
        this.request = request;
    }

    public void process() throws IOException, InterruptedException {



        System.out.println("Got request:");
        System.out.println(request.toString());
        System.out.flush();

        PrintWriter output = new PrintWriter(socket.getOutputStream());

        if (request.getRequestLine().toString().equals("GET /create/item1 HTTP/1.1")){
            Thread.sleep(2000);
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<html>");
            output.println("<head><title>Hello Create ID</title></head>");
            output.println("<body><p>Hello Create ID!</p></body>");
            output.println("</html>");
            output.flush();
            socket.close();
        }
        else if (request.getRequestLine().toString().equals("GET /delete/item1 HTTP/1.1")){
            Thread.sleep(2000);
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<html>");
            output.println("<head><title>Hello Delete ID</title></head>");
            output.println("<body><p>Hello Delete ID!</p></body>");
            output.println("</html>");
            output.flush();
            socket.close();
        }
        else if (request.getRequestLine().toString().equals("GET /exec/params HTTP/1.1")){
            Thread.sleep(2000);
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<html>");
            output.println("<head><title>Hello Exec ID</title></head>");
            output.println("<body><p>Hello Exec ID!</p></body>");
            output.println("</html>");
            output.flush();
            socket.close();
        }
        // We are returning a simple web page now.
        else if (request.getRequestLine().toString().equals("GET /index/item1 HTTP/1.1")) {
            Thread.sleep(2000);
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<html>");
            output.println("<head><title>Hello</title></head>");
            output.println("<body><p>Hello, world!</p></body>");
            output.println("</html>");
            output.flush();
            socket.close();
        }
    }
}