package task01.commands.execcomand;

import task01.commands.Command;
import task01.filesystem.node.Directory;

import static task01.commands.util.CommandUtil.*;

public class ChangeDirectoryCommand extends Command {
    public ChangeDirectoryCommand(String name) {
        super(name);
    }

    @Override
    public Directory exec(String[] splittedCommand, Directory mainDir, Directory curDir) {
        String path = splittedCommand[1];

        if (path.equals("/") || path.equals("")) {
            return mainDir;

        }
        Directory copyOfDir = curDir;

        for (String e : getPathDirectories(path)) {
            if (curDir.getListOfChildren().containsKey(e)) {
                curDir = (Directory) curDir.getListOfChildren().get(e);

            } else {
                System.out.println("Указанной директории не найдено");
                return copyOfDir;
            }
        }

        return curDir;
    }

    @Override
    public boolean isCorrectCommand(String[] splittedCommand) {
        return splittedCommand.length == 2;
    }
}