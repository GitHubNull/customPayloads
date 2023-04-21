package top.oxff.models;

import burp.BurpExtender;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class PayloadModel extends AbstractTableModel {
    String[] columnNames = new String[]{"序号", "标签", "payload", "生效/失效", "备注"};
    ArrayList<Payload> payloadArrayList = new ArrayList<>();


    @Override
    public int getRowCount() {
        return payloadArrayList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (0 == payloadArrayList.size() || rowIndex < 0 || columnIndex < 0 || rowIndex >= payloadArrayList.size() || columnIndex >= columnNames.length) {
            return null;
        }
        Payload payload = payloadArrayList.get(rowIndex);
        if (null == payload) {
            return null;
        }

        switch (columnIndex) {
            case 0:
                return payload.getId();
            case 1:
                return payload.getTag();
            case 2:
                return payload.getPayloadStr();
            case 3:
                return payload.getEnable();
            case 4:
                return payload.getComment();
            default:
                return null;
        }
    }

    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex != 0;
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
            case 2:
            case 4:
                return String.class;
            case 3:
                return Boolean.class;
            default:
                return null;
        }

    }

    public synchronized void addPayload(Payload payload) {
        if (null == payload) {
            return;
        }

        payloadArrayList.add(payload);
    }

    public synchronized void addPayload(String tag, String payloadStr, Boolean enable, String comment) {
        int id = payloadArrayList.size();
        Payload payload = new Payload(id, tag, payloadStr, enable, comment);
        SwingUtilities.invokeLater(() -> {
            payloadArrayList.add(payload);
            fireTableRowsInserted(id, id);
        });
    }

    public synchronized void addPayload(int id, String tag, String payloadStr, Boolean enable, String comment) {
        Payload payload = new Payload(id, tag, payloadStr, enable, comment);
        SwingUtilities.invokeLater(() -> {
            payloadArrayList.add(payload);
            fireTableRowsInserted(id, id);
        });
    }

    public Payload getPayloadById(int id) {
        if (id < 0 || id >= payloadArrayList.size()) {
            return null;
        }
        return payloadArrayList.get(id);
    }

    public ArrayList<Payload> getPayloadArrayListByTag(String tag) {
        ArrayList<Payload> payloads = new ArrayList<>();
        for (Payload payload : payloadArrayList) {
            if (payload.getTag().equals(tag)) {
                payloads.add(payload);
            }
        }

        return payloads;
    }

    public String getPayloadStrByTag(String tag) {
        for (Payload payload : payloadArrayList) {
            if (payload.getTag().equals(tag)) {
                return payload.getPayloadStr();
            }
        }
        return null;
    }

    public ArrayList<Payload> getPayloadArrayList() {
        return payloadArrayList;
    }

    public synchronized void deletePayloadById(int id) {
        if (0 > id || payloadArrayList.size() < id) {
            BurpExtender.stdout.println("deletePayloadById 136, id: " + id);
            return;
        }
        SwingUtilities.invokeLater(() -> {
            payloadArrayList.remove(id);
            fireTableRowsDeleted(id, id);
        });
    }

    @Override
    public void setValueAt(Object obj, int row, int col) {
        if (null == obj || 0 > row || payloadArrayList.size() < row || 0 >= col || columnNames.length < col) {
            return;
        }

        Payload payload = payloadArrayList.get(row);
        switch (col) {
            case 1:
                SwingUtilities.invokeLater(() -> {
                    payload.setTag((String) obj);
                    fireTableCellUpdated(row, col);
                });
                break;
            case 2:
                SwingUtilities.invokeLater(() -> {
                    payload.setPayloadStr((String) obj);
                    fireTableCellUpdated(row, col);
                });
                break;
            case 3:
                SwingUtilities.invokeLater(() -> {
                    payload.setEnable((Boolean) obj);
                    fireTableCellUpdated(row, col);
                });
                break;
            case 4:
                SwingUtilities.invokeLater(() -> {
                    payload.setComment((String) obj);
                    fireTableCellUpdated(row, col);
                });
                break;
            default:
                break;


        }
    }
}
