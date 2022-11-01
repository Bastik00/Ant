package de.hska.lat.robot.morphology.appearance.Editor3D;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class VertexTableButtonRenderer implements TableCellRenderer {
	JButton button = new JButton();

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        button.setText("x");
        return button;
    }
}
