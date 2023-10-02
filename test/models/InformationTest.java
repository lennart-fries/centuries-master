package models;

import org.junit.Test;
import play.Application;
import play.db.Database;
import play.test.WithApplication;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static play.test.Helpers.fakeApplication;

public class InformationTest extends WithApplication {
    protected Application provideApplication() {
        return fakeApplication();
    }

    @Test
    public void getContentTest() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        Location loc = new Location(1, db);
        Information info = loc.getLocationData().get(0);
        assertEquals(loc.getLocationData().get(0).getContent(), info.getContent());
    }

    @Test
    public void isCoinTest() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        Location loc = new Location(1, db);
        Information info = loc.getLocationData().get(0);
        assertTrue(info.isCoin());
    }

    @Test
    public void isNotCoinTest() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        Location loc = new Location(2, db);
        Information info = loc.getLocationData().get(0);
        assertFalse(info.isCoin());
    }

    @Test
    public void collectCoin() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        Location loc = new Location(9, db);
        Information info = loc.getLocationData().get(0);
        UserFactory uf = app.injector().instanceOf(UserFactory.class);
        String s = "admin";
        UserFactory.User user = uf.create(s, s, s);
        assertTrue(info.collectCoin(user));
        user.delete();
    }

}