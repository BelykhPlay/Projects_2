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

        Command command;
        Directory resultDir;

        String[] request = requestProcessing(readCommand());

        while (!request[0].equals("exit")) {
            command = commands.get(request[0]);

            if (command != null) {
                resultDir = command.exec(request, mainDir, curDir);

                if (resultDir != null) {
                    setCurDir(resultDir);

                }
            } else if (!request[0].equals("")) {
                System.out.println("Введена некорректная команда");
            }

            CommandUtil.printLocationPointer(curDir);
            request = requestProcessing(readCommand());
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
