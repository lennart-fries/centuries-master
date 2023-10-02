package models;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.*;

import play.db.Database;

import java.util.*;


@Singleton
public class UserFactory {
    @Inject
    Database db;
    @Inject
    QuizFactory qf;

    public class User {

        private int id;
        private String username;
        private String password;
        private String email;
        private int coins;
        private Map<TarotCard, Integer> cards;

        private User(int id, String username, String password, String email, int coins, Map<TarotCard, Integer> cards) {
            this.id = id;
            this.username = username;
            this.password = password;
            this.email = email;
            this.coins = coins;
            this.cards = cards;
        }

        public int getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) throws SQLException {
            this.username = username;
            update();
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) throws SQLException {
            this.password = password;
            update();
        }

        public int getCoins() {
            return coins;
        }

        public void setCoins(int coins) throws SQLException {
            this.coins = coins;
            update();
        }

        public Map<TarotCard, Integer> getCards() {
            return cards;
        }

        public void addCard(TarotCard card) throws SQLException {
            cards.put(card, cards.get(card) + 1);
            updateCardAdded(card);
        }

        public boolean removeCard(TarotCard card) throws SQLException {
            if (cards.get(card) > 0) {
                cards.put(card, cards.get(card) - 1);
                updateCardRemoved(card);
                return true;
            }
            return false;
        }

        /**
         * Gets all the Friends for the User from the Database
         *
         * @return a list of users that are friends with this user
         * @throws SQLException if there was a problem with getting the required Data from the Database
         */
        public List<User> getFriends() throws SQLException {
            Connection con = db.getConnection();
            String query = "SELECT User.* from User, Friend WHERE (Friend.idFriend = User.idUser AND " + id + "= Friend.idUser) OR (Friend.idFriend =" + id + " AND Friend.idUser = User.idUser) ORDER BY username DESC";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            List<User> res = new LinkedList<>();

            while (rs.next()) {
                int id = rs.getInt("idUser");
                res.add(new User(id, rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getInt("coins"), getCardsByUserID(id)));
            }

            rs.close();
            statement.close();
            con.close();

            return res;
        }

        /**
         * Updates the Database when a Friend is added
         *
         * @param idFriend the id of the user that was added as a friend
         * @throws SQLException if there was a problem with updating the Data to the Database
         */
        public void addFriend(int idFriend) throws SQLException {
            Connection con = db.getConnection();
            Statement statement = con.createStatement();
            statement.executeUpdate("INSERT INTO Friend (idFriend, idUser) VALUES (" + idFriend + ", " + id + ");");
            statement.close();
            con.close();
        }

        /**
         * Returns all the solved percentages in the Database for this User
         *
         * @return a list of percentages
         * @throws SQLException if there was a problem with getting the required Data from the Database
         */
        private List<Double> getSolved() throws SQLException {
            Connection con = db.getConnection();
            String query = "SELECT solved from User_has_Quiz WHERE User_has_Quiz.idUser = " + id;
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            LinkedList<Double> res = new LinkedList<>();

            while (rs.next()) {
                res.add(rs.getDouble("solved"));
            }

            rs.close();
            statement.close();
            con.close();

            return res;
        }

        /**
         * Returns all the solved percentages in the Database for this User
         *
         * @param idCentury the id of the Century from which the percentages are required
         * @return a list of percentages
         * @throws SQLException if there was a problem with getting the required Data from the Database
         */
        private List<Double> getSolvedInCentury(int idCentury) throws SQLException {
            Connection con = db.getConnection();
            String query = "SELECT solved from User_has_Quiz, Quiz WHERE User_has_Quiz.idUser = " + id + " AND Quiz.idCentury = " + idCentury + " AND User_has_Quiz.idQuiz = Quiz.idQuiz";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            LinkedList<Double> res = new LinkedList<>();

            while (rs.next()) {
                res.add(rs.getDouble("solved"));
            }

            rs.close();
            statement.close();
            con.close();

            return res;
        }

        /**
         * Calculates the total Progress of the User
         *
         * @return a double representing the percentage
         * @throws SQLException if there was a problem with getting the required Data from the Database
         */
        public double getProgress() throws SQLException {
            double solvedValue = getSolved().stream().mapToDouble(a -> a).sum();
            double allQuizzes = (double) qf.getNumberOfQuizzes();

            return Math.round((solvedValue / allQuizzes) * 100) / 100D;
        }

        /**
         * Calculates the Progress of the User in a given Century
         *
         * @param idCentury the id of the Century
         * @return a double representing the percentage
         * @throws SQLException if there was a problem with getting the required Data from the Database
         */
        public double getProgressByCentury(int idCentury) throws SQLException {
            double solvedValueInLevel = getSolvedInCentury(idCentury).stream().mapToDouble(a -> a).sum();
            double quizzesInLevel = (double) qf.getNumberOfQuizzesInCentury(idCentury);
            return Math.round((solvedValueInLevel / quizzesInLevel) * 100) / 100D;
        }

        /**
         * Returns the solved percentage for a Quiz
         *
         * @param idQuiz the id of the Quiz from which the percentage is required
         * @return a double representing the percentage
         * @throws SQLException if there was a problem with getting the required Data from the Database
         */
        public double getProgressByQuiz(int idQuiz) throws SQLException {
            Connection con = db.getConnection();
            String query = "SELECT solved from User_has_Quiz, Quiz WHERE User_has_Quiz.idUser = " + id + " AND User_has_Quiz.idQuiz =" + idQuiz;
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            double res = 0;

            while (rs.next()) {
                res = rs.getDouble("solved");
            }

            rs.close();
            statement.close();
            con.close();

            return res;
        }

        /**
         * Updates the corresponding User in the Database
         *
         * @throws SQLException if there was a problem with updating the Data to the Database
         */
        private void update() throws SQLException {
            Connection con = db.getConnection();
            PreparedStatement statement = con.prepareStatement(
                    "UPDATE User SET `username`=?, `password`=?, `email`=?, `coins`=? WHERE idUser=" + id + ";");

            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);
            statement.setInt(4, coins);

            statement.executeUpdate();
            statement.close();
            con.close();
        }

        /**
         * Updates in the Database when a Card was added
         *
         * @param card the card that was added
         * @throws SQLException if there was a problem with updating the Data to the Database
         */
        private void updateCardAdded(TarotCard card) throws SQLException {
            Connection con = db.getConnection();
            PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO Cards (`type`,`idUser`) VALUES (?,?);", Statement.RETURN_GENERATED_KEYS
            );

            statement.setString(1, card.toString());
            statement.setInt(2, id);

            statement.execute();
            statement.close();
            con.close();
        }

        /**
         * Updates in the Database when a Card was removed
         *
         * @param card the card that was removed
         * @throws SQLException if there was a problem with updating the Data to the Database
         */
        private void updateCardRemoved(TarotCard card) throws SQLException {
            Connection con = db.getConnection();
            PreparedStatement statement = con.prepareStatement(
                    "DELETE FROM Cards WHERE idCards=" + getHighestIndexByCard(card) + ";");
            statement.executeUpdate();
            statement.close();
            con.close();
        }

        /**
         * Gets the highest id of a specified card in the Database
         *
         * @param card the card whose id is required
         * @return int representing the highest id
         * @throws SQLException if there was a problem with getting the required Data from the Database
         */
        private int getHighestIndexByCard(TarotCard card) throws SQLException {
            Connection con = db.getConnection();
            String query = "SELECT idCards from Cards WHERE `type` ='" + card.toString() + "' AND idUser = " + id + ";";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            List<Integer> indicesList = new LinkedList<>();

            while (rs.next()) {
                indicesList.add(rs.getInt("idCards"));
            }

            rs.close();
            statement.close();
            con.close();

            return indicesList.get(indicesList.size() - 1);
        }


        /**
         * Deletes the corresponding User
         *
         * @throws SQLException if there was a problem with updating the Data to the Database
         */
        public void delete() throws SQLException {
            Connection con = db.getConnection();
            PreparedStatement statement = con.prepareStatement(
                    "DELETE FROM User WHERE idUser=" + id + ";");
            statement.executeUpdate();
            statement.close();
            statement.close();
            con.close();
        }

        /**
         * Checks if the User has already play the Memory for the specified century
         *
         * @param idCentury the id of the Century
         * @return true if the Memory has already been played
         * @throws SQLException if there was a problem with getting the required Data from the Database
         */
        public boolean hasPlayedMemoryInLevel(int idCentury) throws SQLException {
            Connection con = db.getConnection();
            String query = "SELECT playedMemory FROM User_has_Century WHERE idCentury =" + idCentury + " AND idUser =" + id + ";";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            int finished = 0;

            while (rs.next()) {
                finished = rs.getInt("playedMemory");
            }
            rs.close();
            statement.close();
            con.close();
            if (finished == 1) {
                return true;
            }

            return false;
        }

        /**
         * Updates to the Database when a User has played the Memory for a century
         *
         * @param idCentury the id of the Century
         * @throws SQLException if there was a problem with updating the Data to the Database
         */
        public void updateMemoryPlayed(int idCentury) throws SQLException {
            Connection con = db.getConnection();
            PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO User_has_Century (`idUser`,`idCentury`,`playedMemory`) VALUES (?,?,?);", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
            statement.setInt(2, idCentury);
            statement.setInt(3, 1);

            statement.executeUpdate();
            statement.close();
            con.close();
        }

        /**
         * Checks for the highest level the user has reached
         *
         * @return int representing the highest level
         * @throws SQLException if there was a problem with getting the required Data from the Database
         */
        public int getHighestLevel() throws SQLException {
            int highest = 1;
            if (getProgressByCentury(1) > 70) {
                highest = 2;
            }
            if (getProgressByCentury(1) > 70 && getProgressByCentury(2) > 70) {
                highest = 3;
            }
            if (getProgressByCentury(1) == 100 && getProgressByCentury(2) == 100 && getProgressByCentury(3) == 100) {
                highest = 4;
            }
            return highest;
        }
    }

    public UserFactory() {

    }

    /**
     * Creates a new User and saves it to the Database
     *
     * @param username the name of the user
     * @param password the password of the user
     * @param email    the email of the user
     * @return the created user
     * @throws SQLException - if there was a problem with updating the Data to the Database
     */
    public User create(String username, String password, String email) throws SQLException {
        Connection con = db.getConnection();
        PreparedStatement statement = con.prepareStatement(
                "INSERT INTO User (`username`, `password`, `email`, `coins`) VALUES (?,?,?,?);",
                Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, username);
        statement.setString(2, password);
        statement.setString(3, email);
        statement.setInt(4, 0);

        statement.executeUpdate();

        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            User res = new User(generatedKeys.getInt(1), username, password, email, 0, null);
            generatedKeys.close();
            statement.close();
            con.close();
            return res;
        } else {
            generatedKeys.close();
            statement.close();
            con.close();
            throw new SQLException("Creating User failed, no ID obtained.");
        }
    }

    /**
     * Gets all Users from the Database
     *
     * @return a list containing all users
     * @throws SQLException if there was a problem with getting the required Data from the Database
     */
    public List<User> fromDB() throws SQLException {
        return fromDBWhere(null);
    }

    /**
     * Gets an User from the Database
     *
     * @param idUser - the id of User that is required
     * @return the requested User
     * @throws SQLException - if there was a problem with getting the required Data from the Database
     */
    public User fromDBWithID(int idUser) throws SQLException {
        return fromDBWhere("idUser=" + idUser).get(0);
    }

    /**
     * Returns all Users with the same Username from the DB
     *
     * @param username the name of the user
     * @return a list containing all users with the specified name
     * @throws SQLException if there was a problem with getting the required Data from the Database
     */
    public List<User> fromDBWithUsername(String username) throws SQLException {
        return fromDBWhere("username='" + username + "'");
    }

    /**
     * Gets Users from the Database
     *
     * @param where an optional condition for the query
     * @return a list of users that meet the required conditions specified by the parameter
     * @throws SQLException if there was a problem with getting the required Data from the Database
     */
    private List<User> fromDBWhere(String where) throws SQLException {
        Connection con = db.getConnection();
        String query = "SELECT * FROM User";

        if (where == null) query += ";";
        else query += " WHERE " + where + ";";

        Statement statement = con.createStatement();

        ResultSet rs = statement.executeQuery(query);
        List<User> res = new LinkedList<>();

        while (rs.next()) {
            int id = rs.getInt("idUser");
            res.add(new User(id, rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getInt("coins"), getCardsByUserID(id)));
        }

        rs.close();
        statement.close();
        con.close();

        return res;
    }

    /**
     * Gets the Cards for a User
     *
     * @param idUser the id of the User
     * @return a Map representing the Cards of the User
     * @throws SQLException if there was a problem with getting the required Data from the Database
     */
    private Map<TarotCard, Integer> getCardsByUserID(int idUser) throws SQLException {
        Connection con = db.getConnection();
        String query = "SELECT * from Cards WHERE idUser = " + idUser + ";";
        Statement statement = con.createStatement();

        ResultSet rs = statement.executeQuery(query);
        Map<TarotCard, Integer> res = new EnumMap<>(TarotCard.class);

        for (TarotCard t : TarotCard.values()) {
            res.put(t, 0);
        }

        while (rs.next()) {
            TarotCard tarot = TarotCard.valueOf(rs.getString("type"));
            res.put(tarot, res.get(tarot) + 1);
        }

        rs.close();
        statement.close();
        con.close();

        return res;
    }
}


