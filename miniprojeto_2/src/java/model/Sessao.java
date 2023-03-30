package model;

public class Sessao {
    private int id;
    private int userId;
    private String sessionId;

    public Sessao(int id, int userId, String sessionId) {
        this.id = id;
        this.userId = userId;
        this.sessionId = sessionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
