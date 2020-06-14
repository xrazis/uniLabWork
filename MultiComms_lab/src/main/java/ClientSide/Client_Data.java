package ClientSide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client_Data {
    protected Socket socket;
    protected PrintWriter out;
    protected BufferedReader in;

    protected String connSpeed;
    protected String format;

    public String makeServerConn(String host, int port) throws IOException {
        socket = new Socket(host, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out.println("Connected!");
        return "Connected to server";
    }

    public void test() {
        out.println("hey");
        System.out.println("test");
    }

    public String sendSpeedFormat(String iSpeed, String iFormat) {
        connSpeed = iSpeed;
        format = iFormat;

        out.write(connSpeed);
        out.write(format);

        return "okay";
    }

}
