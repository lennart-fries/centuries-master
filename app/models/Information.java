package models;

import java.sql.*;

import play.db.Database;

public class Information {
    private String content;
    private boolean isCoin;
    private int id;
    private Database db;

    public Information(int id, String content, boolean isCoin, Database db) {
        this.id = id;
        this.content = content;
        this.isCoin = isCoin;
        this.db = db;
    }

    public String getContent() {
        return content;
    }

    public boolean isCoin() {
        return isCoin;
    }


    /**
     * A Method to have a User collect the Coin that is in this Information
     *
     * @param user user who wants to collect the coin
     * @return true if the user can collect the coin
     * @throws SQLException if there was a problem with getting the required Data from the Database
     */
    public boolean collectCoin(UserFactory.User user) throws SQLException {
        if (!hasCoin(user)) {
            user.setCoins(user.getCoins() + 1);
            updateCollected(user);
            return true;
        }
        return false;
    }

    /**
     * Updates the Database to show that a User has collected the Coin of this Information
     *
     * @param user user who collected the Coin
     * @throws SQLException if there was a problem with getting the required Data from the Database
     */
    private void updateCollected(UserFactory.User user) throws SQLException {
        Connection con = db.getConnection();
        PreparedStatement statement = con.prepareStatement(
                "INSERT INTO User_has_Information(`idUser`,`idInformation`,`collectedCoin`) VALUES (?,?,?);", Statement.RETURN_GENERATED_KEYS
        );
        statement.setInt(1, user.getId());
        statement.setInt(2, id);
        statement.setInt(3, 1);
        statement.executeUpdate();
        statement.close();
        con.close();
    }

    /**
     * Checks if the User has already collected the Coin of this
     *
     * @param user the user who wants to know if he has already collected the coin
     * @return true if the user has already collected the coin
     * @throws SQLException if there was a problem with getting the required Data from the Database
     */
    private boolean hasCoin(UserFactory.User user) throws SQLException {
        Connection con = db.getConnection();
        String query = "SELECT collectedCoin from User_has_Information WHERE idInformation =" + id + " AND idUser=" + user.getId() + ";";
        Statement statementmnt = con.createStatement();
        ResultSet rs = statementmnt.executeQuery(query);
        int coinCollected = 0;

        while (rs.next()) {
            coinCollected = rs.getInt("collectedCoin");
        }

        rs.close();
        statementmnt.close();
        con.close();

        if (coinCollected == 1) {
            return true;
        }

        return false;
    }
}
