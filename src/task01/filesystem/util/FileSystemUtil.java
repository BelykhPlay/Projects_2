package task01.filesystem.util;

import java.util.Scanner;

public class FileSystemUtil {
    public static String readCommand() {
        return new Scanner(System.in).nextLine();
    }

    public static String[] getSplittedCommand(String command){
        return command.split(" ");
    }
}
