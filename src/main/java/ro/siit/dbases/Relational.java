package ro.siit.dbases;

public class Relational {
    private int id;
    private int id_accomodation;
    private int id_room_fair;

    /**
     * Defines the object 'Relational' has getters.
     * Correlates with DB table 'room_fair' that has fields:
     * -- id: int (primary key)
     * -- id_accomodation: int (foreign key of accomodation)
     * -- id_room_fair: int (foreign key of room fair)
     *
     * @param id - id of the relational entry on DB - integer
     * @param id_accomodation - id of 'accomodation' to which is related - integer
     * @param id_room_fair - id of 'room_fair' to which is related
     */
    public Relational(int id, int id_accomodation, int id_room_fair) {
        this.id = id;
        this.id_accomodation = id_accomodation;
        this.id_room_fair = id_room_fair;
    }

    public int getId() {
        return id;
    }

    public int getId_room_fair() {
        return id_room_fair;
    }

    public int getId_accomodation() {
        return id_accomodation;
    }

}
