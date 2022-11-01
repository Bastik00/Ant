package de.hska.lat.robot.component.ircom.view;

import javax.swing.table.AbstractTableModel;



public class IrComTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5389244863309817053L;
	private final static int NR = 0;
	private final static int TIMESTAMP = 1;
	private final static int SOURCE = 3;
	private final static int NAME = 2;
	private final static int TYPE = 4;
	private final static int DATA = 5; 
	
	private static final String[] COLUMN_NAMES = {"Nr", "Timestamp","Name", "Source","Type", "Data"};
	private IrComPackageLogger dataLogger;
	
	// contains the system logger
	/*private DataPacketLogger systemDataLoagger;*/
	
	/**
	 * Creates an empty instance of a irComDataPacketLoggerModel
	 */
	public IrComTableModel() {
		//dataLogger = new IrComPackageLogger();
	}
	
	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}
	
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}
	
	public int getRowCount() {
		return this.dataLogger.size();
	}
	
	public Object getValueAt(int row, int column) {
		
		if (dataLogger == null) {
			return null;
		}
		
		switch (column) {
		case NR: // returns the number of the given column and row
			return dataLogger.get(row).getNumber(); // TODO Check if it needs an integer container
			
		case TIMESTAMP:
			return dataLogger.get(row).getTimestamp();
		
		case SOURCE:
			return dataLogger.get(row).getSource();
		
		case NAME:
			return dataLogger.get(row).getCommandName() + " (" + dataLogger.get(row).getCommand() + ")";
			
		case TYPE:
			return dataLogger.get(row).getTypeName();
			
		case DATA:
			return dataLogger.get(row).getDataAsString();
			
		default: // Unknown
			return null;
		}
		
	}
	
	/*public void setPackageLogger(DataPacketLogger systemDataLoagger) {
		this.systemDataLoagger = systemDataLoagger;
	}*/
	
	public void setIrComPackageLogger(IrComPackageLogger dataLogger) {
		this.dataLogger = dataLogger;
	}
	
	
}
