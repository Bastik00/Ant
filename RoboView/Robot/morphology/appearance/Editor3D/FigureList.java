package de.hska.lat.robot.morphology.appearance.Editor3D;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import de.hska.lat.robot.displayFrame.DisplayFrame;
import de.hska.lat.robot.morphology.appearance.Editor3D.entities.Figure;
import de.hska.lat.robot.morphology.appearance.Editor3D.entities.Figures;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresEvent;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresEventHandler;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresListener;
import de.hska.lat.robot.morphology.appearance.Editor3D.xml.Export;
import de.hska.lat.robot.morphology.appearance.Editor3D.xml.SaveFileChooser;

public class FigureList extends DisplayFrame implements ActionListener, FiguresListener {
	
	private boolean isSaved = true;
	private boolean areSavedEnabled = true;
	private static final long serialVersionUID = 1L;
	private FiguresEventHandler eventHandler;
	private Figures figures;
	private String figurePath;
	
	private ArrayList<JButton> showButtonList;
	private ArrayList<JButton> hideButtonList;
	private ArrayList<JButton> deleteButtonList;
	private ArrayList<JButton> editButtonList;
	private ArrayList<JButton> newTriangleButtonList;
	private ArrayList<JLabel> labelList;

	JButton buttonCuboid;
	JButton buttonSave;
	JButton buttonSaveUnder;
	JButton buttonClose;
	
	
	int selectedPoint = -1;
	
	
	public FigureList(FiguresEventHandler eventHandler, String figurePath) {
		super("Editor3D FigureList", true, true, true, true);
		this.eventHandler = eventHandler;
		this.figures = eventHandler.getFigures();
		this.figurePath = figurePath;
		eventHandler.addFiguresListener(this);
		

		showButtonList = new ArrayList<JButton>();
		hideButtonList = new ArrayList<JButton>();
		deleteButtonList = new ArrayList<JButton>();
		editButtonList = new ArrayList<JButton>();
		newTriangleButtonList = new ArrayList<JButton>();
		labelList = new ArrayList<JLabel>();
		
				
		this.setLayout(null);
		this.setSize(540, 400);
		this.setLocation (550,0);

		createUserInterface();
		
		
		this.show();
	}

	private void removeButtons() {
		
		this.remove(buttonCuboid);
		this.remove(buttonSave);
		this.remove(buttonSaveUnder);
		this.remove(buttonClose);
		
		for (JButton button : showButtonList)
		{
			this.remove(button);
		}
		showButtonList.clear();

		for (JButton button : hideButtonList)
		{
			this.remove(button);
		}
		hideButtonList.clear();
		
		for (JButton button : deleteButtonList)
		{
			this.remove(button);
		}
		deleteButtonList.clear();
		
		for (JButton button : editButtonList)
		{
			this.remove(button);
		}
		editButtonList.clear();
		
		for (JButton button : newTriangleButtonList)
		{
			this.remove(button);
		}
		newTriangleButtonList.clear();
		
		for (JLabel label : labelList)
		{
			this.remove(label);
		}
		labelList.clear();
		
		this.repaint();
	}




	private void createUserInterface() {
		buttonCuboid = new JButton("Neuer Quader");
		buttonCuboid.setActionCommand("Neuer Quader");
		buttonCuboid.setBounds(10, 10, 120, 40);
		buttonCuboid.addActionListener(this);
		
		if (!areSavedEnabled) {
			buttonCuboid.setEnabled(false);
		}
		
		
		this.add(buttonCuboid);

		buttonSave = new JButton("Speichern");
		buttonSave.setActionCommand("Speichern");
		buttonSave.setBounds(140, 10, 120, 40);
		buttonSave.addActionListener(this);
		
		if (figurePath.equals("") || !areSavedEnabled) {
			buttonSave.setEnabled(false);
		}
		
		
		this.add(buttonSave);	


		buttonSaveUnder = new JButton("Speichern Unter");
		buttonSaveUnder.setActionCommand("SpeichernUnter");
		buttonSaveUnder.setBounds(270, 10, 120, 40);
		buttonSaveUnder.addActionListener(this);
		
		if (!areSavedEnabled) {
			buttonSaveUnder.setEnabled(false);
		}
		
		this.add(buttonSaveUnder);
		

		buttonClose = new JButton("Schlieﬂen");
		buttonClose.setActionCommand("Schliessen");
		buttonClose.setBounds(400, 10, 120, 40);
		buttonClose.addActionListener(this);

		if (!areSavedEnabled) {
			buttonClose.setEnabled(false);
		}
		
		this.add(buttonClose);	
		
		
		
		int elementCount = figures.getSize();

		int figureCount = 0;
		for (int FiguresId = 0 ; FiguresId < elementCount ; FiguresId++)
		{ 
		
			Figure figure = figures.getFigure(FiguresId);
			
			
			if (figure.getType().equals("Figure")) {
				JLabel label = new JLabel(figure.getName());
				label.setBounds(10, 60 + (40 * figureCount), 60,30);
				this.add(label);
				labelList.add(label);
				
				JButton showButton = new JButton("Show"); 
				showButton.setActionCommand("show;" + FiguresId);
				showButton.setContentAreaFilled( true );
				showButton.setBorderPainted( false );
				showButton.setBorder(new LineBorder(Color.GRAY,2));
				showButton.setBounds(70, 65 + (40 * figureCount), 50, 25);
				showButton.addActionListener(this);
				showButton.setEnabled(!figure.isVisibile());
				this.add(showButton);
				showButtonList.add(showButton);
				
				
				JButton hideButton = new JButton("Hide"); 
				hideButton.setActionCommand("hide;" + FiguresId);
				hideButton.setContentAreaFilled( true );
				hideButton.setBorderPainted( false );
				hideButton.setBorder(new LineBorder(Color.GRAY,2));
				hideButton.setBounds(125, 65 + (40 * figureCount), 50, 25);
				hideButton.addActionListener(this);
				hideButton.setEnabled(figure.isVisibile());
				this.add(hideButton);
				hideButtonList.add(hideButton);

				
				JButton editButton = new JButton("Editieren"); 
				editButton.setActionCommand("edit;" + FiguresId);
				editButton.setContentAreaFilled( true );
				editButton.setBorderPainted( false );
				editButton.setBorder(new LineBorder(Color.GRAY,2));
				editButton.setBounds(180, 65 + (40 * figureCount), 100, 25);
				editButton.addActionListener(this);
				
				if (figure.getStatus().equals("edit")) {
					editButton.setEnabled(false);
				}
				
				this.add(editButton);
				editButtonList.add(editButton);


				JButton newTriangleButton = new JButton("Neues Dreieck"); 
				newTriangleButton.setActionCommand("newTriangle;" + FiguresId);
				newTriangleButton.setContentAreaFilled( true );
				newTriangleButton.setBorderPainted( false );
				newTriangleButton.setBorder(new LineBorder(Color.GRAY,2));
				newTriangleButton.setBounds(285, 65 + (40 * figureCount), 100, 25);
				newTriangleButton.addActionListener(this);
				
				if (figure.getStatus().equals("edit")) {
					newTriangleButton.setEnabled(false);
				}
				
				this.add(newTriangleButton);
				newTriangleButtonList.add(newTriangleButton);
				
				JButton deleteButton = new JButton("Lˆschen"); 
				deleteButton.setActionCommand("delete;" + FiguresId);
				deleteButton.setContentAreaFilled( true );
				deleteButton.setBorderPainted( false );
				deleteButton.setBorder(new LineBorder(Color.GRAY,2));
				deleteButton.setBounds(390, 65 + (40 * figureCount), 100, 25);
				deleteButton.addActionListener(this);

				if (figure.getStatus().equals("edit")) {
					deleteButton.setEnabled(false);
				}
				
				this.add(deleteButton);
				deleteButtonList.add(deleteButton);
				

				figureCount++;
			}
		}

		this.repaint();
	}


	@Override
	public void actionPerformed(ActionEvent event) {

		if (event.getActionCommand().equals("Neuer Quader")) {
			this.getDesktopPane().add(new WizardCuboid(eventHandler));
			setUnsaved();
		}
		else if (event.getActionCommand().equals("Speichern")) {
			
			new Export(eventHandler, figurePath);
			
			setSaved();
		}
		else if (event.getActionCommand().equals("SpeichernUnter")) {
			
			JFileChooser chooser = new SaveFileChooser();
	
			// Filter nur f¸r XML
			chooser.removeChoosableFileFilter(chooser.getFileFilter());
			FileNameExtensionFilter filter = new FileNameExtensionFilter("XML figure files", "xml");
			chooser.setFileFilter(filter);
	    
	
			if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
	
				figurePath = chooser.getSelectedFile().getPath();

				if (!figurePath.endsWith(".xml")) {
					figurePath += ".xml";
				}
				
				new Export(eventHandler, figurePath);
				setSaved();
				
				//buttonSave.setEnabled(true);
			}
			
			
		}
		else if (event.getActionCommand().equals("Schliessen")) {
			if (!isSaved) {
				int result = JOptionPane.showConfirmDialog(null,
                    "Ohne Speichern schlieﬂen?",
                    "Schlieﬂen",
                    JOptionPane.YES_NO_OPTION);
				
				if (result == 0) {
					this.getDesktopPane().add(new MainMenu(eventHandler));
					this.dispose();
					eventHandler.removeFiguresListener(this);
				}
			}
			else {
				this.getDesktopPane().add(new MainMenu(eventHandler));
				this.dispose();
				eventHandler.removeFiguresListener(this);
			}
		}
		else {
			String actionCommand = event.getActionCommand();

			String[] splitResult = actionCommand.split(";");
			String action = splitResult[0];

			int figureId = Integer.parseInt(splitResult[1]);
			
			if (action.equals("show")) {
				figures.getFigure(figureId).setVisibile(true);
			}
			else if (action.equals("hide")) {
				figures.getFigure(figureId).setVisibile(false);
			}
			else if (action.equals("edit")) {
				this.getDesktopPane().add(new EditCuboid(eventHandler, figureId));
				setUnsaved();
			}
			else if (action.equals("newTriangle")) {
				this.getDesktopPane().add(new NewMesh(eventHandler, figureId));
				setUnsaved();
			}
			else if (action.equals("delete")) {
				figures.removeFigure(figures.getFigure(figureId));
				setUnsaved();
			}
		}
		eventHandler.notifyUpdate(new FiguresEvent(this));
	}



	private void setUnsaved() {
		this.setTitle("Editor3D FigureList (Unsaved)");
		isSaved = false;
	}
	private void setSaved() {
		this.setTitle("Editor3D FigureList");
		isSaved = true;
	}


	@Override
	public void wasUpdated(FiguresEvent e) {

		areSavedEnabled = true;
		for (FiguresListener listener : eventHandler.getFiguresListener().getListeners(FiguresListener.class)) {
			if (   listener.getClass().toString().endsWith("EditCuboid")
				|| listener.getClass().toString().endsWith("NewMesh")
				|| listener.getClass().toString().endsWith("WizardCuboid")
			) {
				areSavedEnabled = false;
			}
		}
		

		removeButtons();
		createUserInterface();
		
		figures = eventHandler.getFigures();
	}

	

}
