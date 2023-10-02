package controllers;

import java.sql.*;
import java.util.*;
import javax.inject.Inject;

import models.MessageFactory;
import models.MessageForm;
import models.UserFactory;
import models.UserForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class FriendsController extends Controller {
    @Inject
    UserFactory userFactory;
    @Inject
    MessageFactory messageFactory;
    @Inject
    FormFactory formFactory;

    /**
     * Renders the Socials Page
     *
     * @return socials page
     */
    public Result friendsPage() {
        if (!session().containsKey("userId")) {
            return redirect(routes.LoginController.loginPage());
        }
        try {
            UserFactory.User curUser = userFactory.fromDBWithID(Integer.parseInt(session().get("userId")));
            List<MessageFactory.Message> inbox = messageFactory.getInboxOfUser(curUser.getId());
            List<MessageFactory.Message> outbox = messageFactory.getOutboxOfUser(curUser.getId());
            List<UserFactory.User> friends = curUser.getFriends();
            Form addFriends = formFactory.form(models.UserForm.class);
            return ok(map.render(postBox.render(curUser, inbox, outbox, friends, addFriends)));
        } catch (SQLException s) {
            return redirect(routes.LoginController.loginPage());
        }
    }

    /**
     * Renders the profile of the given user
     *
     * @param id id of user which profile should be loaded
     * @return profilpage of given user
     */
    public Result showProfile(int id) {
        if (!session().containsKey("userId")) {
            return redirect(routes.LoginController.loginPage());
        }
        try {
            UserFactory.User friend = userFactory.fromDBWithID(id);
            return ok(map.render(profile.render(friend, friend.getHighestLevel())));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return redirect(routes.FriendsController.friendsPage());
    }

    /**
     * Adds new Friend to user, if a valid user is given
     *
     * @return friendspage
     */
    public Result addNewFriend() {
        try {
            Form<UserForm> newForm = formFactory.form(UserForm.class).bindFromRequest();
            if (newForm == null) {
                return redirect(routes.FriendsController.friendsPage());
            }
            UserForm newFriend = newForm.get();

            UserFactory.User friend = userFactory.fromDBWithUsername(newFriend.getUsername()).get(0);
            UserFactory.User curUser = userFactory.fromDBWithID(Integer.parseInt(session().get("userId")));
            if (friend == null) {
                return ok();
            }
            curUser.addFriend(friend.getId());

            return friendsPage();
        } catch (SQLException s) {
            s.printStackTrace();
            return redirect(routes.FriendsController.friendsPage());
        } catch (IndexOutOfBoundsException i) {
            i.printStackTrace();
            return redirect(routes.FriendsController.friendsPage());
        }
    }

    /**
     * render the write Message Page
     *
     * @return writeMessagePAge
     */
    public Result writeMessage() {
        if (!session().containsKey("userId")) {
            return redirect(routes.LoginController.loginPage());
        } else {
            try {
                Form<MessageForm> messageForm = formFactory.form(MessageForm.class);
                Map<String, String> friends = new HashMap<>();
                List<UserFactory.User> friendsOfCurUser = userFactory.fromDBWithID(Integer.parseInt(session().get("userId"))).getFriends();
                for (UserFactory.User friend : friendsOfCurUser) {
                    friends.put(friend.getUsername(), friend.getUsername());
                }
                return ok(map.render(messageWrite.render(messageForm, friends)));
            } catch (SQLException s) {
                s.printStackTrace();
                return redirect(routes.FriendsController.writeMessage());
            } catch (IndexOutOfBoundsException i) {
                i.printStackTrace();
                return redirect(routes.FriendsController.friendsPage());
            }
        }
    }

    /**
     * Adds the written Message to the outbox of the sender and the inbox of the receiver, if receiver and content of Message is valid
     *
     * @return friendsPage
     */
    public Result sendMessage() {
        Form mForm = formFactory.form(MessageForm.class).bindFromRequest();
        if (!checkMessageForm(mForm)) {
            return redirect(routes.FriendsController.friendsPage());
        }
        MessageForm messageForm = (MessageForm) mForm.get();
        try {
            UserFactory.User sender = userFactory.fromDBWithID(Integer.parseInt(session().get("userId")));
            UserFactory.User receiver = userFactory.fromDBWithUsername(messageForm.getNameOfreceiver()).get(0);
            String content = messageForm.getContent();
            messageFactory.create(content, sender.getId(), receiver.getId());
            return friendsPage();
        } catch (SQLException s) {
            s.printStackTrace();
            return redirect(routes.FriendsController.friendsPage());
        } catch (IndexOutOfBoundsException i) {
            i.printStackTrace();
            return redirect(routes.FriendsController.friendsPage());
        }
    }

    /**
     * Checks if the given Message Form is null or empty and if the given Receiver is valid
     *
     * @param messageForm messageForm to check on
     * @return true if message and content is not null, the content is not empty and the given receiver is found in the database
     */
    public boolean checkMessageForm(Form<MessageForm> messageForm) {
        MessageForm message = messageForm.get();
        if (message == null) return false;
        if (message.getContent() == null || message.getContent().equals("")) return false;
        try {
            if (userFactory.fromDBWithUsername(message.getNameOfreceiver()).size() < 0) {
                return false;
            }
        } catch (SQLException s) {
            return false;
        } catch (IllegalArgumentException i) {
            i.getMessage();
            return false;
        }
        return true;
    }
}