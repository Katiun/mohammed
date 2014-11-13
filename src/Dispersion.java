import lejos.nxt.SensorPort;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.robotics.navigation.CompassPilot;
import lejos.robotics.subsumption.Behavior;



public class Dispersion implements Behavior{

	private CompassPilot pilot;
	private OpticalDistanceSensor mediumInfraRed;
	private OpticalDistanceSensor irMax;

	public Dispersion(CompassPilot pilot, OpticalDistanceSensor mediumInfraRed, OpticalDistanceSensor longInfraRed) {
		super();
		this.pilot = pilot;
		this.mediumInfraRed = mediumInfraRed;
		this.irMax = longInfraRed;
		
	}

	@Override
	public boolean takeControl() {
		int dist1 = mediumInfraRed.getDistance();
		int dist2 = irMax.getDistance();
		return Variables.dispersion && dist1 > Constants.SAFE_MEDIUM_DISTANCE_ANOTHER_AGENT && Math.abs(dist1 - dist2) < Constants.DELTA_DISTANCE;
	}

	@Override
	public void action() {
		pilot.stop();
		Variables.dispersion = false;
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		
	}
	

}
