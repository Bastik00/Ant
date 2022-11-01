package de.hska.lat.robot.value.view;



import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.value.ComponentValue;
import de.hska.lat.robot.value.ComponentValueChangeListener;




public abstract class ValueView<V extends ComponentValue<?>> extends ComponentView implements  ComponentValueChangeListener
{

/**
	 * 
	 */
	private static final long serialVersionUID = 5053002074211917692L;

	
	
	protected V value;



	
public ValueView(V value, boolean embedded)
{
	super(value.getName(), embedded);

	this.value=value;


}





}
