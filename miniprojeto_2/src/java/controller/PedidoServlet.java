package controller;

import java.io.IOException;
import java.util.List;

import DTO.PedidoDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import database.PedidoDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Lanche;
import model.Pedido;
import org.apache.tomcat.jakartaee.commons.io.IOUtils;

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
        try {
            String jsonString = IOUtils.toString(request.getInputStream());

            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();

            Gson gson = builder.create();

            PedidoDTO pedido = gson.fromJson(jsonString, PedidoDTO.class);

            pedidoDao.insertPedido(pedido);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
