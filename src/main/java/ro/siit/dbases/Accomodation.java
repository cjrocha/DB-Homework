package ro.siit.dbases;

public class Accomodation {
    private int id;
    private String type;
    private String bed_type;
    private int max_guests;
    private String description;

    /**
     * Defines the object 'Accomodation' has getters and to string for printing
     * Correlates with DB table 'accomodation' that has fields:
     * -- id: int (primary key)
     * -- type: varchar (32)
     * -- bed_type: varchar (32)
     * -- max_guests: int
     * -- description: varchar(512)
     *
     * @param id - id of accomodation on DB - integer
     * @param type - type of accomodation on DB - String, also can be an enum
     * @param bed_type - type of bed of accomodation on DB - String, also can be an enum
     * @param max_guests - the max. allowed guests for accomodation on DB - integer
     * @param description - the accomodation description - String
     */
    public Accomodation(int id, String type, String bed_type, int max_guests, String description) {
        this.id = id;
        this.type = type;
        this.bed_type = bed_type;
        this.max_guests = max_guests;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getBed_type() {
        return bed_type;
    }

    public int getMax_guests() {
        return max_guests;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("%s -- %s -- %s -- %s -- %s", id, type, bed_type, max_guests, description);
    }
}
