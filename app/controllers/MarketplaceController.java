package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Marketplace;
import models.TarotCard;
import models.UserFactory;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MarketplaceController extends Controller {
    @Inject
    UserFactory userFactory;

    /**
     * Renders the Market Page
     *
     * @return marketplacePage
     */
    public Result marketplace() {
        if (!session().containsKey("userId")) {
            return redirect(routes.LoginController.loginPage());
        } else {
            try {
                Marketplace market = new Marketplace();
                UserFactory.User currentUser = userFactory.fromDBWithID(Integer.parseInt(session().get("userId")));
                return ok(views.html.map.render(views.html.marketplace.render(market, currentUser)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ok();
    }

    /**
     * Adds the card from the json Object to the users inventory
     *
     * @return marketplace if card was added
     * @throws SQLException if there was a problem with getting the required Data from the Database
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result buyACard() throws SQLException {
        if (!session().containsKey("userId")) {
            return redirect(routes.LoginController.loginPage());
        } else {
            JsonNode json = request().body().asJson();
            String tc = json.get("Card").asText();
            TarotCard card = TarotCard.valueOf(tc);
            UserFactory.User user = userFactory.fromDBWithID(Integer.parseInt(session().get("userId")));

            Marketplace mp = new Marketplace();

            if (mp.buyTarotCard(user, card)) {
                return ok();
            }
            ObjectNode res = Json.newObject();
            res.put("failed", true);
            return ok(res);
        }
    }

}
