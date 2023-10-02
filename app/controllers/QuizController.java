package controllers;

import java.sql.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.QuestionFactory;
import models.QuizFactory;
import models.QuizFactory.*;
import models.TarotCard;
import models.UserFactory;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;
import javax.inject.Inject;

public class QuizController extends Controller {
    @Inject
    UserFactory uf;
    @Inject
    QuizFactory quizFactory;
    @Inject
    QuestionFactory questionFactory;

    /**
     * Renders the Quizlist
     *
     * @return quiz page
     * @throws SQLException if there was a problem with getting the required Data from the Database
     */

    public Result quiz() throws SQLException {
        if (!session().containsKey("userId")) {
            return redirect(routes.LoginController.loginPage());
        } else {
            session().put("questionId", "-1");
            List<Quiz> allQuizzes = quizFactory.fromDB();
            UserFactory.User currentUser = uf.fromDBWithID(Integer.parseInt(session().get("userId")));
            return ok(views.html.map.render(views.html.quiz.render(allQuizzes, currentUser)));
        }
    }

    //-------------------------------- Question ------------------------------------------------------------------

    /**
     * Renders a Question
     *
     * @param idQuiz - the id specifying the quiz the question that has to be rendered belongs to
     * @return question page
     * @throws SQLException if there was a problem with getting the required Data from the Database
     */
    public Result question(int idQuiz) throws SQLException {

        if (!session().containsKey("userId")) {
            return redirect(routes.LoginController.loginPage());
        } else {
            UserFactory.User currentUser = uf.fromDBWithID(Integer.parseInt(session().get("userId")));
            QuestionFactory.Question currentQuestion = getNextQuestion(idQuiz);
            return ok(views.html.map.render(views.html.question.render(currentQuestion, currentUser)));
        }
    }

    /**
     * Saves the selected Answer of a User in the Database
     *
     * @return result which includes the next question that has to be loaded
     * @throws SQLException if there was a problem with getting the required Data from the Database
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result questionPost() throws SQLException {
        if (!session().containsKey("userId")) {
            return redirect(routes.LoginController.loginPage());
        } else {
            JsonNode json = request().body().asJson();
            int quiz = json.get("quiz").asInt();
            int question = json.get("question").asInt();
            int selectedAnswer = json.get("answer").asInt();

            QuizFactory.Quiz currentQuiz = quizFactory.getQuizFromDBbyId(quiz);
            QuestionFactory.Question questionToUpdate = questionFactory.fromDBbyID(question);
            int user = uf.fromDBWithID(Integer.parseInt(session().get("userId"))).getId();
            questionToUpdate.selectAnswer(selectedAnswer, user);
            QuestionFactory.Question currentQuestion = getNextQuestion(quiz);
            ObjectNode res = Json.newObject();
            if (currentQuestion != null) {
                ArrayNode questions = Json.newArray();
                questions.add(currentQuestion.getContent());
                for (String a : currentQuestion.getPossibleAnswers()) {
                    questions.add(a);
                }
                questions.add(currentQuestion.getID());
                res.set("questions", questions);
                return ok(res);
            }
            res.put("finished", true);
            res.put("percentage", currentQuiz.evaluate(user));
            session().put("questionId", "-1");
            currentQuiz.updateProgressOfUser(user);
            return ok(res);

        }
    }

    /**
     * Updates the Database when a User uses a Card
     *
     * @return result including the modifications of the question on which the card was used
     * @throws SQLException if there was a problem with updating the Data to the Database
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result useCard() throws SQLException {
        if (!session().containsKey("userId")) {
            return redirect(routes.LoginController.loginPage());
        } else {
            JsonNode json = request().body().asJson();
            int question = json.get("question").asInt();
            String card = json.get("card").asText();
            TarotCard tarot = TarotCard.valueOf(card);
            QuestionFactory.Question questionToUpdate = questionFactory.fromDBbyID(question);
            UserFactory.User user = uf.fromDBWithID(Integer.parseInt(session().get("userId")));

            ObjectNode res = Json.newObject();
            List<Integer> wrongAnswers;

            if (user.removeCard(tarot)) {
                questionToUpdate.useCard(user.getId());
                wrongAnswers = tarot.applyEffect(questionToUpdate);
            } else {
                res.put("none", true);
                ArrayNode questions = Json.newArray();
                questions.add(questionToUpdate.getContent());
                for (String a : questionToUpdate.getPossibleAnswers()) {
                    questions.add(a);
                }
                questions.add(questionToUpdate.getID());
                res.set("questions", questions);
                return ok(res);
            }

            ArrayNode questions = Json.newArray();
            ArrayNode striked = Json.newArray();
            questions.add(questionToUpdate.getContent());
            for (String a : questionToUpdate.getPossibleAnswers()) {
                questions.add(a);
            }

            for (int i : wrongAnswers) {
                striked.add(i);
            }

            questions.add(questionToUpdate.getID());
            res.set("questions", questions);
            res.set("striked", striked);
            return ok(res);
        }
    }

    /**
     * Returns the next Question
     *
     * @param idQuiz - the id of the quiz the question belongs to
     * @return the next question if ther is any, null if it was the last question
     * @throws SQLException - if there was a problem with getting the required Data from the Database
     */
    private QuestionFactory.Question getNextQuestion(int idQuiz) throws SQLException {
        QuizFactory.Quiz currentQuiz = quizFactory.getQuizFromDBbyId(idQuiz);
        if (!session().containsKey("questionId")) {
            session().put("questionId", "-1");
        }
        int nextQuestion = Integer.parseInt(session().get("questionId")) + 1;
        if (nextQuestion < currentQuiz.getNumberOfQuestions()) {
            QuestionFactory.Question currentQuestion = currentQuiz.getQuestions().get(nextQuestion);
            session().put("questionId", String.valueOf(nextQuestion));
            return currentQuestion;
        } else {
            return null;
        }
    }
}
