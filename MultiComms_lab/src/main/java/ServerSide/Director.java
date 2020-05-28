package ServerSide;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFprobe;

import java.io.IOException;

public class Director {

    public static void main(String[] args) {

        String inputDir = System.getProperty("user.dir") + "/input/";
        String outDir = System.getProperty("user.dir") + "/output/";
        try {
            FFmpeg ffmpeg = new FFmpeg("PATH");
            FFprobe ffprobe = new FFprobe("PATH");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
