
public class Variables {

	public static boolean turn = false;
	public static TURN whereToTurn = TURN.LEFT_2;
	public static Constants.STATE state = Constants.STATE.FAR_LEFT;
	
	
}

enum TURN{
	LEFT_1,
	LEFT_2,
	RIGHT_1,
	RIGHT_2
}
