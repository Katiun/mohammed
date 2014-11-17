import lejos.nxt.addon.CompassHTSensor;
import lejos.util.Delay;

public class RotateLeft extends Rotate{

	public RotateLeft(CompassHTSensor cs) {
		this.cs = cs;
	}

	@Override
	public boolean takeControl() {
		return Variables.turn && 
			(Variables.state == Constants.STATE.FAR_LEFT || Variables.state == Constants.STATE.MEDIUM_LEFT ||
			Variables.state == Constants.STATE.NEARBY_LEFT || Variables.state == Constants.STATE.UP_LEFT);
	}

	@Override
	public void action() {

		switch(Variables.state){
			case FAR_LEFT:
			case MEDIUM_LEFT:
				rotate1(Constants.LEFT_MOTOR, Constants.RIGHT_MOTOR,false);
				break;
				
			case NEARBY_LEFT:
			case UP_LEFT:
				Constants.RIGHT_MOTOR.setSpeed(Constants.SPEED_BACWARD);
				Constants.LEFT_MOTOR.setSpeed(Constants.SPEED_BACWARD);
				Constants.RIGHT_MOTOR.backward();
				Constants.LEFT_MOTOR.backward();

				Delay.msDelay(Constants.TIME_BACKWARD);

				rotate2(Constants.RIGHT_MOTOR, Constants.LEFT_MOTOR,false);
				break;
		}
		Variables.turn = false;

	}

	@Override
	public void suppress() {
	}

}
