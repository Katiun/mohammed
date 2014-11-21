import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;



public class Dispersion implements Behavior{

	private DifferentialPilot pilot;
	private OpticalDistanceSensor mediumInfraRed;
	private OpticalDistanceSensor longInfraRed;

	public Dispersion(DifferentialPilot pilot, OpticalDistanceSensor mediumInfraRed, OpticalDistanceSensor longInfraRed) {
		super();
		this.pilot = pilot;
		this.mediumInfraRed = mediumInfraRed;
		this.longInfraRed = longInfraRed;
		
	}

	@Override
	public boolean takeControl() {
		int dist1 = mediumInfraRed.getDistance();
		int dist2 = longInfraRed.getDistance();
		//Variables.dispersion = true se setea en el action del comportamiento Agregation (solo se ejecuta luego de un Agregation)
		boolean cond1 = Variables.dispersion;
		
		//Si la dist1 > Constants.SAFE_MEDIUM_DISTANCE_ANOTHER_AGENT (caso que el otro agente está a una distancia prudente)
		boolean cond2 = dist1 > Constants.SAFE_MEDIUM_DISTANCE_ANOTHER_AGENT;
		
		//Si la diferencia entre dist1 y dist2 es menor a un delta (caso que el otro robot se movió y con los 2 sensores detecto la pared)
		//o la medida de dist1 es igual a la máxima distancia que el sensor puede medir (caso que el otro robot se movió pero el sensor de media distancia está muy lejos de la pared)
		boolean cond3 = (Math.abs(dist1 - dist2) < Constants.DELTA_DISTANCE || dist1 == Constants.MAXIMUM_RANGE_MEDIUM_INFRARED);
		
		//Si el Agregation demora demasiado toma el control de todas formas
		boolean cond4 = Variables.dispersionTimeout;
		
		return (cond1 && cond2 && cond3) || cond4;
	}

	@Override
	public void action() {
		System.out.println("action Disp");
//		Constants.stopMotors();
		pilot.stop();
		
		Constants.RIGHT_MOTOR.setSpeed(Constants.SPEED_BACWARD);
		Constants.LEFT_MOTOR.setSpeed(Constants.SPEED_BACWARD);
		Constants.RIGHT_MOTOR.backward();
		Constants.LEFT_MOTOR.backward();

		Delay.msDelay(Constants.TIME_BACKWARD / 3);
		Constants.stopMotors();

		//Si es por timeout elijo realizo una acción que trata de sacar al robot de deadlock
		//si sale por otra condición sigue ejecutando como si no hubiera pasado nada
		if ((longInfraRed.getDistance() < Constants.DISTANCE_DISPERSION_ROTATE)){
			/*
			switch(Variables.state){
				case FAR_LEFT:
				case MEDIUM_LEFT:
					rotate(Constants.LEFT_MOTOR, Constants.RIGHT_MOTOR, Constants.ANGLE_ROTATE1, Constants.SPEED_SLOW_SPIN1, Constants.SPEED_FAST_SPIN, false);
					break;
					
				case NEARBY_LEFT:
				case UP_LEFT:
					rotate(Constants.RIGHT_MOTOR, Constants.LEFT_MOTOR, Constants.ANGLE_ROTATE2, Constants.SPEED_SLOW_SPIN2, Constants.SPEED_FAST_SPIN, true);
					break;
					
				case FAR_RIGHT:
				case MEDIUM_RIGHT:
					rotate(Constants.RIGHT_MOTOR, Constants.LEFT_MOTOR, Constants.ANGLE_ROTATE1, Constants.SPEED_SLOW_SPIN1, Constants.SPEED_FAST_SPIN, true);
					break;
					
				case NEARBY_RIGHT:
				case UP_RIGHT:
					rotate(Constants.LEFT_MOTOR, Constants.RIGHT_MOTOR, Constants.ANGLE_ROTATE2, Constants.SPEED_SLOW_SPIN2, Constants.SPEED_FAST_SPIN, false);
					break;
			}
			*/
			switch(Variables.state){
			case UP_LEFT:
			case UP_RIGHT:
				break;
				
				default: 
					Constants.SHOVEL_MOTOR.setSpeed(Constants.SPEED_SHOVEL);
					Constants.SHOVEL_MOTOR.rotateTo(Constants.ANGLE_SHOVEL);

					Variables.turn = true;
					Variables.shovel = false;
					break;
			}
		}
		
		Variables.dispersion = false;
		Variables.dispersionTimeout = false;
	}

	@Override
	public void suppress() {
	}
	
	private void rotate(NXTRegulatedMotor motorIn, NXTRegulatedMotor motorOut, float angleRotate, int slowSpeed, int fastSpeed,boolean derecha){

		//Levanto la pala
		Constants.SHOVEL_MOTOR.setSpeed(Constants.SPEED_SHOVEL);
		Constants.SHOVEL_MOTOR.rotateTo(Constants.ANGLE_SHOVEL);

		float distance = Constants.DISTANCE_ROTATE_1;
		float radius = Constants.RADIUS_ROTATE;
		if (derecha){
			radius *= -1;
		}
		if(angleRotate == Constants.ANGLE_ROTATE2){
			distance = Constants.DISTANCE_ROTATE_2;
		}
		//Cambio el estado para pasarlo al estado de giro
		Variables.state = Constants.STATE.getNextState(Variables.state.ordinal());
		
		//Aviso al shoter
		Shot.getInstance().shoter();
		
		//Giro
		pilot.travelArc(radius, distance);
		pilot.stop();
		
		//Voy para atrás para alinearme
		motorIn.setSpeed(Constants.SPEED_BACWARD);
		motorOut.setSpeed(Constants.SPEED_BACWARD);
		motorIn.backward();
		motorOut.backward();

		Delay.msDelay(Constants.TIME_BACKWARD_ALIGN);
		
		Constants.stopMotors();

		Constants.SHOVEL_MOTOR.rotateTo(0);
		Constants.SHOVEL_MOTOR.stop();

		//Cambio el estado para pasarlo al estado de giro
		Variables.state = Constants.STATE.getNextState(Variables.state.ordinal());
		
		//Aviso al shoter
		Shot.getInstance().shoter();
		
	}


}
