import lejos.robotics.subsumption.Behavior;

public class Dummy implements Behavior{

	@Override
	public boolean takeControl() {
		//System.out.println("Uso el dummy?");
		return false;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		System.out.println("Ejecuto DUMMY");
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		System.out.println("Suprimo DUMMY");
	}

}
