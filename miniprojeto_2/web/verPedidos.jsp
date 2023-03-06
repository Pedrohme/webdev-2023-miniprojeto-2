<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.List" %>
<%@page import="model.Lanche" %>
<%@page import="model.LanchePedido" %>
<%@page import="model.Ingrediente" %>
<%@page import="model.Pedido" %>

<% List<Pedido> pedidos = (List<Pedido>) request.getAttribute("pedidos"); %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="./styles/reset.css"/>
    <link rel="stylesheet" href="./styles/index.css"/>
    <link rel="stylesheet" href="./styles/verPedido.css"/>
    <title>Chess Burger</title>
</head>
<body>
<main>
    <h1 class="title">Pedidos</h1>
    <ul class="cards">
        <% for (Pedido pedido : pedidos) { %>
        <li>
            <section class="card">
                <h2 class="card-title">Pedido número: <%=pedido.getId()%>
                </h2>
                <ul>
                    <% for (LanchePedido lanche : pedido.getLanches()) { %>
                    <li>
                        <article>
                            <h3 class="card-subtitle"><%=lanche.getLanche().getNome()%>
                            </h3>
                            <% if (lanche.getLanche().getAdicionados().size() > 0) { %>
                            <div>
                                <h4>Adicionais:</h4>
                                <ul>
                                    <% for (Ingrediente ingrediente : lanche.getLanche().getAdicionados()) { %>
                                    <li class="list-item"><%=ingrediente.getNome()%>
                                    </li>
                                    <%}%>
                                </ul>
                            </div>
                            <%}%>
                            <% if (lanche.getLanche().getRemovidos().size() > 0) { %>
                            <div>
                                <h4>Remover:</h4>
                                <ul>
                                    <% for (Ingrediente ingrediente : lanche.getLanche().getRemovidos()) { %>
                                    <li class="list-item"><%=ingrediente.getNome()%>
                                    </li>
                                    <%}%>
                                </ul>
                            </div>
                            <%}%>
                        </article>
                    </li>
                    <%}%>
                </ul>
            </section>
        </li>
        <%}%>
    </ul>
</main>
</body>
</html>
