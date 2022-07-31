package Students;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StudentsTest {

    @Test
    void testBestSeries1() {
        int[] mass = {2,2,5,4,4,3,3,2,2,2,5,5};
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < mass.length; i++)
            list.add(mass[i]);

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
        int expectationSize = 2, expectationValue = 5;

        assertEquals(expectationSize, max);
        assertEquals(expectationValue, bestScore);
    }

    @Test
    void testBestSeries2() {
        int[] mass = {2,2,5,4,4,5,5,5,2,2,5,5};
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < mass.length; i++)
            list.add(mass[i]);

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
        int expectationSize = 3, expectationValue = 5;

        assertEquals(expectationSize, max);
        assertEquals(expectationValue, bestScore);
    }

    @Test
    void testBestSeries3() {
        int[] mass = {2,2,5,5,5,5,4,4,3,3,2,2,2,5,5};
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < mass.length; i++)
            list.add(mass[i]);

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
        int expectationSize = 4, expectationValue = 5;

        assertEquals(expectationSize, max);
        assertEquals(expectationValue, bestScore);
    }

    @Test
    void testBestSeries4() {
        int[] mass = {5,2};
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < mass.length; i++)
            list.add(mass[i]);

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
        int expectationSize = 0, expectationValue = 0;

        assertEquals(expectationSize, max);
        assertEquals(expectationValue, bestScore);
    }

    @Test
    void testBestSeriesEmpty() {
        int[] mass = new int[0];
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < mass.length; i++)
            list.add(mass[i]);

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
        int expectationSize = 0, expectationValue = 0;

        assertEquals(expectationSize, max);
        assertEquals(expectationValue, bestScore);
    }

}