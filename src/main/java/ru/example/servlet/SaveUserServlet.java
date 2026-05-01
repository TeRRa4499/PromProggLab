package ru.example.servlet;
import ru.example.model.User;
import ru.example.storage.Storage;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class SaveUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        String login = req.getParameter("login");
        String passwordHash = req.getParameter("passwordHash");
        String role = req.getParameter("role");
        String studentIdStr = req.getParameter("studentId");
        String email = req.getParameter("email");
        String createdDateStr = req.getParameter("createdDate");
        String isActiveStr = req.getParameter("isActive");

        if (login != null && !login.isBlank() && role != null && !role.isBlank()) {
            try {
                Integer studentId = studentIdStr != null && !studentIdStr.isBlank() ? Integer.parseInt(studentIdStr) : null;
                LocalDate createdDate = createdDateStr != null && !createdDateStr.isBlank() ? LocalDate.parse(createdDateStr) : LocalDate.now();
                Boolean isActive = isActiveStr != null && !isActiveStr.isBlank() ? Boolean.parseBoolean(isActiveStr) : true;

                User user = new User(null, login.trim(), passwordHash, role.trim(), studentId, email, createdDate, isActive);
                if (id != null && !id.isBlank()) {
                    user.setUserId(Integer.parseInt(id));
                    Storage.updateUser(user);
                } else {
                    Storage.createUser(user);
                }
            } catch (Exception ignored) {}
        }
        resp.sendRedirect(req.getContextPath() + "/users");
    }
}