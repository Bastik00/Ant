package de.hska.lat.robot.morphology.appearance.Editor3D;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultFormatter;

import de.hska.lat.robot.displayFrame.DisplayFrame;
import de.hska.lat.robot.morphology.appearance.Editor3D.entities.Cuboid;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresEvent;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresEventHandler;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresListener;

public class WizardCuboid extends DisplayFrame implements ActionListener, ChangeListener, KeyListener, FiguresListener {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FiguresEventHandler eventHandler;


	private Cuboid cuboid;
	private Cuboid pointCuboid;
	
	private SpinnerNumberModel stepSizeX;
	private SpinnerNumberModel stepSizeY;
	private SpinnerNumberModel stepSizeZ;
	private SpinnerNumberModel stepSizeCenterX;
	private SpinnerNumberModel stepSizeCenterY;
	private SpinnerNumberModel stepSizeCenterZ;
	private SpinnerNumberModel colorFieldR;
	private SpinnerNumberModel colorFieldG;
	private SpinnerNumberModel colorFieldB;
	private SpinnerNumberModel colorFieldA;
	private JSpinner spinnerX;
	private JSpinner spinnerY;
	private JSpinner spinnerZ;
	private JSpinner spinnerCenterX;
	private JSpinner spinnerCenterY;
	private JSpinner spinnerCenterZ;
	private JSpinner spinnerColorFieldR;
	private JSpinner spinnerColorFieldG;
	private JSpinner spinnerColorFieldB;
	private JSpinner spinnerColorFieldA;
	private JTextField nameField;
	
	
	public WizardCuboid(FiguresEventHandler eventHandler) {
		super("Editor3D New Cuboid", true, true, true, true);
		this.eventHandler = eventHandler;
		
		eventHandler.addFiguresListener(this);


		cuboid = new Cuboid(eventHandler, "Figure", "NewField");

		pointCuboid = new Cuboid(eventHandler, "Point", "");

		cuboid.setName("Figure " + (cuboid.getFigureId() + 1));
		
		createUserInterface();
		
		eventHandler.notifyUpdate(new FiguresEvent(this));
		
	}
	
	private void createUserInterface() {
		this.setLayout(null);
		this.setSize(300, 440);
		this.setLocation (960,0);
		this.toFront();
		this.setClosable(false);
		
		JLabel labelNameField = new JLabel();
		labelNameField.setBounds(10,10,60,30);
		labelNameField.setText("Name");
		
		nameField = new JTextField();
		nameField.setBounds(70, 15, 60, 25);
		nameField.setText(cuboid.getName());
		nameField.addKeyListener(this);
		
		this.add(labelNameField);
		this.add(nameField);
		
		
		
		JLabel labelBorder = new JLabel();
		labelBorder.setBounds(10,40, 120,30);
		labelBorder.setText("Kantenlänge");
		this.add(labelBorder);
		
		stepSizeX = new SpinnerNumberModel(0, 0, 1000, 1);
		JLabel labelX = new JLabel();
		labelX.setBounds(15,60, 60,30);
		labelX.setText("Kante x");
		spinnerX = new JSpinner(stepSizeX);
		spinnerX.setBounds(75,65,60,25);
		spinnerX.addChangeListener(this);
		
	    JFormattedTextField fieldSpinnerX = (JFormattedTextField) spinnerX.getEditor().getComponent(0);
	    ((DefaultFormatter) fieldSpinnerX.getFormatter()).setCommitsOnValidEdit(true);
		
		stepSizeY = new SpinnerNumberModel(0, 0, 1000, 1);
		JLabel labelY = new JLabel();
		labelY.setBounds(15,100, 60,30);
		labelY.setText("Kante y");
		spinnerY = new JSpinner(stepSizeY);
		spinnerY.setBounds(75,105,60,25);
		spinnerY.addChangeListener(this);
		spinnerY.addKeyListener(this);

	    JFormattedTextField fieldSpinnerY = (JFormattedTextField) spinnerY.getEditor().getComponent(0);
	    ((DefaultFormatter) fieldSpinnerY.getFormatter()).setCommitsOnValidEdit(true);
		
		
		stepSizeZ = new SpinnerNumberModel(0, 0, 1000, 1);
		JLabel labelZ = new JLabel();
		labelZ.setBounds(15,140, 60,30);
		labelZ.setText("Kante Z");
		spinnerZ = new JSpinner(stepSizeZ);
		spinnerZ.setBounds(75,145,60,25);
		spinnerZ.addChangeListener(this);
		spinnerZ.addKeyListener(this);
		
	    JFormattedTextField fieldSpinnerZ = (JFormattedTextField) spinnerZ.getEditor().getComponent(0);
	    ((DefaultFormatter) fieldSpinnerZ.getFormatter()).setCommitsOnValidEdit(true);
		
		
		this.add(labelX);
		this.add(spinnerX);
		this.add(labelY);
		this.add(spinnerY);
		this.add(labelZ);
		this.add(spinnerZ);
		
		

		JLabel labelCenter = new JLabel();
		labelCenter.setBounds(160,40, 120,30);
		labelCenter.setText("Quader Mittelpunkt");
		this.add(labelCenter);
		
		stepSizeCenterX = new SpinnerNumberModel(0, -1000, 1000, 1);
		JLabel labelCenterX = new JLabel();
		labelCenterX.setBounds(165,60, 60,30);
		labelCenterX.setText("Center x");
		spinnerCenterX = new JSpinner(stepSizeCenterX);
		spinnerCenterX.setBounds(215,65,60,25);
		spinnerCenterX.addChangeListener(this);
		
	    JFormattedTextField fieldSpinnerCenterX = (JFormattedTextField) spinnerCenterX.getEditor().getComponent(0);
	    ((DefaultFormatter) fieldSpinnerCenterX.getFormatter()).setCommitsOnValidEdit(true);
		
		stepSizeCenterY = new SpinnerNumberModel(0, -1000, 1000, 1);
		JLabel labelCenterY = new JLabel();
		labelCenterY.setBounds(165,100, 60,30);
		labelCenterY.setText("Center y");
		spinnerCenterY = new JSpinner(stepSizeCenterY);
		spinnerCenterY.setBounds(215,105,60,25);
		spinnerCenterY.addChangeListener(this);

	    JFormattedTextField fieldSpinnerCenterY = (JFormattedTextField) spinnerCenterY.getEditor().getComponent(0);
	    ((DefaultFormatter) fieldSpinnerCenterY.getFormatter()).setCommitsOnValidEdit(true);
		
		stepSizeCenterZ = new SpinnerNumberModel(0, -1000, 1000, 1);
		JLabel labelCenterZ = new JLabel();
		labelCenterZ.setBounds(165,140, 60,30);
		labelCenterZ.setText("Center Z");
		spinnerCenterZ = new JSpinner(stepSizeCenterZ);
		spinnerCenterZ.setBounds(215,145,60,25);
		spinnerCenterZ.addChangeListener(this);
		
	    JFormattedTextField fieldSpinnerCenterZ = (JFormattedTextField) spinnerCenterZ.getEditor().getComponent(0);
	    ((DefaultFormatter) fieldSpinnerCenterZ.getFormatter()).setCommitsOnValidEdit(true);

		this.add(labelCenterX);
		this.add(spinnerCenterX);
		this.add(labelCenterY);
		this.add(spinnerCenterY);
		this.add(labelCenterZ);
		this.add(spinnerCenterZ);


		JLabel labelColorField = new JLabel();
		labelColorField.setBounds(10,180,60,30);
		labelColorField.setText("Farbe");
		
		this.add(labelColorField);
		
		JLabel labelColorFieldR = new JLabel();
		labelColorFieldR.setBounds(15,200,60,30);
		labelColorFieldR.setText("Rot");
		colorFieldR = new SpinnerNumberModel(1, 0, 1, 0.1);
		spinnerColorFieldR = new JSpinner(colorFieldR);
		spinnerColorFieldR.setBounds(75,205,60,25);
		spinnerColorFieldR.addChangeListener(this);
		
	    JFormattedTextField fieldColorR = (JFormattedTextField) spinnerColorFieldR.getEditor().getComponent(0);
	    ((DefaultFormatter) fieldColorR.getFormatter()).setCommitsOnValidEdit(true);

		this.add(labelColorFieldR);
		this.add(spinnerColorFieldR);

		JLabel labelColorFieldG = new JLabel();
		labelColorFieldG.setBounds(15,240,60,30);
		labelColorFieldG.setText("Grün");
		colorFieldG = new SpinnerNumberModel(1, 0, 1, 0.1);
		spinnerColorFieldG = new JSpinner(colorFieldG);
		spinnerColorFieldG.setBounds(75,245,60,25);
		spinnerColorFieldG.addChangeListener(this);
		
	    JFormattedTextField fieldColorG = (JFormattedTextField) spinnerColorFieldG.getEditor().getComponent(0);
	    ((DefaultFormatter) fieldColorG.getFormatter()).setCommitsOnValidEdit(true);

		this.add(labelColorFieldG);
		this.add(spinnerColorFieldG);

		
		JLabel labelColorFieldB = new JLabel();
		labelColorFieldB.setBounds(15,280,60,30);
		labelColorFieldB.setText("Blau");
		colorFieldB = new SpinnerNumberModel(1, 0, 1, 0.1);
		spinnerColorFieldB = new JSpinner(colorFieldB);
		spinnerColorFieldB.setBounds(75,285,60,25);
		spinnerColorFieldB.addChangeListener(this);
		
	    JFormattedTextField fieldColorB = (JFormattedTextField) spinnerColorFieldB.getEditor().getComponent(0);
	    ((DefaultFormatter) fieldColorB.getFormatter()).setCommitsOnValidEdit(true);

		this.add(labelColorFieldB);
		this.add(spinnerColorFieldB);
		
		
		
		JLabel labelColorFieldA = new JLabel();
		labelColorFieldA.setBounds(15,320,60,30);
		labelColorFieldA.setText("Alpha");
		colorFieldA = new SpinnerNumberModel(1, 0, 1, 0.1);
		spinnerColorFieldA = new JSpinner(colorFieldA);
		spinnerColorFieldA.setBounds(75,325,60,25);
		spinnerColorFieldA.addChangeListener(this);
		
	    JFormattedTextField fieldColorA = (JFormattedTextField) spinnerColorFieldA.getEditor().getComponent(0);
	    ((DefaultFormatter) fieldColorA.getFormatter()).setCommitsOnValidEdit(true);

		this.add(labelColorFieldA);
		this.add(spinnerColorFieldA);
		
		
		
		JButton okButton;
		okButton = new JButton("OK");
		okButton.setActionCommand("Ok");
		okButton.setBounds(10, 360, 120, 40);
		okButton.setToolTipText("A");
		okButton.addActionListener(this);
		this.add(okButton);
		
		JButton cancelButton;
		cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		cancelButton.setBounds(150, 360, 120, 40);
		cancelButton.setToolTipText("A");
		cancelButton.addActionListener(this);
		this.add(cancelButton);
		this.show();
	}


	// Beim Ok drücken
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Ok")) {
			if (nameField.getText().equals("")) {
				cuboid.setName("Figure " + (cuboid.getFigureId() + 1));
			}
			else {
				cuboid.setName(nameField.getText());
			}
			
			cuboid.setStatus("done");
			
			eventHandler.notifyUpdate(new FiguresEvent(this));
		}
		if (e.getActionCommand().equals("Cancel")) {
			cuboid.removeFigure();
		}

		this.dispose();

		pointCuboid.removeFigure();

		eventHandler.removeFiguresListener(this);
		
		eventHandler.notifyUpdate(new FiguresEvent(this));
	}
	
	
	// Beim ändern der Spinner
	@Override
	public void stateChanged(ChangeEvent event) {

		if (	event.getSource().equals(spinnerX)
				 || event.getSource().equals(spinnerY)
				 || event.getSource().equals(spinnerZ)
				 || event.getSource().equals(spinnerCenterX)
				 || event.getSource().equals(spinnerCenterY)
				 || event.getSource().equals(spinnerCenterZ)
			 ) {

				cuboid.updateCuboid(
						stepSizeX.getNumber().floatValue(),
						stepSizeY.getNumber().floatValue(),
						stepSizeZ.getNumber().floatValue(),
		
						stepSizeCenterX.getNumber().floatValue(),
						stepSizeCenterY.getNumber().floatValue(),
						stepSizeCenterZ.getNumber().floatValue()
				);
			}
			else if (   event.getSource().equals(spinnerColorFieldR)
					 || event.getSource().equals(spinnerColorFieldG)
					 || event.getSource().equals(spinnerColorFieldB)
					 || event.getSource().equals(spinnerColorFieldA)
			) {
				cuboid.setColor(colorFieldR.getNumber().floatValue(),
								colorFieldG.getNumber().floatValue(),
								colorFieldB.getNumber().floatValue(),
								colorFieldA.getNumber().floatValue()
				);
				
			}
			
			eventHandler.notifyUpdate(new FiguresEvent(this));
	}

	

	@Override
	public void keyReleased(KeyEvent event) {
		if (event.getSource().equals(nameField)) {
			cuboid.setName(nameField.getText());
			eventHandler.notifyUpdate(new FiguresEvent(this));
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
	

	@Override
	public void wasUpdated(FiguresEvent event) {
	}
}
