package Client;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client_Global_Data {
    private static Socket socket;
    private static DataOutputStream out;
    private static ObjectInputStream in;

    private static String connSpeed;
    private static String format;
    public static ArrayList<String> matchingVideos = new ArrayList<String>();

    public static void makeServerConn(String host, int port) throws IOException {
        socket = new Socket(host, port);
        out = new DataOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    public static void sendSpeedFormat(String iSpeed, String iFormat) throws IOException, ClassNotFoundException {
        connSpeed = iSpeed;
        format = iFormat;
        out.writeUTF(connSpeed);
        out.writeUTF(format);
        getMatchingList();
    }

    public static void getMatchingList() throws IOException, ClassNotFoundException {
        matchingVideos = (ArrayList<String>) in.readObject();
        for (String video : matchingVideos) {
            System.out.println(video);
        }
    }

    public static void getFile(String file, String protocol) throws IOException {
        out.writeUTF(protocol);
        out.writeUTF(file);
        String fetchFile = "/home/xaris/Desktop/repos/Uni/MultiComms_lab/fetched_file/" + file;

        InputStream fin = socket.getInputStream();
        OutputStream fout = new FileOutputStream(fetchFile);

        String outputDir = System.getProperty("user.dir") + "/fetched_file/";
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
                .setInput(String.valueOf(fin))
                .addOutput(outputDir)
                .setFormat("avi")
                .done();

        executor.createJob(builder).run();
//
//        byte[] buffer = new byte[8192];
//        int count;
//        while ((count = fin.read(buffer)) > 0) {
//            fout.write(buffer, 0, count);
//        }

        fin.close();
        fout.close();
    }
}
