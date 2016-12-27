import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

class ClockLabel extends JLabel implements ActionListener {
	private String type;
	private Color textColor;
	private String timeZone;
	private SimpleDateFormat sdf;
	
	//constructor
	public ClockLabel(String type ,Color textColor) {
		this.type = type;
		this.textColor = textColor;
		
		//setForeground(textColor);		//設定文字顏色
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Taipei"));	//設定時區
		
		switch (type) {
			case "date":
				sdf = new SimpleDateFormat("  MMMM dd yyyy");
				setFont(new Font("sans-serif", Font.PLAIN, 12));
				setHorizontalAlignment(SwingConstants.LEFT);
				break;
			case "time":
				sdf = new SimpleDateFormat("HH:mm:ss");
				setFont(new Font("sans-serif", Font.PLAIN, 40));
				setHorizontalAlignment(SwingConstants.CENTER);
				break;
			case "day":
				sdf = new SimpleDateFormat("EEEE  ");
				setFont(new Font("sans-serif", Font.PLAIN, 16));
				setHorizontalAlignment(SwingConstants.RIGHT);
				break;
			default:
				sdf = new SimpleDateFormat();
				break;
		}
 
		Timer t = new Timer(1000, this);
		t.start();
	}
 
	public void actionPerformed(ActionEvent ae) {
		Date d = new Date();
	    setText(sdf.format(d));
	}
}