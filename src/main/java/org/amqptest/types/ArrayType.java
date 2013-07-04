package org.amqptest.types;

import java.util.List;

public class ArrayType implements AmqpType<List<AmqpType>> {
    private List<AmqpType> content;

    public ArrayType(List<AmqpType> content) {
        this.content = content;
    }

    @Override
    public List<AmqpType> getContent() {
        return content;
    }
}
