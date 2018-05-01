package sample;

import com.jfoenix.controls.JFXTextArea;
import com.sun.security.ntlm.Server;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ConectionServer implements  Runnable {

    private Socket socket;
    private String message;
    private JFXTextArea textArea;

    ConectionServer(Socket socket, JFXTextArea textArea){
        this.socket = socket;
        this.textArea = textArea;

    }

    @Override
    public void run() {
        try {
            //Conexion creada

            InetAddress inetAddress = socket.getInetAddress();
            //

            DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            System.out.println("before conection loop");
            outputStream.writeUTF(textArea.getText());
            while (true){
                System.out.println("Clients address: "+inetAddress.getHostName()+inetAddress.getHostAddress());
                System.out.println("in conection loop");
                message = '\n'+inputFromClient.readUTF();

                textArea.insertText(textArea.getLength(),message);
                System.out.println("Client says: "+message);
                //outputStream.writeUTF("Hola!!!!!");

                outputStream.writeUTF("3");
                outputStream.flush();
                System.out.println("Text Area says:"+textArea.getText());
               // outputStream.
            }
        }catch (Exception e){
            System.out.println("Something happens");
        }
    }


}
