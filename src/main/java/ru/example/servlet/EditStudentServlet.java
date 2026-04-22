package ru.example.servlet;

import ru.example.model.Student;
import ru.example.storage.Storage;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class EditStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Student student = null;

        if (id != null && !id.isBlank()) {
            try {
                student = Storage.readStudentById(Integer.parseInt(id));
            } catch (NumberFormatException ignored) {
                student = null;
            }
        }

        boolean isEdit = student != null;
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter w = resp.getWriter();

        w.println("<html><head><meta charset='UTF-8'><title>Edit Student</title></head><body>");
        w.println("<h2>" + (isEdit ? "Edit student" : "Create student") + "</h2>");
        w.println("<form method='post' action='" + req.getContextPath() + "/students/save'>");

        if (isEdit) {
            w.println("<input type='hidden' name='id' value='" + student.getStudentId() + "'>");
        }

        w.println("Record book: <input name='recordBookNumber' required value='" + escape(isEdit ? student.getRecordBookNumber() : "") + "'><br><br>");
        w.println("Last name: <input name='lastName' required value='" + escape(isEdit ? student.getLastName() : "") + "'><br><br>");
        w.println("First name: <input name='firstName' required value='" + escape(isEdit ? student.getFirstName() : "") + "'><br><br>");
        w.println("Group ID: <input type='number' min='1' name='groupId' required value='" + (isEdit ? student.getGroupId() : "") + "'><br><br>");
        w.println("<button type='submit'>" + (isEdit ? "Update" : "Create") + "</button>");
        w.println("</form><br>");
        w.println("<a href='" + req.getContextPath() + "/students'>Back to students</a>");
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
