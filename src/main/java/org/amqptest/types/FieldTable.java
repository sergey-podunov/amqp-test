package org.amqptest.types;

import java.util.Map;

public class FieldTable {
    private Map<String, Object> content;

    public FieldTable(Map<String, Object> content) {
        this.content = content;
    }

    public byte[] bytes() {
        throw new RuntimeException("Haven't implemented yet");
    }
}
