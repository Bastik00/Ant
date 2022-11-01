package de.hska.lat.robot.ui.icon;



import javax.swing.JPanel;
/*
import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.BlurMaskFilter.Blur;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
*/

public class DroidIcon<L extends DroidIconListener> extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5883077378798595723L;

	
	
	protected String name;
//	protected Paint paint = new Paint();;
	
	protected int frameColor;
	protected int attentionColor;
	protected int errorColor;
	
//	protected RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(0, 0);

	protected L listener;
	
	
	protected static final int MIN_HEIGHT 	= 30 ;
	protected static final int MIN_WIDTH 	= 30 ;
	
	protected int minWidth = DroidIcon.MIN_WIDTH;
	protected int minHeight = DroidIcon.MIN_HEIGHT;
	
	
public DroidIcon(String name)
{
//	super(context);

	this.name=name;
	this.init();

	

}




private void init()
{
/*	paint.setTextSize(25);
	paint.setAntiAlias(true);
*/	
	this.frameColor=0xff5090ff;
	this.attentionColor=0xffaaff00;
	this.errorColor=0xffff0000;
}

/**
 * set listener for icon selection
 * @param listener icon listener
 */
public void setListener(L listener)
{
	this.listener = listener;
}


/**
 * set frame normal color
 * @param color normal frame color
 */
public void setFrameColor(int color)
{
	this.frameColor=color;
}

/**
 * Sets frame attention color. The icon frame will be drawn in attention color if
 * object represented thru this icon need some interaction with user 
 * @param color attention color
 */
public void setAttentionColor(int color)
{
	this.attentionColor=color;
}

/**
 * Sets frame error color. The icon frame will be drawn in this color if object represented 
 * by this icon is in error state  
 * @param color error color
 */

public void setErrorColor(int color)
{
	this.errorColor=color;
}

public void setHight(int height) 
{
	if (height < this.minHeight) 
	{
		height = this.minHeight;
	}
	
	this.setSize(this.getWidth(),height );
}



public void setWidth(int width) 
{
	if (width < this.minWidth) 
	{
		width = this.minWidth;
	}
	
	super.setSize(width, this.getHeight());
	
}


}