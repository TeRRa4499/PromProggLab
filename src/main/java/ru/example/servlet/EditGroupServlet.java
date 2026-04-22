package ru.example.servlet;

import ru.example.model.Group;
import ru.example.storage.Storage;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class EditGroupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Group group = null;

        if (id != null && !id.isBlank()) {
            try {
                group = Storage.readGroupById(Integer.parseInt(id));
            } catch (NumberFormatException ignored) {
                group = null;
            }
        }

        boolean isEdit = group != null;
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter w = resp.getWriter();

        w.println("<html><head><meta charset='UTF-8'><title>Edit Group</title></head><body>");
        w.println("<h2>" + (isEdit ? "Edit group" : "Create group") + "</h2>");
        w.println("<form method='post' action='" + req.getContextPath() + "/groups/save'>");

        if (isEdit) {
            w.println("<input type='hidden' name='id' value='" + group.getGroupId() + "'>");
        }

        w.println("Имя: <input name='groupName' required value='" + escape(isEdit ? group.getGroupName() : "") + "'><br><br>");
        w.println("Курс: <input type='number' min='1' name='course' required value='" + (isEdit ? group.getCourse() : "") + "'><br><br>");
        w.println("Факультет: <input name='faculty' required value='" + escape(isEdit ? group.getFaculty() : "") + "'><br><br>");
        w.println("<button type='submit'>" + (isEdit ? "Update" : "Create") + "</button>");
        w.println("</form><br>");
        w.println("<a href='" + req.getContextPath() + "/groups'>Back to groups</a>");
        w.println("</body></html>");
    }

    private String escape(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;");
    }
}
