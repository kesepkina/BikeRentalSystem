package com.epam.brs.controller;

import com.epam.brs.command.Command;
import com.epam.brs.command.CommandProvider;
import com.epam.brs.model.pool.ConnectionPool;
import com.epam.brs.model.pool.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.epam.brs.command.PagePath.INDEX;

@WebServlet(urlPatterns = {"/controller", "*.do"})
public class Controller extends HttpServlet {

    private static final Logger logger = LogManager.getLogger();
    private static final String COMMAND = "command";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Command> optionalCommand = CommandProvider.defineCommand(req.getParameter(COMMAND));
        Command command = optionalCommand.orElseThrow(IllegalArgumentException::new);
        String page = command.execute(req);

        if (page != null) {
            RequestDispatcher dispatcher = req.getRequestDispatcher(page);
            dispatcher.forward(req, resp);
        } else {
            req.getSession().setAttribute("nullPage", "Null page requested.");
            resp.sendRedirect(req.getContextPath() + INDEX);
        }
    }

    @Override
    public void destroy() {
        try {
            ConnectionPool.INSTANCE.destroyPool();
        } catch (ConnectionPoolException e) {
            logger.error(e);
            // todo: throw exception
        }
    }
}
