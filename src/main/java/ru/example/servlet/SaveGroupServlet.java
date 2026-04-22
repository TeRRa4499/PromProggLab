package ru.example.servlet;

import ru.example.model.Group;
import ru.example.storage.Storage;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveGroupServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        String groupName = req.getParameter("groupName");
        String course = req.getParameter("course");
        String faculty = req.getParameter("faculty");

        if (groupName != null && !groupName.isBlank()
                && course != null && !course.isBlank()
                && faculty != null && !faculty.isBlank()) {
            try {
                Group group = new Group(
                        null,
                        groupName.trim(),
                        Integer.parseInt(course),
                        faculty.trim()
                );

                if (id != null && !id.isBlank()) {
                    group.setGroupId(Integer.parseInt(id));
                    Storage.updateGroup(group);
                } else {
                    Storage.createGroup(group);
                }
            } catch (NumberFormatException ignored) {
                // Ignore invalid numeric input and just redirect back.
            }
        }

        resp.sendRedirect(req.getContextPath() + "/groups");
    }
}
