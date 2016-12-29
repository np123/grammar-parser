package automata;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Controller extends JPanel {
	
	private UserInterface UI;
	
	public Controller(){		
		this.UI = new UserInterface();		
		Layout layout = new Layout();
		UI.setLayout(layout);
		
		/*
		 * Creates the text field for entering alphabet
		 */
		JFormattedTextField characterBox = new JFormattedTextField();
		characterBox.setVisible(true);
		characterBox.setEditable(true);
		characterBox.setName("ALPHABET");
		characterBox.setBackground(Color.WHITE);
		characterBox.setMargin(new Insets(0,10,0,10));
		characterBox.setPreferredSize(new Dimension(100,30));
		
		/*
		 * Creates the text field for entering alphabet
		 */
		JFormattedTextField alphabetBox = new JFormattedTextField();
		alphabetBox.setVisible(true);
		alphabetBox.setEditable(true);
		alphabetBox.setName("ALPHABET");
		alphabetBox.setBackground(Color.WHITE);
		alphabetBox.setMargin(new Insets(0,10,0,10));
		alphabetBox.setPreferredSize(new Dimension(500,30));
		
		/*
		 * Creates the text field for entering test string
		 */
		JFormattedTextField inputBox = new JFormattedTextField();
		inputBox.setVisible(true);
		inputBox.setEditable(true);
		inputBox.setName("PARSE");
		inputBox.setBackground(Color.WHITE);
		inputBox.setMargin(new Insets(0,10,0,10));
		inputBox.setPreferredSize(new Dimension(500,30));
		
		
		/*
		 * Creates the text field for entering start state
		 */
		JFormattedTextField start = new JFormattedTextField();
		start.setVisible(true);
		start.setEditable(true);
		start.setName("START");
		start.setBackground(Color.WHITE);
		start.setMargin(new Insets(0,10,0,10));
		start.setPreferredSize(new Dimension(100,30));
		
		
		/*
		 * Creates the text field for entering start state
		 */
		JFormattedTextField end = new JFormattedTextField();
		end.setVisible(true);
		end.setEditable(true);
		end.setName("START");
		end.setBackground(Color.WHITE);
		end.setMargin(new Insets(0,10,0,10));
		end.setPreferredSize(new Dimension(100,30));
		
		
		/*JComboBox<String> start = new JComboBox<String>();
		start.setName("START");
		start.setOpaque(true);
		start.setVisible(true);
		start.setEditable(false);		
		start.setBackground(Color.WHITE);
		start.setPreferredSize(new Dimension(200,30));*/
		
		
		/*JComboBox<String> end = new JComboBox<String>();
		end.setName("END");
		end.setOpaque(true);
		end.setVisible(true);
		end.setEditable(false);		
		end.setBackground(Color.WHITE);
		end.setPreferredSize(new Dimension(200,30));*/
		
		
		UI.add(end, "END");
		UI.add(start, "START");
		UI.add(characterBox, "CHAR");
		
		
		//Sets window height and width based on device graphics settings
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();		
		int width = (int) gd.getDefaultConfiguration().getBounds().getWidth();
		int height = (int) gd.getDefaultConfiguration().getBounds().getHeight();
		
		//Creates new JFrame and sets state to visible
		JFrame window = new JFrame();
		window.add(UI);
		window.setVisible(true);
		window.setSize(width, height);		
		window.setBackground(Color.BLUE);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
	}
	
}
