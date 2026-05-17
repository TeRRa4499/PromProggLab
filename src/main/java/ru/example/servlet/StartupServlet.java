package ru.example.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.example.storage.Storage;

import java.io.IOException;


public final class StartupServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/univ?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC"
                        + "&useUnicode=true"
                        + "&characterEncodiing=UTF-8";
            String user = "root";
            String password = "12345";
            try {
                Storage.init(
                        driver,
                        url,
                        user,
                        password
                );
            } catch (ClassNotFoundException e) {
                throw new ServletException("Не найден JDBC-драйвер: " + driver, e);
            }
        req.getRequestDispatcher("/WEB-INF/views/start.jsp").forward(req, resp);
    }

}
