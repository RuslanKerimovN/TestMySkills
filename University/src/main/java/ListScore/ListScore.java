package ListScore;

import ConnectionForDataBase.ConnectionForDataBase;
import Disciplines.Disciplines;
import Score.Score;
import Students.Students;
import Team.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ListScore {
    private int id;
    private int score;
    private String discipline;
    private String date;
    private String text;

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

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void addScore() throws SQLException {
        Team group = new Team();
        Score score = new Score();
        Disciplines disciplines = new Disciplines();
        Students student = new Students();
        Scanner scanner = new Scanner(System.in);
        boolean checker = false;
        ConnectionForDataBase connectionForDataBase = new ConnectionForDataBase();
        PreparedStatement pS = connectionForDataBase.getConnection().prepareStatement
                ("insert into university.list_score(score,discipline,student,description,ls_data) values (?,?,?,?,?)");


        student.printStudentsByGroup();
        System.out.println("Выберите id студента");
        String studentId = scanner.nextLine();
        int idStudent = Integer.parseInt(studentId);
        ArrayList<Students> slist = student.getStudentsList();
        for (Students iter: slist) {
            if (iter.getId() == idStudent)
                checker = true;
        }
        if (!checker) {
            System.out.println("Некорректные данные");
            return;
        }

        checker = false;
        disciplines.printDisciplineList();
        System.out.println("Выберите id дисциплины");
        String disciplineId = scanner.nextLine();
        int idDiscipline = Integer.parseInt(disciplineId);
        ArrayList<Disciplines> dislist = disciplines.getDisciplineList();
        for (Disciplines iter: dislist) {
            if (iter.getId() == idDiscipline)
                checker = true;
        }
        if (!checker) {
            System.out.println("Некорректные данные");
            return;
        }

        checker = false;
        score.printScoreList();
        System.out.println("Выберите id оценки");
        String scoreId = scanner.nextLine();
        int idScore = Integer.parseInt(scoreId);
        ArrayList<Score> sclist = score.getScoreList();
        for (Score iter: sclist) {
            if (iter.getId() == idScore)
                checker = true;
        }
        if (!checker) {
            System.out.println("Некорректные данные");
            return;
        }

        System.out.println("Введите пояснение к оценке (Контрольная, курсовая...)");
        String text = scanner.nextLine();

        System.out.println("Введите дату выставления оценки в формате ГГГГ-MM-ДД (c '-')");
        String date = scanner.nextLine();
        if (date.length() != 10 || (date.charAt(4) != '-' || date.charAt(7) != '-')) {
            System.out.println("Некорректные данные");
            return;
        }

        String[] splitting = date.split("-");
        if ((Integer.parseInt(splitting[2]) > 31 || Integer.parseInt(splitting[2]) < 1) ||
                (Integer.parseInt(splitting[1]) < 1 || Integer.parseInt(splitting[1]) > 12) ||
                (Integer.parseInt(splitting[1]) == 2 && Integer.parseInt(splitting[2]) > 29)) {
            System.out.println("Некорректные данные");
            return;
        }

        pS.setInt(1, idScore);
        pS.setInt(2, idDiscipline);
        pS.setInt(3, idStudent);
        pS.setString(4, text);
        pS.setDate(5, java.sql.Date.valueOf(date));
        pS.execute();

        System.out.println("Оценка добавлена");

        pS.close();
        connectionForDataBase.closeConnection();
    }

    public HashMap<String,ArrayList<Integer>> getScoreListStudent() throws SQLException {
        Students student = new Students();
        String monthAugust = "-08";
        String dayAugust = "-31";
        String monthJuly = "-07";
        String dayJuly = "-01";
        Scanner scanner = new Scanner(System.in);
        HashMap<String, ArrayList<Integer>> hashMap = new HashMap<>();

        student.printStudentsByGroup();
        System.out.println("Введите id студента");
        String studentId = scanner.nextLine();
        int idStudent = Integer.parseInt(studentId);

        System.out.println("Введите год");
        String year = scanner.nextLine();
        int yearInt = Integer.parseInt(year), yearPlusOne = yearInt + 1;

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
        ps.close();
        connectionForDataBase.closeConnection();
        return hashMap;
    }
}
