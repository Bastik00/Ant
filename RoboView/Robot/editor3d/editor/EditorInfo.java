package de.hska.lat.robot.editor3d.editor;

import de.hska.lat.robot.editor3d.editor.Editor.Axes;

public class EditorInfo {
	private CameraPosition cameraPosition;
	private String editorName;
	private Axes ignoredAxis;

	public EditorInfo(int[] pos, int[] lookAt, int[] up, String name,
			Axes ignoredAxis) {
		cameraPosition = new CameraPosition(pos, lookAt, up);
		editorName = name;
		this.setIgnoredAxis(ignoredAxis);
	}

	public CameraPosition getCameraposition() {
		return cameraPosition;
	}

	public String getEditorName() {
		return editorName;
	}

	public void setEditorName(String editorName) {
		this.editorName = editorName;
	}

	public Axes getIgnoredAxis() {
		return ignoredAxis;
	}

	public void setIgnoredAxis(Axes ignoredAxis) {
		this.ignoredAxis = ignoredAxis;
	}

	// CameraInformationen
	public class CameraPosition {
		private int[] pos;
		private int[] lookAt;
		private int[] up;

		public CameraPosition(int[] pos, int[] lookAt, int[] up) {
			this.pos = pos;
			this.lookAt = lookAt;
			this.up = up;
		}

		public int[] getPos() {
			return pos;
		}

		public void setPos(int[] pos) {
			this.pos = pos;
		}

		public int[] getLookAt() {
			return lookAt;
		}

		public void setLookAt(int[] lookAt) {
			this.lookAt = lookAt;
		}

		public int[] getUp() {
			return up;
		}

		public void setUp(int[] up) {
			this.up = up;
		}
	}
}
