package de.hska.lat.robot.dataPacketLogger.viewer;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.TableColumn;

import de.hska.lat.comm.packetLogger.DataPacketLogerEvent;
import de.hska.lat.comm.packetLogger.DataPacketLogger;


import de.hska.lat.robot.abstractRobot.AbstractRobot;



import de.hska.lat.robot.displayFrame.DisplayFrame;
import de.hska.lat.robot.ui.settings.UiSettings;



public class DataPacketLogViewer extends DisplayFrame implements DataPacketLogerEvent //,DataPacketLoggerControl
{



	/**
	 * 
	 */
	private static final long serialVersionUID = 717554442873552526L;
	
	

	
	protected JTable table; 
	protected JScrollPane scrollPane;
	
	protected DataPacketTableModel tableModel;
	
	protected PacketLoggerToolBar toolBar;
	protected DataPacketLogger packetLogger = new DataPacketLogger();
	
	
	protected static String LOGER_NAME		= " packet logger";
	
	
public DataPacketLogViewer()
{
	super(" ",true,true,true,true);
//	this.packetLoger =packetLoger;

	buildPanel();
	this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	
	this.recoverColumnWidth();
	
	show();
	
}





private void buildPanel()
{

	this.setLayout(new  BorderLayout());
	
	this.toolBar=new PacketLoggerToolBar();
	
	this.add(toolBar,BorderLayout.PAGE_START);
	
	
	this.scrollPane=new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	this.scrollPane.setViewportBorder(BorderFactory.createLineBorder(Color.black));
	
	this.tableModel = new DataPacketTableModel();
	this.table =new JTable(this.tableModel);


	this.scrollPane.setViewportView(table);	
	
	

	
	this.add(this.scrollPane);		
	
}

@Override
public boolean setRobot(AbstractRobot<?,?,?> robot)
{
	DataPacketLogger packetLogger;
	
	this.setTitle(robot.getName()+ DataPacketLogViewer.LOGER_NAME);
	
	packetLogger = robot.getDataPacketLogger();
	
	this.tableModel.setPacketLogger(packetLogger);
	
	this.packetLogger = packetLogger;
	this.tableModel.setDeviceList(robot.getDeviceList());
	this.toolBar.setListener(packetLogger);
	this.packetLogger.addListener(this);
	
	
	return(true);
}





public static final String STRING_CLEAR = "clear list";
public static final String CMD_CLEAR = "clear list";



public void loggerChange() 
{
	
	this.table.revalidate();
	this.table.repaint();
}




protected static final String columnWidthKey = ".columnWidth"; 

protected void recoverColumnWidth()
{

	int index;
	int columnWidth;
	
	TableColumn column;
	
	for (index=0; index<this.tableModel.getColumnCount() ; index++)
	{
		column = this.table.getColumnModel().getColumn(index);
		columnWidth = UiSettings.recoverInt(this.settingsKey+columnWidthKey+index,100);
	//	column.setWidth(columnWidth);
		column.setPreferredWidth(columnWidth);
	}
	
	this.table.repaint();
	
}



protected void saveColumnWidth()
{

	int index;
	
	TableColumn column;
	
	for (index=0; index<this.tableModel.getColumnCount() ; index++)
	{
		column = this.table.getColumnModel().getColumn(index);
		
		UiSettings.saveInt(this.settingsKey+columnWidthKey+index,column.getWidth());
	}
	
}


@Override
protected void onClosing()
{
	super.onClosing();
	
	this.saveColumnWidth();
}








	
}



	

