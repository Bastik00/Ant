package de.hska.lat.robot.device.generic.bipedal.footSensors;


import de.hska.lat.robot.device.generic.bipedal.footSensors.FootSensors;
import de.hska.lat.robot.component.digitalDetector.DigitalDetector;

import de.hska.lat.robot.device.viewer.DeviceView;
import de.hska.lat.robot.value.view.detector.DetectorValueView;



public class FootSensorsDataView extends DeviceView{



/**
	 * 
	 */
	private static final long serialVersionUID = 3075509291650555718L;


public FootSensorsDataView(FootSensors fs, int[] id) 
{
	super(fs);
	
	
	DetectorValueView leftObjectDetector = this.addDetector(id[0],fs);
	DetectorValueView rightObjectDetector = this.addDetector(id[1],fs);
	
	DetectorValueView leftFrontDetector = this.addDetector(id[2],fs);
	DetectorValueView rightFrontDetector = this.addDetector(id[3],fs);
	DetectorValueView leftBackDetector = this.addDetector( id[4],fs);
	DetectorValueView rightBackDetector = this.addDetector(id[5],fs);
	DetectorValueView centerDetector = this.addDetector( id[6],fs);
	
	
	this.addComponent(leftObjectDetector,5,5);
	this.addComponentAtRight(leftObjectDetector,rightObjectDetector,5,0);
	
	
	this.addComponentAtBottom(leftObjectDetector,leftFrontDetector,5,5);
	this.addComponentAtRight(leftFrontDetector,rightFrontDetector,5,0);
	
	
	this.addComponentAtBottom(leftFrontDetector,centerDetector,leftFrontDetector.getWidth()/2,5);
	
	this.addComponentAtBottom(centerDetector,leftBackDetector,-leftFrontDetector.getWidth()/2,5);
	this.addComponentAtRight(leftBackDetector,rightBackDetector,5,0);


	

	//this.setSize(100,300);
	this.autoResize();
}



public void makeDisplay()
{
	
}


public DetectorValueView addDetector(int globalId,FootSensors fs )
{
	DigitalDetector detector;
	
	detector= (DigitalDetector) fs.findComponentOnGlobalId(globalId);
	
	DetectorValueView detectorView = new DetectorValueView(detector.getValue(), false);
//	detector.addSensorListener(detectorView);

	return(detectorView);
}





}
