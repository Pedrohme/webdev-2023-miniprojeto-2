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
    <script
            src="https://code.jquery.com/jquery-1.10.2.js"
            type="text/javascript"
    ></script>
    <script src="scripts/ajaxPedidos.js" type="text/javascript"></script>
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
                <section>
                    <p><b>Nome do cliente: </b><%=pedido.getNomeCliente()%>
                    </p>
                    <h3>Endereço:</h3>
                    <p><b>Rua: </b><%=pedido.getRua()%>
                    </p>
                    <p><b>Bairro: </b><%=pedido.getBairro()%>
                    </p>
                    <p><b>Número: </b><%=pedido.getNumero()%>
                    </p>
                </section>
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
    <button id="btn-atualizar"> Atualizar </button>
</main>
</body>
</html>
