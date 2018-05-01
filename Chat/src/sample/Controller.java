package sample;


import com.jfoenix.controls.JFXTextArea;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import java.net.ServerSocket;
import java.net.Socket;

public class Controller {
    @FXML
    JFXTextArea textAreaChat;
    private Service<Void> backgroundThread;
    private Socket socket;

    public void connect(ActionEvent actionEvent) {

       new Thread(()->{
           try {
               ServerSocket serverSocket = new ServerSocket(8080);
               while(true){
                   socket = serverSocket.accept();
                   Platform.runLater(()->{
                       new Thread(new ConectionServer(socket, textAreaChat)).start();
                   });
               }
           }catch(Exception e){

           }
       }).start();
    }
    public void sendMessage(){

    }
}
