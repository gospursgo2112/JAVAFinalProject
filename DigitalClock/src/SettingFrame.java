import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class SettingFrame {
	public static void main(String[] arguments) {
		SettingFrame settingFrame = new SettingFrame();
	}
	
	private String colorNames[] = {"黑色", "藍色", "青色", "深灰", "灰色", "綠色", "淺灰", 
			"洋紅" , "橘色", "粉紅", "紅色", "白色", "黃色"};
	
	private Color[] selectColors = {Color.black, Color.blue, Color.cyan, Color.darkGray,
			Color.gray ,Color.green, Color.lightGray, Color.magenta, Color.orange,
			Color.pink, Color.red, Color.white, Color.yellow};
	private String timeZoneNames[] = {"" , ""};
	
	private JFrame settingFrame;
	private JLabel setTextLabel;
	private JLabel setBackgroundLabel;
	private JLabel setTimeZoneLabel;
	private JComboBox colorComboBox;
	private JComboBox backgroundComboBox;
	private JComboBox timeZoneComboBox;	
	private JButton addButton;
	private JButton testButton;
	
	public SettingFrame(){		
		settingFrame = new JFrame("設定時間樣式");
		settingFrame.setLayout(new GridLayout(4, 2));

		setTextLabel = new JLabel("設定文字顏色");
		setTextLabel.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		settingFrame.add(setTextLabel);
		
		colorComboBox = new JComboBox(colorNames);
		colorComboBox.setFont(new Font("微軟正黑體", Font.BOLD, 13));
		colorComboBox.setMaximumRowCount( 3 );
		settingFrame.add(colorComboBox);
		
		setBackgroundLabel = new JLabel("設定背景顏色");
		setBackgroundLabel.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		settingFrame.add(setBackgroundLabel);
		
		backgroundComboBox = new JComboBox(colorNames);
		backgroundComboBox.setFont(new Font("微軟正黑體", Font.BOLD, 13));
		backgroundComboBox.setMaximumRowCount( 3 );
		settingFrame.add(backgroundComboBox);
		
		setTimeZoneLabel = new JLabel("設定時區");
		setTimeZoneLabel.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		settingFrame.add(setTimeZoneLabel);
		
		timeZoneComboBox= new JComboBox(timeZoneNames);
		timeZoneComboBox.setFont(new Font("微軟正黑體", Font.BOLD, 13));
		timeZoneComboBox.setMaximumRowCount( 3 );
		settingFrame.add(timeZoneComboBox);
		
		addButton = new JButton("OK");
		addButton.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		settingFrame.add(addButton);
		
		testButton = new JButton("提醒");
		settingFrame.add(testButton);
		
		
		settingFrame.setSize(400, 300);
		settingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		settingFrame.setVisible(true);
		//settingFrame.setResizable(false);		//設定視窗大小固定
		
		ButtonHandler handler = new ButtonHandler();
		addButton.addActionListener( handler );
	}
	
	private class ButtonHandler implements ActionListener {
	      // handle button event
	      public void actionPerformed( ActionEvent event ){
	    	 ClockFrame clockFrame = new ClockFrame();
	         clockFrame.initialClockFrame(
	        		 selectColors[backgroundComboBox.getSelectedIndex()],
	        		 selectColors[colorComboBox.getSelectedIndex()]);
	      } // end method actionPerformed
	   }
}
