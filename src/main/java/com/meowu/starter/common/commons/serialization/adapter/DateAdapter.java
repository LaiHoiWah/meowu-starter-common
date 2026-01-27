package com.meowu.starter.common.commons.serialization.adapter;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.Objects;

public class DateAdapter implements TypeAdapter<Date>{

    @Override
    public Type getType(){
        return Date.class;
    }

    @Override
    public Date deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException{
        return Objects.isNull(element) ? null : new Date(element.getAsJsonPrimitive().getAsLong());
    }

    @Override
    public JsonElement serialize(Date date, Type type, JsonSerializationContext context){
        return Objects.isNull(date) ? null : new JsonPrimitive(date.getTime());
    }
}
