package de.hska.lat.robot.dataGraph.valueGraph;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;



import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;


import de.hska.lat.data.csv.Csv;
import de.hska.lat.data.csv.CsvData;
import de.hska.lat.data.csv.CsvReader;
import de.hska.lat.data.csv.CsvWriter;
import de.hska.lat.robot.value.flow.ValueFlow;
import de.hska.lat.robot.value.valueViewer.ValueGraphNotifier;
import de.hska.lat.robot.component.value.flow.ValuePoint;
import de.hska.lat.robot.dataGraph.DataGraphTimeScaleSettings;
import de.hska.lat.robot.dataGraph.DataGraphTimeScaleSettingsListener;
import de.hska.lat.robot.ui.settings.UiSettings;
import de.hska.lat.robot.value.ComponentValue;
import de.hska.lat.robot.value.FloatValue;



public class ValueGraphGrid extends ValueGraphAbstract implements MouseListener, MouseMotionListener, MouseWheelListener, ActionListener, FocusListener, DataGraphTimeScaleSettingsListener
{


/**
	 * 
	 */
	private static final long serialVersionUID = -770958824136944551L;	

	protected boolean mouseDragged;

	protected int movementXOrigin;
	protected int movementYOrigin;
		
    protected JPopupMenu contextMenue;
	
    protected static final String saveText = "save";
    protected static final String loadText = "load";
    protected static final String addText = "add";
    protected static final String deleteText = "delete";
    protected static final String clearText = "clear";
    protected static final String closeText = "close";
    
    protected static final String CMD_SAVE	=  "cmdSave";
    protected static final String CMD_LOAD	=  "cmdLoad";
	
    protected static final String CMD_ADD = "cmdAdd";
    protected static final String CMD_DELETE = "cmdDelete";
    protected static final String CMD_CLEAR = "cmdClear";
	protected static final String CMD_CLOSE = "cmdClose";
    
	protected String settingsKey;
	
	protected String fileName;

	protected Cursor cursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
	protected Cursor oldCursor;

	protected List<ValueGraphNotifier> listeners;
	
	protected JMenuItem addMenuItem;
	protected JMenuItem deleteMenuItem;
	
	protected DataGraphTimeScaleSettings timeScaleSettings;

	protected Color backgroundColor = Color.WHITE;
	
	private ComponentValue<?> value;
	private ValueFlow data;

	
public ValueGraphGrid(DataGraphTimeScaleSettings timeScaleSettings, ComponentValue<?> value, ValueGraphSettings settings) 
{	
	super(settings);
	this.settingsKey = this.getClass().getName();
	
	this.listeners = new ArrayList<ValueGraphNotifier>();
	
	this.setBackground(Color.WHITE);

	this.addMouseListener(this);
	this.addMouseMotionListener(this);
	this.addMouseWheelListener(this);
	this.addFocusListener(this);
	
	
	
	this.value = value;
	this.data = new ValueFlow(this.value);

	this.timeScaleSettings = timeScaleSettings;
	this.timeScaleSettings.addListener(this);
	
	this.settings.setMinRange(value.getMinRange());
	this.settings.setMaxRange(value.getMaxRange());
	
	this.settings.addListener(this);
	
	this.makePopupMenu();
	
	this.calculate();
}
	
		
		
		
		
public void addValueListener(ValueGraphNotifier listener)
{
	this.listeners.add(listener);
}

public void removeValueListener(ValueGraphNotifier listener)
{
	this.listeners.remove(listener);
}


protected void makePopupMenu()
{
	this.contextMenue = new JPopupMenu();
	  MouseListener popupListener = new PopupListener();
	  
	  
	  ButtonGroup group = new ButtonGroup();

	  
	  
	  addMenuItem = this.addRadioButtonMenuItem(this.contextMenue , ValueGraphGrid.addText, ValueGraphGrid.CMD_ADD);
	  addMenuItem.setSelected(false);
	  deleteMenuItem = this.addRadioButtonMenuItem(this.contextMenue , ValueGraphGrid.deleteText, ValueGraphGrid.CMD_DELETE);
	  
	  group.add(addMenuItem);
	  group.add(deleteMenuItem);
	  
	  this.addMenuItem(this.contextMenue , ValueGraphGrid.clearText, ValueGraphGrid.CMD_CLEAR);
	  this.contextMenue.add(new JSeparator());
	
	  this.addMenuItem(this.contextMenue , ValueGraphGrid.loadText, ValueGraphGrid.CMD_LOAD);
	  this.addMenuItem(this.contextMenue , ValueGraphGrid.saveText, ValueGraphGrid.CMD_SAVE);
	  
	  this.contextMenue.add(new JSeparator());
	  this.addMenuItem(this.contextMenue , ValueGraphGrid.closeText, ValueGraphGrid.CMD_CLOSE);
	  

    this.addMouseListener(popupListener);
}



protected JMenuItem addRadioButtonMenuItem(JPopupMenu popupMenu, String text, String command)
{
	
	  JMenuItem  menuItem = new JRadioButtonMenuItem(text);
	   menuItem.addActionListener(this);
	   menuItem.setActionCommand(command);
	   popupMenu.add(menuItem);

    
	   return(menuItem); 
}


protected JMenuItem addMenuItem(JPopupMenu popupMenu, String text, String command)
{
	
	  JMenuItem  menuItem = new JMenuItem(text);
	   menuItem.addActionListener(this);
	   menuItem.setActionCommand(command);
	   popupMenu.add(menuItem);

    
	   return(menuItem); 
}


@Override
public void mouseClicked(MouseEvent mouseEvent)
{
}



@Override
protected void paintComponent(Graphics graphics)
{
	
	super.paintComponent(graphics);
	
	this.drawGrid(graphics) ;	

	this.drawData(graphics);

}



protected void drawGrid(Graphics graphics) 
{
	graphics.setColor(Color.LIGHT_GRAY);
	this.drawGridHorizontalLines(graphics);
	this.drawGridVerticalLines(graphics);
//	this.drawCursorPosition(graphics);
}

protected void drawGridHorizontalLines(Graphics graphics) {
	int width = this.getWidth();
	int height = this.getHeight();
	
	float niceStepSize = this.calcStepSize();
	
	graphics.setColor(Color.LIGHT_GRAY);
	
	int maxLine = (int) (this.displayMaxRange / niceStepSize);
	int minLine = (int) (this.displayMinRange / niceStepSize);
	
	for (int line = maxLine; line >= minLine; line--) 
	{
		int yPos = (int) (((this.displayMaxRange - (niceStepSize * line)) / this.displayRange) * height);
		
		if (line == 0) {
			graphics.setColor(Color.GRAY);
		}
		
		graphics.drawLine(0, yPos,  width, yPos);
	
		if (line == 0) {
			graphics.setColor(Color.LIGHT_GRAY);
		}
	}
}

protected void drawGridVerticalLines(Graphics graphics) {
	int width = this.getWidth();
	int height = this.getHeight();
	int xRasterSize = this.timeScaleSettings.getxRasterSize();
	
	int gridOffset;
	int gridX;
	int gridXStart;
	
	int timescale = this.timeScaleSettings.getTimeScale();
	int timeOffset;
	
	timeOffset = timeScaleSettings.getTimeOffset();
	gridOffset = ((timeOffset / timescale) % xRasterSize);
	gridXStart = xRasterSize - gridOffset;
	
	//draw line on left side
	graphics.drawLine(0, 0,  0, height);

	// draw reaming lines
	for (gridX = gridXStart ; gridX < width; gridX += xRasterSize)
	{
		graphics.drawLine(gridX, 0, gridX, height);
	}
}

//protected void drawCursorPosition(Graphics graphics) {
//	if (cursorOnGrid) {
//
//		float xValue = this.timeFromScreen(cursorXPos);
//		float yValue = this.valueFromScreen(cursorYPos);
//		
//		String values = "(" + FloatValue.toFormatedFractionString(xValue, 2) 
//				+ ", " + FloatValue.toFormatedFractionString(yValue, 2) + ")";
//		
//		drawStringWithBGLeftFrom(graphics, values, this.getWidth() - 30, this.getHeight() - 2);
//	
//	}
//}


//private void drawStringWithBGLeftFrom(Graphics graphics, String string, int xPos, int yPos) {
//	int descriptionWidth = graphics.getFontMetrics().stringWidth(string);
//	
//	drawStringWithBG(graphics, string, xPos - descriptionWidth, yPos);
//}


//private void drawStringWithBG(Graphics graphics, String string, int xPos, int yPos) {
//	Color fontColor = graphics.getColor();
//	
//	FontMetrics fm = graphics.getFontMetrics();
//	Rectangle2D rect = fm.getStringBounds(string, graphics);
//	graphics.setColor(backgroundColor);
//	graphics.fillRect(xPos,
//				yPos - fm.getAscent(),
//               (int) rect.getWidth(),
//               (int) rect.getHeight());
//	
//	graphics.setColor(fontColor);
//	graphics.drawString(string, xPos, yPos);
//}


protected void drawValueLeftFrom(Graphics graphics, float value, int xPos, int yPos)
{
	String description;
	int descriptionWidth;
	
		
	description = FloatValue.toFormatedFractionString(value, 2);
	descriptionWidth=graphics.getFontMetrics().stringWidth(description);
	graphics.drawString(description, xPos- descriptionWidth , yPos);
}




protected void drawData(Graphics graphics)
{
	
	int x;
	int y;
	
	int oldX=0;
	int oldY=0;
	
	ValuePoint originPoint;
	 
	if (this.data.size() == 0)
		return;
	
	originPoint = this.data.get(0);
	oldX = timeToScreen(originPoint.getTime());
	oldY = valueToScreen(originPoint.getValue());

	
	
	for(ValuePoint dataPoint : this.data)
	{
		
		x = timeToScreen(dataPoint.getTime());
		y = valueToScreen( dataPoint.getValue());
		
		graphics.setColor(Color.BLACK);
		
		graphics.drawLine(oldX, oldY, x, y);
		
		graphics.setColor(Color.RED);
		graphics.drawRect(x - 1, y - 1, 2, 2);
		
		oldX = x;
		oldY= y;
	}

	
	
}




protected int timeToScreen(int time)
{
	return((time - this.timeScaleSettings.getTimeOffset()) 
					/ this.timeScaleSettings.getTimeScale());
}


protected int timeFromScreen(int xPos)
{
	return((xPos * this.timeScaleSettings.getTimeScale())
			+ this.timeScaleSettings.getTimeOffset());
}



/**
 * return values y position on panels screen
 * @param value 
 * @return
 */

protected int valueToScreen(float value)
{
	return( (int) (((this.displayMaxRange - value) / this.displayRange) * this.getHeight()));
}




protected float valueFromScreen(int position)
{
	float value;
	value =  this.displayMaxRange - ((position * this.displayRange) / this.getHeight());
	return(value);
}




public void update()
{
	this.invalidate();
	this.repaint();
}



public void loadCsvData(File fileToLoad)
{
	CsvReader reader = new CsvReader();
	reader.setCsv(new Csv());
	
	reader.setInputFile(fileToLoad);
	
	Csv csv = null;
	try
	{
		csv = reader.read();
	} catch (IOException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	if (csv != null) {
		if (csv.getCsvData().getHeader().contains("time") 
				&& csv.getCsvData().getHeader().size() > 1) 
		{
		
			String dataColumn = this.value.getName();
			
			if (!csv.getCsvData().getHeader().contains(this.value.getName()) || csv.getCsvData().getHeader().size() > 2) 
			{
				dataColumn = showDataSelectionDialog(csv, dataColumn);	
			}
			
			if (dataColumn != null) {
				this.loadData(csv, dataColumn);
			}
			
		} else {
			System.out.println("Error: Csv does not contain column 'time' and an additional data column");
		}
	}
}

private void setNewValuePoints(List<ValuePoint> values) {
	this.data.clear();
	this.data.addAll(values);
	
	this.update();
}

private void loadData(Csv csv, String yAxisData) {
	List<ValuePoint> values = new ArrayList<ValuePoint>();
	int inconstitentDataRows = 0;
	int amountRows = csv.getCsvData().getColumn("time").size();			
	
	
	for (int row = 0; row < amountRows; row++) 
	{
		try {
			int time = Integer.parseInt((String) csv.getCsvData().getColumn("time").get(row));
			float fValue = Float.parseFloat((String) csv.getCsvData().getColumn(yAxisData).get(row));
			
			values.add(new ValuePoint(time, fValue));
	
		} 
		catch (NumberFormatException e)
		{
			inconstitentDataRows++;
			System.out.println("illegalData : '" + csv.getCsvData().getColumn("time").get(row) + "', '" + csv.getCsvData().getColumn(yAxisData).get(row) + "'");
		}
	}
	
	
	if (inconstitentDataRows != 0) {
		String headerText = "Inconsitent Data";
		String messageText = "The CSV to load contains " + inconstitentDataRows + " inconsistent data rows.\n\nDo you still want to load data?";
		
		int option = JOptionPane.showInternalConfirmDialog(
				this, messageText, headerText, JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
		if (option == 0) {
			this.setNewValuePoints(values);	
		}
		
		
	} else {
		this.setNewValuePoints(values);	
	}
}

private String showDataSelectionDialog(Csv csv, String dataColumn) {
	List<String> possibilitiesList = new ArrayList<String>(csv.getCsvData().getHeader());
	possibilitiesList.remove("time");
	Object[] possibilities = possibilitiesList.toArray();
	String headerText = "Select Data";
	String messageText = "The CSV to load does not contain '" + dataColumn + "'\nor contains more than one data column.\n\nSelect data to load.";
	
	String selection = (String) JOptionPane.showInternalInputDialog(
	                    this, messageText, headerText,
	                    JOptionPane.WARNING_MESSAGE,
	                    null,
	                    possibilities,
	                    this.value.getName());
	
	return selection; // if cancel pressed, selection == null!
}


public void saveCsvData(File filetoSave)
{	
	List<Integer> timeList = new ArrayList<Integer>();
	List<Float> valueList = new ArrayList<Float>();
	
	for (ValuePoint dataPoint : this.data)
	{
		timeList.add(dataPoint.getTime());
		valueList.add(dataPoint.getValue());
	}
	

	CsvData csvData = new CsvData();
	csvData.add("time", timeList, Integer.class);
	csvData.add(value.getName(), valueList, Float.class);
	
	CsvWriter writer = new CsvWriter();
	writer.setOutputFile(filetoSave);
	writer.setCsv(new Csv(csvData));
	
	try
	{
		writer.write();
	} 
	catch (IOException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}



@Override
public void mouseEntered(MouseEvent arg0) 
{
	for (ValueGraphNotifier listener : listeners) {
		listener.onFocus();
	}
}

@Override
public void mouseExited(MouseEvent arg0) 
{
	for (ValueGraphNotifier listener : listeners) {
		listener.lostFocus();
	}
}



@Override
public void mousePressed(MouseEvent mouseEvent)
{
	this.mouseDragged = false;
	this.movementXOrigin = mouseEvent.getX();
	this.movementYOrigin= mouseEvent.getY();
}



@Override
public void mouseReleased(MouseEvent mouseEvent)
{

	if (this.mouseDragged) 
	{
		return;
	}
		
	if (SwingUtilities.isLeftMouseButton(mouseEvent))
	{
		int time = this.timeFromScreen(mouseEvent.getX());
		float fValue = this.valueFromScreen(mouseEvent.getY());
		
		if (this.addMenuItem.isSelected())
		{
			this.data.addAtTime(time, fValue);
			this.update();
			
		}
		else if (this.deleteMenuItem.isSelected())
		{
			int timeRadius = this.timeFromScreen(mouseEvent.getX() + 5) - time;
			if (this.removeAtTime(time, timeRadius))
			{
				this.update();		
			}
		}

	}		
		
	return;
}

private boolean removeAtTime(int time, int radius) {
	boolean removed = false;
	
	for (int r = 0; r <= radius; r++) {
		if (data.removeAtTime(time + r)) {
			removed = true;
			break;
		} else if (data.removeAtTime(time - r)) {
			removed = true;
			break;
		}
	}
	
	return removed;
}


@Override
public void mouseDragged(MouseEvent mouseEvent)
{
	this.mouseDragged = true;
	//move ()

	for (ValueGraphNotifier listener : listeners) {
		listener.cursorMoved(this.timeFromScreen(mouseEvent.getX()),this.valueFromScreen(mouseEvent.getY()));
	}
	
	int deltaX = this.movementXOrigin - mouseEvent.getX();
	this.movementXOrigin = mouseEvent.getX();
	
	int deltaY = this.movementYOrigin - mouseEvent.getY();
	this.movementYOrigin = mouseEvent.getY();
	
	if (SwingUtilities.isLeftMouseButton(mouseEvent))
	{
		if (deltaX != 0) {
			timeScaleSettings.moveXOffset(deltaX);
		}
		
		if (deltaY != 0)
		{
			float newDisplayOffset = settings.getYOffset() - (  this.displayRange * deltaY ) / this.getHeight();
			settings.setYOffset(newDisplayOffset);
		}

	}
}


@Override
public void mouseMoved(MouseEvent mouseEvent)
{
	for (ValueGraphNotifier listener : listeners) {
		listener.cursorMoved(this.timeFromScreen(mouseEvent.getX()),this.valueFromScreen(mouseEvent.getY()));
	}
}



@Override
public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent)
{
	int notches = mouseWheelEvent.getWheelRotation();
	int scaleStep = this.settings.getYScaleStep();
	
	if (notches > 0)
	{
		scaleStep--;
	}
	else
	{
		scaleStep++;
	}
	
	if (scaleStep < 1)
	{
		scaleStep = 1;
	}	
	else if (scaleStep > 50) {
		scaleStep = 50;
	}
	
	if (scaleStep != this.settings.getYScaleStep()) {
		this.settings.setYScaleStep(scaleStep);
	}
	
	for (ValueGraphNotifier listener : listeners) {
		listener.cursorMoved(this.timeFromScreen(mouseWheelEvent.getX()),this.valueFromScreen(mouseWheelEvent.getY()));
	}
}






class PopupListener extends MouseAdapter 
{
    public void mousePressed(MouseEvent e) 
    {
        maybeShowPopup(e);
    }

    public void mouseReleased(MouseEvent e) 
    {
        maybeShowPopup(e);
    }

    private void maybeShowPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
        	contextMenue.show(e.getComponent(),
                       e.getX(), e.getY());
        }
    }
}




protected static final String dataDirectory = ".dataDirectory"; 

@Override
public void actionPerformed(ActionEvent actionEvent)
{
	String command;
	
	command = actionEvent.getActionCommand();
	
	
	if (command.equals(ValueGraphGrid.CMD_LOAD))
	{
		final JFileChooser fc = new JFileChooser();
		File  currentDirectory;
		
		currentDirectory = new File(UiSettings.recoverString(this.settingsKey+dataDirectory, ""));
		fc.setCurrentDirectory(currentDirectory);

		  if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
		  {
	            File fileToLoad = fc.getSelectedFile();
	            this.loadCsvData(fileToLoad);
	            currentDirectory = fc.getCurrentDirectory();
	            UiSettings.saveString(this.settingsKey+dataDirectory, currentDirectory.getAbsolutePath());
		  }
	}
	else if (command.equals(ValueGraphGrid.CMD_SAVE))
	{
	
		final JFileChooser fc = new JFileChooser();
		File  currentDirectory;
		
		currentDirectory = new File(UiSettings.recoverString(this.settingsKey+dataDirectory, ""));
		fc.setCurrentDirectory(currentDirectory);
		
		  if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
		  {
	            File fileToSave = fc.getSelectedFile();
	            this.saveCsvData(fileToSave);
	            currentDirectory = fc.getCurrentDirectory();
	            UiSettings.saveString(this.settingsKey+dataDirectory, currentDirectory.getAbsolutePath());
	            
		  }
		
		
		
	}
	else if (command.equals(ValueGraphGrid.CMD_CLEAR))
	{
		this.data.clear();
		this.update();
	}
	else if (command.equals(ValueGraphGrid.CMD_CLOSE))
	{
	//	this.controll.closeValue(this.value);
	}	
	
}




@Override
public void focusGained(FocusEvent arg0)
{
	System.out.println("focus gained");
	oldCursor = this.getCursor();
	this.setCursor(cursor);
	
}





@Override
public void focusLost(FocusEvent arg0)
{
	System.out.println("focus lost");
	this.setCursor(oldCursor);
}

public ValueFlow getValueFlow()
{
	return this.data;
}





public ComponentValue<?> getValue()
{
	return this.value;
}





@Override
public void timeScaleChanged(float scale)
{
	this.calculate();
	this.update();	
}





@Override
public void xRasterSizeChanged(int rasterSize)
{
	this.calculate();
	this.update();	
}





@Override
public void timeOffsetChanged(int timeOffset)
{
	this.calculate();
	this.update();
}






	
	
}
