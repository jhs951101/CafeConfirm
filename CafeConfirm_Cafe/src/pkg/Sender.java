package pkg;

import java.io.DataOutputStream;
import java.net.Socket;

public class Sender extends Thread {
	
	private CafeConfirmCafe gui;
	private Socket socket;
	private DataOutputStream out;
	private volatile String message;
	
	public Sender(CafeConfirmCafe gui, Socket s){
		try {
			this.gui = gui;
			this.socket = s;
			this.message = null;
            this.out = new DataOutputStream(socket.getOutputStream());
        } catch(Exception e) {
            e.printStackTrace();
        }
	}
	
	@Override
	public void run(){
		try {
            while (out != null) {
            	if(message != null) {
            		message += "\n";
            		out.write(message.getBytes("UTF-8"));
                    out.flush();
                    
            		if(message.equals("disconnect"))
            			break;
            		
            		message = null;
            	}
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
				socket.close();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
		
    	/*
    	PythonExecuter pythonExecuter = new PythonExecuter();
    	String[] arguments = new String[1];
		arguments[0] = "C:/Projects/JAVA Projects/CafeConfirmClient/Learning_Data_Face.xml";
    	 
    	String str = "setNumOfPeople " + CafeConfirmCafe.CafeName + " ";
    	str += pythonExecuter.executePython("C:/Projects/JAVA Projects/CafeConfirmCafe", "FaceRecignition.py", arguments);
        str += "\n";
        out.write(str.getBytes("UTF-8"));
        out.flush();
        
        Thread.sleep(5000);
        */
		
		/*
		gui.button.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			try{
    				String str = "msg";
            		str += "\n";
                	out.write(str.getBytes("UTF-8"));
                	out.flush();
    			} catch(Exception e2){
    				e2.printStackTrace();
    			}
    		}
        });
        */
		
		/*
		try {
            while(out != null){
                if(message != null){
                    message += "\n";
                    out.write(message.getBytes("UTF-8"));
                    out.flush();

                    if(message.equals("disconnect"))
                        break;

                    message = null;
                }
            }
        } catch(IOException e) {
            e1.printStackTrace();
        } finally {
            closeAll();
        }
        */
    }
	
	public void sendMessage(String msg) {
		this.message = msg;
	}
}