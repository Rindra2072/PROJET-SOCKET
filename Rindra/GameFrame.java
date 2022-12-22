import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class GameFrame extends JFrame{

	GamePanel panel;

	GameFrame(GamePanel panel){
		panel=panel;
		add(panel);
		setTitle("Ping Pong");
		setResizable(false);
		setBackground(Color.black);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
	}


}