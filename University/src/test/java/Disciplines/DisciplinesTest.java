package Disciplines;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DisciplinesTest {

    @Test
    void getDisciplineList() throws SQLException {
        Disciplines disciplines = new Disciplines();
        ArrayList<Disciplines> arrayList = disciplines.getDisciplineList();

        String s = new String("ОПАД");
        String s1 = new String("Геодезия");
        String s2 = new String("БЖД");
        String s3 = new String("Биология");
        String s4 = new String("Высшая математика");
        String s5 = new String("Строительная механика");

        assertEquals(s, arrayList.get(0).getName());
        assertEquals(s1, arrayList.get(1).getName());
        assertEquals(s2, arrayList.get(2).getName());
        assertEquals(s3, arrayList.get(3).getName());
        assertEquals(s4, arrayList.get(4).getName());
        assertEquals(s5, arrayList.get(5).getName());

    }
}