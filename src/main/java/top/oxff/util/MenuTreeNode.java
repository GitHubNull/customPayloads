package top.oxff.util;

import java.util.ArrayList;
import java.util.List;

public class MenuTreeNode {
    String name;
    List<MenuTreeNode> child;

    public MenuTreeNode(String name) {
        this.name = name;
        child = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuTreeNode> getChild() {
        return child;
    }

    public void setChild(List<MenuTreeNode> child) {
        this.child = child;
    }
}
