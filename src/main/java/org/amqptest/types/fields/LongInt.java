package org.amqptest.types.fields;

import org.amqptest.types.AmqpType;

public class LongInt implements AmqpType<Integer> {
    private Integer content;

    public LongInt(Integer content) {
        this.content = content;
    }

    @Override
    public Integer getContent() {
        return content;
    }
}
