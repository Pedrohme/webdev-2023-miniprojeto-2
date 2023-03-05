package controller;

import database.IngredienteDao;
import database.LancheDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Ingrediente;
import model.Lanche;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GetLanchesServlet extends HttpServlet {
    private LancheDao lancheDao;
    private IngredienteDao ingredienteDao;

    @Override
    public void init() {
        lancheDao = new LancheDao();
        ingredienteDao = new IngredienteDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Lanche> lanches = lancheDao.getAllLanches();
            List<Ingrediente> ingredientes = ingredienteDao.getAllIngredientes();
            request.setAttribute("lanches", lanches);
            request.setAttribute("ingredientes", ingredientes);
            RequestDispatcher dispatcher = request.getRequestDispatcher("fazerPedido.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
