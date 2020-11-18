package Interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Интерфейс для описания работы сериализации-десериализации xml строк
 */
public interface IXmlService {
    <T> T Deserialize(String data, Class<T> type) throws Exception;
    <T> String Serialize(T data) throws JsonProcessingException;
}