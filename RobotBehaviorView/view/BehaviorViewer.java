package de.hska.lat.behavior.view;


import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ListIterator;

import javax.swing.JFrame;

import de.hska.lat.behavior.arbiter.Arbiter;

import de.hska.lat.behavior.arbiter.ArbiterGroup;
import de.hska.lat.behavior.arbiter.InhibitorNode;
import de.hska.lat.behavior.arbiter.SuppressorNode;
import de.hska.lat.behavior.arbiter.component.servo.ServoArbiter;
import de.hska.lat.behavior.arbiter.view.ArbiterDefaultViewer;
import de.hska.lat.behavior.arbiter.view.ArbiterGroupViewer;
import de.hska.lat.behavior.arbiter.view.ArbiterViewer;
import de.hska.lat.behavior.arbiter.view.InhibitorNodeViewer;
import de.hska.lat.behavior.arbiter.view.ServoArbiterViewer;
import de.hska.lat.behavior.arbiter.view.SuppressorNodeViewer;
import de.hska.lat.behavior.behavior.RobotBehavior;
import de.hska.lat.behavior.behavior.manualControl2D.ManualControl2D;
import de.hska.lat.behavior.manualControl.view.ManualControl2DViewer;
import de.hska.lat.robot.RobotSettings;




public class BehaviorViewer extends JFrame implements WindowListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3533934849855070957L;

	protected RobotBehavior<?> behavior;
	protected RobotSettings settings;
	
	protected static final String X_POSITION_KEY 	= ".xPositionKey";
	protected static final String Y_POSITION_KEY 	= ".yPositionKey";
	protected static final String X_SIZE_KEY 	= ".xSizeKey";
	protected static final String Y_SIZE_KEY 	= ".ySizeKey";
	
	
	
	protected String settingsKey;

	private ArbiterLinkViewer parentLinkPanel;
	
public 	BehaviorViewer(RobotBehavior<?> behavior, RobotSettings settings)
{
	this.settingsKey=this.getClass().getName();
	
	this.settings = settings;
	this.behavior = behavior;
	
	parentLinkPanel = new ArbiterLinkViewer();
	this.setContentPane(parentLinkPanel);
	
	init(settings);
	
}
	



protected void init(RobotSettings settings)
{
	
	this.setLayout(null);
    this.setSize(200,200);

    
	this.setBounds(this.settings.recoverInt(settingsKey + X_POSITION_KEY, 10),
			this.settings.recoverInt(settingsKey + Y_POSITION_KEY, 10),
			this.settings.recoverInt(settingsKey + X_SIZE_KEY, 600),
			this.settings.recoverInt(settingsKey + Y_SIZE_KEY, 600));

	this.addWindowListener(this);
    
    this.setVisible(true);
	

    
	ListIterator<Arbiter> arbiterList;
	//HashMap<ArbiterViewer, ArrayList<ArbiterViewer>> map = new HashMap<ArbiterViewer, ArrayList<ArbiterViewer>>();
	
	if (behavior!=null){
		
		for (arbiterList = this.behavior.getArbiters() ; arbiterList.hasNext(); ) 
		{
		    Arbiter arbiter = arbiterList.next();
		    ArbiterViewer<?> arbiterViewer = this.findViewer(arbiter,  settings);

		    if (arbiterViewer!=null)
		    {
		    	this.parentLinkPanel.add(arbiterViewer);
		    }  
		}
	}
	
	ArbiterMapping mapping = new ArbiterMapping(this.behavior.getArbiters());
	parentLinkPanel.setMapping(mapping.getArbiterMap());
		
}


///**
// * Creates an Mapping of the Arbiters which will be used by the ArbiterLinkViewer to show connections between arbiters.
// * Will be transfered to own class in future.
// * @param arbiterViewer
// * @param map
// */
//private void createMapping(ArbiterViewer<?> arbiterViewer, HashMap<ArbiterViewer, ArrayList<ArbiterViewer>> map){
//    ArrayList<ArbiterViewer> out = new ArrayList<ArbiterViewer>();
//    
//    for (ArbiterConnection connection : arbiterViewer.getArbiter().getAllOutputs()) {
//    	Arbiter preArbiter = (Arbiter)connection.getListener();
//
//    	out.add(getView(preArbiter));
//    	map.put(arbiterViewer, out);
//	}
//  
////    if (arbiter instanceof ArbiterGroup) {
////    	 System.out.print("GROUP: ");
////    	 
////    	 ListIterator<Arbiter> arbiterListInGroup;
////    	 for (arbiterListInGroup = ((ArbiterGroup) arbiter).getArbiters() ; arbiterListInGroup.hasNext(); ) 
////    		{
////    		 Arbiter arbiter2 = arbiterListInGroup.next();
////    		  System.out.print(arbiter2.getId() + " ,  ");
//////    		  for (ArbiterConnection connection : arbiter2.getAllOutputs()) {
//////    			  	if(connection.getListener() != null){
//////    			  		Arbiter preArbiter = (Arbiter)connection.getListener();
//////	    		    	 System.out.print(preArbiter.getId() + " ,  ");
//////    			  	}
//////    			}
////    		}
////    	 System.out.print("GROUP END ");
////	}
//}




public ArbiterViewer<?> findViewer(Arbiter arbiter,RobotSettings settings)
{
	
	    ArbiterViewer<?> arbiterViewer;
	    
	    if (arbiter instanceof SuppressorNode)
	    {
		    arbiterViewer = new SuppressorNodeViewer(this.behavior, (SuppressorNode)arbiter, settings);
	    }
	    else if (arbiter instanceof InhibitorNode)
	    {
		    arbiterViewer = new InhibitorNodeViewer(this.behavior, (InhibitorNode)arbiter, settings);
	    } 
	    else if (arbiter instanceof ServoArbiter)
	    {
		    arbiterViewer = new ServoArbiterViewer(this.behavior, (ServoArbiter)arbiter, settings);
	    }
	    else if (arbiter instanceof ManualControl2D)
	    {
		    arbiterViewer = new ManualControl2DViewer(this.behavior, (ManualControl2D)arbiter, settings);
	    }
	    
	    else if (arbiter instanceof ArbiterGroup)
	    {
		    arbiterViewer = new ArbiterGroupViewer<>(this, this.behavior, (ArbiterGroup)arbiter, settings);
	    }
	    
	    else
	    {
	    	arbiterViewer = new ArbiterDefaultViewer(this.behavior, arbiter, settings);
	    }
	    return(arbiterViewer);
	    
	
}



public void saveSettings()
{
	
	
	this.settings.saveInt(settingsKey + X_POSITION_KEY, this.getX());
	this.settings.saveInt(settingsKey + Y_POSITION_KEY, this.getY());
	this.settings.saveInt(settingsKey + X_SIZE_KEY, this.getWidth());
	this.settings.saveInt(settingsKey + Y_SIZE_KEY, this.getHeight());
	
	this.settings.writeSettings();
}



@Override
public void windowActivated(WindowEvent arg0)
{
	// TODO Auto-generated method stub
	
}




@Override
public void windowClosed(WindowEvent arg0)
{
	// TODO Auto-generated method stub
	
}




@Override
public void windowClosing(WindowEvent arg0)
{
	// TODO Auto-generated method stub
	
	this.saveSettings();
}




@Override
public void windowDeactivated(WindowEvent arg0)
{
	// TODO Auto-generated method stub
	
}




@Override
public void windowDeiconified(WindowEvent arg0)
{
	// TODO Auto-generated method stub
	
}




@Override
public void windowIconified(WindowEvent arg0)
{
	// TODO Auto-generated method stub
	
}




@Override
public void windowOpened(WindowEvent arg0)
{
	// TODO Auto-generated method stub
	
}


}
