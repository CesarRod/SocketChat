package sample;

import com.jfoenix.controls.JFXTextArea;
import javafx.application.Platform;
import javafx.fxml.FXML;



public class ControllerClient {

    @FXML
    JFXTextArea textAreaMessage;
    @FXML
    JFXTextArea textAreaChat;

    Client client;
    boolean isStarted = false;

    public void sendMessage(javafx.event.ActionEvent actionEvent) {
        if(isStarted){
            System.out.println("Text: "+textAreaMessage.getText());
            client.setMessage(textAreaMessage.getText());
            textAreaMessage.clear();
        }
    }

    public void connect(javafx.event.ActionEvent actionEvent) {
        if(!isStarted) {
            isStarted = true;
            client = new Client("Cesar", "localhost", 8080);
            //client.start();
            Platform.runLater(()->{

            new Thread(()->{
                System.out.println("Thread start");
                client.initClient();
                while(true){
                    textAreaChat.setText(client.getServerMessage());
                }
            }

        ).start();
        });
        }
    }


}
