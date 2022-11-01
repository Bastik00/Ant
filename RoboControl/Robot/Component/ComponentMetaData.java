package de.hska.lat.robot.component;

public class ComponentMetaData
{

	private String name;
	private String metaName;
	private int globalId;
	private int localId;
	private int deviceId;
	protected ComponentProtocol componentProtocol;
	
	
public ComponentMetaData(String name,String metaName, int globalId, int localId, int deviceId)
{
	this.name = name;
	this.metaName = metaName;
	this.globalId = globalId;
	this.localId = localId;
	this.componentProtocol = null;
	this.deviceId = deviceId;
	
}


public ComponentMetaData(ComponentMetaData metaData)
{
	this.name = metaData.getName();
	this.metaName = metaData.getMetaName();
	this.globalId = metaData.getGlobalId();
	this.localId = metaData.getLocalId();
	this.componentProtocol = metaData.getProtocol();
	this.deviceId = metaData.getDeviceId();
	
}


public void setProtocol(ComponentProtocol componentProtocol)
{
	this.componentProtocol = componentProtocol; 
}


public int getLocalId()
{
	return(this.localId);
}

public int getGlobalId()
{
	return(this.globalId);
}


public int getDeviceId()
{
	return(this.deviceId);
}

public String getName()
{
	return(this.name);
}


public String getMetaName()
{
	return(metaName);
}


public ComponentProtocol getProtocol()
{

	return this.componentProtocol;
}

}
