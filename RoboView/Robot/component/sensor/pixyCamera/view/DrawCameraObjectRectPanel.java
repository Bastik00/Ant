package de.hska.lat.robot.component.sensor.pixyCamera.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;

public class DrawCameraObjectRectPanel extends JPanel  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -810380539580999101L;
	public int signature;
	public int xCenter;
	public int yCenter;
	public int width;
	public int height;
	
	public ArrayList<DetectedObject> detectedObjects;
	public ArrayList<DetectedObject> detectedObjectsDrawCopy;
	
    DrawCameraObjectRectPanel() {
        detectedObjects = new ArrayList<DetectedObject>();
        detectedObjectsDrawCopy  = new ArrayList<DetectedObject>();
    }
    
public void setXcenter(int xCenter)
{
	this.xCenter = xCenter;
}

	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D graphics2D = (Graphics2D) g;
        // Antialiasing ON
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
          RenderingHints.VALUE_ANTIALIAS_ON);
        
        //if PixyCameraObjectScreenView detects a new frame, it overwrites detectedObjects with new objects
        //if the unlikely case happens, that in this moment, the frame is redrawn, drop it and draw the next one
        try{
        	for (DetectedObject detectedObject : detectedObjectsDrawCopy) {
        		//generate dynamic Color out of HSV-Farbraum and signature
        		graphics2D.setColor(Color.getHSBColor((detectedObject.getSignature()*0.12f)%1.0f,1,1));
            	graphics2D.drawString("s"+detectedObject.getSignature()+" a="+detectedObject.getAngle()+"°", detectedObject.getxCenter()-(detectedObject.getWidth()/2), detectedObject.getyCenter()-(detectedObject.getHeight()/2));
            	graphics2D.drawRect(detectedObject.getxCenter()-(detectedObject.getWidth()/2), detectedObject.getyCenter()-(detectedObject.getHeight()/2), detectedObject.getWidth(), detectedObject.getHeight());
    		}
        } catch(Exception e)
        {
        	System.out.println("DrawCameraObjectRectPanel: current frame dropped -> redraw\n"+e.toString());
        	//paintComponent gets recalled from the thread, causing the exception like explained above.
        	//So here´s nothing more to do than to...
        	return;
        }
    }

	public void updateFrame() {
		
		
		
	}
 }