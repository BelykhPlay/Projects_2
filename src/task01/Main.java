package task01;

import task01.commands.*;
import task01.commands.execcomand.*;
import task01.filesystem.FileSystem;

public class Main {
    public static void main(String[] args) {
        Command[] commands = new Command[]{
                new ListFilesCommand("ls"),
                new ChangeDirectoryCommand("cd"),
                new MakeDirectoryCommand("mkdir"),
                new MakeFileCommand("touch"),
                new RemoveNodeCommand("rm"),
                new TreeCommand("tree"),
                new EchoCommands("echo"),
                new CatCommand("cat")};

        FileSystem fileSystem = new FileSystem("", commands);
        fileSystem.start();
    }
}
