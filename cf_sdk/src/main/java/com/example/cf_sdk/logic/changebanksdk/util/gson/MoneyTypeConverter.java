package com.example.cf_sdk.logic.changebanksdk.util.gson;

import com.example.cf_sdk.logic.changebanksdk.model.account.Money;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.math.BigDecimal;

/**
 * Money type converter to convert money string
 */

public class MoneyTypeConverter implements JsonSerializer<Money>, JsonDeserializer<Money> {
    @Override
    public JsonElement serialize(Money src, Type srcType, JsonSerializationContext context) {
        return new JsonPrimitive(serialize(src));
    }

    @Override
    public Money deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        String jsonString = json.getAsString();
        return deserialize(jsonString);
    }

    Money deserialize(String jsonString) {
        return new Money(jsonString);
    }

    BigDecimal serialize(Money src) {
        return src;
    }

}