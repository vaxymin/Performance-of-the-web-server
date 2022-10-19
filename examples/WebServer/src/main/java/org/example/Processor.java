package org.example;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Processor of HTTP request.
 */
public class Processor {
    private final Socket socket;
    private final HttpRequest request;

    public Processor(Socket socket, HttpRequest request) {
        this.socket = socket;
        this.request = request;
    }


    static void PrimeNumbers(int inputNumber)
    {
        boolean prime = true;

        if(inputNumber <= 1)
        {
            prime = false;

        }
        else
        {
            for (int i = 2; i<= inputNumber/2; i++)
            {
                if ((inputNumber % i) == 0)
                {
                    prime = false;

                    break;
                }
            }

        }

    }


    void count() {
        int count = 0;
        try {
            // create a new file object
            File file = new File("index.txt");

            // create an object of Scanner
            // associated with the file
            Scanner sc = new Scanner(file);

            // read each line and
            // count number of lines
            while (sc.hasNextLine()) {
                sc.nextLine();
                count++;
            }
            System.out.println("Total Number of Lines: " + count);

            // close scanner
            sc.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    public void process() throws IOException {



        System.out.println("Got request:");
        System.out.println(request.toString());
        System.out.flush();

        PrintWriter output = new PrintWriter(socket.getOutputStream());

        if (request.getRequestLine().toString().equals("GET /create/item1 HTTP/1.1")){
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
            for(int i=0;i<1000;i++){
            //count();
                PrimeNumbers(100);
            }


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
