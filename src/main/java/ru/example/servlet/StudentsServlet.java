package ru.example.servlet;

import ru.example.model.Student;
import ru.example.storage.Storage;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class StudentsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<Student> students = Storage.readAllStudents();
        req.setAttribute("students", students);
        req.getRequestDispatcher("/WEB-INF/views/students.jsp").forward(req, resp);
    }
}
