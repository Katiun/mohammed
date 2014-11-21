import lejos.nxt.addon.CompassHTSensor;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.robotics.navigation.CompassPilot;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;


public class Forward implements Behavior{

	private CompassHTSensor cs;
//	private CompassPilot pilot;
	private DifferentialPilot pilot;
	private OpticalDistanceSensor mediumInfraRed;
	private boolean suppressed = false;
	

//	public Forward(CompassHTSensor cs, CompassPilot pilot, OpticalDistanceSensor mediumInfraRed) {
	public Forward(DifferentialPilot pilot, OpticalDistanceSensor mediumInfraRed) {
//		this.cs = cs;
		this.pilot = pilot;
		this.mediumInfraRed = mediumInfraRed;
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
		if ((Variables.state == Constants.STATE.FAR_LEFT) || (Variables.state == Constants.STATE.NEARBY_RIGHT)
				|| (Variables.state == Constants.STATE.UP_RIGHT)){
			Variables.SPEED_FORWARD_LEFT_MOTOR = Constants.SPEED_FORWARD_FAST;
			Variables.SPEED_FORWARD_RIGHT_MOTOR = Constants.SPEED_FORWARD;
		}else if ((Variables.state == Constants.STATE.NEARBY_LEFT) || (Variables.state == Constants.STATE.UP_LEFT)
				|| (Variables.state == Constants.STATE.FAR_RIGHT)){
			Variables.SPEED_FORWARD_LEFT_MOTOR = Constants.SPEED_FORWARD;
			Variables.SPEED_FORWARD_RIGHT_MOTOR = Constants.SPEED_FORWARD_FAST;
		}else if (Variables.state == Constants.STATE.MEDIUM_RIGHT){
			Variables.SPEED_FORWARD_LEFT_MOTOR = Constants.SPEED_FORWARD;
			Variables.SPEED_FORWARD_RIGHT_MOTOR = Constants.SPEED_FORWARD + 3;
		}else if (Variables.state == Constants.STATE.MEDIUM_LEFT){
			Variables.SPEED_FORWARD_LEFT_MOTOR = Constants.SPEED_FORWARD + 3;
			Variables.SPEED_FORWARD_RIGHT_MOTOR = Constants.SPEED_FORWARD;
		}else{
			Variables.SPEED_FORWARD_LEFT_MOTOR = Constants.SPEED_FORWARD;
			Variables.SPEED_FORWARD_RIGHT_MOTOR = Constants.SPEED_FORWARD;
		}

		Constants.LEFT_MOTOR.setSpeed(Variables.SPEED_FORWARD_LEFT_MOTOR);
		Constants.RIGHT_MOTOR.setSpeed(Variables.SPEED_FORWARD_RIGHT_MOTOR);

		if (Variables.SPEED_FORWARD_LEFT_MOTOR > Variables.SPEED_FORWARD_RIGHT_MOTOR){
			Constants.RIGHT_MOTOR.forward();
			Constants.LEFT_MOTOR.forward();
		}else{
			Constants.LEFT_MOTOR.forward();
			Constants.RIGHT_MOTOR.forward();
		}

		while (!suppressed){
			Thread.yield();
		}
//		Constants.LEFT_MOTOR.stop();
//		Constants.RIGHT_MOTOR.stop();
		Constants.stopMotors();

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
