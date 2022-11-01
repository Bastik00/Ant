package de.hska.lat.robot.device.generic.bipedal.legController.mark1;





import de.hska.lat.robot.device.generic.bipedal.legController.mark1.LegController;
import de.hska.lat.robot.device.viewer.DeviceView;

public class DualServoLegControllerDataView extends DeviceView
{



/**
	 * 
	 */
	private static final long serialVersionUID = 229025960287452693L;


public DualServoLegControllerDataView()
{
}


public void makeDisplay(String robotName, LegController legController)
{
//	ServoSet servos;
//	CurrentSensorSet currents;

	this.setDevice(robotName, legController);
	
	
	
//	servos = legController.getServos();
//	currents = legController.getCurrents();

	
	/*
	ComponentView hipdServo = ServoValueView.createView(servos.get(0));
	ComponentView footServo = ServoValueView.createView(servos.get(1));
	*/
//	ComponentView hipdServoCurrent = CurrentValueView.createView(currents.get(0).getValue());
//	ComponentView footServoCurrent = CurrentValueView.createView(currents.get(1).getValue());
	 
	
	
//	ComponentView accView =AccDataView.createView(legController.getAcc());
	
	/*	
	this.addComponent(hipdServo,5,5);
	this.addComponentAtBottom(hipdServo,hipdServoCurrent,0,5);

	this.addComponentAtBottom(hipdServoCurrent,footServo,0,5);
	this.addComponentAtBottom(footServo,footServoCurrent,0,5);
	
	this.addComponentAtRight(hipdServo,accView,5,0);
	this.autoResize();
	
	*/
}







}
