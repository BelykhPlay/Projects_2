package task01.commands.execcomand;

import task01.commands.Command;
import task01.filesystem.node.Directory;
import task01.filesystem.node.File;

import java.util.Set;

import static task01.commands.util.CommandUtil.getPathToNode;

public class EchoCommands extends Command {
    public EchoCommands(String name) {
        super(name);
    }

    @Override
    public Directory exec(String[] splittedCommand, Directory mainDir, Directory curDir){
        if (!isCorrectCommand(splittedCommand)) {
            System.out.println("Введена некорректная команда");
            return null;
        }

        String[] pathToFile = {"", splittedCommand[3]};
        String pathToOriginalDir = curDir.getPath();

        Command cd = new ChangeDirectoryCommand("");
        Command touch = new MakeFileCommand("");

        if(splittedCommand[3].contains("/")) {
            pathToFile = getPathToNode(splittedCommand[3]);

            touch.exec(new String[]{"", splittedCommand[3]}, mainDir, curDir);
            curDir = cd.exec(new String[]{"", pathToFile[0]}, mainDir, curDir);
        }

        File file = (File) curDir.getChildren(pathToFile[1]);

        if(file == null) {
            System.out.println("Файл не найден");
            return null;
        }

        if (splittedCommand[2].equals(">")) {
            file.setText(splittedCommand[1]);

        } else {
            file.addText(splittedCommand[1]);
        }

        curDir = mainDir;
        return cd.exec(new String[]{"", pathToOriginalDir}, mainDir, curDir);
    }

    @Override
    public boolean isCorrectCommand(String[] splittedCommand) {
        return splittedCommand.length == 4 && Set.of(">", ">>").contains(splittedCommand[2]);
    }
}
