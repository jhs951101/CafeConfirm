package spcompany.sharping.cafeconfirm;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Sender extends Thread {

    private String order;
    private Socket socket;
    private DataOutputStream out;

    public void setSocket(Socket socket){
        try {
            this.socket = socket;
            order = null;
            out = new DataOutputStream(socket.getOutputStream());
        } catch(Exception e) {
            e.printStackTrace();
            closeAll();
        }
    }

    public void turnOnSignal(String order){
        this.order = order;
    }

    public void closeAll() {
        try {
            if(socket != null)
                socket.close();
            if(out != null)
                out.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void run(){
        try {
            while(out != null){
                if(order != null){
                    order += "\n";
                    out.write(order.getBytes("UTF-8"));
                    out.flush();

                    if(order.equals("disconnect"))
                        break;

                    order = null;
                }
            }
        } catch(IOException e1) {
            e1.printStackTrace();
        } finally {
            closeAll();
        }
    }
}
