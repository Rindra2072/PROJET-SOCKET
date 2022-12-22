
import java.net.Socket;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;


public class Joueur  {
    Paddle paddle;
    Socket socket;
    boolean accepted=true;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream  objectInputStream;
    int port =1111;
    String ip="localhost";
    int id;
    GamePanel p=new GamePanel();

    public Joueur(){
        paddle=new Paddle();

    }
     public Joueur(Boolean move) throws Exception{
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your id");
        id=scanner.nextInt();

        System.out.println("Enter port");
        port = scanner.nextInt();

    
        if(id == 1){
            paddle=new Paddle(0,(p.GAME_HEIGHT/2)-(p.PADDLE_HEIGHT/2),p.PADDLE_WIDTH,p.PADDLE_HEIGHT,1);
        }
        else if(id != 1){
            paddle=new Paddle(p.GAME_WIDTH-p.PADDLE_WIDTH,(p.GAME_HEIGHT/2)-(p.PADDLE_HEIGHT/2),p.PADDLE_WIDTH,p.PADDLE_HEIGHT,2);
        }

        ArrayList<Joueur> joueurs = new ArrayList<>();
        joueurs.add(this);
        
        this.socket = new Socket(ip, port);
        System.out.println("Vita");


        this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream()); 
        this.objectInputStream = new ObjectInputStream(socket.getInputStream());
    }
   
    // public void setName(){
    //     Scanner sc=new Scanner(System.in);
    //     System.out.println("Entrer nom");
    //     this.carte.setName(sc.nextLine());   
    // }

    


	
    public void sendObject(Object c){
        // new Thread(new Runnable(){
        //     @Override
        // public void run() {
        System.out.println("Envoi en cours...");
        try{   
          //  while(true){
            objectOutputStream.writeObject(c);
            System.out.println("Envoi reussi");
            objectOutputStream.reset();
            objectOutputStream.flush();
            //}
        }
        catch(Exception e){
            e.printStackTrace();
        }
        //}
        //}).start();
    }

    public void receiveObject(Joueur j1){
        new Thread(new Runnable(){
            @Override
            public void run() {
                
                Joueur j2 = new Joueur();
                Ball ball;
                if (j1.id==1) {
                ball=new Ball((p.GAME_WIDTH/2)-(p.BALL_DIAMETER/2),(p.GAME_HEIGHT/2)-(p.BALL_DIAMETER/2),p.BALL_DIAMETER,p.BALL_DIAMETER);

                }
                else ball=new Ball();

                GamePanel gm=new GamePanel(j1,j2,ball);

                GameFrame frame=new GameFrame(gm);
                // try {
                //     if (j1.id==2){ 
                //         gm.ball=(Ball) objectInputStream.readObject();
                //     }
                // }catch(Exception e){e.printStackTrace();}
                if (j1.id==2) {
                    gm.score.player1=0;
                    gm.score.player2=0;
                }
                while(true){
                    try {          
                         if (j1.id==2){
                          Object obj2= objectInputStream.readObject();
                           //if(obj2.getClass()==gm.ball.getClass()) 
                          gm.ball=(Ball) obj2;
                        }
                         Object obj= objectInputStream.readObject();
                        // if(obj.getClass()==j2.paddle.getClass())                   
                        j2.paddle= (Paddle) obj;

                        gm.j2=j2;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


}