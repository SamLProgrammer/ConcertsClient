 package views;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class RoundJButton extends JButton{
    private Shape shape;
    private static final int radius = 10;
    private boolean reserved;
    private boolean available;
    private String code;
    
    public RoundJButton(String colorCode, boolean available, String code) {
    	this.code = code;
    	this.available = available;
    	initProperties(colorCode);
    	initListeners();
    }
    
    private void initListeners() {
    	addMouseListener(new MouseAdapter(){ 
            public void mouseClicked(MouseEvent me) {
            	if(available) {
            		if(!reserved) {
            			reserved = true;
            			setBackground(Color.decode("#5454E1"));
            		}
            		else {
            			reserved = false;
            			setBackground(Color.decode("#99A802"));
            		}
            	}
            } 
          }); 
    }
    
    private void initProperties(String colorCode) {
    	setBackground(Color.decode(colorCode));
    	String string = "";
    	if(available) {
    		string = "FREE";
    	}
    	else {
    		string = "TAKEN";
    	}
    	setText("<html><p style=\"text-align: center;\">" + code + "<br>" + string + "</br></p></html>");
    	setHorizontalTextPosition(SwingConstants.CENTER);
    	setVerticalTextPosition(SwingConstants.BOTTOM);
    	setFocusPainted(false);
    	setMargin(new Insets(0, 0, 0, 0));
    	setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    protected void paintComponent(Graphics g) {
         g.setColor(getBackground());
         g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, radius, radius);
         super.paintComponent(g);
    }
    
    protected void paintBorder(Graphics g) {
    	Graphics2D g2 = (Graphics2D)g;
    	g2.setStroke(new BasicStroke(3));
         g2.setColor(Color.decode("#696060"));
         g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, radius, radius);
    }
    public boolean contains(int x, int y) {
         if (shape == null || !shape.getBounds().equals(getBounds())) {
             shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, radius, radius);
         }
         return shape.contains(x, y);
    }
    
	public boolean isReserved() {
		return reserved;
	}
	
	public void reserve() {
		reserved = true;
		setBackground(Color.decode("#2c5871"));
	}
	
	public void dismiss() {
		reserved = false;
		setBackground(Color.decode(""));
	}
	
	public String getCode() {
		return code;
	}
	
	public boolean isAvailable() {
		return available;
	}
}