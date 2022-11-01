package de.hska.lat.robot.display.generics.map2D;

import javax.swing.JInternalFrame;



public class MapControlElement extends JInternalFrame//extends ElementView
{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	protected enum MapControlMode_e {MODE_STANDARD, MODE_PATH, MODE_DESTINATION};
	
	protected MapControlMode_e controlMode;
	
	protected MapEventListener eventListener;
	
public MapControlElement( boolean frame, MapControlMode_e controlMode, MapEventListener eventListener) 
{
//	super( frame);
	this.controlMode =controlMode;
	this.eventListener = eventListener;
}

	
public MapControlMode_e getControlMode()
{
	return(this.controlMode);
}

}
