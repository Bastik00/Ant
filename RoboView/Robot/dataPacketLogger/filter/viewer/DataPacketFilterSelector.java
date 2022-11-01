package de.hska.lat.robot.dataPacketLogger.filter.viewer;

import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JComboBox;

import de.hska.lat.comm.packetLoger.filter.DataPacketFilter;
import de.hska.lat.comm.packetLoger.filter.DataPacketFilterList;

public class DataPacketFilterSelector extends JComboBox<DataPacketFilter> implements ItemListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2762331358636333462L;

	
	protected DataPacketFilterList filters;
	
	
	protected ArrayList<FilterSelectorNotifier> selectorListeners = new ArrayList<FilterSelectorNotifier>();
	
	
	
public DataPacketFilterSelector(DataPacketFilterList filters)
{
	this.filters = filters;
	
	setPreferredSize(new Dimension( 200,30));
	this.addItemListener(this);
}


public void firePopupMenuWillBecomeVisible()
{
	this.removeAllItems();
	
	if (this.filters==null)
	{
		//this.addItem("- ");
	}
	else
	{
		for( DataPacketFilter filter : this.filters)
		{
			this.addItem(filter);
		}
	}


}



/**
 * Add a listener for filter selection. This listener sends a notification when a Filter has been selected  
 * @param listener selection listener to be add to the notification list
 */
public void addSelectorListener(FilterSelectorNotifier listener)
{
	selectorListeners.add(listener);
}

/**
 * remove a selection listener
 * @param listener selection listener to be removed from the notification list 
 */

public void removeSelectorListener(FilterSelectorNotifier listener)
{
	selectorListeners.remove(listener);
}



@Override
public void itemStateChanged(ItemEvent event)
{
	if (event.getStateChange() == ItemEvent.SELECTED) 
	{
		DataPacketFilter filter = (DataPacketFilter) event.getItem();
		
		for (FilterSelectorNotifier notifier : selectorListeners)
		{
			notifier.filterSelected(filter);
		}
		
	}
	        

	
}


}
