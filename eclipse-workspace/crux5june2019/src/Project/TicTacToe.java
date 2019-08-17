package Project;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TicTacToe extends JFrame implements ActionListener {
	public static int board_size=3;
	public static enum Gamestatus{
		incomplete,Xwins,Zwins,Tie
	}
	private JButton [][] buttons=new JButton[board_size][board_size];
	boolean crosstrun =true;
public TicTacToe () {
	super.setTitle("TicTacToe");
	super.setSize(800,800);
	GridLayout gridlayout =new GridLayout(board_size,board_size);
	super.setLayout(gridlayout);
	Font font=new Font("Comic Sans", 1,150);
	for(int row=0;row<board_size;row++) {
		for(int col=0;col<board_size;col++) {
			JButton button =new JButton("");
			buttons[row][col]=button;
			button.setFont(font);
			button.addActionListener(this);
			super.add(button);
		}
	}
	super.setResizable(false);
	super.setVisible(true);
}
@Override
public void actionPerformed(ActionEvent e) {
	JButton clickedbutton =(JButton)e.getSource();
	makemove(clickedbutton);
	Gamestatus gs=this.getGamestatus();
	if(gs==Gamestatus.incomplete) {
		return;
	}
	declarewinner(gs);
	int choice=JOptionPane.showConfirmDialog(this,"Do you want to restart the Game!");
	if(choice==JOptionPane.YES_OPTION) {
		for(int row=0;row<board_size;row++) {
			for(int col=0;col<board_size;col++) {
				buttons[row][col].setText("");
			}
		}
		crosstrun=true;
	}
	else {
		super.dispose();
	}
	
	
}
private Gamestatus getGamestatus() {
	String text1="";
	String text2="";
	int row=0;
	int col=0;
	//for rows
	row=0;
	while(row<board_size) {
		col=0;
		while(col<board_size-1) {
			text1=buttons[row][col].getText();
			text2=buttons[row][col+1].getText();
			if(!text1.equals(text2)||text1.length()==0) {
				break;
			}
			col++;
		}
		if(col==board_size-1) {
			if(text1.equals(text2)) {
				return Gamestatus.Xwins;
			}
			else {
				return Gamestatus.Zwins;
			}
		}
		row++;
	}
	//for cols
	col=0;
	while(col<board_size) {
		row=0;
		while(row<board_size-1) {
			text1=buttons[row][col].getText();
			text2=buttons[row+1][col].getText();
			if(!text1.equals(text2)||text1.length()==0) {
				break;
			}
			row++;
		}
		if(row==board_size-1) {
			if(text1.equals(text2)) {
				return Gamestatus.Xwins;
			}
			else {
				return Gamestatus.Zwins;
			}
		}
		col++;
	}
	//for diagonal1
	row=0;
	col=0;
	while(row<board_size-1) {
		text1=buttons[row][col].getText();
		text2=buttons[row+1][col+1].getText();
		if(!text1.equals(text2)||text1.length()==0) {
			break;
		}
		row++;
		col++;
	}
	if(row==board_size-1) {
		if(text1.equals(text2)) {
			return Gamestatus.Xwins;
		}
		else {
			return Gamestatus.Zwins;
		}
	}
	//for diagonal 2
	
	row=board_size-1;
	col=0;
	while(row>0) {
		text1=buttons[row][col].getText();
		text2=buttons[row-1][col+1].getText();
		if(!text1.equals(text2)||text1.length()==0) {
			break;
		}
		row--;
		col++;
	}
	if(row==0) {
		if(text1.equals(text2)) {
			return Gamestatus.Xwins;
		}
		else {
			return Gamestatus.Zwins;
		}
	}
	String txt="";
	for(row=0;row<board_size;row++) {
		for(col=0;col<board_size;col++) {
			txt=buttons[row][col].getText();
			if(txt.length()==0) {
				return Gamestatus.incomplete;
			}
		}
	}
	return Gamestatus.Tie;
}
private void declarewinner(Gamestatus gs) {
	if(gs==Gamestatus.Xwins) {
		JOptionPane.showMessageDialog(this, "X wins");
	}
	else if(gs==Gamestatus.Zwins) {
		JOptionPane.showMessageDialog(this, "Z wins");
	}
	else {
		JOptionPane.showMessageDialog(this, "It is a Tie");
	}
	
}
private void makemove(JButton clickedbutton) {
	String buttontext=clickedbutton.getText();
	if(buttontext.length()>0) {
		JOptionPane.showMessageDialog(this, "Invalid Move");
	}
	else {
		if(crosstrun) {
			clickedbutton.setText("X");
		}else {
			clickedbutton.setText("0");
		}
		crosstrun=!crosstrun;
	}
	
}
}
