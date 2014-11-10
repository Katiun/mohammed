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
		OpticalDistanceSensor irMax = new OpticalDistanceSensor(SensorPort.S1);
		
		Forward forward = new Forward(cs, pilot, ir);
		RotateLeft rotateLeft = new RotateLeft(cs);
		RotateRight rotateRight = new RotateRight(cs);
		Shovel sholve = new Shovel(ir, pilot);
		Avoid avoid = new Avoid(pilot, ir, irMax);
		
		Behavior[] behaviorList = {forward, sholve, rotateLeft, rotateRight, avoid};
		
		Arbitrator arbitor = new Arbitrator(behaviorList);
		arbitor.start();
	}

	private static void init(){

		Variables.turn = false;
		Variables.state = Constants.STATE.FAR_LEFT;
		
		Delay.msDelay(2000);

		Constants.SHOVEL_MOTOR.setSpeed(Constants.SPEED_SHOVEL);
		Constants.SHOVEL_MOTOR.rotateTo(-Constants.ANGLE_SHOVEL);
		Constants.SHOVEL_MOTOR.stop();
		Constants.SHOVEL_MOTOR.resetTachoCount();

		Delay.msDelay(3000);

		Shot.getInstance();
		
	}
}
