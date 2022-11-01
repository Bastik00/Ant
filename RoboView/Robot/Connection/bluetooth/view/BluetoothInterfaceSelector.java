package de.hska.lat.robot.connection.bluetooth.view;



import java.util.ArrayList;


import javax.bluetooth.RemoteDevice;
import javax.swing.JComboBox;



public class BluetoothInterfaceSelector extends JComboBox<RemoteDevice> {



	

/**
	 * 
	 */
	private static final long serialVersionUID = 7990672984145098654L;





public 	BluetoothInterfaceSelector()
{
	
	super();
	
	this.setRenderer(new BluetoothlInterfaceSelectorCellRederer());
}



public void setList( ArrayList<RemoteDevice> devices)
{
	this.removeAllItems();
	
	
	for (RemoteDevice device: devices)
	{
		this.addItem(device);
	}
	
}



public boolean setSelectedPort(String portToSelect )
{
	
/*	
	int enumerator;
	CommPortIdentifier portIdentifier;
	
	
	for (enumerator=0;enumerator<this.getItemCount();enumerator++)
		{
		portIdentifier=this.getItemAt(enumerator);
		if (portIdentifier.getName().equals(portToSelect))
			{
			this.setSelectedIndex(enumerator);
			return(true);
			}
		}
	*/
	return(false);
}





public String getSelectedPortName()
{
/*	CommPortIdentifier portIdentifier;
	
	
	portIdentifier=this.getItemAt(this.getSelectedIndex());
	if (portIdentifier!=null)
		{
		return(portIdentifier.getName());
		}
	*/
	return("");
}






}
