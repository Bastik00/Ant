package de.hska.lat.robot.ui.temperatureSpinner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TemperatureSpinner extends JSpinner implements ActionListener, ChangeListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3115640211354006237L;

	
	
	
public	TemperatureSpinner()
{
//	(this.setEditor(editor)
	
	JComponent editor = this.getEditor();
	editor.removeAll();
	editor.add(new TemperatureEditor()); //.addActionListener(this));
	this.getModel().addChangeListener(this);
//	this.setModel(new TemperatureModel());

	
}
	//.addDocumentListener(this);




@Override
public void actionPerformed(ActionEvent arg0)
{
	// TODO Auto-generated method stub
	
}




@Override
public void stateChanged(ChangeEvent arg0)
{
	// TODO Auto-generated method stub
	System.out.println("change");
}
}
