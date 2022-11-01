package de.hska.lat.robot.morphology.appearance.Editor3D;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.text.DefaultFormatter;

import de.hska.lat.robot.displayFrame.DisplayFrame;
import de.hska.lat.robot.morphology.appearance.Editor3D.entities.Cuboid;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresEvent;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresEventHandler;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresListener;

public class EditCuboid extends DisplayFrame implements ActionListener, ChangeListener, ListSelectionListener, KeyListener, FiguresListener {
	
	private static final long serialVersionUID = 1L;
	
	private FiguresEventHandler eventHandler;


	private float[] oldVertices;
	private char[] oldElements;

	private Cuboid cuboid;
	private Cuboid pointCuboid;

	private JSpinner spinnerCenterX; 
	private JSpinner spinnerCenterY; 
	private JSpinner spinnerCenterZ;
	private JSpinner spinnerColorFieldR;
	private JSpinner spinnerColorFieldG;
	private JSpinner spinnerColorFieldB;
	private JSpinner spinnerColorFieldA;
	private SpinnerNumberModel stepSizeCenterX;
	private SpinnerNumberModel stepSizeCenterY;
	private SpinnerNumberModel stepSizeCenterZ;
	private SpinnerNumberModel colorFieldR;
	private SpinnerNumberModel colorFieldG;
	private SpinnerNumberModel colorFieldB;
	private SpinnerNumberModel colorFieldA;

	private float lastStepSizeCenterX = 0;
	private float lastStepSizeCenterY = 0;
	private float lastStepSizeCenterZ = 0;
	
	private int selectedPoint = -1;
	
	JScrollPane scrollPane;
	private JTextField nameField;
	private JTable table;
	private TableModel model;
	private VertexTableButtonRenderer buttonRenderer;
	
	
	public EditCuboid(FiguresEventHandler eventHandler, int figureId) {
		super("Editor3D Quader editieren", true, true, true, true);
		this.eventHandler = eventHandler;
		
		eventHandler.addFiguresListener(this);

		
		cuboid = new Cuboid(eventHandler, figureId);
		saveOldVertices();
		pointCuboid = new Cuboid(eventHandler, "Point", "", 1.0f, 0.0f, 0.0f, 1.0f);
		
		createUserInterface();
		eventHandler.notifyUpdate(new FiguresEvent(this));
	}

	private void createUserInterface() {
		this.setLayout(null);
		this.setSize(350, 540);
		this.setLocation (960,0);

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

		JLabel labelColor = new JLabel();
		labelColor.setBounds(10,40, 120,30);
		labelColor.setText("Farbe");
		this.add(labelColor);
		
		JLabel labelColorFieldR = new JLabel();
		labelColorFieldR.setBounds(15,60, 60,30);
		labelColorFieldR.setText("Rot");
		colorFieldR = new SpinnerNumberModel(cuboid.getColorR(), 0, 1, 0.1);
		spinnerColorFieldR = new JSpinner(colorFieldR);
		spinnerColorFieldR.setBounds(75,65,60,25);
		spinnerColorFieldR.addChangeListener(this);
		
	    JFormattedTextField fieldColorR = (JFormattedTextField) spinnerColorFieldR.getEditor().getComponent(0);
	    ((DefaultFormatter) fieldColorR.getFormatter()).setCommitsOnValidEdit(true);

		this.add(labelColorFieldR);
		this.add(spinnerColorFieldR);
		
		JLabel labelColorFieldG = new JLabel();
		labelColorFieldG.setBounds(15,100, 60,30);
		labelColorFieldG.setText("Grün");
		colorFieldG = new SpinnerNumberModel(cuboid.getColorG(), 0, 1, 0.1);
		spinnerColorFieldG = new JSpinner(colorFieldG);
		spinnerColorFieldG.setBounds(75,105,60,25);
		spinnerColorFieldG.addChangeListener(this);

	    JFormattedTextField fieldColorG = (JFormattedTextField) spinnerColorFieldG.getEditor().getComponent(0);
	    ((DefaultFormatter) fieldColorG.getFormatter()).setCommitsOnValidEdit(true);
	    
		this.add(labelColorFieldG);
		this.add(spinnerColorFieldG);
		

		JLabel labelColorFieldB = new JLabel();
		labelColorFieldB.setBounds(15,140, 60,30);
		labelColorFieldB.setText("Blau");
		colorFieldB = new SpinnerNumberModel(cuboid.getColorB(), 0, 1, 0.1);
		spinnerColorFieldB = new JSpinner(colorFieldB);
		spinnerColorFieldB.setBounds(75,145,60,25);
		spinnerColorFieldB.addChangeListener(this);

	    JFormattedTextField fieldColorB = (JFormattedTextField) spinnerColorFieldB.getEditor().getComponent(0);
	    ((DefaultFormatter) fieldColorB.getFormatter()).setCommitsOnValidEdit(true);

		this.add(labelColorFieldB);
		this.add(spinnerColorFieldB);
		

		JLabel labelColorFieldA = new JLabel();
		labelColorFieldA.setBounds(15,180, 60,30);
		labelColorFieldA.setText("Alpha");
		colorFieldA = new SpinnerNumberModel(cuboid.getColorA(), 0, 1, 0.1);
		spinnerColorFieldA = new JSpinner(colorFieldA);
		spinnerColorFieldA.setBounds(75,185,60,25);
		spinnerColorFieldA.addChangeListener(this);

	    JFormattedTextField fieldColorA = (JFormattedTextField) spinnerColorFieldA.getEditor().getComponent(0);
	    ((DefaultFormatter) fieldColorA.getFormatter()).setCommitsOnValidEdit(true);

		this.add(labelColorFieldA);
		this.add(spinnerColorFieldA);

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

		
		createTable();
		
		JButton okButton;
		
		okButton = new JButton("OK");
		okButton.setActionCommand("Ok");
		okButton.setBounds(10, 460, 120, 40);
		okButton.setToolTipText("A");
		okButton.addActionListener(this);
		this.add(okButton);
		
		JButton cancelButton;
		
		
		cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		cancelButton.setBounds(150, 460, 120, 40);
		cancelButton.setToolTipText("A");
		cancelButton.addActionListener(this);
		this.add(cancelButton);
		this.show();
	}
	
	public void saveOldVertices() {
		
		float[] vertices = cuboid.getVertices();
		char[] elements = cuboid.getElements();
		
		int verticesSize = vertices.length;
		int elementSize = elements.length;

		oldVertices = new float[verticesSize];
		for (int i = 0; i < verticesSize; i++) {
			oldVertices[i] = vertices[i];
		}
		

		oldElements = new char[elementSize];
		for (int i = 0; i < elementSize; i++) {
			oldElements[i] = elements[i];
		}
	}
	
	// Beim Ok drücken
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Ok")) {
			//oldFigure = figure;
			if (nameField.getText().equals("")) {
				cuboid.setName("Figure " + (cuboid.getFigureId() + 1));
			}
			else {
				cuboid.setName(nameField.getText());
			}

		}
		else if (e.getActionCommand().equals("Cancel")) {
			for (int i = (cuboid.getVertices().length / 10) - 1; i >= 0; i--) {
				cuboid.removeVertex(i);
			}

			for (int i = (oldVertices.length / 10) - 1; i >= 0; i--) {
				cuboid.addVertex(0,
						oldVertices[i * 10],
						oldVertices[(i * 10) + 1],
						oldVertices[(i * 10) + 2]
				);
				cuboid.setColor(
						oldVertices[((i * 10) + 6)],
						oldVertices[((i * 10) + 7)],
						oldVertices[((i * 10) + 8)],
						oldVertices[((i * 10) + 9)]
				);
				
			}

			
			for (int i = (cuboid.getElements().length / 3) - 1; i >= 0; i--) {
				cuboid.removeLastElements();
			}
			
			for (int i = 0; i < (oldElements.length / 3); i++) {
				cuboid.addElements(oldElements[(i * 3) + 0], oldElements[(i * 3) + 1], oldElements[(i * 3) + 2]);
			}
			
		}

		cuboid.setStatus("done");

		pointCuboid.removeFigure();

		eventHandler.removeFiguresListener(this);
		
		this.dispose();
		
		eventHandler.notifyUpdate(new FiguresEvent(this));

	}
	
	
	

	// Beim ändern der Spinner
	@Override
	public void stateChanged(ChangeEvent event) {
		 if (   event.getSource().equals(spinnerColorFieldR)
				 || event.getSource().equals(spinnerColorFieldG)
				 || event.getSource().equals(spinnerColorFieldB)
				 || event.getSource().equals(spinnerColorFieldA)) {
			cuboid.setColor(colorFieldR.getNumber().floatValue(),
							colorFieldG.getNumber().floatValue(),
							colorFieldB.getNumber().floatValue(),
							colorFieldA.getNumber().floatValue()
			);
			
		}
		else if (   event.getSource() == spinnerCenterX
				 || event.getSource() == spinnerCenterY
			 	 || event.getSource() == spinnerCenterZ
		) {
			
			float newStepSizeCenterX = stepSizeCenterX.getNumber().floatValue();
			float newStepSizeCenterY = stepSizeCenterY.getNumber().floatValue();
			float newStepSizeCenterZ = stepSizeCenterZ.getNumber().floatValue();

			newStepSizeCenterX = newStepSizeCenterX - lastStepSizeCenterX;
			newStepSizeCenterY = newStepSizeCenterY - lastStepSizeCenterY;
			newStepSizeCenterZ = newStepSizeCenterZ - lastStepSizeCenterZ;
			
			float[] vertices = cuboid.getVertices();
			
			for (int i = 0; i < (vertices.length / 10); i++ ){
				
				cuboid.updateVertex(
						i,
						vertices[i * 10] + newStepSizeCenterX,
						vertices[(i * 10) + 1] + newStepSizeCenterY,
						vertices[(i * 10) + 2] + newStepSizeCenterZ
				);
			}
			
			lastStepSizeCenterX = stepSizeCenterX.getNumber().floatValue();
			lastStepSizeCenterY = stepSizeCenterY.getNumber().floatValue();
			lastStepSizeCenterZ = stepSizeCenterZ.getNumber().floatValue();
			
		}
		
		updatePointCuboid();
		eventHandler.notifyUpdate(new FiguresEvent(this));
		
	}
	
	public void removeTable() {
		this.remove(scrollPane);
	}
	
	public void createTable() {
		model = new VertexTableView(eventHandler, cuboid);
		
    	table = new JTable();
    	table.setModel( model );
    	table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    	table.getColumnModel().getColumn(0).setPreferredWidth(47);
    	table.getColumnModel().getColumn(1).setPreferredWidth(67);
    	table.getColumnModel().getColumn(2).setPreferredWidth(67);
    	table.getColumnModel().getColumn(3).setPreferredWidth(67);
    	table.getColumnModel().getColumn(4).setPreferredWidth(50);
		table.getColumnModel().getColumn(0).setHeaderValue("Punkt");
		table.getColumnModel().getColumn(1).setHeaderValue("X");
		table.getColumnModel().getColumn(2).setHeaderValue("Y");
		table.getColumnModel().getColumn(3).setHeaderValue("Z");
		table.getColumnModel().getColumn(4).setHeaderValue("Del");
		buttonRenderer = new VertexTableButtonRenderer();
		table.getColumnModel().getColumn(4).setCellRenderer(buttonRenderer);
		
		table.setRowSelectionAllowed(true);
		table.setRowHeight(24);
		table.setDefaultEditor(Object.class, new VertexTableEditor(cuboid, eventHandler));
    	table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    	table.getSelectionModel().addListSelectionListener(this);
    	
        table.setShowGrid(true);

        scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 230, 320,220);    
		this.add(scrollPane);
	}
	
	// Verschiebt den Ausgewählten Hilfspunkt
	public void updatePointCuboid() {
		if (selectedPoint != -1) {
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
	}


	@Override
	public void keyReleased(KeyEvent e) {
		cuboid.setName(nameField.getText());
		eventHandler.notifyUpdate(new FiguresEvent(this));
	}
	
	@Override
	public void keyPressed(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
	

	@Override
	public void wasUpdated(FiguresEvent event) {
		
		if (event.getSource().toString().startsWith("updateTable")) {

			String[] splitResult = event.getSource().toString().split(";");
			
			selectedPoint = -1;
			
			int selectedRow = Integer.parseInt(splitResult[1]);
			cuboid.deletePointFromFigure(selectedRow);
			this.removeTable();
			this.createTable();
			this.repaint();
		}
		else {
			updatePointCuboid();
		}
	}

	//Tabelle Auswahl geändert
	@Override
	public void valueChanged(ListSelectionEvent event) {

		if (event.getValueIsAdjusting()) {
            return;
        }
		else if (table.getSelectedRow() >= 0 && table.getSelectedRow() != selectedPoint && table.getSelectedColumn() != 4) {
			selectedPoint = table.getSelectedRow();
	
			updatePointCuboid();
			eventHandler.notifyUpdate(new FiguresEvent(this));
			table.setRowSelectionInterval(selectedPoint, selectedPoint);
		}
	}
}


