package de.hska.lat.robot.dataPacketLogger.viewer;

import javax.swing.table.AbstractTableModel;

import de.hska.lat.comm.packetLogger.DataPacketLogger;
import de.hska.lat.comm.packetLogger.LoggedDataPacket.DisplayDataWidth_e;
import de.hska.lat.comm.packetLogger.LoggedDataPacket.DisplayFormat_e;
import de.hska.lat.robot.abstractRobot.device.AbstractRobotDeviceList;
import de.hska.lat.robot.connection.Connection;



public class DataPacketTableModel extends AbstractTableModel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private static final String [] COLUMNS_NAME = {"nr","timestamp","type","direction","destination","source","name","data"};

	protected DataPacketLogger dataPcketLoger;
	protected AbstractRobotDeviceList<?> deviceList;


	
//2012.2.8
public DataPacketTableModel()
{
	// load dataWidth !!!
}


/*
protected String getAsRaw(int row)
{
	String dataString;
	int value;
	
	switch(this.dataWidth)
	{
	case WIDTH_16:
			return(this.dataPcketLoger.get(row).getDataAsString16(false));

		
	case WIDTH_24:
			return(this.dataPcketLoger.get(row).getDataAsString24());

		
	case WIDTH_32:
			return(this.dataPcketLoger.get(row).getDataAsString32());

		
	case WIDTH_8:
			return(this.dataPcketLoger.get(row).getDataAsString());

	default:
		break;
	
	}
	//return(this.dataPcketLoger.get(row).getDataAsHexString());
}
*/
/*
protected String getAsNative()
{
	
}
*/

public void setPacketLogger(DataPacketLogger packetLoger)
{
	this.dataPcketLoger=packetLoger;
}


public void setDeviceList(AbstractRobotDeviceList<?> deviceList)
{
	this.deviceList=deviceList;
}

//2012.2.8
@Override
public String getColumnName(int column)
{
	return(COLUMNS_NAME [column]);	
}	


//2012.2.8
@Override
public int getColumnCount() 
{
	return (COLUMNS_NAME.length);
}


//2012.2.8
@Override
public int getRowCount() 
{
	return(this.dataPcketLoger.size());

}


//2012.2.8
@Override
public Object getValueAt(int row, int column) 
{
	int deviceID;
	
	try
	{
	if (this.dataPcketLoger==null)
		return("----");
	
	switch(column)
	{
	case 0:
		return(new Integer(this.dataPcketLoger.get(row).getNumber()));
		
	case 1:
		return(this.dataPcketLoger.get(row).getTimestamp());
	
		
	case 2:	
		return(this.dataPcketLoger.get(row).getTypeName());

		
	case 3:	
		return(this.dataPcketLoger.get(row).getDirectionAsString());
		
		
	case 4:
		
		deviceID = this.dataPcketLoger.get(row).getDestination();
		//TODO use actual connection id
		if (Connection.REMOTE_CHANEL_ID == deviceID)
		{
			return("Connection");
		}
		else
		{
			return(this.deviceList.getDeviceName(deviceID));
		}
	
	case 5:
	//TODO use actual connection id
		deviceID = this.dataPcketLoger.get(row).getSource();
		
		if (Connection.REMOTE_CHANEL_ID == deviceID)
		{
			return("Connection");
		}
		else
		{
			return(this.deviceList.getDeviceName(deviceID));
		}
		

	
	case 6:
		
		return(this.dataPcketLoger.get(row).getCommandName()+" ("+dataPcketLoger.get(row).getCommand()+")");
	
		
	case 7:	
		
		DisplayDataWidth_e dataWidth = this.dataPcketLoger.getStandardDataWidth();
		DisplayFormat_e dataFormat = this.dataPcketLoger.getStandardDataFormat();
		
		switch (dataFormat)
		{
		case DECIMAL:
			return(this.dataPcketLoger.get(row).getDataAsString(dataWidth,false));


		case HEXADECIMAL:
			return(this.dataPcketLoger.get(row).getDataAsString(dataWidth,true));

			
		case NATIVE:
			return(this.dataPcketLoger.get(row).getParametersAsString(false));
		
		
		case NATIVE_WITH_DESCRIPTION:
			return(this.dataPcketLoger.get(row).getParametersAsString(true));
		
		}
		
		return(this.dataPcketLoger.get(row).getDataAsString(dataWidth,false));
		/*
		switch(this.dataWidth)
		{
		case WIDTH_16:
				return(this.dataPcketLoger.get(row).getDataAsString16());

			
		case WIDTH_24:
				return(this.dataPcketLoger.get(row).getDataAsString24());

			
		case WIDTH_32:
				return(this.dataPcketLoger.get(row).getDataAsString32());

			
		case WIDTH_8:
				return(this.dataPcketLoger.get(row).getDataAsString());

		default:
			break;
		
		}*/
		//return(this.dataPcketLoger.get(row).getDataAsHexString());
		
	}	
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
//	return(this.dataPcketLoger.get(row).getDataAsHexString()); 
	return null;
}




public void setSize(int newSize)
{
	this.dataPcketLoger.setMaxSize(newSize);
}








}

