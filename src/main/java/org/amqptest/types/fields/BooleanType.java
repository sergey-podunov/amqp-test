package org.amqptest.types.fields;

import org.amqptest.types.AmqpType;

public class BooleanType implements AmqpType<Boolean> {
    private Boolean content;

    public BooleanType(Boolean content) {
        this.content = content;
    }

    @Override
    public Boolean getContent() {
        return content;
    }
}
