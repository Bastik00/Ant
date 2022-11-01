package de.hska.lat.robot.dataPacketLogger.filter.viewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import de.hska.lat.comm.packetLoger.filter.DataPacketFilter;

public class FilterDataPannel extends JPanel implements FilterSelectorNotifier
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2831107397236361426L;
	
	protected JTextField filterName;
	protected JTextArea description;
	private static final String FILTER_NAME_TEXT = "filter name";
	
	protected DataPacketFilter filter;
	
	
public FilterDataPannel()	
{
	
	this.setBorder(new LineBorder(Color.BLACK));
	this.buildPanel();
}
	


protected void buildPanel()
{
	this.setLayout(null);
	
	JLabel tmpLabel;
	
	
	tmpLabel = new JLabel(FilterDataPannel.FILTER_NAME_TEXT);
	tmpLabel.setBounds(10, 10, 100, 25);
	this.add(tmpLabel);
	

	this.setPreferredSize(new Dimension(300,100));
	this.setSize(new Dimension(300,100));
	tmpLabel = new JLabel();
	
	this.filterName = new JTextField();
	this.filterName.setBounds(110, 10, 300, 25);
	this.add(this.filterName);
	 
	 
	this.description= new  JTextArea();
	this.description.setBounds(110, 40, 300, 60);
	this.add(this.description);
	 
	
	JButton tmpButton;
	
	tmpButton = new JButton("new block");
	tmpButton.setBounds(10, 40, 100, 40);
	tmpButton.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			// TODO Auto-generated method stub
			if (FilterDataPannel.this.filter!=null)
			{
				FilterDataPannel.this.filter.addFilterBlock();
			}
		}
		
	});
	this.add(tmpButton);
	
	
}


public void editFilter(DataPacketFilter filter)
{
	if (filter!=null)
	{
		this.filterName.setText(filter.getName());
	}
	else
	{
		
	}
}






public void filterSelected(DataPacketFilter filter)
{
	this.editFilter(filter);
}



}
