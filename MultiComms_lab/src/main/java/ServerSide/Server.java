package ServerSide;

import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Server {
    private static DataOutputStream out;
    private static DataInputStream in;
    private static Socket socket;
    private static ServerSocket server;

    private static String speed;
    private static String format;
    private static ArrayList<String> videos = new ArrayList<String>();

    public static void startServer(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");
            socket = server.accept();
            System.out.println("Client accepted");

            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(new DataInputStream(socket.getInputStream()));

            getSpeedFormat();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static void getSpeedFormat() throws IOException {
        speed = in.readUTF();
        format = in.readUTF();
        System.out.println("Got speed:" + speed + " and format:" + format);

        searchForFiles();
    }

    private static void searchForFiles() {
//        switch (speed)
    }

    public static void getFilesFromDir(String dir) {
        try (Stream<Path> paths = Files.walk(Paths.get(dir))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(file -> videos.add(String.valueOf((file.getFileName()))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        getFilesFromDir("/home/xaris/Desktop/repos/Uni/MultiComms_lab/videos");
        for (String video : videos) {
            System.out.println(video);
        }

        startServer(5000);
    }

}
