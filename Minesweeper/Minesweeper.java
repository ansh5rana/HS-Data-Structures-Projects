import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Minesweeper extends JFrame implements MouseListener
{
	JToggleButton[][] buttons;
	JPanel buttonPanel;
	int numMines =10;
	boolean gameOver;
	boolean firstClick= true;
	ImageIcon[] numbers = new ImageIcon[8];
	ImageIcon flag, mine, smile, win, wait, dead;

	public Minesweeper(){
		setGrid(9,9);
		for(int x=0; x<8; x++)
		{
			numbers[x] = new ImageIcon("Minesweeper Images\\"+(x+1)+".png");
			numbers[x] = new ImageIcon(numbers[x].getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		}
		flag = new ImageIcon("MineSweeper\\flag.png");
		flag = new ImageIcon(flag.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		mine = new ImageIcon("MineSweeper\\mine0.png");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public void setGrid(int rows, int cols)
	{
		if(buttonPanel!=null)
			this.remove(buttonPanel);
		buttonPanel = new JPanel();
		gameOver = false;
		buttonPanel.setLayout(new GridLayout(rows, cols));
		buttons = new JToggleButton[rows][cols];
		for(int r=0; r<rows;r++)
		{
			for(int c=0; c<cols; c++)
			{
				buttons[r][c] = new JToggleButton();
				buttons[r][c].putClientProperty("row", r);
				buttons[r][c].putClientProperty("col", c);
				buttons[r][c].putClientProperty("state", 0);
				buttons[r][c].addMouseListener(this);
				buttonPanel.add(buttons[r][c]);
			}
		}
		this.add(buttonPanel, BorderLayout.CENTER);
		this.setSize(cols*50, rows*50);
		this.revalidate();
	}

	public void mouseReleased(MouseEvent e)
	{
		int rowClicked = (int)((JToggleButton)e.getComponent()).getClientProperty("row");
		int colClicked = (int)((JToggleButton)e.getComponent()).getClientProperty("col");


		if(!gameOver)
		{
			if(e.getButton() == MouseEvent.BUTTON1)
			{
				if(firstClick)
				{
					dropMines(rowClicked, colClicked);
					firstClick = false;
				}
				int state = (int)(buttons[rowClicked][colClicked].getClientProperty("state"));
				if(state==-1)
				{gameOver=true; JOptionPane.showMessageDialog(null, "You are a loser!");}
				else
				{expand(rowClicked, colClicked);}

				if(!firstClick && e.getButton() == MouseEvent.BUTTON3)
				{
						if(buttons[rowClicked][colClicked].getIcon()==null && !buttons[rowClicked][colClicked].isSelected())
						{
							buttons[rowClicked][colClicked].setIcon(flag);
							buttons[rowClicked][colClicked].setDisabledIcon(flag);
							buttons[rowClicked][colClicked].setEnabled(false);
						}
						else if(flag.equals(buttons[rowClicked][colClicked].getIcon()))
						{
							buttons[rowClicked][colClicked].setIcon(null);
							buttons[rowClicked][colClicked].setDisabledIcon(null);
							buttons[rowClicked][colClicked].setEnabled(true);
						}
				}
			}
		}

	}

	public void expand(int row, int col)
	{
		if(!buttons[row][col].isSelected())
		{
			buttons[row][col].setSelected(true);
		}
		int state = (int)buttons[row][col].getClientProperty("state");
		if(state>0)
		{
			buttons[row][col].setText(""+state);
		}
		else
		{
			for(int r=row-1; r<=row+1; r++)
			{
				for(int c=col-1; c<=col+1; c++)
				{
					try
					{
						if(!buttons[r][c].isSelected())
						{
							expand(r,c);
						}
					}
					catch(ArrayIndexOutOfBoundsException e){}
				}
			}
		}
	}

	public void dropMines(int row, int col)
	{
		int count = numMines;
		while(count>0)
		{
			int r = (int)(Math.random()*buttons.length);
			int c = (int)(Math.random()*buttons[0].length);
			int state = (int)buttons[r][c].getClientProperty("state");
			if(Math.abs(r-row) >1 || Math.abs(c-col)>12 && state==0)
			{
				buttons[r][c].putClientProperty("state", -1);
				count--;
			}
		}

		for(int r=0; r<buttons.length; r++)
		{
			for(int c=0; c<buttons[0].length; c++)
			{
				int state = (int)(buttons[r][c].getClientProperty("state"));
				if((state!=-1))
				{
					count=0;
					for(int a=r-1; a<=r+1; a++)
					{
						for(int b=c-1; b<=c+1; b++)
						{

							try
							{
								state=(int)(buttons[a][b].getClientProperty("state"));
								if(state==-1)
									count++;
							}catch(ArrayIndexOutOfBoundsException e){}
						}
					}
					buttons[r][c].putClientProperty("state",count);

				}
			}
		}
/*
		for(int r=0; r<buttons.length; r++)
		{
			for(int c=0; c<buttons[0].length; c++)
			{
				int state=(int)(buttons[r][c].getClientProperty("state"));
				buttons[r][c].setText(""+state);
			}
		}
*/
	}

	public static void main(String[] args)
	{
		Minesweeper app = new Minesweeper();
	}
	public void mouseExited(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}

}