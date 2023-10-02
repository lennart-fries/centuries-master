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

public class UserFactoryTest extends WithApplication {
    protected Application provideApplication() {
        return fakeApplication();
    }

    @Test
    public void getID() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        UserFactory uf = app.injector().instanceOf(UserFactory.class);
        UserFactory.User user = uf.fromDBWithID(1);
        assertTrue(user.getId() == 1);
    }

    @Test
    public void getUsername() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        UserFactory uf = app.injector().instanceOf(UserFactory.class);
        List<UserFactory.User> user = uf.fromDBWithUsername("freddi");
        for (int i = 0; i < user.size(); i++) {
            assertEquals("freddi", user.get(i).getUsername());
        }
    }

    @Test
    public void setUsername() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        UserFactory uf = app.injector().instanceOf(UserFactory.class);
        UserFactory.User user = uf.create("a", "a", "a");
        user.setUsername("b");
        assertNotEquals(user.getUsername(), "a");
        user.delete();
    }

    @Test
    public void getCoins() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        UserFactory uf = app.injector().instanceOf(UserFactory.class);
        UserFactory.User user = uf.create("a", "a", "a");
        assertTrue(user.getCoins() == 0);
        user.delete();
    }

    @Test
    public void setCoins() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        UserFactory uf = app.injector().instanceOf(UserFactory.class);
        UserFactory.User user = uf.create("a", "a", "a");
        user.setCoins(10);
        assertTrue(user.getCoins() == 10);
        user.delete();
    }

    @Test
    public void getPassword() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        UserFactory uf = app.injector().instanceOf(UserFactory.class);
        UserFactory.User user = uf.create("a", "a", "a");
        assertTrue(user.getPassword() == "a");
        user.delete();
    }

    @Test
    public void setPassword() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        UserFactory uf = app.injector().instanceOf(UserFactory.class);
        UserFactory.User user = uf.create("a", "a", "a");
        user.setPassword("b");
        assertTrue(user.getPassword() != "a");
        user.delete();
    }

    @Test
    public void getCards() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        UserFactory uf = app.injector().instanceOf(UserFactory.class);
        UserFactory.User user = uf.fromDBWithID(1);
        assertNotNull(user.getCards());
    }

    @Test
    public void addAndDeleteCard() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        UserFactory uf = app.injector().instanceOf(UserFactory.class);
        UserFactory.User user = uf.fromDBWithID(1);

        int numberFools = user.getCards().get(TarotCard.FOOL);

        user.addCard(TarotCard.FOOL);

        assertTrue(numberFools + 1 == user.getCards().get(TarotCard.FOOL));

        user.removeCard(TarotCard.FOOL);

        assertTrue(numberFools == user.getCards().get(TarotCard.FOOL));
    }

    @Test
    public void hasPlayedAMemory() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        UserFactory uf = app.injector().instanceOf(UserFactory.class);
        UserFactory.User user = uf.fromDBWithID(4);

        assertTrue(user.hasPlayedMemoryInLevel(1));
    }

    @Test
    public void updatePlayedMemory() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        UserFactory uf = app.injector().instanceOf(UserFactory.class);
        String s = "admin";
        UserFactory.User user = uf.create(s, s, s);
        assertFalse(user.hasPlayedMemoryInLevel(1));
        user.updateMemoryPlayed(1);
        assertTrue(user.hasPlayedMemoryInLevel(1));
        user.delete();
    }

    @Test
    public void hasNotPlayedAMemory() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        UserFactory uf = app.injector().instanceOf(UserFactory.class);
        UserFactory.User user = uf.fromDBWithID(1);

        assertFalse(user.hasPlayedMemoryInLevel(1));
    }

    @Test
    public void highestLevel() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        UserFactory uf = app.injector().instanceOf(UserFactory.class);
        UserFactory.User user = uf.fromDBWithID(1);
        UserFactory.User secUser = uf.fromDBWithID(4);

        assertTrue(secUser.getHighestLevel() > user.getHighestLevel());
    }

    @Test
    public void progress() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        UserFactory uf = app.injector().instanceOf(UserFactory.class);
        UserFactory.User user = uf.fromDBWithID(1);
        UserFactory.User secUser = uf.fromDBWithID(4);

        assertTrue(secUser.getProgress() > user.getProgress());
    }

    @Test
    public void progressByCentury() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        UserFactory uf = app.injector().instanceOf(UserFactory.class);
        UserFactory.User user = uf.fromDBWithID(1);
        UserFactory.User secUser = uf.fromDBWithID(4);

        assertTrue(secUser.getProgressByCentury(2) > user.getProgressByCentury(2));
    }

    @Test
    public void NoProgressByQuiz() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        UserFactory uf = app.injector().instanceOf(UserFactory.class);
        UserFactory.User user = uf.fromDBWithID(1);

        assertTrue(0 == user.getProgressByQuiz(1));
    }

    @Test
    public void friends() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        UserFactory uf = app.injector().instanceOf(UserFactory.class);
        UserFactory.User user = uf.fromDBWithID(1);

        List<UserFactory.User> list = user.getFriends();

        assertFalse(list.isEmpty());
    }

    @Test
    public void addFriend() throws SQLException {

        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        UserFactory uf = app.injector().instanceOf(UserFactory.class);
        String s = "admin";
        UserFactory.User user = uf.create(s, s, s);

        int friendsBefore = user.getFriends().size();

        user.addFriend(1);

        assertTrue(friendsBefore < user.getFriends().size());

        user.delete();
    }

    @Test
    public void createAndDeleteAUser() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        UserFactory uf = app.injector().instanceOf(UserFactory.class);

        int allUsers = uf.fromDB().size();

        UserFactory.User user = uf.create("a", "aa", "a");

        assertTrue(allUsers < uf.fromDB().size());

        user.delete();
    }

    @Test
    public void getSolved() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        UserFactory uf = app.injector().instanceOf(UserFactory.class);
        QuizFactory qf = app.injector().instanceOf(QuizFactory.class);
        QuizFactory.Quiz quiz = qf.getQuizFromDBbyId(1);
        String s = "admin";
        UserFactory.User user = uf.create(s, s, s);

        for (int i = 0; i < quiz.getNumberOfQuestions(); i++) {
            quiz.getQuestions().get(i).selectAnswer(quiz.getQuestions().get(i).getCorrectAnswer(), user.getId());
        }
        quiz.updateProgressOfUser(user.getId());

        assertTrue(user.getProgress() > 0);

        user.delete();
    }

    @Test
    public void getSolvedByCentury() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        UserFactory uf = app.injector().instanceOf(UserFactory.class);
        QuizFactory qf = app.injector().instanceOf(QuizFactory.class);
        QuizFactory.Quiz quiz = qf.getQuizFromDBbyId(1);
        String s = "admin";
        UserFactory.User user = uf.create(s, s, s);

        for (int i = 0; i < quiz.getNumberOfQuestions(); i++) {
            quiz.getQuestions().get(i).selectAnswer(quiz.getQuestions().get(i).getCorrectAnswer(), user.getId());
        }
        quiz.updateProgressOfUser(user.getId());

        assertTrue(user.getProgressByCentury(1) > 0);

        user.delete();
    }

    @Test
    public void getSolvedByQuiz() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        UserFactory uf = app.injector().instanceOf(UserFactory.class);
        QuizFactory qf = app.injector().instanceOf(QuizFactory.class);
        QuizFactory.Quiz quiz = qf.getQuizFromDBbyId(1);
        String s = "admin";
        UserFactory.User user = uf.create(s, s, s);

        for (int i = 0; i < quiz.getNumberOfQuestions(); i++) {
            quiz.getQuestions().get(i).selectAnswer(quiz.getQuestions().get(i).getCorrectAnswer(), user.getId());
        }
        quiz.updateProgressOfUser(user.getId());

        assertTrue(user.getProgressByQuiz(1) > 0);

        user.delete();
    }

    @Test
    public void getAllTheUsers() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        UserFactory uf = app.injector().instanceOf(UserFactory.class);

        assertTrue(uf.fromDB().size() > 0);
    }

    @Test
    public void getUserByName() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        UserFactory uf = app.injector().instanceOf(UserFactory.class);
        List<UserFactory.User> list = uf.fromDBWithUsername("freddi");
        assertTrue(list.size() > 0);
    }
}