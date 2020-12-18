package RepositoryTerror;

import Interfaces.IRepositoryTerror;
import Interfaces.IXmlService;
import RepositoryTerror.Data.TerrorResponse;
import Service.XmlService;

import java.nio.file.Files;
import java.nio.file.Paths;

public class RepositoryFile implements IRepositoryTerror {

    public RepositoryFile(String fileName)  {
        _fileName = fileName;
    }

    private final String _fileName;
    private final IXmlService _xmlService = new XmlService();

    public TerrorResponse GetResponse() throws Exception {

        String content = new String(Files.readAllBytes(Paths.get(_fileName))).replace("&lt;", "<").replace("&gt;", ">");
        return _xmlService.Deserialize(content, TerrorResponse.class);
    }
}