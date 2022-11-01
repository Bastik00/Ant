package de.hska.lat.robot.control;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JToggleButton;

import net.java.games.input.Component.Identifier;

import de.hska.lat.robot.control.joystick.IJoystick;
import de.hska.lat.robot.control.joystick.Joystick;
import de.hska.lat.robot.control.joystick.axis.AxisEvent;
import de.hska.lat.robot.control.joystick.axis.AxisListener;
import de.hska.lat.robot.control.joystick.button.ButtonEvent;
import de.hska.lat.robot.control.joystick.button.ButtonListener;

public class JoystickWatcherForm implements AxisListener, ButtonListener {

	public IJoystick joystick;
	
	private JoystickTestView window;
	
	public JoystickWatcherForm(JoystickTestView window) {
		joystick = new Joystick();
		
		joystick.addAxisListener(this);
		joystick.addButtonListener(this);
		
		this.window = window;
	}
	
	public void initWindow() {
		initButtons();

    	initAxis();
	}
	
	private void initButtons() {
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 1));
        buttonsPanel.setBounds(6, 19, 246, 110);
        
        Identifier.Button[] buttonIdentifiers = joystick.getAllButtonIdentifier();
        
        if(buttonIdentifiers == null) {
        	return;
        }
        
        int i = 0;
        for(Identifier.Button buttonIdentifier : buttonIdentifiers) {
        	Boolean buttonValue = joystick.getButtonValue(buttonIdentifier);
        	JToggleButton aToggleButton = new JToggleButton(""+(++i), buttonValue);
            aToggleButton.setPreferredSize(new Dimension(48, 25));
            aToggleButton.setEnabled(false);
            buttonsPanel.add(aToggleButton);
        }
        
        window.setControllerButtons(buttonsPanel);
	}
	
	private void initAxis() {
//		window.setXYAxis(Joystick.getOldAxisPercentageValue(AxisName.XAxis), 
//				Joystick.getOldAxisPercentageValue(AxisName.YAxis));
//		
//		window.setXYAxis(Joystick.getOldAxisPercentageValue(AxisName.XAxis), 
//				Joystick.getOldAxisPercentageValue(AxisName.YAxis));
//
//		window.setZRotation(Joystick.getOldAxisPercentageValue(AxisName.ZRotation));
//
//		window.setSliderPosition(joystick.getOldAxisPercentageValue(Identifier.Axis.SLIDER));
//
//		window.setHatSwitch(joystick.getOldAxisPercentageValue(Identifier.Axis.POV));
		
		window.setSliderPosition(joystick.getAxisValue(Identifier.Axis.SLIDER));
	}
	
	@Override
	public void buttonChanged(ButtonEvent buttonEvent) {
		if(this.window.isClosed()) {
			return;
		}
		String buttonName = buttonEvent.getButtonIdentifier().toString();
		
		System.out.println(buttonName + " was changed.");
		
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 1));
        buttonsPanel.setBounds(6, 19, 246, 110);
        
        Identifier.Button[] buttonIdentifiers = this.joystick.getAllButtonIdentifier();
        
        int i=0;
        for (Identifier.Button buttonIdentifier : buttonIdentifiers) {
        	Boolean value = joystick.getButtonValue(buttonIdentifier);
            JToggleButton aToggleButton = new JToggleButton(""+(++i), value);
            aToggleButton.setPreferredSize(new Dimension(48, 25));
            aToggleButton.setEnabled(false);
            buttonsPanel.add(aToggleButton);
		}
        
        window.setControllerButtons(buttonsPanel);
	}

	@Override
	public void axisMove(AxisEvent axisEvent) {
		if(this.window.isClosed()) {
			return;
		}
		Identifier.Axis axisIdentifier = axisEvent.getAxisIdentifier();
		float value = axisEvent.getValue();
		float speed = axisEvent.getSpeed();
		
		System.out.println(axisIdentifier.toString() + " was moved. Value: " + value + " Speed: " + speed);
		
		if(axisIdentifier.getName().equals("x")) {
			this.window.setXYAxis(axisEvent.getPercentageValue(), this.joystick.getOldAxisPercentageValue(Identifier.Axis.Y));
		} else if(axisIdentifier.getName().equals("y")) {
			this.window.setXYAxis(this.joystick.getOldAxisPercentageValue(Identifier.Axis.X), axisEvent.getPercentageValue());
		} else if(axisIdentifier.getName().equals("rz")) {
			this.window.setZRotation(axisEvent.getPercentageValue());
		}  else if(axisIdentifier.getName().equals("slider")) {
			this.window.setSliderPosition(axisEvent.getValue());
		}  else if(axisIdentifier.getName().equals("pov")) {
			this.window.setHatSwitch(axisEvent.getValue());
		} 
	}
	
}
