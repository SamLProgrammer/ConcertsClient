package views;

import javax.swing.JButton;

public class GridButton extends JButton{
	
	private String code;

	public GridButton(String code) {
		this.code = code;
		initComponents();
	}
	
	private void initComponents() {
		setText(code);
	}
}
