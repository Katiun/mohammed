import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lejos.nxt.LCD;
import lejos.nxt.comm.NXTConnection;
import lejos.nxt.comm.RS485;
import lejos.nxt.comm.RS485Connection;
import lejos.util.Delay;


public class Shot {

	private static final String NAME = "NXT";
	private static final int STOP = 0;
	private static final int MEDIUM = 1;
	private static final int SLOW = 2;
	private static final int HIGH = 3;
	
	private static Shot instance = null;
	private RS485Connection connection = null;
	private DataInputStream dis = null;
    private DataOutputStream dos = null;

    private void sendInt(int msg) throws IOException {
        dos.writeInt(msg);
        dos.flush();
    }
    
    
	private Shot() {

		RS485Connection connection = RS485.connect(NAME, NXTConnection.PACKET);
		if (connection == null){
			LCD.drawString("Error al", 0, 0);
			LCD.drawString("Conectar", 0, 1);
			Delay.msDelay(5000);
			System.exit(1);
		}
			
		DataInputStream dis = connection.openDataInputStream();
        DataOutputStream dos = connection.openDataOutputStream();

	}
	
	public static Shot getInstance() {
		
		if(instance == null){
			instance = new Shot();
		}
		
		return instance;
	}
	

	public void shotStop() {
        try{
        	sendInt(STOP);
        }catch (IOException ioe){
            LCD.drawString("Stop Exception", 0, 0);
        }
	}
	
	public void shotSlow() {
        try{
        	sendInt(SLOW);
        }catch (IOException ioe){
            LCD.drawString("shotSlow Exception", 0, 0);
        }
	}

	public void shotMedium() {
        try{
        	sendInt(MEDIUM);
        }catch (IOException ioe){
            LCD.drawString("shotMedium Exception", 0, 0);
        }
	}
	
	public void shotHigh() {
        try{
        	sendInt(HIGH);
        }catch (IOException ioe){
            LCD.drawString("shotHigh Exception", 0, 0);
        }
	}
	
	public void close() {
        try{
            dis.close();
            dos.close();
            connection.close();
        }catch (IOException ioe){
        	LCD.clear();
            LCD.drawString("Close Exception", 0, 0);
            LCD.refresh();
        }
	}

}
