package pkg;

import java.net.Socket;
import javax.swing.JOptionPane;

public class Client {
	
	public Sender sender;  // sender: Client for Sending
	public Receiver receiver;  // receiver: Client for Receiving
	private CafeConfirmCafe gui;
	
	private String IPAddress;  // IPAddress: IP주소
	private int portNumber;  // portNumber: 포트번호
	private boolean connected;
	
	public Client(CafeConfirmCafe ui){
		this.gui = ui;
		this.connected = false;
	}
	
	public void ready(String IP, int port) {
		this.IPAddress = IP;
		this.portNumber = port;
	}
	
	public void start(){
		// 서버에 접속하는 과정
		try {
            Socket socket = new Socket(IPAddress, portNumber);  // 소켓을 생성하고 서버와 연결함
            JOptionPane.showMessageDialog(gui, "서버에 정상적으로 접속 되었습니다!", "Notice", JOptionPane.INFORMATION_MESSAGE);

            sender = new Sender(gui, socket);
    		receiver = new Receiver(gui, socket);
    		
    		sender.start();
    		receiver.start();  // Thread Start
    		
    		connected = true;
    		
        } catch(Exception e) {
        	JOptionPane.showMessageDialog(gui, "연결에 실패하였습니다.", "Error...", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            gui.exit();
        }
	}

	public boolean isConnected() {
		return connected;
	}
}