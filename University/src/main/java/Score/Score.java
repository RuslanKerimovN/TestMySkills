package Score;

import ConnectionForDataBase.ConnectionForDataBase;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Score{
    private int id;
    private int score;
    private String description;

    public Score() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getText() {
        return description;
    }

    public void setText(String text) {
        this.description = text;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", score=" + score +
                ", description='" + description + '\'' +
                '}';
    }

    public void printScoreList() throws SQLException {
        ConnectionForDataBase connectionForDataBase = new ConnectionForDataBase();
        Statement statement = connectionForDataBase.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("select * from university.score");

        System.out.printf("%s| %-5s | %-15s | %-20s|%s\n","\u001B[33m", "id", "Оценка", "Описание", "\u001B[0m");

        while (resultSet.next()) {
            Score score = new Score();
            score.setId(resultSet.getInt("id"));
            score.setScore(resultSet.getInt("value"));
            score.setText(resultSet.getString("description"));
            System.out.printf("| %-5d | %-15d | %-20s|\n", score.getId(), score.getScore(), score.getText());
        }
        statement.close();
        connectionForDataBase.closeConnection();
    }
    public ArrayList<Score> getScoreList() throws SQLException {
        ConnectionForDataBase connectionForDataBase = new ConnectionForDataBase();
        ArrayList<Score> list = new ArrayList<>();
        Statement statement = connectionForDataBase.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("select * from university.score");

        while (resultSet.next()) {
            Score score = new Score();
            score.setId(resultSet.getInt("id"));
            score.setScore(resultSet.getInt("value"));
            score.setText(resultSet.getString("description"));
            list.add(score);
        }
        statement.close();
        connectionForDataBase.closeConnection();
        return list;
    }
}
