package org.amqptest.command;

public class CommandMethodId {
    private int commandId, methodId;

    public int getCommandId() {
        return commandId;
    }

    public int getMethodId() {
        return methodId;
    }

    public CommandMethodId(int commandId, int methodId) {
        this.commandId = commandId;
        this.methodId = methodId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommandMethodId that = (CommandMethodId) o;

        if (commandId != that.commandId) return false;
        if (methodId != that.methodId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commandId;
        result = 31 * result + methodId;
        return result;
    }
}
