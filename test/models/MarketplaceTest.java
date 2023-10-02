package models;

import org.junit.Test;
import play.Application;
import play.db.Database;
import play.test.WithApplication;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static play.test.Helpers.fakeApplication;

public class MarketplaceTest extends WithApplication {
    protected Application provideApplication() {
        return fakeApplication();
    }

    @Test
    public void buyTarotCardLegal() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        UserFactory uf = app.injector().instanceOf(UserFactory.class);
        UserFactory.User user = uf.fromDBWithID(1);
        int oldCoins = user.getCoins();
        user.setCoins(5);
        assertTrue(new Marketplace().buyTarotCard(user, TarotCard.WHEEL_OF_FORTUNE));
        user.removeCard(TarotCard.WHEEL_OF_FORTUNE);
        assertTrue(user.getCoins() == 0);
        user.setCoins(oldCoins);
    }

    @Test
    public void buyTarotCardIllegal() throws SQLException {
        Database db = app.injector().instanceOf(Database.class);
        Connection c = db.getConnection();
        UserFactory uf = app.injector().instanceOf(UserFactory.class);
        UserFactory.User user = uf.fromDBWithID(1);
        int oldCoins = user.getCoins();
        user.setCoins(4);
        assertFalse(new Marketplace().buyTarotCard(user, TarotCard.WHEEL_OF_FORTUNE));
        user.setCoins(oldCoins);
    }
}