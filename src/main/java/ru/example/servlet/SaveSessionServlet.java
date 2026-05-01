package ru.example.servlet;
import ru.example.model.Session;
import ru.example.storage.Storage;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveSessionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        String sessionNumber = req.getParameter("sessionNumber");
        String year = req.getParameter("year");
        String semester = req.getParameter("semester");

        if (sessionNumber != null && !sessionNumber.isBlank() && year != null && !year.isBlank() && semester != null && !semester.isBlank()) {
            try {
                Session session = new Session(null, Integer.parseInt(sessionNumber), Integer.parseInt(year), semester.trim());
                if (id != null && !id.isBlank()) {
                    session.setSessionId(Integer.parseInt(id));
                    Storage.updateSession(session);
                } else {
                    Storage.createSession(session);
                }
            } catch (NumberFormatException ignored) {}
        }
        resp.sendRedirect(req.getContextPath() + "/sessions");
    }
}