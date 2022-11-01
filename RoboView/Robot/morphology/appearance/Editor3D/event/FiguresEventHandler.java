package de.hska.lat.robot.morphology.appearance.Editor3D.event;

import javax.swing.event.EventListenerList;

import de.hska.lat.color.RgbColor;
import de.hska.lat.robot.editor3d.palette.Palette.Tool;
import de.hska.lat.robot.morphology.appearance.AppearanceModel;
import de.hska.lat.robot.morphology.appearance.Editor3D.entities.Figures;

public class FiguresEventHandler {

	private EventListenerList listeners = new EventListenerList();
	private Figures figures;
	private AppearanceModel appearanceModel;
	private RgbColor color;
	private Tool tool;
	private float Zoomfactor;
	private String figurePath;
	private int selectedFigure;
	private boolean organLoaded;
	
	public FiguresEventHandler(Figures figures) {
		this.figures = figures;
		setColor(new RgbColor(0.5f, 0.5f, 0.5f));
		this.setZoomfactor(1);
		this.setOrganLoaded(false);
	}
	
	public Figures getFigures() {
		return figures;
	}
	
	public void setFigures(Figures figures) {
		this.figures = figures;
	}
	
	public AppearanceModel getAppearanceModel() {
		return appearanceModel;
	}

	public void setAppearanceModel(AppearanceModel appearanceModel) {
		this.appearanceModel = appearanceModel;
	}
	
	public void addFiguresListener( FiguresListener listener )
	{
		listeners.add(FiguresListener.class, listener);
	}


	public void removeFiguresListener( FiguresListener listener )
	{
		listeners.remove(FiguresListener.class, listener);
	}
	
	public EventListenerList getFiguresListener() {
		return listeners;
	}

	public void notifyUpdate( FiguresEvent event )
	{
		for (FiguresListener listener : listeners.getListeners(FiguresListener.class)) {
			listener.wasUpdated(event);	
		}
	}

	public RgbColor getColor() {
		return color;
	}

	public void setColor(RgbColor color) {
		this.color = color;
	}

	public Tool getTool() {
		return tool;
	}

	public void setTool(Tool tool) {
		this.tool = tool;
	}

	public float getZoomfactor() {
		return Zoomfactor;
	}

	public void setZoomfactor(float zoomfactor) {
		Zoomfactor = zoomfactor;
	}

	public String getFigurePath() {
		return figurePath;
	}

	public void setFigurePath(String figurePath) {
		this.figurePath = figurePath;
	}

	public int getSelectedFigure() {
		return selectedFigure;
	}

	public void setSelectedFigure(int selectedFigure) {
		this.selectedFigure = selectedFigure;
	}

	public boolean isOrganLoaded() {
		return organLoaded;
	}

	public void setOrganLoaded(boolean organLoaded) {
		this.organLoaded = organLoaded;
	}
	
	
}
