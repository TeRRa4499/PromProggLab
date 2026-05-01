package ru.example.servlet;
import ru.example.model.Grade;
import ru.example.storage.Storage;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditGradeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Grade grade = null;
        if (id != null && !id.isBlank()) {
            try { grade = Storage.readGradeById(Integer.parseInt(id)); } catch (NumberFormatException ignored) {}
        }
        req.setAttribute("grade", grade);
        req.setAttribute("isEdit", grade != null);
        req.getRequestDispatcher("/WEB-INF/views/grade-form.jsp").forward(req, resp);
    }
}