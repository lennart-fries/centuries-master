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

public class QuestionFactoryTest extends WithApplication {
    protected Application provideApplication() {
        return fakeApplication();
    }

    @Test
    public void getID() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        QuestionFactory qf = app.injector().instanceOf(QuestionFactory.class);
        QuestionFactory.Question qu = qf.fromDBbyID(30);
        assertTrue(qu.getID() == 30);
    }

    @Test
    public void getContent() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        QuestionFactory qf = app.injector().instanceOf(QuestionFactory.class);
        QuestionFactory.Question qu = qf.fromDBbyID(30);
        String s = qu.getContent();
        assertNotNull(s);
    }

    @Test
    public void setContent() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        QuestionFactory qf = app.injector().instanceOf(QuestionFactory.class);
        QuestionFactory.Question qu = qf.fromDBbyID(30);
        String s = qu.getContent();
        qu.setContent("asdf");
        assertNotEquals(s, qu.getContent());
    }

    @Test
    public void getCorrectAnswer() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        QuestionFactory qf = app.injector().instanceOf(QuestionFactory.class);
        QuestionFactory.Question qu = qf.fromDBbyID(30);
        int i = qu.getCorrectAnswer();
        assertTrue(0 < i);
        assertTrue(i < 5);
    }

    @Test
    public void getPossibleAnswers() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        QuestionFactory qf = app.injector().instanceOf(QuestionFactory.class);
        QuestionFactory.Question qu = qf.fromDBbyID(30);
        List<String> list = qu.getPossibleAnswers();
        assertFalse(list.isEmpty());
    }

    @Test
    public void getAnswerAtIndex() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        QuestionFactory qf = app.injector().instanceOf(QuestionFactory.class);
        QuestionFactory.Question qu = qf.fromDBbyID(30);
        int i = qu.getCorrectAnswer();
        assertTrue(qu.getAnswerAtIndex(i).equals(qu.getAnswerAtIndex(qu.getCorrectAnswer())));
    }

    @Test
    public void getQuizByQuestionId() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        QuestionFactory qf = app.injector().instanceOf(QuestionFactory.class);
        QuestionFactory.Question qu = qf.fromDBbyID(1);
        assertTrue(0 < qu.getQuizIdFromDbByQuestionId());
    }

    @Test
    public void getFromDBbyID() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        QuestionFactory qf = app.injector().instanceOf(QuestionFactory.class);
        QuestionFactory.Question qu = qf.fromDBbyID(1);
        assertNotNull(qu);
    }

    @Test
    public void getFromDBByQuiz() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        QuestionFactory qf = app.injector().instanceOf(QuestionFactory.class);
        List<QuestionFactory.Question> list = qf.fromDBByQuiz(1);
        assertFalse(list.isEmpty());
    }
}