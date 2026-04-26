package ru.example.servlet;

import ru.example.model.Group;
import ru.example.storage.Storage;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class GroupsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<Group> groups = Storage.readAllGroups();
        req.setAttribute("groups", groups);
        req.getRequestDispatcher("/WEB-INF/views/groups.jsp").forward(req, resp);
    }
}
