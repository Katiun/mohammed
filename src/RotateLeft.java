import lejos.nxt.addon.CompassHTSensor;
import lejos.robotics.subsumption.Behavior;

public class RotateLeft extends Rotate implements Behavior {

	public RotateLeft(CompassHTSensor cs) {
		this.cs = cs;
	}

	@Override
	public boolean takeControl() {
		return Variables.turn
				&& (Variables.whereToTurn == TURN.LEFT_1 || Variables.whereToTurn == TURN.LEFT_2);
	}

	@Override
	public void action() {
		// Seteo variables
		Variables.turn = false;
		cs.resetCartesianZero();

		switch(Variables.state){
			case FAR_LEFT:
			case MEDIUM_LEFT:
				rotate1(Constants.RIGHT_MOTOR, Constants.LEFT_MOTOR);
				break;
				
			case NEARBY_LEFT:
			case UP_LEFT:
				rotate2(Constants.RIGHT_MOTOR, Constants.LEFT_MOTOR);
				break;
		}

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
