package de.hska.lat.robot.ui.analoguePanelMeter;




import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;

import javax.swing.border.EtchedBorder;


import de.hska.lat.math.transformations.Transformation;
import de.hska.lat.math.vector.FloatVector2D;
import de.hska.lat.robot.ui.analogueInstrument.AnalogueInstrument;


public class AnalogueQuaterPanelMeter extends AnalogueInstrument{

	

	
/**
	 * 
	 */
	private static final long serialVersionUID = 8834651668839130197L;


public AnalogueQuaterPanelMeter(String name) 
{
	super(name);
	this.setBorder(new EtchedBorder());

}

	
public AnalogueQuaterPanelMeter(String name,float minRange,float maxRange) 
{
	super(name, minRange, maxRange);

	this.setBorder(new EtchedBorder());

}






@Override
protected void paintComponent(Graphics graphics)
{
	Graphics2D graphics2D;
	
	super.paintComponent(graphics);


	
	graphics2D = (Graphics2D) graphics;

	Stroke originalStroke = graphics2D.getStroke();
	
	
	drawScale(graphics2D);


	if (scaleUnit!=null)
	{
		graphics.drawString(value.getValue()+this.scaleUnit, 10, this.getHeight()-5);
	}
	graphics2D.setStroke(new BasicStroke( 2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER ));
	
	float angle =(3.14f*(value.getValue()-value.getMinRange())/(value.getMaxRange()-value.getMinRange()));
	
	
	
	
	
	FloatVector2D vector;

	
	
	vector=Transformation.rotate(angle, new FloatVector2D(1,0));
	
	vector.scale(-(this.getWidth()/2-5));
	
	vector.add(new FloatVector2D(this.getWidth()/2,this.getHeight()-20));
	
	graphics.drawLine(this.getWidth()-10, this.getHeight()-20 , (int)vector.x,(int) vector.y);
	
	graphics2D.setStroke(new BasicStroke( 1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER ));

	
	
	graphics.fillOval(this.getWidth()-10, this.getHeight()-23, 5, 5);

	
	graphics2D.setStroke(originalStroke);
}




private void drawScale(Graphics2D graphics)
{
	int index;
	float scaleFragmentStart=90;
	float scaleFragmentSize=0;
	

	graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
	        RenderingHints.VALUE_ANTIALIAS_ON);

	
	
	if (this.scale==null)
	{
		graphics.setStroke(new BasicStroke( 2f ));
		graphics.drawArc(10, 10, this.getWidth()-20, (this.getHeight()-20)*2-20, 0, 90);

	}
	else 
	{

		for (index=this.scale.length-1;index>=0;index--)
		{
			if (scale[index]==null)
				break;
			
			scaleFragmentSize=(90*scale[index].getSize()/(value.getMaxRange()-value.getMinRange()));
			
			
			if ((scaleFragmentStart+scaleFragmentSize)>180)
				scaleFragmentSize=180-scaleFragmentStart;
			
		
			graphics.setColor(scale[index].getColor());
			graphics.drawArc(10, 10, (this.getWidth()*2)-40, (this.getHeight()*2)-60, (int)scaleFragmentStart, (int)scaleFragmentSize);
			scaleFragmentStart+=scaleFragmentSize;

			if (scaleFragmentStart>180)
				break;
		}
		
		
	}
	
	graphics.setColor(Color.BLACK);
	if (this.tickCount!=0)
		for (index=0;index<=this.tickCount;index++)
		{
			graphics.setStroke( new BasicStroke( 5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER ) );
			graphics.drawArc(10, 10, (this.getWidth()*2)-40, (this.getHeight()-20)*2-20, (90+(90/this.tickCount)*index), 2);
		}

}

}
