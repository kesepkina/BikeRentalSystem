package com.epam.brs.controller;

import com.epam.brs.command.PagePath;
import com.epam.brs.command.SessionAttribute;
import com.epam.brs.model.entity.User;
import com.epam.brs.model.service.ServiceException;
import com.epam.brs.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "FileUploadServlet", urlPatterns = {"/fileuploadservlet"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class FileUploadServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger();

    private static final UserServiceImpl service = new UserServiceImpl();
    private static final String DASH = "-";

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file");
        User user = (User) request.getSession().getAttribute(SessionAttribute.USER);
        String fileName = filePart.getSubmittedFileName();
        String newFileName = user.getLogin() + DASH + UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
        String path = "E:/Workspace/BRC_Warehouse/images/" + newFileName;
        logger.debug("Path: {}", path);
        for (Part part : request.getParts()) {
            part.write(path);
        }
        try {
            service.updatePhotoName(newFileName, user.getLogin());
            user.setPhotoName(newFileName);
            request.getSession().setAttribute(SessionAttribute.USER, user);
            response.getWriter().print("The file uploaded successfully.");
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute("updatingError", "true");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.PROFILE);
        dispatcher.forward(request, response);
    }

}