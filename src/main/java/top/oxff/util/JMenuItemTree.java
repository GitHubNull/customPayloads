package top.oxff.util;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class JMenuItemTree {

    JMenu root;

    public JMenuItemTree(JMenu root) {
        this.root = root;
    }

    private static JMenuItem createChild(String str, JMenuItem children, int levelIndex, int levelCnt) {
        if (null == str || str.trim().isEmpty() || null == children) {
            return null;
        }

        for (Component child : children.getComponents()) {
            JMenuItem tmp = (JMenuItem) child;
            if (tmp.getText().equals(str)) {
                return tmp;
            }
        }

        if (levelIndex < levelCnt) {
            JMenu subMenuItem = new JMenu(str);
            children.add(subMenuItem);
            return subMenuItem;
        }

        JMenuItem subMenuItem = new JMenuItem(str);
        children.add(subMenuItem);

        return null;
    }

    public void genMenuItemTree(List<String> menuItemStrs) {
        for (String menuItemStr : menuItemStrs) {
            JMenuItem children = null;
            String[] strings = menuItemStr.split("\\.");
            int len = strings.length;
            for (int i = 0; i < len; i++) {
                String str = strings[i];
                if (null == children) {
                    children = root;
                }
                children = createChild(str, children, i, len - 1);
            }
        }
    }

}
