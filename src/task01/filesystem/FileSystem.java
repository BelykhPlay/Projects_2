package task01.filesystem;

import task01.commands.*;
import task01.commands.util.CommandUtil;
import task01.filesystem.node.Directory;

import java.util.HashMap;
import java.util.Map;

import static task01.commands.util.CommandUtil.printWelcome;
import static task01.filesystem.util.FileSystemUtil.*;

public class FileSystem {
    private final Directory mainDir;
    private Directory curDir;

    private final Map<String, Command> commands = new HashMap<>();

    public FileSystem(String nameMainDir, Command[] commands) {
        this.mainDir = new Directory(nameMainDir, null);
        this.curDir = this.mainDir;
        setCommands(commands);
    }

    public void start() {
        printWelcome();
        CommandUtil.printLocationPointer(curDir);

        Directory resultDir;

        String[] command = getSplittedCommand(readCommand());
        while (!command[0].equals("exit")) {
            if (commands.containsKey(command[0])) {
                resultDir = commands.get(command[0]).exec(command, mainDir, curDir);

                if(resultDir != null) {
                    setCurDir(resultDir);
                }
            } else {
                System.out.println("Введите корректную команду");
            }

            CommandUtil.printLocationPointer(curDir);
            command = getSplittedCommand(readCommand());
        }
    }

    private void setCommands(Command[] commands) {
        for (Command e : commands) {
            this.commands.put(e.getName(), e);
        }
    }

    private void setCurDir(Directory curDir) {
        this.curDir = curDir;
    }
}
