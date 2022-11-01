package de.hska.lat.robot.component.sensor.vcnl4000.view;



import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;

import de.hska.lat.robot.component.sensor.vcnl4000.DistanceTable;

public class Vcnl4000DistanceTable extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2814993150756650074L;

	protected static final int[] COLUMN_WIDTH ={10,80,80};  
	protected Vcnl4000DistanceTableModel tableModel;
	protected JTable distanceTable;
	
	
public Vcnl4000DistanceTable()
{
	
	this.setBorder(new BevelBorder(BevelBorder.LOWERED));
	
	this.setLayout(new BorderLayout());
	this.tableModel = new Vcnl4000DistanceTableModel();
	this.distanceTable = new JTable(this.tableModel);
	this.add(this.distanceTable.getTableHeader(), BorderLayout.PAGE_START);
	this.add(this.distanceTable, BorderLayout.CENTER);
	
	this.distanceTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
	this.distanceTable.setRowSelectionAllowed(false);
	
	
	for (int index = 0; index < COLUMN_WIDTH.length; index++) 
	{
	   this.distanceTable.getColumnModel().getColumn(index).setPreferredWidth(COLUMN_WIDTH[index]);
	}
	
	
	
}
	

public void setDistanceTable(DistanceTable distanceTable)
{
	this.tableModel.setDistanceTable(distanceTable);
}




public DistanceTable getDistanceTable()
{
	return(this.tableModel.getDistanceTable());
}

}
