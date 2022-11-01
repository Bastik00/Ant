package de.hska.lat.robot.display.generics.map2D.gridMap;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Set;
import java.util.Vector;

import de.hska.lat.cartography.map.gridMap.Collision;
import de.hska.lat.cartography.map.gridMap.GridMap2D;
import de.hska.lat.cartography.map.gridMap.GridMapFragment;
import de.hska.lat.robot.display.generics.map2D.displayData.DisplayData;
import de.hska.lat.robot.display.generics.map2D.drawer.Map2DDrawer;

public class GridMap2DDrawer extends Map2DDrawer
{

	GridMap2D map;

	public GridMap2DDrawer(DisplayData displayData, GridMap2D map)
	{
		this.map = map;
		this.displayData = displayData;
	}

	/**
	 * draw grid map
	 */
	public void draw(Graphics2D graphics)
	{
		float yMax = 0;
		float xMin = 0;
		float yMin = yMax + (this.displayData.getyDimension());
		float xMax = xMin + (this.displayData.getxDimension());

		graphics.setColor(Color.RED);
		// System.out.println("Eckpunkte des Bildschirms: " + xMin + "/" + yMin
		// + "/" + xMax + "/" + yMax);

		graphics.drawString("X", xMin, yMin);
		graphics.drawString("X", xMin, yMax);
		graphics.drawString("X", xMax, yMin);
		graphics.drawString("X", xMax, yMax);

		graphics.drawLine((int) xMin, (int) yMin, (int) xMax, (int) yMax);
		graphics.drawLine((int) xMin, (int) yMax, (int) xMax, (int) yMin);

		xMin = this.displayData.translateXFormScreen(xMin);
		xMax = this.displayData.translateXFormScreen(xMax);
		yMin = this.displayData.translateYFormScreen(yMin);
		yMax = this.displayData.translateYFormScreen(yMax);

		Set<Vector<Integer>> keys = this.map.collisionMap.keySet();
		for (Vector<Integer> gridFragmentCoordVector : keys)
		{
			if (gridFragmentCoordVector.get(0) >= (int) xMin && gridFragmentCoordVector.get(0) <= (int) xMax
					&& gridFragmentCoordVector.get(1) >= (int) yMin && gridFragmentCoordVector.get(1) <= (int) yMax)
			{
				// graphics.drawString("XXXX",
				// this.displayData.translateXToScreen(vector.get(0) * 10),
				// this.displayData.translateYToScreen(vector.get(1) * (-10)));

				GridMapFragment fragment = this.map.collisionMap.get(gridFragmentCoordVector);
				for (Collision c : fragment.getCollisionList())
				{
					if (c.isConnectToPreviousCollision())
					{
						drawLine(graphics, gridFragmentCoordVector, c);
					} else
					{
						drawPoint(graphics, gridFragmentCoordVector, c);
					}
					// System.out.println("!!!!!!!!!!!!!!!!!!!!!Geschrieben wurde: " + (minX + x) + "/" + (minY + y));

				}
			}
		}
	}

	private void drawLine(Graphics2D graphics, Vector<Integer> gridFragmentCoordVector, Collision c)
	{
		int x = 0;
		int y = 0;
		int minX = 0;
		int minY = 0;
		if (gridFragmentCoordVector.get(0) < 0)
		{
			x = -(GridMapFragment.SIZE - c.getX());
			minX = gridFragmentCoordVector.get(0) + 1;
		} else
		{
			x = c.getX();
			minX = gridFragmentCoordVector.get(0);
		}
		if (gridFragmentCoordVector.get(1) < 0)
		{
			y = -(GridMapFragment.SIZE - c.getY());
			minY = gridFragmentCoordVector.get(1) + 1;
		} else
		{
			y = c.getY();
			minY = gridFragmentCoordVector.get(1);
		}

		graphics.drawString("X", this.displayData.translateXToScreen((minX + x) * 10),
				this.displayData.translateYToScreen((minY + y) * (-10)));
		graphics.drawLine((int) this.displayData.translateXToScreen((minX + x) * 10),
				(int) this.displayData.translateYToScreen((minY + y) * (-10)),
				(int) this.displayData.translateXToScreen((c.getLastX()) * 10),
				(int) this.displayData.translateYToScreen((c.getLastY()) * (-10)));

	}

	private void drawPoint(Graphics2D graphics, Vector<Integer> gridFragmentCoordVector, Collision c)
	{
		int x = 0;
		int y = 0;
		int minX = 0;
		int minY = 0;
		if (gridFragmentCoordVector.get(0) < 0)
		{
			x = -(GridMapFragment.SIZE - c.getX());
			minX = gridFragmentCoordVector.get(0) + 1;
		} else
		{
			x = c.getX();
			minX = gridFragmentCoordVector.get(0);
		}
		if (gridFragmentCoordVector.get(1) < 0)
		{
			y = -(GridMapFragment.SIZE - c.getY());
			minY = gridFragmentCoordVector.get(1) + 1;
		} else
		{
			y = c.getY();
			minY = gridFragmentCoordVector.get(1);
		}

		graphics.drawString("X", this.displayData.translateXToScreen((minX + x) * 10),
				this.displayData.translateYToScreen((minY + y) * (-10)));
	}

}
