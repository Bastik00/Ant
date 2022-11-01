package de.hska.lat.robot.component.generic.imu.view;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;


import de.hska.lat.math.Radiant;
import de.hska.lat.math.vector.AngularVector3D;
import de.hska.lat.robot.component.generic.imu.Imu;
import de.hska.lat.robot.component.generic.imu.ImuChangeNotifier;
import de.hska.lat.robot.ui.icon.DroidIcon;
import de.hska.lat.robot.ui.icon.DroidIconListener;

public class ImuArtificialHorizon extends DroidIcon<DroidIconListener> implements  ImuChangeNotifier
{

	private static final long serialVersionUID = 4186186438419970466L;
	
	private int pitch;
	private float roll;

	protected static final int MIN_HIGHT 	= 100 ;
	protected static final int MIN_WIDTH 	= 100 ;
	
	public ImuArtificialHorizon(Imu sensor, int width, int height)
	{
		super(sensor.getComponentName());
		sensor.addSensorListener(this);
		
		pitch = 0;
		roll = 0;
		
		this.setWidth(width);
		this.setHight(height);
	}
	

	

	@Override
	public void imuChanged(Imu sensor) {

		AngularVector3D angles;
		
		angles = sensor.getAngles();

		pitch = Math.round(Radiant.convertRadiantToDegree(angles.x));
		roll = angles.y;
		
		this.invalidate();
		this.repaint();
	}

	
	@Override
	protected void paintComponent(Graphics graphics)
	{
		int height = this.getHeight();
		int width = this.getWidth();
		
		float step = height / (2.0f * 55.0f);
		float heightShift = pitch * step;
		
		super.paintComponent(graphics);
		
		Graphics2D g2d = (Graphics2D) graphics;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        BufferedImage buffBackground = paintBackground(step, heightShift);
        
        // paint Frame
        BufferedImage buffFrame = paintFrame();
		
        // Merge Background + imgSize
        g2d.fill(new RoundRectangle2D.Double(0, 0, width, height, 0, 0));
        g2d.drawImage(buffBackground, 0, 0, null);
        
        
        // Paint airplane
        float strokeStrength = width / 62.5f;
        g2d.setStroke(new BasicStroke(strokeStrength, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
        g2d.setPaint(Color.ORANGE);
        Arc2D.Float arc2D = new Arc2D.Float();
        float radius = width / 12.5f;
        arc2D.setArcByCenter(width / 2, height / 2, radius, 0.f, -180.f, Arc2D.OPEN );
        g2d.draw(arc2D);
        
        g2d.draw(new Line2D.Double(width / 10 * 2, height / 2, width / 2 - radius - 2, height / 2));
        g2d.draw(new Line2D.Double(width / 2 + radius + 2, height / 2, width / 10 * 8, height / 2));
        
        g2d.setStroke(new BasicStroke(strokeStrength / 2.0f));
        
        arc2D.setArcByCenter(width / 2, height / 2, strokeStrength / 2 * 1.5f, 0.f, 360.f, Arc2D.OPEN);
        g2d.draw(arc2D);
        
        g2d.drawImage(buffFrame, 0, 0, null);  

	}
	
	private BufferedImage paintBackground(float step, float heightShift) 
	{
	
		int height = this.getHeight();
		int width = this.getWidth();
		
        BufferedImage buffBackground = new BufferedImage(width * 3, height * 4, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gbg = buffBackground.createGraphics();
        gbg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
	    AffineTransform rot = new AffineTransform();
	    rot.translate(width / 2, (height / 2) + heightShift);
	    rot.rotate((-1) * roll, 0, - heightShift);
	    gbg.transform(rot);
	    
	    // Paint Air
	    gbg.setPaint(new GradientPaint(0, 0, new Color(130,190,225), 0, - height, new Color(55,75,100), false));
		Rectangle2D.Double air = new Rectangle2D.Double((-1.5f) * width, (-2) * height, width * 3, height * 4);
		gbg.fill(air);
		
		// Paint Ground
		gbg.setPaint(new GradientPaint(0, 0, new Color(180,110,55), 0, height, new Color(70,35,15), false));
		Rectangle2D.Double ground = new Rectangle2D.Double((-1.5f) * width, 0, width * 3, height * 2);
		gbg.fill(ground);
		
		// Paint horizon line
		BasicStroke basicStroke = new BasicStroke(2.0f);
		gbg.setStroke(basicStroke);
		gbg.setPaint(Color.WHITE);
		gbg.draw(new Line2D.Double((-1) * width, (-1) * 0, width, 0));
		
		// Paint pitch lines
		basicStroke = new BasicStroke(2.0f);
		gbg.setStroke(basicStroke);
		gbg.setPaint(Color.WHITE);
		int fontSize = Math.round(height / 15.625f);
		Font textFont = new Font("Arial", Font.BOLD, fontSize);  
		gbg.setFont(textFont);  
		
		// Paint long lines and text
		for (int i = -40; i <= 40; i += 20) {
			if (i != 0) {
				gbg.draw(new Line2D.Double(width / (-3.33f), step * i, width / (-10.f), step * i));
				gbg.draw(new Line2D.Double( width / 10.f, step * i, width / 3.33f, step * i));
				gbg.drawString("" + Math.abs(i) + "", 0 - (fontSize / 2), step * i + (fontSize / 2));	
			}
		}
		// Paint small lines
		for (int i = -50; i <= 50; i += 20) {
			if (i != 0) {
				gbg.draw(new Line2D.Double(width / -16.6f, step * i, width / 16.6f, step * i));
			}
		}
		
		return buffBackground;
	}
	
	private BufferedImage paintFrame() 
	{
	
		int height = this.getHeight();
		int width = this.getWidth();
		
		BufferedImage buffFrame = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gFrame = buffFrame.createGraphics();
        gFrame.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OUT, 1.0f);

        gFrame.fill(new RoundRectangle2D.Double(3, 3, width - 6, height - 6, height / 2, height / 2));
        gFrame.setComposite(ac);
        
        GradientPaint gradient = new GradientPaint(0, 0, Color.DARK_GRAY, 0, height / 2 , Color.LIGHT_GRAY, true); // true means to repeat pattern
        gFrame.setPaint(gradient);
        gFrame.fill(new Rectangle2D.Double(0, 0, width, height));
        
		return buffFrame;
	}
	
}
