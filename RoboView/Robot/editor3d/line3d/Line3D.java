package de.hska.lat.robot.editor3d.line3d;

import de.hska.lat.math.vector.FloatVector3D;

public class Line3D {
	private FloatVector3D a;
	private FloatVector3D b;
	private FloatVector3D dir;

	public Line3D(FloatVector3D a, FloatVector3D b) {
		// Stützvektor
		this.a = a;

		// Richtungsvektor
		this.b = b.copy();
		this.dir = b.copy();
		this.dir.sub(a);
		
	}

	public FloatVector3D calculatePoint(float r) {
		FloatVector3D aCopy = a.copy();
		FloatVector3D dirCopy = dir.copy();
		dirCopy.scale(r);

		aCopy.add(dirCopy);
		return aCopy.copy();
	}

	public FloatVector3D getPointInSegmentWithDistance(double distance) {
		double distanceNew = 0;
		float r = 0;

		if(Double.isInfinite(distance)){
			return null;
		}
		FloatVector3D currentPoint = a.copy();
//		System.out.println("Suche Punkt zwischen: A: " + a + " und B: " + b + " mit dem Abstand: " + distance);
//		System.out.println("Berechnung gestartet... Erster Versuch");
//		System.out.println("Entfernung der Vectoren: " + distance);

		while (distanceNew <= distance) {
			currentPoint = calculatePoint(r);
			r += 0.0001;
			distanceNew = currentPoint.distance(a);
			if (Math.abs(distanceNew - distance) < 0.05) {
				if (!(a.distance(b) < a.distance(currentPoint) && (a.distance(b) < b
						.distance(currentPoint)))) {
					return currentPoint;
				}
			}
		}

		currentPoint.clear();
		r = 0;
//		System.out.println("distanceNew: " + distanceNew);
		while (distanceNew <= distance) {
			currentPoint = calculatePoint(r);
			r -= 0.0001;

			distanceNew = currentPoint.distance(a);
			if (Math.abs(distanceNew - distance) < 0.05) {
				if ((a.distance(b) < a.distance(currentPoint) || a.distance(b) < b
						.distance(currentPoint))) {
					return currentPoint;
				}
			}
		}

		return null;
	}

	public FloatVector3D getA() {
		return a;
	}

	public void setA(FloatVector3D a) {
		this.a = a;
	}

	public FloatVector3D getDir() {
		return dir;
	}

	public void setDir(FloatVector3D dir) {
		this.dir = dir;
	}

	public FloatVector3D getB() {
		return b;
	}

	public void setB(FloatVector3D b) {
		this.b = b;
	}
}
