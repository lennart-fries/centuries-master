package models;

import org.junit.Test;
import play.Application;
import play.db.Database;
import play.test.WithApplication;

import java.sql.Connection;

import static org.junit.Assert.*;
import static play.test.Helpers.fakeApplication;

public class MessageFormTest extends WithApplication {

    protected Application provideApplication() {
        return fakeApplication();
    }

    @Test
    public void setReceiver() {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        MessageForm mf = new MessageForm("a", "a");
        assertTrue(mf.getNameOfreceiver() == "a");
        mf.setReceiver("b");
        assertTrue(mf.getNameOfreceiver() == "b");
    }

    @Test
    public void setContent() {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        MessageForm mf = new MessageForm("a", "a");
        assertTrue(mf.getContent() == "a");
        mf.setContent("b");
        assertTrue(mf.getContent() == "b");
    }

    @Test
    public void getNameOfreceiver() {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        MessageForm mf = new MessageForm("a", "a");
        assertTrue(mf.getNameOfreceiver() == "a");
    }

    @Test
    public void getContent() {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        MessageForm mf = new MessageForm("a", "a");
        assertTrue(mf.getContent() == "a");
    }
}