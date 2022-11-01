package de.hska.lat.robot.component.generic.imu.view;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
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
import de.hska.lat.robot.ui.settings.UiSettings;
import de.hska.lat.robot.value.ComponentValue;
import de.hska.lat.robot.value.ComponentValueChangeListener;
import de.hska.lat.robot.value.angular.AngularValue;

public class ImuCompass extends DroidIcon<DroidIconListener> implements  ImuChangeNotifier, ComponentValueChangeListener
{
	private static final long serialVersionUID = 2236186467899970466L;
	
	protected String settingsKey;
	protected String componentName;
	protected static final String rangeFullCircleKey = ".rangeFullCircle"; 
	
	private int yaw;
	private int height;
	private int width;
	private boolean compassMode;
	private boolean rangeFullCircle; // true = [0 .. 360], false = [-180 .. 180]
	
	public ImuCompass(String componentName, Imu sensor, int width, int height, boolean compassMode)
	{
		super(sensor.getComponentName());
		sensor.addSensorListener(this);
		this.yaw = 0;
		this.setWidth(width);
		this.setHight(height);
		this.compassMode = compassMode;
		
		this.componentName = componentName;
		this.settingsKey=this.getClass().getName()+"."+this.componentName;
		
		this.setRangeFullCircle(UiSettings.recoverBoolean(settingsKey+ImuCompass.rangeFullCircleKey, false));
		
	}

	public ImuCompass(String componentName, AngularValue value, int width, int height, boolean compassMode)
	{
		super(value.getName());
		value.addListener(this);
		this.yaw = 0;
		this.setWidth(width);
		this.setHight(height);
		this.compassMode = compassMode;
		
		this.componentName = componentName;
		this.settingsKey=this.getClass().getName()+"."+this.componentName;
		
		this.setRangeFullCircle(UiSettings.recoverBoolean(settingsKey+ImuCompass.rangeFullCircleKey, false));
		
	}
	
	
	protected void saveSettings()
	{
		UiSettings.saveBoolean(this.settingsKey+rangeFullCircleKey, this.getRangeFullCircle());
	}

	@Override
	public void imuChanged(Imu sensor) {

		AngularVector3D angles;
		angles = sensor.getAngles();

		this.yaw = Math.round(Radiant.convertRadiantToDegree(angles.z));
		
		this.invalidate();
		this.repaint();
	}
	
	public void setHight(int height) {
		if (height > 0) {
			this.height = height;
			this.setSize(this.width, this.height);
		}
	}
	
	public void setWidth(int width) {
		if (width > 0) {
			this.width = width;
			this.setSize(this.width, this.height);
		}
	}
	
	@Override
	protected void paintComponent(Graphics graphics)
	{		
		super.paintComponent(graphics);
		
		Graphics2D g2d = (Graphics2D) graphics;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
		// paint Backgrund
        BufferedImage buffBackground = paintBackground();
        // paint Scala
        BufferedImage buffScala = paintScala();
        // paint Center Locator
        BufferedImage buffCenter = paintCenterLocator();
        // paint Frame
        BufferedImage buffFrame = paintFrame();

	  
	    g2d.drawImage(buffBackground, 0, 0, null);
	    g2d.drawImage(buffScala, 0, 0, null);
	    
	    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.5f));  
	    
	    g2d.drawImage(buffCenter, 0, 0, null);
	    
	    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 1.0f));
	    
	    g2d.drawImage(buffFrame, 0, 0, null);  
	   
	    g2d.dispose();
	    
	}
	
	private BufferedImage paintBackground() {
		BufferedImage buffBackground = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gbg = buffBackground.createGraphics();
        gbg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
	    // Paint Background
        GradientPaint gradient = new GradientPaint(0, 0, Color.BLACK, width / 2.0f, 0, Color.GRAY, true); // true means to repeat pattern
	    gbg.setPaint(gradient);
		Rectangle2D.Double ground = new Rectangle2D.Double(0, 0, this.width, this.height);
		gbg.fill(ground);
		
		return buffBackground;
	}
	
	private BufferedImage paintScala() {
		float step = this.width / 100.0f; // 1 step = 1 degree
		
        BufferedImage buffScala = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gScala = buffScala.createGraphics();
        gScala.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		// Paint scala
		gScala.setStroke(new BasicStroke(1.5f));
		gScala.setPaint(new GradientPaint(0, 0, Color.WHITE, this.width / 2.f, 0, new Color(60, 60, 60), true));

	    AffineTransform shift = new AffineTransform();
	    shift.setToTranslation(this.width / 2, 0);
	    gScala.transform(shift);
	    
		int fontSize = Math.round(this.width / 30f);
		Font textFont = new Font("Arial", Font.BOLD, fontSize);  
		gScala.setFont(textFont);  
	    
	    int offset = this.yaw % 10;
	    
	    // draw scala from yaw +-60 degree
	    for (int i = -60; i <= 60; i += 5) {
	    	
	    	if ((i + this.yaw - offset) % 30 == 0) {	// each 30° draw "big line" + text
	    		gScala.draw(new Line2D.Double((i - offset) * step, this.height / 6.0f,
	    									(i - offset) * step, this.height / 4.0f));
	    		gScala.draw(new Line2D.Double((i - offset) * step, this.height - (this.height / 4.0f),
	    									(i - offset) * step, this.height - (this.height / 6.0f)));
	    		
	    		String text = generateText(offset, i);
	
	    		FontMetrics fm = getFontMetrics(getFont());
	    		int textWidth = fm.stringWidth(text);
	    		
	    		gScala.drawString(text, (i - offset) * step - (textWidth / 2) + 1 ,
	    								(this.height / 2) + (fontSize / 2) - 1);	
	    		
	    	} else if (i % 10 == 0) { 	// at each 10 degree
	    		gScala.draw(new Line2D.Double((i - offset) * step, this.height / 3.5f,
	    									(i - offset) * step, this.height - (this.height / 3.5f)));	
	    	} else { 					// at each 5 degree
	    		gScala.draw(new Line2D.Double((i - offset) * step, this.height / 2.5f,
	    									(i - offset) * step, this.height - (this.height / 2.5f)));	
	    	}
	    	
	    }
		
		return buffScala;
	}
	
	private String generateText(int offset, int i) {
		String text ="";
		
		if (compassMode) {
    		switch ((this.yaw - offset + 360 + i) % 360) {
    			case 0: 	text = "N"; break; //write "N"
    			case 90: 	text = "O"; break; //write "O"
    			case 180: 	text = "S"; break; //write "S"
    			case 270: 	text = "W"; break; //write "W"
    			default: 	text = "" + ((this.yaw - offset + 360 + i) % 360);
    		}	
		} else if (rangeFullCircle) {
			text = "" + ((this.yaw - offset + 360 + i) % 360);
		} else {
			if (((this.yaw - offset + i) % 360) > 180 ) {
				text = "" + (((this.yaw - offset + i) % 360) - 360);
			} else if (((this.yaw - offset + i) % 360) < -180 ) {
				text = "" + (((this.yaw - offset + i) % 360) + 360);
			} else {
				text = "" + ((this.yaw - offset + i) % 360);
			}

		}	
		return text;
	}
	
	private BufferedImage paintCenterLocator() {
		BufferedImage buffCenter = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gCenter = buffCenter.createGraphics();
        gCenter.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        gCenter.setPaint(Color.RED);
        
	    int triangleHeight = Math.round(this.height / 6f);
	    int xpointsTop[] = {(this.width / 2) - triangleHeight, this.width / 2 + triangleHeight, this.width / 2};
	    int ypointsTop[] = {0, 0, triangleHeight};
	    int xpointsButtom[] = {this.width / 2 - triangleHeight, width / 2 + triangleHeight, width / 2};
	    int ypointsButtom[] = {this.height, this.height, this.height - triangleHeight};
	    int npoints = 3;
	    gCenter.fill(new Polygon(xpointsTop, ypointsTop, npoints));
	    gCenter.fill(new Polygon(xpointsButtom, ypointsButtom, npoints));
		
		return buffCenter;
	}
	
	private BufferedImage paintFrame() {
		
		BufferedImage buffFrame = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gFrame = buffFrame.createGraphics();
        gFrame.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OUT, 1.0f);

        gFrame.fill(new RoundRectangle2D.Double(3, 3, this.width - 6, this.height - 6, this.height / 2, this.height / 2));
        gFrame.setComposite(ac);
        
        GradientPaint gradient = new GradientPaint(0, 0, Color.DARK_GRAY, 0, this.height, Color.LIGHT_GRAY, true); // true means to repeat pattern
        gFrame.setPaint(gradient);
        gFrame.fill(new Rectangle2D.Double(0, 0, this.width, this.height));
        
		return buffFrame;
	}
	
	
	public boolean getRangeFullCircle() {
		return this.rangeFullCircle;
	}
	
	public void setRangeFullCircle(boolean circle) {
		this.rangeFullCircle = circle;
		this.saveSettings();
	}

	public boolean getCompassMode() {
		return this.compassMode;
	}



	@Override
	public void valueChanged(ComponentValue<?> componentValue)
	{

		this.yaw = Math.round(Radiant.convertRadiantToDegree(componentValue.getValue()));
		
		this.invalidate();
		this.repaint();
	}
		
}
