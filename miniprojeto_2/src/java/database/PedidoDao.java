package database;

import DTO.LanchePedidoDTO;
import DTO.PedidoDTO;
import model.Ingrediente;
import model.Lanche;
import model.LanchePedido;
import model.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDao {

    private String GET_PEDIDOS_QUERY = "SELECT" + "                    	p.id as ID_PEDIDO," + "                    	p.nome_cliente as NOME_CLIENTE," + "                    	p.rua as RUA," + "                    	p.bairro as BAIRRO," + "                    	p.numero as NUMERO," + "                    	lp.id as ID_LANCHE_PEDIDO," + "                    	l.id as ID_LANCHE," + "                    	l.nome as NOME_LANCHE," + "                    	lpie.id_ingrediente as ID_INGREDIENTE," + "                    	i.nome as NOME_INGREDIENTE," + "                    	lpie.remover as REMOVER" + "                    FROM" + "                    	pedidos p" + "                    INNER JOIN lanche_pedido lp ON" + "                    	lp.id_pedido = p.id" + "                    INNER JOIN lanches l ON" + "                    	l.id = lp.id_lanche" + "                    LEFT JOIN (lanche_pedido_ingredientes_extras lpie" + "                    INNER JOIN ingredientes i ON" + "                    	i.id = lpie.id_ingrediente) ON lpie.id_lanche_pedido = lp.id" + "                    ORDER BY" + "                    	ID_PEDIDO ASC, ID_LANCHE_PEDIDO ASC, REMOVER ASC, ID_INGREDIENTE ASC;";

    private String INSERT_PEDIDO_STATEMENT = "INSERT INTO pedidos (nome_cliente, rua, bairro, numero) VALUES (?, ?, ?, ?);";
    private String INSERT_LANCHE_PEDIDO_STATEMENT = "INSERT INTO lanche_pedido (id_lanche, id_pedido) VALUES (?, ?);";
    private String INSERT_LANCHE_PEDIDO_INGREDIENTES_EXTRAS_STATEMENT = "INSERT INTO lanche_pedido_ingredientes_extras (id_lanche_pedido, id_ingrediente, remover) VALUES (?, ?, ?);";

    public List<Pedido> getAllPedidos() {
        List<Pedido> pedidos = new ArrayList<>();

        try (Connection conn = DbHelper.getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(GET_PEDIDOS_QUERY); ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                int pedidoId = rs.getInt("ID_PEDIDO");

                Pedido currentPedido = pedidos.stream().filter(pedido -> pedidoId == pedido.getId()).findFirst().orElse(null);

                if (currentPedido == null) {
                    String nomeCliente = rs.getString("NOME_CLIENTE");
                    String rua = rs.getString("RUA");
                    String bairro = rs.getString("BAIRRO");
                    String numero = rs.getString("NUMERO");
                    Pedido newPedido = new Pedido(pedidoId, nomeCliente, rua, bairro, numero);
                    pedidos.add(newPedido);
                    currentPedido = pedidos.stream().filter(pedido -> pedidoId == pedido.getId()).findFirst().orElse(null);
                }

                List<LanchePedido> pedidoLanches = currentPedido.getLanches();
                int lanchePedidoId = rs.getInt("ID_LANCHE_PEDIDO");
                LanchePedido currentLanchePedido = pedidoLanches.stream().filter(lanche -> lanche.getId() == lanchePedidoId).findFirst().orElse(null);

                if (currentLanchePedido == null) {
                    int idLanche = rs.getInt("ID_LANCHE");
                    String nomeLanche = rs.getString("NOME_LANCHE");

                    Lanche newLanche = new Lanche(idLanche, nomeLanche);
                    LanchePedido newLanchePedido = new LanchePedido(lanchePedidoId, newLanche);
                    pedidoLanches.add(newLanchePedido);
                    pedidos.stream().filter(pedido -> pedidoId == pedido.getId()).findFirst().ifPresent(pedido -> pedido.setLanches(pedidoLanches));
                    currentLanchePedido = newLanchePedido;
                }

                int ingredienteExtraId = rs.getInt("ID_INGREDIENTE");
                if (!rs.wasNull()) {
                    String ingredienteExtraNome = rs.getString("NOME_INGREDIENTE");
                    int currentLanchePedidoId = currentLanchePedido.getId();

                    Ingrediente novoExtra = new Ingrediente(ingredienteExtraId, ingredienteExtraNome);

                    boolean remover = rs.getBoolean("REMOVER");
                    if (remover == true) {
                        pedidoLanches.stream().filter(lanche -> lanche.getId() == currentLanchePedidoId).findFirst().ifPresent(lanche -> lanche.addRemovido(novoExtra));
                    } else {
                        pedidoLanches.stream().filter(lanche -> lanche.getId() == currentLanchePedidoId).findFirst().ifPresent(lanche -> lanche.addAdicionado(novoExtra));
                    }

                    pedidos.stream().filter(pedido -> pedidoId == pedido.getId()).findFirst().ifPresent(pedido -> pedido.setLanches(pedidoLanches));
                }
            }
        } catch (SQLException e) {
            DbHelper.printSQLException(e);
        }
        return pedidos;
    }

    public void insertPedido(PedidoDTO pedidoDto) {
        try (Connection conn = DbHelper.getConnection()) {

            try (PreparedStatement preparedStatement = conn.prepareStatement(INSERT_PEDIDO_STATEMENT)) {
                preparedStatement.setString(1, pedidoDto.getNome());
                preparedStatement.setString(2, pedidoDto.getRua());
                preparedStatement.setString(3, pedidoDto.getBairro());
                preparedStatement.setString(4, pedidoDto.getNumero());

                preparedStatement.executeUpdate();
            }

            int pedidoId = 0;

            try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT LAST_INSERT_ID() AS ID;"); ResultSet rs = preparedStatement.executeQuery()) {
                rs.next();
                pedidoId = rs.getInt("ID");
            }

            for (LanchePedidoDTO lanchePedido : pedidoDto.getItens()) {
                int lanchePedidoId = 0;
                try (PreparedStatement preparedStatement = conn.prepareStatement(INSERT_LANCHE_PEDIDO_STATEMENT)) {
                    preparedStatement.setInt(1, lanchePedido.getId());
                    preparedStatement.setInt(2, pedidoId);
                    preparedStatement.executeUpdate();

                    try (PreparedStatement statementGetId = conn.prepareStatement("SELECT LAST_INSERT_ID() AS ID;"); ResultSet idRs = statementGetId.executeQuery()) {
                        idRs.next();
                        lanchePedidoId = idRs.getInt("ID");
                    }

                    for (Ingrediente extra : lanchePedido.getAdicionados()) {
                        try (PreparedStatement insertLancheExtraStatement = conn.prepareStatement(INSERT_LANCHE_PEDIDO_INGREDIENTES_EXTRAS_STATEMENT)) {
                            insertLancheExtraStatement.setInt(1, lanchePedidoId);
                            insertLancheExtraStatement.setInt(2, extra.getId());
                            insertLancheExtraStatement.setBoolean(3, false);

                            insertLancheExtraStatement.executeUpdate();
                        }
                    }
                    for (Ingrediente extra : lanchePedido.getRemovidos()) {
                        try (PreparedStatement insertLancheExtraStatement = conn.prepareStatement(INSERT_LANCHE_PEDIDO_INGREDIENTES_EXTRAS_STATEMENT)) {
                            insertLancheExtraStatement.setInt(1, lanchePedidoId);
                            insertLancheExtraStatement.setInt(2, extra.getId());
                            insertLancheExtraStatement.setBoolean(3, true);

                            insertLancheExtraStatement.executeUpdate();
                        }
                    }
                }
            }

        } catch (SQLException e) {
            DbHelper.printSQLException(e);
        }
    }

}
