package ComparatorForStudents;

import Students.Students;

import java.util.Comparator;

public class ComparatorSurname implements Comparator<Students> {
    @Override
    public int compare(Students o1, Students o2) {
        int result =  o1.getSurname().compareTo(o2.getSurname());
        if (result > 0)
            return 1;
        else if (result < 0)
            return -1;
        return 0;
    }
}
