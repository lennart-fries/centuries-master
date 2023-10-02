package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class MapController extends Controller {

    /**
     * Renders the map page without any popup shown on it
     *
     * @return mapPage without popup
     */
    public Result map() {
        if (!session().containsKey("userId")) {
            return redirect(routes.LoginController.loginPage());
        } else {
            return ok(views.html.map.render(null));
        }
    }

}
