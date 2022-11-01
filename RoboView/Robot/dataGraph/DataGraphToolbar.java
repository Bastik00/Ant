package de.hska.lat.robot.dataGraph;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;

public class DataGraphToolbar extends JToolBar
{

	
	
/**
	 * 
	 */
	private static final long serialVersionUID = 2917534218670120355L;

	
	JButton add;
	JButton start;
	JButton stop;
	
	JButton load;
	JButton save;
	JButton record;
	
	DataGraphControl control;
	
	
	
public DataGraphToolbar(DataGraphControl control)
{
	this.control = control;
	
	this.setName("Toolbar");
	this.setFloatable(false);
	this.setBorder(new LineBorder(Color.black));

	
	
	
	
	this.add = new JButton("add");
	this.add.addActionListener(new ActionListener()
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			DataGraphToolbar.this.control.addValue();
		}
		
	}
			
			);
	this.add(this.add);
	
	
	this.start = new JButton("\u25ba");
	this.start.addActionListener(new ActionListener()
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			DataGraphToolbar.this.control.play();
		}
		
	}
			
			);
	this.add(this.start);
	

	this.stop = new JButton("\u25a0");
	this.stop.addActionListener(new ActionListener()
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			DataGraphToolbar.this.control.stop();
		}
		
	}
			
			);
	this.add(this.stop);
	

	
	this.record = new JButton("\u25cf");
	this.record.addActionListener(new ActionListener()
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			DataGraphToolbar.this.control.record();
		}
		
	}
			
			);
	this.add(this.record);
	
	
	this.load = new JButton("load");
	this.load.addActionListener(new ActionListener()
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			DataGraphToolbar.this.control.load();
		}
		
	}
			
			);
	this.add(this.load);
	

	
	this.save = new JButton("save");
	this.save.addActionListener(new ActionListener()
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			DataGraphToolbar.this.control.save();
		}
		
	}
			
			);
	this.add(this.save);	
	
}

}
