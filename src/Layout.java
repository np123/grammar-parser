package automata;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.LayoutManager2;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Layout implements LayoutManager2 {

	protected static LinkedList<Component> fixedComponents = new LinkedList<Component>();
	protected static LinkedList<Dimension> size = new LinkedList<Dimension>();	

	final static int windowHeight;
	final static int windowWidth;

	static {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		windowWidth = (int) gd.getDefaultConfiguration().getBounds().getWidth();
		windowHeight = (int) gd.getDefaultConfiguration().getBounds().getHeight();
	}

	public static Dimension getComponentSize(int index){
		return size.get(index);		
	}

	public static Rectangle getComponentPosition(int index){
		return fixedComponents.get(index).getBounds();
	}


	@Override
	public void addLayoutComponent(String name, Component comp) {		
		fixedComponents.add(comp);
		size.add(comp.getPreferredSize());
	}

	@Override
	public void layoutContainer(Container parent) {		

		
		Component menu1 = fixedComponents.get(0);
		Dimension sz1 = size.get(0);
		Component menu2 = fixedComponents.get(1);
		Dimension sz2 = size.get(1);
		Component character = fixedComponents.get(2);
		Dimension sz3 = size.get(2);
		
		menu1.setBounds(windowWidth/2 - sz1.width*2, windowHeight/2, sz1.width, sz1.height);
		menu2.setBounds(windowWidth/2 + sz2.width*2, windowHeight/2, sz2.width, sz2.height);
		character.setBounds(windowWidth/2, windowHeight/2, sz3.width, sz3.height);
		
		
		/*int totalWidth = 0;
		for (int i = 0; i < fixedComponents.size(); i++){
			Dimension dim = size.get(i);	
			totalWidth += dim.getWidth();
		}


		for (int i = 0; i < fixedComponents.size(); i++){
			Component current = fixedComponents.get(i);
			Dimension dim = size.get(i);

			if (current.getName() == null) continue;

			if (current.getName().equals("TEXT FIELD")) 
				current.setBounds(windowWidth/2 - totalWidth/2 + dim.width/2, windowHeight/3, dim.width,dim.height);
			else if (current.getName().equals("SUBMIT"))
				current.setBounds(windowWidth/2  + totalWidth/2 + dim.width/2, windowHeight/3, dim.width,dim.height);
			else if (current.getName().equals("OPTION"))
				current.setBounds(windowWidth/2 - totalWidth/2, windowHeight/4, dim.width,dim.height);	
			else if (current.getName().equals("MENU2"))
				current.setBounds(windowWidth/2 - totalWidth/2, windowHeight/2-2*dim.height, dim.width,dim.height);	
			else if (current.getName().equals("NEXT")) {				
				current.setBounds(windowWidth/2 + totalWidth/2 + dim.width/2, windowHeight/2 - dim.height, dim.width,dim.height);
			}
		}*/
	}

	@Override
	public Dimension minimumLayoutSize(Container parent) {
		return preferredLayoutSize(parent);
	}

	@Override
	public Dimension preferredLayoutSize(Container parent) {
		return new Dimension(windowWidth,windowHeight);
	}

	@Override
	public void removeLayoutComponent(Component comp) {
		if (fixedComponents.contains(comp)) fixedComponents.remove(comp);		
	}

	@Override
	public void addLayoutComponent(Component arg0, Object arg1) {
		if (arg1 == null) throw new IllegalArgumentException();			
		else if (arg1 instanceof String) addLayoutComponent((String) arg1, arg0);
		else throw new IllegalArgumentException("Invalid constraints specified: " + arg1);

	}

	@Override
	public float getLayoutAlignmentX(Container arg0) {
		return 0.5f;
	}

	@Override
	public float getLayoutAlignmentY(Container arg0) {
		return 0.5f;
	}

	@Override
	public void invalidateLayout(Container arg0) {}

	@Override
	public Dimension maximumLayoutSize(Container parent) {
		return preferredLayoutSize(parent);
	}

}
