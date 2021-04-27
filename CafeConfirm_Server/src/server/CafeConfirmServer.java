package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;

import database.DBOperation;
import model.CafeInfo;

public class CafeConfirmServer extends Thread {
	
	private static final int portNumber = 1234;
	
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private DBOperation dbService;
	
	public CafeConfirmServer(Socket socket) {
		try {
			this.socket = socket;
			dbService = new DBOperation();
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
			closeAll();
		}
	}
	
	public void run() {
		try {
			String order = "";
			String[] orderInfo;
			
			boolean available = true;
			String success = "success";
			String fail = "fail";
			
			while(available) {
				byte[] bytes = new byte[1024];
	        	in.read(bytes, 0, 1024);
	        	String str = new String(bytes, "UTF-8");
	        	str = str.split("\n")[0];
	        	orderInfo = str.split(" ");
	        	
	        	//System.out.println(str);
	        	
	        	if(orderInfo[0].equals("setNumOfPeople")) {
	        		dbService.update(new CafeInfo(orderInfo[1], orderInfo[2]));
	        		order = orderInfo[0] + " " + success;
	        	}
	        	else if(orderInfo[0].equals("loadMap")) {
	        		CafeInfo[] cafeInfos = dbService.selectMulti();
	        		order = orderInfo[0] + " " + cafeInfos.length;
	        		
	        		for(int i=0; i<cafeInfos.length; i++) {
	        			order += (" " + cafeInfos[i].getCafeName() + " " + cafeInfos[i].getNumOfPeople()
	        					+ " " + cafeInfos[i].getMaxNum() + " " + cafeInfos[i].getLatitude() + " " + cafeInfos[i].getLongitude());
	        		}
	        	}
	        	else if(orderInfo[0].equals("join")) {
	        		dbService.insert(new CafeInfo(orderInfo[1], orderInfo[2], orderInfo[3], orderInfo[4], orderInfo[5]));
	        		order = orderInfo[0] + " " + success;
	        	}
	        	else if(orderInfo[0].equals("login")) {
	        		CafeInfo cafeInfo = dbService.selectSingle(orderInfo[1]);
	        		
	        		if(cafeInfo == null)
	        			order = orderInfo[0] + " " + fail;
	        		else if(!cafeInfo.getPassword().equals(orderInfo[2]))
	        			order = orderInfo[0] + " " + fail;
	        		else
	        			order = orderInfo[0] + " " + success;
	        	}
				else if(orderInfo[0].equals("disconnect")) {
					order = orderInfo[0];
					available = false;
	        	}
	        	
	        	order += "\n";
        		out.write(order.getBytes("UTF-8"));
    			out.flush();
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			closeAll();
			System.out.println("\nA User Quit...");
        }
	}
	
	public void closeAll() {
		try {
			if(socket != null)
				socket.close();
			if(in != null)
				in.close();
			if(out != null)
				out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		ServerSocket serverSocket = null;
		
		try {
			System.out.println("Server Started");
			System.out.println("IP Address: " + Inet4Address.getLocalHost().getHostAddress());
			System.out.println("Port Number: " + portNumber);
			
			serverSocket = new ServerSocket(portNumber);
			
			while(true) {
				Socket socket = serverSocket.accept();
				CafeConfirmServer m = new CafeConfirmServer(socket);
				m.start();
				System.out.println("\nA User Accessed!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(serverSocket != null);
					serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
