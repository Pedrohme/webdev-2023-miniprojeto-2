package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import database.PedidoDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Ingrediente;
import model.LanchePedido;
import model.Pedido;

public class PedidoAjaxServlet extends HttpServlet {
    private PedidoDao pedidoDao;

    @Override
    public void init() {
        pedidoDao = new PedidoDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Pedido> pedidos = pedidoDao.getAllPedidos();

            PrintWriter out = response.getWriter();
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");

            out.println("    <form method=\"post\" action=\"LogoutServlet\">");
            out.println("        <input type=\"submit\">Logout</input>");
            out.println("    </form>");
            out.println("    <h1 class=\"title\">Pedidos</h1>");
            out.println("    <ul class=\"cards\">");
            for (Pedido pedido : pedidos) {
                out.println("        <li>");
                out.println("            <section class=\"card\">");
                out.println("                <h2 class=\"card-title\">Pedido número: " + pedido.getId());
                out.println("                </h2>");
                out.println("                <section>");
                out.println("                    <p><b>Nome do cliente: </b>" + pedido.getNomeCliente());
                out.println("                    </p>");
                out.println("                    <h3>Endereço:</h3>");
                out.println("                    <p><b>Rua: </b>" + pedido.getRua());
                out.println("                    </p>");
                out.println("                    <p><b>Bairro: </b>" + pedido.getBairro());
                out.println("                    </p>");
                out.println("                    <p><b>Número: </b>" + pedido.getNumero());
                out.println("                    </p>");
                out.println("                </section>");
                out.println("                <ul>");
                for (LanchePedido lanche : pedido.getLanches()) {
                    out.println("                    <li>");
                    out.println("                        <article>");
                    out.println("                            <h3 class=\"card-subtitle\">" + lanche.getLanche().getNome());
                    out.println("                            </h3>");
                    if (lanche.getLanche().getAdicionados().size() > 0) {
                        out.println("                            <div>");
                        out.println("                                <h4>Adicionais:</h4>");
                        out.println("                                <ul>");
                        for (Ingrediente ingrediente : lanche.getLanche().getAdicionados()) {
                            out.println("                                    <li class=\"list-item\">" + ingrediente.getNome());
                            out.println("                                    </li>");
                        }
                        out.println("                                </ul>");
                        out.println("                            </div>");
                    }
                    if (lanche.getLanche().getRemovidos().size() > 0) {
                        out.println("                            <div>");
                        out.println("                                <h4>Remover:</h4>");
                        out.println("                                <ul>");
                        for (Ingrediente ingrediente : lanche.getLanche().getRemovidos()) {
                            out.println("                                    <li class=\"list-item\">" + ingrediente.getNome());
                            out.println("                                    </li>");
                        }
                        out.println("                                </ul>");
                        out.println("                            </div>");
                    }
                    out.println("                        </article>");
                    out.println("                    </li>");
                }
                out.println("                </ul>");
                out.println("            </section>");
                out.println("        </li>");
            }
            out.println("    </ul>");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
