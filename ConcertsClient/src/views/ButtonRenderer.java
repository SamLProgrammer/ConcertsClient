package views;

import java.awt.Component;
import java.awt.Cursor;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ButtonRenderer extends JButton implements TableCellRenderer{
    /**
     *
     * @param color
     */
    public ButtonRenderer() {
    	setOpaque(false);
    	setCursor(new Cursor(Cursor.HAND_CURSOR));
    	setContentAreaFilled(false);
	}
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		setText((value == null) ? "":value.toString());		
		return this;
	}
}
