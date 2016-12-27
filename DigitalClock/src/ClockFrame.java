import java.awt.Color;
import java.awt.GridLayout;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class ClockFrame extends JFrame{
	public void initialClockFrame(Color backgoundColor, Color textColor) {
	    //JFrame.setDefaultLookAndFeelDecorated(true);
	    JFrame f = new JFrame("Digital Clock");
	    f.setSize(200 ,150);
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.setLayout(new GridLayout(3, 1));
	    f.setLocationRelativeTo(null);
	    //f.setUndecorated(true); 	//設定JFrame上面那條為透明
	    f.getContentPane().setBackground(backgoundColor);	//設定背景顏色
	    f.setVisible(true);
	    
	    ClockLabel dateLable = new ClockLabel("date", textColor); 
	    ClockLabel timeLable = new ClockLabel("time", textColor);
	    ClockLabel dayLable = new ClockLabel("day", textColor);
	    //f.add(dateLable);
	    f.add(timeLable);
	    //f.add(dayLable);
	    //設定文字顏色
	    dateLable.setForeground(textColor);
	    timeLable.setForeground(textColor);
	    dayLable.setForeground(textColor);

	}
	
}
