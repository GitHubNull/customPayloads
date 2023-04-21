package top.oxff.util;

import burp.BurpExtender;
import burp.IContextMenuInvocation;
import burp.IHttpRequestResponse;
import top.oxff.models.Payload;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class MultiMenuSupport {
    IContextMenuInvocation contextMenuInvocation;
    ArrayList<Payload> payloads;
    List<JMenuItem> menuItems;


    public MultiMenuSupport(IContextMenuInvocation contextMenuInvocation, ArrayList<Payload> payloads, List<JMenuItem> menuItems) {
        this.contextMenuInvocation = contextMenuInvocation;
        this.payloads = payloads;
        this.menuItems = menuItems;
    }

    public void genMenuItems() {
        IHttpRequestResponse[] httpRequestResponses = contextMenuInvocation.getSelectedMessages();
        if (null == httpRequestResponses || 0 == httpRequestResponses.length) {
            return;
        }

        byte[] requestBytes = httpRequestResponses[0].getRequest();
        if (null == requestBytes || 0 == requestBytes.length) {
            return;
        }

        int[] selection = contextMenuInvocation.getSelectionBounds();

        byte[] leftBytes = Arrays.copyOfRange(requestBytes, 0, selection[0]);
        byte[] rightBytes = null;
        if (1 == selection.length) {
            rightBytes = Arrays.copyOfRange(requestBytes, selection[0], requestBytes.length);
        } else {
            rightBytes = Arrays.copyOfRange(requestBytes, selection[1], requestBytes.length);
        }

        Set<String> levelOneMenuItemsSet = new HashSet<>();
        Set<String> levelTwoMenuItemsSet = new HashSet<>();
        Set<String> levelThreeMenuItemsSet = new HashSet<>();

//        Set<String> tags = new HashSet<>();

        for (Payload payload : payloads) {
            String tag = payload.getTag();
            String[] items = null;
            if (tag.contains(".")) {
                items = payload.getTag().split(".", 3);
            } else {
                items = new String[]{tag};
            }


            levelOneMenuItemsSet.add(items[0]);

            if (2 > items.length) {
                continue;
            }
            levelTwoMenuItemsSet.add(items[0] + "." + items[1]);

            if (3 > items.length) {
                continue;
            }

            levelTwoMenuItemsSet.add(tag);

            // a.b0
            // a.b1
            // a.b2
            // a.b3
            // a1.b0.c0
            // a1.b0.c1

//            tags.add(payload.getTag());
        }


        for (String s : levelOneMenuItemsSet) {
            if (levelTwoMenuItemsSet.contains(s)) {
                for (String s1 : levelTwoMenuItemsSet) {
                    JMenuItem levelOneMenu = new JMenuItem(s);
                    menuItems.add(levelOneMenu);

                    if (levelThreeMenuItemsSet.contains(s1)) {
                        JMenuItem leveTwoMenu = new JMenuItem(s1);
                        levelOneMenu.add(leveTwoMenu);


                        for (String s2 : levelThreeMenuItemsSet) {

                        }


                    } else {

                        JMenuItem levelOneSubMenuItem = new JMenuItem(s1);
                        levelOneMenu.add(levelOneSubMenuItem);
                        byte[] finalRightBytes = rightBytes;

                        levelOneSubMenuItem.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                for (Payload payload : payloads) {
                                    if (payload.getTag().equals(s1)) {
                                        String payloadStr = payload.getPayloadStr();
                                        byte[] payloadBytes = BurpExtender.extensionHelpers.stringToBytes(payloadStr);
                                        byte[] finallyBytes = byteMergerAll(leftBytes, payloadBytes, finalRightBytes);

                                        httpRequestResponses[0].setRequest(finallyBytes);
                                        break;
                                    }
                                }
                            }
                        });

                    }
                }


            } else {
                JMenuItem levelOneMenuItem = new JMenuItem(s);
                byte[] finalRightBytes = rightBytes;
                levelOneMenuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (Payload payload : payloads) {
                            if (payload.getTag().equals(s)) {
                                String payloadStr = payload.getPayloadStr();
                                byte[] payloadBytes = BurpExtender.extensionHelpers.stringToBytes(payloadStr);
                                byte[] finallyBytes = byteMergerAll(leftBytes, payloadBytes, finalRightBytes);

                                httpRequestResponses[0].setRequest(finallyBytes);
                                break;
                            }
                        }
                    }
                });
                menuItems.add(levelOneMenuItem);

            }

        }


    }

    private static byte[] byteMergerAll(byte[]... values) {
        int length_byte = 0;
        for (byte[] value : values) {
            length_byte += value.length;
        }
        byte[] all_byte = new byte[length_byte];
        int countLength = 0;
        for (byte[] b : values) {
            System.arraycopy(b, 0, all_byte, countLength, b.length);
            countLength += b.length;
        }
        return all_byte;
    }
}
