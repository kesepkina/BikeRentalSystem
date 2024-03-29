package com.epam.brs.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Properties;

@WebServlet("/image/*")
public class ImageServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger();
    private static final String CONFIG_PATH = "properties/config/config.properties";
    private static final String IMAGES_PATH;
    private static final Properties properties = new Properties();
    private static final String IMAGES_PATH_KEY = "images_warehouse_path";

    static {
        try (InputStream inputStream = ImageServlet.class.getClassLoader().getResourceAsStream(CONFIG_PATH);) {
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            logger.error("Properties file not found", e);
        } catch (IOException e) {
            logger.error("Reader of properties files not loaded", e);
        }
        IMAGES_PATH = (String) properties.get(IMAGES_PATH_KEY);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestedImage = request.getPathInfo();

        if (requestedImage == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }

        File image = new File(IMAGES_PATH, URLDecoder.decode(requestedImage, StandardCharsets.UTF_8));

        // Check if file actually exists in filesystem.
        if (!image.exists()) {
            // Do your thing if the file appears to be non-existing.
            // Throw an exception, or send 404, or show default/warning image, or just ignore it.
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }

        // Get content type by filename.
        String contentType = getServletContext().getMimeType(image.getName());

        // Check if file is actually an image (avoid download of other files by hackers!).
        // For all content types, see: http://www.w3schools.com/media/media_mimeref.asp
        if (contentType == null || !contentType.startsWith("image")) {
            // Do your thing if the file appears not being a real image.
            // Throw an exception, or send 404, or show default/warning image, or just ignore it.
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }

        // Init servlet response.
        response.reset();
        response.setContentType(contentType);
        response.setHeader("Content-Length", String.valueOf(image.length()));

        // Write image content to response.
        Files.copy(image.toPath(), response.getOutputStream());
    }

}
