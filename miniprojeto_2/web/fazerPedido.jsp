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
    <title>JSP Page</title>
</head>
<body>
<main>
    <h1>Chess Burger</h1>
    <h2>Escolha seus lanches</h2>
    <ul>
        <% for (Lanche lanche : lanches) { %>
        <li>
            <section>
                <h3><%=lanche.getNome()%>
                </h3>
                <ul>
                    <% for (Ingrediente ingrediente : lanche.getIngredientes()) { %>
                    <li><%=ingrediente.getNome()%>
                    </li>
                    <%}%>
                </ul>
                <button>Adicionar ao pedido</button>
            </section>
        </li>
        <%}%>
    </ul>
    <h2>Seu pedido</h2>
    <ul>
        <li>
            <section>
                <h3>{Nome do lanche}</h3>
                <button>Remover do pedido</button>
                <article>
                    <h3>Que tal subir o rating do seu lanche?</h3>
                    <ul>
                        <li>
                            <p>{Adicional 1}</p>
                            <button>Adicionar no lanche</button>
                        </li>
                    </ul>
                </article>
                <article>
                    <h3>Deseja remover alguma peça dessa posição?</h3>
                    <ul>
                        <li>
                            <p>{Ingrediente 1}</p>
                            <button>Remover do lanche</button>
                        </li>
                    </ul>
                </article>
            </section>
        </li>
    </ul>
    <div>
        <button>Cancelar pedido</button>
        <button>Confirmar pedido</button>
    </div>
</main>
</body>
</html>
