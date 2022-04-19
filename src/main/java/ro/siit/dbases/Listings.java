package ro.siit.dbases;

public class Listings {
    String id;
    String type;
    String bedType;
    String maxGuests;
    String descr;
    String value;
    String season;

    /** This class is not used in the homework context!!!
     *
     *
     * Defines the object 'Listings' has getters and to string for printing.
     * Can be used for setting up the returning form and/or working with the
     * entire data from all tables.
     * DB fields to which correlates are:
     * -- id: int (primary key)
     * -- type: varchar (32)
     * -- bed_type: varchar (32)
     * -- max_guests: int
     * -- description: varchar(512)
     * -- value: double
     * -- season: varchar(32)
     *
     * @param id - id of the entry - integer
     * @param type - type of accomodation - string
     * @param bedType - bed_type of accomodation - string
     * @param maxGuests - max. number of guests allowed for this entry - integer
     * @param descr - string, accomodation description
     * @param value - price of accomodation - double
     * @param season - season to which the price applies - string
     */
    public Listings(String id, String type, String bedType, String maxGuests, String descr, String value, String season) {
        this.id = id;
        this.type = type;
        this.bedType = bedType;
        this.maxGuests = maxGuests;
        this.descr = descr;
        this.value = value;
        this.season = season;
    }

    @Override
    public String toString() {
        return String.format("%s -- %s -- %s -- %s -- %s -- %s -- %s", id, type, bedType, maxGuests, descr, value, season);
    }
}
