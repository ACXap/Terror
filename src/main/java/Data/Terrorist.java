package Data;

import RepositoryTerror.Data.TerrorEntity;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Terrorist {

    public static String SEPARATOR;

    public Terrorist(TerrorEntity terrorist) {
        Name = GetName(RemoveBadChar(terrorist.Name));
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
    public Date DateList;
    public long IdList;
    public String NumList;

    private String GetName(String name) {
        return name.replace("(", SEPARATOR).
                replace(";", SEPARATOR).
                replace(")", SEPARATOR);
    }

    private String RemoveBadChar(String data) {
        if (data == null || data.isEmpty()) return null;

        String temp = data.replace("&quot;", "\"").
                replaceAll(" +", " ").
                replace("\n", "").
                replace("* ", "").
                replace("*", "").
                replace("&amp;", "&").
                replace("&apos;", "'");

        return temp.isEmpty() ? null : temp;
    }
}