package de.hska.lat.robot.dataGraph.valueGraph;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JSeparator;

public class ValueGraphSeparator extends JSeparator implements MouseListener, MouseMotionListener
{

	protected List<ValueGraphResizeListener> listeners;
	/**
	 * 
	 */
	private static final long serialVersionUID = -279172647610467926L;
	
	protected int cursor;
	protected Cursor resizeXCursor;
	protected Cursor resizeYCursor;
	protected Cursor defaultCursor;
	
	protected int movementXOrigin;
	protected int movementYOrigin;
	protected boolean resize;

	
	
	public ValueGraphSeparator()
	{
		super();
		init();
	}
	
	

	public ValueGraphSeparator(int orientation)
	{
		super(orientation);
		init();
		
		if (orientation == JSeparator.VERTICAL) {
			System.out.println("TODO - Vertical Implementation of ValueGraphSeparator");
		}
	}

	protected void init() {
		this.resizeXCursor = new Cursor(Cursor.E_RESIZE_CURSOR);
		this.resizeYCursor = new Cursor(Cursor.N_RESIZE_CURSOR);
		this.defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		
		this.movementYOrigin = 0;
		this.resize = false;
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.listeners = new ArrayList<ValueGraphResizeListener>();
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		//
		
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		this.movementYOrigin = e.getY();
		this.resize = true;
		
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		this.resize = false;
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		if (this.getOrientation() == VERTICAL) {
			this.cursor = Cursor.N_RESIZE_CURSOR;
			this.setCursor(resizeXCursor);
		}
		else if (this.getOrientation() == HORIZONTAL) {
			this.cursor = Cursor.N_RESIZE_CURSOR;
			this.setCursor(resizeYCursor);
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		this.cursor = Cursor.DEFAULT_CURSOR;
		this.setCursor(defaultCursor);
	}

	public void addResizeListener(ValueGraphResizeListener listener) {
		listeners.add(listener);
	}

	public void removeResizeListener(ValueGraphResizeListener listener) {
		listeners.remove(listener);
	}



	@Override
	public void mouseDragged(MouseEvent e)
	{
		int y = e.getY();
		int deltaY = y - this.movementYOrigin;
		
		this.notifyHeightChanged(deltaY);
	}



	private void notifyHeightChanged(int deltaY)
	{
		for (ValueGraphResizeListener listener : this.listeners) {
			listener.heightChanged(deltaY);
		}
		
	}

	
	private void notifyWidthChanged(int deltaX)
	{
		for (ValueGraphResizeListener listener : this.listeners) {
			listener.widthChanged(deltaX);
		}
		
	}


	@Override
	public void mouseMoved(MouseEvent e)
	{
		int x = e.getX();
		int deltaX = x - this.movementXOrigin;
		
		this.notifyWidthChanged(deltaX);
		
	}
}
