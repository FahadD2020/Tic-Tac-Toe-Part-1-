/************************************************************
 Software Engineering
Fahad Dawood, Ethan Hannen.
*************************************************************/



import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

enum ButtonType
{
    NEW,
    MARK,
    MIN,
    EXIT
}

@SuppressWarnings("serial")
public class Button extends JButton implements MouseListener
{
    private Cursor defaultCursor = this.getCursor();;
    private Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
    private ButtonType type;
    private int index = 0;

    public Button(String text, Font f, ButtonType t)
    {
         this.setBackground(t == ButtonType.NEW ? Color.ORANGE : Color.BLACK);
         this.setForeground(t == ButtonType.NEW ? Color.BLACK : Color.WHITE);
         this.setFocusable(false);
         this.setOpaque(true);
         this.setText(text);
         this.setFont(f);
         this.type = t;
         addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
    	if (this.type == ButtonType.NEW)
            Game.newGame();
    	else if (this.type == ButtonType.EXIT)
            Grid.closeWindow();
    	else if (this.type == ButtonType.MIN)
            Grid.minimizeWindow();
    	else if (this.type == ButtonType.MARK)
    	{
            Game.markBlock(this);
            
          //  System.out.println("button pushed");
    	}
    }

    @Override
    public void mousePressed(MouseEvent e)
    {

    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
        this.setCursor(handCursor);
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        this.setCursor(defaultCursor);
    }
    
    public void setIndex(int index)
    {
    	this.index = index;
    }
    
    public int getIndex()
    {
    	return this.index;
    }
}
