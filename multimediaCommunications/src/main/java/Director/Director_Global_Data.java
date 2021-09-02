package Director;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Director_Global_Data {
    public static String inputDir = System.getProperty("user.dir") + "/FFmpeg_videos/raw_videos/";
    public static String outDir = System.getProperty("user.dir") + "/FFmpeg_videos/built_videos/";

    public static final ArrayList<String> videos = new ArrayList<String>();

    //Reads files from dir and adds them to an ArrayList.
    public static void getFilesFromDir(String dir) {
        try (Stream<Path> paths = Files.walk(Paths.get(dir))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(file -> videos.add(String.valueOf((file.getFileName()))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Reads the files from the Arraylist and initiates building method.
    public static void buildFiles() throws IOException {
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

        FileUtils.cleanDirectory(new File(outDir));

        for (String video : videos) {
            builder(executor, video, "avi");
            builder(executor, video, "mp4");
            builder(executor, video, "matroska");
        }
    }

    //The actual ffmpeg builder functionality.
    public static void builder(FFmpegExecutor executor, String video, String format) {
        String outName = FilenameUtils.removeExtension(video);

        FFmpegBuilder builder_1 = new FFmpegBuilder()
                .setInput(inputDir + video)
                .addOutput(outDir + outName + "-0.2Mbps." + format)
                .setFormat(format)
                .setVideoResolution(480, 270)
                .setAudioBitRate(64000)
                .done();

        FFmpegBuilder builder_2 = new FFmpegBuilder()
                .setInput(inputDir + video)
                .addOutput(outDir + outName + "-0.5Mbps." + format)
                .setFormat(format)
                .setVideoResolution(680, 360)
                .setAudioBitRate(96000)
                .done();

        FFmpegBuilder builder_3 = new FFmpegBuilder()
                .setInput(inputDir + video)
                .addOutput(outDir + outName + "-1Mbps." + format)
                .setFormat(format)
                .setVideoResolution(854, 480)
                .setAudioBitRate(96000)
                .done();

        FFmpegBuilder builder_4 = new FFmpegBuilder()
                .setInput(inputDir + video)
                .addOutput(outDir + outName + "-3Mbps." + format)
                .setFormat(format)
                .setVideoResolution(1280, 720)
                .setAudioBitRate(128000)
                .done();

        executor.createJob(builder_1).run();
        executor.createJob(builder_2).run();
        executor.createJob(builder_3).run();
        executor.createJob(builder_4).run();
    }

    //Deletes raw videos.
    public static void deleteFiles() throws IOException {
        FileUtils.cleanDirectory(new File(inputDir));
    }
}
