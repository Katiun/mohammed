import lejos.nxt.addon.CompassHTSensor;
import lejos.robotics.subsumption.Behavior;


public class RotateRight extends Rotate implements Behavior{

	
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
		//Seteo variables
		cs.resetCartesianZero();
		
		switch(Variables.state){
			case FAR_RIGHT:
			case MEDIUM_RIGHT:
				rotate1(Constants.RIGHT_MOTOR, Constants.LEFT_MOTOR);
				break;
				
			case NEARBY_RIGHT:
			case UP_RIGHT:
				rotate2(Constants.RIGHT_MOTOR, Constants.LEFT_MOTOR);
				break;
		}
		Variables.turn = false;

	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		
	}

}
