import lejos.nxt.Motor;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.robotics.navigation.CompassPilot;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;


public class Sholve implements Behavior{

	OpticalDistanceSensor ir;
	CompassPilot pilot;
	
	public Sholve(OpticalDistanceSensor ir, CompassPilot pilot){
		this.ir = ir;
		this.pilot = pilot;
	}
	
	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		return ir.getDistance() < Constants.DISTANCE_SHOLVE && !Variables.turn;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
//		Constants.RIGHT_MOTOR.stop();
//		Constants.LEFT_MOTOR.stop();
		pilot.stop();

//		Constants.RIGHT_MOTOR.backward();
//		Constants.LEFT_MOTOR.backward();
		pilot.backward();
		Delay.msDelay(500);

//		Constants.RIGHT_MOTOR.stop();
//		Constants.LEFT_MOTOR.stop();
		pilot.stop();

		Constants.SHOLVE_MOTOR.setSpeed(Constants.SPEED_SHOLVE);
		Constants.SHOLVE_MOTOR.rotateTo(Constants.ANGLE_SHOLVE);
		Constants.SHOLVE_MOTOR.rotateTo(0);
		Constants.SHOLVE_MOTOR.stop();

//		Constants.RIGHT_MOTOR.forward();
//		Constants.LEFT_MOTOR.forward();
		pilot.forward();
		Delay.msDelay(550);

//		Constants.RIGHT_MOTOR.stop();
//		Constants.LEFT_MOTOR.stop();
		pilot.stop();

//		Constants.RIGHT_MOTOR.backward();
//		Constants.LEFT_MOTOR.backward();
		pilot.backward();
		Delay.msDelay(550);

//		Constants.RIGHT_MOTOR.stop();
//		Constants.LEFT_MOTOR.stop();
		pilot.stop();

		Constants.SHOLVE_MOTOR.setSpeed(Constants.SPEED_SHOLVE);
		Constants.SHOLVE_MOTOR.rotateTo(Constants.ANGLE_SHOLVE);

		Variables.turn = true;
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		
	}

}
