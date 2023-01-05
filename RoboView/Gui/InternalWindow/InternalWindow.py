

from tkinter import Frame, Button, Toplevel
import customtkinter as ctk
from RoboView.Gui.InternalWindow.WindowCloser import WindowCloser
from RoboView.Gui.InternalWindow.WindowResizer import WindowResizer

from RoboView.Gui.InternalWindow.WindowTitle import WindowTitle
from RoboView.Robot.Viewer.RobotSettings import RobotSettings
from RoboView.Gui.InternalWindow.ExternalWindow import ExternalWindow

class InternalWindow():

    def __init__(self, name, window_bar):
        
        self._frame = Frame(bg="GRAY", borderwidth=1, relief='solid')
        self._settings_key = self.__class__.__name__

        self._min_width = 200
        self._min_height = 150

        self._x_pos = RobotSettings.get_int(self._settings_key+".x_pos")
        self._y_pos = RobotSettings.get_int(self._settings_key+".y_pos")
        self._width = RobotSettings.get_int(self._settings_key+".x_size")
        self._height = RobotSettings.get_int(self._settings_key+".y_size")

        if self._width < self._min_width:
            self._width = self._min_width

        if self._height < self._min_height:
            self._height = self._min_height

        self._frame.place(height=self._height, width=self._width, x=self._x_pos, y=self._y_pos)
        

        self._title = WindowTitle(self._frame, self, name)
        
        self._resizer = WindowResizer(self._frame, self)
        self._closer = WindowCloser(self._frame, self)
        self.resize_window()
        self._external_window = None
        self._window_bar = window_bar


    def move(self, x_delta, y_delta):
        self._frame.lift()
        x = self._frame.winfo_x()
        y = self._frame.winfo_y()
        new_x = x - x_delta
        new_y = y - y_delta
        if (new_x > 0):
            self._x_pos = x - x_delta
        if (new_y > 0):
            self._y_pos = y - y_delta
            
        self._frame.place(x=self._x_pos, y=self._y_pos)
        
        RobotSettings.set_key(self._settings_key+".x_pos", x)
        RobotSettings.set_key(self._settings_key+".y_pos", y)

    def rename(self, new_name):
        self._title.rename(new_name)

    def draw(self):
        self.resize_window()

    def resize(self, width, height):

        if width < self._min_width:
            self._width = self._min_width
        else:
            self._width = width

        if height < self._min_height:
            self._height = self._min_height
        else:
             self._height = height
        self._frame.place(x=self._x_pos, y=self._y_pos, width=self._width, height=self._height)

        RobotSettings.set_key(self._settings_key+".x_size", self._width)
        RobotSettings.set_key(self._settings_key+".y_size", self._height)

        self.resize_window()

    def resize_window(self):
        self._frame.lift()
        self._frame.update()
        
        self._title._canvas.place(height=30, width=self._width - 27, x=0, y=0)
        self._resizer._canvas.place(
            height=22, width=22, x=self._width-24, y=self._height-24)
        self._closer._canvas.place(x=self._width-30, y=0)
        

    def set_min_dimension(self, new_min_x, new_min_y):
        self._min_width = new_min_x
        self._min_height = new_min_y
        # toDo auto resize !

    def close(self, event):
        self._frame.place_forget()
        self._frame.destroy()
        self.on_close_device()

    def set_robot(self, robot):
        return True

    def save_bounds(self, x_pos, y_pos, width, height):
        RobotSettings.set_key(self._settings_key+".x_pos", x_pos)
        RobotSettings.set_key(self._settings_key+".y_pos", y_pos)
        RobotSettings.set_key(self._settings_key+".x_size", width)
        RobotSettings.set_key(self._settings_key+".y_size", height)

    def on_closing(self):
        RobotSettings.set_key(self._title._name+".isOpen", False)
        print(self._title._name+".isOpen")
        self.save_bounds()
    
    def extract_window(self):
        self.hide_window()
        self._external_window = ExternalWindow(self)
        
        
    def hide_toplevel_window(self):
        #self._window.withdraw()
        self._window.wm_deiconify()
        
    def hide_window(self):
        RobotSettings.set_key(self._settings_key+".visible", False)
        self._frame.place(x=-10000, y=-10000)
    
    def show_window(self):
        RobotSettings.set_key(self._settings_key+".visible", True)
        self._frame.place(x=self._x_pos, y=self._y_pos)
        
    def minimize_window(self):
        self.hide_window()
        self._window_bar.add_window(self)
        


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
