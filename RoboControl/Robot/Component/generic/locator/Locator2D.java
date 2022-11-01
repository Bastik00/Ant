package de.hska.lat.robot.component.generic.locator;

import java.util.HashSet;
import java.util.Set;

import de.hska.lat.comm.remote.RemoteStream;
import de.hska.lat.math.vector.FloatVector2D;
import de.hska.lat.math.vector.FloatVector3D;
import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.generic.locator.protocol.Cmd_setLocation2D;
import de.hska.lat.robot.component.generic.locator.protocol.Stream_location2D;
import de.hska.lat.robot.value.FloatValue;

public class Locator2D
		extends
		Locator<LocatorChangeNotifier, ComponentSettingsChangeNotifier, Locator2DProtocol>
{

	public static final int X_AXIS = 0;
	public static final int Y_AXIS = 1;

	protected FloatValue[] location = new FloatValue[2];
	private Set<LocationUpdateListener> locationListeners = new HashSet<LocationUpdateListener>();

	public Locator2D(ComponentMetaData metaData, Locator2DProtocol protocol)
	{
		super(metaData, protocol);

		this.location[Locator2D.X_AXIS] = new FloatValue("x");
		this.location[Locator2D.Y_AXIS] = new FloatValue("y");
	}

	public FloatVector2D getLocation()
	{
		FloatVector2D location;

		location = new FloatVector2D(
				this.location[Locator2D.X_AXIS].getValue(),
				this.location[Locator2D.Y_AXIS].getValue());

		return (location);
	}

	public void setLocation(FloatVector2D location)
	{

		this.location[Locator2D.X_AXIS].setValue(location.x);
		this.location[Locator2D.Y_AXIS].setValue(location.y);

		FloatVector3D location3D;

		location3D = new FloatVector3D(location.x, 0, location.y);

		for (LocatorChangeNotifier listener : sensorListener)
		{
			listener.locationChanged(location3D);
		}
	}

	public void processLocation(Stream_location2D remoteData)
	{
		this.setLocation(remoteData.getLocation());
		this.updateListeners(getLocation());
	}

	@Override
	public boolean decodeStream(RemoteStream remoteStreamData)
	{

		if (remoteStreamData instanceof Stream_location2D)
		{
			this.processLocation((Stream_location2D) remoteStreamData);

		} else
		{
			return (false);
		}

		return (true);

	}

	public boolean remote_setActualLocation(FloatVector2D location)
	{

		if (this.componentProtocol == null)
			return (false);

		return (sendData(Cmd_setLocation2D.getCommand(
				this.componentProtocol.cmdSetLocationId, location)));
	}

	public void registerLocationListener(LocationUpdateListener listener)
	{
		this.locationListeners.add(listener);
	}

	public void unregisterLocationListener(LocationUpdateListener listener)
	{
		this.locationListeners.remove(listener);
	}

	protected void updateListeners(FloatVector2D coordinates)
	{
		for (LocationUpdateListener listener : locationListeners)
		{
			listener.locationUpdate(coordinates);
		}
	}

	public interface LocationUpdateListener
	{
		public void locationUpdate(FloatVector2D position);
	}

}
