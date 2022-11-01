package de.hska.lat.robot.component.ircom.view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import de.hska.lat.comm.packetLogger.DataPacketLogerEvent;
import de.hska.lat.comm.packetLogger.DataPacketLogger;
import de.hska.lat.comm.packetLogger.LoggedDataPacket;
import de.hska.lat.robot.component.ircom.view.IrComDataPackage.TYPES;

public class IrComPackageLogger extends LinkedList<IrComDataPackage> implements DataPacketLogerEvent{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -1715926704835884859L;
	private int maxSize;
	private int packageCount;
	
	private DataPacketLogger systemLogger;
	
	private List<DataPacketLogerEvent> listeners = new ArrayList<DataPacketLogerEvent>();
	
	public IrComPackageLogger(DataPacketLogger systemLogger) {
		this.maxSize = 200;
		this.packageCount = 0;
		this.systemLogger = systemLogger;
		this.systemLogger.addListener(this);
	}
	
	public void setMaxSize(final int maxSize) {
		if (maxSize < 1) {
			this.maxSize = 1;
		} else {
			this.maxSize = maxSize;
		}
		
		if (this.size() > this.maxSize) {
			int deletMax = this.size() - this.maxSize;
			this.removeRange(0, deletMax-1);
		}
	}
	
	/**
	 * TODO
	 */
	public void resetPackageCount() {
		this.packageCount = 0;
	}
	
	public int getPackageCount() {
		return this.packageCount;
	}
	
	/**
	 * TODO
	 * @param dataPacket
	 * @return
	 */
	public boolean addPackage(LoggedDataPacket dataPackage) {
		packageCount++;
		
		if (this.size() >= maxSize) {
			this.removeFirst();
		}
		
		boolean changed = this.add(new IrComDataPackage(dataPackage, TYPES.RC5, packageCount));
		
		if (changed) {
			listChanged();
		}
		
		return changed;
	}
	
	private void listChanged() {
		for (DataPacketLogerEvent listener: listeners) {
			listener.loggerChange();
		}
	}
	
	/**
	 * TODO
	 * @param listener
	 */
	public void addListener(DataPacketLogerEvent listener) {
		listeners.add(listener); // adds a new listener to the list
	}
	
	public void loggerChange() {
		// make transformation from systemdatenlogger to IrComPackagelogger
		if (systemLogger != null && systemLogger.size() != 0) {
			LoggedDataPacket systemData = systemLogger.getLast();
			if (systemData.getSource() == 14) {
				addPackage(systemData);
			}
		}
	}
	

}
