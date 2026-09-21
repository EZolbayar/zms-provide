package com.example.terguun.dto.sain;

import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;

/** ЗМС-ийн заавал текст талбар хоосон ("" эсвэл зөвхөн зай) бол "-" болгож илгээнэ. */
public class DashIfBlankSerializer extends ValueSerializer<String> {

    @Override
    public void serialize(String value, JsonGenerator gen, SerializationContext ctxt) {
        gen.writeString(value == null || value.isBlank() ? DashIfNullSerializer.MISSING : value);
    }
}
