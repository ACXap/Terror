package RepositoryTerror.Data;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.Date;
import java.util.List;

public class TerrorResponse {

    @JacksonXmlProperty(isAttribute = true, localName = "NUM")
    public String Num;

    @JacksonXmlProperty(isAttribute = true, localName = "DATE")
    public String Date;

    @JacksonXmlProperty(isAttribute = true, localName = "ID")
    public long Id;

    @JacksonXmlProperty(localName = "TERRORISTS")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<TerrorEntity> Terror;
}