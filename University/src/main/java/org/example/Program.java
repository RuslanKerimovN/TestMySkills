package org.example;

import InterfaceProgram.InterfaceProgram;
import org.postgresql.Driver;
import java.sql.*;
import java.util.*;


public class Program {
    static void printMenu() {
        System.out.println("1.Получение списка учебных групп");
        System.out.println("2.Получение списка дисциплин");
        System.out.println("3.Получение списка студентов");
        System.out.println("4.Получение списка студентов по id группы");
        System.out.println("5.Добавление студента");
        System.out.println("6.Добавление оценки студенту");
        System.out.println("7.Вывод статистики студента");
        System.out.println("8.Нажмите для выхода");
    }

    public static void main(String[] args) throws SQLException {
        Driver driver = new Driver();
        InterfaceProgram interfaceProgram = new InterfaceProgram();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            String result = scanner.nextLine();
            try {
                switch (Integer.parseInt(result)) {
                    case 1:
                        interfaceProgram.getGroups();
                        break;
                    case 2:
                        interfaceProgram.getDisciplines();
                        break;
                    case 3:
                        interfaceProgram.getStudents();
                        break;
                    case 4:
                        interfaceProgram.getStudentsByGroup();
                        break;
                    case 5:
                        interfaceProgram.addStudent();
                        break;
                    case 6:
                        interfaceProgram.addScoreStudent();
                        break;
                    case 7:
                        interfaceProgram.getStatistic();
                        break;
                    default:
                        System.exit(0);
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());;
            }
            System.out.println("----------------------------------------------------------------------");
        }
    }
}