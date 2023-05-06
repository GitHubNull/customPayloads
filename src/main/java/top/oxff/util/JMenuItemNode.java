package top.oxff.util;

import javax.swing.*;
import java.util.List;

public class JMenuItemNode {
    JMenuItem menuItem;
    List<JMenuItemNode> child;

    public JMenuItemNode(String menuItemStr) {
        this.menuItem = new JMenuItem(menuItemStr);
    }

    public JMenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(JMenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public List<JMenuItemNode> getChild() {
        return child;
    }

    public void setChild(List<JMenuItemNode> child) {
        this.child = child;
    }
}
