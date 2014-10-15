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
		
		rotate(motorIn, motorOut, Constants.ANGLE_ROTATE1);
//		//Cambio el estado para pasarlo al estado de giro
//		Variables.state = Constants.STATE.getNextState(Variables.state.ordinal());
//		
//		//Aviso al shoter
//		//Shot.getInstance().
//		
//		float initialDegrees = cs.getDegreesCartesian();
//		if (initialDegrees > 180){
//			initialDegrees -= 360;
//		}
//		float readDegrees = initialDegrees;
//		float lastReadDegrees = readDegrees;
//		int changeDegreesCount = 0;
//		
//		//Inicializo motores
//		motorIn.stop();
//		motorOut.stop();
//		motorIn.setSpeed(Constants.SPEED_SLOW_SPIN);
//		motorOut.setSpeed(Constants.SPEED_FAST_SPIN);
//
//		//Giro 1
//		motorIn.forward();
//		motorOut.backward();
//		
//		while (changeDegreesCount < Constants.ANGLE_ROTATE1){
//			readDegrees = cs.getDegreesCartesian();
//			if (readDegrees != lastReadDegrees){
//				lastReadDegrees = readDegrees;
//				changeDegreesCount++;
//			}
//		}
//		motorIn.stop();
//		motorOut.stop();
//
//		//Voy para adelante
//		motorIn.setSpeed(Constants.SPEED_BACWARD);
//		motorOut.setSpeed(Constants.SPEED_BACWARD);
//		motorIn.backward();
//		motorOut.backward();
//
//		Delay.msDelay(Constants.TIME_BACKWARD);
//		
//		motorIn.stop();
//		motorOut.stop();
//
//		Constants.SHOLVE_MOTOR.rotateTo(0);
//		Constants.SHOLVE_MOTOR.stop();
//
//		//Cambio el estado para pasarlo al estado de giro
//		Variables.state = Constants.STATE.getNextState(Variables.state.ordinal());
//		
//		//Aviso al shoter
//		//Shot.getInstance().
		
	}
	
	/**
	 * Hace el giro hacia atrás de 270 grados
	 * @param motorIn Es el de más adentro en el giro
	 * @param motorOut Es el de más afuera en el giro
	 */
	protected void rotate2(NXTRegulatedMotor motorIn, NXTRegulatedMotor motorOut){
		
		rotate(motorIn, motorOut, Constants.ANGLE_ROTATE2);
//		//Cambio el estado para pasarlo al estado de giro
//		Variables.state = Constants.STATE.getNextState(Variables.state.ordinal());
//		
//		//Aviso al shoter
//		//Shot.getInstance().
//		
//		float initialDegrees = cs.getDegreesCartesian();
//		if (initialDegrees > 180){
//			initialDegrees -= 360;
//		}
//		float readDegrees = initialDegrees;
//		
//		//Inicializo motores
//		motorIn.stop();
//		motorOut.stop();
//		motorIn.setSpeed(Constants.SPEED_SLOW_SPIN);
//		motorOut.setSpeed(Constants.SPEED_FAST_SPIN);
//
//		//Giro 1
//		motorIn.forward();
//		motorOut.backward();
//		while (Math.abs(initialDegrees - readDegrees) < Constants.ANGLE_ROTATE2){
//			readDegrees = cs.getDegreesCartesian();
//			if (readDegrees > 180){
//				readDegrees -= 360;
//			}
//			System.out.println("R2 " + readDegrees);
//		}
//		motorIn.stop();
//		motorOut.stop();
//
//		//Voy para adelante
//		motorIn.setSpeed(Constants.SPEED_BACWARD);
//		motorOut.setSpeed(Constants.SPEED_BACWARD);
//		motorIn.backward();
//		motorOut.backward();
//
//		Delay.msDelay(Constants.TIME_BACKWARD);
//		
//		motorIn.stop();
//		motorOut.stop();
//
//		Constants.SHOLVE_MOTOR.rotateTo(0);
//		Constants.SHOLVE_MOTOR.stop();
//
//		//Cambio el estado para pasarlo al estado de giro
//		Variables.state = Constants.STATE.getNextState(Variables.state.ordinal());
//		
//		//Aviso al shoter
//		//Shot.getInstance().
		
	}

	private void rotate(NXTRegulatedMotor motorIn, NXTRegulatedMotor motorOut, int angleRotate){

		//Cambio el estado para pasarlo al estado de giro
		Variables.state = Constants.STATE.getNextState(Variables.state.ordinal());
		
		//Aviso al shoter
		Shot.getInstance().shoter();
		
		float initialDegrees = cs.getDegreesCartesian();
		if (initialDegrees > 180){
			initialDegrees -= 360;
		}
		float readDegrees = initialDegrees;
		float lastReadDegrees = readDegrees;
		int changeDegreesCount = 0;
		
		//Inicializo motores
		motorIn.stop();
		motorOut.stop();
		motorIn.setSpeed(Constants.SPEED_SLOW_SPIN);
		motorOut.setSpeed(Constants.SPEED_FAST_SPIN);

		//Giro 1
		motorIn.forward();
		motorOut.backward();
		
		while (changeDegreesCount < angleRotate){
			readDegrees = cs.getDegreesCartesian();
			if (readDegrees != lastReadDegrees){
				lastReadDegrees = readDegrees;
				changeDegreesCount++;
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
