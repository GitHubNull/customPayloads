package top.oxff.util;

import java.util.ArrayList;
import java.util.List;

public class MenuTree {

    private static List<MenuTreeNode> createChild(String str, List<MenuTreeNode> children) {
        if (null == str || str.trim().isEmpty() || null == children) {
            return children;
        }

        for (MenuTreeNode child : children) {
            if (child.getName().equals(str)) {
                return child.getChild();
            }
        }

        MenuTreeNode menuTreeNode = new MenuTreeNode(str);
        children.add(menuTreeNode);

        return menuTreeNode.child;
    }

    public static void main(String[] args) {
        List<String> rootStrs = new ArrayList<>();

        rootStrs.add("a0.b0.c0");
        rootStrs.add("a0.b0.c1");
        rootStrs.add("a0.b0.c2");
        rootStrs.add("a0.b1.c0");
        rootStrs.add("a0.b1.c1");
        rootStrs.add("a1.b1.c0");
        rootStrs.add("a1.b1.c1");
        rootStrs.add("a1.b2.c0");
        rootStrs.add("a1.b2.c1");
        rootStrs.add("a2");
        rootStrs.add("a3");
        rootStrs.add("a4");

        MenuTreeNode root = new MenuTreeNode("root");

        for (String rootStr : rootStrs) {
            List<MenuTreeNode> eachList = null;
            for (String str : rootStr.split("\\.")) {
                System.out.println("str: " + str);
                if (null == eachList) {
                    eachList = root.getChild();
                }
                eachList = createChild(str, eachList);
            }
        }


    }
}
