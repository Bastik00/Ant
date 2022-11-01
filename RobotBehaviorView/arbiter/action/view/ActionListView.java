package de.hska.lat.behavior.arbiter.action.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import de.hska.lat.behavior.arbiter.Arbiter;
import de.hska.lat.behavior.arbiter.ArbiterChangeNotifier;
import de.hska.lat.behavior.behavior.RobotBehavior;
import de.hska.lat.robot.displayFrame.DisplayFrame;



public class ActionListView extends DisplayFrame implements ArbiterChangeNotifier
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2751366388859600201L;

	
	
	protected JTable table; 
	protected JScrollPane scrollPane;
	
	
	protected ActionListTableModel tableModel;

	
public ActionListView(RobotBehavior<?> behavior, Arbiter arbiter)
{

	super("action list for : "+arbiter.getName(), true, true, true,true);
			
 
	arbiter.addChangeListener(this);
	this.buildPanel(behavior, arbiter);
	
	
	
}
	
	
private void buildPanel(RobotBehavior<?> behavior, Arbiter arbiter)
{

	this.setLayout(new  BorderLayout());
	
	//this.toolBar=new PacketLoggerToolBar();
	
//	this.add(toolBar,BorderLayout.PAGE_START);

	
	this.scrollPane=new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	this.scrollPane.setViewportBorder(BorderFactory.createLineBorder(Color.black));
	
	this.tableModel = new ActionListTableModel(behavior);
	this.table =new JTable(this.tableModel);

	this.tableModel.setActions(	arbiter.getActions());
	
	this.scrollPane.setViewportView(table);	
	
	

	
	this.add(this.scrollPane);		
	
}


@Override
public void arbiterChanged(Arbiter arbiter)
{
	if (this.table!=null)
	{
		this.table.revalidate();
		this.table.repaint();
	}
}


@Override
public void statusChanged(Arbiter arbiter)
{
	// TODO Auto-generated method stub
	
}	
	
}
