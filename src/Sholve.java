import lejos.nxt.Motor;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.robotics.navigation.CompassPilot;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;


public class Sholve implements Behavior{

	private int state;
	OpticalDistanceSensor ir;
	CompassPilot pilot;
	
	public Sholve(OpticalDistanceSensor ir, CompassPilot pilot){
		this.ir = ir;
		this.pilot = pilot;
		state = 0;
	}
	
	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		return state == 0 && ir.getDistance() < Constants.DISTANCE_SHOLVE;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		state++;
		pilot.stop();
		pilot.backward();
		Delay.msDelay(500);
		pilot.stop();
		Constants.SHOLVE_MOTOR.setSpeed(Constants.SPEED_SHOLVE);
		Constants.SHOLVE_MOTOR.rotateTo(Constants.ANGLE_SHOLVE);
		Constants.SHOLVE_MOTOR.rotateTo(0);
		Constants.SHOLVE_MOTOR.stop();
		pilot.forward();
		Delay.msDelay(550);
		pilot.stop();
		pilot.backward();
		Delay.msDelay(550);
		pilot.stop();
		Constants.SHOLVE_MOTOR.setSpeed(Constants.SPEED_SHOLVE);
		Constants.SHOLVE_MOTOR.rotateTo(Constants.ANGLE_SHOLVE);

		Variables.turn = true;
		state = 0;
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		
	}

}
