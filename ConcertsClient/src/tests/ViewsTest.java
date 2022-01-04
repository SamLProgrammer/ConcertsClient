package tests;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.ClientFrame;

public class ViewsTest {

	public static void main(String[] args) {
		new ClientFrame(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
}
