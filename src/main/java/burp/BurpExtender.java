package burp;

import top.oxff.controller.ContextMenuFactoryIml;
import top.oxff.models.Payload;
import top.oxff.ui.TabUI;

import java.awt.*;
import java.io.PrintWriter;
import java.util.*;

public class BurpExtender implements IBurpExtender, ITab, IExtensionStateListener {
    final static String NAME = "custom payloads";

    public static IBurpExtenderCallbacks burpExtenderCallbacks;

    public static IExtensionHelpers extensionHelpers;

    public static PrintWriter stdout;
    public static PrintWriter stderr;

    public final static Map<String, String> KVS = new HashMap<>();

    public final static Set<Integer> Tool_flags = new HashSet<>();

    public final static String KVS_SEP = "//__//";
    public final static String SEP = "//_//";

    private final static String KVS_EX_NAME = "KVS";
    private final static String Tool_FLag_EX_NAME = "TOOL_FLAG";

    static TabUI tabUI;

    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks burpExtenderCallbacks) {
        BurpExtender.burpExtenderCallbacks = burpExtenderCallbacks;

        burpExtenderCallbacks.setExtensionName(NAME);

        extensionHelpers = burpExtenderCallbacks.getHelpers();

        stdout = new PrintWriter(BurpExtender.burpExtenderCallbacks.getStdout(), true);
        stderr = new PrintWriter(BurpExtender.burpExtenderCallbacks.getStderr(), true);

        tabUI = new TabUI();

        ContextMenuFactoryIml contextMenuFactoryIml = new ContextMenuFactoryIml();

        BurpExtender.burpExtenderCallbacks.addSuiteTab(this);
        BurpExtender.burpExtenderCallbacks.registerContextMenuFactory(contextMenuFactoryIml);
        BurpExtender.burpExtenderCallbacks.registerExtensionStateListener(this);

//        loadExConfig();
    }

    @Override
    public void extensionUnloaded() {
        stdout.println("插件加载中...");
    }

    @Override
    public String getTabCaption() {
        return NAME;
    }

    @Override
    public Component getUiComponent() {
        return tabUI;
    }

    public static ArrayList<Payload> getPayloadArrayList() {
        return tabUI.getPayloadArrayList();
    }
}
