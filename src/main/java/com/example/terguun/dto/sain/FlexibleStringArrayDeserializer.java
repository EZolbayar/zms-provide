package com.example.terguun.dto.sain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;

/** Sain "errors" талбарыг массив, объект эсвэл ганц текстээр буцаадаг тул бүх хэлбэрийг String[] болгож хөрвүүлнэ. */
public class FlexibleStringArrayDeserializer extends ValueDeserializer<String[]> {

    @Override
    public String[] deserialize(JsonParser p, DeserializationContext ctxt) {
        Object raw = ctxt.readValue(p, Object.class);
        List<String> messages = new ArrayList<>();
        flatten(null, raw, messages);
        return messages.toArray(new String[0]);
    }

    private void flatten(String key, Object value, List<String> out) {
        if (value == null) {
            return;
        }
        if (value instanceof Map<?, ?> map) {
            map.forEach((k, v) -> flatten(k == null ? key : String.valueOf(k), v, out));
            return;
        }
        if (value instanceof Iterable<?> iterable) {
            iterable.forEach(v -> flatten(key, v, out));
            return;
        }
        String text = String.valueOf(value);
        if (text.isBlank()) {
            return;
        }
        out.add(key == null || key.isBlank() ? text : key + ": " + text);
    }

}
