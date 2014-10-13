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
	
}
