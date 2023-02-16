package task01.commands.execcomand;

import task01.commands.Command;
import task01.filesystem.node.Directory;

import static task01.commands.util.CommandUtil.getPathToNode;

public class MakeDirectoryCommand extends Command {
    public MakeDirectoryCommand(String name) {
        super(name);
    }

    @Override
    public Directory exec(String[] splittedCommand, Directory mainDir, Directory curDir) {
        if (!isCorrectCommand(splittedCommand)) {
            System.out.println("Введена некорректная команда");
            return null;
        }

        String path = splittedCommand[1];
        Command cd = new ChangeDirectoryCommand("");

        if (!path.contains("/")) {
            curDir.addChildren(new Directory(path, curDir));
            return curDir;
        }

        String[] pathToDirBeingCreated = getPathToNode(path);
        String pathToOriginalDir = curDir.getPath();

        curDir = cd.exec(new String[]{"cd", pathToDirBeingCreated[0]}, mainDir, curDir);
        curDir.addChildren(new Directory(pathToDirBeingCreated[1], curDir));
        curDir = mainDir;

        return cd.exec(new String[]{"Cd", pathToOriginalDir}, mainDir, curDir);
    }

    @Override
    public boolean isCorrectCommand(String[] splittedCommand) {
        return splittedCommand.length == 2;
    }
}
