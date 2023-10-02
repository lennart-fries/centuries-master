import models.QuestionFactory;
import models.TarotCard;
import org.junit.Test;
import play.Application;
import play.db.Database;
import play.test.WithApplication;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static play.test.Helpers.fakeApplication;

public class TarotCardTest extends WithApplication {

    protected Application provideApplication() {
        return fakeApplication();
    }

    @Test
    public void getNameFOOL() {
        assertEquals("The Fool", TarotCard.FOOL.getName());
    }

    @Test
    public void getNameWHEEL() {
        assertEquals("Wheel of Fortune", TarotCard.WHEEL_OF_FORTUNE.getName());
    }

    @Test
    public void getNameJudgement() {
        assertEquals("Judgement", TarotCard.JUDGEMENT.getName());
    }

    @Test
    public void getCostFOOL() {
        assertEquals(3, TarotCard.FOOL.getCost());
    }

    @Test
    public void getCostWHEEL() {
        assertEquals(5, TarotCard.WHEEL_OF_FORTUNE.getCost());
    }

    @Test
    public void getCostJudgement() {
        assertEquals(7, TarotCard.JUDGEMENT.getCost());
    }

    @Test
    public void applyEffectFOOLContainsWrongAnswerAndNotRightAnswer() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        QuestionFactory questionFactory = app.injector().instanceOf(QuestionFactory.class);
        QuestionFactory.Question qu = questionFactory.fromDBByQuiz(1).get(1);

        List<Integer> list = TarotCard.FOOL.applyEffect(qu);
        assertFalse(list.contains(qu.getCorrectAnswer()));
        assertFalse(list.isEmpty());
    }

    @Test
    public void applyEffectWHEELContainsWrongAnswerAndNotRightAnswer() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        QuestionFactory questionFactory = app.injector().instanceOf(QuestionFactory.class);
        QuestionFactory.Question qu = questionFactory.fromDBByQuiz(1).get(1);

        List<Integer> list = TarotCard.WHEEL_OF_FORTUNE.applyEffect(qu);
        assertFalse(list.contains(qu.getCorrectAnswer()));
        assertTrue(list.size()==2);
    }

    @Test
    public void applyEffectJUDGEMENTContainsWrongAnswerAndNotRightAnswer() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        QuestionFactory questionFactory = app.injector().instanceOf(QuestionFactory.class);
        QuestionFactory.Question qu = questionFactory.fromDBByQuiz(1).get(1);

        List<Integer> list = TarotCard.JUDGEMENT.applyEffect(qu);
        assertFalse(list.contains(qu.getCorrectAnswer()));
        assertTrue(list.size()==3);
    }

    @Test
    public void getWrongAnswersFool() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        List<Integer> list = TarotCard.getWrongAnswers(1,1);
        assertTrue(list.size() == 1);
    }

    @Test
    public void getWrongAnswersWheel() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        List<Integer> list = TarotCard.getWrongAnswers(2,1);
        assertTrue(list.size() == 2);
    }

    @Test
    public void getWrongAnswersJudgement() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        List<Integer> list = TarotCard.getWrongAnswers(3,1);
        assertTrue(list.size() == 3);
    }
}