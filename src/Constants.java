import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;


public class Constants {

	public final static NXTRegulatedMotor LEFT_MOTOR = Motor.A;
	public final static NXTRegulatedMotor RIGHT_MOTOR = Motor.B;
	public final static NXTRegulatedMotor SHOVEL_MOTOR = Motor.C;
	
	public final static int ANGLE_SHOVEL = 180;
	public final static int SPEED_SHOVEL = 200;
	public final static int DISTANCE_SHOVEL = 205;
	public final static int DISTANCE_SHOVEL_ERROR = 180;
	
	public final static int ANGLE_ROTATE1 = 180;
	public final static int ANGLE_ROTATE2 = 270;
	public final static int SPEED_FAST_SPIN = 350;
	public final static int SPEED_SLOW_SPIN1 = 120;
	public final static int SPEED_SLOW_SPIN2 = 10;
	public final static int SPEED_BACWARD = 200;
	public final static long TIME_BACKWARD = 400; //1 segundos
//	public final static long TIME_FORWARD = 2000; //3 segundos
	
	public final static int SPEED_FORWARD = 500;
	public final static int SPEED_FORWARD_FAST = 502;

	public final static int SAFE_MEDIUM_DISTANCE_ANOTHER_AGENT = 350;
	public final static int SAFE_LONG_DISTANCE_ANOTHER_AGENT = 500;
	public final static int DELTA_DISTANCE = 100;
	
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
	
	public static void stopMotors(){
		switch (Variables.state){
			case FAR_LEFT:
			case MEDIUM_LEFT:
			case NEARBY_RIGHT:
			case UP_RIGHT:
			case MEDIUM_RIGHT_ROTATING:
			case NEARBY_LEFT_ROTATING:
			case UP_LEFT_ROTATING:
			case FAR_RIGHT_ROTATING:
				LEFT_MOTOR.stop(true);
				RIGHT_MOTOR.stop();
			break;

			case FAR_RIGHT:
			case MEDIUM_RIGHT:
			case NEARBY_LEFT:
			case UP_LEFT:
			case FAR_LEFT_ROTATING:
			case MEDIUM_LEFT_ROTATING:
			case NEARBY_RIGHT_ROTATING:
			case UP_RIGHT_ROTATING:
				RIGHT_MOTOR.stop(true);
				LEFT_MOTOR.stop();
			break;
		}
	}
	
}
