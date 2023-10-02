package models;

import play.db.Database;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Singleton
public class MessageFactory {

    @Inject
    Database db;
    @Inject
    UserFactory uf;

    public class Message {
        private int id;
        private UserFactory.User sender;
        private UserFactory.User receiver;
        private String content;


        public Message(UserFactory.User sender, UserFactory.User receiver, String content) {
            this.sender = sender;
            this.receiver = receiver;
            this.content = content;
        }

        public Message(int id, UserFactory.User sender, UserFactory.User receiver, String content) {
            this.id = id;
            this.sender = sender;
            this.receiver = receiver;
            this.content = content;
        }

        public UserFactory.User getSender() {
            return sender;
        }

        public UserFactory.User getReceiver() {
            return receiver;
        }

        public String getContent() {
            return content;
        }

        public int getId() {
            return id;
        }

    }

    /**
     * Creates a new Message and saves it to the Database
     *
     * @param content  the content of the message
     * @param sender   the user who sent the message
     * @param receiver the user whom the message was sent to
     * @return the created Message
     * @throws SQLException - if there was a problem with updating the Data to the Database
     */
    public Message create(String content, int sender, int receiver) throws SQLException {
        Connection con = db.getConnection();
        PreparedStatement statement = con.prepareStatement(
                "INSERT INTO Messages (`content`, `idSender`, `idReceiver`) VALUES (?,?,?);",
                Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, content);
        statement.setInt(2, sender);
        statement.setInt(3, receiver);

        statement.executeUpdate();
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            Message res = new Message(generatedKeys.getInt(1), uf.fromDBWithID(sender), uf.fromDBWithID(receiver), content);
            generatedKeys.close();
            statement.close();
            con.close();
            return res;
        } else {
            generatedKeys.close();
            statement.close();
            con.close();
            throw new SQLException("Creating Message failed, no ID obtained.");
        }
    }

    /**
     * Gets all Messages that were sent to a User
     *
     * @param user the id of the user whose inbox is required
     * @return a list containing all Messages sent to the specified user
     * @throws SQLException if there was a problem with getting the required Data from the Database
     */
    public List<MessageFactory.Message> getInboxOfUser(int user) throws SQLException {
        return fromDBWhere("idReceiver=" + user);
    }

    /**
     * Gets all Messages that were sent by the User
     *
     * @param user the id of the user whose outbox is required
     * @return a list containing all Messages sent from the specified user
     * @throws SQLException if there was a problem with getting the required Data from the Database
     */
    public List<MessageFactory.Message> getOutboxOfUser(int user) throws SQLException {
        return fromDBWhere("idSender=" + user);
    }

    /**
     * Gets a Message from the Database
     *
     * @param idMessage the id of the Message that is required
     * @return the requested Message
     * @throws SQLException if there was a problem with getting the required Data from the Database
     */
    public MessageFactory.Message fromDBWithID(int idMessage) throws SQLException {
        return fromDBWhere("idMessages=" + idMessage).get(0);
    }

    /**
     * Gets Messages from the DB
     *
     * @param where an optional condition for the query
     * @return a list of messages that meet the conditions specified by the parameter
     * @throws SQLException if there was a problem with getting the required Data from the Database
     */
    private List<Message> fromDBWhere(String where) throws SQLException {
        Connection con = db.getConnection();
        String query = "SELECT * FROM Messages";

        if (where == null) query += ";";
        else query += " WHERE " + where + " ORDER BY idMessages DESC;";

        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(query);
        List<Message> res = new LinkedList<>();

        while (rs.next()) {
            int sender = rs.getInt("idSender");
            int receiver = rs.getInt("idReceiver");
            int messageId = rs.getInt("idMessages");
            String content = rs.getString("content");
            res.add(new Message(messageId, uf.fromDBWithID(sender), uf.fromDBWithID(receiver), content));
        }

        rs.close();
        statement.close();
        con.close();


        return res;
    }
}
