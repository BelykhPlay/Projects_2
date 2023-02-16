package task01.commands.execcomand;

import task01.commands.Command;
import task01.filesystem.node.Directory;

// ls > *path*/*file*
// ls >> *path*/*file*
public class ListFilesCommand extends Command {
    public ListFilesCommand(String name) {
        super(name);
    }

    @Override
    public Directory exec(String[] splittedCommand, Directory mainDir, Directory curDir) {
        if (!isCorrectCommand(splittedCommand)) {
            System.out.println("Введена некорректная команда");
            return null;
        }

        String text = getChildrenToPrint(curDir);
        if (splittedCommand.length == 1) {
            System.out.println(text);

        } else {
            Command echo = new EchoCommands("");
            Command touch = new MakeFileCommand("");

            touch.exec(new String[]{"", splittedCommand[2]}, mainDir, curDir);
            echo.exec(new String[]{"", text, splittedCommand[1], splittedCommand[2]}, mainDir, curDir);
        }

        return null;
    }

    private String getChildrenToPrint(Directory directory) {
        StringBuilder sb = new StringBuilder();

        for (String name : directory.getListOfChildren().keySet()) {
            sb.append(name).append("  ");
        }

        return sb.toString();
    }

    @Override
    public boolean isCorrectCommand(String[] splittedCommand) {
        return splittedCommand.length == 1 || splittedCommand.length == 3;
    }
}
