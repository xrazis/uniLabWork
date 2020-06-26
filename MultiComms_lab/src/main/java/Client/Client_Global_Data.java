package Client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Client_Global_Data {
    private static Socket socket;
    private static DataOutputStream dataOut;
    private static ObjectInputStream objIn;

    private static String connSpeed;
    private static String format;
    public static ArrayList<String> matchingVideos = new ArrayList<String>();

    //Makes the server connection.
    public static void makeServerConn(String host, int port) throws IOException {
        socket = new Socket(host, port);
        dataOut = new DataOutputStream(new DataOutputStream(socket.getOutputStream()));
        objIn = new ObjectInputStream(socket.getInputStream());
    }

    //Sends the speed and format to the server.
    public static void sendSpeedFormat(String iSpeed, String iFormat) throws IOException, ClassNotFoundException {
        connSpeed = iSpeed;
        format = iFormat;
        dataOut.writeUTF(connSpeed);
        dataOut.writeUTF(format);
        getMatchingList();
    }

    //Gets back an ArrayList from the server containing all the matching videos for the specific speed and format.
    public static void getMatchingList() throws IOException, ClassNotFoundException {
        matchingVideos = (ArrayList<String>) objIn.readObject();
    }

    //Initiates listening for the streamer and sends file and protocol to the server.
    public static void getFile(String file, String protocol) throws IOException, InterruptedException {
        Process p = Runtime.getRuntime().exec("ffplay -i " + protocol + "://127.0.0.1:2000?listen");
        System.out.println(p.waitFor());

        dataOut.writeUTF(protocol);
        dataOut.writeUTF(file);
    }
}