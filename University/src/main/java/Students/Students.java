package Students;

import ComparatorForStudents.ComparatorAge;
import ComparatorForStudents.ComparatorName;
import ComparatorForStudents.ComparatorSurname;
import ConnectionForDataBase.ConnectionForDataBase;
import ListScore.ListScore;
import Team.Team;

import java.sql.*;
import java.util.*;

public class Students {
    private int id;
    private String name;
    private String surname;
    private int age;
    private String group;
    public Students() {}
    public int getId() { return id; }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getGroup() {
        return group;
    }
    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Students{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", group=" + group +
                '}';
    }
    private void printComponents(int id, String s1, String s2, int i, String s3) {
//        System.out.println("Студент: id ->"  + id + " " + ", " + s1 + ", " + s2 + ", " + i + " лет " + ",группа - " + s3);
        System.out.printf("| %-15d | %-20s | %-20s | %-15d | %-20s |\n", id, s1, s2, i, s3);
    }

    public void printStudentList() throws SQLException {
        ArrayList<Students> studentsArrayList = getStudentsList();
        System.out.println("Выберите вид запроса: \n->1.Сортировка студентов по имени" +
                "\n->2.Сортировка студентов по фамилии\n->3.Сортировка студентов по возрасту" +
                "\n->4.Поиск студентов по имени или фамилии\n->5.Поиск студентов по возрасту");
        Scanner scanner = new Scanner(System.in);
        String result = scanner.nextLine();
        boolean countStudent = false;

        if (result.isEmpty()) {
            System.out.printf("%s| %-15s | %-20s | %-20s | %-15s | %-20s |%s\n","\u001B[33m", "id студента", "Фамилия", "Имя", "Возраст", "Группа","\u001B[0m");
            for (Students stud : studentsArrayList)
                printComponents(stud.getId(),stud.getSurname(), stud.getName(), stud.getAge(), stud.getGroup());
            return;
        }


        switch (Integer.parseInt(result)) {
            case 1:
                studentsArrayList.sort(new ComparatorName());
                System.out.printf("%s| %-15s | %-20s | %-20s | %-15s | %-20s |%s\n",
                        "\u001B[33m", "id студента", "Фамилия", "Имя", "Возраст", "Группа","\u001B[0m");
                for (Students stud : studentsArrayList)
                    printComponents(stud.getId(), stud.getSurname(), stud.getName(), stud.getAge(), stud.getGroup());
                break;
            case 2:
                studentsArrayList.sort(new ComparatorSurname());
                System.out.printf("%s| %-15s | %-20s | %-20s | %-15s | %-20s |%s\n",
                        "\u001B[33m", "id студента", "Фамилия", "Имя", "Возраст", "Группа","\u001B[0m");
                for (Students stud : studentsArrayList)
                    printComponents(stud.getId(), stud.getSurname(), stud.getName(), stud.getAge(), stud.getGroup());
                break;
            case 3:
                studentsArrayList.sort(new ComparatorAge());
                System.out.printf("%s| %-15s | %-20s | %-20s | %-15s | %-20s |%s\n",
                        "\u001B[33m", "id студента", "Фамилия", "Имя", "Возраст", "Группа","\u001B[0m");
                for (Students stud : studentsArrayList)
                    printComponents(stud.getId(), stud.getSurname(), stud.getName(), stud.getAge(), stud.getGroup());
                break;
            case 4:
                System.out.println("Введите имя или фамилию студентов");
                String student = scanner.nextLine();
                System.out.printf("%s| %-15s | %-20s | %-20s | %-15s | %-20s |%s\n",
                        "\u001B[33m", "id студента", "Фамилия", "Имя", "Возраст", "Группа","\u001B[0m");
                for (Students stud : studentsArrayList) {
                    if (stud.getName().toUpperCase().equals(student.toUpperCase()) ||
                            stud.getSurname().toUpperCase().equals(student.toUpperCase())) {
                        printComponents(stud.getId(), stud.getSurname(), stud.getName(), stud.getAge(), stud.getGroup());
                        countStudent = true;
                    }
                }
                if (!countStudent)
                    System.out.println("Студент не найден");
                break;
            case 5:
                System.out.println("Введите возраст студентов");
                String ageLine = scanner.nextLine();
                int age = Integer.parseInt(ageLine);
                System.out.printf("%s| %-15s | %-20s | %-20s | %-15s | %-20s |%s\n",
                        "\u001B[33m", "id студента", "Фамилия", "Имя", "Возраст", "Группа","\u001B[0m");
                for (Students stud : studentsArrayList) {
                    if (stud.getAge() == age) {
                        printComponents(stud.getId(), stud.getSurname(), stud.getName(), stud.getAge(), stud.getGroup());
                        countStudent = true;
                    }
                }
                if (!countStudent)
                    System.out.println("Студент не найден");
                break;
            default:
                System.out.println("Некорректные данные");
                break;
        }
    }

    public ArrayList<Students> getStudentsList() throws SQLException {
        Team group = new Team();
        ArrayList<Team> groupList = group.getGroupList();
        ArrayList<Students> list = new ArrayList<>();
        ConnectionForDataBase connectionForDataBase = new ConnectionForDataBase();
        Statement statement = connectionForDataBase.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("select * from university.student");

        while (resultSet.next()) {
            Students student = new Students();
            student.setId(resultSet.getInt("id"));
            student.setName(resultSet.getString("name"));
            student.setSurname(resultSet.getString("surname"));
            student.setAge(resultSet.getInt("age"));

            int tmpForCheckGroup = resultSet.getInt("group_id");

            for (int i = 0; i < groupList.size(); i++) {
                if (groupList.get(i).getId() == tmpForCheckGroup)
                    student.setGroup(groupList.get(i).getName());
            }
            list.add(student);
        }
        statement.close();
        connectionForDataBase.closeConnection();
        return list;
    }
    public void printStudentsByGroup() throws SQLException {
        Team group = new Team();
        Students students = new Students();
        Scanner scanner = new Scanner(System.in);
        int countStudent = 0;

        ArrayList<Team> groupList = group.getGroupList();
        ArrayList<Students> studentsList = students.getStudentsList();

        System.out.println("-------------------------------");
        group.printGroupList();
        System.out.println("-------------------------------");
        System.out.println("Выберите группу для вывода студентов");

        String numGroup = scanner.nextLine();
        int groupNum = Integer.parseInt(numGroup);
        String groupName = new String();

        for (int i = 0; i < groupList.size(); i++) {
            if (groupList.get(i).getId() == groupNum) {
                groupName = groupList.get(i).getName();
                break;
            }
        }
        if (groupName.isEmpty()) {
            System.out.println("Некорректные данные");
            return;
        }
        System.out.printf("%s| %-15s | %-20s | %-20s | %-15s | %-20s |%s\n",
                "\u001B[33m", "id студента", "Фамилия", "Имя", "Возраст", "Группа","\u001B[0m");
        for (int i = 0; i < studentsList.size(); i++) {
            if (studentsList.get(i).getGroup().equals(groupName)) {
                printComponents(studentsList.get(i).getId(),studentsList.get(i).getSurname(), studentsList.get(i).getName(),
                        studentsList.get(i).getAge(),studentsList.get(i).getGroup());
                countStudent++;
            }
        }
        if (countStudent == 0)
            System.out.println("Студенты не найдены");
    }

    public void addStudent() throws SQLException {
        Team group = new Team();
        Scanner scanner = new Scanner(System.in);
        ConnectionForDataBase connectionForDataBase = new ConnectionForDataBase();
        PreparedStatement pS = connectionForDataBase.getConnection().prepareStatement
                ("insert into university.student(name, surname, age, group_id) values (?,?,?,?)");

        System.out.println("Введите имя студента ");
        String Name = scanner.nextLine();

        System.out.println("Введите фамилию студента ");
        String Surname = scanner.nextLine();

        System.out.println("Введите возраст студента ");
        String ageLine = scanner.nextLine();
        int Age = Integer.parseInt(ageLine);

        group.printGroupList();
        System.out.println("Введите id группы студента ");
        String idLine = scanner.nextLine();
        int Id = Integer.parseInt(idLine);

        boolean checker = false;
        ArrayList<Team> groupList = group.getGroupList();
        for (int i = 0; i < groupList.size(); i++) {
            if (groupList.get(i).getId() == Id) {
                checker = true;
                break;
            }
        }
        if (!checker) {
            System.out.println("Некорректные данные");
            return;
        }

        pS.setString(1,Name);
        pS.setString(2,Surname);
        pS.setInt(3,Age);
        pS.setInt(4,Id);
        pS.execute();
        System.out.println("Студент добавлен");

        pS.close();
        connectionForDataBase.closeConnection();
    }

    private void minimumAndMaximumAndMiddle(ArrayList<Integer> list) {
        int min = 5, max = 0, result = 0;


        for (int i = 0; i < list.size(); i++) {
            if (max < list.get(i))
                max = list.get(i);
            if (min > list.get(i))
                min = list.get(i);
            result += list.get(i);
        }
        result /= list.size();
        System.out.println("Средняя арифметическая оценка - " + result + "\nНаименьшая оценка - " +
                min + ", наивысшая - " + max + "\n");
    }
    private void bestSeries(ArrayList<Integer> list) {
        int count = 1, max = 0, bestScore = 0;

        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) == list.get(i + 1) && bestScore <= list.get(i)) {
                bestScore = list.get(i);
                count++;
            } else if (list.get(i) != list.get(i + 1)) {
                count = 1;
            }
            if (max < count && count != 1)
                max = count;
        }
        System.out.println(list + "- лучшая серия " + bestScore + " - " + max + " раз");
    }

    private void countScores(ArrayList<Integer> list) {
        int two = 0, three = 0, four = 0, five = 0;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == 2)
                two++;
            else if (list.get(i) == 3)
                three++;
            else if (list.get(i) == 4)
                four++;
            else if (list.get(i) == 5)
                five++;
        }
        System.out.println("Оценка 5 - " + five + " шт, " + "оценка 4 - " + four + " шт, " + "оценка 3 - "
                + three + " шт, " + "оценка 2 - " + two + " шт");
    }

    public void getStatisticStudent() throws SQLException {
        ListScore listScore = new ListScore();
        HashMap<String, ArrayList<Integer>> hashMap = listScore.getScoreListStudent();
        if (hashMap == null)
            return;
        
        for (Map.Entry<String, ArrayList<Integer>> entry: hashMap.entrySet()) {
            System.out.println("\n---- " + entry.getKey() + " ----\n");
            bestSeries(entry.getValue());
            countScores(entry.getValue());
            minimumAndMaximumAndMiddle(entry.getValue());
            System.out.println("--------------------------------------------------------------------");
        }
    }

}
