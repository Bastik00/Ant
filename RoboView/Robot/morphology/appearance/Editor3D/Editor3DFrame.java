package de.hska.lat.robot.morphology.appearance.Editor3D;

import javax.swing.JDesktopPane;
import javax.swing.WindowConstants;

import de.hska.lat.robot.Robot;
import de.hska.lat.robot.abstractRobot.AbstractRobot;
import de.hska.lat.robot.displayFrame.DisplayFrame;
import de.hska.lat.robot.morphology.appearance.Editor3D.entities.Figures;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresEventHandler;
/*
 * Generates the View for 3D-Editor
 * 
 * @author Jonas Knopf
 */
public class Editor3DFrame extends DisplayFrame  {
	

	private static final long serialVersionUID = 5083085764139448164L;
	private JDesktopPane editorPane;
	private Editor3DView view3D;
	private MainMenu menu;
	private Figures figures;
	private FiguresEventHandler eventHandler;
	
	public Editor3DFrame() {
		super("3D Editor", true, true, true, true);
		editorPane = new JDesktopPane();
		figures = new Figures();
		
		eventHandler = new FiguresEventHandler(figures);
		
		view3D = new Editor3DView(eventHandler);
		
		editorPane.setLayout(null);
		editorPane.setSize(200,200);
		setSize(1300,700);
		setContentPane(editorPane);
		editorPane.add(view3D);

		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE );
    	
		setVisible(true);	
	}

	@Override	
	public boolean setRobot(AbstractRobot<?,?,?> robot)
	{
		eventHandler.setAppearanceModel(((Robot) robot).getAppearanceModel());
		menu = new MainMenu(eventHandler);

		editorPane.add(menu);

		return true;
	}
}