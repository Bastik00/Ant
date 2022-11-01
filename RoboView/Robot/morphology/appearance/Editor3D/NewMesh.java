package de.hska.lat.robot.morphology.appearance.Editor3D;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultFormatter;

import de.hska.lat.robot.displayFrame.DisplayFrame;
import de.hska.lat.robot.morphology.appearance.Editor3D.entities.Cuboid;
import de.hska.lat.robot.morphology.appearance.Editor3D.entities.Figure;
import de.hska.lat.robot.morphology.appearance.Editor3D.entities.Figures;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresEvent;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresEventHandler;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresListener;

public class NewMesh extends DisplayFrame implements FiguresListener, ItemListener, ChangeListener, ActionListener {

	private static final long serialVersionUID = -2832054949430641482L;
	private FiguresEventHandler eventHandler;
	private Figures figures;
	private Figure figure;

	private JComboBox<String> comboBoxPoint1;
	private JComboBox<String> comboBoxPoint2;
	private JComboBox<String> comboBoxPoint3;

	private JSpinner spinnerPoint1X; 
	private JSpinner spinnerPoint1Y; 
	private JSpinner spinnerPoint1Z;
	private SpinnerNumberModel numberPoint1X;
	private SpinnerNumberModel numberPoint1Y;
	private SpinnerNumberModel numberPoint1Z;	

	private JSpinner spinnerPoint2X; 
	private JSpinner spinnerPoint2Y; 
	private JSpinner spinnerPoint2Z;
	private SpinnerNumberModel numberPoint2X;
	private SpinnerNumberModel numberPoint2Y;
	private SpinnerNumberModel numberPoint2Z;	

	private JSpinner spinnerPoint3X; 
	private JSpinner spinnerPoint3Y; 
	private JSpinner spinnerPoint3Z;
	private SpinnerNumberModel numberPoint3X;
	private SpinnerNumberModel numberPoint3Y;
	private SpinnerNumberModel numberPoint3Z;
	
	private JButton okButton;
	private JButton cancelButton;
	

	private Cuboid cuboid;
	private Cuboid Point1Cuboid;
	private Cuboid Point2Cuboid;
	private Cuboid Point3Cuboid;
	
	
	private int pointId1;
	private int pointId2;
	private int pointId3;
	
	private int startPointId1;
	private int startPointId2;
	private int startPointId3;
	
	boolean updateViaSelect = false;
	
	
	public NewMesh(FiguresEventHandler eventHandler, int figureId) {
		super("Editor3D Neuer Mesh", true, true, true, true);
		this.eventHandler = eventHandler;
		figures = eventHandler.getFigures();
		figure = figures.getFigure(figureId);
		
		eventHandler.addFiguresListener(this);

		cuboid = new Cuboid(eventHandler, figureId);
		
		Point1Cuboid = new Cuboid(eventHandler, "Point", "", 1.0f, 0.0f, 0.0f, 1.0f);
		Point2Cuboid = new Cuboid(eventHandler, "Point", "", 1.0f, 0.0f, 0.0f, 1.0f);
		Point3Cuboid = new Cuboid(eventHandler, "Point", "", 1.0f, 0.0f, 0.0f, 1.0f);
		
		createUserInterface();
		
		
		pointId1 = (figure.getVertices().length / 10);
		startPointId1 = pointId1;
		cuboid.addVertex(pointId1, 0.0f, 0.0f, 0.0f);

		pointId2 = (figure.getVertices().length / 10);
		startPointId2 = pointId2;
		cuboid.addVertex(pointId2, 0.0f, 0.0f, 0.0f);
		
		pointId3 = (figure.getVertices().length / 10);
		startPointId3 = pointId3;
		cuboid.addVertex(pointId3, 0.0f, 0.0f, 0.0f);

		cuboid.addElements(pointId1, pointId2, pointId3);
	}

	private void createUserInterface() {
		this.setLayout(null);
		this.setSize(300, 570);
		this.setLocation (960,0);

		this.setClosable(false);
		

		JLabel labelPoint1 = new JLabel();
		labelPoint1.setBounds(10,10,200,20);
		labelPoint1.setText("Punkt 1 - Punkt / Koordinaten");
		this.add(labelPoint1);
		
		JLabel labelSelectPoint1 = new JLabel();
		labelSelectPoint1.setBounds(15,30,60,30);
		labelSelectPoint1.setText("Punkt");
		this.add(labelSelectPoint1);
		
		comboBoxPoint1 = new JComboBox<String>();
		comboBoxPoint1.addItem("");
		for (int i = 1; i <= (figure.getVertices().length / 10); i++) { 
			comboBoxPoint1.addItem(Integer.toString(i));
		}
		comboBoxPoint1.addItemListener(this);
		comboBoxPoint1.setBounds(70,35,60,25);
		comboBoxPoint1.setSelectedItem(null);
		this.add(comboBoxPoint1);
		
		
		JLabel labelPoint1X = new JLabel();
		labelPoint1X.setBounds(165,30,60,30);
		labelPoint1X.setText("Punkt X");
		this.add(labelPoint1X);
		numberPoint1X = new SpinnerNumberModel(0, -1000, 1000, 1);
		spinnerPoint1X = new JSpinner(numberPoint1X);
		spinnerPoint1X.setBounds(215,35,60,25);
		spinnerPoint1X.addChangeListener(this);
		this.add(spinnerPoint1X);
	    
		JFormattedTextField fieldSpinnerPoint1X = (JFormattedTextField) spinnerPoint1X.getEditor().getComponent(0);
	    ((DefaultFormatter) fieldSpinnerPoint1X.getFormatter()).setCommitsOnValidEdit(true);
		


		JLabel labelPoint1Y = new JLabel();
		labelPoint1Y.setBounds(165,70,60,30);
		labelPoint1Y.setText("Punkt Y");
		this.add(labelPoint1Y);
		numberPoint1Y = new SpinnerNumberModel(0, -1000, 1000, 1);
		spinnerPoint1Y = new JSpinner(numberPoint1Y);
		spinnerPoint1Y.setBounds(215,75,60,25);
		spinnerPoint1Y.addChangeListener(this);
		this.add(spinnerPoint1Y);
		JFormattedTextField fieldSpinnerPoint1Y = (JFormattedTextField) spinnerPoint1Y.getEditor().getComponent(0);
	    ((DefaultFormatter) fieldSpinnerPoint1Y.getFormatter()).setCommitsOnValidEdit(true);

		JLabel labelPoint1Z = new JLabel();
		labelPoint1Z.setBounds(165,110,60,30);
		labelPoint1Z.setText("Punkt Z");
		this.add(labelPoint1Z);
		numberPoint1Z = new SpinnerNumberModel(0, -1000, 1000, 1);
		spinnerPoint1Z = new JSpinner(numberPoint1Z);
		spinnerPoint1Z.setBounds(215,115,60,25);
		spinnerPoint1Z.addChangeListener(this);
		this.add(spinnerPoint1Z);
		JFormattedTextField fieldSpinnerPoint1Z = (JFormattedTextField) spinnerPoint1Z.getEditor().getComponent(0);
	    ((DefaultFormatter) fieldSpinnerPoint1Z.getFormatter()).setCommitsOnValidEdit(true);
		
		
		JLabel labelPoint2 = new JLabel();
		labelPoint2.setBounds(10,175,200,20);
		labelPoint2.setText("Punkt 2 - Punkt / Koordinaten");
		this.add(labelPoint2);
		
		JLabel labelSelectPoint2 = new JLabel();
		labelSelectPoint2.setBounds(15,195,60,30);
		labelSelectPoint2.setText("Punkt");
		this.add(labelSelectPoint2);
		
		comboBoxPoint2 = new JComboBox<String>();
		comboBoxPoint2.addItem("");
		for (int i = 1; i <= (figure.getVertices().length / 10); i++) { 
			comboBoxPoint2.addItem(Integer.toString(i));
		}
		comboBoxPoint2.addItemListener(this);
		comboBoxPoint2.setBounds(70,200,60,25);
		comboBoxPoint2.setSelectedItem(null);
		this.add(comboBoxPoint2);
		
		
		JLabel labelPoint2X = new JLabel();
		labelPoint2X.setBounds(165,195,60,30);
		labelPoint2X.setText("Punkt X");
		this.add(labelPoint2X);
		numberPoint2X = new SpinnerNumberModel(0, -1000, 1000, 1);
		spinnerPoint2X = new JSpinner(numberPoint2X);
		spinnerPoint2X.setBounds(215,200,60,25);
		spinnerPoint2X.addChangeListener(this);
		this.add(spinnerPoint2X);
		JFormattedTextField fieldSpinnerPoint2X = (JFormattedTextField) spinnerPoint2X.getEditor().getComponent(0);
	    ((DefaultFormatter) fieldSpinnerPoint2X.getFormatter()).setCommitsOnValidEdit(true);

		JLabel labelPoint2Y = new JLabel();
		labelPoint2Y.setBounds(165,235,60,30);
		labelPoint2Y.setText("Punkt Y");
		this.add(labelPoint2Y);
		numberPoint2Y = new SpinnerNumberModel(0, -1000, 1000, 1);
		spinnerPoint2Y = new JSpinner(numberPoint2Y);
		spinnerPoint2Y.setBounds(215,240,60,25);
		spinnerPoint2Y.addChangeListener(this);
		this.add(spinnerPoint2Y);
		JFormattedTextField fieldSpinnerPoint2Y = (JFormattedTextField) spinnerPoint2Y.getEditor().getComponent(0);
	    ((DefaultFormatter) fieldSpinnerPoint2Y.getFormatter()).setCommitsOnValidEdit(true);

		JLabel labelPoint2Z = new JLabel();
		labelPoint2Z.setBounds(165,275,60,30);
		labelPoint2Z.setText("Punkt Z");
		this.add(labelPoint2Z);
		numberPoint2Z = new SpinnerNumberModel(0, -1000, 1000, 1);
		spinnerPoint2Z = new JSpinner(numberPoint2Z);
		spinnerPoint2Z.setBounds(215,280,60,25);
		spinnerPoint2Z.addChangeListener(this);
		this.add(spinnerPoint2Z);
		JFormattedTextField fieldSpinnerPoint2Z = (JFormattedTextField) spinnerPoint2Z.getEditor().getComponent(0);
	    ((DefaultFormatter) fieldSpinnerPoint2Z.getFormatter()).setCommitsOnValidEdit(true);

		
		
		
		JLabel labelPoint3 = new JLabel();
		labelPoint3.setBounds(10,340,200,20);
		labelPoint3.setText("Punkt 3 - Punkt / Koordinaten");
		this.add(labelPoint3);
		
		JLabel labelSelectPoint3 = new JLabel();

		labelSelectPoint3.setBounds(15,360,60,30);
		labelSelectPoint3.setText("Punkt");
		this.add(labelSelectPoint3);
		
		comboBoxPoint3 = new JComboBox<String>();
		comboBoxPoint3.addItem("");
		for (int i = 1; i <= (figure.getVertices().length / 10); i++) { 
			comboBoxPoint3.addItem(Integer.toString(i));
		}
		comboBoxPoint3.addItemListener(this);
		comboBoxPoint3.setBounds(70,365,60,25);
		comboBoxPoint3.setSelectedItem(null);
		this.add(comboBoxPoint3);
		
		
		JLabel labelPoint3X = new JLabel();
		labelPoint3X.setBounds(165,360,60,30);
		labelPoint3X.setText("Punkt X");
		this.add(labelPoint3X);
		numberPoint3X = new SpinnerNumberModel(0, -1000, 1000, 1);
		spinnerPoint3X = new JSpinner(numberPoint3X);
		spinnerPoint3X.setBounds(215,365,60,25);
		spinnerPoint3X.addChangeListener(this);
		this.add(spinnerPoint3X);
		JFormattedTextField fieldSpinnerPoint3X = (JFormattedTextField) spinnerPoint3X.getEditor().getComponent(0);
	    ((DefaultFormatter) fieldSpinnerPoint3X.getFormatter()).setCommitsOnValidEdit(true);

		JLabel labelPoint3Y = new JLabel();
		labelPoint3Y.setBounds(165,400,60,30);
		labelPoint3Y.setText("Punkt Y");
		this.add(labelPoint3Y);
		numberPoint3Y = new SpinnerNumberModel(0, -1000, 1000, 1);
		spinnerPoint3Y = new JSpinner(numberPoint3Y);
		spinnerPoint3Y.setBounds(215,405,60,25);
		spinnerPoint3Y.addChangeListener(this);
		this.add(spinnerPoint3Y);
		JFormattedTextField fieldSpinnerPoint3Y = (JFormattedTextField) spinnerPoint3Y.getEditor().getComponent(0);
	    ((DefaultFormatter) fieldSpinnerPoint3Y.getFormatter()).setCommitsOnValidEdit(true);

		JLabel labelPoint3Z = new JLabel();
		labelPoint3Z.setBounds(165,440,60,30);
		labelPoint3Z.setText("Punkt Z");
		this.add(labelPoint3Z);
		numberPoint3Z = new SpinnerNumberModel(0, -1000, 1000, 1);
		spinnerPoint3Z = new JSpinner(numberPoint3Z);
		spinnerPoint3Z.setBounds(215,445,60,25);
		spinnerPoint3Z.addChangeListener(this);
		this.add(spinnerPoint3Z);
		JFormattedTextField fieldSpinnerPoint3Z = (JFormattedTextField) spinnerPoint3Z.getEditor().getComponent(0);
	    ((DefaultFormatter) fieldSpinnerPoint3Z.getFormatter()).setCommitsOnValidEdit(true);
		
		
		okButton = new JButton("OK");
		okButton.setBounds(10, 490, 120, 40);
		okButton.setToolTipText("A");
		okButton.addActionListener(this);
		this.add(okButton);
		
		
		
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(150, 490, 120, 40);
		cancelButton.setToolTipText("A");
		cancelButton.addActionListener(this);
		this.add(cancelButton);
		
		this.show();
	}

	

	@Override
	public void itemStateChanged(ItemEvent event) {
		

		if( event.getItem().toString() != "") {
			updateViaSelect = true;
			if (event.getSource().equals(comboBoxPoint1)) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					pointId1 = Integer.parseInt(event.getItem().toString()) -1;
					cuboid.removeLastElements();
					cuboid.addElements(pointId1, pointId2, pointId3);
					updatePointSpinner(1, pointId1);
					updatePointCuboid(Point1Cuboid, pointId1);
				}
			}
			else if (event.getSource().equals(comboBoxPoint2)) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					pointId2 = Integer.parseInt(event.getItem().toString()) -1;
					cuboid.removeLastElements();
					cuboid.addElements(pointId1, pointId2, pointId3);
					updatePointSpinner(2, pointId2);
					updatePointCuboid(Point2Cuboid, pointId2);
				}
			}
			else if (event.getSource().equals(comboBoxPoint3)) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					pointId3 = Integer.parseInt(event.getItem().toString()) -1;
					cuboid.removeLastElements();
					cuboid.addElements(pointId1, pointId2, pointId3);
					updatePointSpinner(3, pointId3);
					updatePointCuboid(Point3Cuboid, pointId3);
				}
			}
			eventHandler.notifyUpdate(new FiguresEvent(this));
			updateViaSelect = false;
		}
	}
	

	@Override
	public void stateChanged(ChangeEvent event) {
		if (!updateViaSelect) {
			
			if (   event.getSource() == spinnerPoint1X
					|| event.getSource() == spinnerPoint1Y
					|| event.getSource() == spinnerPoint1Z
				) {
				
	
				
				cuboid.updateVertex(
						startPointId1,
						numberPoint1X.getNumber().floatValue(),
						numberPoint1Y.getNumber().floatValue(),
						numberPoint1Z.getNumber().floatValue()
				);
				
				pointId1 = startPointId1;
			 	
				cuboid.removeLastElements();
				cuboid.addElements(pointId1, pointId2, pointId3);
	
				updatePointCuboid(Point1Cuboid, pointId1);
			
			}
	
			else if (   event.getSource() == spinnerPoint2X
					|| event.getSource() == spinnerPoint2Y
					|| event.getSource() == spinnerPoint2Z
				) {
				cuboid.updateVertex(
						startPointId2,
						numberPoint2X.getNumber().floatValue(),
						numberPoint2Y.getNumber().floatValue(),
						numberPoint2Z.getNumber().floatValue()
				);
				
				pointId2 = startPointId2;
	
				cuboid.removeLastElements();
				cuboid.addElements(pointId1, pointId2, pointId3);
				updatePointCuboid(Point2Cuboid, pointId2);
			}
			
	
			else if (   event.getSource() == spinnerPoint3X
					|| event.getSource() == spinnerPoint3Y
					|| event.getSource() == spinnerPoint3Z
				) {
				cuboid.updateVertex(
						startPointId3,
						numberPoint3X.getNumber().floatValue(),
						numberPoint3Y.getNumber().floatValue(),
						numberPoint3Z.getNumber().floatValue()
				);
				
				pointId3 = startPointId3;
	
				cuboid.removeLastElements();
				cuboid.addElements(pointId1, pointId2, pointId3);
				updatePointCuboid(Point3Cuboid, pointId3);
			}
	
			eventHandler.notifyUpdate(new FiguresEvent(this));
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(okButton)) {
			
			if (pointId1 != startPointId1) {
				cuboid.removeVertex(startPointId1);
				
				if (pointId2 == startPointId2) {
					pointId2--;
				}
				startPointId2--;
				
				if (pointId3 == startPointId3) {
					pointId3--;
				}
				startPointId3--;
			}
			
			if (pointId2 != startPointId2) {
				cuboid.removeVertex(startPointId2);
				
				if (pointId3 == startPointId3) {
					pointId3--;
				}
				startPointId3--;
			}
			
			if (pointId3 != startPointId3) {
				cuboid.removeVertex(startPointId3);
			}
			

			cuboid.removeLastElements();
			cuboid.addElements(pointId1, pointId2, pointId3);
			
		}
		else if (event.getSource().equals(cancelButton)) {
			cuboid.removeVertex(startPointId3);
			cuboid.removeVertex(startPointId2);
			cuboid.removeVertex(startPointId1);
			cuboid.removeLastElements();
		}

		cuboid.setStatus("done");
		
		this.dispose();
		
		
		Point1Cuboid.removeFigure();
		Point2Cuboid.removeFigure();
		Point3Cuboid.removeFigure();

		eventHandler.removeFiguresListener(this);
		
		eventHandler.notifyUpdate(new FiguresEvent(this));
	}
	
	public void updatePointSpinner(int uiId, int selectedPoint) {
		if (uiId == 1) {
			float[] vertices = cuboid.getVertices();

			numberPoint1X.setValue(new Float(vertices[selectedPoint * 10]));
			numberPoint1Y.setValue(new Float(vertices[(selectedPoint * 10) + 1]));
			numberPoint1Z.setValue(new Float(vertices[(selectedPoint * 10) + 2]));
			
		}
		else if (uiId == 2) {
			float[] vertices = cuboid.getVertices();

			numberPoint2X.setValue(new Float(vertices[selectedPoint * 10]));
			numberPoint2Y.setValue(new Float(vertices[(selectedPoint * 10) + 1]));
			numberPoint2Z.setValue(new Float(vertices[(selectedPoint * 10) + 2]));
			
		}
		else if (uiId == 3) {
			float[] vertices = cuboid.getVertices();

			numberPoint3X.setValue(new Float(vertices[selectedPoint * 10]));
			numberPoint3Y.setValue(new Float(vertices[(selectedPoint * 10) + 1]));
			numberPoint3Z.setValue(new Float(vertices[(selectedPoint * 10) + 2]));
			
		}
	}
	public void updatePointCuboid(Cuboid pointCuboid, int selectedPoint) {

		float[] vertices = cuboid.getVertices();
		
		pointCuboid.updateCuboid(
				10,
				10,
				10,
				vertices[selectedPoint * 10],
				vertices[(selectedPoint * 10) + 1],
				vertices[(selectedPoint * 10) + 2]
		);
	}
	

	@Override
	public void wasUpdated(FiguresEvent e) {}
}
