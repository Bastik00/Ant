package de.hska.lat.robot.dataGraph.valueGraph;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Rectangle2D;

import javax.swing.SwingUtilities;

import de.hska.lat.robot.value.ComponentValue;
import de.hska.lat.robot.value.FloatValue;
import de.hska.lat.robot.value.flow.ValueFlow;

public class ValueGraphScale extends ValueGraphAbstract implements MouseListener, MouseMotionListener, MouseWheelListener, ValueGraphSettingsListener
{

	private static final long serialVersionUID = -7330512526654921082L;

	protected ValueFlow data; 
	protected ComponentValue<?> value;	
	
	private int movementYOrigin;
	
	protected boolean mouseDragged;
	
	protected Color backgroundColor = Color.WHITE;


	
public ValueGraphScale(ValueGraphSettings settings)
{
	super(settings);
	
	this.setBackground(backgroundColor);
	this.addMouseListener(this);
	this.addMouseMotionListener(this);
	this.addMouseWheelListener(this);
	
	this.settings.addListener(this);
	
	this.calculate();
}



protected void drawValueLeftFrom(Graphics graphics, float value, int xPos, int yPos)
{
	String description;
	int descriptionWidth;
	
		
	description = FloatValue.toFormatedFractionString(value, 2);
	descriptionWidth=graphics.getFontMetrics().stringWidth(description);
	
	graphics.drawString(description, xPos - descriptionWidth , yPos);
}



protected float valueFromScreen(int position)
{
	float value;
	value =  this.displayMaxRange - ((position * this.displayRange) / this.getHeight());
	return(value);
}



@Override
protected void paintComponent(Graphics graphics)
{
	
	super.paintComponent(graphics);
	
	this.drawScale(graphics);	
	
}

public void update()
{
	this.invalidate();
	this.repaint();
}

protected void drawScale (Graphics graphics) 
{
	this.drawGrid(graphics);
	this.drawMinAndMax(graphics);
}

private void drawMinAndMax(Graphics graphics) {
	// draw min and max values
	int height = this.getHeight();
	
	graphics.setColor(Color.LIGHT_GRAY);
	
	this.drawStringWithBG(graphics, FloatValue.toFormatedFractionString(this.displayMaxRange, 2), 1, 10);
	this.drawStringWithBG(graphics, FloatValue.toFormatedFractionString(this.displayMinRange, 2), 1, height);	
}

private void drawStringWithBG(Graphics graphics, String string, int xPos, int yPos) {
	Color fontColor = graphics.getColor();
	
	FontMetrics fm = graphics.getFontMetrics();
	Rectangle2D rect = fm.getStringBounds(string, graphics);
	graphics.setColor(backgroundColor);
	graphics.fillRect(xPos,
				yPos - fm.getAscent(),
               (int) rect.getWidth(),
               (int) rect.getHeight());
	
	graphics.setColor(fontColor);
	graphics.drawString(string, xPos, yPos);
}

private void drawGrid(Graphics graphics) {
	int width = this.getWidth();
	int height = this.getHeight();
	
	float niceStepSize = this.calcStepSize();
	int rightValueMargin = 2;
	
	graphics.setColor(Color.LIGHT_GRAY);
	
	int maxLine = (int) (this.displayMaxRange / niceStepSize);
	int minLine = (int) (this.displayMinRange / niceStepSize);
	
	for (int line = maxLine; line >= minLine; line--) 
	{
		int yPos = (int) (((this.displayMaxRange - (niceStepSize * line)) / this.displayRange) * height);
		
		if (line == 0) {
			graphics.setColor(Color.GRAY);
		}
		
		graphics.drawLine(0, yPos,  width, yPos);
		float label = line * niceStepSize;
		this.drawValueLeftFrom(graphics, label,  this.getWidth() - rightValueMargin , yPos);
	
		if (line == 0) {
			graphics.setColor(Color.LIGHT_GRAY);
		}
	}
}


@Override
public void mouseEntered(MouseEvent arg0)
{
	// do nothing
	
}

@Override
public void mouseExited(MouseEvent arg0)
{
	// do nothing

}

@Override
public void mousePressed(MouseEvent mouseEvent)
{

	this.mouseDragged = false;
	this.movementYOrigin= mouseEvent.getY();
}

@Override
public void mouseReleased(MouseEvent mouseEvent)
{
	// do nothing
}


@Override
public void mouseDragged(MouseEvent mouseEvent)
{
	this.mouseDragged =true;
	int deltaY = this.movementYOrigin - mouseEvent.getY();
	this.movementYOrigin = mouseEvent.getY();
	
	if (SwingUtilities.isLeftMouseButton(mouseEvent))
	{
		if (deltaY !=0)
		{
			float newDisplayOffset = settings.getYOffset() - (  this.displayRange * deltaY ) / this.getHeight();
			settings.setYOffset(newDisplayOffset);
		}
	}
}

@Override
public void mouseMoved(MouseEvent mouseEvent)
{
	// do nothing
}



@Override
public void mouseClicked(MouseEvent arg0)
{
	// do nothing
}



@Override
public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent)
{
	int notches = mouseWheelEvent.getWheelRotation();
	int scaleStep = this.settings.getYScaleStep();
	
	if (notches > 0)
	{
		scaleStep--;
	}
	else
	{
		scaleStep++;
	}
	
	if (scaleStep < 1)
	{
		scaleStep = 1;
	} 
	else if (scaleStep > 50) {
		scaleStep = 50;
	}
	
	if (scaleStep != this.settings.getYScaleStep()) {
		this.settings.setYScaleStep(scaleStep);
	}

}




	
}
