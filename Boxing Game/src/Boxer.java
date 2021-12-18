
public class Boxer extends Sprite
{
	static String[] pose = {"_Idle_", "_Walk_", "_WalkBack_", "_PunchUp_", "_PunchLeft_", "_PunchRight_", "_Blocking_", "_Dizzy_", "_Hurt_", "_KO_"};
	static int[] poseCount = {10, 	  10, 		10, 		  5, 		   4, 			  4, 			  10,  			8, 		   3, 			10};

	public Boxer(int x, int y, int boxerNum)
	{
		super(boxerNum, x, y, 499, 489, "__Boxing0" + boxerNum, pose, poseCount, "png", 7);
	}


}