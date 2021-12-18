import java.awt.Color;
import java.awt.Graphics;

// Class to represent Axis-Aligned Rectangles


public class Rect
{
	int x;
	int y;
	
	int w;
	int h;
	
	Color color = Color.BLACK;
	
	public Rect(int x, int y, int w, int h)
	{
		this.x = x;
		this.y = y;
		
		this.w = w;
		this.h = h;
	}
	
	public Rect(int x, int y, int w, int h, Color color)
	{
		this.x = x;
		this.y = y;
		
		this.w = w;
		this.h = h;
		
	    setColor(color);
	}

	public void setBounds(int x, int y, int w, int h)
	{
		this.x = x;
		this.y = y;
		
		this.w = w;
		this.h = h;
	}
	public void setPosition(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void moveLeft(int dx)
	{
		x -= dx;
	}
	
	public void moveRight(int dx)
	{
		x += dx;
	}

	public void moveUp(int dy)
	{
		y -= dy;
	}
	
	public void moveDown(int dy)
	{
		y += dy;
	}
	
	public void moveBy(int dx, int dy)
	{
		x += dx;
		y += dy;
	}
	
	public void resizeBy(int dw, int dh)
	{
		w += dw;
		h += dh;
	}
	
	public boolean overlaps(Rect r)
	{
		return (x + w >= r.x      ) &&
			   (x     <= r.x + r.w) &&
			   (y + h >= r.y      ) &&
			   (y     <= r.y + r.h);
	}
	
	public boolean contains(int mx, int my)
	{
		return ( mx > x   ) &&   
			   ( mx < x+w ) && 
			   ( my > y   ) && 
			   ( my < y+h );
	}
	
	public void setColor(Color color)
	{
		this.color = color;
	}
	public void bHit()
	{	
		if(w != 0)
		w -= 50;
	}
	public void rHit()
	{
		if(w != 0)
		{
		w -= 200;
		x += 50;
		System.out.println("Hit");
		}
	}
	
	
	
	public void draw(Graphics pen)
	{
		pen.setColor(color);
		
		pen.drawRect(x, y, w, h);
	}
}