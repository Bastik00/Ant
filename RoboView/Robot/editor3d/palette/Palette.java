package de.hska.lat.robot.editor3d.palette;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import de.hska.lat.color.RgbColor;
import de.hska.lat.robot.morphology.appearance.Editor3D.NewMesh;
import de.hska.lat.robot.morphology.appearance.Editor3D.WizardCuboid;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresEvent;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresEventHandler;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresListener;

public class Palette extends JPanel implements ActionListener, FiguresListener {

	private static final long serialVersionUID = 1L;
	private HSVChooser hsvChooser;
	private JButton triangleFromLineButton;
	private JButton triangleSplitButton;
	private JButton triangleButton;
	private JButton cuboidButton;
	private JButton handButton;

	private FiguresEventHandler eventHandler;

	public Palette(FiguresEventHandler eventHandler) {

		setLayout(new GridLayout(2, 1));
		setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Palette"));
		JPanel buttonPanel = new JPanel();
		this.eventHandler = eventHandler;
		eventHandler.addFiguresListener(this);
		eventHandler.setTool(Tool.HAND);

		triangleFromLineButton = new JButton();
		triangleFromLineButton.setToolTipText("Draw new Triangle fom a Line");
		triangleFromLineButton.setIcon(new ImageIcon(getClass().getResource(
				"img/triangleFromLine.png")));
		triangleFromLineButton.setName("triangle");
		triangleFromLineButton.addActionListener(this);
		buttonPanel.add(triangleFromLineButton);

		triangleSplitButton = new JButton();
		triangleSplitButton
				.setToolTipText("Split Triangle at clicked position");
		triangleSplitButton.setIcon(new ImageIcon(getClass().getResource(
				"img/splitTriangle.png")));
		triangleSplitButton.setName("splitTriangle");
		triangleSplitButton.addActionListener(this);
		buttonPanel.add(triangleSplitButton);

		triangleButton = new JButton();
		triangleButton.setToolTipText("Draw new Triangle using input Mask");
		triangleButton.setIcon(new ImageIcon(getClass().getResource(
				"img/triangle.png")));
		triangleButton.setName("triangleWithGUI");
		triangleButton.addActionListener(this);
		triangleButton.setEnabled(false);
		buttonPanel.add(triangleButton);

		cuboidButton = new JButton();
		cuboidButton.setToolTipText("Draw new cuboid");
		cuboidButton.setIcon(new ImageIcon(getClass().getResource(
				"img/cube.png")));
		cuboidButton.setName("cuboid");
		cuboidButton.addActionListener(this);
		buttonPanel.add(cuboidButton);

		handButton = new JButton();
		handButton.setToolTipText("return to Hand tool");
		handButton
				.setIcon(new ImageIcon(getClass().getResource("img/hand.png")));
		handButton.setName("hand");
		handButton.getModel().setPressed(true);
		handButton.addActionListener(this);
		buttonPanel.add(handButton);

		add(buttonPanel, BorderLayout.SOUTH);

		hsvChooser = new HSVChooser(eventHandler);
		add(hsvChooser, BorderLayout.SOUTH);

	}

	/**
	 * 
	 * @return the rgb Color representation of this palette's hsvChooser
	 */
	public RgbColor getColor() {
		return hsvChooser.getColorAsRgb();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		if(!eventHandler.isOrganLoaded()){
			showErrorDialog("Please load or create an organ on wich you want to work. This is done with Model -> Load organ");
			return;
		}
		JButton sourceButton = null;
		Tool currentTool = eventHandler.getTool();
		if (event.getSource() instanceof JButton) {
			sourceButton = (JButton) event.getSource();
			if (sourceButton.getName().equals("hand")) {
				currentTool = Tool.HAND;
				handButton.getModel().setPressed(true);
				cuboidButton.getModel().setPressed(false);
				triangleFromLineButton.getModel().setPressed(false);
			} else if (sourceButton.getName().equals("triangle")) {
				currentTool = Tool.TRIANGLE;
				handButton.getModel().setPressed(false);
				cuboidButton.getModel().setPressed(false);
				triangleFromLineButton.getModel().setPressed(true);
			} else if (sourceButton.getName().equals("cuboid")) {
				handButton.getModel().setPressed(false);
				cuboidButton.getModel().setPressed(true);
				triangleFromLineButton.getModel().setPressed(false);
				WizardCuboid newMesh = new WizardCuboid(eventHandler);
				this.getTopLevelAncestor().add(newMesh);
				newMesh.toFront();
			} else if (sourceButton.getName().equals("triangleWithGUI")) {
				handButton.getModel().setPressed(false);
				cuboidButton.getModel().setPressed(false);
				triangleFromLineButton.getModel().setPressed(false);
				triangleButton.getModel().setPressed(true);
				triangleSplitButton.getModel().setPressed(false);
				NewMesh newMeshDialog = new NewMesh(eventHandler,
						eventHandler.getSelectedFigure());
				this.getTopLevelAncestor().add(newMeshDialog);
				newMeshDialog.toFront();
			} else if (sourceButton.getName().equals("splitTriangle")){
				currentTool = Tool.SPLIT_TRIANGLE;
				handButton.getModel().setPressed(false);
				cuboidButton.getModel().setPressed(false);
				triangleFromLineButton.getModel().setPressed(false);
				triangleButton.getModel().setPressed(false);
				triangleSplitButton.getModel().setPressed(true);
			}
			eventHandler.setTool(currentTool);
		}
	}

	private void showErrorDialog(String message){
		JOptionPane.showMessageDialog(this.getTopLevelAncestor(), message, "Ooops... an error occured", JOptionPane.ERROR_MESSAGE );
	}
	public enum Tool {
		HAND, TRIANGLE, CUBOID, SPLIT_TRIANGLE;
	}

	@Override
	public void wasUpdated(FiguresEvent e) {
		if (eventHandler.getSelectedFigure() < 0) {
			triangleButton.setEnabled(false);
		} else {
			triangleButton.setEnabled(true);
		}

	}
}
