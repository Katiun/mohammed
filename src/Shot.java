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
    	try{
    		if (dos == null){
    			System.out.println("DOS ES NULL");
    		}
    		System.out.println(msg);
    		Delay.msDelay(4000);
	        dos.writeInt(msg);
	        dos.flush();
    	}catch(Exception ex){
    		System.out.println("SI " + ex.getMessage());
    	}
    }
    
    
	private Shot() {
		try {
//			Delay.msDelay(3000);
	
			RS485Connection connection = RS485.connect(NAME, NXTConnection.PACKET);
//			Delay.msDelay(3000);
			
			if (connection == null){
				System.out.println("Error al conectar");
				Delay.msDelay(5000);
				System.exit(1);
			}
//			Delay.msDelay(3000);
				
			dis = connection.openDataInputStream();
	        dos = connection.openDataOutputStream();
//			Delay.msDelay(3000);
		} catch (Exception e) {
			System.out.println("Error al crear shot");
			Delay.msDelay(2000);
		}

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
            System.out.println("shotHigh Exception");
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
	
	public void shoter(){
		try{
			switch (Variables.state) {
				case FAR_LEFT:
					sendInt((-1) * HIGH);
					break;
				case FAR_RIGHT:
					sendInt(HIGH);
					break;
				case MEDIUM_LEFT:
					sendInt((-1) * MEDIUM);
					break;
				case MEDIUM_RIGHT:
					sendInt(MEDIUM);				
					break;
				case NEARBY_LEFT:
					sendInt((-1) * SLOW);
					break;
				case NEARBY_RIGHT:
					sendInt(SLOW);
					break;
				default:
					sendInt(STOP);
					break;
			}
		} catch(IOException ioe){
			System.out.println("SH " + ioe.getMessage());
		}
	}
}
