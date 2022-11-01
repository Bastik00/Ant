package de.hska.lat.robot.component.ircom.view;

import java.util.Date;

import de.hska.lat.comm.packetLogger.LoggedDataPacket;
import de.hska.lat.comm.packetLogger.LoggedDataPacket.DisplayDataWidth_e;


public class IrComDataPackage {
	enum TYPES {RC5, DATA};
	
	private LoggedDataPacket dataPackage;
	private int number;
	private TYPES type;
	
	/**
	 * Creates an empty instance of an IrComDataPackage.
	 */

	public IrComDataPackage(LoggedDataPacket dataPackage, TYPES type, int number) {
		this.dataPackage = dataPackage;
		this.type = type;
		this.number = number;
	}
	
	public int getSource() {
		return dataPackage.getSource();
	}
	
	public int getCommand() {
		return dataPackage.getCommand();
	}
	
	public String getCommandName() {
		if (dataPackage.getCommandName() == null) {
			return "undefined";
		}
		
		return dataPackage.getCommandName();
	}
	
	public String getTypeName() {
		if (dataPackage.getTypeName() == null) {
			return "undefined";
		}
		
		return type == TYPES.RC5 ? "RC5" : "DATA";
	}
	
	public int getNumber() {
		return number;
	}
	
	public String getDataAsString() {
		String data = "";
		
		/*if (dataPackage.getDataAsString() != 0) {
			
			for (int i = 0; i < dataPackage.getDataSize(); i++) {
				if (i != 0) {
					data += ",";
					data += dataPackage.getByte(i);
				}
			}
		}*/
		
		
		
		if (type == TYPES.RC5) {
			int test = Integer.parseInt(dataPackage.getDataAsString(DisplayDataWidth_e.WIDTH_16, false));
			int adress = (test & 0x1F00)>>8;
			int toogleBit = (test & 0x2000) >> 13;
			int cmd = (test & 0x00FC) >> 2;
			data = "Adress: " + adress + ",ToggleBit: " + toogleBit + ", Command: " + cmd;
		} else {
			data = dataPackage.getDataAsString(DisplayDataWidth_e.WIDTH_16, true);
		}
		
		//return dataPackage.getDataAsString(DisplayDataWidth_e.WIDTH_16, true);
		return data;
		//return dataPackage.getDataAsString();
	}
	
	public Date getTimestamp() {
		return dataPackage.getTimestamp();
	}

}
