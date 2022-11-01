package de.hska.lat.behavior.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;


import de.hska.lat.behavior.arbiter.Arbiter;
import de.hska.lat.behavior.arbiter.InhibitorNode;
import de.hska.lat.behavior.arbiter.SuppressorNode;
import de.hska.lat.behavior.arbiter.view.ArbiterViewer;


/**
 * The JPanel which holds and connects all ArbiterViewer.
 * @author Lukas M.
 * @see ArbiterViewer
 */
class ArbiterLinkViewer extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4730961676482244979L;
	
	HashMap<Arbiter, ArrayList<Arbiter>> map = new HashMap<Arbiter, ArrayList<Arbiter>>();

	public ArbiterLinkViewer() {
		//this.setBorder(new TitledBorder(""));
		this.repaint();
	}

	public void setMapping(HashMap<Arbiter, ArrayList<Arbiter>> hashMap) {
		this.map = hashMap;
		this.repaint();
		this.revalidate();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (Arbiter key : map.keySet()) {
			
			if(key instanceof SuppressorNode){
				((SuppressorNode)key).getSuppressorInput();
				((SuppressorNode)key).getInput();
			}else if(key instanceof InhibitorNode){
				((InhibitorNode)key).getInhibitorInput();
				((InhibitorNode)key).getInput();
			}else{
				int i = -50;
				for (Arbiter component : map.get(key)) {
					Point p1 = getRight(getView(key));
					Point p2 = getLeft(getView(component));
					
					g.drawString(component.getActions().getActualAction().getName(), p1.x + 10, p1.y - i);
					g.drawLine(p1.x, p1.y - i, p2.x - 5, p2.y);
					
					g.setColor(Color.MAGENTA);
					g.fillOval(p2.x - 5, p2.y - 2, 5, 5);
					g.fillOval(p1.x , p1.y - 2 - i, 5, 5);
					g.setColor(Color.BLACK);
					
					i = i - 20;	
				}
			}
		}
	}

	private Point getLeft(Component c) {
		Point r = new Point();
		r.x = (int) c.getX();
		r.y = (int) c.getBounds().getCenterY();
		return r;
	}

	private Point getRight(Component c) {
		Point r = new Point();
		r.x = (int) c.getX() + c.getWidth();
		r.y = (int) c.getY();
		return r;
	}
	
	/**
	 * Experimental; get the ArbiterViewer from linkpanel by arbiter
	 * @param arbiter
	 * @return JPanel which is an ArbiterViewer
	 */
	private ArbiterViewer<?> getView(Arbiter arbiter) {
		for (Component c : this.getComponents()) {
			if(((ArbiterViewer<?>)c).getArbiter().equals(arbiter)){
				return (ArbiterViewer<?>)c;
			}
		}
		return null;
	}
}