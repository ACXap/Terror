package Service;

import Interfaces.IXmlService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.deser.std.CollectionDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.util.Collection;

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

    public static class XmlWhitespaceModule extends SimpleModule {
        private static class CustomizedCollectionDeserialiser extends CollectionDeserializer {

            public CustomizedCollectionDeserialiser(CollectionDeserializer src) {
                super(src);
            }

            private static final long serialVersionUID = 1L;

            @Override
            public Collection<Object> deserialize(JsonParser jp, DeserializationContext ctxt)
                    throws IOException {
                if (jp.getCurrentToken() == JsonToken.VALUE_STRING
                        && jp.getText().matches("^[\\r\\n\\t ]+$")) {
                    return (Collection<Object>) _valueInstantiator.createUsingDefault(ctxt);
                }
                return super.deserialize(jp, ctxt);
            }

            @Override
            public CollectionDeserializer createContextual(DeserializationContext ctxt,
                                                           BeanProperty property) throws JsonMappingException {
                return new XmlWhitespaceModule.CustomizedCollectionDeserialiser(super.createContextual(ctxt, property));
            }
        }

        private static final long serialVersionUID = 1L;

        @Override
        public void setupModule(SetupContext context) {
            super.setupModule(context);
            context.addBeanDeserializerModifier(new BeanDeserializerModifier() {
                @Override
                public JsonDeserializer<?> modifyCollectionDeserializer(
                        DeserializationConfig config, CollectionType type,
                        BeanDescription beanDesc, JsonDeserializer<?> deserializer) {
                    if (deserializer instanceof CollectionDeserializer) {
                        return new XmlWhitespaceModule.CustomizedCollectionDeserialiser(
                                (CollectionDeserializer) deserializer);
                    } else {
                        return super.modifyCollectionDeserializer(config, type, beanDesc,
                                deserializer);
                    }
                }
            });
        }
    }
}