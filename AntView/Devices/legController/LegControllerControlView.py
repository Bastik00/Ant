from RoboView.Robot.Device.Viewer.DeviceView import DeviceView
from RoboView.Robot.component.actor.servo.view.ServoControlView import ServoControlView



class LegControllersControlView(DeviceView):
	def __init__(self, device) :
		super().__init__( "Leg Controller Control", device, 100, 100 ,200, 200)
		

		self.make_display(device)
		

	def make_display(self, device):

		servos = device.get_servo_set()
		for servo in servos:
			print("servo")
			view = ServoControlView.create_view(self._display , servo, self._settings_key)
			if view is not None:
				view._frame.place(x = 50, y = 50)
				
"""				package de.hska.lat.ant.devices.legController;

import de.hska.lat.ant.metaData.AntDeviceId;
import de.hska.lat.robot.abstractRobot.AbstractRobot;

import de.hska.lat.robot.component.servo.view.ServoControlView;
import de.hska.lat.robot.component.actor.servo.Servo;
import de.hska.lat.robot.component.actor.servo.ServoSet;
import de.hska.lat.robot.device.generic.motionController.MotionControllerControlView;



public class AntLegControllerControlView extends MotionControllerControlView
{



/**
	 * 
	 */
	private static final long serialVersionUID = -5337372269809597680L;

@Override
public boolean setRobot(AbstractRobot<?,?,?> robot)
{
	LegController legController;
	
	legController=(LegController)robot.getDeviceOnId(AntDeviceId.LEG_CONTROLLER.getId());
	
			
	if (legController!=null)
	{
		this.makeDisplay(robot.getName(), legController);
		return(true);
	}
	else
	{
		makeErrorDisplay(LegController.class.getName());
		return(false);
	}
}
	


public void makeDisplay(String robotName, LegController motionController)
{
	
	this.setDevice(robotName, motionController);
	
	

	this.addServos(motionController.getServos());
	
	
}



protected void addServos(ServoSet servos)
{

	for (Servo servo : servos)
	{
		this.addComponent(new ServoControlView(servo));
	}
}

	



}
"""