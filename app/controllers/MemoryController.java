package controllers;

import java.sql.SQLException;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.MemoryFactory;
import models.UserFactory;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Controller;
import play.twirl.api.Html;

public class MemoryController extends Controller {

    @Inject
    UserFactory userFactory;
    @Inject
    MemoryFactory memoryFactory;

    /**
     * Renders the Memory
     *
     * @return memory
     * @throws SQLException if there was a problem with getting the required Data from the Database
     */
    public Result memory() throws SQLException {
        if (!session().containsKey("userId")) {
            return redirect(routes.LoginController.loginPage());
        } else {
            MemoryFactory.Memory memory = memoryFactory.getMemoryForLevel(Integer.parseInt(session().get("level")) - 1);
            session().put("memory", memory.toString());
            return ok(views.html.map.render(views.html.memory.render(memory)));
        }
    }

    /**
     * Evaluates the Memory
     *
     * @return result which contains the information if memory was won
     * @throws SQLException if there was a problem with getting the required Data from the Database
     */
    public Result evaluate() throws SQLException {
        MemoryFactory.Memory memory = memoryFactory.fromString(session().get("memory"));

        //get card from request
        Http.RequestBody body = request().body();
        JsonNode json = body.asJson();
        JsonNode node = json.get("card");
        String card = node.textValue();

        //openCard from request
        boolean twoOpened = memory.openCard(card);

        //begin to build response
        ObjectNode result = Json.newObject();
        result.put("twoOpened", twoOpened);
        result.put("openCardOne", memory.getOpenOne());
        result.put("openCardTwo", memory.getOpenTwo());

        //memory logic, test and update model
        if (twoOpened) {
            if (memory.evaluate()) {
                memory.removeOpenCards();
                result.put("rightAnswer", true);
            } else {
                result.put("rightAnswer", false);
            }
            memory.resetOpenCards();
        }
        String memoryString = memory.toString();
        session().put("memory", memoryString);

        if (memory.getCards().isEmpty()) {
            result.put("won", true);
            won();
        } else {
            result.put("won", false);

        }
        return ok(result);
    }

    /**
     * Closes a Card
     *
     * @param card the card that shall be closed
     * @return ok
     */
    public Result closeCard(String card) {
        MemoryFactory.Memory memory = memoryFactory.fromString(session().get("memory"));
        if (memory.getOpenOne().equals(card)) {
            memory.setOpenOne("x");
            String memoryString = memory.toString();
            session().put("memory", memoryString);
            return ok();
        } else if (memory.getOpenTwo().equals(card)) {
            memory.setOpenTwo("x");
            String memoryString = memory.toString();
            session().put("memory", memoryString);
            return ok();
        } else {
            return badRequest();
        }
    }

    /**
     * Gives the User that has won coins
     *
     * @return ok
     * @throws SQLException if there was a problem with getting the required Data from the Database
     */
    public Result won() throws SQLException {
        UserFactory.User user = userFactory.fromDBWithID(Integer.parseInt(session().get("userId")));
        user.setCoins(user.getCoins() + 3);
        user.updateMemoryPlayed(Integer.parseInt(session().get("level")) - 1);
        return ok();
    }

    /**
     * Checks if the User is qualified to change centuries and has played the memory before
     *
     * @param level the level the user wants to change into
     * @return memory if the user can change and has not played the memory yet, map - if the user has already played the memory for the century
     * @throws SQLException if there was a problem with getting the required Data from the Database
     */
    public Result levelChange(int level) throws SQLException {
        UserFactory.User user = userFactory.fromDBWithID(Integer.parseInt(session().get("userId")));
        if (user.getProgressByCentury(level - 1) >= 70 || level == 1) {
            session().put("level", Integer.toString(level));
            if (!user.hasPlayedMemoryInLevel(level - 1) && level > 1) {
                return memory();
            }
        } else {
            Html html = new Html("<div class='alert alert-warning failure'><strong>You can not advance to this Century yet!</strong> <br> You need to have at least 70% Progress in your current Century to move on to the next one. <br> Earn some Progress by doing Quizzes and try again!</div>");
            return ok(views.html.map.render(html));
        }
        return redirect(routes.MapController.map());
    }
}
