package de.hska.lat.robot.control.driveControl.driveControl2D.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class DriveControlPanel extends JPanel implements MouseListener
{

/**
	 * 
	 */
	private static final long serialVersionUID = 284634765219621217L;

	
	protected DriveControlListener listener;
	
	
	protected float xValue;
	protected float yValue;
	protected float angle;
	protected int controlSize;
	
	protected float value;
	
public DriveControlPanel(DriveControlListener listener)
{
	this.listener = listener;
	this.addMouseListener(this);
}


public float getAngle()
{
	return(this.angle);
}


public float getValue()
{
	return(this.value);
}

public float getXValue()
{
	return(this.xValue);
}


public float getYValue()
{
	return(this.yValue);
}



@Override
protected void paintComponent(Graphics graphics)
{
	
	super.paintComponent(graphics);
	this.paintGrid((Graphics2D) graphics);

}

	
	
private void paintGrid(Graphics2D graphics)
{
	int middleX;
	int middleY;
	
	
	middleX = this.getWidth()/2;
	middleY = this.getHeight()/2;
	
	if (this.getWidth()>this.getHeight())
	{
		controlSize = this.getHeight()-10;
	}
	else
	{
		controlSize = this.getWidth()-10;
	}
	graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
	        RenderingHints.VALUE_ANTIALIAS_ON);

	graphics.fillOval(middleX-10, middleY-10, 20,20);
	
	//Quer
	//graphics.drawArc(10, 10, this.getWidth()-20, (this.getHeight()-20)*2-20, 0, 90);
	graphics.drawLine(middleX - (this.controlSize/2), middleY ,middleX + (this.controlSize/2), middleY );
	//graphics.drawLine(middleX - (this.controlSize/3), middleY- (this.controlSize/3) ,middleX + (this.controlSize/2), middleY );
	//Vertical
	double radWinkel;
	int x_koordinate, x_koordinate2;
	int y_koordinate, y_koordinate2;
	graphics.drawLine(middleX , middleY - (this.controlSize/2),middleX , middleY + (this.controlSize/2));
	for(double i = 30; i<360;i = i + 30){
		radWinkel = i / 180 * Math.PI;
		x_koordinate = (int)(Math.cos( radWinkel ) * ((middleY-5)));  
		y_koordinate = (int)(Math.sin( radWinkel ) * ((middleY-5)));
		x_koordinate2 = (int)(Math.cos( radWinkel ) * ((middleY-30)));
		y_koordinate2 = (int)(Math.sin( radWinkel ) * ((middleY-30)));
		//System.out.println(radWinkel2 + " / " + x_koordinate2 + " / " + y_koordinate2);
		graphics.drawLine(middleX+x_koordinate2, middleY+y_koordinate2,middleX+x_koordinate,middleY+y_koordinate);
	}
	graphics.drawOval(middleX - (this.controlSize/2), middleY- (this.controlSize/2), this.controlSize,this.controlSize);
	graphics.drawString("Schrittweite: " + (int)((this.value)*100) + " %", 30, 40);
	graphics.drawString("Winkel: " + (int)(this.angle*(180/Math.PI)) + " �", this.getWidth()-90, 40);
	graphics.drawOval(middleX - (this.controlSize/4), middleY- (this.controlSize/4), this.controlSize/2,this.controlSize/2);
	double temp = this.angle*(180/Math.PI);
	temp = temp - 90;
	radWinkel = temp / 180 * Math.PI;
	x_koordinate = (int)(Math.cos( radWinkel ) * ((middleY-5)));  
	y_koordinate = (int)(Math.sin( radWinkel ) * ((middleY-5)));
	//System.out.println(x_koordinate + " / " + y_koordinate + " / " + this.angle + " / " + (middleY-5));
	graphics.setStroke ( new BasicStroke ( 5.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL ) );
	graphics.setColor(Color.red);
	graphics.drawLine(middleX, middleY,middleX+x_koordinate,middleY+y_koordinate);
	//framesPerSecond.addChangeListener(this);

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

@Override
public void mousePressed(MouseEvent arg0)
{
	// TODO Auto-generated method stub
	
}

@Override
public void mouseReleased(MouseEvent mouseEvent)
{
	float x;
	float y;
	
	float xOrigin;
	float yOrigin;
	
	float xExtend;
	float yExtend;

	float value;
	float angle;
	
	x=mouseEvent.getX();
	y=mouseEvent.getY();
	
	xOrigin=this.getWidth()/2;
	yOrigin=this.getHeight()/2;
	
	yExtend= y-yOrigin;
	xExtend= x-xOrigin;


	value=(float)Math.sqrt(xExtend*xExtend+yExtend*yExtend );

	
	value = value/(this.controlSize/2);
	
	angle = (float)Math.atan2(xExtend, -yExtend);
	
	if (value >1)
	{
		value = 1;
	}
	else if (value<-1)
	{
		value = -1;
	}

	this.value = value;
	this.angle = angle;
	this.xValue = xExtend;
	this.yValue = yExtend;
	
	if (this.listener != null)
	{
		this.listener.valueSelected(this);
	}
	this.invalidate();
	this.repaint();
	//System.out.println("released :" + value+" :"+angle);
}
	
	
	
}
