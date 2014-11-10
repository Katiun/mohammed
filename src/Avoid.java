import lejos.nxt.SensorPort;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.robotics.navigation.CompassPilot;
import lejos.robotics.subsumption.Behavior;



public class Avoid implements Behavior{

	private CompassPilot pilot;
	private OpticalDistanceSensor ir;
	private OpticalDistanceSensor irMax;

	public Avoid(CompassPilot pilot, OpticalDistanceSensor ir, OpticalDistanceSensor irMax) {
		super();
		this.pilot = pilot;
		this.ir = ir;
		this.irMax = irMax;
		
	}

	@Override
	public boolean takeControl() {
		int dist1 = ir.getDistance();
		int dist2 = irMax.getDistance();
		System.out.println("dist1 = " + dist1);
		System.out.println("dist2 = " + dist2);
		return Math.abs(dist1 - dist2) > Constants.DELTA_DISTANCE;
	}

	@Override
	public void action() {
		pilot.stop();
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		
	}
	

}
