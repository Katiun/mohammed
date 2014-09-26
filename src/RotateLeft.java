import lejos.nxt.addon.CompassHTSensor;
import lejos.robotics.navigation.CompassPilot;
import lejos.robotics.subsumption.Behavior;


public class RotateLeft implements Behavior{

	CompassPilot pilot;
	CompassHTSensor cs;
	
	public RotateLeft(CompassPilot pilot, CompassHTSensor cs){
		this.pilot = pilot;
		this.cs = cs;
	}
	
	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		return Variables.turn;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		System.out.println("ACT " + Variables.turn);
		Variables.turn = false;
		//positivo a la izquierda, negativo a la derecha
		pilot.resetCartesianZero();
//		float degrees = cs.getDegreesCartesian();
//		while (degrees != 90){
//			System.out.println("" + degrees);
//			degrees = cs.getDegreesCartesian();
//		}
		pilot.rotate(90);
//		pilot.stop();
		System.out.println("ACT FIN");
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		
	}

}
