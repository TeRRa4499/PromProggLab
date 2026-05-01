package ru.example.servlet;
import ru.example.model.Subject;
import ru.example.storage.Storage;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class SubjectsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<Subject> subjects = Storage.readAllSubjects();
        req.setAttribute("subjects", subjects);
        req.getRequestDispatcher("/WEB-INF/views/subjects.jsp").forward(req, resp);
    }
}