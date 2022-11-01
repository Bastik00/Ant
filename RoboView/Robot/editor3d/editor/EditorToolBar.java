package de.hska.lat.robot.editor3d.editor;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuBar;

public class EditorToolBar extends JMenuBar {

	private static final long serialVersionUID = 1L;

	public EditorToolBar(ActionListener listener) {
		JButton upButton = new JButton(new ImageIcon(getClass().getResource(
				"icons/arrow-up.png")));
		upButton.addActionListener(listener);
		upButton.setName("up");
		this.add(upButton);
		JButton dowButton = new JButton(new ImageIcon(getClass().getResource(
				"icons/arrow-down.png")));
		dowButton.setName("down");
		dowButton.addActionListener(listener);
		this.add(dowButton);
		JButton leftButton = new JButton(new ImageIcon(getClass().getResource(
				"icons/arrow-left.png")));
		leftButton.setName("left");
		leftButton.addActionListener(listener);
		this.add(leftButton);
		JButton rightButton = new JButton(new ImageIcon(getClass().getResource(
				"icons/arrow-right.png")));
		rightButton.setName("right");
		rightButton.addActionListener(listener);
		this.add(rightButton);
	}
}
