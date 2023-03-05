<%@page import="java.util.List" %>
<%@page import="model.Lanche" %>
<%@page import="model.Ingrediente" %>
<%@page import="model.Pedido" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<% List<Lanche> lanches = (List<Lanche>) request.getAttribute("lanches"); %>
<% List<Ingrediente> ingredientes = (List<Ingrediente>) request.getAttribute("ingredientes"); %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="./styles/reset.css" />
    <link rel="stylesheet" href="./styles/index.css" />
    <link rel="stylesheet" href="./styles/fazerPedido.css" />
    <title>Chess Burger</title>
</head>
<body>
<main>
    <h1 class="title">Chess Burger</h1>
    <h2 class="subtitle">Escolha seus lanches</h2>
    <ul class="cards">
        <% for (Lanche lanche : lanches) { %>
        <li>
            <section class="card">
                <h3 class="card-title"><%=lanche.getNome()%>
                </h3>
                <h4 class="card-subtitle">Ingredientes:</h4>
                <ul>
                    <% for (Ingrediente ingrediente : lanche.getIngredientes()) { %>
                    <li class="list-item"><%=ingrediente.getNome()%>
                    </li>
                    <%}%>
                </ul>
                <div class="center">
                    <button class="btn center">Adicionar ao pedido</button>
                </div>
            </section>
        </li>
        <%}%>
    </ul>
    <h2 class="subtitle">Seu pedido</h2>
    <ul class="cards">
        <li>
            <section class="card">
                <h3 class="card-title">{Nome do lanche}</h3>
                <h4 class="card-subtitle">Adicionais:</h4>
                <ul>
                    <li class="list-item">{Adicional 1}</li>
                </ul>
                <h4 class="card-subtitle">Removidos:</h4>
                <ul>
                    <li class="list-item">{Ingrediente 1}</li>
                </ul>
                <article>
                    <h4 class="card-subtitle">
                        Que tal subir o rating do seu lanche?
                    </h4>
                    <ul>
                        <li>
                            <p class="list-item">{Adicional 1}</p>
                            <button class="btn">Adicionar</button>
                        </li>
                    </ul>
                </article>
                <article>
                    <h4 class="card-subtitle">
                        Deseja remover alguma peça dessa posição?
                    </h4>
                    <ul>
                        <li>
                            <p class="list-item">{Ingrediente 1}</p>
                            <button class="btn">Remover</button>
                        </li>
                    </ul>
                </article>
                <div class="center">
                    <button class="btn">Excluir do pedido</button>
                </div>
            </section>
        </li>
    </ul>
    <div class="btn-div">
        <button class="btn">Cancelar pedido</button>
        <button class="btn">Confirmar pedido</button>
    </div>
</main>
</body>
</html>
