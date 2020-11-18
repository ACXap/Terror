package RepositoryTerror.Data;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class TerrorEntity {

    @JacksonXmlProperty(localName = "TERRORISTS_NAME")
    public String Name;

    @JacksonXmlProperty(localName = "ID_NEW")
    public int IdNew;

    @JacksonXmlProperty(localName = "PERSON_TYPE")
    public int PersonType;

    @JacksonXmlProperty(localName = "IS_TERRORIST")
    public int IsTerrorist;

    @JacksonXmlProperty(localName = "INN")
    public String Inn;

    @JacksonXmlProperty(localName = "BIRTH_DATE")
    public String BirthDate;

    @JacksonXmlProperty(localName = "DESCRIPTION")
    public String Description;

    @JacksonXmlProperty(localName = "ADDRESS")
    public String Address;

    @JacksonXmlProperty(localName = "TERRORISTS_RESOLUTION")
    public String TerroristReslution;

    @JacksonXmlProperty(localName = "BIRTH_PLACE")
    public String BirthPlace;

    @JacksonXmlProperty(localName = "PASSPORT")
    public String Passport;
}