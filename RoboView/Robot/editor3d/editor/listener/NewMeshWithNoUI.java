package de.hska.lat.robot.editor3d.editor.listener;

import de.hska.lat.math.vector.FloatVector3D;
import de.hska.lat.robot.morphology.appearance.Editor3D.entities.Cuboid;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresEventHandler;

public class NewMeshWithNoUI  {
	public NewMeshWithNoUI(FiguresEventHandler eventHandler,
			FloatVector3D newPoint, int afterID, int beforeID, int figureID) {
		
		int pointId1 = beforeID;
		int pointId2 = eventHandler.getFigures().getFigure(figureID).getVertices().length/10;
		int pointId3 = afterID;
		
		Cuboid cuboid = new Cuboid(eventHandler,
				figureID);
		
		cuboid.addVertex(pointId2, newPoint.x, newPoint.y, newPoint.z);
		
		cuboid.addElements(pointId1, pointId2, pointId3);
		cuboid.setStatus("done");
		eventHandler.notifyUpdate(null);
	}
}
