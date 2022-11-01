package de.hska.lat.robot.display.generics.map2D;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ButtonGroup;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;

import de.hska.lat.navigation.path.NavigationPath;
import de.hska.lat.robot.Robot;
import de.hska.lat.robot.display.generics.map.navigationPath.NavigationPathDrawer;
import de.hska.lat.robot.display.generics.map2D.destination.DestinationDrawer;
import de.hska.lat.robot.display.generics.map2D.displayData.DisplayData;
import de.hska.lat.robot.display.generics.map2D.fieldOfView.FieldOfViewDrawer;
import de.hska.lat.robot.display.generics.map2D.gridMap.GridMap2DDrawer;
import de.hska.lat.robot.display.generics.map2D.metaData.MapDisplayMetaData;
import de.hska.lat.robot.display.generics.map2D.origin.MapOriginDrawer;
import de.hska.lat.robot.display.generics.map2D.pose.PoseDrawer;

public class MapView extends JPanel implements MouseMotionListener,
		MouseListener, ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5781080143930657188L;

	protected Robot robot;
	protected PoseDrawer poseDrawer;
	protected DestinationDrawer destinationDrawer;
	protected MapOriginDrawer originDrawer;
	protected NavigationPathDrawer navigationPathDrawer;

	protected ArrayList<FieldOfViewDrawer> fieldOfViewDrawers;

	protected DisplayData displayData;

	protected MapEventListener eventListener;

	protected GridMap2DDrawer gridMapDrawer;

	protected int mouseOriginX;
	protected int mouseOriginY;

	private static final String CMD_ADD_NAVPOINTT = "modeAddNavpoint";
	private static final String CMD_REMOVE_NAVPOINT = "modeRemoveNavpoint";
	private static final String CMD_MOVE_NAVPOINT = "modeMoveNavpoint";

	private static final String CMD_MOVE_MAP = "modeMoveMap";

	private static final String ADD_NAVPOINT_TEXT = "add navpoint";
	private static final String REMOVE_NAVPOINT_TEXT = "remove navpoint";
	private static final String MOVE_NAVPOINT_TEXT = "move navpoint";

	private static final String MOVE_MAP_TEXT = "move map";


	protected JPopupMenu contextMenue;

	public MapView(Robot robot, MapDisplayMetaData metaData)
	{
		// super(context, true);
		this.robot = robot;
		this.originDrawer = metaData.getOriginDrawer();
		this.poseDrawer = metaData.getPoseDrawer();
		this.destinationDrawer = metaData.getDestinationDrawer();
		this.fieldOfViewDrawers = metaData.getFieldOfViewDrawers();
		this.displayData = metaData.getDisplayData();
		this.navigationPathDrawer = metaData.getNavigationPathDrawer();
		this.setBackground(Color.BLACK);

		this.addMouseMotionListener(this);
		this.addMouseListener(this);

		this.makePopupMenu();

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new RemindTask(), 0, 100);
	}

	/*
	 * private static final String CMD_ADD_NAVPOINTT = "modeAddNavpoint";
	 * private static final String CMD_REMOVE_NAVPOINT = "modeRemoveNavpoint";
	 * private static final String CMD_MOVE_NAVPOINT = "modeMoveNavpoint";
	 * 
	 * private static final String CMD_MOVE_MAP = "modeMoveMap";
	 * 
	 * 
	 * 
	 * private static final String ADD_NAVPOINT_TEXT = "add navpoint"; private
	 * static final String REMOVE_NAVPOINT_TEXT = "remove navpoint"; private
	 * static final String MOVE_NAVPOINT_TEXT = "move navpoint";
	 * 
	 * private static final String MOVE_MAP_TEXT = "move map";
	 */
	protected void makePopupMenu()
	{
		this.contextMenue = new JPopupMenu();
		MouseListener popupListener = new PopupListener();

		JMenuItem tmpItem;

		ButtonGroup group = new ButtonGroup();

		tmpItem = this.addRadioButtonMenuItem(this.contextMenue, MapView.ADD_NAVPOINT_TEXT, MapView.CMD_ADD_NAVPOINTT);
		group.add(tmpItem);

		tmpItem = this.addRadioButtonMenuItem(this.contextMenue, MapView.REMOVE_NAVPOINT_TEXT, MapView.CMD_REMOVE_NAVPOINT);
		group.add(tmpItem);

		tmpItem = this.addRadioButtonMenuItem(this.contextMenue, MapView.MOVE_NAVPOINT_TEXT, MapView.CMD_MOVE_NAVPOINT);
		group.add(tmpItem);
		this.contextMenue.add(new JSeparator());

		tmpItem = this.addRadioButtonMenuItem(this.contextMenue, MapView.MOVE_MAP_TEXT, MapView.CMD_MOVE_MAP);
		group.add(tmpItem);

		this.addMouseListener(popupListener);
	}

	protected JMenuItem addRadioButtonMenuItem(JPopupMenu popupMenu,
			String text, String command)
	{

		JMenuItem menuItem = new JRadioButtonMenuItem(text);
		menuItem.addActionListener(this);
		menuItem.setActionCommand(command);
		popupMenu.add(menuItem);

		return (menuItem);
	}

	public void setPath(NavigationPath path)
	{
		this.navigationPathDrawer.setPath(path);
	}

	public void setEventListener(MapEventListener eventListener)
	{
		this.eventListener = eventListener;

	}

	public MapOriginDrawer getOriginDrawer()
	{
		return (this.originDrawer);
	}

	public PoseDrawer getPoseDrawer()
	{
		return (this.poseDrawer);
	}

	@Override
	public void update(Graphics graphics)
	{
		this.draw(graphics);

	}

	public void paint(Graphics graphics)
	{

		this.draw(graphics);
	}

	protected void drawGrid(Graphics graphics)
	{
		int xCounter;
		int yCounter;
		int start;
		int width;
		int height;

		graphics.setColor(Color.DARK_GRAY);

		width = this.getWidth();
		height = this.getHeight();

		start = ((width / 2) - ((width / 100) * 50));

		if (start < 10)
			start += 50;

		for (xCounter = start; xCounter < width - 10; xCounter += 50)
		{
			graphics.drawLine(xCounter, 5, xCounter, this.getHeight() - 10); // ,
																				// paint);
		}

		start = ((height / 2) - ((height / 100) * 50));

		if (start < 10)
			start += 50;

		start += 2;

		for (yCounter = start; yCounter < height - 10; yCounter += 50)
		{
			graphics.drawLine(5, yCounter, this.getWidth() - 10, yCounter);// ,
																			// paint);
		}

	}

	protected void draw(Graphics g)
	{

		Graphics2D graphics;
		graphics = (Graphics2D) g;

		/*
		 * BufferStrategy bs = this.getBufferStrategy(); if (bs==null) {
		 * this.createBufferStrategy(2); bs = this.getBufferStrategy(); }
		 */
		// graphics = bs.getDrawGraphics(); // acquire the graphics

		super.paint(graphics);

		this.drawGrid(graphics);

		// draw Fields of View

		for (FieldOfViewDrawer drawer : this.fieldOfViewDrawers)
		{
			if (drawer.isOn())
			{
				drawer.draw(graphics);
			}
		}

		if (this.gridMapDrawer != null)
		{
			this.gridMapDrawer.draw(graphics);
		}

		if (this.originDrawer != null)
		{
			this.originDrawer.draw(graphics);
		}

		if (this.poseDrawer != null)
		{
			this.poseDrawer.draw(graphics);
		}

		// if active
		// this.destinationDrawer.draw(graphics);

		if (this.navigationPathDrawer != null)
			this.navigationPathDrawer.draw(graphics);

		// if sennsors -> draw Sensor

		// if Map -> Draw Map

		// if FieldOf View draw Field Of Views

		// if Pose -> draw Pose

		// if Path -> draw Path

		// bs.show();
	}

	class RemindTask extends TimerTask
	{

		public void run()
		{

			MapView.this.invalidate();
			MapView.this.repaint();
		}
	}

	@Override
	public void mouseDragged(MouseEvent mouseEvent)
	{
		int x;
		int y;

		x = mouseEvent.getX() - this.mouseOriginX;
		y = mouseEvent.getY() - this.mouseOriginY;

		this.mouseOriginX = mouseEvent.getX();
		this.mouseOriginY = mouseEvent.getY();

		this.displayData.moveDisplayOffset(x, y);

	}

	@Override
	public void mouseMoved(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent mouseEvent)
	{

		this.eventListener.mapClicked(mouseEvent.getX(), mouseEvent.getY());

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
		this.mouseOriginX = mouseEvent.getX();
		this.mouseOriginY = mouseEvent.getY();

	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

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
				contextMenue.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	public void setOriginDrawer(MapOriginDrawer originDrawer)
	{
		this.originDrawer = originDrawer;

	}

	public void setPoseDrawer(PoseDrawer poseDrawer)
	{
		this.poseDrawer = poseDrawer;
	}

	public void setGridMapDrawer(GridMap2DDrawer gridMapDrawer)
	{
		this.gridMapDrawer = gridMapDrawer;

	}

}
