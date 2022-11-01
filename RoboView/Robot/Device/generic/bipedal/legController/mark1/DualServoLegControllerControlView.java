package de.hska.lat.robot.device.generic.bipedal.legController.mark1;






import de.hska.lat.robot.component.actor.servo.ServoSet;
import de.hska.lat.robot.component.servo.view.ServoControlView;
import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.device.generic.bipedal.legController.mark1.LegController;
import de.hska.lat.robot.device.viewer.DeviceView;



public class DualServoLegControllerControlView  extends DeviceView
{


/**
	 * 
	 */
	private static final long serialVersionUID = 1056248882130933204L;



public DualServoLegControllerControlView() 
{
}



public void makeDisplay(String robotName, LegController legController)
{
	ServoSet servos;
	
	this.setDevice(robotName, legController);
	
	servos = legController.getServos();


	
	
	ComponentView hipdServo = ServoControlView.createView(servos.get(0));
	ComponentView footServo = ServoControlView.createView(servos.get(1));
	
		
	addComponent(hipdServo,5,5);
	addComponentAtBottom(hipdServo,footServo,5,5);
	

	this.autoResize();
	
	
}







}

