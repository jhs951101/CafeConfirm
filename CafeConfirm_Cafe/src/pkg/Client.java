package pkg;

import java.net.Socket;
import javax.swing.JOptionPane;

public class Client {
	
	public Sender sender;  // sender: Client for Sending
	public Receiver receiver;  // receiver: Client for Receiving
	private CafeConfirmCafe gui;
	
	private String IPAddress;  // IPAddress: IP�ּ�
	private int portNumber;  // portNumber: ��Ʈ��ȣ
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
		// ������ �����ϴ� ����
		try {
            Socket socket = new Socket(IPAddress, portNumber);  // ������ �����ϰ� ������ ������
            JOptionPane.showMessageDialog(gui, "������ ���������� ���� �Ǿ����ϴ�!", "Notice", JOptionPane.INFORMATION_MESSAGE);

            sender = new Sender(gui, socket);
    		receiver = new Receiver(gui, socket);
    		
    		sender.start();
    		receiver.start();  // Thread Start
    		
    		connected = true;
    		
        } catch(Exception e) {
        	JOptionPane.showMessageDialog(gui, "���ῡ �����Ͽ����ϴ�.", "Error...", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            gui.exit();
        }
	}

	public boolean isConnected() {
		return connected;
	}
}