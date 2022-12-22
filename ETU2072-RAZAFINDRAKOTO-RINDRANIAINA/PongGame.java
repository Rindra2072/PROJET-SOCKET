import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class PongGame{
	public static void main(String[] args) throws Exception
	{
		Joueur player =new Joueur(true);
		player.sendObject(player.paddle);
		player.receiveObject(player);
	}
}