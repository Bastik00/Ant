package de.hska.lat.robot.dataPacketLogger.filter.viewer;


import java.awt.BorderLayout;

import de.hska.lat.robot.abstractRobot.AbstractRobot;
import de.hska.lat.robot.displayFrame.DisplayFrame;

public class DataPacketFilterEditor extends DisplayFrame
{

	protected static final String FRAME_NAME = "Data packet filter editor";
	
/**
	 * 
	 */
	private static final long serialVersionUID = 8658319213425624930L;

	
	protected FilterEditorToolBar toolBar;
	
	protected FilterDataPannel filterData;
	protected FilterEditorPanel filterEditor;
	
public DataPacketFilterEditor()
{
	super(DataPacketFilterEditor.FRAME_NAME, true, true, true, true);
	
	this.buildPanel();
	//super(" ",false,false,false,false);
//	this.setBounds(100, 100,200, 200 );
	show();
}
	


protected void buildPanel()
{

	this.setLayout(new  BorderLayout());
	
	this.toolBar=new FilterEditorToolBar();
	this.add(toolBar,BorderLayout.PAGE_START);
	
	this.filterData = new FilterDataPannel();
	this.add(this.filterData,BorderLayout.CENTER);

	
	this.filterEditor = new FilterEditorPanel();
	//this.filterEditor.setPreferredSize(preferredSize)
	this.add(this.filterEditor,BorderLayout.PAGE_END);
	
	
	this.toolBar.setSelectorListener(this.filterData);
}	


@Override
public boolean setRobot(AbstractRobot<?,?,?> robot)
{
	this.setTitle(DataPacketFilterEditor.FRAME_NAME+" : "+robot.getName());
	return(true);
}


}
