package de.hska.lat.robot.value.view.detector;


import de.hska.lat.robot.value.ComponentValue;
import de.hska.lat.robot.value.detector.DetectorValue;
import de.hska.lat.robot.value.view.ValueView;


public class DetectorValueView extends ValueView<DetectorValue>
{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -9199828567159980780L;
	DetectorPanel canvas;
	

	
public DetectorValueView(DetectorValue value, boolean embedded) 
{
	super(value, embedded);

	this.value.addListener(this);
	buildView();
	this.setSize(80, 70);

}


@Override
protected void buildView()
{
	
	this.canvas = new DetectorPanel();
	canvas.setBounds(20, 20, 40, 40);
	this.add(canvas);

	
}


protected void finalize()
{
	this.value.removeListener(this);
}



@Override
public void valueChanged(ComponentValue<?> componentValue)
{
	this.canvas.setStatus(value.getStatus());
	this.canvas.repaint();
}


}
