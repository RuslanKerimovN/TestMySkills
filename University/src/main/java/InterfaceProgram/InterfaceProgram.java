package InterfaceProgram;

import Disciplines.Disciplines;
import ListScore.ListScore;
import Students.Students;
import Team.Team;
import java.sql.SQLException;

public class InterfaceProgram {
    public void getGroups() throws SQLException {
        Team group = new Team();
        group.printGroupList();
    }
    public void getDisciplines() throws SQLException {
        Disciplines disciplines = new Disciplines();
        disciplines.printDisciplineList();
    }
    public void getStudents() throws SQLException {
        Students studentsCase4 = new Students();
        studentsCase4.printStudentList();
    }
    public void getStudentsByGroup() throws SQLException {
        Students studentsCase4 = new Students();
        studentsCase4.printStudentsByGroup();
    }
    public void addStudent() throws SQLException {
        Students studentsCase5 = new Students();
        studentsCase5.addStudent();
    }
    public void addScoreStudent() throws SQLException {
        ListScore listScore = new ListScore();
        listScore.addScore();
    }
    public void getStatistic() throws SQLException {
        Students studentsCase7 = new Students();
        studentsCase7.getStatisticStudent();
    }
}
