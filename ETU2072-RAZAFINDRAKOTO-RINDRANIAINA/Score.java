import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class Score extends Rectangle{
    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    int player1;
    int player2;

    Score(int GAME_WIDTH,int GAME_HEIGHT){
    	this.GAME_WIDTH=GAME_WIDTH;
    	this.GAME_HEIGHT=GAME_HEIGHT;
    }
	public void draw(Graphics g){
		g.setColor(Color.white);
		g.drawLine(this.GAME_WIDTH/2,0,this.GAME_WIDTH/2,this.GAME_HEIGHT);
		g.setFont(new Font("Consolas",Font.PLAIN,30));
		g.drawString(String.valueOf(player1),(GAME_WIDTH/2)-25,30);
		g.drawString(String.valueOf(player2),(GAME_WIDTH/2)+10,30);
	}
	
}