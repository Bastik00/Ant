package de.hska.lat.robot.valueScope;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;



public class ValueScopeToolbar extends JToolBar
{


/**
	 * 
	 */
	private static final long serialVersionUID = 3588712476135323908L;

	protected static final String ADD_VALUE_TEXT	= "add value";
	
	protected static final String TO_PNG_TEXT		= "to png";
	protected static final String TO_CSV_TEXT		= "to csv";
	protected static final String DURATION_TEXT		="duration(s)";
	//	JButton OriginButton
	JButton add;
	JButton start;
	JButton stop;
	
	JSpinner zeroPos;
	JSpinner scale;
	JSpinner duration;
	
	
	protected ValueScopeScreenControl control;
	
public ValueScopeToolbar(ValueScopeScreenControl control)
{
	this.setName("Toolbar");
	this.setFloatable(false);

	this.control = control;
	this.setBorder(new LineBorder(Color.black));
	this.buildToolBar();
}



private void buildToolBar()
{
	
	
	this.setLayout(new FlowLayout(FlowLayout.LEFT));
	
	
	this.add = new JButton(ADD_VALUE_TEXT);
	this.add.addActionListener(new ActionListener()
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			ValueScopeToolbar.this.control.addValue(); 
		}
		
	}
			);
	this.add(this.add);

	
	this.addSeparator();
	
	
	this.start = new JButton("\u25ba");
	this.start.addActionListener(new ActionListener()
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			ValueScopeToolbar.this.control.start();
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
			ValueScopeToolbar.this.control.stop();
		}
		
	}
			
			);
	this.add(this.stop);
	
	this.zeroPos = new JSpinner();
	
	this.add(this.zeroPos);

	this.scale = new JSpinner();
	
	this.add(this.scale);
	

	this.add(new JLabel(DURATION_TEXT));
	this.duration = new JSpinner();
	
	this.add(this.duration);
	

}



}