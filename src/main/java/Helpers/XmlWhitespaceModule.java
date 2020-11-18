package Helpers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.deser.std.CollectionDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.util.Collection;

public class XmlWhitespaceModule extends SimpleModule {
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
            return new CustomizedCollectionDeserialiser(super.createContextual(ctxt, property));
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
                    return new CustomizedCollectionDeserialiser(
                            (CollectionDeserializer) deserializer);
                } else {
                    return super.modifyCollectionDeserializer(config, type, beanDesc,
                            deserializer);
                }
            }
        });
    }
}