package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import play.db.Database;

public class Location {
    private Database db;
    private int id;

    public Location(int id, Database db) {
        this.id = id;
        this.db = db;
    }

    /**
     * A Method to get the name of the Location from the Database
     *
     * @return a string that represents the name of the location
     * @throws SQLException if there was a problem with getting the required Data from the Database
     */
    public String getName() throws SQLException {
        Connection con = db.getConnection();
        Statement statement = con.createStatement();
        String query = "SELECT name FROM Location WHERE idLocation= " + id + ";";
        ResultSet rs = statement.executeQuery(query);
        String name = null;
        while (rs.next()) {
            name = rs.getString("name");
        }
        rs.close();
        statement.close();
        con.close();
        return name;
    }

    /**
     * A Method to get the Path of the Background Image from the Database
     *
     * @return a string that represents the background path
     * @throws SQLException if there was a problem with getting the required Data from the Database
     */
    public String getBackgroundPath() throws SQLException {
        Connection con = db.getConnection();
        Statement statement = con.createStatement();
        String query = "SELECT backgroundPath FROM Location WHERE idLocation= " + id + ";";
        ResultSet rs = statement.executeQuery(query);
        String backgroundPath = null;
        while (rs.next()) {
            backgroundPath = rs.getString("backgroundPath");
        }
        rs.close();
        statement.close();
        con.close();
        return backgroundPath;
    }

    /**
     * A Method to get the Information for the Location from the Database
     *
     * @return a list of all Information associatesd with this location
     * @throws SQLException if there was a problem with getting the required Data from the Database
     */
    public List<Information> getLocationData() throws SQLException {
        Connection con = db.getConnection();
        Statement statement = con.createStatement();
        String query = "SELECT * FROM Information WHERE idLocation= " + id + ";";
        ResultSet rs = statement.executeQuery(query);
        List<Information> locationData = new ArrayList<>();

        while (rs.next()) {
            locationData.add(new Information(rs.getInt("idInformation"), rs.getString("content"), rs.getBoolean("isCoin"), db));
        }

        rs.close();
        statement.close();
        con.close();

        return locationData;
    }

    public int getHighestId() throws SQLException {
        Connection con = db.getConnection();
        Statement statement = con.createStatement();
        String query = "SELECT idLocation from Location;";
        ResultSet rs = statement.executeQuery(query);
        List<Integer> values = new ArrayList<>();
        while (rs.next()) {
            values.add(rs.getInt("idLocation"));
        }
        rs.close();
        statement.close();
        con.close();

        return values.get(values.size() - 1);
    }

}