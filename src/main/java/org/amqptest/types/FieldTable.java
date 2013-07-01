package org.amqptest.types;

import java.util.Map;

public class FieldTable implements AmqpType<Map<String, AmqpType>> {
    private Map<String, AmqpType> content;

    public FieldTable(Map<String, AmqpType> content) {
        this.content = content;
    }

    @Override
    public Map<String, AmqpType> getContent() {
        return content;
    }

    public byte[] bytes() {
        throw new RuntimeException("Haven't implemented yet");
    }
}
