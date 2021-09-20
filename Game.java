/************************************************************
 Software Engineering
Fahad Dawood, Ethan Hannen.
*************************************************************/



import java.awt.Color;
import java.util.Arrays;

import javax.swing.JButton;

public class Game {
	
    static Grid grid;
    static CountDown counter;
    static Button[] marks;
    static short markCount = 0;
    static boolean playerTurn = true;
    static boolean gameOver = false;
    static int[][] rows = {{0,0,0},{0,0,0},{0,0,0}};// creates data structure to hold x's and O's to check win conditions 
    static final int SIZE = 3; // Take as user input in future releases

    public Game()
    {
    	marks = new Button[SIZE * SIZE];
    	grid = new Grid(marks);
        counter = new CountDown();
    }

    public static void newGame()
    {
    	for (JButton b : marks)
            b.setText("");
    	
    	resetRows();
    	
    	playerTurn = true;
    	gameOver = false;
    	markCount = 0;
    	grid.updateMessage(" Player 1's Turn");
    	counter.reset();    	
    }
    
    public static void updateTimer(short timeLeft)
    {
    	if (timeLeft != 0 && !gameOver)
    	{
            if (timeLeft <= 3)
                grid.setTimeColor(Color.RED);
            grid.updateTime(" Time Remaining: " + timeLeft);
    	}
    	else
    	{
            gameOver = true;
            grid.updateMessage(" Time\'s up! Player " + (!playerTurn ? 1 : 2) + " wins!");
    	}
    }
    
    public static void markBlock(Button b)
    {
        if (b.getText() == "" && !gameOver)
        {
            b.setText(playerTurn ? "X": "O");
            
            int row = (int)Math.floor(b.getIndex() / SIZE);
            int col = (int)b.getIndex() % SIZE;

            updateRows(row, col, playerTurn ? 1 : -1);
            gameOver = checkWin();
            
            if (gameOver)
            {
            	grid.updateMessage(" Player " + (playerTurn ? 1 : 2) + " wins!");
            	counter.stop();
            }
            else if (++markCount == 9)
            {
            	counter.stop();
            	grid.updateMessage(" Draw!");
            }
            else
            {
            	counter.reset();
                setPlayerTurn(!playerTurn);
            	grid.updateMessage(" Player " + (playerTurn ? 1 : 2) + "\'s Turn");
            }
            grid.setTimeColor(Color.WHITE);
        }
    }
    
    public static boolean checkWin()
    {
    	//needs 3 arrays of length 3
    	//rows[m][n] is a m X n matrix of arrays m is number of rows n is number of columns 
    	//x= 1 in array O = -1 in array
    	// for version 2 we can create a nxn matrix taken from user input and use that to sum each row and column to check win conditions 

    	// Check Rows & Columns
    	
    	for(int x = 0; x < SIZE; x++)
    	{
    		if(Math.abs(rows[x][0] + rows[x][1] + rows[x][2]) == SIZE)
    			return true;
    		if(Math.abs(rows[0][x] + rows[1][x] + rows[2][x]) == SIZE)
    			return true;
    	}

    	// Diagonal Checks
		if( Math.abs(rows[0][0] + rows[1][1] + rows[2][2]) == SIZE | 
            Math.abs(rows[2][0] + rows[1][1] + rows[0][2]) == SIZE)
			return true;

        return false;
    }
    
    public static void setPlayerTurn(boolean turn)
    {
    	playerTurn = turn;
    }
    
    public static int[][] getRows()
    {
    	return rows;
    }
    
    public static void updateRows(int row, int col, int value)
    {
    	rows[row][col] = value;
    }
    
    public static void resetRows()
    {
    	for (int[] row: rows)
    	    Arrays.fill(row, 0);
    }
}
