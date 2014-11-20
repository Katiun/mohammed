import lejos.nxt.Sound;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;


public class Agregation implements Behavior{

	private DifferentialPilot pilot;
	private OpticalDistanceSensor mediumInfraRed;
	private OpticalDistanceSensor longInfraRed;
	private boolean suppressed;
	
	public Agregation(DifferentialPilot pilot, OpticalDistanceSensor mediumInfraRed, OpticalDistanceSensor longInfraRed){
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
		//System.out.println("action Agr");
		
		//System.out.println("MIR: " + mediumInfraRed.getDistance());
		//System.out.println("LIR: " + longInfraRed.getDistance());
		Constants.stopMotors();
		
		Variables.dispersion = true;
		
		int dist = mediumInfraRed.getDistance();
		while ((!suppressed) && (dist < Constants.SAFE_MEDIUM_DISTANCE_ANOTHER_AGENT)){
			Sound.twoBeeps();
			if (dist > mediumInfraRed.getDistance()){
				pilot.backward();
			}else if ((dist < mediumInfraRed.getDistance()) || (dist < Constants.SAFE_MEDIUM_DISTANCE_ANOTHER_AGENT)){
				pilot.forward();
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
