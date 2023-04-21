package top.oxff.ui;

import top.oxff.models.Payload;
import top.oxff.models.PayloadModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TabUI extends JPanel {
    JPanel north;
    static final JComboBox<String> searchColumn = new JComboBox<>(new String[]{"标签", "payload"});
    JTextField filterText;
    JButton searchBtn;

    JScrollPane center;
    JTable table;
    PayloadModel tableModel;


    JPanel south;
    JButton addBtn;
    JButton deleteBtn;
    JButton selectAllBtn;
    JButton selectNoneBtn;

    public TabUI() {
        setLayout(new BorderLayout());

        north = new JPanel(new FlowLayout(FlowLayout.LEFT));

        north.add(searchColumn);

        filterText = new JTextField(32);
        searchBtn = new JButton("搜索");

        north.add(filterText);
        north.add(searchBtn);

        add(north, BorderLayout.NORTH);

        table = new JTable();
        tableModel = new PayloadModel();
//        tableModel.
//        tableModel.setColumnIdentifiers(new String[]{"序号", "标签", "payload", "备注"});

        table.setModel(tableModel);

        center = new JScrollPane(table);

        add(center, BorderLayout.CENTER);


        south = new JPanel(new FlowLayout(FlowLayout.LEFT));

        addBtn = new JButton("新增");
        deleteBtn = new JButton("删除");
        selectAllBtn = new JButton("全选");
        selectNoneBtn = new JButton("全不选");

        south.add(addBtn);
        south.add(deleteBtn);
        south.add(selectAllBtn);
        south.add(selectNoneBtn);

        add(south, BorderLayout.SOUTH);

        initActionListening();
    }

    public void initActionListening() {
//        table.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
//
//                int row = table.getSelectedRow();
//                int col = table.getSelectedColumn();
//
//                if (e.getClickCount() != 2 || col != 3) {
//                    return;
//                }
//
//                Boolean oldEnable = (Boolean) tableModel.getValueAt(row, 3);
//                Boolean newEnable = oldEnable.equals(Boolean.TRUE) ? Boolean.FALSE : Boolean.TRUE;
//
//                tableModel.setValueAt(newEnable, row, col);
//            }
//        });

        addBtn.addActionListener(e -> {
            NewPayloadDialog newPayloadDialog = new NewPayloadDialog(TabUI.this);
            newPayloadDialog.setLocationRelativeTo(null);
            newPayloadDialog.setVisible(true);
        });

        deleteBtn.addActionListener(e -> {
            int[] selectRows = table.getSelectedRows();
            if (null == selectRows || 0 == selectRows.length) {
                return;
            }

            for (int selectRow : selectRows) {
                tableModel.deletePayloadById(selectRow);
            }
        });

        selectAllBtn.addActionListener(e -> table.selectAll());

        selectNoneBtn.addActionListener(e -> table.clearSelection());
    }

    public void addPayload(String tag, String payloadStr, Boolean enable, String comment) {
        tableModel.addPayload(tag, payloadStr, enable, comment);
    }

    public ArrayList<Payload> getPayloadArrayList() {
        return tableModel.getPayloadArrayList();
    }
}
