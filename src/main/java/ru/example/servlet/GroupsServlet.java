package ru.example.servlet;

import ru.example.model.Group;
import ru.example.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

public class GroupsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<Group> groups = Storage.readAllGroups();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter w = resp.getWriter();

        w.println("<html><head><meta charset='UTF-8'><title>Groups</title>");
        w.println("<style>table{border-collapse:collapse}th,td{border:1px solid black;padding:6px}</style>");
        w.println("</head><body>");
        w.println("<h2>Groups</h2>");
        w.println("<p><a href='students'>Students</a></p>");
        w.println("<form method='post' action='groups'>");
        w.println("Name: <input name='groupName' required> ");
        w.println("Course: <input type='number' min='1' name='course' required> ");
        w.println("Faculty: <input name='faculty' required> ");
        w.println("<button type='submit'>Add</button></form><br>");
        w.println("<table><tr><th>ID</th><th>Name</th><th>Course</th><th>Faculty</th><th>Action</th></tr>");

        for (Group group : groups) {
            w.printf(
                    "<tr><td>%d</td><td>%s</td><td>%d</td><td>%s</td><td>"
                            + "<form method='post' action='groups/delete' style='display:inline'>"
                            + "<input type='hidden' name='id' value='%d'>"
                            + "<button type='submit'>Delete</button></form>"
                            + "</td></tr>",
                    group.getGroupId(),
                    escape(group.getGroupName()),
                    group.getCourse(),
                    escape(group.getFaculty()),
                    group.getGroupId()
            );
        }

        w.println("</table></body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String groupName = req.getParameter("groupName");
        String course = req.getParameter("course");
        String faculty = req.getParameter("faculty");

        if (groupName != null && !groupName.trim().isEmpty() && course != null && faculty != null) {
            Storage.createGroup(new Group(null, groupName.trim(), Integer.parseInt(course), faculty.trim()));
        }

        resp.sendRedirect(req.getContextPath() + "/groups");
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
