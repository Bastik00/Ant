package de.hska.lat.robot.component.sensor.vcnl4000.view;



import javax.swing.table.AbstractTableModel;

import de.hska.lat.robot.component.sensor.vcnl4000.DistanceTable;
import de.hska.lat.robot.component.sensor.vcnl4000.Vcnl4000DistanceSensor;




public class Vcnl4000DistanceTableModel extends AbstractTableModel
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 6059305693949768812L;
	
	
	private static final String [] COLUMNS_NAME = {"nr","mm","value"};


	protected DistanceTable distanceTable = new DistanceTable();

	
//2012.2.8
public  Vcnl4000DistanceTableModel()
{


	

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

public Class<?> getColumnClass(int columnIndex)
{
	return(Integer.class);	
}


@Override
public int getRowCount() 
{
	return(Vcnl4000DistanceSensor.DISTANCE_DATA_DEEPTH);
}

@Override
public boolean isCellEditable(int row, int column)
{
	if ((column==2) || (column==1))
		return(true);
	
	return(false);
}



//2012.2.8
@Override
public Object getValueAt(int row, int column) 
{



	switch(column)
	{
	case 0:
		return(row);
		
	case 1:
		return(distanceTable.getDistance(row));

	
		
	case 2:	
		return(distanceTable.getProximityValue(row));
		
	}

	
	return null;
}





public void setValueAt(Object Value, int rowIndex, int columnIndex)
{

	switch (columnIndex)
	{
	case 1:
		distanceTable.setDistance(rowIndex, ((Integer) Value).intValue());
		break;
	case 2:
		distanceTable.setProximityValue(rowIndex, ((Integer) Value).intValue());
		break;
	}
	
	
}

/**
 * set a distance Table
 * @param distanceTable new distance Table;
 */

public void setDistanceTable(DistanceTable distanceTable)
{
	this.distanceTable = distanceTable;
	this.fireTableRowsUpdated(0, this.getRowCount());
}


/**
 * get this models distance Table
 * @return this models distance table
 */
public DistanceTable getDistanceTable()
{
	return(this.distanceTable);
}




}

