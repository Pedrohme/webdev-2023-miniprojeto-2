package database;

import model.Ingrediente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredienteDao {
    private String GET_INGREDIENTES_QUERY = """
                    SELECT
                    	i.id AS ID_INGREDIENTE,
                    	i.nome AS NOME_INGREDIENTE
                    FROM
                    	ingredientes i
                    ORDER BY ID_INGREDIENTE ASC;
            """;

    public List<Ingrediente> getAllIngredientes() throws SQLException {
        List<Ingrediente> ingredientes = new ArrayList<>();

        try {
            Connection conn = DbHelper.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement(GET_INGREDIENTES_QUERY);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int newIngredienteId = rs.getInt("ID_INGREDIENTE");
                String newIngredienteNome = rs.getString("NOME_INGREDIENTE");

                Ingrediente newIngrediente = new Ingrediente(newIngredienteId, newIngredienteNome);

                ingredientes.add(newIngrediente);
            }

        } catch (SQLException e) {
            DbHelper.printSQLException(e);
        }
        return ingredientes;
    }
}
