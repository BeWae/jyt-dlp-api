package com.github.bewae.jytdlpapi.Services;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

@Service
public class DownloadService {

    private static final String YT_DLP_EXE = "resources/yt-dlp.exe";

    public String downloadVideo(String url) throws IOException, InterruptedException {
        File downloadDir = new File("downloads");
        if (!downloadDir.exists()) {
            downloadDir.mkdirs();
        }

        String outputTemplate = Paths.get(downloadDir.getAbsolutePath(), "%(title)s.%(ext)s").toString();

        File ytDlpExe = new File(YT_DLP_EXE);
        ProcessBuilder builder = new ProcessBuilder(
                ytDlpExe.getAbsolutePath(),
                "--extract-audio",
                "--audio-format", "mp3",
                "-o", outputTemplate,
                url
        );

        builder.redirectErrorStream(true);
        Process process = builder.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("yt-dlp Prozess endete mit Code " + exitCode);
        }

        File[] files = downloadDir.listFiles(File::isFile);
        if (files == null || files.length == 0) {
            throw new RuntimeException("Keine Datei gefunden nach Download");
        }

        File latestFile = files[0];
        for (File f : files) {
            if (f.lastModified() > latestFile.lastModified()) {
                latestFile = f;
            }
        }

        return latestFile.getAbsolutePath();
    }
}