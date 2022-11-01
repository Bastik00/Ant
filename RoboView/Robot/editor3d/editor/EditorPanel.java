package de.hska.lat.robot.editor3d.editor;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.jogamp.opengl.util.FPSAnimator;

import de.hska.lat.color.RgbColor;
import de.hska.lat.robot.editor3d.editor.Editor.Axes;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresEventHandler;

/**
 * Panel to the left of the Palette, containing 3 Editors and one liveview field
 * 
 * @author Moritz Aleithe
 * 
 */
public class EditorPanel extends JPanel {
	private static final long serialVersionUID = 1895775210066679370L;
	private List<Editor> editors = new ArrayList<>();

	@SuppressWarnings("static-access")
	public EditorPanel(RgbColor color, FiguresEventHandler eventHandler,
			FPSAnimator animator, RgbColor backgroundColor) {

		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);

		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = gridBagConstraints.VERTICAL;

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridheight = 1;
		int[] frontPos = { 0, 0, 700 };
		int[] frontLookAt = { 0, 0, 0 };
		int[] frontUp = { 0, 1, 0 };

		EditorInfo frontEditorInfo = new EditorInfo(frontPos, frontLookAt,
				frontUp, "Front", Axes.Z);
		Editor frontEditor = new Editor(eventHandler, animator, frontEditorInfo);
		gridBagLayout.setConstraints(frontEditor, gridBagConstraints);
		add(frontEditor);
		editors.add(frontEditor);
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridheight = 1;
		int[] topPos = { 0, 700, 0 };
		int[] topLookAt = { 0, 0, 0 };
		int[] topUp = { 0, 0, -1 };
		EditorInfo topEditorInfo = new EditorInfo(topPos, topLookAt, topUp,
				"Top", Axes.Y);
		Editor topEditor = new Editor(eventHandler, animator, topEditorInfo);
		gridBagLayout.setConstraints(topEditor, gridBagConstraints);
		add(topEditor);
		editors.add(topEditor);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridheight = 1;
		int[] sidePos = { 700, 0, 0 };
		int[] sideLookAt = { 0, 0, 0 };
		int[] sideUp = { 0, 1, 0 };
		EditorInfo sideEditorInfo = new EditorInfo(sidePos, sideLookAt, sideUp,
				"(Right-hand) Side", Axes.X);
		Editor sideEditor = new Editor(eventHandler, animator, sideEditorInfo);
		gridBagLayout.setConstraints(sideEditor, gridBagConstraints);
		add(sideEditor);
		editors.add(sideEditor);
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridheight = 1;
		JPanel liveViewPanel = new JPanel();
		View3D liveView = new View3D(animator, eventHandler);
		liveViewPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "3D View"));
		liveViewPanel.add(liveView);
		gridBagLayout.setConstraints(liveViewPanel, gridBagConstraints);
		add(liveViewPanel);
	}

	public List<Editor> getEditors() {
		return editors;
	}

}
