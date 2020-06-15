package ClientSide;

import java.io.*;
import java.net.Socket;

public class Client_Data {
    protected static Socket socket;
    protected static DataOutputStream out;
    protected static DataInputStream in;

    protected static String connSpeed;
    protected static String format;

    public String makeServerConn(String host, int port) throws IOException {
        socket = new Socket(host, port);
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(new DataInputStream(socket.getInputStream()));
        return "Connected to server";
    }

    public static void test() throws IOException {
        out.writeUTF("hey");
        System.out.println("test");
    }

    public static String sendSpeedFormat(String iSpeed, String iFormat) throws IOException {
        connSpeed = iSpeed;
        format = iFormat;

        out.writeUTF(connSpeed);
        out.writeUTF(format);

        return "Data were sent to server";
    }

}
