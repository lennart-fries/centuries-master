import models.MemoryFactory;
import org.junit.Test;
import play.Application;
import play.db.Database;
import play.test.WithApplication;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static play.test.Helpers.fakeApplication;

public class MemoryTest extends WithApplication {
    protected Application provideApplication() {
        return fakeApplication();
    }

    @Test
    public void testToAndFromString() throws SQLException {
        MemoryFactory memoryFactory = app.injector().instanceOf(MemoryFactory.class);
        MemoryFactory.Memory memory = memoryFactory.getMemoryForLevel(1);
        String memoryString = memory.toString();
        MemoryFactory.Memory memoryConstructed = memoryFactory.fromString(memoryString);

        assertEquals(memory, memoryConstructed);
    }

    @Test
    public void testNoCardIsOpen() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        MemoryFactory mf = app.injector().instanceOf(MemoryFactory.class);
        MemoryFactory.Memory memo = mf.getMemoryForLevel(1);

        assertFalse(memo.openCard("A"));
    }


    @Test
    public void testFirstCardIsOpen() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        MemoryFactory mf = app.injector().instanceOf(MemoryFactory.class);
        MemoryFactory.Memory memo = mf.getMemoryForLevel(1);
        memo.setOpenOne("A");
        assertTrue(memo.openCard("A"));
    }

    @Test
    public void testSecondCardIsOpen() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        MemoryFactory mf = app.injector().instanceOf(MemoryFactory.class);
        MemoryFactory.Memory memo = mf.getMemoryForLevel(1);
        memo.setOpenTwo("A");
        assertTrue(memo.openCard("A"));
    }

    @Test (expected = IllegalStateException.class)
    public void testTwoCardsAreOpen() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        MemoryFactory mf = app.injector().instanceOf(MemoryFactory.class);
        MemoryFactory.Memory memo = mf.getMemoryForLevel(1);
        memo.setOpenOne("A");
        memo.setOpenTwo("A");
        assertTrue(memo.openCard("A"));
    }

    @Test (expected = IllegalStateException.class)
    public void testIllegalEvaluate() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        MemoryFactory mf = app.injector().instanceOf(MemoryFactory.class);
        MemoryFactory.Memory memo = mf.getMemoryForLevel(1);
        assertTrue(memo.evaluate());
    }

    @Test
    public void testEvaluateWithEqualCards() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        MemoryFactory mf = app.injector().instanceOf(MemoryFactory.class);
        MemoryFactory.Memory memo = mf.getMemoryForLevel(1);
        String value = memo.getCards().get(0);
        String key = memo.getCards().get(6);
        memo.setOpenOne(value);
        memo.setOpenTwo(key);
        assertTrue(memo.evaluate());
    }

    @Test
    public void testEvaluateWithDifferentCards() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        MemoryFactory mf = app.injector().instanceOf(MemoryFactory.class);
        MemoryFactory.Memory memo = mf.getMemoryForLevel(1);
        String key = memo.getCards().get(1);
        String secKey = memo.getCards().get(11);
        memo.setOpenOne(key);
        memo.setOpenTwo(secKey);
        assertFalse(memo.evaluate());
    }

    @Test
    public void testResetCards() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        MemoryFactory mf = app.injector().instanceOf(MemoryFactory.class);
        MemoryFactory.Memory memo = mf.getMemoryForLevel(1);
        String value = memo.getCards().get(0);
        String key = memo.getCards().get(6);
        memo.setOpenOne(value);
        memo.setOpenTwo(key);
        assertTrue(memo.evaluate());

        memo.resetOpenCards();

        assertFalse(memo.openCard("A"));
    }

    @Test
    public void removeCards() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        MemoryFactory mf = app.injector().instanceOf(MemoryFactory.class);
        MemoryFactory.Memory memo = mf.getMemoryForLevel(1);
        String value = memo.getCards().get(0);
        String key = memo.getCards().get(6);
        memo.setOpenOne(value);
        memo.setOpenTwo(key);
        int i = memo.getCards().size();
        memo.removeOpenCards();
        assertTrue(memo.getCards().size() == i - 2);
    }

    @Test
    public void getCards() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        MemoryFactory mf = app.injector().instanceOf(MemoryFactory.class);
        MemoryFactory.Memory memo = mf.getMemoryForLevel(1);
        List<String> list = memo.getCards();
        assertFalse(list.isEmpty());
    }

    @Test
    public void getOpenOne() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        MemoryFactory mf = app.injector().instanceOf(MemoryFactory.class);
        MemoryFactory.Memory memo = mf.getMemoryForLevel(1);
        memo.resetOpenCards();
        assertTrue(memo.getOpenOne() == "x");
    }

    @Test
    public void getOpenTwo() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        MemoryFactory mf = app.injector().instanceOf(MemoryFactory.class);
        MemoryFactory.Memory memo = mf.getMemoryForLevel(1);
        memo.resetOpenCards();
        assertTrue(memo.getOpenTwo() == "x");
    }

    @Test
    public void setOpenOne() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        MemoryFactory mf = app.injector().instanceOf(MemoryFactory.class);
        MemoryFactory.Memory memo = mf.getMemoryForLevel(1);
        memo.setOpenOne("y");
        assertTrue(memo.getOpenOne() == "y");
    }

    @Test
    public void setOpenTwo() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        MemoryFactory mf = app.injector().instanceOf(MemoryFactory.class);
        MemoryFactory.Memory memo = mf.getMemoryForLevel(1);
        memo.setOpenTwo("y");
        assertTrue(memo.getOpenTwo() == "y");
    }

    @Test
    public void equals() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        MemoryFactory mf = app.injector().instanceOf(MemoryFactory.class);
        MemoryFactory.Memory memo = mf.getMemoryForLevel(1);
        MemoryFactory.Memory test = mf.getMemoryForLevel(1);
        assertTrue(memo.equals(test));
    }

    @Test
    public void getMemoryForLevel() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        MemoryFactory mf = app.injector().instanceOf(MemoryFactory.class);
        MemoryFactory.Memory memo = mf.getMemoryForLevel(1);
        assertNotNull(memo);
    }
}
