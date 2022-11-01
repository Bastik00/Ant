package de.hska.lat.robot.morphology.appearance.Editor3D;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableCellEditor;
import javax.swing.text.DefaultFormatter;

import de.hska.lat.robot.morphology.appearance.Editor3D.entities.Cuboid;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresEvent;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresEventHandler;

public class VertexTableEditor extends AbstractCellEditor implements TableCellEditor, ChangeListener, ActionListener {


	private static final long serialVersionUID = -419134325459309553L;

	FiguresEventHandler eventHandler;
	Cuboid cuboid;
	private JSpinner spinner;
	private SpinnerNumberModel numberModel;
	private JButton button;
	int rowIndex;
	int colIndex;
	
	public VertexTableEditor(Cuboid cuboid, FiguresEventHandler eventHandler) {
		this.cuboid = cuboid;
		this.eventHandler = eventHandler;
		
		numberModel = new SpinnerNumberModel(0, -1000, 1000, 1);
		spinner = new JSpinner(numberModel);
		spinner.addChangeListener(this);

	    JFormattedTextField field = (JFormattedTextField) spinner.getEditor().getComponent(0);
	    ((DefaultFormatter) field.getFormatter()).setCommitsOnValidEdit(true);
		
	}
	
	public Component getTableCellEditorComponent(
		JTable table, Object value,
      	boolean isSelected, int rowIndex, int colIndex 
  	) {
		this.rowIndex = rowIndex;
		this.colIndex = colIndex;
		
		if (colIndex == 4) {
			button = new JButton("X");
			button.addActionListener(this);
			return button;
		}
		numberModel.setValue(Float.parseFloat(value.toString()));
		return spinner;
	}
	
	
	public Object getCellEditorValue()
	{
		return numberModel.getValue();
	}


	@Override
	public void stateChanged(ChangeEvent arg0) {
		float[] vertices = cuboid.getVertices();
		
		float x = vertices[rowIndex * 10]; 
		float y = vertices[(rowIndex * 10) + 1];
		float z = vertices[(rowIndex * 10) + 2];
		
		if (colIndex == 1) {
			x = Float.parseFloat(numberModel.getValue().toString());
		}
		else if (colIndex == 2) {
			y = Float.parseFloat(numberModel.getValue().toString());
		}
		else if (colIndex == 3) {
			z = Float.parseFloat(numberModel.getValue().toString());
		}
		
		cuboid.updateVertex(rowIndex, x, y, z);

		eventHandler.notifyUpdate(new FiguresEvent(this));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		eventHandler.notifyUpdate(new FiguresEvent("updateTable;" + rowIndex ));
	}
}
