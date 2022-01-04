package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.Actions;

public class PurchaseDialog extends JDialog{

	private JLabel purchaseAdviceLabel;
	private DCButton okButton;
	
	public PurchaseDialog(ActionListener listener) {
		initGUIProperties();
		locate();
		initComponents(listener);
	}
	
	private void initGUIProperties() {
		setIconImage(new ImageIcon(getClass().getResource("/img/spotlights.png")).getImage());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBackground(Color.GREEN);
		setLayout(new BorderLayout());
	}
	
	private void initComponents(ActionListener listener) {
		purchaseAdviceLabel = new JLabel("", SwingConstants.CENTER);
		purchaseAdviceLabel.setFont(new Font("Oswald", Font.BOLD, 20));
		okButton = new DCButton("#FFFFFF");
		okButton.setText("OK");
		okButton.setActionCommand(Actions.PURCHASE_DIALOG_OK_BUTTON.name());
		okButton.addActionListener(listener);
		okButton.setPreferredSize(new Dimension(getWidth()/4, getHeight()/10));
		JPanel buttonPanel = new JPanel(new GridBagLayout());
		buttonPanel.add(okButton);
		add(purchaseAdviceLabel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	private void locate() {
		int width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		setSize(width/4,height/4);
		setLocation(width/2 - getWidth()/2, height/2 - getHeight()/2);
	}
	
	public void setAdviceText(String advice) {
		purchaseAdviceLabel.setText(advice);
	}
	
}
