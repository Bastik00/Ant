package de.hska.lat.robot.dataPacketLogger.filter.viewer;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import javax.swing.JToolBar;


import de.hska.lat.comm.packetLoger.filter.DataPacketFilter;
import de.hska.lat.comm.packetLoger.filter.DataPacketFilterList;

public class FilterEditorToolBar extends JToolBar
{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2305719747747136083L;
	protected DataPacketFilterSelector  filterSelector;
	
	
	protected static final String LOAD_TEXT = "load";
	protected static final String SAVE_TEXT = "save";
	protected static final String NEW_TEXT = "new";
	
	protected JButton loadButton;
	protected JButton saveButton;
	protected JButton newButton;
	
	public FilterEditorToolBar()
	{
		
		this.setName("Toolbar");

//		setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(400,40));
//		this.setBorder(new LineBorder(Color.black));
		buildToolBar();
	}
	
	
protected void buildToolBar()
{
	
	
	this.newButton = new JButton(FilterEditorToolBar.NEW_TEXT);
	this.newButton.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			//PacketLoggerToolBar.this.getDesktopPane().add(new DataPacketFilterBlockConfigViewer());
		//	PacketLoggerToolBar.this.getParent().add(new DataPacketFilterBlockConfigViewer());
		}
		
	});
	this.add(this.newButton);
	
	this.loadButton = new JButton(FilterEditorToolBar.LOAD_TEXT);
	this.loadButton.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			//PacketLoggerToolBar.this.getDesktopPane().add(new DataPacketFilterBlockConfigViewer());
		//	PacketLoggerToolBar.this.getParent().add(new DataPacketFilterBlockConfigViewer());
		}
		
	});
	this.add(this.loadButton);
	
	this.saveButton = new JButton(FilterEditorToolBar.SAVE_TEXT);
	this.saveButton.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			//PacketLoggerToolBar.this.getDesktopPane().add(new DataPacketFilterBlockConfigViewer());
		//	PacketLoggerToolBar.this.getParent().add(new DataPacketFilterBlockConfigViewer());
		}
		
	});
	this.add(this.saveButton);
	

	DataPacketFilterList filters = new DataPacketFilterList(); 
	
	DataPacketFilter a;
	
	a = new DataPacketFilter();
	a.setName("f1");
	filters.add(a);
	
	a = new DataPacketFilter();
	a.setName("f2");
	filters.add(a);
	
	
	this.filterSelector = new DataPacketFilterSelector(filters);
	this.filterSelector.setPreferredSize(new Dimension( 200,30));
	this.add(this.filterSelector);
	
}
	

public void  setSelectorListener(FilterSelectorNotifier listener)
{
	this.filterSelector.addSelectorListener(listener);
}

	
}
