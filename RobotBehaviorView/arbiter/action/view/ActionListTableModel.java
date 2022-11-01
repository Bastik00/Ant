package de.hska.lat.behavior.arbiter.action.view;

import javax.swing.table.AbstractTableModel;

import de.hska.lat.behavior.arbiter.Arbiter;
import de.hska.lat.behavior.arbiter.ArbiterActionList;
import de.hska.lat.behavior.arbiter.action.ArbiterAction;
import de.hska.lat.behavior.behavior.RobotBehavior;




public class ActionListTableModel extends AbstractTableModel
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 666090521702879787L;

	
	
	private static final String [] COLUMNS_NAME = {"priority","name","status"};

	protected ArbiterActionList actions;
	protected RobotBehavior<?> behavior;
	
	//protected ArbiterList arbiters;
	
//2012.2.8
public ActionListTableModel(RobotBehavior<?> behavior)
{
	this.behavior = behavior;
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
//	return(this.dataPcketLoger.size());
	return(this.actions.size());
}


//2012.2.8
@Override
public Object getValueAt(int row, int column) 
{
	
	
	if (this.actions==null)
		return("----");
	
	ArbiterAction action = this.actions.getAction(row);
	
	if (action!=null)
	{
		switch(column)
		{
		case 0:
			Arbiter arbiter = this.behavior.getArbiterOnId(action.getPrioryty());
			
			String name;
			
			name = ""+action.getPrioryty();
			
			if (arbiter!=null)
				name +=	 "("+arbiter.getName()+")";
			
			return(name);
			
		
		case 1:
			return(action.getName());
		
			
		case 2:
			return(action.getStatus());
			/*	
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
			*/
		//	return(this.dataPcketLoger.get(row).getDataAsString(dataWidth,false));
		
		
		}	
	}
//	return(this.dataPcketLoger.get(row).getDataAsHexString()); 
	return null;
}





public void setActions(ArbiterActionList actions)
{
	// TODO Auto-generated method stub
	this.actions = actions;
}








}

