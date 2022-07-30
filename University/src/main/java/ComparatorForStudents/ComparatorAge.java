package ComparatorForStudents;

import Students.Students;
import java.util.Comparator;

public class ComparatorAge implements Comparator<Students> {
    @Override
    public int compare(Students o1, Students o2) {

        if (o1.getAge() < o2.getAge())
            return -1;
        else if (o1.getAge() > o2.getAge())
            return 1;
        return 0;
    }
}

