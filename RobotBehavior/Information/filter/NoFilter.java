package de.hska.lat.behavior.information.filter;

public class NoFilter extends InformationFilter
{

public NoFilter()
{
	super(1);
}



@Override
protected float calculate()
{
	return (this.data[0]);
}


}
