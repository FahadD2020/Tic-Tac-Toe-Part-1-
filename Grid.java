/************************************************************
 Software Engineering
Fahad Dawood, Ethan Hannen.
*************************************************************/



import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

class Grid
{
    public static JFrame window;
    JLabel timeRemaining;
    JLabel playerMessage;

    private JLabel newLabel(String text, int fontSize)
    {
        JLabel label = new JLabel();
        label.setBackground(Color.BLACK);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Ink Free", Font.ITALIC, fontSize));
        label.setHorizontalAlignment(JLabel.LEFT);
        label.setText(text);
        label.setOpaque(true);
        return label;
    }

    Grid(Button[] marks)
    {
       /**************************
    	Control Panel
    	Minimize & Exit Buttons
    	**************************/
    	
    	JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    	controlPanel.setBackground(Color.BLACK);
    	controlPanel.setPreferredSize(new Dimension(450, 30));
    	JButton min = new Button("- ", new Font("Sans Serif", Font.BOLD, 15), ButtonType.MIN);
    	JButton exit = new Button("X", new Font("Sans Serif", Font.BOLD, 15), ButtonType.EXIT);
    	min.setBorder(BorderFactory.createEmptyBorder());
    	exit.setBorder(BorderFactory.createEmptyBorder());
    	controlPanel.add(min);
    	controlPanel.add(exit);
    	
       /**************************
        Title Panel
        Title & New Game Button
    	**************************/

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new GridLayout(0,2));
        titlePanel.setPreferredSize(new Dimension(450, 50));
        titlePanel.add(newLabel(" Tic Tac Toe", 30));
        JButton newGame = new Button("New Game", new Font("Ink Free", Font.ITALIC, 25), ButtonType.NEW);
        newGame.setBorder(BorderFactory.createEmptyBorder());
        newGame.setBackground(Color.ORANGE);
        titlePanel.add(newGame);
        
        /**************************
    	 Game Panel
    	 Main Game Grid
     	**************************/

        JPanel gamePanel = new JPanel();
        gamePanel.setPreferredSize(new Dimension(450, 300));
        gamePanel.setLayout(new GridLayout(3,3));

        for(int i = 0; i < 9 ; i++)
        {
            Button b = new Button("", new Font("Ink Free", Font.ITALIC,50), ButtonType.MARK);
            b.setIndex(i);
            gamePanel.add(b);
            marks[i] = b;
        }
        
        /**************************
         Turn Panel
         Timing and Player Turns
    	 **************************/

        JPanel turnPanel = new JPanel();
        turnPanel.setPreferredSize(new Dimension(450, 30));
        turnPanel.setLayout(new GridLayout());
        timeRemaining = newLabel(" Time Remaining: 10", 20);
        playerMessage = newLabel(" Player 1's Turn", 20);
        turnPanel.add(timeRemaining);
        turnPanel.add(playerMessage);

        /**************************
         Window Frame
         Setup Frame & Add Panels
    	 **************************/

        window = new JFrame("Tic Tac Toe");    	
    	window.setSize(450,450);
    	window.add(controlPanel);
    	window.add(titlePanel);
    	window.add(gamePanel);
    	window.add(turnPanel);
    	window.setLocationRelativeTo(null); // Center
    	window.setLayout(new FlowLayout(FlowLayout.RIGHT));
    	window.getContentPane().setBackground(Color.BLACK);
    	window.setUndecorated(true);
    	window.setVisible(true);
        window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public void updateTime(String t)
    {
    	timeRemaining.setText(t);
    }
    
    public void updateMessage(String t)
    {
    	playerMessage.setText(t);
    }
    
    public void setTimeColor(Color c)
    {
    	timeRemaining.setForeground(c);
    }
    
    public void setMessageColor(Color c)
    {
    	playerMessage.setForeground(c);
    }
    
    public static void minimizeWindow()
    {
    	window.setState(Frame.ICONIFIED);
    }
    
    public static void closeWindow()
    {
    	System.exit(0);
    }
    
}
