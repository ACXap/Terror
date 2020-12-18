package Data;

import RepositoryTerror.Data.TerrorEntity;

import java.util.List;
import java.util.stream.Collectors;

public class Terrorist {


    public Terrorist(TerrorEntity terrorist) {
        Name = RemoveBadChar(terrorist.Name);
        IdNew = terrorist.IdNew;
        PersonType = terrorist.PersonType;
        IsTerrorist = terrorist.IsTerrorist;
        Inn = RemoveBadChar(terrorist.Inn);
        BirthDate = RemoveBadChar(terrorist.BirthDate);
        Description = RemoveBadChar(terrorist.Description);
        Address = RemoveBadChar(terrorist.Address);
        TerroristResolution = RemoveBadChar(terrorist.TerroristReslution);
        BirthPlace = RemoveBadChar(terrorist.BirthPlace);
        Passport = RemoveBadChar(terrorist.Passport);
    }

    public static List<Terrorist> ConvertEntityToTerrorist(List<TerrorEntity> collection) {
        return collection.stream().map(Terrorist::new).collect(Collectors.toList());
    }

    public String Name;

    public int IdNew;

    public int PersonType;

    public int IsTerrorist;

    public String Inn;

    public String BirthDate;

    public String Description;

    public String Address;

    public String TerroristResolution;

    public String BirthPlace;

    public String Passport;

    private String RemoveBadChar(String data) {
        if (data == null || data.isEmpty()) return null;

        String temp = data.replace("\n", "");
        return temp.isEmpty() ? null : temp;
    }
}
