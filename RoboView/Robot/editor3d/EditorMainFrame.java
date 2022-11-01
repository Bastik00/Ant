package de.hska.lat.robot.editor3d;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.jogamp.opengl.util.FPSAnimator;

import de.hska.lat.color.RgbColor;
import de.hska.lat.robot.Robot;
import de.hska.lat.robot.abstractRobot.AbstractRobot;
import de.hska.lat.robot.displayFrame.DisplayFrame;
import de.hska.lat.robot.editor3d.editor.EditorPanel;
import de.hska.lat.robot.editor3d.palette.Palette;
import de.hska.lat.robot.morphology.appearance.Editor3D.entities.Figures;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresEventHandler;

/**
 * Main Window of the Editor
 * 
 * @author moritz
 * 
 */

public class EditorMainFrame extends DisplayFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private RgbColor selectedColor;
	private FPSAnimator animator;
	private Figures figures;
	private FiguresEventHandler eventHandler;

	public EditorMainFrame() {
		super("3D Editor Neu", true, true, true, true);

		figures = new Figures();
		eventHandler = new FiguresEventHandler(figures);
		animator = new FPSAnimator(30);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		EditorPanel editorPanel = new EditorPanel(selectedColor, eventHandler,
				animator, null);
		Editor3DMenu editor3DMenu = new Editor3DMenu(eventHandler,
				getContentPane(), editorPanel.getEditors());

		setJMenuBar(editor3DMenu);
		setVisible(true);
		setSize(1210, 815);
		contentPane = new JPanel();
		Palette palette = new Palette(eventHandler);
		contentPane.add(palette);
		getContentPane().add(contentPane, BorderLayout.WEST);

		add(editorPanel, BorderLayout.CENTER);
	}

	@Override
	public boolean setRobot(AbstractRobot<?, ?, ?> robot) {
		eventHandler.setAppearanceModel(((Robot) robot).getAppearanceModel());
		return true;
	}

	public static void main(String[] args) {
		EditorMainFrame frame = new EditorMainFrame();
		frame.setVisible(true);
	}
}
