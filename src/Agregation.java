import lejos.nxt.Sound;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;
import lejos.util.Timer;
import lejos.util.TimerListener;


public class Agregation implements Behavior{

	private DifferentialPilot pilot;
	private OpticalDistanceSensor mediumInfraRed;
	private OpticalDistanceSensor longInfraRed;
	private boolean suppressed;
	
	public Agregation(DifferentialPilot pilot, OpticalDistanceSensor mediumInfraRed, OpticalDistanceSensor longInfraRed){
		this.pilot = pilot;
		this.mediumInfraRed = mediumInfraRed;
		this.longInfraRed = longInfraRed;
		suppressed = false;
	}
	
	@Override
	public boolean takeControl() {
		return mediumInfraRed.getDistance() < Constants.SAFE_MEDIUM_DISTANCE_ANOTHER_AGENT && longInfraRed.getDistance() > Constants.SAFE_LONG_DISTANCE_ANOTHER_AGENT &&
				!Variables.turn && !Variables.shovel;
	}

	@Override
	public void action() {
		System.out.println("action Agr");
		
		//Para el robot
		Constants.stopMotors();
		
		//Habilita a que se pueda ejecutar el comportamiento Dispersion
		Variables.dispersion = true;
		
		//Inicio un timer para no quedar en deadlock
		Timer timer = new Timer(Constants.TIMEOUT_AGREGATION, new TimerListener() {
			@Override
			public void timedOut() {
				Variables.dispersionTimeout = true;
			}
		});
		
		try{
			timer.start();
		}catch(Exception ex){ }
		
		//Se mueve hacia adelante o hacia atrás segun el otro robot
		int dist = mediumInfraRed.getDistance();
		while ((!suppressed) && (dist < Constants.SAFE_DISTANCE_ANOTHER_AGENT)){
			Sound.twoBeeps(); //Solo para saber que estoy en Agregation
			if (dist > mediumInfraRed.getDistance()){
				pilot.backward(); //con el pilot backward va para adelante
			}else if ((dist < mediumInfraRed.getDistance()) || (dist < Constants.SAFE_MEDIUM_DISTANCE_ANOTHER_AGENT)){
				pilot.forward(); //con el pilot forward va para atrás
			}
			dist = mediumInfraRed.getDistance();
			Delay.msDelay(100);
			Thread.yield();
		}
		
		try{
			timer.stop();
		}catch(Exception ex){ }
	}

	@Override
	public void suppress() {
		suppressed = true;
	}

}
