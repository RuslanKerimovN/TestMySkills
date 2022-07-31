package Team;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {

    @Test
    void getGroupList() throws SQLException {
        Team group = new Team();
        ArrayList<Team> arrayList = group.getGroupList();

        assertEquals(1, arrayList.get(0).getId());
        String str = new String("A-02");
        assertEquals(str, arrayList.get(0).getName());

        assertEquals(2, arrayList.get(1).getId());
        String str1 = new String("P-03");
        assertEquals(str1, arrayList.get(1).getName());

        assertEquals(3, arrayList.get(2).getId());
        String str2 = new String("F-01");
        assertEquals(str2, arrayList.get(2).getName());

        assertEquals(4, arrayList.get(3).getId());
        String str3 = new String("O-04");
        assertEquals(str3, arrayList.get(3).getName());
    }
}