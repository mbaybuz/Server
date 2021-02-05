package com.github.mbaybuz;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.PrimitiveIterator;

public class Main {

    private static String www = "/home/mikle/IdeaProjects/Server/www"; //local path of the folder WWW

    public static void main(String[] args) {
        while (true) { //endless cycle for server always working
            try (ServerSocket serverSocket = new ServerSocket(8080)) { //Create web-server
                System.out.println("Server started"); //Starting server and waiting for clients

                Socket socket = serverSocket.accept();
                System.out.println("New client connected"); //Open in browser: \\localhost:8080

                try (BufferedReader input = new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream(), StandardCharsets.UTF_8));
                     PrintWriter output = new PrintWriter(socket.getOutputStream());
                ) {

                    while (!input.ready()) ; //while nobody connected

                    while (input.ready()) { //if someone connected
                        System.out.println(input.readLine());
                    }

                    output.println("HTTP/1.1 200 OK"); //info for PrintWriter
                    output.println("Content type: text/html, Charset: UTF-8");
                    output.println(); //empty string - separator
                    output.println("<h1>Hello Zdaz!!!</h1>");
                    output.flush();  //all information will be send

                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
