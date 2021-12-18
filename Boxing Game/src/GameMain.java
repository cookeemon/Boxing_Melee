import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

public class GameMain extends GameBase 
{
	//Display half = 1280x720
	//Images sizes = 499 x 489

	//Background
	Rect background 	= new Rect (0, 0, 2560, 1440, Color.DARK_GRAY);
	Rect endscreen		= background;
	
	Rect leftBoundary 	= new Rect (0, 620, 5, 270, Color.BLUE);
	Rect rightBoundary 	= new Rect (2555, 620, 5, 270, Color.RED);
	
	//Boxers
	Boxer bBoxer = new Boxer (660, 475, 1 );
	Boxer rBoxer = new Boxer (1400, 475, 2);

	//Character
	Rect bRect1 = new Rect   (885, 620, 250, 270, Color.BLUE);
	Rect rRect1 = new Rect   (1435, 620, 250, 270, Color.RED);
	
	//Body	
	Rect bRect2 = new Rect   (900, 700, 150, 150, Color.BLUE);
	Rect rRect2 = new Rect   (1510, 700, 150, 150, Color.RED);
	
	//Fists
	Rect bRect3 = new Rect   (bRect2.x+90, bRect2.y+100, 65, 50, Color.BLUE);
	Rect rRect3 = new Rect   (rRect2.x-5, rRect2.y+100, 65, 50, Color.RED);
	
	//Upper cut Cool down
	Countdown bCD = new Countdown();
	boolean bInCD = false;
	Countdown rCD = new Countdown();
	boolean rInCD = false;
	
	//Health Bars
	Rect bHealthOutlineRect = new Rect   (0, 49, 702, 102, Color.BLACK		);
	Rect rHealthOutlineRect = new Rect   (1859, 49, 702, 102, Color.WHITE	);
	Rect bHealthRect 		= new Rect   (0, 50, 700, 100, Color.BLUE		);
	Rect rHealthRect 		= new Rect   (1860, 50, 700, 100, Color.RED		);
	
	//Characters Health
	int bHealth = 700;
	int rHealth = 700;

	boolean GameDone = false;

	

	public void initialize()
	{
		
		
	}
	
	public void inGameLoop()
	{	
//-----------------------------------------BLUE BOXER-------------------------------------------------------------------

		if(bHealth >= 100)
		{
			//Stop blue boxer from going out of screen
			if(bRect1.overlaps(leftBoundary)) {
				if(pressing[_A])
				{
					bBoxer.bMoveBoundary(5);
					bRect1.moveRight(5);
					bRect2.moveRight(5);
					bRect3.moveRight(5);
				}
				else
				{
					bBoxer.action = 0;
				}
			}
			
			//Starts Upper Cut cool down. After approximate 6 seconds the ability is ready to be used.
			if(bInCD)
			{
			bBoxer.AbilityReady = false;
			bCD.Cooldown();
				if(!bCD.inCoolDown)
				{
					bInCD = false;
					bCD.inCoolDown = true;	
					bBoxer.AbilityReady = true;
				}
			}
			
			//Stops blue and red boxer from punching by going through sprite class and stopping the animation.
			if(bBoxer.getPunching())
			{
				bBoxer.setPunching();
			}
			
			//Similar to punching, this stops the boxer hurting animation
			if(bBoxer.getHurting())
			{
				bBoxer.setHurting();
			}
			
			//Boxers going towards each other. Stops if either is overlapping.
			if(pressing[_D])   
			{
				if(!(bRect1.overlaps(rRect1)))
				{
					bBoxer.bMoveRight(5);
					bRect1.moveRight(5);
					bRect2.moveRight(5);
					bRect3.moveRight(5);
				}
			}

			//Moving away
			else if(pressing[_A])
			{
				bBoxer.bMoveLeft(5);
				bRect1.moveLeft(5);
				bRect2.moveLeft(5);
				bRect3.moveLeft(5);
				
			}
			
			//Punching Left
			else if(pressing[_C] && !bBoxer.getHurting())
			{
				bBoxer.punchLeft();
				if(bRect1.overlaps(rRect1))
				{
						bRect3.moveRight(5);
					}
			}
			
			//Punching Right
			else if(pressing[_V] && !bBoxer.getHurting())
			{
				bBoxer.punchRight();
				if(bRect1.overlaps(rRect1))
				{
					bRect3.moveRight(5);
				}
			}
			
			//Upper cut
			else if(pressing[_B] && !bInCD  && !bBoxer.getHurting())
			{
				bBoxer.punchUp();
				if(bRect1.overlaps(rRect1))
				{
					bRect3.moveRight(5);
				}
			}
			
			//Blocking
			else if(pressing[_N])
			{
				bBoxer.Block();
			}
			

			if(bRect3.overlaps(rRect2) && !(rBoxer.action==6))
			{
				rBoxer.Hurt();
				bRect3.setPosition(bRect2.x+90, bRect2.y+100);
				if(bBoxer.action == 3)
				{
					rHealthRect.moveRight(100);
					rHealth -= 100;
					bBoxer.setPunching();
					bInCD = true;
				}
				else
				{
					rHealthRect.moveRight(50);
					rHealth -= 50;
				}
			}

		}
		else if ((bHealth <= 100) && (bHealth > 0))
		{
			bBoxer.Dizzy();
			if(rRect3.overlaps(bRect2))
			{
				rHealth =-100;
			}
		}
		else if((bHealth <= 0)) {
			bBoxer.KO();
		}
		
		
//-----------------------------------------RED BOXER-------------------------------------------------------------------
		if((rHealth > 100))
		{
			//
			if(rRect1.overlaps(rightBoundary)) {
				if(pressing[RT])
				{
					rBoxer.rMoveBoundary(5);
					rRect1.moveLeft(5);
					rRect2.moveLeft(5);
					rRect3.moveLeft(5);
				}
				else
				{
					rBoxer.action = 0;
				}
			}
			
			//
			if(rInCD)
			{
			rBoxer.AbilityReady = false;
			rCD.Cooldown();
				if(!rCD.inCoolDown)
				{
					rInCD = false;
					rCD.inCoolDown = true;	
					rBoxer.AbilityReady = true;
				}
			}
			
			//
			if(rBoxer.getPunching())
			{
				rBoxer.setPunching();
			}
			
			//
			if(rBoxer.getHurting())
			{
				rBoxer.setHurting();
			}
			
			//
			if(pressing[LT])   
			{
				if(!(rRect1.overlaps(bRect1)))
				{
					rBoxer.rMoveLeft(5);
					rRect1.moveLeft(5);
					rRect2.moveLeft(5);
					rRect3.moveLeft(5);
				}
			}
			
			//
			else if(pressing[RT])
			{
				rBoxer.rMoveRight(5);
				rRect1.moveRight(5);
				rRect2.moveRight(5);
				rRect3.moveRight(5);
				
			}
			
			//
			else if(pressing[_9] && !rBoxer.getHurting()) 
			{
				rBoxer.punchRight();
				if(rRect1.overlaps(bRect1))
				{
					rRect3.moveLeft(5);
				}
			}
			
			//
			else if(pressing[_0] && !rBoxer.getHurting())
			{
				rBoxer.punchLeft();
				if(rRect1.overlaps(bRect1))
				{
					rRect3.moveLeft(5);
				}
			}
			
			//
			else if(pressing[MINUS] && !rInCD  && !rBoxer.getHurting())
			{
				rBoxer.punchUp();
				if(rRect1.overlaps(bRect1))
				{
					rRect3.moveLeft(5);
				}
			}
			
			//
			else if (pressing[EQUALS])
			{
				rBoxer.Block();
			}
			
			//
			if(rRect3.overlaps(bRect2) && !(bBoxer.action==6))
			{
				bBoxer.Hurt();
				rRect3.setPosition(rRect2.x-5, rRect2.y+100);
				if(rBoxer.action == 3)
				{
					bHealthRect.moveLeft(100);
					bHealth -= 100;
					rBoxer.setPunching();
					rInCD = true;
				}
				else
				{
					bHealthRect.moveLeft(50);
					bHealth -= 50;
				}
			}
			
		}
		else if ((rHealth <= 100) && (rHealth > 0))
		{
			rBoxer.Dizzy();
			if(bRect3.overlaps(rRect2))
			{
				rHealth =-100;
			}
		}
		else if((rHealth <= 0)) {
			rBoxer.KO();
		}
	}
		
 	
	
	public void paint(Graphics pen)
	{
		background.draw(pen);
		pen.fillRect(background.x, background.y, background.w, background.h);
		
		leftBoundary.draw(pen);
		rightBoundary.draw(pen);
		
		//Red Boxer and hit boxes
		rBoxer.draw(pen);
		rRect1.draw(pen);
		rRect2.draw(pen);
		rRect3.draw(pen);
		
		//Blue Boxer and hit boxes
		bBoxer.draw(pen);
		bRect1.draw(pen);
		bRect2.draw(pen);
		bRect3.draw(pen);
		
		//Health Bars
		bHealthOutlineRect.draw(pen);
		bHealthRect.draw(pen);
		pen.setColor(Color.RED);
		pen.fillRect(rHealthRect.x, rHealthRect.y, rHealthRect.w, rHealthRect.h);
		
		rHealthOutlineRect.draw(pen);
		rHealthRect.draw(pen);
		pen.setColor(Color.BLUE);
		pen.fillRect(bHealthRect.x, bHealthRect.y, bHealthRect.w, bHealthRect.h);
		
		if(bBoxer.action==9)
		{
		pen.setColor(Color.BLUE);
		pen.fillRect(endscreen.x, endscreen.y, endscreen.w, endscreen.h);
		endscreen.draw(pen);
		pen.setColor(Color.RED);
		pen.setFont(new Font("Cooper Black", Font.BOLD, 100));
		pen.drawString("RED BOXER VICTORY", 750, 650);
		}
		else if(rBoxer.action==9) {
			pen.setColor(Color.RED);
			pen.fillRect(endscreen.x, endscreen.y, endscreen.w, endscreen.h);
			endscreen.draw(pen);
			pen.setColor(Color.BLUE);
			pen.setFont(new Font("Cooper Black", Font.BOLD, 100));
			pen.drawString("BLUE BOXER VICTORY", 750, 650);
		}
		
	}
	
	public void keyReleased(KeyEvent e)
	{
		pressing[e.getKeyCode()] = false;
		
		if(e.getKeyCode() == _C || e.getKeyCode() == _V)
		{
			bRect3.setPosition(bRect2.x+90, bRect2.y+100);
			bBoxer.setPunching();
		}
		else if(e.getKeyCode() == _B)
		{
			bRect3.setPosition(bRect2.x+90, bRect2.y+100);
			bBoxer.setPunching();
			bInCD = true;
		
		}
		else if(e.getKeyCode() == _N)
		{
			bBoxer.action = 0;
		}
		if(e.getKeyCode() == _9 || e.getKeyCode() == _0)
		{
			rRect3.setPosition(rRect2.x-5, rRect2.y+100);
			rBoxer.setPunching();
		}
		else if(e.getKeyCode() == MINUS)
		{
			rRect3.setPosition(rRect2.x-5, rRect2.y+100);
			rBoxer.setPunching();
			rInCD = true;
		
		}
		else if(e.getKeyCode() == EQUALS)
		{
			rBoxer.action = 0;
		}
		
		
		bBoxer.moving = false;
		rBoxer.moving = false;
		
		
	}

	

}