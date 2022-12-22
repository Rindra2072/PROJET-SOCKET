import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class GamePanel extends JPanel implements Runnable{

	static final int GAME_WIDTH=700;
	static final int GAME_HEIGHT=(int) (GAME_WIDTH* (0.5555));
	static final Dimension SCREEN_SIZE= new Dimension(GAME_WIDTH,GAME_HEIGHT);
	static final int BALL_DIAMETER = 10;
	static final int PADDLE_WIDTH  = 15;
	static final int PADDLE_HEIGHT =70;
    Thread gamethread;
    Image image;
    Graphics graphics;
    Random random;

    // Paddle paddle1;
    // Paddle paddle2;
    Joueur j1;
    Joueur j2; 
    Ball ball;
    Score score;
    boolean accepted=false;
    int PAUSE=1;

    GamePanel(){}
    GamePanel(Joueur j1 ,Joueur j2 ,Ball ball ){
    	this.j1=j1;
    	this.j2=j2;
    	//newPaddles();
    	//newBall();
      this.ball=ball;
    	score=new Score(GAME_WIDTH,GAME_HEIGHT);
    	setFocusable(true);
    	addKeyListener(new AL());
    	setPreferredSize(SCREEN_SIZE);

    	gamethread=new Thread(this);
    	gamethread.start();
  
    }

    // public void newBall(){
    //       ball=new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),(GAME_HEIGHT/2)-(BALL_DIAMETER/2),BALL_DIAMETER,BALL_DIAMETER);
    // }
    // public void newPaddles(){
    // 	paddle1=new Paddle(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
    // 	paddle2=new Paddle(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);

    // }
    public void paint(Graphics g){
    	image=createImage(getWidth(),getHeight());
    	graphics =image.getGraphics();
    	draw(graphics);
    	g.drawImage(image,0,0,this);
    }
    public void draw(Graphics g){
    	j1.paddle.draw(g);
    	if (j2.paddle!=null) {
    	j2.paddle.draw(g);	
    	}
      ball.draw(g);
      score.draw(g);
    	 
    }
    public void move(){
    	//j1.paddle.move();
    	//if (j2.paddle!=null) {
    	//j2.paddle.move();	
    	//}
      if(PAUSE==1){
        
        if (j1.id==1) {
           j1.sendObject(ball);  
        }
        ball.move();
        j1.sendObject(j1.paddle);   
      }
    	
    }
    public void checkCollision(){
      //ball bounds of top && window
      if (ball.y<=0) 
          ball.setYDirection(-ball.yVelocity);
      if (ball.y>=(GAME_HEIGHT-BALL_DIAMETER)) 
          ball.setYDirection(-ball.yVelocity);
      if (ball.x<=0) {
          ball.setXDirection(-ball.xVelocity);
          score.player2++;
      }

      if (ball.x>=(GAME_WIDTH+BALL_DIAMETER)){ 
          ball.setXDirection(-ball.xVelocity);
          score.player1++;
      }     
      // ball bounds of paddles
      if(ball.intersects(j1.paddle))
          ball.setXDirection(-ball.xVelocity);
      if(ball.intersects(j2.paddle))
          ball.setXDirection(-ball.xVelocity);
       

    	if (j1.paddle.y<=0) {
    		j1.paddle.y=0;
    	}
    	if (j1.paddle.y>=(GAME_HEIGHT-PADDLE_HEIGHT)) {
    		j1.paddle.y=(GAME_HEIGHT-PADDLE_HEIGHT);
    	}
    	if (j2.paddle.y<=0) {
    		j2.paddle.y=0;
    	}
    	if (j2.paddle.y>=(GAME_HEIGHT-PADDLE_HEIGHT)) {
    		j2.paddle.y=(GAME_HEIGHT-PADDLE_HEIGHT);
    	}

    }
    public void run(){

    	//game loop
    	// long lastTime=System.nanoTime();
    	// double amountofTicks=60;
    	// double ns=1000000000/amountofTicks;
    	// double delta=0;

    	while(true){
    		// long  now= System.nanoTime();
    		// delta+= (now-lastTime)/ns;
    		// lastTime=now;
    		//if (delta>=1) {
    			move();
    			checkCollision();
    			repaint();
    			//delta--;
          try {
    			 Thread.sleep(10);
          } 
          catch (Exception e){} 
    		//}
    	}
    }

    public class AL extends KeyAdapter{
    	public void keyPressed(KeyEvent e){
        if (e.getKeyCode()==KeyEvent.VK_SPACE) {
          PAUSE=-PAUSE;
        }
    		j1.paddle.keyPressed(e);
    		// j1.sendObject(j1.paddle);
        if (e.getKeyCode()==KeyEvent.VK_O) {
        
          
        }
        
    		
    	}
    	public void keyRealeased(KeyEvent e){
    		j1.paddle.keyRealeased(e);
    	}
    }
}