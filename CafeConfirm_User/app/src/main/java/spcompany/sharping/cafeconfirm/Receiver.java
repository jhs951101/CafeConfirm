package spcompany.sharping.cafeconfirm;

import android.os.Handler;
import android.os.Message;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receiver extends Thread  {

    private Socket socket;
    private DataInputStream in;

    public Handler loadMapSuccess;

    public void setSocket(Socket socket){
        try {
            this.socket = socket;
            loadMapSuccess = null;
            in = new DataInputStream(socket.getInputStream());
        } catch(Exception e) {
            e.printStackTrace();
            closeAll();
        }
    }

    public void closeAll() {
        try {
            if(socket != null)
                socket.close();
            if(in != null)
                in.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void run(){
        try {
            String[] orderinfo;  // orderinfo[]: 명령문을 띄어쓰기를 기준으로 분리해서 저장하기 위한 배열

            while(in != null) {
                byte[] bytes = new byte[1024];
                in.read(bytes, 0, 1024);
                String str = new String(bytes, "UTF-8");
                orderinfo = str.split("\n")[0].split(" ");

                if(orderinfo[0].equals("loadMap")){
                    int length = Integer.parseInt(orderinfo[1]);
                    int i = 2;

                    for(int n=1; n<=length; n++) {
                        Message message = new Message();
                        message.obj = orderinfo[i] + " " + orderinfo[i+1] + " " + orderinfo[i+2] + " " + orderinfo[i+3] + " " + orderinfo[i+4];
                        System.out.println(message.obj);
                        loadMapSuccess.sendMessage(message);
                        i += 5;
                    }
                }
                else if(orderinfo[0].equals("disconnect")){
                    break;
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
    }
}
