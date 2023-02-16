package task01.commands.util;

import task01.filesystem.node.Directory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandUtil {
    public static String[] getPathDirectories(String path) {
        List<String> pathElements = new ArrayList<>();

        for (String e : path.split("/")) {
            if (isFile(e)) {
                pathElements.clear();
                break;

            } else {
                pathElements.add(e);
            }
        }

        return pathElements.toArray(new String[0]);
    }

    public static String[] getPathToNode(String path) {
        String[] pathToNode = new String[2];

        pathToNode[0] = path.substring(0, path.lastIndexOf("/"));
        pathToNode[1] = path.substring(path.lastIndexOf("/") + 1);

        return pathToNode;
    }

    public static boolean isFile(String name) {
        return Arrays.asList(name.split("\\.")).contains("txt");
    }

    public static void printLocationPointer(Directory curDir) {
        System.out.print("[" + curDir.getPath() + "]-> ");
    }

    public static void printWelcome() {
        System.out.println("-->FileSystem by BebraIt");
    }
}
