import lejos.nxt.addon.CompassHTSensor;
import lejos.robotics.subsumption.Behavior;


public class RotateRight extends Rotate implements Behavior{

	
	public RotateRight(CompassHTSensor cs){
		this.cs = cs;
	}
	
	@Override
	public boolean takeControl() {
		return Variables.turn && (Variables.whereToTurn == TURN.RIGHT_1 || Variables.whereToTurn == TURN.RIGHT_2);
	}

	@Override
	public void action() {
		//Seteo variables
		Variables.turn = false;
		cs.resetCartesianZero();
		
		switch(Variables.state){
		case FAR_RIGHT:
		case MEDIUM_RIGHT:
			rotate1(Constants.LEFT_MOTOR, Constants.RIGHT_MOTOR);
			break;
			
		case NEARBY_RIGHT:
		case UP_RIGHT:
			rotate2(Constants.LEFT_MOTOR, Constants.RIGHT_MOTOR);
			break;
	}

	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		
	}

}
