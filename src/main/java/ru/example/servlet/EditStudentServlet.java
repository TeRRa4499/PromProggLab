package ru.example.servlet;

import ru.example.model.Student;
import ru.example.storage.Storage;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Student student = null;

        if (id != null && !id.isBlank()) {
            try {
                student = Storage.readStudentById(Integer.parseInt(id));
            } catch (NumberFormatException ignored) {
                student = null;
            }
        }

        req.setAttribute("student", student);
        req.setAttribute("isEdit", student != null);
        req.getRequestDispatcher("/WEB-INF/views/student-form.jsp").forward(req, resp);
    }
}
