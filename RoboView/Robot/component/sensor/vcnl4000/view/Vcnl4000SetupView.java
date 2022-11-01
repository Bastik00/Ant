package de.hska.lat.robot.component.sensor.vcnl4000.view;


import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.border.LineBorder;


import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.RobotComponent;
import de.hska.lat.robot.component.sensor.vcnl4000.DistanceTable;
import de.hska.lat.robot.component.sensor.vcnl4000.Vcnl4000AveragingModes;
import de.hska.lat.robot.component.sensor.vcnl4000.Vcnl4000DistanceSensor;
import de.hska.lat.robot.component.sensor.vcnl4000.Vcnl4000DistanceSensorSettingsChangeNotifier;
import de.hska.lat.robot.component.sensor.vcnl4000.Vcnl4000FrequencyModes;
import de.hska.lat.robot.component.sensor.vcnl4000.Vcnl4000IrCurrent;
import de.hska.lat.robot.component.sensor.vcnl4000.Vcnl4000;
import de.hska.lat.robot.component.view.ComponentSettingsView;
import de.hska.lat.robot.component.view.ComponentView;



import de.hska.lat.robot.value.view.MissingValueView;


public class Vcnl4000SetupView extends ComponentSettingsView<Vcnl4000>  implements ComponentSettingsChangeNotifier, Vcnl4000DistanceSensorSettingsChangeNotifier 


{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6199812393064386683L;
	
	
	
	protected JComboBox<Vcnl4000IrCurrent> currentSelector;
	protected JComboBox<Vcnl4000AveragingModes> averagingSelector;
	protected JComboBox<Vcnl4000FrequencyModes> frequencySelector;
	
	protected static final String AVERAGING_TEXT = "averaging";
	protected static final String AUTO_COMPENSATION_TEXT = "auto offset";
	protected static final String AUTO_CONVERSION_TEXT = "auto conversion";
	
	protected static final String FREQUENCY_TEXT = "IR frequency";
	protected static final String IR_CURRENT_TEXT	= "IR current";
	protected static final String CALIBRATION_TEXT	= "calibration";
	protected static final String CMD_CALIBRATION	= "cmdCalibration";
	

	
	protected static final String WRITE_TEXT		= "write";
	protected static final String FETCH_TEXT		= "fetch";

	
	protected static final String EXPORT_TEXT		= "export";
	protected static final String CMD_IMPORT		= "cmdImport";

	protected static final String IMPORT_TEXT		= "import";
	protected static final String CMD_EXPORT		= "cmdExport";

	
	
	protected static final int width = 310;
	protected static final int HEIGHT = 105;
	protected static final int HEIGHT_CALIBRATION = 270;
	
	
	
	protected JCheckBox autoConversionCheckBox;
	protected JCheckBox autoCompensationCheckBox;
	
	protected boolean calibration = false;
	
	protected JMenuItem showCalibrationMenueItem;
	protected Vcnl4000DistanceTable distanceTableView =new Vcnl4000DistanceTable();
	
public Vcnl4000SetupView(Vcnl4000 sensor)
{
	super(sensor);
	
	this.buildView();
	
	this.component.addSetupListener(this);
	
	this.component.getDistanceSensor().addSetupListener(this);
	

}


@Override
protected void buildView()
{
	
	super.buildView();

	
	Insets insets = this.getBorder().getBorderInsets(this);
	
	
	JLabel description;
	
	
	

	
	
	description = new JLabel(Vcnl4000SetupView.AVERAGING_TEXT);
	description.setBounds(insets.left+130,insets.top+5,80,22);
	this.add(description);
	
	
	this.averagingSelector = new JComboBox<Vcnl4000AveragingModes>();
	
	for(Vcnl4000AveragingModes rate : Vcnl4000AveragingModes.values())
	{
		this.averagingSelector.addItem(rate);
	}
	this.averagingSelector.setBounds(insets.left+200,insets.top+5,110,22);
	this.add(this.averagingSelector);
	
	this.autoCompensationCheckBox = new JCheckBox(AUTO_COMPENSATION_TEXT);
	this.autoCompensationCheckBox.setBounds(insets.left+5,insets.top+5,110,22);
	this.add(this.autoCompensationCheckBox);
	

	this.autoConversionCheckBox = new JCheckBox(AUTO_CONVERSION_TEXT);
	this.autoConversionCheckBox.setBounds(insets.left+5,insets.top+30,110,22);
	this.add(this.autoConversionCheckBox);
	
	
	description = new JLabel(Vcnl4000SetupView.IR_CURRENT_TEXT);
	description.setBounds(insets.left+130,insets.top+30,80,22);
	this.add(description);

	
	
	
	this.currentSelector = new JComboBox<Vcnl4000IrCurrent>();
	
	for(Vcnl4000IrCurrent current : Vcnl4000IrCurrent.values())
	{
		this.currentSelector.addItem(current);
	}
	this.currentSelector.setBounds(insets.left+200,insets.top+30,110,22);
	this.add(this.currentSelector);
	
	
	
	
	
	description = new JLabel(Vcnl4000SetupView.FREQUENCY_TEXT);
	description.setBounds(insets.left+130,insets.top+55,80,22);
	this.add(description);
		
	
	this.frequencySelector = new JComboBox<Vcnl4000FrequencyModes>();
	for(Vcnl4000FrequencyModes frequency : Vcnl4000FrequencyModes.values())
	{
		this.frequencySelector.addItem(frequency);
	}
	this.frequencySelector.setBounds(insets.left+200,insets.top+55,110,22);
	this.add(this.frequencySelector);
	
	
	
	
	this.addSetButton(insets.left+5, insets.top+55, 50, 22);
	this.addGetButton(insets.left+60, insets.top+55, 50, 22);
	
	this.addSaveButton(insets.left+5, insets.top+80, 50, 22);
	this.addLoadButton(insets.left+60, insets.top+80, 50, 22);


	
	if (this.calibration)
		this.addCalibrationView(insets);
	
}

protected void addCalibrationView(Insets insets)
{
	int index;
	
	this.distanceTableView= new Vcnl4000DistanceTable();
	distanceTableView.setBounds(insets.left+5, insets.top+105, 200, 155);
	this.add(distanceTableView);
	
	JButton tmpButton;
	
	for (index =0; index<8 ;index++)
	{
		tmpButton = new JButton("\u21BB");
		tmpButton.setBounds(insets.left+205, insets.top+129+index*16, 30, 16);
		tmpButton.setBorder(new LineBorder(Color.GRAY));
		tmpButton.setActionCommand(""+index);
		tmpButton.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent actionEvent)
			{
				
				try
				{
					Vcnl4000SetupView.this.component.remote_getRawProximityValue();
					Thread.sleep(100);
					
					Vcnl4000SetupView.this.distanceTableView.getDistanceTable().setProximityValue(
								Integer.valueOf(actionEvent.getActionCommand()), 
								Vcnl4000SetupView.this.component.getDistanceSensor().getProximityValue());
					
					Vcnl4000SetupView.this.distanceTableView.invalidate();
					Vcnl4000SetupView.this.distanceTableView.repaint();
				} catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		this.add(tmpButton);
	}
	
	
	
	
	tmpButton = new JButton(Vcnl4000SetupView.WRITE_TEXT);
	tmpButton.setBounds(insets.left+245, insets.top+105, 60, 25);
	tmpButton.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			Vcnl4000SetupView.this.component.remote_setDistanceTable(Vcnl4000SetupView.this.distanceTableView.getDistanceTable());;
		}
		
	});
	this.add(tmpButton);
	tmpButton.setBorder(new LineBorder(Color.GRAY));
	this.add(tmpButton);
	
	

	tmpButton = new JButton(Vcnl4000SetupView.FETCH_TEXT);
	tmpButton.setBounds(insets.left+245, insets.top+130, 60, 25);
	tmpButton.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			Vcnl4000SetupView.this.component.remote_getDistanceTable();
		}
		
	});
	tmpButton.setBorder(new LineBorder(Color.GRAY));
	this.add(tmpButton);
	
	tmpButton = new JButton(Vcnl4000SetupView.EXPORT_TEXT);
	tmpButton.setBounds(insets.left+245, insets.top+155, 60, 25);
	tmpButton.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			Vcnl4000SetupView.this.exportDistanceTable();
		}
		
	});
	tmpButton.setBorder(new LineBorder(Color.GRAY));
	this.add(tmpButton);
	
	
	tmpButton = new JButton(Vcnl4000SetupView.IMPORT_TEXT);
	tmpButton.setBounds(insets.left+245, insets.top+180, 60, 25);
	tmpButton.setActionCommand(Vcnl4000SetupView.CMD_IMPORT);
	tmpButton.addActionListener(this);
	tmpButton.setBorder(new LineBorder(Color.GRAY));
	this.add(tmpButton);	
}


//protected static final Object [] [] DISTANCE_TABLE_COLLUMNS = {{"distance",new CsvDataInteger()},{"value", new CsvDataInteger()}};



protected void exportDistanceTable()
{
	Object [] dataRow = new Object[2];
	// TODO Auto-generated method stub
	
//	CsvHeadRow csvHead = new CsvHeadRow();
//	
//	csvHead.addColumn("test",new CsvDataInteger(0));
//	csvHead.addColumn("test2",new CsvDataInteger(0));
//	
//	Csv csv = new Csv(csvHead);
//
//	DistanceTable distanceTable;
//	
//	
//	
//	distanceTable = this.distanceTableView.getDistanceTable();
//	
//	int index;
//	
//	index=0;
//	
//	// for
//	dataRow[0] = new CsvDataInteger(distanceTable.getDistance(index));
//	dataRow[1] = new CsvDataInteger(distanceTable.getProximityValue(index));
//	csv.addDataRow(dataRow);
//	
//	
//	
//	csv.write();
}


@Override
protected int getViewWidth()
{
	return(Vcnl4000SetupView.width);
}

@Override
protected int getViewHeight()
{
	if (this.calibration)
		return(Vcnl4000SetupView.HEIGHT_CALIBRATION);
	
	
	return(Vcnl4000SetupView.HEIGHT);
}


@Override
protected void makePopupMenu()
{
	
	super.makePopupMenu();
	this.showCalibrationMenueItem = this.addCheckBoxMenuItem(this.contextMenue , Vcnl4000SetupView.CALIBRATION_TEXT, Vcnl4000SetupView.CMD_CALIBRATION);
}





private void update()
{
	this.currentSelector.setSelectedItem(this.component.getIrCurrent());
	this.averagingSelector.setSelectedItem(this.component.getAveraging());
	this.frequencySelector.setSelectedItem(this.component.getProximityFrequency());
	this.autoConversionCheckBox.setSelected(this.component.getAutoConversion());
	this.autoCompensationCheckBox.setSelected(this.component.getAutoCompensation());
}



@Override
protected boolean setSettings()
{
	this.component.remote_setSettings(
	(Vcnl4000AveragingModes)this.averagingSelector.getSelectedItem(),
	(Vcnl4000FrequencyModes)this.frequencySelector.getSelectedItem(),
	(Vcnl4000IrCurrent)this.currentSelector.getSelectedItem(),
	
	this.autoConversionCheckBox.isSelected(),
	this.autoCompensationCheckBox.isSelected());
	
	return(false);
}




/**
 * creates new servo data view and link it given servo 
 * @param servo servo to be connected with created view
 * @return a new servo data view
 */

public static ComponentView createView(Vcnl4000 sensor)
{

	if (sensor!=null)
	{
		return(new Vcnl4000SetupView(sensor));
	}
	else
	{
		return(new MissingValueView(Vcnl4000.class.getName()));
	}
}


@Override
public void settingsChanged(RobotComponent<?, ?, ?> component)
{
	this.update();
}










@Override
public void actionPerformed(ActionEvent actionEvent) 
{
	

	String cmd;
	
	cmd=actionEvent.getActionCommand();
	
	if (cmd.equals(Vcnl4000SetupView.CMD_CALIBRATION))
	{
		this.calibration = showCalibrationMenueItem.isSelected();
		this.buildView();
	}
	else
	{
		System.out.println(cmd);
		super.actionPerformed(actionEvent);
	}
}



@Override
public void distanceTableChanged(Vcnl4000DistanceSensor sensor)
{
	this.distanceTableView.setDistanceTable(sensor.getDistanceTable());
}



}
