package ru.example.servlet;
import ru.example.model.Grade;
import ru.example.storage.Storage;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public class GradesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Collection<Grade> grades = Storage.readAllGrades();
            req.setAttribute("grades", grades);
            req.getRequestDispatcher("/WEB-INF/views/grades.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException("Failed to load grades", e);
        }
    }
}
