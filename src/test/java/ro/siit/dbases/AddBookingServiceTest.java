package ro.siit.dbases;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

class AddBookingServiceTest {
    private static Logger logger = Logger.getLogger("db_log.txt");
    ConnManager connectionManager = new ConnManager();
    Connection connection = connectionManager.getConnection("jdbc:postgresql://localhost:5432/booking", "postgres", "ardei1975");

    //Data for DBase table 'accomodation'
    List<Accomodation> addAccomodation() {
        List<Accomodation> accoms = new ArrayList<>();
        accoms.add(new Accomodation(1, "room", "single", 1, " jtgubtobg"));
        accoms.add(new Accomodation(2, "room", "double", 2, "fghrth retrt"));
        accoms.add(new Accomodation(3, "room", "single + double", 3, "ghdf fghrth retrt"));
        accoms.add(new Accomodation(4, "apartment", "2 * double", 4, "srtes wertyrt yueru rttwef"));
        return accoms;
    }
    //Data for DBase table 'room_fair'
    List<RoomFair> addRoomFair() {
        List<RoomFair> fairs = new ArrayList<>();
        fairs.add(new RoomFair(501, 150.0, "Winter"));
        fairs.add(new RoomFair(502, 250.0, "Summer"));
        fairs.add(new RoomFair(503, 200.0, "Autumn"));
        fairs.add(new RoomFair(504, 100.0, "Spring"));
        return fairs;
    }
    //Data for DBase table 'accomodation_fair_relation'
    List<Relational> addAccomFairRelation() {
        List<Relational> list = new ArrayList<>();
        list.add(new Relational(100,1, 502));
        list.add(new Relational(101,1, 503));
        list.add(new Relational(102,2, 501));
        list.add(new Relational(103,2, 502));
        list.add(new Relational(104,2, 503));
        list.add(new Relational(105,2, 504));
        list.add(new Relational(106,3, 501));
        list.add(new Relational(107,3, 504));
        list.add(new Relational(108,4, 502));
        list.add(new Relational(109,4, 503));
        list.add(new Relational(110,4, 504));
        return list;
    }

    /**
     * Addition of data into DB "booking"
     * on all 3 tables using prepared Statement
     */
    @Test
    void insertDataToDB(){
        try{
            PreparedStatement pp1Statement = connection.prepareStatement("INSERT INTO accomodation VALUES (?, ?, ?, ?, ?)");
            for (Accomodation accoms : this.addAccomodation()){
                pp1Statement.setObject(1, accoms.getId());
                pp1Statement.setString(2, accoms.getType());
                pp1Statement.setString(3, accoms.getBed_type());
                pp1Statement.setObject(4, accoms.getMax_guests());
                pp1Statement.setString(5, accoms.getDescription());
                pp1Statement.executeUpdate();
            }
            PreparedStatement pp2Statement = connection.prepareStatement("INSERT INTO room_fair VALUES (?, ?, ?)");
            for(RoomFair rf : this.addRoomFair()) {
                pp2Statement.setObject(1, rf.getId());
                pp2Statement.setObject(2, rf.getValue());
                pp2Statement.setString(3, rf.getSeason());
                pp2Statement.executeUpdate();
            }
            PreparedStatement pp3Statement = connection.prepareStatement("INSERT INTO accomodation_fair_relation VALUES (?, ?, ?)");
            for(Relational rl : this.addAccomFairRelation()){
                pp3Statement.setObject(1, rl.getId());
                pp3Statement.setObject(2, rl.getId_accomodation());
                pp3Statement.setObject(3, rl.getId_room_fair());
                pp3Statement.executeUpdate();
            }
            logger.info("All good! DBase has been filled-up!");
        } catch(SQLException e){
            logger.severe("Error when trying to upload data to SQL");
            e.printStackTrace();
        }

    }

}