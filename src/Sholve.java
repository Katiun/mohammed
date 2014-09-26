import lejos.nxt.Motor;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.robotics.navigation.CompassPilot;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;


public class Sholve implements Behavior{

	private final int SPEED = 200;
	private final int ANGLE = 200;
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
		return state == 0 && ir.getDistance() < 220;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		state++;
		pilot.stop();
		pilot.backward();
		Delay.msDelay(500);
		pilot.stop();
		Motor.C.setSpeed(SPEED);
		Motor.C.rotateTo(ANGLE);
		Motor.C.rotateTo(0);
		Motor.C.stop();
		pilot.forward();
		Delay.msDelay(500);
		pilot.stop();
		pilot.backward();
		Delay.msDelay(500);
		pilot.stop();
		Motor.C.setSpeed(SPEED);
		Motor.C.rotateTo(ANGLE);
		Motor.C.rotateTo(0);
		Motor.C.stop();
		Variables.turn = true;
		state = 0;
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		
	}

}
