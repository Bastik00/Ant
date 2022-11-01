package de.hska.lat.robot.display.generics.map.navigationPath;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import de.hska.lat.math.vector.FloatVector3D;
import de.hska.lat.navigation.path.NavigationPath;
import de.hska.lat.navigation.path.NavigationPoint;
import de.hska.lat.navigation.path.action.NavPointAction;
import de.hska.lat.robot.display.generics.map.navigationPath.action.view.ActionEditorProvider;
import de.hska.lat.robot.display.generics.map.navigationPath.action.view.DefaultActionsEditors;
import de.hska.lat.robot.displayFrame.DisplayFrame;




public class NavigationPathManager extends DisplayFrame implements ActionListener
{

	/**
	 * 
	 */
	

	
	private static final long serialVersionUID = -6760515425796794992L;
	protected static final String FRAME_NAME = "Path Manager";
	
	protected final static String WAYPOINT_TEXT = "waypoint";
	protected final static String PATH_TEXT = "path";
	protected final static String POSITION_TEXT = "position";
	protected final static String TEXT_ADD = "add";
	protected final static String REMOVE_TEXT = "remove";
	
	
	protected final static String [] EXCEPTION_TEXT = {"next","wait"};
	protected final static String ACTION_TEXT	= "action";
	

	
	protected final static String CMD_ACTION_CHANGED = "cmdActionChanged";
	
	
	protected final static String CMD_ADD_NAVPOINT = "cmdAddNavpoint";
	protected final static String CMD_REMOVE_NAVPOINT = "cmdRemoveNavpoint";
	protected final static String CMD_NAVPOINT_SELECTED = "cmdNavpointSelected";
	protected final static String CMD_NAVPOINT_RENAMED = "cmdNavpoindRenamed";
	
	protected final static String CMD_PATH_SELECTED = "cmdPathSelected";
	
	protected final static String CMD_ADD_PATH = "cmdPathAdd";
	protected final static String CMD_PATH_RENAMED = "cmdPathRenamed";
	
	protected JTextField xPosition;
	protected JTextField yPosition;
	protected JTextField zPosition;
	
	

	protected JPanel pathPanel;
	protected JTextField pathName;
	
	protected JPanel navpointPanel;
	protected JPanel actionSettingsPanel;

	
	protected NavigationActionSelector actionSelector;
	protected JPanel actionPanel;
	
	protected JComboBox<NavigationPath> pathList; 
	
	protected PositionPanel positionPanel;
	
	/* list of all paths */
	protected ArrayList<NavigationPath> paths;// = new ArrayList<NavigationPath>();
	
	/* actual edited path */
	protected NavigationPath activePath; 
	
public NavigationPathManager()
{
	super(NavigationPathManager.FRAME_NAME, false,true, false , false );

	
	this.setLayout(null);
	this.addPathEditor();
	this.addWaypointEditor();
	
	
	this.positionPanel = new PositionPanel();
	this.positionPanel.setBounds(10,200,150,150);
	this.add(this.positionPanel);
	
	this.addPositionEditor();
	this.addActionEditor();
	
	this.setSize(400, 400);
	this.show();


}



private void addPathEditor()
{
	this.pathPanel = new JPanel();
	this.pathPanel.setLayout(null);
	this.pathPanel.setBorder( new TitledBorder(NavigationPathManager.PATH_TEXT));
	this.pathPanel.setBounds(0,0,385,100);
	//this.pathPanel.setPreferredSize(new Dimension(150,50));
	
	this.pathList = new JComboBox<NavigationPath>(); 
//	this.actionComboBox.setBounds(insets.left+5,insets.top+5,100,25);

	this.pathList.setPreferredSize(new Dimension (100,30)); 
	this.pathList.setBounds(10,30,150,30);
	this.pathList.addActionListener(this);
	this.pathList.setActionCommand(NavigationPathManager.CMD_PATH_SELECTED);
	
	this.pathPanel.add(this.pathList); //, BorderLayout.LINE_END);
	
	
	
	this.pathName = new JTextField();
	this.pathName.setBounds(170,30,100,30);
	this.pathName.addActionListener(this);
	this.pathName.setActionCommand(NavigationPathManager.CMD_PATH_RENAMED);
	
	this.pathPanel.add(this.pathName); //, BorderLayout.LINE_END);
	
	
	JButton tmpButton;
	
	tmpButton= new JButton(NavigationPathManager.TEXT_ADD);
	tmpButton.addActionListener(this);
	tmpButton.setActionCommand(NavigationPathManager.CMD_ADD_PATH);
	tmpButton.setBounds(280,30,50,30);
	
	this.pathPanel.add(tmpButton);
	

	
	
	
	
	
	this.add(this.pathPanel);
}
	
protected  NavigationPointSelector  navpointList;
protected JTextField navpointName;






private void addWaypointEditor()
{
	this.navpointPanel = new JPanel();
	this.navpointPanel.setLayout(null);
	this.navpointPanel.setBorder( new TitledBorder(NavigationPathManager.WAYPOINT_TEXT));
//	positionPanel.setSize(new Dimension(200,200));
	//this.waypointPanel.setPreferredSize(new Dimension(150,50));
	this.navpointPanel.setBounds(0,100,385,100);
	
	
	
	this.navpointList = new NavigationPointSelector(); 
//	this.actionComboBox.setBounds(insets.left+5,insets.top+5,100,25);

	this.navpointList.setPreferredSize(new Dimension (100,30)); 
	this.navpointList.setBounds(10,30,150,30);
	this.navpointList.addActionListener(this);
	this.navpointList.setActionCommand(NavigationPathManager.CMD_NAVPOINT_SELECTED);
	
	this.navpointPanel.add(this.navpointList); //, BorderLayout.LINE_END);
	
	
	this.navpointName = new JTextField();
	this.navpointName.setBounds(170,30,100,30);
	this.navpointName.setActionCommand(NavigationPathManager.CMD_NAVPOINT_RENAMED);
	
	
	this.navpointName.addActionListener(this);
	//this.pathName.setActionCommand(NavigationPathManager.cmdPathSelected);
	
	this.navpointPanel.add(this.navpointName); //, BorderLayout.LINE_END);
	
	
	JButton tmpButton;
	
	tmpButton= new JButton(NavigationPathManager.TEXT_ADD);
	tmpButton.addActionListener(this);
	tmpButton.setActionCommand(NavigationPathManager.CMD_ADD_NAVPOINT);
	tmpButton.setBounds(280,30,50,30);
	
	this.navpointPanel.add(tmpButton);
	
	
	
	tmpButton= new JButton(NavigationPathManager.REMOVE_TEXT);
	tmpButton.addActionListener(this);
	tmpButton.setActionCommand(NavigationPathManager.CMD_REMOVE_NAVPOINT);
	tmpButton.setBounds(320,30,50,30);
	
	this.navpointPanel.add(tmpButton);
	
	this.add(this.navpointPanel);
}




	

private void addPositionEditor()
{
	JPanel positionPanel;
	JLabel description;
	
	
	

	positionPanel = new JPanel();
	positionPanel.setLayout(null);
	positionPanel.setBorder( new TitledBorder(NavigationPathManager.POSITION_TEXT));
//	positionPanel.setSize(new Dimension(200,200));
//	positionPanel.setPreferredSize(new Dimension(150,300));
	positionPanel.setBounds(10,200,150,150);
	
	Insets insets =positionPanel.getBorder().getBorderInsets(positionPanel);
	
	description = new JLabel ("x");
	description.setBounds(insets.left+5,insets.top+5,20,25);
	positionPanel.add(description);
	
	this.xPosition = new JTextField();
	this.xPosition.setBounds(insets.left+30,insets.top+5,60,25);
	positionPanel.add(this.xPosition);
	
	description = new JLabel ("y");
	description.setBounds(insets.left+5,insets.top+35,20,25);
	positionPanel.add(description);
	
	this.yPosition = new JTextField();
	this.yPosition.setBounds(insets.left+30,insets.top+35,60,25);
	positionPanel.add(this.yPosition);
	
	
	description = new JLabel ("z");
	description.setBounds(insets.left+5,insets.top+65,20,20);
	positionPanel.add(description);
	
	this.zPosition = new JTextField();
	this.zPosition.setBounds(insets.left+30,insets.top+65,60,25);
	positionPanel.add(this.zPosition);
		
	
	this.add(positionPanel,BorderLayout.LINE_START);
}

	
/**
 * add default action editor provider for navigation points to select combo box . Override this method in your robot implementation to add specific NavPointCommands! 
 */
protected void addActions()
{
	
	for (DefaultActionsEditors editor: DefaultActionsEditors.values())
	{
		this.actionSelector.addItem(editor.getEditorProvider());
	}
	
}



private void addActionEditor()
{
	

	JLabel description;
	

	this.actionPanel = new JPanel();
//	this.actionPanel.setLayout(null);
	
	
	this.actionPanel.setBorder( new TitledBorder(NavigationPathManager.ACTION_TEXT));
//	actionPanel.setSize(new Dimension(200,200));
	this.actionPanel.setPreferredSize(new Dimension(220,220));
	this.actionPanel.setBounds(160,200,220,160);
	
	//JLabel description;
	
//	Insets insets =this.actionPanel.getBorder().getBorderInsets(this.actionPanel);
	
	 description = new JLabel(NavigationPathManager.ACTION_TEXT);
	 
	 this.actionPanel.add(description, BorderLayout.NORTH);
	
	 //(this.actions.toArray(new NavPointAction[0]));
//	this.actionComboBox.setBounds(insets.left+5,insets.top+5,100,25);
		
	this.actionSelector = new NavigationActionSelector();
	this.actionSelector.setPreferredSize(new Dimension (200,30)); 
	this.actionSelector.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			NavigationPathManager.this.displayAction();
			
		}
		
	});
	this.actionSelector.addActionListener(this);
	this.actionSelector.setActionCommand(NavigationPathManager.CMD_ACTION_CHANGED);

	
	this.actionPanel.add(this.actionSelector, BorderLayout.LINE_END);
	
	
	
	
	this.add(this.actionPanel,BorderLayout.CENTER);
	
	this.actionSettingsPanel = new JPanel();
//	this.actionSettingsPanel.setBorder(new LineBorder(Color.BLACK,4));
	//this.actionSettingsPanel.setPreferredSize(new Dimension (400,400)); 
	//.setBounds(5,100,100,100);
	this.actionPanel.add(this.actionSettingsPanel);
	
	this.addActions();
	
//	this.makePausePanel();
	
}



/**
 * display actual action of this navigation point
 */
protected void displayAction()
{
	System.out.println("DisplayActionn");
	this.actionSettingsPanel.removeAll();
	
	ActionEditorProvider provider = (ActionEditorProvider) this.actionSelector.getSelectedItem();
	System.out.println(provider.action.toString());
	
	this.actionSettingsPanel.add(provider.actionEditor);
	provider.actionEditor.displayAction(provider.action);
	
	this.actionSettingsPanel.repaint();
	
	
}



/**
 * display action editor to a given action
 * @param action action to be displayed in 
 */
protected void displayAction(NavPointAction action)
{
	System.out.println("DisplayAction NavPointAction");
	this.actionSettingsPanel.removeAll();
	
	
	ActionEditorProvider provider = (ActionEditorProvider) this.actionSelector.getActionEditor(action);
	
	
	this.actionSettingsPanel.add(provider.actionEditor);
	provider.actionEditor.displayAction(action);
	


	
	this.actionSettingsPanel.repaint();
}





public void setPathList(ArrayList<NavigationPath> paths)
{
	this.paths = paths;
}





@Override
public void actionPerformed(ActionEvent actionEvent)
{
	String cmd;
	
	
	cmd = actionEvent.getActionCommand();
	
 if (cmd.equals(NavigationPathManager.CMD_ADD_PATH))
	{
		NavigationPath navPath;
		navPath = new NavigationPath();
		navPath.setName("new");
		this.paths.add(navPath);
		this.pathList.addItem(navPath);
	}
	else if (cmd.equals(NavigationPathManager.CMD_PATH_SELECTED))
			
	{

		this.activePath = (NavigationPath)this.pathList.getSelectedItem();
		this.pathName.setText(activePath.getName());
		this.navpointList.setPath(this.activePath); 
		
	}
	else if (cmd.equals(NavigationPathManager.CMD_PATH_RENAMED))
	{
		if (activePath!=null)
		{
			activePath.setName(this.pathName.getText());	
		}
		
		
	}
	else if (cmd.equals(NavigationPathManager.CMD_NAVPOINT_SELECTED))
	{
		if (activePath!=null)
		{
			NavigationPoint navPoint;
	
			navPoint = this.navpointList.getSelected();
			 if (navPoint!= null)
			 {
				 this.navpointName.setText(navPoint.getName());
				 this.positionPanel.setNavPoint(navPoint);
				 navPoint.select();
			 }
			 else
			 {
				 this.navpointName.setText("");
				 this.positionPanel.clear();
				 
			 }
		}
	}

	else if (cmd.equals(NavigationPathManager.CMD_ADD_NAVPOINT))
	{
		if (activePath!=null)
		{
			this.activePath.addNavPoint(new FloatVector3D(0,0,0));
		}
	}
	else if (cmd.equals(NavigationPathManager.CMD_NAVPOINT_RENAMED))
	{
		if (activePath!=null)
		{
			NavigationPoint navPoint;
			
			navPoint = this.navpointList.getSelected();
			
			if (navPoint!=null)
			{
				navPoint.setName(this.navpointName.getText());				
			}

		}
		else if (cmd.equals(NavigationPathManager.REMOVE_TEXT))
		{
			if (activePath!=null)
			{
				this.activePath.remove(navpointList.getSelectedIndex());
			}
		}
		
	}
	else if(cmd.equals(NavigationPathManager.CMD_ACTION_CHANGED))
	{
		if (activePath!=null)
		{
			NavigationPoint navPoint;
	
			navPoint = this.navpointList.getSelected();
			 if (navPoint!= null)
			 {
				 navPoint.setAction(this.actionSelector.getNavPointAction());
				 this.positionPanel.setNavPoint(navPoint);
				 navPoint.select();
			 }
			 else
			 {
				 System.out.println("navPoint is null");
				 //navPoint.setAction(new NavPointActionNext());
				 
			 }
		}
	}

}





	


}
