package ru.example.servlet;
import ru.example.model.User;
import ru.example.storage.Storage;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        User user = null;
        if (id != null && !id.isBlank()) {
            try { user = Storage.readUserById(Integer.parseInt(id)); } catch (NumberFormatException ignored) {}
        }
        req.setAttribute("user", user);
        req.setAttribute("isEdit", user != null);
        req.getRequestDispatcher("/WEB-INF/views/user-form.jsp").forward(req, resp);
    }
}