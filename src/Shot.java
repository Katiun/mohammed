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
	private final int STOP = 0;
	private final int MEDIUM = 1;
	private final int SLOW = 2;
	private final int HIGH = 3;
	
	private static Shot instance = null;
	private RS485Connection connection = null;
    private DataOutputStream dos = null;

    private void sendInt(int msg){
    	
    	try{
	        dos.writeInt(msg);
	        dos.flush();
    	}catch(Exception ex){
        	System.out.println(ex);
        }
    }
    
    private void connect(){
		try {
			connection = RS485.connect(NAME, NXTConnection.PACKET);
			if (connection == null){
				System.out.println("Error al conectar");
				Delay.msDelay(5000);
				System.exit(1);
			}
	        dos = connection.openDataOutputStream();
		} catch (Exception e) {
			System.out.println("Error al crear shot");
			Delay.msDelay(2000);
		}
    	
    }
    
	private Shot() {
		connect();
	}
	
	public static Shot getInstance() {
		
		if(instance == null){
			instance = new Shot();
		}
		
		return instance;
	}
	

	
	public void close() {
        try{
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
			int msg = 0;
			switch (Variables.state) {
				case FAR_LEFT:
					msg = -3;
					break;
				case FAR_RIGHT:
					msg = 3;
					break;
				case MEDIUM_LEFT:
					msg = -1;
					break;
				case MEDIUM_RIGHT:
					msg = 1;				
					break;
				case NEARBY_LEFT:
					msg = -2;
					break;
				case NEARBY_RIGHT:
					msg = 2;
					break;
				default:
					msg = 0;
					break;
			}
	        dos.writeInt(msg);
	        dos.flush();
		} catch(Exception ioe){
			System.out.println("Error al enviar datos");
		}
	}
}
