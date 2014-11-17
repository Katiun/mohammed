import lejos.nxt.addon.CompassHTSensor;
import lejos.nxt.addon.NXTLineLeader.Command;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;


public class RotateRight extends Rotate{

	
	public RotateRight(CompassHTSensor cs){
		this.cs = cs;
	}
	
	@Override
	public boolean takeControl() {
		return Variables.turn && 
				(Variables.state == Constants.STATE.FAR_RIGHT || Variables.state == Constants.STATE.MEDIUM_RIGHT ||
				Variables.state == Constants.STATE.NEARBY_RIGHT || Variables.state == Constants.STATE.UP_RIGHT);
	}

	@Override
	public void action() {
		
		switch(Variables.state){
			case FAR_RIGHT:
			case MEDIUM_RIGHT:
				rotate1(Constants.RIGHT_MOTOR, Constants.LEFT_MOTOR,true);
				break;
				
			case NEARBY_RIGHT:
			case UP_RIGHT:
				Constants.LEFT_MOTOR.setSpeed(Constants.SPEED_BACWARD);
				Constants.RIGHT_MOTOR.setSpeed(Constants.SPEED_BACWARD);
				Constants.LEFT_MOTOR.backward();
				Constants.RIGHT_MOTOR.backward();

				Delay.msDelay(Constants.TIME_BACKWARD);

				rotate2(Constants.LEFT_MOTOR, Constants.RIGHT_MOTOR,true);
				break;
		}
		Variables.turn = false;

	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		
	}

}
