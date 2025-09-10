package org.escuelaing.edu.co.ejercicio5;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HttpServer {
    private static final File PUBLIC = new File("src/main/resources");

    public static void main(String[] args) throws IOException {
        if (PUBLIC.exists()) PUBLIC.mkdirs();
        try ( ServerSocket server = new ServerSocket(35000)) {
            System.out.println("Listening on http://localhost:35000/");
            while (true) {
                try (Socket client = server.accept()) {
                    handle(client);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void handle(Socket client) throws IOException {
        InputStream is = client.getInputStream();
        OutputStream os = client.getOutputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

        String request = br.readLine();
        if (request == null || request.isEmpty()) return;
        String [] reqParts = request.split(" ");
        if (reqParts.length < 2) return;

        String method = reqParts[0];
        String path = reqParts[1].split("\\?")[0];

        Map<String, String> headers = new HashMap<>();
        String line;
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            int idx = line.indexOf(":");
            if (idx > 0) {
                String name = line.substring(0, idx).trim();
                String value = line.substring(idx + 1).trim();
                headers.put(name.toLowerCase(), value);
            }
        }

        int contentLength = 0;
        if (headers.containsKey("content-length")) {
            try {
                contentLength = Integer.parseInt(headers.get("content-length"));
            } catch (NumberFormatException ignored) {}
        }

        String bodyStr = "";
        if (contentLength > 0) {
            char[] cbuf = new char[contentLength];
            int read = 0;
            while (read < contentLength) {
                int r = br.read(cbuf, read, contentLength - read);
                if (r == -1) break;
                read += r;
            }
            bodyStr = new String(cbuf, 0, Math.max(0, Math.min(read, contentLength)));
        }

        if (path.startsWith("/api/")) {
            handleApi(os, method, path, headers, bodyStr);
            return;
        }

        if ("/".equals(path)) path = "/index.html";
        File file = new File(PUBLIC, path);

        if (!file.exists() || file.isDirectory()) {
            String notFoundHtml = "<h1>404 Not Found</h1>";
            sendBytes(os, 404, "text/html; charset=utf-8", notFoundHtml.getBytes(StandardCharsets.UTF_8));
            return;
        }

        byte [] data = Files.readAllBytes(file.toPath());
        String ct = contentType(file.getName());
        sendBytes(os, 200, ct, data);
    }

    private static void handleApi(OutputStream os, String method, String path,
                                  Map<String, String> headers, String bodyStr) throws IOException {
        if ("GET".equalsIgnoreCase(method) && "/api/time".equals(path)) {
            String json = "{ \"time\": \"" + new Date().toString() + "\" }";
            sendBytes(os, 200, "application/json; charset=utf-8", json.getBytes(StandardCharsets.UTF_8));
            return;
        }

        if ("POST".equalsIgnoreCase(method) && "/api/echo".equals(path)) {
            String message = bodyStr;
            try {
                int idx = bodyStr.indexOf("\"message\"");
                if (idx >= 0) {
                    int colon = bodyStr.indexOf(":", idx);
                    int firstQuote = bodyStr.indexOf("\"", colon);
                    int secondQuote = bodyStr.indexOf("\"", firstQuote + 1);
                    if (firstQuote >= 0 && secondQuote > firstQuote) {
                        message = bodyStr.substring(firstQuote + 1, secondQuote);
                    }
                }
            } catch (Exception ignore) {}

            String escaped = message.replace("\\", "\\\\").replace("\"", "\\\"");
            String json = "{ \"message\": \"" + escaped + "\" }";
            sendBytes(os, 200, "application/json; charset=utf-8", json.getBytes(StandardCharsets.UTF_8));
            return;
        }

        String json = "{ \"error\": \"Not found\" }";
        sendBytes(os, 404, "application/json; charset=utf-8", json.getBytes(StandardCharsets.UTF_8));
    }

    public static void sendBytes(OutputStream os, int code, String contentType, byte[] data) throws IOException {
        sendHeaders(os, code, contentType, data.length);
        os.write(data);
        os.flush();
    }

    public static void sendHeaders(OutputStream os, int status, String contentType, int length) throws IOException {
        String reason = (status==200) ? "OK" : (status==404 ? "Not Found" : (status==403 ? "Forbidden" : "OK"));
        os.write(("HTTP/1.1 " + status + " " + reason + "\r\n").getBytes(StandardCharsets.UTF_8));
        os.write(("Content-Type: " + contentType + "\r\n").getBytes(StandardCharsets.UTF_8));
        os.write(("Content-Length: " + length + "\r\n").getBytes(StandardCharsets.UTF_8));
        os.write(("Connection: close\r\n").getBytes(StandardCharsets.UTF_8));
        os.write(("\r\n").getBytes(StandardCharsets.UTF_8));
    }

    private static String contentType(String fileName) {
        fileName.toLowerCase();
        if (fileName.endsWith(".html")) return "text/html; charset=utf-8";
        if (fileName.endsWith(".css")) return "text/css; charset=utf-8";
        if (fileName.endsWith(".js")) return "application/javascript; charset=utf-8";
        if (fileName.endsWith(".jpeg")) return "image/jpeg;";
        if (fileName.endsWith(".png")) return "image/png;";
        if (fileName.endsWith(".gif")) return "image/gif;";
        return "application/octet-stream";
    }
}
