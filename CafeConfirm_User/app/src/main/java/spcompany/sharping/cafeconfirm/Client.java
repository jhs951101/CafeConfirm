package spcompany.sharping.cafeconfirm;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

public class Client extends Thread {

    private String IPAddress;  // IPAddress: IP주소
    private int portNumber;  // portNumber: 포트번호

    public static Client client;
    public Sender sender;  // sender: Client for Sending
    public Receiver receiver;  // receiver: Client for Receiving

    public Client(){
        sender = new Sender();
        receiver = new Receiver();
    }

    public void setIPInfo(String IPAddress, int portNumber){
        this.IPAddress = IPAddress;
        this.portNumber = portNumber;
    }

    @Override
    public void run(){
        // 서버에 접속하는 과정
        Socket socket = null;
        boolean success = true;

        try {
            socket = new Socket(IPAddress, portNumber);  // 소켓을 생성하고 서버와 연결함

            sender.setSocket(socket);
            receiver.setSocket(socket);

            sender.start();
            receiver.start();  // Thread Start

        } catch(ConnectException ce) {
            ce.printStackTrace();
            success = false;
        } catch(Exception e) {
            e.printStackTrace();
            success = false;
        }

        if(!success){
            try {
                if(socket != null)
                    socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}