import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.CompassHTSensor;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.robotics.navigation.CompassPilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;


public class Init {

	public static void main(String[] args){
		
		//Inicializacion de variables
		init();
		
		CompassHTSensor cs = new CompassHTSensor(SensorPort.S3);
		CompassPilot pilot = new CompassPilot(cs, 56f, 136f, Constants.LEFT_MOTOR, Constants.RIGHT_MOTOR);
		OpticalDistanceSensor ir = new OpticalDistanceSensor(SensorPort.S2);
		
		Forward forward = new Forward(cs, pilot, ir);
		RotateLeft rotateLeft = new RotateLeft(cs);
		RotateRight rotateRight = new RotateRight(cs);
		Sholve sholve = new Sholve(ir, pilot);
//		Shoter shoter = new Shoter();
		
		Behavior[] behaviorList = {forward, sholve, rotateLeft, rotateRight};
		
		Arbitrator arbitor = new Arbitrator(behaviorList);
		arbitor.start();
//		Motor.A.setSpeed(740);
//		Motor.B.setSpeed(740);
//		
//		Motor.A.forward();
//		Motor.B.forward();
//		
//		Delay.msDelay(10000);
//		
//		Motor.A.stop();
//		Motor.B.stop();
	}

	private static void init(){

		Variables.turn = false;
		Variables.whereToTurn = TURN.LEFT_2;
		Variables.state = Constants.STATE.FAR_LEFT;
		
		Delay.msDelay(2000);

		Constants.SHOLVE_MOTOR.setSpeed(Constants.SPEED_SHOLVE);
		Constants.SHOLVE_MOTOR.rotateTo(-Constants.ANGLE_SHOLVE);
		Constants.SHOLVE_MOTOR.stop();
		Constants.SHOLVE_MOTOR.resetTachoCount();

		Delay.msDelay(3000);

		Shot.getInstance();
		
	}
}
