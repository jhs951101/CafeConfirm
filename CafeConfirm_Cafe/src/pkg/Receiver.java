package pkg;

import java.io.DataInputStream;
import java.net.Socket;

public class Receiver extends Thread {
	
	private CafeConfirmCafe gui;
	private Socket socket;
	private DataInputStream in;
    
    public Receiver(CafeConfirmCafe gui, Socket s){
		try {
			this.gui = gui;
			this.socket = s;
            this.in = new DataInputStream(socket.getInputStream());
        } catch(Exception e) {
            e.printStackTrace();
        }
	}
    
    @Override
	public void run(){
    	String[] orderInfo;  // orderinfo[]: 명령문을 띄어쓰기를 기준으로 분리해서 저장하기 위한 배열
    	
    	try {
            while(in != null) {
            	byte[] bytes = new byte[1024];
                in.read(bytes, 0, 1024);
                String str = new String(bytes, "UTF-8");
                orderInfo = str.split("\n")[0].split(" ");
                 
                if(orderInfo[0].equals("setNumOfPeople")) {
                	
	        	}
	        	else if(orderInfo[0].equals("join")) {
	        		
	        	}
	        	else if(orderInfo[0].equals("login")) {
	        		
	        	}
                else if(orderInfo[0].equals("disconnect")) {
                	break;
                }
             }
            
         } catch(Exception e) {
        	 e.printStackTrace();
         } finally {
             try {
 				socket.close();
 				in.close();
 			} catch (Exception e) {
 				e.printStackTrace();
 			}
         }
	}
}