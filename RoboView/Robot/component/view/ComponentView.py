
from tkinter import Frame, Label, Menu

from RoboView.Robot.Viewer.RobotSettings import RobotSettings
 

class ComponentView:
	def __init__(self, root, name, settings_key,  width, height):

		self._settings_key = settings_key+"."+self.__class__.__name__+"."+name

		self._frame = Frame(master = root, bg = "BLACK", borderwidth=2, width =300)
		self._master = root
		self._width = width
		self._height = height

		self._data_frame = Frame(master = self._frame, bg = "YELLOW", borderwidth=0)
		
		x_pos = RobotSettings.get_int(self._settings_key+".x_pos")
		y_pos = RobotSettings.get_int(self._settings_key+".y_pos")
		self._frame.place(x = x_pos, y = y_pos)

		self._display_name = RobotSettings.get_bool(self._settings_key+".display_name")		



		self._data_frame.bind("<Button-1>", self.mouse_pressed)
		self._data_frame.bind("<ButtonRelease-1>", self.mouse_released)
		self._data_frame.bind("<Leave>", self.mouse_released)
	
		self._data_frame.bind("<ButtonRelease-3>", self.show_context_manue)
	
		self.build_context_menue()

		self._name = name

		self._name_label = Label(self._frame, text=self._name, font=("Courier", 12))
		self.draw()



	def build_context_menue(self):
		self._context_menue = Menu(self._frame, tearoff=0)
		self._context_menue.add_command(label="display name", command=self.on_display_name) 
		self._context_menue.add_command(label="lock position", command=self.on_lock_position) 
		self._context_menue.add_separator()


	def mouse_pressed(self,event):
		self._origin_x = event.x
		self._origin_y = event.y
		self._data_frame.bind("<Motion>", self.mouse_motion)
		print("mouse pr")


	def mouse_motion(self, event):
		x = self._frame.winfo_x()
		y = self._frame.winfo_y()
		x = x - self._origin_x + event.x 
		y = y - self._origin_y + event.y 

		self._frame.place(x = x, y = y)
        
		RobotSettings.set_key(self._settings_key+".x_pos" ,x)
		RobotSettings.set_key(self._settings_key+".y_pos", y)



	def mouse_released(self,event):
		self._data_frame.unbind("<Motion>")


	def show_context_manue(self,event):

		try:
			self._context_menue.tk_popup(event.x_root, event.y_root)
		finally:
			self._context_menue.grab_release()

	def on_lock_position(self):
		#self._device.remote_clear_cpu_statistics()
		pass

	def on_display_name(self):
		#self._frame.place( width=100, height=100)
		if (self._display_name): 
			self._display_name = False
		else: 
			self._display_name = True

		RobotSettings.set_key(self._settings_key+".display_name" ,self._display_name)		
		self.draw()
	

	def draw(self):
		
		height = self._height
		width = self._width
		y = 2
		if self._display_name: 
			self._name_label.place(x = 1, y = 1, width = self._width, height=15 )
			y += 16
		else:
			self._name_label.place(x = 1, y = -17, width = self._width, height=15)
			pass

		self._frame.place(width = width + 6 , height = height + y + 6)  
		self._data_frame.place(x = 1, y = y ,width = self._width , height = self._height )
"""package de.hska.lat.robot.component.view;



import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;

import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import javax.swing.border.TitledBorder;

import de.hska.lat.robot.ui.settings.UiSettings;



public abstract class ComponentView  extends  JPanel implements MouseListener, MouseMotionListener, ActionListener, ComponentViewControl
{
	private static final long serialVersionUID = -2581686906476487529L;

	
	protected static final String DISPLAY_NAME_TEXT = "Display Name";
	protected static final String LOCK_POSITION_TEXT = "lock position";
	
	
	protected static final String cmdDisplayName = "cmdDisplayName";
	protected static final String cmdLockPosition = "cmdLockPosition";
	
	protected static final String xPositionKey = ".xPosition"; 
	protected static final String yPositionKey = ".yPosition";
	protected static final String moveableKey = ".movealble";
	protected static final String showNameKey = ".showName";
	
	
	protected boolean showName = true;

	protected static final int width = 100;
	protected static final int height = 100;
	
	protected int actualWidth 	= 0;
	protected int actualHeight 	= 0;
	
	protected JPopupMenu contextMenue;
	 
	
	protected JMenuItem showNameMenueItem;
	protected JMenuItem lockPositionItem;
	
	protected ResizeEdge resizeEdgePanel;
	protected Border border;
	
	protected String componentName;
	
	
	protected int originX;
	protected int originY;

	protected boolean movable;
	protected boolean resizable;
	protected String settingsKey;
	protected final boolean embedded;
	
/**
 * Builds a Component
 * @param componentName
 * @param embedded
 */
public ComponentView(String componentName, boolean embedded)
{

	
	this.componentName = componentName;
	this.embedded = embedded;
	
	this.setLayout(null);
	this.setToolTipText(this.componentName);
	
	
	this.settingsKey=this.getClass().getName()+"."+this.componentName;
	this.setLocation(UiSettings.recoverInt(settingsKey+ComponentView.xPositionKey,50),
			UiSettings.recoverInt(settingsKey+ComponentView.yPositionKey,50));
	
	
	this.makePopupMenu();

	this.setMoveable(UiSettings.recoverBoolean(this.settingsKey+ComponentView.moveableKey,true));
	this.showName=UiSettings.recoverBoolean(this.settingsKey+ComponentView.showNameKey,true);

	
	this.addMouseListener(this);
	this.addMouseMotionListener(this);
	
	
	this.setActualSize(ComponentView.width, ComponentView.height);
	
	this.resizeEdgePanel = new ResizeEdge(this);

}


protected void buildView()
{	
	this.removeAll();
	
	if (this.embedded)
	{
		this.showName=false;
	}
	
	if (this.showName)
	{
		this.border = new TitledBorder(this.componentName);
	
	}
	else
	{
		this.border = new BevelBorder(BevelBorder.LOWERED);
	}
	this.setBorder(this.border);
	
	Insets insets = this.border.getBorderInsets(this);
	
	this.setSize( insets.left + insets.right + this.getViewWidth(),
			insets.top + insets.bottom + this.getViewHeight());


	this.resizeEdgePanel.setBounds(this.getWidth() - insets.right - 15,
								this.getHeight() - insets.bottom - 15, 20, 20);
	
	if (this.resizable)
	{
		this.resizeEdgePanel.setOpaque(false);
		this.add(this.resizeEdgePanel);	
	}
	else
	{
		this.remove(this.resizeEdgePanel);
	}
	
}


public boolean isShowingName()
{
	return(this.showName);
}

/**
 * 
 * @param status
 */

public void showName(boolean status)
{
	this.showName = status;
	this.buildView();
}


public void setMoveable(boolean status)
{
	if (!this.embedded)
	{
	this.movable= status;
	this.lockPositionItem.setSelected(!this.movable);
	UiSettings.saveBoolean(this.settingsKey+ComponentView.moveableKey, status);
	}
}

public boolean isMoveable()
{
	return(this.movable);
}



protected int getViewWidth()
{
	if (this.resizable)
	{
	 return(this.actualWidth);	
	}
	else
	{
	return(ComponentView.width);
	}
}

protected int getViewHeight()
{
	if (this.resizable)
	{
	 return(this.actualHeight);	
	}

	return(ComponentView.height);
}

protected void setActualSize(int x, int y) {
	this.actualWidth = x;
	this.actualHeight = y;
}

protected Dimension getViewportSize()
{
	return(new Dimension(this.getViewWidth(), this.getViewHeight()));
}


protected JMenuItem addRadioButtonMenuItem(JPopupMenu popupMenu, String text, String command)
{
	
	  JMenuItem  menuItem = new JRadioButtonMenuItem(text);
	   menuItem.addActionListener(this);
	   menuItem.setActionCommand(command);
	   popupMenu.add(menuItem);

    
	   return(menuItem); 
}


protected JMenuItem addMenuItem(JPopupMenu popupMenu, String text, String command)
{
	
	  JMenuItem  menuItem = new JMenuItem(text);
	   menuItem.addActionListener(this);
	   menuItem.setActionCommand(command);
	   popupMenu.add(menuItem);

    
	   return(menuItem); 
}


protected JCheckBoxMenuItem addCheckBoxMenuItem(JPopupMenu popupMenu,String MenuItemText,String ActionCommand)
{
	JCheckBoxMenuItem tmpItem;
	
	tmpItem = new JCheckBoxMenuItem(MenuItemText);
	tmpItem.setActionCommand(ActionCommand);
	tmpItem.addActionListener(this);
	popupMenu.add(tmpItem);
	
	return(tmpItem);
	
}





protected void makePopupMenu()
{
	this.contextMenue = new JPopupMenu();
	
	  MouseListener popupListener = new PopupListener();
	  if (!this.embedded)
	  {
	  this.showNameMenueItem = this.addCheckBoxMenuItem(this.contextMenue , ComponentView.DISPLAY_NAME_TEXT, ComponentView.cmdDisplayName);
	  this.lockPositionItem = this.addCheckBoxMenuItem(this.contextMenue , ComponentView.LOCK_POSITION_TEXT, ComponentView.cmdLockPosition);
	  }
	 	

    this.addMouseListener(popupListener);
}




protected void saveSettings()
{
	UiSettings.saveInt(this.settingsKey+xPositionKey, this.getX());
	UiSettings.saveInt(this.settingsKey+yPositionKey, this.getY());
}




protected void close() 
{
}




protected  void onResize()
{

}



@Override
public void mouseDragged(MouseEvent mouseEvent)
{

	if (this.movable)
	{
		mouseEvent.getX();
		mouseEvent.getY();
	
		this.setLocation(this.getX()-originX+mouseEvent.getX(),this.getY()-originY+mouseEvent.getY());
		this.saveSettings();
	}
}

@Override
public void mouseMoved(MouseEvent arg0)
{
	// TODO Auto-generated method stub
	
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
public void mousePressed(MouseEvent mouseEvent) 
{
	this.originX=mouseEvent.getX();
	this.originY=mouseEvent.getY();
}


@Override
public void mouseReleased(MouseEvent arg0)
{
	// TODO Auto-generated method stub
	
}




@Override
public void actionPerformed(ActionEvent actionEvent)
{

	String cmd;
	
	cmd = actionEvent.getActionCommand();
	
	if (cmd.equals(cmdDisplayName))
	{
		this.showName=!this.showName;
		this.showNameMenueItem.setSelected(this.showName);
		UiSettings.saveBoolean(this.settingsKey+ComponentView.showNameKey, this.showName);
		this.buildView();

	}
	else if (cmd.equals(cmdLockPosition))
	{
		this.setMoveable(!this.isMoveable());
		

	}
	
}




class PopupListener extends MouseAdapter 
{
    public void mousePressed(MouseEvent e) 
    {
        maybeShowPopup(e);
    }

    public void mouseReleased(MouseEvent e) 
    {
        maybeShowPopup(e);
    }

    private void maybeShowPopup(MouseEvent e) 
    {
        if (e.isPopupTrigger()) 
        {
        	contextMenue.show(e.getComponent(),
                       e.getX(), e.getY());

        }
    }
}



@Override
public void resizeView(int x, int y) // x, y Differnez
{	
	this.setActualSize(this.getViewWidth() + x, this.getViewHeight() + y);
	this.onResize();
	this.buildView();	
}



}
"""