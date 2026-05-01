package ru.example.servlet;
import ru.example.model.Grade;
import ru.example.storage.Storage;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveGradeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        String studentId = req.getParameter("studentId");
        String subjectId = req.getParameter("subjectId");
        String sessionId = req.getParameter("sessionId");
        String score = req.getParameter("score");

        if (studentId != null && !studentId.isBlank() && subjectId != null && !subjectId.isBlank()
                && sessionId != null && !sessionId.isBlank() && score != null && !score.isBlank()) {
            try {
                Grade grade = new Grade(null, Integer.parseInt(studentId), Integer.parseInt(subjectId), Integer.parseInt(sessionId), Integer.parseInt(score));
                if (id != null && !id.isBlank()) {
                    grade.setGradeId(Integer.parseInt(id));
                    Storage.updateGrade(grade);
                } else {
                    Storage.createGrade(grade);
                }
            } catch (NumberFormatException ignored) {}
        }
        resp.sendRedirect(req.getContextPath() + "/grades");
    }
}