package task01.commands.execcomand;

import task01.commands.Command;
import task01.filesystem.node.Directory;
import task01.filesystem.node.File;
import static task01.commands.util.CommandUtil.getPathToNode;

// cat *file*
// cat *originalFile* (>/>>) *copyfile*
public class CatCommand extends Command {
    public CatCommand(String name) {
        super(name);
    }

    @Override
    public Directory exec(String[] splittedCommand, Directory mainDir, Directory curDir) {
        if (!isCorrectCommand(splittedCommand)) {
            return null;
        }

        if (splittedCommand.length == 2) {
            printFileContents(getFile(splittedCommand[1], mainDir, curDir));
            return null;

        } else {
            copyFileContents(splittedCommand, mainDir, curDir);
        }


        return null;
    }

    private File getFile(String path, Directory mainDir, Directory curDir) {
        Command cd = new ChangeDirectoryCommand("");

        String[] pathToFile;
        File file;

        if (!path.contains("/")) {
            file = (File) curDir.getChildren(path);

        } else {
            pathToFile = getPathToNode(path);
            curDir = cd.exec(new String[]{"", pathToFile[0]}, mainDir, curDir);

            file = (File) curDir.getChildren(pathToFile[1]);
        }

        return file;
    }

    private void printFileContents(File file) {
        if (file != null) {
            System.out.println("Имя файла: " + file.getName());
            System.out.println(file.getText());

        } else {
            System.out.println("Файл не найден");
        }
    }

    private void copyFileContents(String[] splittedCommand, Directory mainDir, Directory curDir) {
        Command echo = new EchoCommands("");

        File originalFile = getFile(splittedCommand[1], mainDir, curDir);

        if (originalFile == null) {
            System.out.println("Оригинальный файл не найден");

        } else {
            echo.exec(new String[]{"", originalFile.getText(), splittedCommand[2], splittedCommand[3]}, mainDir, curDir);
        }
    }

    @Override
    public boolean isCorrectCommand(String[] splittedCommand) {
        return splittedCommand.length == 2 || splittedCommand.length == 4;
    }
}
