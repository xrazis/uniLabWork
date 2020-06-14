package ServerSide;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;

import java.io.IOException;

@SuppressWarnings("SpellCheckingInspection")
public class Director {

    public static void main(String[] args) {

        String inputDir = System.getProperty("user.dir") + "/raw_videos/";
        String outDir = System.getProperty("user.dir") + "/videos/";

        FFmpeg ffmpeg = null;
        FFprobe ffprobe = null;

        try {
            ffmpeg = new FFmpeg("/usr/bin/ffmpeg");
            ffprobe = new FFprobe("/usr/bin/ffprobe");
        } catch (IOException e) {
            e.printStackTrace();
        }

        FFmpegBuilder builder = new FFmpegBuilder()
                .setInput(inputDir + "google.mp4")
                .overrideOutputFiles(true)
                .addOutput(outDir + "google.mp4")
                .setFormat("avi")
                .done();


        assert ffmpeg != null;
        assert ffprobe != null;
        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

        executor.createJob(builder).run();
    }
}
