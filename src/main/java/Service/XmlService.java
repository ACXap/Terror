package Service;

import Helpers.XmlWhitespaceModule;
import Interfaces.IXmlService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XmlService implements IXmlService {

    //region PublicMethod

    public <T> T Deserialize(String data, Class<T> type) throws Exception {
        try {
            return GetXmlMapper().registerModule(new XmlWhitespaceModule()).readValue(data, type);
        } catch (JsonProcessingException ex) {
            throw new Exception("Ошибка разбора ответа от сервера. " + ex.getLocalizedMessage());
        }
    }

    public <T> String Serialize(T data) throws JsonProcessingException {
        return GetXmlMapper().writeValueAsString(data);
    }
    //endregion PublicMethod

    //region PrivateMethod
    private XmlMapper GetXmlMapper() {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        xmlMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        xmlMapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
        xmlMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        return xmlMapper;
    }
    //endregion PrivateMethod
}