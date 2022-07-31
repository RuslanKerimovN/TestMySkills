package ListScore;

import ConnectionForDataBase.ConnectionForDataBase;
import Disciplines.Disciplines;
import Score.Score;
import Students.Students;
import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ListScoreTest {

    @Test
    void getScoreListStudent() throws SQLException {
        Students student = new Students();
        String monthAugust = "-08";
        String dayAugust = "-31";
        String monthJuly = "-07";
        String dayJuly = "-01";
        Scanner scanner = new Scanner(System.in);
        HashMap<String, ArrayList<Integer>> hashMap = new HashMap<>();

        int idStudent = 1;

        String year = "2013";

        int yearInt = 2013, yearPlusOne = yearInt + 1;

        year = year + monthAugust + dayAugust;
        String nextYear = yearPlusOne + monthJuly + dayJuly;

        ConnectionForDataBase connectionForDataBase = new ConnectionForDataBase();
        PreparedStatement ps = connectionForDataBase.getConnection().prepareStatement
                ("select * from university.list_score where student = ? and ls_data > ?  and ls_data < ?");

        ps.setInt(1, idStudent);
        ps.setDate(2, java.sql.Date.valueOf(year));
        ps.setDate(3, java.sql.Date.valueOf(nextYear));
        ResultSet rs = ps.executeQuery();

        Disciplines disciplines = new Disciplines();
        Score score = new Score();
        ArrayList<Disciplines> disciplinesLine = disciplines.getDisciplineList();
        ArrayList<Score> scoreList = score.getScoreList();
        Integer disciplineId = 0, scoreId = 0, scoreValue = 0;
        String discipl = new String();

        while (rs.next()) {
            scoreId = rs.getInt("score");
            disciplineId = rs.getInt("discipline");
            for (int i = 0; i < disciplinesLine.size(); i++) {
                if (disciplinesLine.get(i).getId() == disciplineId) {
                    discipl = disciplinesLine.get(i).getName();
                    break;
                }
            }
            for (int i = 0; i < scoreList.size(); i++) {
                if (scoreList.get(i).getId() == scoreId) {
                    scoreValue = scoreList.get(i).getScore();
                    break;
                }
            }
            if (!hashMap.containsKey(discipl)) {
                hashMap.put(discipl, new ArrayList<Integer>());
                hashMap.get(discipl).add(scoreValue);
            } else {
                hashMap.get(discipl).add(scoreValue);
            }
        }

        boolean check = true;
        if (!hashMap.containsKey("Геодезия") || hashMap.get("Геодезия").get(0) != 5)
            check = false;
        if (hashMap.get("Геодезия").get(1) != 5)
            check = false;
        if (!hashMap.containsKey("ОПАД") || hashMap.get("ОПАД").get(0) != 4)
            check = false;
        if (!hashMap.containsKey("Биология") || hashMap.get("Биология").get(0) != 5)
            check = false;
        assertEquals(true, check);

        ps.close();
        connectionForDataBase.closeConnection();
    }
}