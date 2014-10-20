import lejos.robotics.subsumption.Behavior;


public class Shoter implements Behavior{

	
	
	@Override
	public boolean takeControl() {
		return true;
	}

	@Override
	public void action() {
		Shot.getInstance().shotHigh();
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		System.out.println("Suprimo SHOT");
	}

}
