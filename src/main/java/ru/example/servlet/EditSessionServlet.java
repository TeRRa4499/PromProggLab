package ru.example.servlet;
import ru.example.model.Session;
import ru.example.storage.Storage;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditSessionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Session session = null;
        if (id != null && !id.isBlank()) {
            try { session = Storage.readSessionById(Integer.parseInt(id)); } catch (NumberFormatException ignored) {}
        }
        req.setAttribute("session", session);
        req.setAttribute("isEdit", session != null);
        req.getRequestDispatcher("/WEB-INF/views/session-form.jsp").forward(req, resp);
    }
}