package Db;


public class QueryGenerator {

    public QueryGenerator(String schema) {
        TABLE_PERSON = schema + "terror_person";
    }

    //region PrivateField
    private final String TABLE_PERSON;
    private static final String INSERT_INTO = "INSERT INTO ";
    //endregion PrivateField

    public String GetQueryInsertTerror() {
        return INSERT_INTO + TABLE_PERSON
                + " (name,id_new,person_type_id,is_terrorist,inn,birth_date,description,address,terrorist_resolution,birth_place,passport,date_list,num_list,id_list)"
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
    }

    public String GetQueryDeleteData() {
        String delete = "DELETE FROM ";
        return delete + TABLE_PERSON + ";";
    }
}