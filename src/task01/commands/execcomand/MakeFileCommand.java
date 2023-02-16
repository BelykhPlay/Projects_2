package task01.commands.execcomand;

import task01.commands.Command;
import task01.filesystem.node.Directory;
import task01.filesystem.node.File;

import static task01.commands.util.CommandUtil.getPathToNode;
import static task01.commands.util.CommandUtil.isFile;

public class MakeFileCommand extends Command {
    public MakeFileCommand(String name) {
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
            if (!isFile(path)) {
                System.out.println("Введите имя создаваемого файла");
                return null;
            }

            if (!curDir.getListOfChildren().containsKey(path)) {
                curDir.addChildren(new File(path, curDir));
            }
            return curDir;
        }

        String[] pathToFileBeingCreated = getPathToNode(path);
        String pathToOriginalDir = curDir.getPath();

        if (isFile(pathToFileBeingCreated[1])) {
            curDir = cd.exec(new String[]{"", pathToFileBeingCreated[0]}, mainDir, curDir);

            if (!curDir.getListOfChildren().containsKey(pathToFileBeingCreated[1])) {
                curDir.addChildren(new File(pathToFileBeingCreated[1], curDir));
            }

            curDir = mainDir;
            return cd.exec(new String[]{"", pathToOriginalDir}, mainDir, curDir);
        }

        return curDir;
    }

    @Override
    public boolean isCorrectCommand(String[] splittedCommand) {
        return splittedCommand.length == 2;
    }
}
