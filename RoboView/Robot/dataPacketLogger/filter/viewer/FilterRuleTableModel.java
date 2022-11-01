package de.hska.lat.robot.dataPacketLogger.filter.viewer;

import javax.swing.JComboBox;
import javax.swing.table.AbstractTableModel;



public class FilterRuleTableModel extends AbstractTableModel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3088773869823233319L;

	private static final String [] COLUMNS_NAME = {"nr","timestamp","type","direction","destination","source","name","data"};

	protected JComboBox<String> box = new JComboBox<String>();
	
	@Override
	public String getColumnName(int column)
	{

		return(COLUMNS_NAME [column]);	
	}	

/*	
	@Override
	public Class<?> getColumnClass(int column)
	{
System.out.println("get class");
		return(box.getClass());	
	}	
*/
	@Override
	public boolean isCellEditable(int rowIndex,
            int columnIndex)
	{
		return(true);
	}
	@Override
	public int getColumnCount()
	{
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public int getRowCount()
	{
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public Object getValueAt(int arg0, int arg1)
	{
		String test[] = {"dd","aaa"}; 

		// TODO Auto-generated method stub
		return ("asdfgf");
	}

}
