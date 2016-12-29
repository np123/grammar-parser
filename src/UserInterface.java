package automata;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class UserInterface extends JPanel {

	final static int windowHeight;
	final static int windowWidth;
	
	private static Font font2 = new Font("TimesRoman", Font.BOLD, 20);
	
	static {	

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		windowWidth = (int) gd.getDefaultConfiguration().getBounds().getWidth();
		windowHeight = (int) gd.getDefaultConfiguration().getBounds().getHeight();
	}

	public UserInterface(){

	}
	
	public UserInterface(LayoutManager m){
		super(m);
	}
	
	public void paintComponent(Graphics g){
		labelMenu1(g);
		labelMenu2(g);
		labelTransition(g);
	}
	
	private void labelMenu1(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		String prompt = "Enter start state";
		g2d.setFont(font2);
		g2d.setColor(Color.WHITE);
		Rectangle2D textArea = font2.getStringBounds(prompt, g2d.getFontRenderContext());
		
		Rectangle menuPosition = Layout.getComponentPosition(0);
		
		g2d.drawString(prompt, (int) menuPosition.getX(), (int) menuPosition.getY() - (int) textArea.getHeight()/2);
	}
	
	private void labelMenu2(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		String prompt = "Enter end state";
		g2d.setFont(font2);
		g2d.setColor(Color.WHITE);
		Rectangle2D textArea = font2.getStringBounds(prompt, g2d.getFontRenderContext());
		
		Rectangle menuPosition = Layout.getComponentPosition(1);
		
		g2d.drawString(prompt, (int) menuPosition.getX(), (int) menuPosition.getY() - (int) textArea.getHeight()/2);
	}
	
	private void labelTransition(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		String prompt = "Transition character";
		g2d.setFont(font2);
		g2d.setColor(Color.WHITE);
		Rectangle2D textArea = font2.getStringBounds(prompt, g2d.getFontRenderContext());
		
		Rectangle menuPosition = Layout.getComponentPosition(2);
		
		g2d.drawString(prompt, (int) menuPosition.getX() - (int) textArea.getWidth()/3, (int) menuPosition.getY() - (int) textArea.getHeight()/2);
	}
	
}
