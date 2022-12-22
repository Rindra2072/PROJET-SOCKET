
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class GameServer {

    private ServerSocket serverSocket;

    public GameServer(){
        try{
        Scanner sc=new Scanner(System.in);
        System.out.println("Entrer port");   
        int port=sc.nextInt(); 
        this.serverSocket = new ServerSocket(port);
        System.out.println(port);
        }
        catch(Exception e){
          e.printStackTrace();
        }
    }

    public void startServer(){
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("A new client has connected!");
                Active clientHandler = new Active(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (Exception e) {
        }
    }

    public void closeServerSocket(){
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException{
        System.out.println("Demmarage du serveur");
        GameServer start = new GameServer();
        start.startServer();

    }
}
