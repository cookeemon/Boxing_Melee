
import java.awt.*;

import javax.swing.ImageIcon;

public class Animation
{
	Image[] image;
	Image abilityIcon;
	
	int r = 0;
	int b = 1;
	
	int delay;
	int delay2 = 12;
	boolean notHurting = false;
	boolean notPunching = false;
	boolean KODone = false;
	
	final int duration;
	
	
	int current = 1;
	
	public Animation(String name, String pose, int poseCount, String filetype, int delay)
	{
		image  = new Image[poseCount];
		

		for(int i = 0; i < poseCount; i++)
			
			image[i] = Toolkit.getDefaultToolkit().getImage(name + pose + "00" + i + "." + filetype);
		
		
		this.delay = delay;
		
		duration   = delay;
		
	}
	
	public Animation(int boxerNum) {
		this.duration = 0;
		if(boxerNum == 1) {
			abilityIcon = Toolkit.getDefaultToolkit().getImage("B01_1.png");
		}
		else if (boxerNum == 2)
			abilityIcon = Toolkit.getDefaultToolkit().getImage("R01_1.png");
	}
	
	public Image stillImage()
	{
		return abilityIcon;
	}

	public Image currentImage()
	{

		
		delay--;
		
		if(delay == 0)
		{
			if(current < image.length-1)  current++;
			
			else                          current = 1;
			
			delay = duration;
		}
		
		return image[current];
	}
	
	
	public Image Hurting() 
	{

		delay--;
		
		if(delay == 0)
		{
			if(current < image.length-1)  current++;
			
			else
			{
				current = 1;
				notHurting = true;
			}
			
			delay = duration;
		}
		
		return image[current];
	}
	
	public boolean getHurting() {
		return notHurting;
	}
	
	public void setHurting() {
		notHurting = false;
	}
	
	
	public Image Punching()
	{
		
		delay2--;
		
		if(delay2 == 0)
		{
			if(current < image.length-1)  current++;
			
			else
			{
				current = 1;
				notPunching = true;
			}
			
			delay2 = 12;
		}
		
		return image[current];
	}
	
	public boolean getPunching() {
		return notPunching;
	}
	public void setPunching() {
		notPunching = false;
	}
	
	public Image KO()
	
	{
		current = 8;
		return image[current];
	}
		
	

}