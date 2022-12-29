

from tkinter import  Frame
import customtkinter as ctk
from RoboView.Gui.InternalWindow.WindowCloser import WindowCloser
from RoboView.Gui.InternalWindow.WindowResizer import WindowResizer

from RoboView.Gui.InternalWindow.WindowTitle import WindowTitle
from RoboView.Robot.Viewer.RobotSettings import RobotSettings



class InternalWindow():
    
    def __init__(self, name, x_pos, y_pos ,x_size, y_size):
        self._frame = Frame(bg = "GRAY", borderwidth=1, relief='solid') 
        
        self._settings_key = self.__class__.__name__

        self._min_width = 200
        self._min_height = 150

        x_pos = RobotSettings.get_int(self._settings_key+".x_pos")
        y_pos = RobotSettings.get_int(self._settings_key+".y_pos")
        x_size = RobotSettings.get_int(self._settings_key+".x_size")
        y_size = RobotSettings.get_int(self._settings_key+".y_size")

        if x_size < self._min_width:  
            x_size = self._min_width 

        if y_size < self._min_height:  
            y_size =self._min_height 

        self._frame.place(height=y_size, width = x_size, x=x_pos, y=y_pos)

        self._title = WindowTitle(self._frame, self)
        self._title.rename(name)

        self._resizer =  WindowResizer(self._frame, self)    
        self._closer =  WindowCloser(self._frame, self)    

        self.resize_window()


    def move(self, x_delta, y_delta):
        x = self._frame.winfo_x()
        y = self._frame.winfo_y()
        new_x = x - x_delta
        new_y = y - y_delta
        if(new_x > 0 ):
            x = x - x_delta
        if(new_y > 0): 
            y = y - y_delta
        self._frame.place(x = x, y = y)

        RobotSettings.set_key(self._settings_key+".x_pos" ,x)
        RobotSettings.set_key(self._settings_key+".y_pos", y)

        
    def rename(self, new_name):
         self._title.rename(new_name)

    def draw(self):
      self.resize_window()

    def resize(self, width, height):

        if width < self._min_width:
            width = self._min_width

        if  height < self._min_height:
            height = self._min_height

        x = self._frame.winfo_x()
        y = self._frame.winfo_y()

        self._frame.place(x=x, y = y, width = width, height= height)

        RobotSettings.set_key(self._settings_key+".x_size" ,width)
        RobotSettings.set_key(self._settings_key+".y_size", height)

        self.resize_window()



    def resize_window(self):
 
        self._frame.update()
        x_size = self._frame.winfo_width() 
        y_size = self._frame.winfo_height()

        self._title._canvas.place(height=30, width = x_size -27 , x=0, y = 0)
        self._resizer._canvas.place(height=20, width = 20, x=x_size-22, y=y_size-22)
        self._closer._canvas.place(height=30, width = 30, x=x_size-30, y = 0)
        

    def set_min_dimension(self, new_min_x, new_min_y):
        self._min_width = new_min_x
        self._min_height =new_min_y
        #toDo auto resize !


    def close(self):
        self._frame.place_forget()
        self._frame.destroy()


    def set_robot(self, robot):
        return True
    

    def save_bounds(self, x_pos, y_pos, width, height):
        RobotSettings.set_key(self._settings_key+".x_pos" ,x_pos)
        RobotSettings.set_key(self._settings_key+".y_pos", y_pos)
        RobotSettings.set_key(self._settings_key+".x_size" ,width)
        RobotSettings.set_key(self._settings_key+".y_size", height)


    def on_closing(self):
        self.save_bounds()



"""
package de.hska.lat.robot.displayFrame;



import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;


import de.hska.lat.robot.abstractRobot.AbstractRobot;
import de.hska.lat.robot.ui.settings.UiSettings;

public class DisplayFrame extends JInternalFrame implements InternalFrameListener, ComponentListener 
{




	/**
	 * 
	 */
	private static final long serialVersionUID = -185116113757512604L;

	protected String settingsKey;
	
	
	protected static final String xPositionKey = ".xPosition"; 
	protected static final String yPositionKey = ".yPosition";

	protected static final String xSizeKey = ".xSize"; 
	protected static final String ySizeKey = ".ySize";
	
	
public DisplayFrame(String frameName, boolean b, boolean c, boolean d,
		boolean e)
{
	super(frameName,b,c,d,e);
	
	this.settingsKey=this.getClass().getName();
	
	
	this.setBounds(UiSettings.recoverInt(settingsKey+xPositionKey,10),
			UiSettings.recoverInt(settingsKey+yPositionKey,10),
			UiSettings.recoverInt(settingsKey+xSizeKey,100),
			UiSettings.recoverInt(settingsKey+ySizeKey,100));

	this.addInternalFrameListener(this);
	
	this.addComponentListener(new ComponentAdapter() 
	{
	    public void componentMoved(ComponentEvent e) 
	    {
	    	
	    }
	});
	
	this.addComponentListener(this);
	
}




@Override
public void internalFrameActivated(InternalFrameEvent arg0)
{
	// TODO Auto-generated method stub
	
}





@Override
public void internalFrameClosed(InternalFrameEvent arg0)
{
	// TODO Auto-generated method stub
	
}





@Override
public void internalFrameClosing(InternalFrameEvent arg0)
{
	this.onClosing();
}





@Override
public void internalFrameDeactivated(InternalFrameEvent arg0)
{
	// TODO Auto-generated method stub
	
}





@Override
public void internalFrameDeiconified(InternalFrameEvent arg0)
{
	// TODO Auto-generated method stub
	
}





@Override
public void internalFrameIconified(InternalFrameEvent arg0)
{
	// TODO Auto-generated method stub
	
}





@Override
public void internalFrameOpened(InternalFrameEvent arg0)
{
	// TODO Auto-generated method stub
	
}




@Override
public void componentHidden(ComponentEvent arg0)
{
	// TODO Auto-generated method stub
	
}




@Override
public void componentMoved(ComponentEvent arg0)
{
	DisplayFrame.this.saveBounds();
}




@Override
public void componentResized(ComponentEvent arg0)
{
	DisplayFrame.this.saveBounds();
}




@Override
public void componentShown(ComponentEvent arg0)
{
	// TODO Auto-generated method stub
	
}
	

}
"""