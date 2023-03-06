<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./styles/reset.css" />
        <link rel="stylesheet" href="./styles/index.css" />
        <link rel="stylesheet" href="./styles/verPedido.css" />
        <title>Chess Burger</title>
    </head>
    <body>
        <main>
            <h1 class="title">Pedidos</h1>
            <ul class="cards">
                <li>
                    <section class="card">
                        <h2 class="card-title">Pedido</h2>
                        <ul>
                            <li>
                                <article>
                                    <h3 class="card-subtitle">{Nome do lanche}</h3>
                                    <h3 class="card-subtitle">Observações:</h3>
                                    <div>
                                        <h4>Adicionais:</h4>
                                        <ul>
                                            <li class="list-item">{Adicional 1}</li>
                                        </ul>
                                    </div>
                                    <div>
                                        <h4>Remover:</h4>
                                        <ul>
                                            <li class="list-item">{Ingrediente 1}</li>
                                        </ul>
                                    </div>
                                </article>
                            </li>
                        </ul>
                    </section>
                </li>
            </ul>
        </main>
    </body>
</html>
