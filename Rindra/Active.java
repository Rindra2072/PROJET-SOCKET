
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Active implements Runnable{

    Socket socket;
    static ArrayList<Active> actives = new ArrayList<>();
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;
    String id;
    public Active(Socket socket){
        this.socket = socket;
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            actives.add(this);
        } catch (IOException e) {
       
            e.printStackTrace();
        }
    }   
    @Override
    public void run(){
        while(socket.isConnected()){
            try{
                Object c =  objectInputStream.readObject();
                System.out.println("Ito");
                System.out.println(c);
                retour(c);
            }
            catch(Exception e){

            }
        }
    }
    public void retour(Object j){
        for (Active clientHandler : actives) {
            try {
                if (!clientHandler.equals(this)) {
                    clientHandler.objectOutputStream.writeObject(j);
                    clientHandler.objectOutputStream.reset();
                    clientHandler.objectOutputStream.flush();
                }

            } catch (Exception e) {
                
              
            }
        }
    }
}
