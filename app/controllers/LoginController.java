package controllers;


import models.UserFactory;
import models.UserForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.login;
import views.html.map;
import views.html.register;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

/*
 *Login Controller verarbeitet Login und Registe
 * die jeweilige __Page laedt die jeweilige Seite
 * die login und register Methode verarbeitet die anfrage zum login und register
 */

public class LoginController extends Controller {

    @Inject
    FormFactory formFactory;
    @Inject
    UserFactory userFactory;


    //-----------------login methods ---------------------------------------------------------------

    /**
     * Renders the Login Page
     *
     * @return login page
     */
    public Result loginPage() {
        Form loginForm = formFactory.form(models.UserForm.class);
        return ok(login.render(loginForm));
    }

    /**
     * Checks if the credentials provided are valid
     *
     * @return map - if login was successfull
     */
    public Result login() {
        try {
            Form loginForm = formFactory.form(UserForm.class).bindFromRequest();

            if (loginForm == null | !loginIsValid(loginForm)) {
                return loginPage();
            }
            UserForm userForm = (UserForm) loginForm.get();
            //get the Id, if it is not found it returns -1;
            int userID = userID(userForm);
            if (userID == -1) {
                return loginPage();
            }
            UserFactory.User loginUser = userFactory.fromDBWithID(userID);
            if (!(userForm.getPassword().equals(loginUser.getPassword()))) {
                return loginPage();
            }

            session().put("userId", Integer.toString(userID));
            int curhighestLevel = 1;
            if (userFactory.fromDBWithID(userID).getProgressByCentury(1) > 70) curhighestLevel = 2;
            if (userFactory.fromDBWithID(userID).getProgressByCentury(2) > 70) curhighestLevel = 3;
            session().put("level", Integer.toString(curhighestLevel));
            return ok(map.render(null));

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException i) {
            i.printStackTrace();
        }
        return loginPage();
    }

    /**
     * Logs the current User out and clears the Session
     *
     * @return login page
     */
    public Result logout() {
        session().clear();
        return redirect(routes.LoginController.loginPage());
    }

    /**
     * Checks if the login form is valid
     *
     * @param form - the form that was filled by the user
     * @return true - if the form is valid
     */
    private boolean loginIsValid(Form<UserForm> form) {
        UserForm user = form.get();
        if (user.getUsername().equals("")) {
            return false;
        }
        if (user.getPassword().equals("")) {
            return false;
        }
        return true;
    }

    /**
     * Gets the id of the User from the UserForm
     *
     * @param logindata - the form from which the id has to be found
     * @return int representing the id of the user
     * @throws SQLException             - if there was a problem with getting the required Data from the Database
     * @throws IllegalArgumentException - if the user doesn't exist
     */
    private int userID(UserForm logindata) throws SQLException, IllegalArgumentException {
        List<UserFactory.User> users = userFactory.fromDBWithUsername(logindata.getUsername());
        if (users.size() < 1) throw new IllegalArgumentException("Keinen User mit diesem Namen gefunden!");
        return users.get(0).getId();
    }

    //----------register methods-------------------------------------------------------------------------------------

    /**
     * Renders the Register Page
     *
     * @return register page
     */
    public Result registerPage() {
        Form registerForm = formFactory.form(UserForm.class);
        return ok(register.render(registerForm));
    }

    /**
     * Creates new User and saves the User in the Database
     *
     * @return login page
     */
    public Result register() {
        Form registerForm = formFactory.form(UserForm.class).bindFromRequest();
        if (registerForm == null || !registerValid(registerForm)) {
            return registerPage();
        }

        UserForm user = (UserForm) registerForm.get();
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        try {
            userFactory.create(username, password, email);
        } catch (SQLException e) {
            return registerPage();
        }

        return loginPage();
    }

    /**
     * Checks if the register form is valid
     *
     * @param form - the form that was filled by the new user
     * @return true - if the form is valid
     */
    private boolean registerValid(Form<UserForm> form) {
        UserForm user = form.get();
        if (user.getUsername().equals("")) {
            return false;
        }
        if (user.getPassword().equals("")) {
            return false;
        }
        if (user.getEmail().equals("") || !user.getEmail().contains("@")) {
            return false;
        }
        return true;
    }
}