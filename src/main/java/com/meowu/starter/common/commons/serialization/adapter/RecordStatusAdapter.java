package com.meowu.starter.common.commons.serialization.adapter;

import com.google.gson.*;
import com.meowu.starter.domain.commons.enums.RecordStatus;

import java.lang.reflect.Type;
import java.util.Objects;

public class RecordStatusAdapter implements TypeAdapter<RecordStatus>{

    @Override
    public Type getType(){
        return RecordStatus.class;
    }

    @Override
    public RecordStatus deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException{
        return Objects.isNull(element) ? null : RecordStatus.getByCode(element.getAsString());
    }

    @Override
    public JsonElement serialize(RecordStatus status, Type type, JsonSerializationContext context){
        return Objects.isNull(status) ? null : new JsonPrimitive(status.getCode());
    }
}
