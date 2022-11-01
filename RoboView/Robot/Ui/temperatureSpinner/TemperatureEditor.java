package de.hska.lat.robot.ui.temperatureSpinner;


import java.awt.Color;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class TemperatureEditor extends JFormattedTextField implements DocumentListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5228040529745287688L;

	
public TemperatureEditor()
{
	this.getDocument().addDocumentListener(this);
	
	AbstractFormatter format = new formatter();
	this.setFormatter(format);
	
	format.install(this);
	
//	this.getDocument().
}



public boolean checkTemperatureFormat()
{
	String textToCheck =this.getText();
	
	textToCheck=textToCheck.trim();
	textToCheck=textToCheck.toUpperCase();
	
	System.out.println("textToCheck "+textToCheck);
	
	if (textToCheck.endsWith("C"))
	{
		textToCheck= textToCheck.substring(0, textToCheck.length()-1);
	}
	else if (textToCheck.endsWith("K"))
	{
		textToCheck= textToCheck.substring(0, textToCheck.length()-1);
	}
	float value = 0;
	
	try
	{
		 value = Float.parseFloat(textToCheck);	
			this.setBackground(Color.white);
			
			value *= 100;
			// setIndex
	}
	catch (NumberFormatException exception)
	{
		this.setBackground(Color.RED);
	}
	

	
	System.out.println("value "+value);
	
	return(true);
}

@Override
public void setText(String arg0)
{
	
}

@Override
public void changedUpdate(DocumentEvent arg0)
{
	// TODO Auto-generated method stub
	System.out.println("changedUpdate"+arg0+"  "+ this.getText());
	this.checkTemperatureFormat();
}


@Override
public void insertUpdate(DocumentEvent arg0)
{
	// TODO Auto-generated method stub
	System.out.println("insertUpdate"+arg0+"  "+ this.getText());
	this.checkTemperatureFormat();
}


@Override
public void removeUpdate(DocumentEvent arg0)
{
	// TODO Auto-generated method stub
	System.out.println("removeUpdate"+arg0+"  "+ this.getText());

	this.checkTemperatureFormat();
}
	
private class formatter extends AbstractFormatter
{
	


/**
	 * 
	 */
	private static final long serialVersionUID = 1415206918566928220L;

@Override
public Object stringToValue(String text) throws ParseException
{
	System.out.println(text);
	// TODO Auto-generated method stub
	return null;
}

@Override
public String valueToString(Object value) throws ParseException
{
	System.out.println(value);
	// TODO Auto-generated method stub
	return null;
}
}

}
