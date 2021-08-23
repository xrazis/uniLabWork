package Server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.lang.String.valueOf;

public class Server_Global_Data {

    private static ObjectOutputStream objOut;
    private static DataInputStream dataIn;
    private static Socket socket;
    private static ServerSocket server;

    private static String speed;
    private static String format;
    private static String file;
    private static String protocol;
    public static final ArrayList<String> videos = new ArrayList<String>();
    public static final ArrayList<String> matchingVideos = new ArrayList<String>();

    //Reads videos that were build into an ArrayList.
    public static void getFilesFromDir(String dir) {
        try (Stream<Path> paths = Files.walk(Paths.get(dir))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(file -> videos.add(valueOf((file.getFileName()))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Starts the server and listens for a single client.Can be multi threaded for multiple clients.
    public static void startServer(int port) {
        try {
            server = new ServerSocket(port);
            socket = server.accept();
            objOut = new ObjectOutputStream(socket.getOutputStream());
            dataIn = new DataInputStream(new DataInputStream(socket.getInputStream()));
            getSpeedFormat();
        } catch (IOException | InterruptedException e) {
            System.out.println(e);
        }
    }

    //Gets speed and format from client.
    private static void getSpeedFormat() throws IOException, InterruptedException {
        speed = dataIn.readUTF();
        format = dataIn.readUTF();
        searchForFiles();
    }

    //Searches for files with the specific speed and format.
    private static void searchForFiles() throws IOException, InterruptedException {
        if (format.equals("mkv")) format = "matroska";
        for (String video : videos) {
            Pattern mPattern = Pattern.compile(".*-" + speed + "." + format);
            Matcher mMatcher = mPattern.matcher(video);
            if (mMatcher.find()) {
                matchingVideos.add(video);
            }
        }
        objOut.writeObject(matchingVideos);
        getFileProtocol();
    }

    //Gets back the file and protocol and initiates streaming.
    private static void getFileProtocol() throws IOException, InterruptedException {
        protocol = dataIn.readUTF();
        file = dataIn.readUTF();
        String fetchFile = System.getProperty("user.dir") + "/FFmpeg_videos/built_videos/" + file;
        Process p = Runtime.getRuntime().exec("ffmpeg -i " + fetchFile + " -f mpegts " + protocol + "://127.0.0.1:2000");
        System.out.println(p.waitFor());
    }
}