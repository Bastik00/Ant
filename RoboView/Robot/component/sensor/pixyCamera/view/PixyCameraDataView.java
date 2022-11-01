package de.hska.lat.robot.component.sensor.pixyCamera.view;

import java.awt.Insets;

import javax.swing.JTable;

import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.component.sensor.pixyCamera.PixyCamera;
import de.hska.lat.robot.component.sensor.pixyCamera.PixyCameraChangeNotifier;

public class PixyCameraDataView extends ComponentView implements PixyCameraChangeNotifier {

	protected static final int width = 160;
	protected static final int height = 110;

	private static final long serialVersionUID = 6152155775319804201L;

	private JTable objectBlockTable;

	public PixyCameraDataView(PixyCamera camera, boolean embedded) {
		super(camera.getComponentName(), embedded);
		
		camera.addSensorListener(this);
		
		createView();
	}

	protected void createView() {
		super.buildView();

		Insets insets = this.getBorder().getBorderInsets(this);

		Object rowData[][] = { { "signature:", "-" }, { "x center:", "-" }, { "y center:", "-" }, { "width:", "-" },
				{ "height:", "-" }, { "angle:", "-" } };
		Object columnNames[] = { "Descr.", "Value" };

		
		objectBlockTable = new JTable(rowData, columnNames);
		objectBlockTable.setBounds(insets.left + 5, insets.top + 5, 150, 80);
		objectBlockTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		objectBlockTable.setValueAt("init", 2, 1);
		this.add(objectBlockTable);
		
	}

	@Override
	protected int getViewWidth() {
		return (PixyCameraDataView.width);
	}

	@Override
	protected int getViewHeight() {
		return (PixyCameraDataView.height);
	}

	@Override
	public void cameraObjectDetected(PixyCamera camera) {
		objectBlockTable.setValueAt(camera.signature, 0, 1);
		objectBlockTable.setValueAt(camera.xCenter, 1, 1);
		objectBlockTable.setValueAt(camera.yCenter, 2, 1);
		objectBlockTable.setValueAt(camera.width, 3, 1);
		objectBlockTable.setValueAt(camera.height, 4, 1);
		objectBlockTable.setValueAt(camera.angle, 5, 1);
	}

}
