CREATE TABLE IF NOT EXISTS ingredientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS lanches (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS lanche_ingredientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_ingrediente INT,
    id_lanche INT,
    FOREIGN KEY (id_lanche) REFERENCES lanches (id) ON UPDATE RESTRICT ON DELETE CASCADE,
    FOREIGN KEY (id_ingrediente) REFERENCES ingredientes (id) ON UPDATE RESTRICT ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS pedidos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_cliente VARCHAR(255) NOT NULL,
    rua VARCHAR(255) NOT NULL,
    bairro VARCHAR(255) NOT NULL,
    numero VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS lanche_pedido (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_lanche INT,
    id_pedido INT,
    FOREIGN KEY (id_lanche) REFERENCES lanches (id) ON UPDATE RESTRICT ON DELETE CASCADE,
    FOREIGN KEY (id_pedido) REFERENCES pedidos (id) ON UPDATE RESTRICT ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS lanche_pedido_ingredientes_adicionados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_lanche_pedido INT,
    id_ingrediente INT,
    FOREIGN KEY (id_lanche_pedido) REFERENCES lanche_pedido(id) ON UPDATE RESTRICT ON DELETE CASCADE,
    FOREIGN KEY (id_ingrediente) REFERENCES ingredientes(id) ON UPDATE RESTRICT ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS lanche_pedido_ingredientes_removidos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_lanche_pedido INT,
    id_ingrediente INT,
    FOREIGN KEY (id_lanche_pedido) REFERENCES lanche_pedido(id) ON UPDATE RESTRICT ON DELETE CASCADE,
    FOREIGN KEY (id_ingrediente) REFERENCES ingredientes(id) ON UPDATE RESTRICT ON DELETE CASCADE
);