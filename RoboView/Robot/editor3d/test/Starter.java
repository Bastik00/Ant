package de.hska.lat.robot.editor3d.test;

import javax.swing.DefaultDesktopManager;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import de.hska.lat.robot.editor3d.EditorMainFrame;

public class Starter extends JFrame
{
	public JDesktopPane desktopPane;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Starter()
	{
		super("Test Rahmen");
		
		desktopPane = new JDesktopPane();
		desktopPane.setDesktopManager(new DefaultDesktopManager());
		EditorMainFrame iframe = new EditorMainFrame();
		iframe.setLocation(5, 5);
		iframe.setSize(1600, 900);
		iframe.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
		desktopPane.add(iframe);
		iframe.setVisible(true);
		setContentPane(desktopPane);
		setSize(1800,1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String [] args){
		Starter starter = new Starter();
		starter.setVisible(true);
	}
}
