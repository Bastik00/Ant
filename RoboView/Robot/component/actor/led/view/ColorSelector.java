package de.hska.lat.robot.component.actor.led.view;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class ColorSelector extends JPanel implements MouseListener
{

	
	
/**
	 * 
	 */
	private static final long serialVersionUID = -2147691061262658706L;

	protected ColorSelectorListener listener;

public ColorSelector()
{
	this.setBorder(new LineBorder(Color.BLACK, 2));
	this.addMouseListener(this);
}
	

public void setColorListener(ColorSelectorListener listener)
{
	this.listener = listener;
}

@Override
public void mouseClicked(MouseEvent arg0)
{
	// TODO Auto-generated method stub
	
}


@Override
public void mouseEntered(MouseEvent arg0)
{
	// TODO Auto-generated method stub
	
}


@Override
public void mouseExited(MouseEvent arg0)
{
	// TODO Auto-generated method stub
	
}



public void setColor(Color color)
{
	this.setBackground(color);
}

public Color getColor(Color color)
{
	return(this.getBackground());
}

@Override
public void mousePressed(MouseEvent arg0) 
{
	Color color = JColorChooser.showDialog(
            this,
            "Choose Sphero LED Color",
            null);
	

	if (color!=null)
	{
		if (listener!=null)
		{
		listener.colorSelected(color);
		}
	}
//	this.led.remote_setColor(color);

	//this.saveColor(color);
	
	
}


@Override
public void mouseReleased(MouseEvent arg0)
{
	// TODO Auto-generated method stub
	
}


}
