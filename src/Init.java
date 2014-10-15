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
		Variables.turn = false;
		Variables.whereToTurn = TURN.LEFT_2;
		Variables.state = Constants.STATE.FAR_LEFT;
		Shot.getInstance();
		
		Delay.msDelay(5000);
		
		CompassHTSensor cs = new CompassHTSensor(SensorPort.S3);
		CompassPilot pilot = new CompassPilot(cs, 56f, 136f, Motor.A, Motor.B);
		OpticalDistanceSensor ir = new OpticalDistanceSensor(SensorPort.S2);
		
		Forward forward = new Forward(cs, pilot, ir);
		RotateLeft rotateLeft = new RotateLeft(cs);
		RotateRight rotateRight = new RotateRight(cs);
		Sholve sholve = new Sholve(ir, pilot);
		
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
	
}
