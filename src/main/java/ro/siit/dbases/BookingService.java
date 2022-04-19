package ro.siit.dbases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * This class is not used in the homework context!!!
 */
public class BookingService {
    private Connection connection;

    /**
     * Constructor for connection
     * @param connection
     */
    public BookingService (Connection connection){
        this.connection = connection;
    }

    /**
     * Adds data in 'accomodation' table from DB.
     * @param newAccom - defines a new accomodation entry
     *
     * Uses Prepared Statement for handling the SQL statements
     */
    //add
    public void addAccomodation (Accomodation newAccom){
        try{
            PreparedStatement addNewAccom = connection.prepareStatement("INSERT INTO accomodation VALUES (?, ?, ?, ?, ?)");
            addNewAccom.setObject(1, newAccom.getId());
            addNewAccom.setString(2, newAccom.getType());
            addNewAccom.setString(3, newAccom.getBed_type());
            addNewAccom.setObject(4, newAccom.getMax_guests());
            addNewAccom.setString(5, newAccom.getDescription());
            addNewAccom.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds data in 'room_fair' table from DB.
     *
     * Uses Prepared Statement for handling the SQL statements
     * @param roomFair - defines a new room_fair entry
     */
    //add
    public void addRoomFair(RoomFair roomFair){
        try {
            PreparedStatement addRoomFair = connection.prepareStatement("INSERT INTO room_fair VALUES (?, ?, ?)");
            addRoomFair.setObject(1, roomFair.getId());
            addRoomFair.setObject(2, roomFair.getValue());
            addRoomFair.setString(3, roomFair.getSeason());
            addRoomFair.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds data in 'accomodation_fair_relation' table from DB.
     * Uses Prepared Statement for handling the SQL statements
     *
     * @param id - id of the entry on database
     * @param newAccom - the id of the new accomodation
     * @param roomFair - the id of the new room_fair
     */
    //add
    public void addAccomFairRelation (int id, Accomodation newAccom, RoomFair roomFair){
        try{
            PreparedStatement addAccomFairRelation = connection.prepareStatement("INSERT INTO accomodation_fair_relation VALUES (?, ?, ?)");
            addAccomFairRelation.setObject(1, id);
            addAccomFairRelation.setObject(2, newAccom.getId());
            addAccomFairRelation.setObject(2, roomFair.getId());
            addAccomFairRelation.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Interrogates the dbase and builds a list of 'listings' entries
     * that can be printed, added to a new DB table or used in different places.
     *
     * @return - complete list of accomodations with all available prices.
     */
    //get
    public List<Listings> listings () {
        List<Listings> entries = new ArrayList<>();
        try{
            String type = null;
            String bedType = null;
            Double value = null;
            String season = null;
            int maxGuests = 0;
            String descr = null;

            PreparedStatement p1Statement = connection.prepareStatement("SELECT * FROM accomodation_fair_relation");
            ResultSet result1 = p1Statement.executeQuery();
            while (result1.next()) {
                int id = result1.getInt("id");
                int accomId = result1.getInt("id_accomodation");
                PreparedStatement p2Statement = connection.prepareStatement("SELECT * FROM accomodation WHERE id = ?");
                p2Statement.setObject(1, accomId);
                ResultSet result2 = p2Statement.executeQuery();
                while (result2.next()) {
                    type = result2.getString("type");
                    bedType = result2.getString("bed_type");
                    maxGuests = result2.getInt("max_guests");
                    descr = result2.getString("description");
                }
                int fairId = result1.getInt("id_room_fair");
                PreparedStatement p3Statement = connection.prepareStatement("SELECT * FROM room_fair WHERE id = ?");
                p2Statement.setObject(1, fairId);
                ResultSet result3 = p3Statement.executeQuery();
                while (result3.next()) {
                    value = result3.getDouble("value");
                    season = result3.getString("season");
                }
                entries.add(new Listings(String.valueOf(id), type, bedType, String.valueOf(maxGuests), descr, String.valueOf(value), season));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return entries;
    }

}
