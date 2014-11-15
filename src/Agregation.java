import lejos.nxt.Sound;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.robotics.navigation.CompassPilot;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;


public class Agregation implements Behavior{

	private CompassPilot pilot;
	private OpticalDistanceSensor mediumInfraRed;
	private OpticalDistanceSensor longInfraRed;
	private boolean suppressed;
	
	public Agregation(CompassPilot pilot, OpticalDistanceSensor mediumInfraRed, OpticalDistanceSensor longInfraRed){
		this.pilot = pilot;
		this.mediumInfraRed = mediumInfraRed;
		this.longInfraRed = longInfraRed;
		suppressed = false;
	}
	
	@Override
	public boolean takeControl() {
		return mediumInfraRed.getDistance() < Constants.SAFE_MEDIUM_DISTANCE_ANOTHER_AGENT && longInfraRed.getDistance() > Constants.SAFE_LONG_DISTANCE_ANOTHER_AGENT;
	}

	@Override
	public void action() {
		System.out.println("action Agr");
		
		System.out.println("MIR: " + mediumInfraRed.getDistance());
		System.out.println("LIR: " + longInfraRed.getDistance());
		Constants.stopMotors();
		
		Variables.dispersion = true;
		
		int dist = mediumInfraRed.getDistance();
		while ((!suppressed) && (dist < Constants.SAFE_MEDIUM_DISTANCE_ANOTHER_AGENT)){
			Sound.twoBeeps();
			if (dist > mediumInfraRed.getDistance()){
				pilot.forward();
			}else if ((dist < mediumInfraRed.getDistance()) || (dist < Constants.SAFE_MEDIUM_DISTANCE_ANOTHER_AGENT)){
				pilot.backward();
			}
			dist = mediumInfraRed.getDistance();
			Delay.msDelay(100);
			Thread.yield();
		}
	}

	@Override
	public void suppress() {
		suppressed = true;
	}

}
