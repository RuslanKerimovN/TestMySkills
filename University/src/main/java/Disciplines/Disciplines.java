package Disciplines;

import ConnectionForDataBase.ConnectionForDataBase;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Disciplines {
    private int id;
    private String name;

    public Disciplines() {}

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

    @Override
    public String toString() {
        return "Disciplines{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public void printDisciplineList() throws SQLException {
        ConnectionForDataBase connectionForDataBase = new ConnectionForDataBase();
        Statement statement = connectionForDataBase.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("select * from university.disciplines");

        System.out.printf("%s| %-5s | %-30s |%s\n","\u001B[33m", "id", "Дисциплина", "\u001B[0m");

        while (resultSet.next()) {
            Disciplines disciplines = new Disciplines();
            disciplines.setId(resultSet.getInt("id"));
            disciplines.setName(resultSet.getString("name"));
            System.out.printf("| %-5d | %-30s |\n", disciplines.getId(), disciplines.getName());
        }
        statement.close();
        connectionForDataBase.closeConnection();
    }

    public ArrayList<Disciplines> getDisciplineList() throws SQLException {
        ArrayList<Disciplines> list = new ArrayList<>();
        ConnectionForDataBase connectionForDataBase = new ConnectionForDataBase();
        Statement statement = connectionForDataBase.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("select * from university.disciplines");

        while (resultSet.next()) {
            Disciplines disciplines = new Disciplines();
            disciplines.setId(resultSet.getInt("id"));
            disciplines.setName(resultSet.getString("name"));
            list.add(disciplines);
        }
        statement.close();
        connectionForDataBase.closeConnection();
        return list;
    }
}
