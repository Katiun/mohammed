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
		// TODO Auto-generated method stub
		return mediumInfraRed.getDistance() < Constants.SAFE_MEDIUM_DISTANCE_ANOTHER_AGENT && longInfraRed.getDistance() > Constants.SAFE_LONG_DISTANCE_ANOTHER_AGENT;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
//		pilot.stop();
		Constants.LEFT_MOTOR.stop();
		Constants.RIGHT_MOTOR.stop();
		
		Variables.dispersion = true;
		
		int dist = mediumInfraRed.getDistance();
		while ((!suppressed) && (mediumInfraRed.getDistance() < Constants.SAFE_MEDIUM_DISTANCE_ANOTHER_AGENT)){
			Sound.twoBeeps();
			if (dist > mediumInfraRed.getDistance()){
				pilot.forward();
			}else if (dist < mediumInfraRed.getDistance()){
				pilot.backward();
			}
			dist = mediumInfraRed.getDistance();
			Delay.msDelay(100);
			Thread.yield();
		}
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		suppressed = true;
	}

}
