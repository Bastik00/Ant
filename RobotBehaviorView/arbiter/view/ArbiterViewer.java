package de.hska.lat.behavior.arbiter.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.TitledBorder;

import de.hska.lat.behavior.arbiter.Arbiter;
import de.hska.lat.behavior.arbiter.ArbiterChangeNotifier;
import de.hska.lat.behavior.arbiter.action.view.ActionListView;
import de.hska.lat.behavior.behavior.RobotBehavior;
import de.hska.lat.robot.RobotSettings;



public abstract class ArbiterViewer <A extends Arbiter> extends JPanel implements MouseListener, MouseMotionListener, ArbiterChangeNotifier
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4136329052303381549L;

	
	protected A arbiter;
	protected int originX;
	protected int originY;
	
	protected String settingsKey;
	protected static final String X_POSITION_KEX = ".xPosition";
	protected static final String Y_POSITION_KEX = ".yPosition";
	
	protected RobotSettings settings;
	
	protected ActionListView actionViewList;
	
	protected JPopupMenu contextMenue;

	protected static final String SHOW_ACTIONS_TEXT =	"show actions";
	protected static final String SHOW_CONTROLS_TEXT =	"show controls"; 
	protected RobotBehavior<?> behavior;
	
public	ArbiterViewer(RobotBehavior<?> behavior, A arbiter, RobotSettings settings)
{
	this.settingsKey=this.getClass().getName()+":"+arbiter.getName();
	this.settings = settings;
	this.arbiter = arbiter;
	this.behavior = behavior;
	
	
	this.build();
	
	this.arbiter.addChangeListener(this);
	
	this.setBorder(new TitledBorder(arbiter.getName()));
	
	this.setVisible(true);
	
    ;
	this.setLocation(settings.recoverInt(settingsKey + X_POSITION_KEX, 10),
			settings.recoverInt(settingsKey + Y_POSITION_KEX, 10));

	
	
	this.addMouseListener(this);
	this.addMouseMotionListener(this);
	this.arbiter.addChangeListener(this);
	
	this.makePopupMenu();
	
	

	//((TitledBorder)
			
//	Border border = this.getBorder(); //.getBorder();         equals(  ).setTitleColor(Color.RED);
	

}
	

protected abstract void build();



protected void makePopupMenu()
{
	this.contextMenue = new JPopupMenu();
	
	  MouseListener popupListener = new PopupListener();
	  
	  addMenuItem(this.contextMenue, SHOW_ACTIONS_TEXT, new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			ArbiterViewer.this.showActions();
			
		}
	  });

	  
	  addMenuItem(this.contextMenue, SHOW_CONTROLS_TEXT, new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			ArbiterViewer.this.showControls();
			
		}
	  });
	  

    this.addMouseListener(popupListener);
}


protected JMenuItem addMenuItem(JPopupMenu popupMenu, String text, ActionListener actionListener)
{
	
	  JMenuItem  menuItem = new JMenuItem(text);
	   menuItem.addActionListener(actionListener);
	   popupMenu.add(menuItem);

    
	   return(menuItem); 
}



protected void showActions()
{

	if (actionViewList==null)
	{
		this.actionViewList = new ActionListView(this.behavior, this.arbiter); 
		
		this.actionViewList.setBounds(10,40,200,100);
		this.actionViewList.show();
		getParent().add(this.actionViewList);
	}
	else
	{
		this.actionViewList.toFront();
	}
}


protected void showControls()
{
	
}

public Arbiter getArbiter() {
	return arbiter;
}

@Override
public void mouseDragged(MouseEvent mouseEvent)
{

	{
		mouseEvent.getX();
		mouseEvent.getY();
	
		this.setLocation(this.getX()-originX+mouseEvent.getX(),this.getY()-originY+mouseEvent.getY());

		
		
		settings.saveInt(settingsKey + X_POSITION_KEX, this.getX());
		settings.saveInt(settingsKey + Y_POSITION_KEX, this.getY());
		this.settings.writeSettings();
		this.getParent().repaint();
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
public void arbiterChanged(Arbiter arbiter)
{

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

    private void maybeShowPopup(MouseEvent e) {
        if (e.isPopupTrigger()) 
        {
        	contextMenue.show(e.getComponent(),
                       e.getX(), e.getY());

        }
    }
}



@Override
public void statusChanged(Arbiter arbiter)
{

	if (arbiter.isReady())
	{
		((TitledBorder) this.getBorder()).setTitleColor(Color.GREEN);
	}
	else if (arbiter.isActive())
	{
		((TitledBorder) this.getBorder()).setTitleColor(Color.BLUE);
	}
	else if (arbiter.isInErrorMode())
	{
		((TitledBorder) this.getBorder()).setTitleColor(Color.RED);
	}
}


}
