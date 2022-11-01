package de.hska.lat.robot.device.generic.bipedal.legController.mark1;


import de.hska.lat.robot.component.actor.servo.ServoSet;
import de.hska.lat.robot.component.currentSensor.CurrentSensorSet;
import de.hska.lat.robot.component.currentSensor.CurrentSetupView;
import de.hska.lat.robot.component.generic.accelerometer.view.AccelerometerSetupView;

import de.hska.lat.robot.component.servo.view.ServoSetupView;
import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.device.generic.bipedal.legController.mark1.LegController;
import de.hska.lat.robot.device.viewer.DeviceView;

public class DualServoLegControllerSetupView extends DeviceView
{


/**
	 * 
	 */
	private static final long serialVersionUID = 8671102471653868034L;


public DualServoLegControllerSetupView( ) 
{

}


public void makeDisplay(String robotName, LegController legController)
{
	ServoSet servos;
	CurrentSensorSet currents;
	
	this.setDevice(robotName, legController);
	
	servos = legController.getServos();
	currents = legController.getCurrents();

	
	
	ComponentView hipdServo = ServoSetupView.createView (servos.get(0));
	ComponentView footServo = ServoSetupView.createView (servos.get(1));
	
	ComponentView hipdServoCurrent = CurrentSetupView.createView(currents.get(0));
	ComponentView footServoCurrent = CurrentSetupView.createView(currents.get(1));
	 
	
	
	ComponentView accView = AccelerometerSetupView.createView(legController.getAcc());
	
	
	this.addComponent(hipdServo,5,5);
	this.addComponentAtBottom(hipdServo,hipdServoCurrent,0,5);

	this.addComponentAtBottom(hipdServoCurrent,footServo,0,5);
	this.addComponentAtBottom(footServo,footServoCurrent,0,5);
	
	this.addComponentAtRight(hipdServo,accView,5,0);
	
	this.autoResize();
	
	
}




}
