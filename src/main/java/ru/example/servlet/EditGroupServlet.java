package ru.example.servlet;

import ru.example.model.Group;
import ru.example.storage.Storage;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditGroupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Group group = null;

        if (id != null && !id.isBlank()) {
            try {
                group = Storage.readGroupById(Integer.parseInt(id));
            } catch (NumberFormatException ignored) {
                group = null;
            }
        }

        req.setAttribute("group", group);
        req.setAttribute("isEdit", group != null);
        req.getRequestDispatcher("/WEB-INF/views/group-form.jsp").forward(req, resp);
    }
}
