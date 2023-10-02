package controllers;


import javax.inject.Inject;

import models.Location;
import models.UserFactory;
import play.db.Database;
import play.mvc.Controller;
import play.mvc.Result;

import java.sql.SQLException;

public class LocationController extends Controller {

    @Inject
    UserFactory userFactory;
    @Inject
    Database db;

    /**
     * Renders the Location
     *
     * @param id the id specifying the location
     * @return location page if user is logged in and the location id is valid
     */
    public Result location(int id) throws SQLException {
        if (!session().containsKey("userId")) {
            return redirect(routes.LoginController.loginPage());
        } else {
            Location location = new Location(id, db);
            if (id > 0 && id <= location.getHighestId()) {
                UserFactory.User currentUser = userFactory.fromDBWithID(Integer.parseInt(session().get("userId")));
                return ok(views.html.location.render(location, currentUser));
            }
            return redirect(routes.MapController.map());

        }
    }
}



