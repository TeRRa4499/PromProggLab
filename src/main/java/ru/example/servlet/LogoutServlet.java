package ru.example.servlet;

import ru.example.model.User;
import ru.example.storage.Storage;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;


public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                                    throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session != null) {
            session.removeAttribute("user");
            session.invalidate();
        }
        resp.sendRedirect(req.getContextPath());
    }
}