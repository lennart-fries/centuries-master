package controllers;


import models.UserFactory;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.sql.SQLException;


public class ProfileController extends Controller {
    @Inject
    UserFactory userFactory;

    /**
     * Renders the profile page
     *
     * @return profilePage
     * @throws SQLException if there was a problem with getting the required Data from the Database
     */
    public Result profile() throws SQLException {
        if (!session().containsKey("userId")) {
            return redirect(routes.LoginController.loginPage());
        } else {
            UserFactory.User currentUser = userFactory.fromDBWithID(Integer.parseInt(session().get("userId")));
            int level;
            if (currentUser.getHighestLevel() == 4) {
                level = 4;
            } else {
                level = Integer.parseInt(session().get("level"));
            }
            return ok(views.html.map.render(views.html.profile.render(currentUser, level)));
        }
    }
}
