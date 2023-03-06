package controller;

import java.io.IOException;
import java.util.List;

import database.PedidoDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Lanche;
import model.Pedido;

public class PedidoServlet extends HttpServlet {
    private PedidoDao pedidoDao;

    @Override
    public void init() {
        pedidoDao = new PedidoDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Pedido> pedidos = pedidoDao.getAllPedidos();
            request.setAttribute("pedidos", pedidos);
            RequestDispatcher dispatcher = request.getRequestDispatcher("verPedidos.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
