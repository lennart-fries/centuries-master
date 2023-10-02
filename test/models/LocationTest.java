package models;

import org.junit.Test;
import play.Application;
import play.db.Database;
import play.test.WithApplication;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;
import static play.test.Helpers.fakeApplication;

public class LocationTest extends WithApplication {

    protected Application provideApplication() {
        return fakeApplication();
    }

    @Test
    public void getName() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        Location loc = new Location(1, db);
        assertNotNull(loc.getName());
    }

    @Test
    public void getBackgroundPath() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        Location loc = new Location(1, db);
        assertNotNull(loc.getBackgroundPath());
    }

    @Test
    public void getLocationData() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        Location loc = new Location(1, db);
        List<Information> list = loc.getLocationData();
        assertFalse(list.isEmpty());
    }


}