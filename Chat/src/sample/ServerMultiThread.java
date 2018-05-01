package sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMultiThread  implements  Runnable{
    private Socket socket;
   // private ServerSocket server;
    private String message;
    private DataInputStream input;
    private DataOutputStream output;

    ServerMultiThread(Socket socket ) throws IOException {
        this.socket = socket;

    }


    @Override
    public void run() {
        try {
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            while(true){
                System.out.println("Cliente dice:"+input.readUTF());
                output.writeUTF("ok");
            }


        }catch (Exception e){

        }

    }

    public static void main(String[] args) {

        new Thread(
                () ->{
                    try{
                        ServerSocket sc = new ServerSocket(8080);
                        System.out.println("Server start");
                        while (true){
                            Socket socket = sc.accept();
                            new Thread( new ServerMultiThread(socket)).start();
                        }

                    }catch(Exception e){

                    }

                }
        ).start();
    }
}
