package com.example.sdk_no_dagger.changebankapi.util

import com.example.cf_sdk.changebankapi.model.account.Money
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type
import java.math.BigDecimal

/**
 * Money type converter to convert money string
 */
class MoneyTypeConverter : JsonSerializer<Money?>, JsonDeserializer<Money?> {

    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        type: Type,
        context: JsonDeserializationContext
    ): Money {
        val jsonString = json.asString
        return deserialize(jsonString)
    }

    fun deserialize(jsonString: String?): Money {
        return Money(jsonString)
    }

    fun serialize(src: Money): BigDecimal {
        return src
    }

    override fun serialize(
        src: Money?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src?.let { serialize(it) })
    }
}