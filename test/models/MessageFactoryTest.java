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

public class MessageFactoryTest extends WithApplication {
    protected Application provideApplication() {
        return fakeApplication();
    }

    @Test
    public void testCreatingMessage() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        MessageFactory mf = app.injector().instanceOf(MessageFactory.class);
        int numberOfInboxMessages = mf.getInboxOfUser(1).size();

        mf.create("Test", 2, 1);

        int numberOfNewInboxMessages = mf.getInboxOfUser(1).size();

        assertEquals(numberOfInboxMessages + 1, numberOfNewInboxMessages);

    }

    @Test
    public void getSender() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        MessageFactory mf = app.injector().instanceOf(MessageFactory.class);
        MessageFactory.Message m = mf.fromDBWithID(1);
        UserFactory.User user = m.getSender();
        assertNotNull(user);
    }

    @Test
    public void getReceiver() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        MessageFactory mf = app.injector().instanceOf(MessageFactory.class);
        MessageFactory.Message m = mf.fromDBWithID(1);
        UserFactory.User user = m.getReceiver();
        assertNotNull(user);
    }

    @Test
    public void getContent() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        UserFactory uf = app.injector().instanceOf(UserFactory.class);
        MessageFactory mf = app.injector().instanceOf(MessageFactory.class);
        MessageFactory.Message m = mf.fromDBWithID(1);
        String content = m.getContent();
        assertNotNull(content);
    }

    @Test
    public void getID() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        UserFactory uf = app.injector().instanceOf(UserFactory.class);
        MessageFactory mf = app.injector().instanceOf(MessageFactory.class);
        MessageFactory.Message m = mf.fromDBWithID(1);
        int i = m.getId();
        assertTrue(i == 1);
    }

    @Test
    public void getInboxOFUser() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        MessageFactory mf = app.injector().instanceOf(MessageFactory.class);
        List<MessageFactory.Message> list = mf.getInboxOfUser(1);
        assertFalse(list.isEmpty());
    }

    @Test
    public void getOutboxOfUser() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        MessageFactory mf = app.injector().instanceOf(MessageFactory.class);
        List<MessageFactory.Message> list = mf.getOutboxOfUser(4);
        assertFalse(list.isEmpty());
    }

    @Test
    public void getFromDbWithID() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        MessageFactory mf = app.injector().instanceOf(MessageFactory.class);
        MessageFactory.Message message = mf.fromDBWithID(1);
        assertNotNull(message);
    }
}