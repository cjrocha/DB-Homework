package ro.siit.dbases;

public class RoomFair {
    private int id;
    private double value;
    private String season;

    /**
     * Defines the object 'RoomFair' has getters and to string for printing
     * Correlates with DB table 'room_fair' that has fields:
     * -- id: int (primary key)
     * -- value: double
     * -- season: varchar(32)
     *
     * @param id - id of the room fair on DB - integer
     * @param value - the price of a room on DB - double
     * @param season - the season for which the prices is stated on DB - string
     */
    public RoomFair(int id, double value, String season) {
        this.id = id;
        this.value = value;
        this.season = season;
    }

    public int getId() {
        return id;
    }

    public double getValue() {
        return value;
    }

    public String getSeason() {
        return season;
    }

    @Override
    public String toString() {
        return String.format("%s -- %s -- %s", id, value, season);
    }
}
