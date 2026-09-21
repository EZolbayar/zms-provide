package com.example.terguun.dto.sain;

import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;

/**
 * ЗМС-ийн заавал талбарын утга DB-д байхгүй (null) бол "-" болгож илгээнэ. Тоо, огноо зэрэг текст бус
 * талбарт `nullsUsing`-ээр хэрэглэнэ: утга байвал ердийн хэлбэрээрээ (тоо, огноо) бичигдэнэ.
 */
public class DashIfNullSerializer extends ValueSerializer<Object> {

    public static final String MISSING = "-";

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializationContext ctxt) {
        gen.writeString(MISSING);
    }
}
