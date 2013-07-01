package org.amqptest.types.fields;

import org.amqptest.types.AmqpType;

public class DoubleType implements AmqpType<Double> {
    private Double content;

    public DoubleType(Double content) {
        this.content = content;
    }

    @Override
    public Double getContent() {
        return content;
    }
}
