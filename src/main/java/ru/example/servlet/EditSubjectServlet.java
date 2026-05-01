package ru.example.servlet;
import ru.example.model.Subject;
import ru.example.storage.Storage;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditSubjectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Subject subject = null;
        if (id != null && !id.isBlank()) {
            try { subject = Storage.readSubjectById(Integer.parseInt(id)); } catch (NumberFormatException ignored) {}
        }
        req.setAttribute("subject", subject);
        req.setAttribute("isEdit", subject != null);
        req.getRequestDispatcher("/WEB-INF/views/subject-form.jsp").forward(req, resp);
    }
}