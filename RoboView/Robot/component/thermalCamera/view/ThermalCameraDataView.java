package de.hska.lat.robot.component.thermalCamera.view;

import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;




import de.hska.lat.robot.component.thermalCamera.ThermalCamera;
import de.hska.lat.robot.component.thermalCamera.ThermalCameraStreamListener;
import de.hska.lat.robot.component.thermalCamera.ThermalFrame;
import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.component.view.MissingComponentView;

public class ThermalCameraDataView extends ComponentView implements ActionListener, ThermalCameraStreamListener
{

	

	
	protected static final int width = 200;
	protected static final int height = 290;
	
	
	/*************** old **********************/
	
	
	protected ThermalPanel thermalPanel;
	
	protected ThermalPanelControl controlPanel;
	protected ThermalDataInfo dataInfo;
	
	
	protected static final String KELVIN_TEXT = "display as kelvin";
	protected static final String CELSIUS_TEXT = "display as celcius";
	
	protected JMenuItem showCelsiusItem;
	protected JMenuItem showKelvinItem;

	

protected ThermalCameraDataView(ThermalCamera camera)
{
	super(camera.getComponentName(), false);

	camera.addStreamListener(this);

	buildView();

	
}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2846883976631541455L;

	@Override
	protected void buildView() 
	{
		
		super.buildView();
		
		this.thermalPanel = new ThermalPanel();
		this.thermalPanel.setBounds(5, 5, 150, 150);
		this.add(this.thermalPanel);	
		
		
		
		this.controlPanel = new ThermalPanelControl();
		this.controlPanel.setBounds(5,160,200,70);
		this.add(this.controlPanel);
		
		this.dataInfo = new ThermalDataInfo();
		this.dataInfo.setBounds(5,230,200,70);
		this.add(this.dataInfo);
		

	}

	
	
@Override
protected int getViewWidth()
{
	return(ThermalCameraDataView.width);
}

@Override
protected int getViewHeight()
{
	return(ThermalCameraDataView.height);
}



@Override
protected void makePopupMenu()
{
	super.makePopupMenu();
	
	this.contextMenue.add(new JSeparator());
	
	 ButtonGroup group = new ButtonGroup();
 
	 
	 
	 this.showKelvinItem = this.addRadioButtonMenuItem(this.contextMenue , KELVIN_TEXT, "ValueGraph.CMD_ADD");
	 showKelvinItem.setSelected(false);

	 this.showCelsiusItem = this.addRadioButtonMenuItem(this.contextMenue , CELSIUS_TEXT, "ValueGraph.CMD_ADD");

	 
	  group.add(this.showKelvinItem);
	  group.add(this.showCelsiusItem);
	  
}


	@Override
	public void nextFrame(ThermalCamera camera)
	{
		System.out.println("next frame");
		
		ThermalFrame frame = camera.getActiveFrame();
		this.thermalPanel.setFrame(frame);
		this.dataInfo.setFrame(frame);
		this.thermalPanel.invalidate();
		this.thermalPanel.repaint();
	}

	public static ComponentView createView(ThermalCamera camera)
	{
		if (camera!=null)
		{
			return(new ThermalCameraDataView(camera));
		}
		else
		{
			return(new MissingComponentView(ThermalCamera.class.getName()));
		}
	}




	
}



	
	


