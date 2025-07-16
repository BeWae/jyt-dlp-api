package com.github.bewae.jytdlpapi.Controller;

import com.github.bewae.jytdlpapi.Services.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DownloadController {

    private final DownloadService downloadService;

    @Autowired
    public DownloadController(DownloadService downloadService) {
        this.downloadService = downloadService;
    }

    @GetMapping("/download")
    public ResponseEntity<String> download(@RequestParam String url) {
        try {
            String filePath = downloadService.downloadVideo(url);
            return ResponseEntity.ok(filePath);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Download fehlgeschlagen: " + e.getMessage());
        }
    }
}