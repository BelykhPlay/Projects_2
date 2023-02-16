package task01.commands;

import task01.filesystem.node.Directory;

public abstract class Command implements CheckCommand{
    private String name;

    protected Command(String name) {
        this.name = name;
    }

    public abstract Directory exec(String[] splittedCommand, Directory mainDir, Directory curDir);

    public String getName() {
        return name;
    }
}
