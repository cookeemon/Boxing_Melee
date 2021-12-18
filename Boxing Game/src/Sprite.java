//import java.awt.Graphics;
//
//public class Sprite
//{
//	// Position P
//	double x;
//	double y;
//	
//	// Velocity V
//	double vx = 0;
//	double vy = 0;
//	
//	// Accerleration a
//	double ax = 0;
//	double ay = gravity;
//	
//	
//	int w;
//	int h;
//	
//	
//	final static double gravity = 0.3; 
//	
//	
//	
//	
//	Animation[] animation;
//	
//	boolean moving = true;
//	
//	final int IDLE  = 0;
//	final int LT 	= 1;
//	final int RT 	= 2;
//	
//	int action = IDLE;
//	
//	boolean highlighted = false;
//	boolean selected    = false;
//	
//	public Sprite(int x, int y, int w, int h, String name, String[] pose, int count, String filetype, int delay)
//	{
//		this.x = x;
//		this.y = y;
//		this.w = w;
//		this.h = h;
//		
//		animation = new Animation[pose.length];
//		
//		for(int i = 0; i < animation.length; i++)
//			
//			animation[i] = new Animation(name, pose[i], count, filetype, delay);
//	}
//	
//	
//	
//	public void draw(Graphics pen)
//	{
//   	   if(moving)
//	
//		   pen.drawImage(animation[action].currentImage(), (int)x, (int)y, null);
//		   
//	   else
//
//		   pen.drawImage(animation[action].stillImage(), (int)x, (int)y, null);
//	   
//   	   if(highlighted)   pen.drawRect((int)x, (int)y, w, h);
//   	   
//	   moving = false;
//	}
//	
//	public void setVelocity(double vx, double vy)
//	{
//		this.vx = vx;
//		this.vy = vy;
//	}
//	
//	public void setAccelleration(double ax, double ay)
//	{
//		this.ax = ax;
//		this.ay = ay;
//	}
//	
//	public void jump()
//	{
//		vy = -8;
//	}
//	
//	public void move()
//	{
//		// Moved based on current velocity
//		x += vx;  
//		y += vy;
//		
//		// Adjust Velocity based on current acceleration
//		vx += ax;
//		vy += ay;
//	}
//	
//
//	public void moveLeft(int dx)
//	{
//		x -= dx;
//		
//		moving = true;
//		
//		action = LT;
//	}
//
//	public void moveRight(int dx)
//	{
//		x += dx;
//		
//		moving = true;
//		
//		action = RT;
//	}
//
//	public void goLeft(int dx)
//	{
//		vx = -dx;
//		
//		moving = true;
//		
//		action = LT;
//	}
//
//	public void goRight(int dx)
//	{
//		vx = +dx;
//		
//		moving = true;
//		
//		action = RT;
//	}
//
//	public boolean overlaps(Rect r)
//	{
//		return (x + w >= r.x      ) &&
//			   (x     <= r.x + r.w) &&
//			   (y + h >= r.y      ) &&
//			   (y     <= r.y + r.h);
//	}
//	
//	
//	public void pushUpFrom(Rect platform)
//	{
//		y -= y + h  - platform.y;
//		
//		vx = 0;
//		vy = 0;
//	}
//	
//	public boolean contains(int mx, int my)
//	{
//		return ( mx > x   ) &&   
//			   ( mx < x+w ) && 
//			   ( my > y   ) && 
//			   ( my < y+h );
//	}
//	
//	public void highlight()
//	{
//		highlighted = true;
//	}
//	
//	public void dehighlight()
//	{
//		highlighted = false;
//	}
//	
//	public void select()
//	{
//		selected = true;
//	}
//	
//	public void deselect()
//	{
//		selected = false;
//	}
//	
//	public boolean isSelected()
//	{
//		return selected;
//	}
//}
import java.awt.Graphics;

public class Sprite
{
	// Position P
	double x;
	double y;
	
	
	int w;
	int h;
	

	
	
	
	
	Animation[] animation;
	Animation ability;
	
	boolean moving = false;
	boolean hurting = false;
	boolean punching = false;
	boolean dizzy = false;
	boolean ko = false;
	boolean AbilityReady = true;
	
	final int IDLE = 0;
	final int WALK = 1;
	final int WALKBACK = 2;
	final int PUNCHUP = 3; 
	final int PUNCHLEFT = 4;
	final int PUNCHRIGHT = 5;
	final int BLOCKING = 6;
	final int DIZZY = 7;
	final int HURT = 8;
	final int KO = 9;
	
	int action = IDLE;

	
	public Sprite(int boxerNum, int x, int y, int w, int h, String name, String[] pose, int[] poseCount, String filetype, int delay)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		
		animation = new Animation[pose.length];
		
		for(int i = 0; i < animation.length; i++)
			
			animation[i] = new Animation(name, pose[i], poseCount[i], filetype, delay);
		
		ability = new Animation(boxerNum);
		
	}
	
	
	
	public void draw(Graphics pen)
	{
		if(AbilityReady && !(action == KO))
		{
		pen.drawImage(ability.stillImage(), (int)x+200, (int)y, null);
		}
   	   if(moving)
	
		   pen.drawImage(animation[action].currentImage(), (int)x, (int)y, null);
   	   else if(hurting) {
   		   pen.drawImage(animation[action].Hurting(), (int)x, (int)y, null);  
   	   }
   	   else if(punching) {
		   pen.drawImage(animation[action].Punching(), (int)x, (int)y, null);  
	   }
   	   else if(ko) {
		   pen.drawImage(animation[action].KO(), (int)x, (int)y, null);  
	   }
	   else 
		   pen.drawImage(animation[IDLE].currentImage(), (int)x, (int)y, null);
	   
	}
	
	public boolean getKODone()
	{
		return animation[action].KODone;
	}
	
	public boolean getPunching()
	{
		return animation[action].getPunching();
	}
	
	public void setPunching()
	{
		animation[action].setPunching();
		punching = false;
	}
	
	public boolean getHurting()
	{
		return animation[action].getHurting();
	}
	
	public void setHurting()
	{
		animation[action].setHurting();
		hurting = false;
	}
	
	public void Hurt()
	{
		hurting = true;
		
		action = HURT;
		
	}

	public void bMoveLeft(int dx)
	{
		moving = true;
		
		x -= dx;
		
		action = WALKBACK;
	}
	public void bMoveRight(int dx)
	{
		moving = true;
		
		x += dx;
		
		action = WALK;
	}
	public void bMoveBoundary(int dx) {
		moving = true;
		
		x += dx;
		
		action = WALKBACK;
		
	}
	
	//
	public void rMoveLeft(int dx)
	{
		moving = true;
		
		x -= dx;
		
		action = WALK;
		
	}
	public void rMoveRight(int dx) {
		moving = true;
		
		x += dx;
		
		action = WALKBACK;
		
	}
	public void rMoveBoundary(int dx) {
		moving = true;
		
		x -= dx;
		
		action = WALKBACK;
		
	}
	public void punchLeft() {
		punching = true;
		
		action = PUNCHLEFT;
	}
	
	public void punchRight() {
		punching = true;
		
		action = PUNCHRIGHT;
	}
	
	public void punchUp() {
		punching = true;
		
		action = PUNCHUP;
	}

	public void Block() {
		moving = true;
		
		action = BLOCKING;
	}
	public void Dizzy() {
		action = DIZZY;
		
	}
	public void KO() {
		ko = true;
		
		action = KO;
		
	}


	public boolean overlaps(Rect r)
	{
		return (x + w >= r.x      ) &&
			   (x     <= r.x + r.w) &&
			   (y + h >= r.y      ) &&
			   (y     <= r.y + r.h);
	}
	
}