import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.addon.CompassHTSensor;
import lejos.robotics.navigation.CompassPilot;
import lejos.util.Delay;


public class Rotate {

	CompassPilot pilot;
	CompassHTSensor cs;

	/**
	 * Hace el primer giro hacia un lado
	 * @param motorOut Es el de más afuera en el giro
	 * @param motorIn Es el de más adentro en el giro
	 */
	protected void rotate1(NXTRegulatedMotor motorIn, NXTRegulatedMotor motorOut){
	}
	
	/**
	 * Hace el primer giro hacia un lado
	 * @param motorOut Es el de más afuera en el giro
	 * @param motorIn Es el de más adentro en el giro
	 */
	protected void rotate2(NXTRegulatedMotor motorOut, NXTRegulatedMotor motorIn){

		float initialDegrees = cs.getDegreesCartesian();
		if (initialDegrees > 180){
			initialDegrees -= 360;
		}
		float readDegrees = initialDegrees;
		
		//Inicializo motores
		motorIn.stop();
		motorOut.stop();
		motorIn.setSpeed(Constants.SPEED_SLOW_SPIN);
		motorOut.setSpeed(Constants.SPEED_FAST_SPIN);

		//Giro 1
		motorIn.forward();
		motorOut.backward();
		while (Math.abs(initialDegrees - readDegrees) < Constants.ANGLE_ROTATE){
			readDegrees = cs.getDegreesCartesian();
			if (readDegrees > 180){
				readDegrees -= 360;
			}
		}
		motorIn.stop();
		motorOut.stop();

		//Voy para adelante
		motorIn.setSpeed(Constants.SPEED_BACWARD);
		motorOut.setSpeed(Constants.SPEED_BACWARD);
		motorIn.backward();
		motorOut.backward();

		Delay.msDelay(Constants.TIME_BACKWARD);
		
		motorIn.stop();
		motorOut.stop();

		Constants.SHOLVE_MOTOR.rotateTo(0);
		Constants.SHOLVE_MOTOR.stop();
	}

	protected void rotate3(NXTRegulatedMotor motorOut, NXTRegulatedMotor motorIn){

		float initialDegrees = cs.getDegreesCartesian();
		if (initialDegrees > 180){
			initialDegrees -= 360;
		}
		float readDegrees = initialDegrees;
		
		//Inicializo motores
		motorIn.stop();
		motorOut.stop();
		motorIn.setSpeed(Constants.SPEED_SLOW_SPIN);
		motorOut.setSpeed(Constants.SPEED_FAST_SPIN);

		//Giro
		motorIn.backward();
		motorOut.forward();
		while (Math.abs(initialDegrees - readDegrees) < Constants.ANGLE_ROTATE){
			readDegrees = cs.getDegreesCartesian();
			if (readDegrees > 180){
				readDegrees -= 360;
			}
		}
		motorIn.stop();
		motorOut.stop();

		//Voy para atras para alinear el robot
		motorIn.setSpeed(Constants.SPEED_SLOW_SPIN);
		motorOut.setSpeed(Constants.SPEED_SLOW_SPIN);
		motorIn.backward();
		motorOut.backward();

		Delay.msDelay(Constants.TIME_BACKWARD);
		
		motorIn.stop();
		motorOut.stop();

		//Avanzo para el próximo giro
		motorIn.backward();
		motorOut.backward();

		Delay.msDelay(Constants.TIME_BACKWARD);
		
		motorIn.stop();
		motorOut.stop();

	}
}
