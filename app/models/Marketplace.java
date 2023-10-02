package models;

import java.sql.SQLException;

public class Marketplace {

    public Marketplace() {

    }

    /**
     * A Method for a User to buy a Tarot Card
     *
     * @param user the user who wants to buy a card
     * @param tc   the card the user wants to buy
     * @return true if the user could successfully buy the coin
     * @throws SQLException if there was a problem with getting the required Data from the Database
     */
    public boolean buyTarotCard(UserFactory.User user, TarotCard tc) throws SQLException {
        if (user.getCoins() >= tc.getCost()) {
            user.setCoins(user.getCoins() - tc.getCost());
            user.addCard(tc);
            return true;
        }

        return false;
    }
}
