package ru.example.servlet;
import ru.example.storage.Storage;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteGradeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        try {
            if (id != null) Storage.deleteGrade(Integer.parseInt(id));
        } catch (NumberFormatException | SQLException ignored) {}
        resp.sendRedirect(req.getContextPath() + "/grades");
    }
}