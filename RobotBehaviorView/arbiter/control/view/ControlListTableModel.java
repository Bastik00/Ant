package de.hska.lat.behavior.arbiter.control.view;

import javax.swing.table.AbstractTableModel;


import de.hska.lat.behavior.control.BehaviorControlSet;




public class ControlListTableModel extends AbstractTableModel
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 666090521702879787L;

	
	
	private static final String [] COLUMNS_NAME = {"priority","name","status"};

	protected BehaviorControlSet controls; 

	
//2012.2.8
public ControlListTableModel()
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
	return(this.controls.size());
}


//2012.2.8
@Override
public Object getValueAt(int row, int column) 
{
	
	
	if (this.controls==null)
		return("----");
	/*
	ArbiterAction action = this.actions.getAction(row);
	
	if (action!=null)
	{
		switch(column)
		{
		case 0:
			return((Integer) action.getPrioryty());
			
		
		case 1:
			return(action.getName());
		
			
		case 2:
			return(action.getStatus());
			/*	
		
		
		}	
	}*/
//	return(this.dataPcketLoger.get(row).getDataAsHexString()); 
	return null;
}




/*
public void setActions(ArbiterActionList actions)
{
	// TODO Auto-generated method stub
	this.controls = controls;
}

*/






}

