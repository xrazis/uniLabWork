package ServerSide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    protected static PrintWriter out;
    protected static BufferedReader in;
    protected static Socket socket;
    protected static ServerSocket server;

    public static void startServer(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");
            socket = server.accept();
            System.out.println("Client accepted");

            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            getSpeedFormat();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static void getSpeedFormat() throws IOException {
        String speed = in.readLine();
        String format = in.readLine();
    }

    public static void main(String[] args) {
        startServer(5000);
    }

}
