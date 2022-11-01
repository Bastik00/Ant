package de.hska.lat.robot.component.sensor.mlx90614.view;


import java.awt.Insets;


import javax.swing.JLabel;
import javax.swing.JSpinner;



import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.RobotComponent;
import de.hska.lat.robot.component.temperatureSensor.mlx90614.Mlx90614;
import de.hska.lat.robot.component.view.ComponentSettingsView;
import de.hska.lat.robot.component.view.ComponentView;


import de.hska.lat.robot.value.view.MissingValueView;


public class Mlx90614SetupView extends ComponentSettingsView<Mlx90614>  implements ComponentSettingsChangeNotifier
{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8543072951844327734L;
	
	
	protected static final int width = 250;
	protected static final int height = 90;
	
	
	protected static final String ADDRES_TEXT = "i2C Addres";
	protected static final String EMISSIVITY_TEXT = "emisivity";
	
	protected JSpinner address;
	protected I2cAddressSpinnerModel addressSpinerModel;
	
	protected JSpinner emissivity;
	protected Mlx90614EmissivitySpinnerModel	emissivitySpinnerModel;
	
	
public Mlx90614SetupView(Mlx90614 sensor)
{
	super(sensor);
	
	component.addSetupListener(this);
	
	buildView();
}


@Override
protected void buildView()
{
	
	super.buildView();

	
	Insets insets = this.getBorder().getBorderInsets(this);
	
	
	JLabel description;
	
	description = new JLabel("lux");
	description.setBounds(10,insets.top+5,80,20);
	this.add(description);
	

	
	description= new JLabel(ADDRES_TEXT);
	description.setBounds(insets.left+5, insets.top+5, 80, 25);
	this.add(description);
	 
	
	this.addressSpinerModel = new I2cAddressSpinnerModel();
	
	this.address = new JSpinner(addressSpinerModel);
	this.address.setBounds(insets.left+100, insets.top+5, 60, 25);
//	this.address.addChangeListener(this);
	add(this.address);
	
	
	description= new JLabel(EMISSIVITY_TEXT);
	description.setBounds(insets.left+5, insets.top+30, 80, 25);
	this.add(description);
	 
	
	this.emissivitySpinnerModel = new Mlx90614EmissivitySpinnerModel();
	
	this.emissivity = new JSpinner(emissivitySpinnerModel);
	this.emissivity.setBounds(insets.left+100, insets.top+30, 60, 25);
//	this.address.addChangeListener(this);
	add(this.emissivity);
	
	
	this.addSetButton(insets.left+5, insets.top+60, 50, 22);
	this.addGetButton(insets.left+60, insets.top+60, 50, 22);
	
	this.addSaveButton(insets.left+115, insets.top+60, 50, 22);
	this.addLoadButton(insets.left+170, insets.top+60, 50, 22);
	
}

@Override
protected int getViewWidth()
{
	return(Mlx90614SetupView.width);
}

@Override
protected int getViewHeight()
{
	return(Mlx90614SetupView.height);
}


@Override
protected boolean setSettings()
{
	/*return(this.component.remote_setSettings(
			((Number)this.addressSpinerModel.getValue()).intValue(),
	//		((Number)this.emissivitySpinnerModel.getValue()).intValue()
			));
*/
	return(false);
}







/**
 * creates new servo data view and link it given servo 
 * @param servo servo to be connected with created view
 * @return a new servo data view
 */

public static ComponentView createView(Mlx90614 sensor)
{

	if (sensor!=null)
	{
		return(new Mlx90614SetupView(sensor));
	}
	else
	{
		return(new MissingValueView(Mlx90614.class.getName(), false));
	}
}


@Override
public void settingsChanged(RobotComponent<?, ?, ?> component)
{
	this.addressSpinerModel.setValue(""+this.component.getI2CAddress());
	this.emissivitySpinnerModel.setValue(""+this.component.getEmissivity());
}




}
