package task01.filesystem.util;

import java.util.Objects;
import java.util.Scanner;

public class FileSystemUtil {
    public static String readCommand() {
        return new Scanner(System.in).nextLine();
    }

    // TODO выражение в "*текст*" не брать
    // Команда в кавычках - обычный текст.
    public static String[] requestProcessing(String command) {
        boolean isCommandContainsText = command.contains(" \"") && command.contains("\" ");

        String text = null;
        if (isCommandContainsText) {
            text = getTextFromCommand(command);
            command = command.replace("\"" + text + "\"", "__text__");
        }

        String[] splittedCommand = command.split(" ");
        changeValueInArray(splittedCommand, "__text__", text);

        return splittedCommand;
    }

    private static String getTextFromCommand(String command) {
        return command.substring(command.indexOf("\"") + 1, command.lastIndexOf("\""));
    }

    private static void changeValueInArray(Object[] arr, Object oldValues, Object newValues) {
        for (int i = 0; i < arr.length; i++) {
            if (Objects.equals(arr[i], oldValues)) {
                arr[i] = newValues;
                break;
            }
        }
    }
}
