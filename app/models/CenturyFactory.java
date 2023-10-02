package models;

import javax.inject.Singleton;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;

import play.db.Database;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Singleton
public class CenturyFactory {

    @Inject
    Database db;

    public class Century {
        private int id;
        private int beginTime;
        private int endTime;

        private Century(int id, int beginTime, int endTime) {

            this.beginTime = beginTime;
            this.endTime = endTime;

            this.id = id;
        }

        public int getId() {
            return id;
        }
    }


    /**
     * Gets a Century from the Database
     *
     * @param idCentury the id of the century that is required
     * @return the requested century if there was a problem with getting the required Data from the Database
     * @throws SQLException if there was a problem with getting the required Data from the Database
     */
    public Century fromDBWithID(int idCentury) throws SQLException {
        return fromDBWhere("idCentury= " + idCentury).get(0);
    }

    /**
     * Returns all Users that meet a given condition.
     *
     * @param where an optional condition for the query
     * @return a list containing all Centuries that meet the required conditions specified by the parameter
     * @throws SQLException if there was a problem with getting the required Data from the Database
     */
    private List<Century> fromDBWhere(String where) throws SQLException {
        Connection con = db.getConnection();
        String query = "SELECT * FROM Century";

        if (where == null) query += ";";
        else query += " WHERE " + where + ";";

        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(query);
        List<Century> res = new LinkedList<Century>();

        while (rs.next()) {
            int id = rs.getInt("idCentury");
            res.add(new Century(id, rs.getInt("beginTime"), rs.getInt("endTime")));
        }

        rs.close();
        stmt.close();
        con.close();

        return res;
    }
}
