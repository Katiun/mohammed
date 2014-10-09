import lejos.robotics.subsumption.Behavior;


public class Shoter implements Behavior{

	@Override
	public boolean takeControl() {
		//System.out.println("Uso el dummy?");
		return true;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		Shot.getInstance().shotHigh();
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		System.out.println("Suprimo SHOT");
	}

}
