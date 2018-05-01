package sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSimple {
    private Socket socket;
    private ServerSocket server;
    private String message;
    private DataInputStream input;
    private DataOutputStream output;

    ServerSimple(int port ) throws IOException {
        server = new ServerSocket(port);

    }
     public void initServer(){
        try{
            socket = server.accept();
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            System.out.println(input.readUTF());
            output.writeUTF("message received");
        }catch(Exception e){

        }


    }

    public static void main(String[] args) throws IOException {
        ServerSimple s = new ServerSimple(8080);
        s.initServer();

    }
}
