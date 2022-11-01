package de.hska.lat.robot.morphology.model.viewer;



import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import de.hska.lat.math.Radiant;
import de.hska.lat.robot.morphology.model.Bone;




public class MorphologicTableModel extends AbstractTableModel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6007163788746622409L;

	protected ArrayList<Bone> bones = new ArrayList<Bone>(); 
	
	public static final String [] columnNames = {"name","x°","y°","z°","x","y","z"};
	
	
public MorphologicTableModel()
{
	
}



public void set(ArrayList<Bone> bones)
{
	this.bones.clear();
	this.bones.addAll(bones);
}



@Override
public String getColumnName(int column)
{
	if (column < columnNames.length)
		return (columnNames[column]);
	
	return ("");
}


@Override
public int getColumnCount()
{
	// TODO Auto-generated method stub
	return (columnNames.length);
}



@Override
public int getRowCount()
{
	// TODO Auto-generated method stub
	return bones.size();
}



@Override
public Object getValueAt(int row, int collumn)
{
	switch (collumn)
	{
	case 0:
		return (this.bones.get(row).getName());
		
		
	case 1:
		return (Radiant.convertRadiantToDegree(this.bones.get(row).getRotation(Bone.Axis_e.X_AXIS))+"°");
		

	case 2:
		return (Radiant.convertRadiantToDegree(this.bones.get(row).getRotation(Bone.Axis_e.Y_AXIS))+"°");
		
	case 3:
		return (Radiant.convertRadiantToDegree(this.bones.get(row).getRotation(Bone.Axis_e.Z_AXIS))+"°");

		
	case 4:
		return (this.bones.get(row).getExtent(Bone.Axis_e.X_AXIS)+"mm");

	case 5:
		return (this.bones.get(row).getExtent(Bone.Axis_e.Y_AXIS)+"mm");

	case 6:
		return (this.bones.get(row).getExtent(Bone.Axis_e.Z_AXIS)+"mm");

		
	}
	
	// TODO Auto-generated method stub
	return null;
}
	
	
}
