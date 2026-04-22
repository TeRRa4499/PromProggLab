package ru.example.servlet;

import ru.example.model.Student;
import ru.example.storage.Storage;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

public class StudentsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<Student> students = Storage.readAllStudents();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter w = resp.getWriter();

        w.println("<html><head><meta charset='UTF-8'><title>Students</title>");
        w.println("<style>table{border-collapse:collapse}th,td{border:1px solid black;padding:6px}</style>");
        w.println("</head><body>");
        w.println("<h2>Students</h2>");
        w.println("<p><a href='groups'>Groups</a></p>");
        w.println("<p><a href='students/edit'>Add new student</a></p>");
        w.println("<table><tr><th>ID</th><th>RecordBook</th><th>Last Name</th><th>First Name</th><th>Group ID</th><th>Action</th></tr>");

        for (Student student : students) {
            w.printf(
                    "<tr><td>%d</td><td>%s</td><td>%s</td><td>%s</td><td>%d</td><td>"
                            + "<a href='students/edit?id=%d'>Edit</a> "
                            + "<form method='post' action='students/delete' style='display:inline'>"
                            + "<input type='hidden' name='id' value='%d'>"
                            + "<button type='submit'>Delete</button></form>"
                            + "</td></tr>",
                    student.getStudentId(),
                    escape(student.getRecordBookNumber()),
                    escape(student.getLastName()),
                    escape(student.getFirstName()),
                    student.getGroupId(),
                    student.getStudentId(),
                    student.getStudentId()
            );
        }

        w.println("</table></body></html>");
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
