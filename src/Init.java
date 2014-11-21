import lejos.nxt.Button;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.CompassHTSensor;
import lejos.nxt.addon.GyroSensor;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.robotics.navigation.CompassPilot;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;


public class Init {

	public static void main(String[] args){
		
//		CompassHTSensor cs = new CompassHTSensor(SensorPort.S3);
//		GyroSensor gs = new GyroSensor(SensorPort.S3);
		//CompassPilot pilot = new CompassPilot(cs, 56f, 136f, Constants.LEFT_MOTOR, Constants.RIGHT_MOTOR);
		DifferentialPilot pilot = new DifferentialPilot(1.5f, 5.8f, Constants.LEFT_MOTOR, Constants.RIGHT_MOTOR, true);
		OpticalDistanceSensor mediumInfraRed = new OpticalDistanceSensor(SensorPort.S2);
		OpticalDistanceSensor longInfraRed = new OpticalDistanceSensor(SensorPort.S1);
		
		//Inicializacion de variables
		init();
		
//		Forward forward = new Forward(cs, pilot, mediumInfraRed);
//		RotateLeft rotateLeft = new RotateLeft(cs, gs);
//		RotateRight rotateRight = new RotateRight(cs, gs);
//		Shovel sholve = new Shovel(mediumInfraRed, pilot);
		Forward forward = new Forward(pilot, mediumInfraRed);
		RotateLeft rotateLeft = new RotateLeft(pilot);
		RotateRight rotateRight = new RotateRight(pilot);
		Shovel sholve = new Shovel(mediumInfraRed, pilot);
		Agregation agregation = new Agregation(pilot, mediumInfraRed, longInfraRed);
		Dispersion dispersion = new Dispersion(pilot, mediumInfraRed, longInfraRed);

		Behavior[] behaviorList = {forward, sholve, rotateLeft, rotateRight, agregation, dispersion};
//		Behavior[] behaviorList  {forward, sholve, rotateLeft, rotateRight};
		
		Arbitrator arbitor = new Arbitrator(behaviorList);
		arbitor.start();
	}

//	private static void init(GyroSensor gs){
//		gs.recalibrateOffset();
//		
//		Variables.GYROSCOPE_OFFSET = gs.readValue();
	private static void init(){

		Variables.turn = false;
		Variables.state = Constants.STATE.FAR_LEFT;
		
//		Delay.msDelay(2000);

		Constants.SHOVEL_MOTOR.setSpeed(Constants.SPEED_SHOVEL);
		Constants.SHOVEL_MOTOR.rotateTo(-Constants.ANGLE_SHOVEL);
		Constants.SHOVEL_MOTOR.stop();
		Constants.SHOVEL_MOTOR.resetTachoCount();

		Delay.msDelay(3000);

		Shot.getInstance();
		
	}
}
