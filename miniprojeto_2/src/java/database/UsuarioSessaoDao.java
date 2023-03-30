package database;

import DTO.LanchePedidoDTO;
import DTO.PedidoDTO;
import model.Ingrediente;
import model.Sessao;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioSessaoDao {
    private String LOGIN_QUERY = "SELECT id, username, password FROM usuario WHERE username = ? AND password = ?;";
    private String GET_USER_SESSION_QUERY = "SELECT id, id_session FROM sessao s WHERE id_usuario = ?;";
    private String INSERT_SESSION_STATEMENT = "INSERT INTO sessao (id_usuario, id_session) VALUES (?, ?);";
    private String UPDATE_SESSION_STATEMENT = "UPDATE sessao SET id_session = ? WHERE id = ?;";

    public Usuario loginUsuario(String username, String password) throws SQLException {
        try (Connection conn = DbHelper.getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(LOGIN_QUERY)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();

            rs.next();

            int userId = rs.getInt("id");
            String rsUsername = rs.getString("username");
            String rsPassword = rs.getString("password");

            Usuario usuario = new Usuario(userId, rsUsername, rsPassword);

            return usuario;
        } catch (SQLException e) {
            DbHelper.printSQLException(e);
            return null;
        }
    }

    public Sessao getSessao(int userId) throws SQLException {
        try (Connection conn = DbHelper.getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(GET_USER_SESSION_QUERY);) {
            preparedStatement.setInt(1, userId);

            ResultSet rs = preparedStatement.executeQuery();

            rs.next();

            int sessionDbId = rs.getInt("id");
            String sessionUuid = rs.getString("id_session");

            Sessao sessao = new Sessao(sessionDbId, userId, sessionUuid);

            return sessao;
        } catch (SQLException e) {
            DbHelper.printSQLException(e);
            return null;
        }
    }

    public void insertSessao(int userId, String sessionId) {
        try (Connection conn = DbHelper.getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(INSERT_SESSION_STATEMENT)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, sessionId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            DbHelper.printSQLException(e);
        }
    }

    public void updateSessionUuid(int sessionId, String uuid) {
        try (Connection conn = DbHelper.getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_SESSION_STATEMENT)) {
            preparedStatement.setString(1, uuid);
            preparedStatement.setInt(2, sessionId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            DbHelper.printSQLException(e);
        }
    }

}
