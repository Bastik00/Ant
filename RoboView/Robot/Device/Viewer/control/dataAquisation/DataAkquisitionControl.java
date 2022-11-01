package de.hska.lat.robot.device.viewer.control.dataAquisation;



import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractSpinnerModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import de.hska.lat.robot.device.control.dataAquisation.DataAquisator;
import de.hska.lat.robot.device.viewer.control.ControlElement;


public class DataAkquisitionControl extends ControlElement implements ActionListener, ChangeListener
{

	

	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JSpinner period;
 
	private AquisatorNumberModel selectionList;
	private JButton onOffControll;
	

	private JComboBox<DataAquisator> selector;
	private static final String ON_TEXT = "on";
	private static final String OFF_TEXT = "off";
	private static final String ON_CHANGE_TEXT ="on change";
	
	protected DataAquisator [] aquisators;
	
	


protected void  buildControl()
{

	this.setLayout(new FlowLayout(FlowLayout.LEFT));
	
	//this.setPreferredSize(new Dimension(250,25));
	

	selectionList =new AquisatorNumberModel(10); //(0,0,100,1);

	period= new JSpinner(selectionList);
	//period.setBounds(120,1,80,20);
	period.addChangeListener(this);
	this.add(period);
	
	onOffControll= new JButton (ON_TEXT);
	//onOffControll.setBounds(200,1,30,20);
	onOffControll.addActionListener(this);
	//onOffControll.setBorder(null);
	this.add(onOffControll);
	
	
	
	selector = new JComboBox<DataAquisator>();
	//selector.setBounds(1,1,110,20);
	selector.addActionListener(this);
	this.add(selector);
}


public void setAquisators(DataAquisator [] aquisators)
{
	int index;
	this.aquisators=aquisators;
	
	if (aquisators==null)
		return;
		
	
	for (index=0;index<aquisators.length;index++)
	{
		selector.addItem(aquisators[index]);
	}
	
}



@Override
public void actionPerformed(ActionEvent actionEvent) 
{
	Object control;
	
	control=actionEvent.getSource();
	
	if (control==onOffControll)
	{
		if (onOffControll.getText().equals(ON_TEXT))
		{
			int index;
			index=selector.getSelectedIndex();
			
			aquisators[index].On();
			
			onOffControll.setText(OFF_TEXT);
	
			if (listener!=null)
			{
				DataAquisator  aquisator;
				aquisator=aquisators[index];
				//selectionList.getIndex();
				//listener.startBroadcastSensorData(aquisators[index].getId(),aquisators[index].getPeriod());
				this.listener.remote_startStreamData(aquisator.getId(),aquisator.getPeriod());
			}
				
		}
		else
			{
	
			int index;
			index=selector.getSelectedIndex();
			
			aquisators[index].Off();
	
			onOffControll.setText(ON_TEXT);
			
			if (listener!=null)
				listener.remote_stopStreamData(aquisators[index].getId());
	
			}
	}
	else if  (control==selector)
	{
		int index;
		
		index=selector.getSelectedIndex();
		if (aquisators[index].isOn())
		{
			onOffControll.setText(OFF_TEXT);
		}
		else
		{
			onOffControll.setText(ON_TEXT);
		}
		selectionList.setIndex(aquisators[index].getPeriod());	

	}
	
	
	
	
}


@Override
public void stateChanged(ChangeEvent arg0) 
{
	int value;
	int index;
	
	value=selectionList.getIndex();

	index=selector.getSelectedIndex();
	aquisators[index].setPeriod(value);
	
}




class AquisatorNumberModel extends AbstractSpinnerModel
{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private int index;


public AquisatorNumberModel(int MaxRange)
{
	this.index=0;
}


	





public String outputText()
{
	String text;
	
	if (index==0)
	{
		text=ON_CHANGE_TEXT;
	}
	else
	{
		text=index*10+"ms";
	}
	return(text);	
}
	
public DataAquisator getAquisator()
{
	return(aquisators[this.index]);
}


@Override
public Object getNextValue()
{
	index++;

	return(outputText());
}


@Override
public Object getPreviousValue() 
{
	if (index>0)
		index--;

	return(outputText());
}


@Override
public void setValue(Object arg0) 
{
	notifyListeners();
}
	
	
private void notifyListeners()
{
	
	ChangeListener [] changeListener;
	int enumerator;


	changeListener=this.getChangeListeners();
	for (enumerator =0;enumerator < changeListener.length;enumerator++)
	{
		changeListener[enumerator].stateChanged(new ChangeEvent(this));
	}


}
	
public void setIndex(int index)
{
	this.index=index;
	notifyListeners();
}




public int getIndex()
{
	

	return(this.index);
}








@Override
public Object getValue()
{
	return(this.outputText());
}
	
}


}
