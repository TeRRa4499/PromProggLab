package ru.example.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.example.storage.Storage;

import java.io.IOException;

/**
 * При первом открытии стартовых URL вызывает {@link Storage#init}, затем отдаёт разметку через JSP.
 */
public final class StartupServlet extends HttpServlet {

    private static final String START_VIEW = "/WEB-INF/views/start.jsp";

    private static final Object LOCK = new Object();
    private static volatile boolean storageInitialized;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        ensureStorageInitialized(req.getServletContext());
        req.getRequestDispatcher(START_VIEW).forward(req, resp);
    }

    private static void ensureStorageInitialized(ServletContext sc) throws ServletException {
        if (storageInitialized) {
            return;
        }
        synchronized (LOCK) {
            if (storageInitialized) {
                return;
            }
            String driver = sc.getInitParameter("jdbc.driver");
            String url = sc.getInitParameter("jdbc.url");
            String user = sc.getInitParameter("jdbc.user");
            String password = sc.getInitParameter("jdbc.password");
            if (driver == null || driver.isBlank() || url == null || url.isBlank()) {
                throw new ServletException("Задайте context-param jdbc.driver и jdbc.url в web.xml");
            }
            try {
                Storage.init(
                        driver.trim(),
                        url.trim(),
                        user != null ? user.trim() : "",
                        password != null ? password : ""
                );
            } catch (ClassNotFoundException e) {
                throw new ServletException("Не найден JDBC-драйвер: " + driver, e);
            }
            storageInitialized = true;
        }
    }
}
