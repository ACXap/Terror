package Data;

import RepositoryTerror.Data.TerrorEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Terrorist {


    public Terrorist(TerrorEntity terrorist) {
        Name = terrorist.Name.replace("\n", "");;
        IdNew = terrorist.IdNew;
        PersonType = terrorist.PersonType;
        IsTerrorist = terrorist.IsTerrorist;
        Inn = terrorist.Inn.replace("\n", "");;
        BirthDate = GetDataToString(terrorist.BirthDate.replace("\n", ""));
        Description = terrorist.Description.replace("\n", "");;
        Address = terrorist.Address;
        TerroristReslution = terrorist.TerroristReslution.replace("\n", "");;
        BirthPlace = terrorist.BirthPlace.replace("\n", "");;
        Passport = terrorist.Passport.replace("\n", "");;
    }

    public static List<Terrorist> ConvertEntityToTerrarist(List<TerrorEntity> collection){
        return collection.stream().map(Terrorist::new).collect(Collectors.toList());
    }

    public String Name;

    public int IdNew;

    public int PersonType;

    public int IsTerrorist;

    public String Inn;

    public Date BirthDate;

    public String Description;

    public String Address;

    public String TerroristReslution;

    public String BirthPlace;

    public String Passport;


    public Date GetDataToString(String date){
        if(date==null || date.isEmpty()) return  null;

        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(date);
        } catch (Exception ex) {
            ex.printStackTrace();
            return  null;
        }
    }

}
