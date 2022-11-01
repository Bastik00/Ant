package de.hska.lat.robot.editor3d.editor;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import java.awt.event.MouseListener;
import com.jogamp.opengl.util.FPSAnimator;

import de.hska.lat.robot.displayFrame.DisplayPanel;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresEventHandler;

/**
 * An editor contains a PaintArea and an Editor Toolbar
 * 
 * @author moritz
 * 
 */
public class Editor extends DisplayPanel  {

	private static final long serialVersionUID = 1L;
	private EditorInfo editorInfo;
	private EditorToolBar toolBar;
	private PaintArea area;

	/**
	 * 
	 * @param color
	 * @param eventHandler
	 * @param animator
	 * @param editorInfo
	 */
	public Editor(FiguresEventHandler eventHandler,
			FPSAnimator animator, EditorInfo editorInfo) {
		setLayout(new BorderLayout());
		this.setEditorInfo(editorInfo);
		
		area = new PaintArea(animator, eventHandler, editorInfo);
		add(area, BorderLayout.NORTH);
		toolBar = new EditorToolBar(area);
		toolBar.setPreferredSize(new Dimension(400, 30));
		add(toolBar, BorderLayout.SOUTH);

		this.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), editorInfo.getEditorName()));

	}

	/**
	 * Add a MouseListener to this Editors paintarea
	 */
	public void addMouseListenerToPaintArea(MouseListener listener) {
		this.area.addMouseListener(listener);
	}



	public EditorInfo getEditorInfo() {
		return editorInfo;
	}

	public void setEditorInfo(EditorInfo editorInfo) {
		this.editorInfo = editorInfo;
	}
	
	public PaintArea getPaintArea(){
		return area;
	}
	public enum Axes{
		X, Y, Z;
	}
}
