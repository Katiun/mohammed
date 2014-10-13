import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;


public class Constants {

	public final static NXTRegulatedMotor LEFT_MOTOR = Motor.A;
	public final static NXTRegulatedMotor RIGHT_MOTOR = Motor.B;
	public final static NXTRegulatedMotor SHOLVE_MOTOR = Motor.C;
	
	public final static int ANGLE_SHOLVE = 190;
	public final static int SPEED_SHOLVE = 200;
	public final static int DISTANCE_SHOLVE = 200;
	
	public final static int SPEED_FORWARD_LEFT_MOTOR = 730;
	public final static int SPEED_FORWARD_RIGHT_MOTOR = 740;
	
	public final static int ANGLE_ROTATE = 85;
	public final static int SPEED_FAST_SPIN = 400;
	public final static int SPEED_SLOW_SPIN = 0;
	public final static int SPEED_BACWARD = 200;
	public final static long TIME_BACKWARD = 2000; //3 segundos
	public final static long TIME_FORWARD = 2000; //3 segundos
	
	public enum STATE{
		FAR_LEFT,
		FAR_LEFT_ROTATING,
		MEDIUM_RIGHT,
		MEDIUM_RIGHT_ROTATING,
		NEARBY_LEFT,
		NEARBY_LEFT_ROTATING,
		UP_LEFT,
		UP_LEFT_ROTATING,
		FAR_RIGHT,
		FAR_RIGHT_ROTATING,
		MEDIUM_LEFT,
		MEDIUM_LEFT_ROTATING,
		NEARBY_RIGHT,
		NEARBY_RIGHT_ROTATING,
		UP_RIGHT,
		UP_RIGHT_ROTATING
		;
		
		public static STATE getNextState(int actualState){
			return values()[(actualState + 1) % values().length];
		}
	}
	
}