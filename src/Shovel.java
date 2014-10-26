import java.util.Date;

import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.robotics.navigation.CompassPilot;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;


public class Shovel implements Behavior{

	OpticalDistanceSensor ir;
	CompassPilot pilot;
	private boolean enterErrorZone;
	private int second;
	int distance;
	
	public Shovel(OpticalDistanceSensor ir, CompassPilot pilot){
		this.ir = ir;
		this.pilot = pilot;
		enterErrorZone = false;
	}
	
	@Override
	public boolean takeControl() {
//		Date readTime = new Date();
//		if (ir.getDistance() < Constants.DISTANCE_SHOVEL + Constants.DISTANCE_SHOVEL_ERROR){
//			if (!enterErrorZone){
//				System.out.println("Inicio zona error");
//				distance = ir.getDistance();
//				System.out.println("Dist " + distance);
//				second = readTime.getHours() * 60 * readTime.getMinutes() * 60 + readTime.getSeconds();
//				System.out.println("Seg " + second);
//				enterErrorZone = true;
//			}else{
//				int lSecond = readTime.getHours() * 60 * readTime.getMinutes() * 60 + readTime.getSeconds();
//				if (ir.getDistance() == distance && Math.abs(lSecond - second) > 2){
//					enterErrorZone = false;
//					return true;
//				}else if (ir.getDistance() != distance){
//					enterErrorZone = false;
//				}
//			}
//		}
		return ir.getDistance() < Constants.DISTANCE_SHOVEL && !Variables.turn;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
//		Constants.RIGHT_MOTOR.stop();
//		Constants.LEFT_MOTOR.stop();
		pilot.stop();

//		Constants.RIGHT_MOTOR.backward();
//		Constants.LEFT_MOTOR.backward();
		pilot.backward();
		Delay.msDelay(500);

//		Constants.RIGHT_MOTOR.stop();
//		Constants.LEFT_MOTOR.stop();
		pilot.stop();

		Constants.SHOVEL_MOTOR.setSpeed(Constants.SPEED_SHOVEL);
		Constants.SHOVEL_MOTOR.rotateTo(Constants.ANGLE_SHOVEL);
		Constants.SHOVEL_MOTOR.rotateTo(0);
		Constants.SHOVEL_MOTOR.stop();

//		Constants.RIGHT_MOTOR.forward();
//		Constants.LEFT_MOTOR.forward();
		pilot.forward();
		Delay.msDelay(550);

//		Constants.RIGHT_MOTOR.stop();
//		Constants.LEFT_MOTOR.stop();
		pilot.stop();

//		Constants.RIGHT_MOTOR.backward();
//		Constants.LEFT_MOTOR.backward();
		pilot.backward();
		Delay.msDelay(550);

//		Constants.RIGHT_MOTOR.stop();
//		Constants.LEFT_MOTOR.stop();
		pilot.stop();

		Constants.SHOVEL_MOTOR.setSpeed(Constants.SPEED_SHOVEL);
		Constants.SHOVEL_MOTOR.rotateTo(Constants.ANGLE_SHOVEL);

		Variables.turn = true;
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		
	}

}
