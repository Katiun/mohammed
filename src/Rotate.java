import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.Sound;
import lejos.nxt.addon.CompassHTSensor;
import lejos.nxt.addon.GyroSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;


public abstract class Rotate implements Behavior {

	CompassHTSensor cs;
	GyroSensor gs;
	DifferentialPilot pilot;

	/**
	 * Hace el giro hacia atrás 180 grados y va para atrás para alinearse
	 * @param motorIn Es el de más adentro en el giro
	 * @param motorOut Es el de más afuera en el giro
	 */
	protected void rotate1(NXTRegulatedMotor motorIn, NXTRegulatedMotor motorOut,boolean derecha){
		rotate(motorIn, motorOut, Constants.ANGLE_ROTATE1, Constants.SPEED_SLOW_SPIN1, Constants.SPEED_FAST_SPIN,derecha);
	}
	
	/**
	 * Hace el giro hacia atrás de 270 grados
	 * @param motorIn Es el de más adentro en el giro
	 * @param motorOut Es el de más afuera en el giro
	 */
	protected void rotate2(NXTRegulatedMotor motorIn, NXTRegulatedMotor motorOut,boolean derecha){
		rotate(motorIn, motorOut, Constants.ANGLE_ROTATE2, Constants.SPEED_SLOW_SPIN2, Constants.SPEED_FAST_SPIN, derecha);
	}

	private void rotate(NXTRegulatedMotor motorIn, NXTRegulatedMotor motorOut, float angleRotate, int slowSpeed, int fastSpeed,boolean derecha){
		float distance = Constants.DISTANCE_ROTATE_1;
		float radius = Constants.RADIUS_ROTATE;
		if (derecha){
			//distance *= -1;
			radius *= -1;
//			angleRotate = angleRotate * -1;
		}
		if(angleRotate == Constants.ANGLE_ROTATE2){
			distance = Constants.DISTANCE_ROTATE_2;
//			Sound.twoBeeps();
		}
		//Cambio el estado para pasarlo al estado de giro
		Variables.state = Constants.STATE.getNextState(Variables.state.ordinal());
		
		//Aviso al shoter
		Shot.getInstance().shoter();
		
		pilot.travelArc(radius, distance);
		
//		cs.resetCartesianZero();
//		float initialDegrees = cs.getDegreesCartesian();
//
//		System.out.println("initDeg = " + initialDegrees);
//		float readDegrees = initialDegrees;
//		float lastReadDegrees = readDegrees;
//		int changeDegreesCount = 0;
//		
//		//Inicializo motores
////		motorIn.stop();
////		motorOut.stop();
//		Constants.stopMotors();
//		motorIn.setSpeed(slowSpeed);
//		motorOut.setSpeed(fastSpeed);
//
//		//Giro 1
//		motorIn.backward();
//		motorOut.backward();
//		float originalReadDegrees;
//		//CAMBIOS
//		/*
//		while (changeDegreesCount < 20.0){
//			readDegrees = cs.getDegreesCartesian();
//			originalReadDegrees = readDegrees;
////			System.out.println("LEO = " + readDegrees);
////			System.out.println("LEO = " + readDegrees);
////			System.out.println("VOY  = " + changeDegreesCount);
////			System.out.println("VOY  = " + changeDegreesCount);
//			
//			if (readDegrees != lastReadDegrees){//si cambió el valor
//				if (Math.abs(readDegrees - lastReadDegrees) > 180){//si la diferencia entre la ultima vez que lei y y la actual es mayor a 180
//					if (readDegrees > lastReadDegrees){
//						readDegrees = 360 - readDegrees;
//					}else{
//						lastReadDegrees = 360 - lastReadDegrees;
//					}
//					changeDegreesCount += Math.abs(readDegrees + lastReadDegrees);
//				}else{
//					changeDegreesCount += Math.abs(readDegrees - lastReadDegrees);
//				}
//				lastReadDegrees = originalReadDegrees;
//			}
//			
//		}
//		*/
//		Delay.msDelay(800);
////		System.out.println("ESTA EN  = " + cs.getDegreesCartesian());
////		System.out.println("ESTA EN  = " + cs.getDegreesCartesian());
////		System.out.println("ESTA EN  = " + cs.getDegreesCartesian());
////		Delay.msDelay(200);
////		float error = 20.0f;
////		readDegrees = cs.getDegreesCartesian();
////		if(derecha){
////			//initialDegrees =0;
////			while(cs.getDegreesCartesian()<(angleRotate-error)){
////				while(cs.getDegreesCartesian()<(angleRotate-error)){
////					while(cs.getDegreesCartesian()<(angleRotate-error)){
////						while(cs.getDegreesCartesian()<(angleRotate-error)){
////							System.out.println("ENTRO ");
////						}
////					}
////				}
////			}
////			System.out.println("ESTA EN  = " + cs.getDegreesCartesian());
////		}else{
////			//initialDegrees=360;
////			while(cs.getDegreesCartesian()>(360.0-angleRotate+error)){
////				while(cs.getDegreesCartesian()>(360.0-angleRotate+error)){
////					while(cs.getDegreesCartesian()>(360.0-angleRotate+error)){
////						while(cs.getDegreesCartesian()>(360.0-angleRotate+error)){
////							System.out.println("ENTRO ");
////						}
////					}
////				}
////			}
////			System.out.println("2 ESTA EN  = " + cs.getDegreesCartesian());
////		}
//
//		int degree = Math.abs(gs.readValue() - Variables.GYROSCOPE_OFFSET);
//		while (degree < angleRotate){
//			degree += Math.abs(gs.readValue() - Variables.GYROSCOPE_OFFSET);
//		}
//		
////		motorIn.stop();
////		motorOut.stop();
//		Constants.stopMotors();

		//pilot.rotate(angleRotate);
		
		pilot.stop();
		
		//Voy para adelante
		motorIn.setSpeed(Constants.SPEED_BACWARD);
		motorOut.setSpeed(Constants.SPEED_BACWARD);
		motorIn.backward();
		motorOut.backward();

		Delay.msDelay(Constants.TIME_BACKWARD_ALIGN);
		
//		motorIn.stop();
//		motorOut.stop();
		Constants.stopMotors();

		Constants.SHOVEL_MOTOR.rotateTo(0);
		Constants.SHOVEL_MOTOR.stop();

		//Cambio el estado para pasarlo al estado de giro
		Variables.state = Constants.STATE.getNextState(Variables.state.ordinal());
		
		//System.out.println("readDeg = " + readDegrees);
//		System.out.println("FIN  = " + cs.getDegreesCartesian()+ " HACIA  = " + angleRotate+ " DESDE = "+initialDegrees+ " PASANDO* = "+readDegrees);
		//System.out.println("hasta   = " + angleRotate);
		
		//Aviso al shoter
		Shot.getInstance().shoter();
		//Delay.msDelay(45678);
		
	}

	@Override
	public abstract boolean takeControl();

	@Override
	public abstract void action();

	@Override
	public abstract void suppress();

}
