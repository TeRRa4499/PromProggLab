package ru.example.servlet;

import ru.example.model.Student;
import ru.example.storage.Storage;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        String recordBookNumber = req.getParameter("recordBookNumber");
        String lastName = req.getParameter("lastName");
        String firstName = req.getParameter("firstName");
        String groupId = req.getParameter("groupId");

        if (recordBookNumber != null && !recordBookNumber.isBlank()
                && lastName != null && !lastName.isBlank()
                && firstName != null && !firstName.isBlank()
                && groupId != null && !groupId.isBlank()) {
            try {
                Student student = new Student(
                        null,
                        recordBookNumber.trim(),
                        lastName.trim(),
                        firstName.trim(),
                        Integer.parseInt(groupId)
                );

                if (id != null && !id.isBlank()) {
                    student.setStudentId(Integer.parseInt(id));
                    Storage.updateStudent(student);
                } else {
                    Storage.createStudent(student);
                }
            } catch (NumberFormatException ignored) {
                // Ignore invalid numeric input and just redirect back.
            }
        }

        resp.sendRedirect(req.getContextPath() + "/students");
    }
}
