package de.hska.lat.robot.morphology.appearance.Editor3D;

import javax.swing.table.AbstractTableModel;

import de.hska.lat.robot.morphology.appearance.Editor3D.entities.Cuboid;
import de.hska.lat.robot.morphology.appearance.Editor3D.entities.Figure;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresEvent;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresEventHandler;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresListener;

public class VertexTableView extends AbstractTableModel implements FiguresListener {

	
	private static final long serialVersionUID = 4316900888544919263L;
	FiguresEventHandler eventHandler;
	Figure figure;
	Cuboid cuboid;

	public VertexTableView(FiguresEventHandler eventHandler, Cuboid cuboid) {
		this.eventHandler = eventHandler;
		eventHandler.addFiguresListener(this);
		this.cuboid = cuboid;
		this.figure = eventHandler.getFigures().getFigure(cuboid.getFigureId());
	}
	
	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public int getRowCount() {
		return (figure.getVertices().length / 10);
	}

	@Override
	public String getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return new Integer(rowIndex + 1).toString();
		}
		else {
			float[] vertices = figure.getVertices();
			return new Float(vertices[(10 * rowIndex) + (columnIndex - 1)]).toString();
		}
	}
	
	public boolean isCellEditable( int rowIndex, int columnIndex )
	{
	  return (columnIndex != 0);
	}
	
	public void setValueAt( Object value, int rowIndex, int columnIndex )
	{
		float x = Float.parseFloat(getValueAt(rowIndex, 1));
		float y = Float.parseFloat(getValueAt(rowIndex, 2));
		float z = Float.parseFloat(getValueAt(rowIndex, 3));
		
		if (columnIndex == 1) {
			x = Float.parseFloat(value.toString());
		}

		else if (columnIndex == 2) {
			y = Float.parseFloat(value.toString());
		}

		else if (columnIndex == 3) {
			z = Float.parseFloat(value.toString());
		}
		
		cuboid.updateVertex(rowIndex, x, y, z);
		
		this.fireTableCellUpdated(rowIndex, columnIndex);
	}
	
	
	@Override
	public void wasUpdated(FiguresEvent e) {
		this.fireTableDataChanged();
	}
}
