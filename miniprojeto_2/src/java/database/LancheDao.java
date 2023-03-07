package database;

import model.Ingrediente;
import model.Lanche;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LancheDao {

    private String GET_LANCHES_AND_INGREDIENTES_QUERY = "SELECT"
            + "                    	l.id AS ID_LANCHE,"
            + "                    	l.nome AS NOME_LANCHE,"
            + "                    	i.id AS ID_INGREDIENTE,"
            + "                    	i.nome AS NOME_INGREDIENTE"
            + "                    FROM"
            + "                    	lanches l"
            + "                    	INNER JOIN lanche_ingredientes li ON l.id  = li.id_lanche"
            + "                    	INNER JOIN ingredientes i ON i.id = li.id_ingrediente"
            + "                    ORDER BY ID_LANCHE ASC;";

    public List<Lanche> getAllLanches() throws SQLException {
        List<Lanche> lanches = new ArrayList<>();

        try (Connection conn = DbHelper.getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(GET_LANCHES_AND_INGREDIENTES_QUERY); ResultSet rs = preparedStatement.executeQuery()) {
            int currentId = 0;
            Lanche currentLanche = null;
            while (rs.next()) {
                int lancheId = rs.getInt("ID_LANCHE");
                if (lancheId > currentId) {
                    if (!(currentLanche == null)) {
                        lanches.add(currentLanche);
                    }
                    String lancheName = rs.getString("NOME_LANCHE");
                    currentLanche = new Lanche(lancheId, lancheName);
                    currentId = lancheId;
                }

                int newIngredienteId = rs.getInt("ID_INGREDIENTE");
                String newIngredienteNome = rs.getString("NOME_INGREDIENTE");

                Ingrediente newIngrediente = new Ingrediente(newIngredienteId, newIngredienteNome);

                assert currentLanche != null;
                currentLanche.addIngrediente(newIngrediente);
            }
        } catch (SQLException e) {
            DbHelper.printSQLException(e);
        }
        return lanches;
    }
}
