import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.addon.CompassHTSensor;
import lejos.util.Delay;


public class Rotate {

	CompassHTSensor cs;

	/**
	 * Hace el giro hacia atrás 180 grados y va para atrás para alinearse
	 * @param motorIn Es el de más adentro en el giro
	 * @param motorOut Es el de más afuera en el giro
	 */
	protected void rotate1(NXTRegulatedMotor motorIn, NXTRegulatedMotor motorOut){
		
		rotate(motorIn, motorOut, Constants.ANGLE_ROTATE1, Constants.SPEED_SLOW_SPIN1, Constants.SPEED_FAST_SPIN);
	}
	
	/**
	 * Hace el giro hacia atrás de 270 grados
	 * @param motorIn Es el de más adentro en el giro
	 * @param motorOut Es el de más afuera en el giro
	 */
	protected void rotate2(NXTRegulatedMotor motorIn, NXTRegulatedMotor motorOut){
		
		motorIn.setSpeed(Constants.SPEED_BACWARD);
		motorOut.setSpeed(Constants.SPEED_BACWARD);
		motorIn.backward();
		motorOut.backward();

		Delay.msDelay(Constants.TIME_BACKWARD);

		rotate(motorIn, motorOut, Constants.ANGLE_ROTATE2, Constants.SPEED_SLOW_SPIN2, Constants.SPEED_FAST_SPIN);
	}

	private void rotate(NXTRegulatedMotor motorIn, NXTRegulatedMotor motorOut, int angleRotate, int slowSpeed, int fastSpeed){

		//Cambio el estado para pasarlo al estado de giro
		Variables.state = Constants.STATE.getNextState(Variables.state.ordinal());
		
		//Aviso al shoter
		Shot.getInstance().shoter();
		
		cs.resetCartesianZero();
		float initialDegrees = cs.getDegreesCartesian();
//		if (initialDegrees > 180){
//			initialDegrees -= 360;
//		}
		float readDegrees = initialDegrees;
		float lastReadDegrees = readDegrees;
		int changeDegreesCount = 0;
		
		//Inicializo motores
		motorIn.stop();
		motorOut.stop();
		motorIn.setSpeed(slowSpeed);
		motorOut.setSpeed(fastSpeed);

		//Giro 1
		motorIn.backward();
		motorOut.backward();
		float originalReadDegrees;
		
		while (changeDegreesCount < angleRotate){
			readDegrees = cs.getDegreesCartesian();
			originalReadDegrees = readDegrees;
			if (readDegrees != lastReadDegrees){
				if (Math.abs(readDegrees - lastReadDegrees) > 180){
					if (readDegrees > lastReadDegrees){
						readDegrees = 360 - readDegrees;
					}else{
						lastReadDegrees = 360 - lastReadDegrees;
					}
					changeDegreesCount += Math.abs(readDegrees + lastReadDegrees);
				}else{
					changeDegreesCount += Math.abs(readDegrees - lastReadDegrees);
				}
				lastReadDegrees = originalReadDegrees;
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

		//Cambio el estado para pasarlo al estado de giro
		Variables.state = Constants.STATE.getNextState(Variables.state.ordinal());
		
		//Aviso al shoter
		Shot.getInstance().shoter();
		
	}
}
