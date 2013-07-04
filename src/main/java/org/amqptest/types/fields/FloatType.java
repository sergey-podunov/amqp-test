package org.amqptest.types.fields;

import org.amqptest.types.AmqpType;

public class FloatType implements AmqpType<Float> {
    private Float content;

    public FloatType(Float content) {
        this.content = content;
    }

    @Override
    public Float getContent() {
        return content;
    }
}
