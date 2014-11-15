import lejos.nxt.SensorPort;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.robotics.navigation.CompassPilot;
import lejos.robotics.subsumption.Behavior;



public class Dispersion implements Behavior{

	private CompassPilot pilot;
	private OpticalDistanceSensor mediumInfraRed;
	private OpticalDistanceSensor longInfraRed;

	public Dispersion(CompassPilot pilot, OpticalDistanceSensor mediumInfraRed, OpticalDistanceSensor longInfraRed) {
		super();
		this.pilot = pilot;
		this.mediumInfraRed = mediumInfraRed;
		this.longInfraRed = longInfraRed;
		
	}

	@Override
	public boolean takeControl() {
		int dist1 = mediumInfraRed.getDistance();
		int dist2 = longInfraRed.getDistance();
		return Variables.dispersion && dist1 > Constants.SAFE_MEDIUM_DISTANCE_ANOTHER_AGENT && Math.abs(dist1 - dist2) < Constants.DELTA_DISTANCE;
	}

	@Override
	public void action() {
		System.out.println("action Disp");
		Constants.stopMotors();
		Variables.dispersion = false;
	}

	@Override
	public void suppress() {
	}
	

}
