package Team;

import ConnectionForDataBase.ConnectionForDataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

public class Team {
    private int id;
    private String name;
    public Team() {}
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void printGroupList() throws SQLException {
        ConnectionForDataBase connectionForDataBase = new ConnectionForDataBase();
        Statement statement = connectionForDataBase.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("select * from university.team");

        System.out.printf("%s| %-5s | %-30s |%s\n","\u001B[33m", "id", "Имя группы", "\u001B[0m");

        while (resultSet.next()) {
            Team group = new Team();
            group.setId(resultSet.getInt("id"));
            group.setName(resultSet.getString("name"));
            System.out.printf("| %-5d | %-30s |\n", group.getId(), group.getName());
        }
        statement.close();
        connectionForDataBase.closeConnection();
    }
    public ArrayList<Team> getGroupList() throws SQLException {
        ArrayList<Team> list = new ArrayList<>();
        ConnectionForDataBase connectionForDataBase = new ConnectionForDataBase();
        Statement statement = connectionForDataBase.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("select * from university.team");

        while (resultSet.next()) {
            Team group = new Team();
            group.setId(resultSet.getInt("id"));
            group.setName(resultSet.getString("name"));
            list.add(group);
        }
        statement.close();
        connectionForDataBase.closeConnection();
        return list;
    }
}
