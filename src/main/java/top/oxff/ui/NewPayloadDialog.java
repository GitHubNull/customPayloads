package top.oxff.ui;

import burp.BurpExtender;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class NewPayloadDialog extends JFrame {
    JPanel north;

    JLabel tagLabel;
    JTextField tag;
    JCheckBox enable;


    JSplitPane center;

    JScrollPane payloadTextContainer;
    JTextPane payloadTextPanel;


    JScrollPane commentContainer;
    JTextPane commentPanel;

    JPanel south;
    JButton okBtn;
    JButton cancelBtn;

    TabUI tabUI;

    public NewPayloadDialog(TabUI tabUI) throws HeadlessException {
        this.tabUI = tabUI;
        setLayout(new BorderLayout());
        setTitle("新增payload");

        north = new JPanel(new FlowLayout(FlowLayout.LEFT));

        tagLabel = new JLabel("标签");
        tag = new JTextField(32);
        enable = new JCheckBox("是否生效");

        north.add(tagLabel);
        north.add(tag);
        north.add(enable);


        add(north, BorderLayout.NORTH);

        payloadTextPanel = new JTextPane();
        payloadTextContainer = new JScrollPane(payloadTextPanel);
        payloadTextContainer.setViewportBorder(new TitledBorder("payload"));

        commentPanel = new JTextPane();
        commentContainer = new JScrollPane(commentPanel);
        commentContainer.setViewportBorder(new TitledBorder("备注"));


        center = new JSplitPane(JSplitPane.VERTICAL_SPLIT, payloadTextContainer, commentContainer);

        add(center, BorderLayout.CENTER);


        south = new JPanel(new FlowLayout(FlowLayout.LEFT));

        okBtn = new JButton("确定");
        cancelBtn = new JButton("取消");

        south.add(okBtn);
        south.add(cancelBtn);

        add(south, BorderLayout.SOUTH);


//        setMaximumSize(getPreferredSize());
//        setLocale(null);
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initActionListening();
    }

    public void initActionListening() {
        okBtn.addActionListener(e -> {
            String tagStr = tag.getText();
            String payloadsStr = payloadTextPanel.getText();
            Boolean enableB = enable.isSelected();
            String commentStr = commentPanel.getText();

            if (null == tagStr || null == payloadsStr || tagStr.trim().equals("") || payloadsStr.trim().equals("")) {
                dispose();
            }
            BurpExtender.stdout.println("NewPayloadDialog --->99");
            SwingUtilities.invokeLater(() -> tabUI.addPayload(tagStr, payloadsStr, enableB, commentStr));
            dispose();
        });

        cancelBtn.addActionListener(e -> dispose());
    }

//    public static void main(String[] args) {
//     NewPayloadDialog newPayloadDialog = new NewPayloadDialog();
//     newPayloadDialog.setVisible(true);
//    }
}
