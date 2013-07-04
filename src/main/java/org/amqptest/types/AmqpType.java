package org.amqptest.types;

public interface AmqpType<C> {
    C getContent();
}
