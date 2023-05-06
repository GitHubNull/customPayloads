package top.oxff.util;

import java.util.ArrayList;
import java.util.List;

public class JMenuItemNodeTree {
    JMenuItemNode rootMenuItem;

    public JMenuItemNodeTree(JMenuItemNode rootMenuItem) {
        this.rootMenuItem = rootMenuItem;
    }


    private static List<JMenuItemNode> createChild(String str, List<JMenuItemNode> children) {
        if (null == str || str.trim().isEmpty() || null == children) {
            return children;
        }

        for (JMenuItemNode child : children) {
            if (child.getMenuItem().getText().equals(str)) {
                return child.getChild();
            }
        }

        JMenuItemNode menuItem = new JMenuItemNode(str);
        children.add(menuItem);

        return menuItem.getChild();
    }

    public void genMenuItemTree(List<String> menuItemStrs) {
        for (String menuItemStr : menuItemStrs) {
            List<JMenuItemNode> eachList = null;
            for (String str : menuItemStr.split("\\.")) {
                if (null == eachList) {
                    eachList = rootMenuItem.getChild();
                }
                eachList = createChild(str, eachList);
            }
        }

    }

    public static void main(String[] args) {
        JMenuItemNode main = new JMenuItemNode("root");
        JMenuItemNodeTree menuItemTree = new JMenuItemNodeTree(main);


        List<String> menuStrs = new ArrayList<>();

        menuStrs.add("a0.b0.c0");
        menuStrs.add("a0.b0.c1");
        menuStrs.add("a0.b0.c2");
        menuStrs.add("a0.b1.c0");
        menuStrs.add("a0.b1.c1");
        menuStrs.add("a1.b1.c0");
        menuStrs.add("a1.b1.c1");
        menuStrs.add("a1.b2.c0");
        menuStrs.add("a1.b2.c1");
        menuStrs.add("a2");
        menuStrs.add("a3");
        menuStrs.add("a4");

        menuItemTree.genMenuItemTree(menuStrs);
        System.out.println("pause");
    }
}
