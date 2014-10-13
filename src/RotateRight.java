import lejos.nxt.Motor;
import lejos.nxt.addon.CompassHTSensor;
import lejos.robotics.navigation.CompassPilot;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;


public class RotateRight extends Rotate implements Behavior{

	
	public RotateRight(CompassPilot pilot, CompassHTSensor cs){
		this.pilot = pilot;
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
		
		//Cambio el estado para el proximo giro
		if (Variables.whereToTurn == TURN.RIGHT_1){
			Variables.whereToTurn = TURN.RIGHT_2;
		}else{
			Variables.whereToTurn = TURN.LEFT_1;
		}
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		
	}

}
