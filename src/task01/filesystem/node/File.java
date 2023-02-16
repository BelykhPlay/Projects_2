package task01.filesystem.node;

public class File extends Node {
    String text;

    public File(String name, Directory parent) {
        super(name, parent);
        this.text = "";
    }

    public void setText(String text) {
        this.text = text;
    }

    public void addText(String text) {
        this.text = this.text + " " + text;
    }

    public String getText() {
        return text;
    }
}
