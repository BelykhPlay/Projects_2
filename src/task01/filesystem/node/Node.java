package task01.filesystem.node;

public abstract class Node {
    private String name;
    private final Directory parent;
    private String path;

    protected Node(String name, Directory parent) {
        this.name = name;
        this.parent = parent;
        this.path = setPath();
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    private String setPath() {
        if(this.parent == null) {
            return name;

        } else {
            return parent.getPath() + name + "/";
        }
    }
}
