package task01.commands.execcomand;

import task01.commands.Command;
import task01.filesystem.node.Directory;
import task01.filesystem.node.File;
import task01.filesystem.node.Node;

public class TreeCommand extends Command {
    public TreeCommand(String name) {
        super(name);
    }

    @Override
    public Directory exec(String[] splittedCommand, Directory mainDir, Directory curDir) {
        if (!isCorrectCommand(splittedCommand)) {
            System.out.println("Введена некорректная команда");
            return null;
        }

        printTree(curDir, 0);
        return null;
    }

    private static void printTree(Directory currentDir, int numberOfSpace) {
        printNameNode(currentDir.getName() + "/", numberOfSpace);

        for (Node e : currentDir.getListOfChildren().values()) {
            if (e.getClass().equals(File.class)) {
                printNameNode(e.getName(), numberOfSpace + 1);

            } else {
                printTree((Directory) e, numberOfSpace + 2);
            }
        }
    }

    private static void printNameNode(String name, int numberOfSpace) {
        for (int i = 0; i < numberOfSpace; i++) {
            System.out.print(" ");
        }

        System.out.println(name);
    }

    @Override
    public boolean isCorrectCommand(String[] splittedCommand) {
        return splittedCommand.length == 1;
    }
}
