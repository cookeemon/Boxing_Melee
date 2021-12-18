import java.awt.Graphics;

import javax.swing.JLabel;

public class Countdown{
	int delay = 100;
	int current = 1;
	boolean inCoolDown = true;
	
	
	
	public void Cooldown()
	{		
			
			delay--;
			if(delay == 0)
			{	
				
				if(current < 5)  current++;
				
				else
				{
					current = 1;
					inCoolDown = false;
				}
				delay = 100;
				
			}
	}
	
	
}

