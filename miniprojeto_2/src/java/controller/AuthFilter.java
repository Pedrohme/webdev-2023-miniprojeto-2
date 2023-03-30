package controller;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import database.UsuarioSessaoDao;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Sessao;

public class AuthFilter implements Filter {

    private ServletContext context;
    private UsuarioSessaoDao usuarioSessaoDao;

    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.usuarioSessaoDao = new UsuarioSessaoDao();
        this.context.log("AuthenticationFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        this.context.log("Requested Resource:" + uri);

        try {

            HttpSession session = req.getSession(false);

            if (session == null && !(uri.endsWith("index.html") || uri.endsWith("login.html") || uri.endsWith("LoginServlet"))) {
                String sessionUuid = "";
                int userId = 0;
                Cookie[] cookies = ((HttpServletRequest) request).getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("sessionId")) {
                            sessionUuid = cookie.getValue();
                        }
                        if (cookie.getName().equals("userId")) {
                            userId = Integer.parseInt(cookie.getValue());
                        }
                    }

                    Sessao sessao = usuarioSessaoDao.getSessao(userId);

                    if (sessao.getSessionId().equals(sessionUuid)) {
                        chain.doFilter(request, response);
                        return;
                    }
                }

                this.context.log("Unauthorized access request");
                res.sendRedirect("login.html");
            } else {
                chain.doFilter(request, response);
            }
        } catch (Exception e) {
            this.context.log("Unauthorized access request");
            res.sendRedirect("login.html");
        }
    }

    public void destroy() {

    }

}
