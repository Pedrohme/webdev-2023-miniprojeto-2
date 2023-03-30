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
 * Servlet implementation class LogoutServlet
 */
//@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        try {
            Cookie[] cookies = request.getCookies();

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }

            response.sendRedirect("index.html");
        } catch (Exception e) {

        }
    }

}





