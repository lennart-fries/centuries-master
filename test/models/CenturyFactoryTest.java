package models;

import org.junit.Test;
import play.Application;
import play.db.Database;
import play.test.WithApplication;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static play.test.Helpers.fakeApplication;

public class CenturyFactoryTest extends WithApplication {

    protected Application provideApplication() {
        return fakeApplication();
    }

    @Test
    public void getRightID() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        CenturyFactory cf = app.injector().instanceOf(CenturyFactory.class);
        CenturyFactory.Century century = cf.fromDBWithID(1);
        assertEquals(1, century.getId());
    }

    @Test
    public void getFromDBWithID() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        CenturyFactory cf = app.injector().instanceOf(CenturyFactory.class);
        CenturyFactory.Century century = cf.fromDBWithID(1);
        assertNotNull(century);
    }


}