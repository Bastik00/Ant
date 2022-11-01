package de.hska.lat.robot.component.generic.compass.view;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import de.hska.lat.math.vector.FloatVector3D;
import de.hska.lat.robot.component.generic.compass.Compass;
import de.hska.lat.robot.component.generic.compass.CompassChangeNotifier;
import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.component.view.MissingComponentView;
import de.hska.lat.robot.component.view.SensorDataView;
import de.hska.lat.robot.value.FloatValue;

public class CompassDataView extends SensorDataView<Compass<?,?>> implements CompassChangeNotifier
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1261654250744113651L;
	
	protected static final int width = 100;
	protected static final int height = 80;
	
	private JLabel [] angle = new JLabel[3];


	public CompassDataView(Compass<?,?> compass)
	{
		super(compass);
		
		compass.addSensorListener(this);
		
		buildView();
	}

	@Override
	protected void buildView()
	{
		
		super.buildView();
	
		JLabel tmpLabel;
		
		Insets insets = this.getBorder().getBorderInsets(this);
		
		tmpLabel= new JLabel("x");
		tmpLabel.setBounds(insets.left+5, insets.top+5, 40, 20);
		this.add(tmpLabel);

		this.angle[Compass.X_AXIS] = new JLabel("");
		this.angle[Compass.X_AXIS] .setBounds(insets.left+35, insets.top+5, 60, 20);
		this.angle[Compass.X_AXIS] .setBorder(new LineBorder(Color.BLACK));
		this.add(this.angle[Compass.X_AXIS] );
	
		
		tmpLabel= new JLabel("y");
		tmpLabel.setBounds(insets.left+5, insets.top+30, 50, 20);
		this.add(tmpLabel);
		
		this.angle[Compass.Y_AXIS]= new JLabel("");
		this.angle[Compass.Y_AXIS].setBounds(insets.left+35, insets.top+30, 60, 20);
		this.angle[Compass.Y_AXIS].setBorder(new LineBorder(Color.BLACK));
		this.add(this.angle[Compass.Y_AXIS]);

		
		tmpLabel= new JLabel("z");
		tmpLabel.setBounds(insets.left+5, insets.top+55, 50, 20);
		this.add(tmpLabel);

		
		this.angle[Compass.Z_AXIS] = new JLabel("");
		this.angle[Compass.Z_AXIS].setBounds(insets.left+35, insets.top+55, 60, 20);
		this.angle[Compass.Z_AXIS].setBorder(new LineBorder(Color.BLACK));
		this.add(this.angle[Compass.Z_AXIS]);
	}

	
	
	@Override
	public void rateChanged(Compass<?,?> compass)
	{

		
		FloatVector3D magneticFieldValues = compass.getGeomagneticField();
		
		String 	unit = " µT";
		int fraction = 3;
	
			
		this.angle[Compass.X_AXIS].setText(FloatValue.toFormatedFractionString(magneticFieldValues.x,fraction)+unit);
		this.angle[Compass.Y_AXIS].setText(FloatValue.toFormatedFractionString(magneticFieldValues.y,fraction)+unit);
		this.angle[Compass.Z_AXIS].setText(FloatValue.toFormatedFractionString(magneticFieldValues.z,fraction)+unit);
	
			
	}
	
	@Override
	protected int getViewWidth()
	{
		return(CompassDataView.width);
	}

	@Override
	protected int getViewHeight()
	{
		return(CompassDataView.height);
	}
	
		
	public static ComponentView createView(Compass<?,?> sensor)
	{
		 
		if (sensor!=null)
		{
			return(new CompassDataView(sensor));
		}
		else
		{
			return(new MissingComponentView(Compass.class.getName()));
		}
	}

}
