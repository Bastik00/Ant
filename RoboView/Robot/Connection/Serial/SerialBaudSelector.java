package de.hska.lat.robot.connection.serial.view;

import javax.swing.JComboBox;

public class SerialBaudSelector  extends JComboBox<String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
public SerialBaudSelector()
{
	super();
	
	this.addItem("115200");	
}


}
