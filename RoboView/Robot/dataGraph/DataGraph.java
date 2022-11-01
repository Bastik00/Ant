package de.hska.lat.robot.dataGraph;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;


import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


import de.hska.lat.robot.abstractRobot.AbstractRobot;

import de.hska.lat.robot.dataGraph.valueGraph.ValueGraphDisplay;
import de.hska.lat.robot.displayFrame.DisplayFrame;
import de.hska.lat.robot.ui.settings.UiSettings;
import de.hska.lat.robot.value.ComponentValue;
import de.hska.lat.robot.value.valueViewer.ValueRecordList;


	public class DataGraph extends DisplayFrame  implements  ComponentListener, DataGraphControl, DataGraphScreenControl
	{
	
		private static final long serialVersionUID = 3898871389587509991L;
	
		
	
		protected DataGraphTimeScale scalePanel;
		protected DataGraphTimeResolution resolutionPanel;
		protected DataGraphTimeScaleSettings timeScaleSettings = new DataGraphTimeScaleSettings();
		
		protected DataGraphToolbar toolBar;
		protected DataGraphStatusBar statusBar;
	
		protected ArrayList<ComponentValue<?>> valueList = new ArrayList<ComponentValue<?>>();
		
		protected ArrayList<ValueGraphDisplay> valueDisplays = new ArrayList<ValueGraphDisplay>();	
		
		protected ValueRecordList recordlist = new ValueRecordList();
		
		
		
	public DataGraph(String name) 
	{
		super(name, true, true, true, true);
		
		this.getContentPane().setLayout(null);
		
		this.toolBar = new DataGraphToolbar(this);
		this.getContentPane().add(this.toolBar);
	
		this.scalePanel = new DataGraphTimeScale(this.timeScaleSettings);
		this.getContentPane().add(this.scalePanel);
		this.getContentPane().addComponentListener(this);
	
		this.statusBar = new DataGraphStatusBar();
		this.add(statusBar);
	
		this.show();
	}
	
	
	@Override
	public boolean setRobot(AbstractRobot<?,?,?> robot)
	{
		System.out.println("setEmulator(RobotEmulator emulator)");
		
		this.valueList.addAll(this.getValues(robot));
		
		this.printValueList();
	
		this.rearange();
		
		return(true);
	}
	
	protected void printValueList() {
		for (ComponentValue<?> value : valueList)
		{
			System.out.println(" name : " + value.getName() + "\t\ttype "+value.getTypeName());
		}
	}
	
	
	protected ArrayList<ComponentValue<?>> getValues(AbstractRobot<?,?,?> robot)
	{
		ArrayList<ComponentValue<?>> valueList = new ArrayList<ComponentValue<?>>();
		
		return(valueList);
	}
	
	
	protected void addValueView(ComponentValue<?> value)
	{
		
		ValueGraphDisplay display = new ValueGraphDisplay(value, timeScaleSettings, this, this);
	
		this.valueDisplays.add(display);
		this.add(display);
		display.addValueGraphNotifier(statusBar);
		this.rearange();
		this.revalidate();
		this.repaint();
	}
	
	protected void removeValueView(ValueGraphDisplay valueDisplay)
	{
		valueDisplay.removeValueGraphNotifier(this.statusBar);
		this.valueDisplays.remove(valueDisplay);	
		this.remove(valueDisplay);
		this.rearange();
		this.revalidate();
		this.repaint();
	}
	
	protected void removeAllValueViews()
	{
		for (ValueGraphDisplay valueDisplay : valueDisplays) 
		{
			valueDisplay.removeValueGraphNotifier(this.statusBar);
			this.remove(valueDisplay);
		}
		
		this.valueDisplays.clear();
		this.rearange();
		this.revalidate();
		this.repaint();
	}
	
	
	protected void rearange()
	{
		int yPosition;
		
		this.toolBar.setSize(this.getContentPane().getWidth(), 25);
		this.scalePanel.setLocation(230, this.toolBar.getHeight());
		this.scalePanel.setSize(this.getContentPane().getWidth() -200, 30);
		
		yPosition = scalePanel.getY() + scalePanel.getHeight();
	
		for (ValueGraphDisplay display : valueDisplays)
		{
			display.setBounds(0, yPosition, this.getContentPane().getWidth(),display.getHeight());
			yPosition += display.getHeight();
		}
		
		this.statusBar.setSize(this.getContentPane().getWidth(), 25);
		this.statusBar.setLocation(0, this.getContentPane().getHeight() - this.statusBar.getHeight());
	
	}
	
	
	
	
	/*
	
	
	public void setEmulator(RobotEmulator emulator)
	{
		System.out.println("setEmulator(RobotEmulator emulator)");
		
		
	
		this.valueList.addAll(emulator.getEmulatorValues());
		
		for (ComponentValue<?,?> value : valueList)
		{
			addDestinationView(value);
			System.out.println(" name : "+value.getName()+"       type "+value.getTypeName());
		}
	
		this.rearange();
	}	
	
	
	*/
	@Override
	public void play()
	{
		this.recordlist.clear();
		//TODO
	}




	protected Timer timer;
	
	@Override
	public void record()
	{
		
		this.recordlist.clear();
		
		timer = new Timer();
		timer.scheduleAtFixedRate(new ValueRefreshTask(), 10, 100);
		
		for (ValueGraphDisplay display : valueDisplays)
		{
			this.recordlist.add(display.getValueFlow());
		}
		this.recordlist.record(0);
		
	}
	
	
	@Override
	public void stop()
	{
		if (timer!=null)
		{
			timer.cancel();
			timer=null;
		}
		this.recordlist.stop();
	}
	
	
	@Override
	public void load()
	{
		File inputFile = this.chooseFile("Select source folder", "open");
		
		if (inputFile != null) {
			Properties dataIndex = loadProperties(inputFile);
			File inputFolder = inputFile.getParentFile();
			
			if (dataIndex != null) {
				this.removeAllValueViews();
			
				String[] sensorNames = ((String) dataIndex.get("order")).split(",");
				
				for (String sensorName : sensorNames) {
					String fileName = (String) dataIndex.get(sensorName);
					
					File file = new File(inputFolder.getAbsolutePath() + File.separator + fileName);
					
					for (ComponentValue<?> value : valueList)
					{
						if (value.getName().equals(sensorName))
						{
							if (!this.isOpen(value))
							{
								this.addValueView(value);
							}

							for (ValueGraphDisplay valueDisplay : valueDisplays)
							{
								if (valueDisplay.getValue() == value)
								{
									valueDisplay.loadCsvData(file);
									break;
								}
							}
						}
					}
				}
			}
			
			
		}
		
		
	}
	
	protected String getFileNameWithoutExt(File file) {
		String fileName = file.getName();
		int pos = fileName.lastIndexOf("."); //remove extention
		if (pos > 0) {
		    fileName = fileName.substring(0, pos);
		}
		
		return fileName;
	}
	
	protected String getFileExtention(File file) {
		String fileName = file.getName();
		String extention = "";
		
		int pos = fileName.lastIndexOf(".") + 1;
		if (pos < fileName.length()) {
		    extention = fileName.substring(pos, fileName.length());
		}
		
		return extention;
	}
	
	@Override
	public void save()
	{
		File outputFile = this.chooseFile("Select target folder", "save");
		
		if (outputFile != null) {
			String fileName = getFileNameWithoutExt(outputFile);
			File outputFolder = outputFile.getParentFile();
			Properties dataIndex = new Properties();
			
			List<String> order = new ArrayList<>();
			for	(ValueGraphDisplay valueDisplay : valueDisplays) {
				String sensorName = valueDisplay.getValue().getName();
				
				
				String fileNameToSave = fileName + " " + sensorName + ".csv";
				fileNameToSave = fileNameToSave.replace(' ', '_');
				File fileToSave = new File(outputFolder.getAbsolutePath() + File.separator + fileNameToSave);
				valueDisplay.saveCsvData(fileToSave);
				dataIndex.setProperty(sensorName, fileToSave.getName());
				order.add(sensorName);
			}
			dataIndex.setProperty("order", this.joinList(order, ','));
			this.saveProperties(dataIndex, "Graph Set", outputFile);
		}
		
		
		
	}
	
	private <T> String joinList(List<T> list, char separator) {
		StringBuilder str = new StringBuilder();
		for (T e : list) {
			str.append(e).append(separator);
		}
		if (list.size() > 0) {
			str.setLength(str.length() - 1);
		}
		
		return str.toString();
	}
	
	protected void saveProperties(Properties prop, String comment, File file) {
		try
		{
			FileOutputStream fos = new FileOutputStream(file);
			prop.storeToXML(fos, comment);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	protected Properties loadProperties(File file) {
		Properties prop = new Properties();
		
		try
		{
			prop.loadFromXML(new FileInputStream(file));
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return prop;
	}
	

	
	
	protected static final String dataDirectory = ".dataDirectory"; 
	
	protected File chooseFile(String choosertitle, String type) {
		JFileChooser chooser;
		String extention = "graphindex";
		FileNameExtensionFilter filter = new FileNameExtensionFilter("graphindex properties xml ", "graphindex");
		

		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File(UiSettings.recoverString(this.settingsKey+dataDirectory, "")));
		chooser.setDialogTitle(choosertitle);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false); // disable the "All files" option.
		chooser.setFileFilter(filter); // only show files with extention

		int option = -1;
		if ("open".equals(type)) {
			option = chooser.showOpenDialog(this);
		} else if ("save".equals(type)) {
			option = chooser.showSaveDialog(this);
		}
		
		if (option == JFileChooser.APPROVE_OPTION)
		{
			UiSettings.saveString(this.settingsKey+dataDirectory, chooser.getSelectedFile().getParent());
			File selectedFile = chooser.getSelectedFile();
			
			if (!extention.equals(this.getFileExtention(selectedFile))) {
				selectedFile = new File(selectedFile.getAbsoluteFile() + "." + extention);
			}
			
			return selectedFile;
		} 
		

		
		return null;
	}
	
	
	@Override
	public void componentHidden(ComponentEvent e) {}
	
	
	
	@Override
	public void componentMoved(ComponentEvent e) {}
	
	
	
	@Override
	public void componentResized(ComponentEvent e)
	{
		this.rearange();
	}
	
	
	
	@Override
	public void componentShown(ComponentEvent e) {}
	
	@Override
	public void addValue()
	{
		ArrayList<ComponentValue<?>> values = new ArrayList<ComponentValue<?>>();
	
		for ( ComponentValue<?> value  : this.valueList)
		{
			if (!this.isOpen(value)) {
				values.add(value);
			}
				
				
		}
		
	
		ComponentValue<?> value = (ComponentValue<?>)JOptionPane.showInputDialog(
		                    this,
		                    "Chose value",
		                    "Chose value",
		                    JOptionPane.PLAIN_MESSAGE,
		                    null, // icon
		                    values.toArray(),
		                    "");
	
		if (value != null)
		{
			this.addValueView(value);
		}
	}
	
	
	
	
	protected boolean isOpen(ComponentValue<?> value)
	{
		for	(ValueGraphDisplay valueDisplay : valueDisplays) {
			if (valueDisplay.getValue().getName().equals(value.getName())) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public void sizeChanged(ValueGraphDisplay graphDisplay)
	{
		this.rearange();
		
	}
	
	
	@Override
	public void removeValue(ComponentValue<?> value)
	{
		
		Iterator<ValueGraphDisplay> itr = valueDisplays.iterator();
		while (itr.hasNext()) {
			ValueGraphDisplay valueDisplay = itr.next();
			
			if (valueDisplay.getValue() == value) {
				this.removeValueView(valueDisplay);		
				break;
			} 
		}
	}
	
	
	int actualTime;
	
	class ValueRefreshTask extends TimerTask
	{
		
			
		public void run() 
		{
			System.out.println(actualTime);
			DataGraph.this.actualTime += 10;
			for (ValueGraphDisplay valueDisplay : valueDisplays)
			{
				valueDisplay.update();
		
			}
		}
			
	}

}
