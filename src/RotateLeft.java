import lejos.nxt.addon.CompassHTSensor;
import lejos.robotics.subsumption.Behavior;

public class RotateLeft extends Rotate implements Behavior {

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
		// Seteo variables
		cs.resetCartesianZero();

		switch(Variables.state){
			case FAR_LEFT:
			case MEDIUM_LEFT:
				rotate1(Constants.LEFT_MOTOR, Constants.RIGHT_MOTOR);
				break;
				
			case NEARBY_LEFT:
			case UP_LEFT:
				rotate2(Constants.RIGHT_MOTOR, Constants.LEFT_MOTOR);
				break;
		}
		Variables.turn = false;

		// //Cambio el estado para el proximo giro
		// if (Variables.whereToTurn == TURN.LEFT_1){
		// Variables.whereToTurn = TURN.LEFT_2;
		// }else{
		// Variables.whereToTurn = TURN.RIGHT_1;
		// }
	}

	@Override
	public void suppress() {
	}

}
