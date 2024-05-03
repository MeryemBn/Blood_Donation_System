package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CustomComboBoxRenderer extends DefaultListCellRenderer {
    private Color selectionBackgroundColor;

    public CustomComboBoxRenderer(Color selectionBackgroundColor) {
        this.selectionBackgroundColor = selectionBackgroundColor;
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (isSelected) {
            component.setBackground(selectionBackgroundColor);
        } else {
            component.setBackground(list.getBackground());
        }

        return component;
    }
}

