package models;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class BloodListModel extends AbstractTableModel {
    private List<BloodGroupList> data;
    private String[] columnNames = {"ID", "Blood Group", "Quantity"};

    public BloodListModel(List<BloodGroupList> data) {
        this.data = data != null ? new ArrayList<>(data) : new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        BloodGroupList bloodGroup = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return bloodGroup.getId();
            case 1:
                return bloodGroup.getBloodGroup();
            case 2:
                return bloodGroup.getQuantity();
            default:
                return null;
        }
    }

    public BloodGroupList getBloodGroupAt(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < data.size()) {
            return data.get(rowIndex);
        }
        return null;
    }
}
