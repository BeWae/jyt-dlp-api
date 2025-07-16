## jyt-dlp-api

A lightweight Java REST API built with Spring Boot that allows you to download YouTube videos as MP3 or MP4 using the powerful `yt-dlp` tool.

## Features
- 🎵 Convert and download YouTube videos as MP3
- 📼 Download full videos as MP4
- 🔧 Simple API interface (GET /api/download?url=...)
- 🧰 Uses `yt-dlp` under the hood
- ✅ Easy to deploy and integrate into your own website

## Requirements
- Java 17 or higher
- yt-dlp (included in `resources/yt-dlp.exe`)

## Example Request
```http
GET http://localhost:8080/api/download?url=https://www.youtube.com/watch?v=abc123
