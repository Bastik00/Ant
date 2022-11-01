package de.hska.lat.robot.pose.view;


import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


import de.hska.lat.robot.abstractRobot.AbstractRobot;
//import de.hska.lat.robot.abstractRobot.AbstractRobot;
import de.hska.lat.robot.displayFrame.DisplayFrame;
import de.hska.lat.robot.pose.Pose3D;
import de.hska.lat.robot.pose.PoseListener;

public class PoseControlView extends DisplayFrame implements PoseListener
{


	/**
	 * 
	 */
	private static final long serialVersionUID = -4900962911605371114L;

	
	protected JTextField [] position = new JTextField[3];
	protected JTextField [] heading = new JTextField[3];
	
	Pose3D pose;
	
public PoseControlView()
{
	super("pose data" , false, true, false, true);

	
	this.setLayout(null);
	
	this.position[Pose3D.X_AXIS] = this.addTextField(5, 5, 120, "x position");
	this.position[Pose3D.Y_AXIS] = this.addTextField(5, 45, 120, "y position");
	this.position[Pose3D.Z_AXIS] = this.addTextField(5, 85, 120, "z position");
	
	this.heading[Pose3D.X_AXIS] = this.addTextField(150, 5, 80, "x°");
	this.heading[Pose3D.Y_AXIS] = this.addTextField(150, 45, 80, "y°");
	this.heading[Pose3D.Z_AXIS] = this.addTextField(150, 85, 80, "z°");
	

	
	this.setSize(250,160);
	
	this.show();
}


public boolean setRobot(AbstractRobot<?,?,?> robot)
{

	this.pose =robot.getPose();
	this.pose.addPoseListener(this);
	poseChanged(this.pose);
	
	return(true);
}



public JTextField addTextField(int x, int y,int width, String name)
{
	
	JTextField tmpLabel;
	
	tmpLabel = new JTextField();
	tmpLabel.setBorder(new TitledBorder(name));
	tmpLabel.setBounds(x, y, width, 35);
	this.add(tmpLabel);
	
	return(tmpLabel);
}




@Override
public void poseChanged(Pose3D pose)
{

//	float[] position;
//	float[] heading;
	
	//position = pose.getPosition();
//	heading = pose.getNormalizedHeading();
/*
	this.position[Pose3D.X_AXIS].setText(ComponentValue.toFormatedFractionString(position[Pose3D.X_AXIS],2)+"mm");
	this.position[Pose3D.Y_AXIS].setText(ComponentValue.toFormatedFractionString(position[Pose3D.Y_AXIS],2)+"mm");
	this.position[Pose3D.Z_AXIS].setText(ComponentValue.toFormatedFractionString(position[Pose3D.Z_AXIS],2)+"mm");
	
	
	this.heading[Pose3D.X_AXIS].setText(ComponentValue.toFormatedFractionString(Radiant.convertRadiantToDegree(heading[Pose3D.X_AXIS]),2)+"°");
	this.heading[Pose3D.Y_AXIS].setText(ComponentValue.toFormatedFractionString(Radiant.convertRadiantToDegree(heading[Pose3D.Y_AXIS]),2)+"°");
	this.heading[Pose3D.Z_AXIS].setText(ComponentValue.toFormatedFractionString(Radiant.convertRadiantToDegree(heading[Pose3D.Z_AXIS]),2)+"°");
*/
	
}

	




@Override
protected void onClosing()
{
	this.pose.removePoseListener(this);	
}

	
}
