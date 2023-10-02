package models;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.*;

import play.db.Database;

import java.util.List;
import java.util.LinkedList;

@Singleton

public class QuestionFactory {

    @Inject
    Database db;

    public class Question {

        private int id;
        private String content;
        private int correctAnswer;
        private List<String> possibleAnswers;

        private Question(int id, String content, int correctAnswer, String answer1, String answer2, String answer3, String answer4) {
            this.id = id;
            this.content = content;
            this.correctAnswer = correctAnswer;
            this.possibleAnswers = new LinkedList<>();
            possibleAnswers.add(answer1);
            possibleAnswers.add(answer2);
            possibleAnswers.add(answer3);
            possibleAnswers.add(answer4);
        }

        public int getID() {
            return id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getCorrectAnswer() {
            return correctAnswer;
        }

        public List<String> getPossibleAnswers() {
            return possibleAnswers;
        }

        /**
         * @param index the id of the required answer
         * @return a string representing the possible answer
         * @throws IllegalArgumentException if the required index is not between 1 and 4
         */
        public String getAnswerAtIndex(int index) {
            String answer = "";
            if (index <= 0 || index > 4) {
                throw new IllegalArgumentException("index must be between 1 and 4");
            } else if (possibleAnswers == null) {
                answer += "No answers have been added for this question";
            } else {
                answer += possibleAnswers.get(index - 1);
            }
            return answer;
        }

        /**
         * Checks if the user already had an answer selected
         *
         * @param idUser the id of the user
         * @return true if the specified user already had an answer selected
         * @throws SQLException if there was a problem with getting the required Data from the Database
         */
        private boolean hasAnswerSelected(int idUser) throws SQLException {
            Connection con = db.getConnection();
            String query = "SELECT selectedAnswer FROM User_has_MultipleChoiceQuestion WHERE idUser =" + idUser + " AND idQuestion =" + id + ";";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int selected = 0;

            while (rs.next()) {
                selected = rs.getInt("selectedAnswer");
            }
            rs.close();
            stmt.close();
            con.close();
            if (selected == 0) {
                return false;
            } else {
                return true;
            }


        }

        /**
         * Updates the Answer a User has selected to the Database
         *
         * @param selectedAnswer the index of the selected Answer
         * @param idUser         the id of the user who has selected the Answer
         * @throws SQLException if there was a problem with updating the Data to the Database
         */
        public void selectAnswer(int selectedAnswer, int idUser) throws SQLException {
            Connection con = db.getConnection();
            if (!hasAnswerSelected(idUser)) {
                PreparedStatement stmnt = con.prepareStatement("INSERT INTO User_has_MultipleChoiceQuestion (`idUser`, `idQuestion`, `selectedAnswer`) VALUES (?,?,?);");
                stmnt.setInt(1, idUser);
                stmnt.setInt(2, id);
                stmnt.setInt(3, selectedAnswer);
                stmnt.executeUpdate();
                stmnt.close();
                con.close();
            } else {
                PreparedStatement stmnt = con.prepareStatement("UPDATE User_has_MultipleChoiceQuestion SET `selectedAnswer`=? where User_has_MultipleChoiceQuestion.idQuestion=" + getID() + ";");
                stmnt.setInt(1, selectedAnswer);
                stmnt.executeUpdate();
                stmnt.close();
                con.close();
            }

            con.close();
        }

        /**
         * Updates to the Database if a User used a card
         *
         * @param user the id of the user that used a card
         * @throws SQLException if there was a problem with updating the Data to the Database
         */
        public void useCard(int user) throws SQLException {
            if (!hasAnswerSelected(user)) {
                Connection con = db.getConnection();
                PreparedStatement statement = con.prepareStatement(
                        "INSERT INTO User_has_MultipleChoiceQuestion (`idUser`,`idQuestion`,`selectedAnswer`,`usedCard`) VALUES (?,?,?,?);"
                );
                statement.setInt(1, user);
                statement.setInt(2, id);
                statement.setInt(3, 1);
                statement.setInt(4, 1);
                statement.executeUpdate();
                statement.close();
                con.close();
            } else {
                Connection con = db.getConnection();
                PreparedStatement statement = con.prepareStatement(
                        "UPDATE User_has_MultipleChoiceQuestion SET `usedCard`=? WHERE idUser=" + user + " AND idQuestion=" + id + ";"
                );
                statement.setInt(1, 1);
                statement.executeUpdate();
                statement.close();
                con.close();
            }
        }

        /**
         * Gets the id of the Quiz that this Question belongs to
         *
         * @return int representing the id of the quiz
         * @throws SQLException if there was a problem with getting the required Data from the Database
         */
        public int getQuizIdFromDbByQuestionId() throws SQLException {
            Connection con = db.getConnection();
            String query = "SELECT idQuiz FROM MultipleChoiceQuestion WHERE idQuestion =" + id + ";";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            int res = 0;

            while (rs.next()) {
                res = rs.getInt("idQuiz");
            }

            rs.close();
            stmt.close();
            con.close();
            return res;
        }
    }

    /**
     * Gets a Question from the Database
     *
     * @param idQuestion the id of the Question that is required
     * @return the requested Question
     * @throws SQLException if there was a problem with getting the required Data from the Database
     */
    public Question fromDBbyID(int idQuestion) throws SQLException {
        return fromDBWhere("idQuestion = " + idQuestion).get(0);
    }

    /**
     * Gets all Question from a certain Quiz
     *
     * @param idQuiz the id of the Quiz whose Questions are required
     * @return a list of Questions that belong to the Quiz
     * @throws SQLException if there was a problem with getting the required Data from the Database
     */
    public List<Question> fromDBByQuiz(int idQuiz) throws SQLException {
        String where = "idQuiz = " + idQuiz;
        return fromDBWhere(where);
    }

    /**
     * Gets Questions from the DB
     *
     * @param where an optional condition for the query
     * @return a list of questions that meet the required conditions specified by the parameter
     * @throws SQLException if there was a problem with getting the required Data from the Database
     */
    private List<Question> fromDBWhere(String where) throws SQLException {
        Connection con = db.getConnection();
        String query = "SELECT * FROM MultipleChoiceQuestion";

        if (where == null) query += ";";
        else query += " WHERE " + where + ";";

        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(query);
        LinkedList<Question> res = new LinkedList<Question>();

        while (rs.next()) {
            res.add(new Question(rs.getInt("idQuestion"), rs.getString("content"), rs.getInt("correctAnswer"), rs.getString("answer1"), rs.getString("answer2"), rs.getString("answer3"), rs.getString("answer4")));
        }

        rs.close();
        stmt.close();
        con.close();

        return res;
    }
}
