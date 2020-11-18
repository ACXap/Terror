package Db;


public class QueryGenerator  {

    public QueryGenerator(String schema) {
        TABLE_PERSON = schema + "terror_person";
    }

    //region PrivateField
    private final String TABLE_PERSON;
    private final String INSERT_INTO = "INSERT INTO ";
    //endregion PrivateField

    public String GetQueryInsertTerror() {
        return INSERT_INTO + TABLE_PERSON
                + " (name, id_new, person_type_id, is_terrorist, inn, birth_date, description, address, terrorist_resolution, birth_place, passport)"
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?);";
    }
}