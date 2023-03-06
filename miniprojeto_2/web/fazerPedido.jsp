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
    
    <script
      src="https://code.jquery.com/jquery-1.10.2.js"
      type="text/javascript"
    ></script>
    <script src="scripts/adicionar.js" type="text/javascript"></script>

</head>
<body>
<main>
    <h1 class="title">Chess Burger</h1>
    <h2 class="subtitle">Escolha seus lanches</h2>
    <ul style="display: none;" id="adicionais">
        <% for (Ingrediente ingrediente : ingredientes) { %>
            <jsp:element name="li">
                <jsp:attribute name="data-id">
                    <%=ingrediente.getId()%>
                </jsp:attribute>
                <jsp:attribute name="data-nome">
                    <%=ingrediente.getNome()%>
                </jsp:attribute>
            </jsp:element>
        <%}%>
    </ul>
    <ul class="cards">
        <% for (Lanche lanche : lanches) { %>
        <li>
            <section class="card">
                <h3 class="card-title"><%=lanche.getNome()%>
                </h3>
                <h4 class="card-subtitle">Ingredientes:</h4>
                <ul>
                    <% for (Ingrediente ingrediente : lanche.getIngredientes()) { %>
                    
                    <jsp:element name="li">
                        <jsp:attribute name="data-id">
                            <%=ingrediente.getId()%>
                        </jsp:attribute>
                        <jsp:attribute name="class">
                            list-item
                        </jsp:attribute>
                        <jsp:attribute name="data-lanche">
                            <%=lanche.getId()%>
                        </jsp:attribute>
                        <jsp:body>
                            <%=ingrediente.getNome()%>
                        </jsp:body>
                    </jsp:element>
                  
                    <%}%>
                </ul>

                <div class="center">
                    <jsp:element name="button">
                        <jsp:attribute name="class">
                            btn center add-lanche
                        </jsp:attribute>
                        <jsp:attribute name="data-nome">
                            <%=lanche.getNome()%>
                        </jsp:attribute>
                        <jsp:attribute name="data-id">
                            <%=lanche.getId()%>
                        </jsp:attribute>
                        <jsp:body>
                            Adicionar ao pedido
                        </jsp:body>
                    </jsp:element>
                </div>
            </section>
        </li>
        <%}%>
    </ul>
    <h2 class="subtitle">Seu pedido</h2>
    <ul class="cards" id="pedidos">
    </ul>
    <div class="btn-div">
        <button class="btn">Cancelar pedido</button>
        <button class="btn">Confirmar pedido</button>
    </div>
</main>
</body>
</html>
