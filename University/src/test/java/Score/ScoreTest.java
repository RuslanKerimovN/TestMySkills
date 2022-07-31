package Score;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    @Test
    void getScoreList() throws SQLException {
        Score score = new Score();
        ArrayList<Score> arrayList = score.getScoreList();

        assertEquals(2, arrayList.get(0).getScore());
        String str = new String("неудовлетворительно");
        assertEquals(str, arrayList.get(0).getText());

        assertEquals(3, arrayList.get(1).getScore());
        String str1 = new String("удовлетворительно");
        assertEquals(str1, arrayList.get(1).getText());

        assertEquals(4, arrayList.get(2).getScore());
        String str2 = new String("хорошо");
        assertEquals(str2, arrayList.get(2).getText());

        assertEquals(5, arrayList.get(3).getScore());
        String str3 = new String("отлично");
        assertEquals(str3, arrayList.get(3).getText());

    }
}