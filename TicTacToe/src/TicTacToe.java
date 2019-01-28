/*
 * A basic implementation of Tic Tac Toe using swing and layouts
 */
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TicTacToe extends JPanel
{
	JButton buttons[] = new JButton[9];
	int turnNumber = 0; //even for X odd for O
	
	public TicTacToe()
	{
		setLayout(new GridLayout(3,3));
		setupGrid();
	}
	
	public void setupGrid()
	{
		for(int i = 0; i <= 8; i++)
		{
			buttons[i] = new JButton();
			buttons[i].setText("");
			buttons[i].addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e)
				{
					JButton buttonClicked = (JButton)e.getSource();
					//Prevents overwriting already placed symbols
					if(buttonClicked.getText().equals(""))
					{
						if(turnNumber % 2 == 0)
						{
							buttonClicked.setText("X");
						}
						else
						{
							buttonClicked.setText("O");
						}
						
						if(checkWin())
						{
							JOptionPane.showConfirmDialog(null, "Game Over. Winner is " + buttonClicked.getText());
							resetGrid();
						}
						
						turnNumber++;
						if(turnNumber > 8)
						{
							JOptionPane.showConfirmDialog(null, "Game Over. Tie game.");
							resetGrid();
						}
					}
				}
			});
			
			add(buttons[i]);
		}
	}
	
	public void resetGrid()
	{
		for(int i = 0; i <= 8; i++)
		{
			buttons[i].setText("");
		}
		turnNumber = 0;
	}
	
	public Boolean checkWin()
	{
		//Horizontal win check
		if(checkForMatchingSquares(0,1) && checkForMatchingSquares(1,2))
		{
			return true;
		}
		else if(checkForMatchingSquares(3,4) && checkForMatchingSquares(4,5))
		{
			return true;
		}
		else if(checkForMatchingSquares(6,7) && checkForMatchingSquares(7,8))
		{
			return true;
		}
		
		//Vertical win check
		else if(checkForMatchingSquares(0,3) && checkForMatchingSquares(3,6))
		{
			return true;
		}
		else if(checkForMatchingSquares(1,4) && checkForMatchingSquares(4,7))
		{
			return true;
		}
		else if(checkForMatchingSquares(2,5) && checkForMatchingSquares(5,8))
		{
			return true;
		}
		
		//Diagonal win check
		else if(checkForMatchingSquares(0,4) && checkForMatchingSquares(4,8))
		{
			return true;
		}
		else if(checkForMatchingSquares(2,4) && checkForMatchingSquares(4,6))
		{
			return true;
		}
		return false;
	}
	
	public Boolean checkForMatchingSquares(int a, int b)
	{
		return buttons[a].getText().equals(buttons[b].getText()) && !buttons[a].getText().equals("");
	}
	
	public static void main(String[] args)
	{
		JFrame window = new JFrame("Tic-Tac-Toe");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().add(new TicTacToe());
		window.setBounds(300,200,300,300);
		window.setVisible(true);
	}
}
