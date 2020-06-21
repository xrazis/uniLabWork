package Server;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Server_Global_Data {

    private static ObjectOutputStream out;
    private static DataInputStream in;
    private static Socket socket;
    private static ServerSocket server;

    private static String speed;
    private static String format;
    private static String file;
    private static String protocol;
    public static final ArrayList<String> videos = new ArrayList<String>();
    public static final ArrayList<String> matchingVideos = new ArrayList<String>();

    public static void getFilesFromDir(String dir) {
        try (Stream<Path> paths = Files.walk(Paths.get(dir))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(file -> videos.add(String.valueOf((file.getFileName()))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void startServer(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");
            System.out.println("Waiting for a client ...");
            socket = server.accept();
            System.out.println("Client accepted");
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new DataInputStream(new DataInputStream(socket.getInputStream()));
            getSpeedFormat();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static void getSpeedFormat() throws IOException {
        speed = in.readUTF();
        format = in.readUTF();
        System.out.println("Got speed: " + speed + " and format: " + format);
        searchForFiles();
    }

    private static void searchForFiles() throws IOException {
        for (String video : videos) {
            Pattern mPattern = Pattern.compile(".*-" + speed + format);
            Matcher mMatcher = mPattern.matcher(video);
            if (mMatcher.find()) {
                matchingVideos.add(video);
            }
        }
        out.writeObject(matchingVideos);
        System.out.println("Sent matching videos");
        getFileProtocol();
    }

    private static void getFileProtocol() throws IOException {
        protocol = in.readUTF();
        file = in.readUTF();

        switch (protocol) {
            case "TCP":
                String fetchFile = "/home/xaris/Desktop/repos/Uni/MultiComms_lab/videos/" + file;
                File file = new File(fetchFile);
//                long length = file.length();
//                byte[] buffer = new byte[8192];
//
                InputStream fin = new FileInputStream(file);
                OutputStream fout = socket.getOutputStream();
//
//                int count;
//                while ((count = fin.read(buffer)) > 0) {
//                    fout.write(buffer, 0, count);
//                }

                String inputDir = System.getProperty("user.dir") + "/videos/";
                FFmpeg ffmpeg = null;
                FFprobe ffprobe = null;

                try {
                    ffmpeg = new FFmpeg("/usr/bin/ffmpeg");
                    ffprobe = new FFprobe("/usr/bin/ffprobe");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                assert ffmpeg != null;
                assert ffprobe != null;
                FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

                FFmpegBuilder builder = new FFmpegBuilder()
                        .setInput(inputDir + file)
                        .addOutput(String.valueOf(fout))
                        .setFormat("avi")
                        .done();

                executor.createJob(builder).run();

                fin.close();
                fout.close();
                break;
//            case "UDP":
//                break;
//            case "RTP/UDP":
//                break;
        }

    }

}
