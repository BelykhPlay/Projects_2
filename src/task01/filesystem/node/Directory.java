package task01.filesystem.node;

import java.util.HashMap;
import java.util.Map;

public class Directory extends Node {
    private HashMap<String, Node> children = new HashMap<>();
    public Directory(String name, Directory parent){
        super(name, parent);
    }

    public Node getChildren(String name) {
        return children.get(name);
    }

    public void addChildren(Node node) {
        if(!children.containsValue(node)) {
            children.put(node.getName(), node);
        }
    }

    public void deleteChildren(String name) {
        children.remove(name);
    }

    public Map<String, Node> getListOfChildren() {
        return this.children;
    }
}
