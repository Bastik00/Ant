package de.hska.lat.robot.morphology.model.viewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;

import de.hska.lat.robot.Robot;
import de.hska.lat.robot.abstractRobot.AbstractRobot;
import de.hska.lat.robot.displayFrame.DisplayFrame;
import de.hska.lat.robot.morphology.model.Bone;
import de.hska.lat.robot.morphology.model.MorphologicModel;
import de.hska.lat.robot.morphology.model.SkeletalOrgan;


public class MorphologicModelViewer extends DisplayFrame implements ActionListener
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5430956239991059952L;
	
	protected static final String NAME_TEXT = "Morphological view : ";

	protected static final String ADD_TEXT	= "add";
	protected static final String ADD_ALL_TEXT = "add all";
	protected static final String REFRESH_RATE_TXT = "refresh rate";
	
	protected static final String cmdAdd = "cmdAdd";
	protected static final String cmdAddAll = "cmdAddAll";
	
	protected MorphologicModel morphologicModel;
	
	
	protected JToolBar toolbar;
		
	protected JButton addButton;
	protected JButton addAllButton;
	
	
	
	protected JTable table; 
	protected JScrollPane scrollPane;
	protected MorphologicTableModel tableModel;
	
	private Timer sheduler;
	
public MorphologicModelViewer()
{
	super(MorphologicModelViewer.NAME_TEXT, true, true, false, false);

	
	this.buildView();
	
	
	this.sheduler = new Timer();
	this.sheduler.scheduleAtFixedRate(new MorphologicViewRefreshTask(), 100, 10);
	
	
}



protected void buildView()
{
	this.setLayout(new  BorderLayout());
	
	this.makeToolbar();
	
	
	this.scrollPane=new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	this.scrollPane.setViewportBorder(BorderFactory.createLineBorder(Color.black));
	
	this.tableModel = new MorphologicTableModel();
	this.table =new JTable(this.tableModel);


	this.scrollPane.setViewportView(table);	
	
	this.add(this.scrollPane);	
}

protected void makeToolbar()
{
	JLabel tmpLabel;
	
	toolbar = new JToolBar();
	this.add(this.toolbar,BorderLayout.PAGE_START);
	
	this.addButton = new JButton(MorphologicModelViewer.ADD_TEXT);
	this.addButton.addActionListener(this);
	this.addButton.setActionCommand(MorphologicModelViewer.cmdAdd);
	toolbar.add(this.addButton);
	
	
	this.addAllButton = new JButton(MorphologicModelViewer.ADD_ALL_TEXT);
	this.addAllButton.addActionListener(this);
	this.addAllButton.setActionCommand(MorphologicModelViewer.cmdAddAll);	
	toolbar.add(this.addAllButton);

	
	
	tmpLabel = new JLabel(MorphologicModelViewer.REFRESH_RATE_TXT);
	toolbar.add(tmpLabel);
	
	
	
}




protected void add()
{
	
}




protected void addAll()
{
	ArrayList<Bone> bones = new ArrayList<Bone>(); 
	ArrayList<SkeletalOrgan> skeleton;
	
	skeleton = this.morphologicModel.getOrgans();

	
	for (SkeletalOrgan organ : skeleton)
	{
		bones.addAll(organ.getBones());
	}

	this.tableModel.set(bones);
}



@Override
public boolean setRobot(AbstractRobot<?,?,?> robot)
{

	this.setTitle(MorphologicModelViewer.NAME_TEXT + robot.getName());
	
	this.morphologicModel = ((Robot) robot).getMorphologicModel();
	
	
	show();
	return(true);
}



@Override
public void actionPerformed(ActionEvent actionEvent)
{
	
	String cmd;
	
	cmd = actionEvent.getActionCommand();
	
	if (cmd.equals(MorphologicModelViewer.cmdAdd))
	{
		this.add();
	}
	else if (cmd.equals(MorphologicModelViewer.cmdAddAll))
	{
		this.addAll();
	}
	
	// TODO Auto-generated method stub
	
}


class MorphologicViewRefreshTask extends TimerTask
{


	
public void run() 
{	
	MorphologicModelViewer.this.table.revalidate();
	MorphologicModelViewer.this.table.repaint();
}
	
}	





}
