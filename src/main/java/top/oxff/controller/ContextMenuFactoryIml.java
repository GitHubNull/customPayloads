package top.oxff.controller;

import burp.BurpExtender;
import burp.IContextMenuFactory;
import burp.IContextMenuInvocation;
import top.oxff.models.Payload;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContextMenuFactoryIml implements IContextMenuFactory {
    Set<Byte> contextSet;

    public ContextMenuFactoryIml() {
        contextSet = new HashSet<>();

        contextSet.add(IContextMenuInvocation.CONTEXT_MESSAGE_EDITOR_REQUEST);
//        contextSet.add(IContextMenuInvocation.CONTEXT_MESSAGE_VIEWER_REQUEST);

    }

    @Override
    public List<JMenuItem> createMenuItems(IContextMenuInvocation contextMenuInvocation) {
        List<JMenuItem> menuItems = new ArrayList<>();
        int[] selection = contextMenuInvocation.getSelectionBounds();
        if (!contextSet.contains(contextMenuInvocation.getInvocationContext()) || null == selection || 0 >= selection.length) {
            return menuItems;
        }

        ArrayList<Payload> payloads = BurpExtender.getPayloadArrayList();
        if (0 == payloads.size()) {
            return menuItems;
        }


        return menuItems;
    }
}
