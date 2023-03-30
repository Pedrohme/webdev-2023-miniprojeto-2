package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import database.IngredienteDao;
import database.LancheDao;
import database.UsuarioSessaoDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Sessao;
import model.Usuario;

/**
 * Servlet implementation class LoginServlet
 */
//@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UsuarioSessaoDao usuarioSessaoDao;

    @Override
    public void init() {
        usuarioSessaoDao = new UsuarioSessaoDao();
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        // get request parameters for userID and password
        String user = request.getParameter("username");
        String pwd = request.getParameter("password");

        try {
            Usuario usuario = usuarioSessaoDao.loginUsuario(user, pwd);
            if (usuario != null) {
                Sessao sessao = usuarioSessaoDao.getSessao(usuario.getId());

                String uuid = UUID.randomUUID().toString();
                if (sessao == null) {
                    usuarioSessaoDao.insertSessao(usuario.getId(), uuid);
                } else {
                    usuarioSessaoDao.updateSessionUuid(sessao.getId(), uuid);
                }

                Cookie userIdCookie = new Cookie("userId", Integer.toString(usuario.getId()));
                Cookie sessionIdCookie = new Cookie("sessionId", uuid);

                userIdCookie.setMaxAge(30 * 60);
                sessionIdCookie.setMaxAge(30 * 60);

                response.addCookie(userIdCookie);
                response.addCookie(sessionIdCookie);

                HttpSession session = request.getSession(true);
                session.setAttribute("userId", Integer.toString(usuario.getId()));
                session.setMaxInactiveInterval(30 * 60);

                response.sendRedirect("PedidoServlet");
            } else {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
                PrintWriter out = response.getWriter();
                out.println("<font color=red>Erro: User id ou password incorreto.</font>");
                rd.include(request, response);
            }
        } catch (Exception e) {

        }


    }

}
