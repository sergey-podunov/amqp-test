package org.amqptest.types.fields;

import org.amqptest.types.AmqpType;

public class TimestampType implements AmqpType<Long> {
    private Long content;

    public TimestampType(Long content) {
        this.content = content;
    }

    @Override
    public Long getContent() {
        return content;
    }
}
