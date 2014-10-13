import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.CompassHTSensor;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.robotics.navigation.CompassPilot;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;


public class Forward implements Behavior{

	private CompassHTSensor cs;
	private CompassPilot pilot;
	private boolean moving;
	private OpticalDistanceSensor ir;
	private boolean suppressed = false;
	

	public Forward(CompassHTSensor cs, CompassPilot pilot, OpticalDistanceSensor ir) {
		this.cs = cs;
		this.pilot = pilot;
		this.ir = ir;
		moving = false;
		System.out.println("Construyo Forward");
	}

	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
//		System.out.println("Mido Distancia");
//		System.out.println(ir.getDistance());
//		int distance = ir.getDistance();
//		if (distance > 500){
//			System.out.println("tc true");
//		}
//		return distance > 400;
		return true;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
//		System.out.println("Ejecuto FORWARD");
//		if (!moving){
//			pilot.forward();
//			moving = true;
//		}
		suppressed = false;
//		pilot.forward();
//		while( !suppressed ){
//	        Thread.yield();
//		}
//		pilot.stop();
		Constants.LEFT_MOTOR.setSpeed(Constants.SPEED_FORWARD_LEFT_MOTOR);
		Constants.RIGHT_MOTOR.setSpeed(Constants.SPEED_FORWARD_RIGHT_MOTOR);
		while (!suppressed){
			Thread.yield();
		}
		Constants.LEFT_MOTOR.stop();
		Constants.RIGHT_MOTOR.stop();
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
//		System.out.println("Suprimo FORWARD");
//		pilot.stop();
//		moving = false;
		suppressed = true;
	}

}
