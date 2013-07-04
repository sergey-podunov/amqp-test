package org.amqptest.types.fields;

import org.amqptest.types.AmqpType;

public class LongLongInt implements AmqpType<Long> {
    private Long content;

    public LongLongInt(Long content) {
        this.content = content;
    }

    @Override
    public Long getContent() {
        return content;
    }
}
