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

public class QuizFactoryTest extends WithApplication {
    protected Application provideApplication() {
        return fakeApplication();
    }

    @Test
    public void getID() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        QuizFactory qf = app.injector().instanceOf(QuizFactory.class);
        QuizFactory.Quiz q = qf.getQuizFromDBbyId(1);
        assertTrue(q.getID() == 1);
    }

    @Test
    public void getName() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        QuizFactory qf = app.injector().instanceOf(QuizFactory.class);
        QuizFactory.Quiz q = qf.getQuizFromDBbyId(1);
        String name = q.getName();
        assertFalse(name.isEmpty());
    }

    @Test
    public void getCentury() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        QuizFactory qf = app.injector().instanceOf(QuizFactory.class);
        QuizFactory.Quiz q = qf.getQuizFromDBbyId(1);
        CenturyFactory.Century cent = q.getCentury();
        assertNotNull(cent);
    }

    @Test
    public void getQuestions() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        QuizFactory qf = app.injector().instanceOf(QuizFactory.class);
        QuizFactory.Quiz q = qf.getQuizFromDBbyId(1);
        List<QuestionFactory.Question> list = q.getQuestions();
        assertFalse(list.isEmpty());
    }

    @Test
    public void numberOfQuestions() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        QuizFactory qf = app.injector().instanceOf(QuizFactory.class);
        QuizFactory.Quiz q = qf.getQuizFromDBbyId(1);
        assertTrue(q.getNumberOfQuestions() > 0);
    }

    @Test
    public void updateProgress() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        QuizFactory qf = app.injector().instanceOf(QuizFactory.class);
        QuestionFactory queFac = app.injector().instanceOf(QuestionFactory.class);
        UserFactory uf = app.injector().instanceOf(UserFactory.class);

        QuizFactory.Quiz q = qf.getQuizFromDBbyId(1);
        UserFactory.User user = uf.create("admin", "admin", "admin");
        double progressFirst = user.getProgress();

        for (int i = 0; i < q.getQuestions().size(); i++) {
            q.getQuestions().get(i).selectAnswer(q.getQuestions().get(i).getCorrectAnswer(), user.getId());
        }
        q.updateProgressOfUser(user.getId());
        assertTrue(user.getProgress() > progressFirst);

        user.delete();
    }

    @Test
    public void evaluate() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        QuizFactory qf = app.injector().instanceOf(QuizFactory.class);
        QuestionFactory queFac = app.injector().instanceOf(QuestionFactory.class);
        UserFactory uf = app.injector().instanceOf(UserFactory.class);

        QuizFactory.Quiz q = qf.getQuizFromDBbyId(1);
        UserFactory.User user = uf.create("admin", "admin", "admin");

        for (int i = 0; i < q.getQuestions().size(); i++) {
            q.getQuestions().get(i).selectAnswer(q.getQuestions().get(i).getCorrectAnswer(), user.getId());
        }

        assertTrue(q.evaluate(user.getId()) > 0);
        assertTrue(q.evaluate(user.getId()) < 101);

        user.delete();
    }

    @Test
    public void getFromDB() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        QuizFactory qf = app.injector().instanceOf(QuizFactory.class);
        List<QuizFactory.Quiz> list = qf.fromDB();

        assertFalse(list.isEmpty());
    }

    @Test
    public void getFromDBByID() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        QuizFactory qf = app.injector().instanceOf(QuizFactory.class);
        QuizFactory.Quiz q = qf.getQuizFromDBbyId(1);

        assertNotNull(q);
    }

    @Test
    public void getNumberOfQuizzes() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        QuizFactory qf = app.injector().instanceOf(QuizFactory.class);
        assertTrue(qf.getNumberOfQuizzes() > 0);
    }

    @Test
    public void getNumberOfQuizzesPerCentury() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        QuizFactory qf = app.injector().instanceOf(QuizFactory.class);
        for (int cent = 1; cent < 4; cent++) {
            assertTrue(qf.getNumberOfQuizzesInCentury(cent) > 0);
        }
    }
}