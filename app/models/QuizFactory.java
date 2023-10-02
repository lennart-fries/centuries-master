package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.*;

import play.db.Database;

@Singleton
public class QuizFactory {

    @Inject
    Database db;
    @Inject
    QuestionFactory qf;
    @Inject
    CenturyFactory cf;

    public class Quiz {

        private List<QuestionFactory.Question> questions;
        private int id;
        private String name;
        private CenturyFactory.Century century;

        private Quiz(int id, String name, CenturyFactory.Century century, List<QuestionFactory.Question> questions) {
            this.id = id;
            this.name = name;
            this.century = century;
            this.questions = questions;
        }

        public int getID() {
            return id;
        }

        public String getName() {
            return name;
        }

        public CenturyFactory.Century getCentury() {
            return century;
        }

        public List<QuestionFactory.Question> getQuestions() {
            return questions;
        }

        public int getNumberOfQuestions() {
            if (questions == null) {
                return 0;
            } else {
                return questions.size();
            }
        }

        /**
         * Updates the Progress of a User
         *
         * @param user the id of the user that took the quiz
         * @throws SQLException if there was a problem with updating the Data to the Database
         */
        public void updateProgressOfUser(int user) throws SQLException {
            double value = evaluate(user);
            if (hasProgress(user)) {
                if (value > getCurrentProgressOfUser(user)) {
                    Connection con = db.getConnection();
                    PreparedStatement statement = con.prepareStatement(
                            "UPDATE User_has_Quiz SET `solved`=? WHERE idUser =" + user + " AND idQuiz =" + id + ";"
                    );
                    statement.setDouble(1, value);
                    statement.executeUpdate();
                    statement.close();
                    con.close();
                }
            } else {
                Connection con = db.getConnection();
                PreparedStatement statement = con.prepareStatement(
                        "INSERT INTO User_has_Quiz(`idUser`,`idQuiz`,`solved`) VALUES (?,?,?)"
                );
                statement.setInt(1, user);
                statement.setInt(2, id);
                statement.setDouble(3, value);
                statement.executeUpdate();
                statement.close();
                con.close();
            }
        }

        /**
         * Evaluates the Result of the Quiz taken by the User
         *
         * @param user the id of the user that took the quiz
         * @return a percentage that represents the completion for the user in this quiz
         * @throws SQLException if there was a problem with getting the required Data from the Database
         */
        public double evaluate(int user) throws SQLException {
            List<Integer> selected = getSelectedAnswersForQuiz(user);
            List<Integer> correct = getCorrectAnswersForQuiz();
            int amountOfQuestions = questions.size();
            int correctAnswers = 0;
            for (int i = 0; i < correct.size(); i++) {
                if (selected.get(i).equals(correct.get(i))) {
                    correctAnswers++;
                }
            }

            double res = ((double) correctAnswers / (double) amountOfQuestions) * 100;
            return res;
        }

        private List<Integer> getCorrectAnswersForQuiz() {
            List<Integer> correctAnswers = new LinkedList<>();
            for (QuestionFactory.Question q : questions) {
                correctAnswers.add(q.getCorrectAnswer());
            }
            return correctAnswers;
        }

        /**
         * Gets the selected Answers for the Quiz from a User
         *
         * @param user the id of the user who took the quiz
         * @return a list including the indices for the selected Answers by the user
         * @throws SQLException if there was a problem with getting the required Data from the Database
         */
        private List<Integer> getSelectedAnswersForQuiz(int user) throws SQLException {
            Connection con = db.getConnection();
            String query = "SELECT selectedAnswer FROM User_has_MultipleChoiceQuestion, MultipleChoiceQuestion WHERE User_has_MultipleChoiceQuestion.idUser=" + user + " AND MultipleChoiceQuestion.idQuiz =" + id + " AND MultipleChoiceQuestion.idQuestion = User_has_MultipleChoiceQuestion.idQuestion;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            List<Integer> selectedAnswers = new LinkedList<>();

            while (rs.next()) {
                selectedAnswers.add(rs.getInt("selectedAnswer"));
            }

            rs.close();
            statement.close();
            con.close();

            return selectedAnswers;
        }

        /**
         * Gets the Progress of a User in the Quiz from the Database
         *
         * @param user the id of the user
         * @return a percentage value that represents the progress
         * @throws SQLException if there was a problem with getting the required Data from the Database
         */
        private double getCurrentProgressOfUser(int user) throws SQLException {
            Connection con = db.getConnection();
            String query = "SELECT solved FROM User_has_Quiz WHERE idUser =" + user + " AND idQuiz=" + id + ";";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            double progress = 0.00;
            while (rs.next()) {
                progress = rs.getDouble("solved");
            }
            rs.close();
            statement.close();
            con.close();

            return Math.round(progress * 100) / 100D;
        }

        /**
         * Checks if the User has already made Progress in the Quiz
         *
         * @param user the id of the user
         * @return true if the User has already progress saved on the database
         * @throws SQLException if there was a problem with getting the required Data from the Database
         */
        private boolean hasProgress(int user) throws SQLException {
            Connection con = db.getConnection();
            String query = "SELECT solved FROM User_has_Quiz WHERE idUser =" + user + " AND idQuiz=" + id + ";";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            double progress = -1;
            while (rs.next()) {
                progress = rs.getDouble("solved");
            }
            rs.close();
            statement.close();
            con.close();
            if (progress == -1) {
                return false;
            } else {
                return true;
            }
        }
    }

    /**
     * Gets all Quizzes from the Database
     *
     * @return a list containing all Quizzes
     * @throws SQLException if there was a problem with getting the required Data from the Database
     */
    public List<Quiz> fromDB() throws SQLException {
        return fromDBWhere(null);
    }


    /**
     * Gets a Quiz from the Database
     *
     * @param idQuiz the id of the Quiz that is required
     * @return the requested Quiz
     * @throws SQLException if there was a problem with getting the required Data from the Database
     */
    public Quiz getQuizFromDBbyId(int idQuiz) throws SQLException {
        return fromDBWhere("idQuiz = " + idQuiz).get(0);
    }

    /**
     * Gets the number of Quizzes in the Database
     *
     * @return int representing the grand total of quizzes
     * @throws SQLException if there was a problem with getting the required Data from the Database
     */
    public int getNumberOfQuizzes() throws SQLException {
        Connection con = db.getConnection();
        String query = "SELECT idQuiz from Quiz";
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(query);
        int res = 0;

        while (rs.next()) {
            res++;
        }

        rs.close();
        statement.close();
        con.close();

        return res;
    }

    /**
     * Gets the number of Quizzes for a specific Century
     *
     * @param idCentury the id of the Century
     * @return int representing the number of quizzes in the specified century
     * @throws SQLException if there was a problem with getting the required Data from the Database
     */
    public int getNumberOfQuizzesInCentury(int idCentury) throws SQLException {
        Connection con = db.getConnection();
        String query = "SELECT idQuiz from Quiz WHERE idCentury = " + idCentury;
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(query);
        int res = 0;

        while (rs.next()) {
            res++;
        }

        rs.close();
        statement.close();
        con.close();

        return res;
    }

    /**
     * Gets Quizzes from the Database
     *
     * @param where an optional condition for the query
     * @return a list of quizzes that meet the required conditions specified by the parameter
     * @throws SQLException if there was a problem with getting the required Data from the Database
     */
    private List<Quiz> fromDBWhere(String where) throws SQLException {
        Connection con = db.getConnection();
        String query = "SELECT * FROM Quiz";

        if (where == null) query += ";";
        else query += " WHERE " + where + ";";

        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(query);
        List<Quiz> res = new LinkedList<>();

        while (rs.next()) {
            int id = rs.getInt("idQuiz");
            int idCentury = rs.getInt("idCentury");
            res.add(new Quiz(id, rs.getString("name"), cf.fromDBWithID(idCentury), qf.fromDBByQuiz(id)));

        }

        rs.close();
        stmt.close();
        con.close();

        return res;
    }
}
