package ro.siit.dbases;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.logging.Logger;

class BookingServiceTest {
    private static Logger logger = Logger.getLogger("db_log.txt");
    ConnManager connectionManager = new ConnManager();
    Connection connection = connectionManager.getConnection("jdbc:postgresql://localhost:5432/booking", "postgres", "ardei1975");

    /**
     * Interrogates the dbase and prints to console all
     * available accomodations with related fairs.
     * Don't necessary needs prepared statements - as we are just selecting data.
     */
    @Test
    void listings() {
        String type = null;
        int maxGuests = 0;
        double value = 0;
        String season = null;
        String bedType = null;
        String descr = null;
        try (Statement query = connection.createStatement()) {
            ResultSet response = query.executeQuery("SELECT * FROM accomodation_fair_relation");
            while (response.next()) {
                int id = (int) response.getObject("id");
                int accId = (int) response.getObject("id_accomodation");
                int fairId = (int) response.getObject("id_room_fair");
                PreparedStatement p2Statement = connection.prepareStatement("SELECT * FROM accomodation WHERE id = ?");
                p2Statement.setObject(1, accId);
                ResultSet result2 = p2Statement.executeQuery();
                while (result2.next()) {
                    type = result2.getString("type");
                    bedType = result2.getString("bed_type");
                    maxGuests = result2.getInt("max_guests");
                    descr = result2.getString("description");
                }
                PreparedStatement p3Statement = connection.prepareStatement("SELECT * FROM room_fair WHERE id = ?");
                p3Statement.setObject(1, fairId);
                ResultSet result3 = p3Statement.executeQuery();
                while (result3.next()) {
                    value = result3.getDouble("value");
                    season = result3.getString("season");
                }
                logger.info("All good! DBase data is correlated and ready for printing!");
                System.out.println("The accommodation: " + type + " with bed type: "
                        + bedType + " and max. allowed guests: " + maxGuests + " and description: "
                        + descr + " has price of: $" + value + " in the season: " + season);
            }
        } catch (SQLException e) {
            logger.severe("Error when interrogating the SQL DBase!");
            e.printStackTrace();
        }
    }
}