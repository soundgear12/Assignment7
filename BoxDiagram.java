package week5;

import acm.program.GraphicsProgram;
import java.awt.event.*;
import javax.swing.*;
import acm.gui.TableLayout;
import acm.program.*;
import java.awt.*;
import acm.graphics.*;
import java.util.*;


public class BoxDiagram extends GraphicsProgram {

	public void init() {

		boxContains = new HashMap<String, GObject>();
		
		addButtons();
		addActionListeners();
		addMouseListeners();

	}
	
	public void addButtons() {
		nameField = new JTextField(20);
		nameField.setActionCommand("name");
		nameField.addActionListener(this);
		
		add(new JLabel("Name"), SOUTH);
		add(nameField, SOUTH);
		
		addButton = new JButton ("add");
		add(addButton, SOUTH);
		
		removeButton = new JButton ("remove");
		add(removeButton, SOUTH);
		
		clearButton = new JButton ("clear");
		add(clearButton, SOUTH);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		if (cmd.equals("name") || cmd.equals("add") ) {
			createBox(nameField.getText());
		} else if (cmd.equals("remove")) {
			removeBox(nameField.getText());
		} else if
		(cmd.equals("clear")) {
			removeAllBoxes();
		}
	}	
		
	
	
	

	private void createBox(String boxName) {
		box = new GCompound();
		rect = new GRect(BOX_WIDTH, BOX_HEIGHT);
		label = new GLabel(boxName);
		double startingWidth = -BOX_WIDTH / 2;
		double startingHeight = - BOX_HEIGHT / 2;
		
		
		box.add(rect, startingWidth, startingHeight);
		box.add(label, -label.getWidth() / 2, label.getHeight() / 2);
		add(box , getWidth() /2, getHeight() /2);
		boxContains.put(boxName, box);

	}
	
	private void removeBox(String boxName) {
		gobj = boxContains.get(boxName);
		
		if (gobj != null) {
			remove(gobj);
		}
	}
	
	/*private void removeAllBoxes() {
		Iterator<String> iterator = boxContains.keySet().iterator();
		
		while (iterator.hasNext()); {
			removeBox(iterator.next());
		}
		boxContains.clear();
	}*/
	
	private void removeAllBoxes() {
		removeAll();
	}

	public void mousePressed(MouseEvent e) {
		lastX = e.getX();
		lastY = e.getY();
		gobj = getElementAt(lastX, lastY);
		
	}
	

	public void mouseDragged(MouseEvent e) {
		if (gobj != null) {
			gobj.move(e.getX() - lastX, e.getY() - lastY);
			lastX = e.getX();
			lastY = e.getY();
		}
	}

	public void mouseClicked(MouseEvent e) {
		if (gobj != null) gobj.sendToFront();
	}
	
	
	private static final double BOX_WIDTH = 120;
	private static final double BOX_HEIGHT = 50;
	
	
	private HashMap<String, GObject> boxContains;
	private GCompound box = new GCompound();
	private GRect rect;
	private GLabel label;
	private JTextField nameField;
	private JButton addButton;
	private JButton removeButton;
	private JButton clearButton;
	private GObject gobj;
	private double lastX;
	private double lastY;
}
