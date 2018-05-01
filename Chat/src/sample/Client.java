package sample;



import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client {

    private DataOutputStream toServer = null;
    private DataInputStream fromServer = null;
    private String nickname = "";
    private String host;
    private int port;
    private String message ="";
    private String serverMessage;

    public Client(String nickname, String host, int port){
        this.nickname = nickname;
        this.host = host;
        this.port = port;
        this.message= nickname+" Se ha conectado";
    }

    public void initClient(){
        try{
            Socket socket = new Socket(host,port);
            toServer = new DataOutputStream(socket.getOutputStream());
            fromServer = new DataInputStream(socket.getInputStream());
            //Thread.sleep(5000);
            System.out.println("before while");
            //serverMessage = fromServer.readUTF();
            //System.out.println("Server says: "+serverMessage);
            //toServer.writeUTF("QLO ");

            while(true){
                System.out.println("Message Inside Loop: "+ message);
                if(!message.isEmpty()){
                    toServer.writeUTF(message);
                    toServer.flush();
                    System.out.println("Message Sent");
                    message=null;
                }

                //serverMessage = fromServer.readUTF();
                System.out.println("Server: "+fromServer.readUTF());
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void setMessage(String message){
        this.message = message;
        System.out.println("Message changed");
    }

    public String getServerMessage(){
        return serverMessage;
    }


}
