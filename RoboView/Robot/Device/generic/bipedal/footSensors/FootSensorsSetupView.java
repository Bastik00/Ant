package de.hska.lat.robot.device.generic.bipedal.footSensors;



import java.util.ArrayList;

import de.hska.lat.robot.device.generic.bipedal.footSensors.FootSensors;
import de.hska.lat.robot.component.analogSensor.AnalogSensor;
import de.hska.lat.robot.component.analogSensor.AnalogSensorSetupView;
import de.hska.lat.robot.component.view.ComponentView;

import de.hska.lat.robot.device.viewer.DeviceView;


public class FootSensorsSetupView extends DeviceView
{


/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



public FootSensorsSetupView() 
{
	
}



public void makeDisplay(FootSensors fs, ArrayList<AnalogSensor> sensorList)
{

	setDevice("",fs);
	
	ComponentView leftFrontSensor = AnalogSensorSetupView.createView(sensorList.get(0));
	ComponentView rightFrontSensor = AnalogSensorSetupView.createView(sensorList.get(1));
	ComponentView centerSensor = AnalogSensorSetupView.createView( sensorList.get(2));
	ComponentView leftBackSensor = AnalogSensorSetupView.createView( sensorList.get(3));
	ComponentView rightBackSensor = AnalogSensorSetupView.createView(sensorList.get(4));
	
	
	addComponent(leftFrontSensor,5,5);
	addComponentAtRight(leftFrontSensor,rightFrontSensor,5,0);
	
	addComponentAtBottom(leftFrontSensor,centerSensor,leftFrontSensor.getWidth()/2,0);

	addComponentAtBottom(centerSensor,leftBackSensor,-centerSensor.getWidth()/2,5);
	addComponentAtRight(leftBackSensor,rightBackSensor,0,0);
	
	this.autoResize();
}



public FootSensorsSetupView(FootSensors fs, int[] id) 
{
	super(fs);
	

	ComponentView leftFrontSensor = addAnalogSensor(id[0],fs);
	ComponentView rightFrontSensor = addAnalogSensor( id[1],fs);
	ComponentView leftBackSensor = addAnalogSensor( id[2],fs);
	ComponentView rightBackSensor = addAnalogSensor(id[3],fs);
	ComponentView centerSensor = addAnalogSensor( id[4],fs);
	
	addComponent(leftFrontSensor,5,5);
	addComponentAtRight(leftFrontSensor,rightFrontSensor,5,0);
	
	addComponentAtBottom(leftFrontSensor,centerSensor,leftFrontSensor.getWidth()/2,0);

	addComponentAtBottom(centerSensor,leftBackSensor,-centerSensor.getWidth()/2,5);
	addComponentAtRight(leftBackSensor,rightBackSensor,0,0);
	
	this.autoResize();

}





public ComponentView addAnalogSensor(int globalId, FootSensors fs )
{
	AnalogSensor analogSensor;
	
	analogSensor= (AnalogSensor) fs.findComponentOnGlobalId(globalId);
	
	return(AnalogSensorSetupView.createView(analogSensor));
}




}
