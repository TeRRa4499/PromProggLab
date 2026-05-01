package ru.example.servlet;
import ru.example.model.Subject;
import ru.example.storage.Storage;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveSubjectServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        String subjectName = req.getParameter("subjectName");

        if (subjectName != null && !subjectName.isBlank()) {
            try {
                Subject subject = new Subject(null, subjectName.trim());
                if (id != null && !id.isBlank()) {
                    subject.setSubjectId(Integer.parseInt(id));
                    Storage.updateSubject(subject);
                } else {
                    Storage.createSubject(subject);
                }
            } catch (NumberFormatException ignored) {}
        }
        resp.sendRedirect(req.getContextPath() + "/subjects");
    }
}