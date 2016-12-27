import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.components.TimePickerSettings.TimeArea;


public class Reminder extends TimerTask{
	private JFrame getTimeFrame;
	private JButton checkButton;
	private JButton cancelButton;
	private JButton exitButton;	
	private JLabel setTimeLable;
	private JLabel setTimeTitle;
	private DatePicker datePicker;
	private TimePicker timePicker;
	private Timer timer;
	private Date inputTime;
	
	public static void main(String[] args) {
		Reminder reminder = new Reminder();
		reminder.getTimeFrame();
    }
	
	
	//設定時間
    public void getTimeFrame(){
    	getTimeFrame = new JFrame("設定提醒時間:");
    	getTimeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	getTimeFrame.setUndecorated(true);
    	getTimeFrame.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
    	//getTimeFrame.setLayout(new GridLayout(5,0, 40, 15));
    	
    	getTimeFrame.setSize(250, 200);
    	getTimeFrame.setLocationRelativeTo(null);
    	//getTimeFrame.getContentPane().setBackground(new Color(117, 159, 189));
    	
    	JLabel dateLable = new JLabel("選擇日期:");
    	dateLable.setFont(new Font("微軟正黑體", Font.BOLD, 15));
    	getTimeFrame.add(dateLable);
    	
    	datePicker = new DatePicker();

    	getTimeFrame.add(datePicker);
        
    	
    	JLabel timeLable = new JLabel("選擇時間:");
    	timeLable.setFont(new Font("微軟正黑體", Font.BOLD, 15));
    	getTimeFrame.add(timeLable);
    	
    	TimePickerSettings timeSettings = new TimePickerSettings();
        timeSettings.setColor(TimeArea.TimePickerTextValidTime, new Color(46, 46, 46));
        timeSettings.initialTime = LocalTime.now();
        timePicker = new TimePicker(timeSettings);
        getTimeFrame.add(timePicker);
    	
        checkButton = new JButton("設定提醒");
        checkButton.setFont(new Font("微軟正黑體", Font.BOLD, 13));
        //checkButton.setBackground(new Color(50, 90, 159));
        getTimeFrame.add(checkButton);
        
        cancelButton = new JButton("取消提醒");
        cancelButton.setFont(new Font("微軟正黑體", Font.BOLD, 13));
        getTimeFrame.add(cancelButton);
        
        JPanel panel=new JPanel(new GridLayout(2, 1));
        panel.setPreferredSize(new Dimension(250, 40));
        
        setTimeTitle= new JLabel("已設定的提醒時間:");
        setTimeTitle.setFont(new Font("微軟正黑體", Font.BOLD, 15));
        panel.add(setTimeTitle);
        
        setTimeLable = new JLabel("無");
        setTimeLable.setFont(new Font("微軟正黑體", Font.BOLD, 15));
        panel.add(setTimeLable);
        
        getTimeFrame.add(panel);
        
        exitButton = new JButton("離開");
        exitButton.setFont(new Font("微軟正黑體", Font.BOLD, 13));
        getTimeFrame.add(exitButton);
        
        
    	getTimeFrame.setVisible(true);
    	
    	ComponentMover componentMover = new ComponentMover(getTimeFrame, getTimeFrame);
    	
        //註冊事件監聽器
    	ButtonHandler handler = new ButtonHandler();
    	checkButton.addActionListener( handler );
    	cancelButton.addActionListener( handler );
    	exitButton.addActionListener( handler );
    	/*
    	System.out.println("輸入提醒時間:(yyyy-MM-dd HH:mm)");
    	Scanner input = new Scanner(System.in);

    	SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
    	Date inputTime = null;
    	try {
    		inputTime = dateFormatter.parse(input.nextLine());
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	*/
    	//Calendar calendar = Calendar.getInstance();
    	//System.out.println(calendar.DAY_OF_MONTH);
        //calendar.set(Calendar.HOUR_OF_DAY, input.nextInt());
        //calendar.set(Calendar.MINUTE, input.nextInt());
        //calendar.set(Calendar.SECOND, input.nextInt());
        //Date time = calendar.getTime();
    	
        //return inputTime;
    }
    
    private class ButtonHandler implements ActionListener {
	      // handle button event
		@Override
		public void actionPerformed( ActionEvent event ){
			if(event.getSource() == checkButton){

		        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");  

		    	try {
		    		inputTime = dateFormatter.parse(
		    				datePicker.getDateStringOrEmptyString() + " " + timePicker.getTimeStringOrEmptyString());
				} catch (ParseException e) {
					e.printStackTrace();
				}
		    	setTimeLable.setText(inputTime.toString());
		    	setSchedule(inputTime);
			}
			else if(event.getSource() == cancelButton){
				setTimeLable.setText("無");
				cancelSchedule();
			}
			else if(event.getSource() == exitButton){
				getTimeFrame.setVisible(false);
			}
		}
    }
    
    public void setSchedule(Date inputTime){
        System.out.println("Reminder的設定時間: " + inputTime);
        
        if(timer == null){
	        timer = new Timer();
	        timer.schedule(new Reminder(), inputTime);
        }
        else{
        	System.out.println("修改Reminder的設定時間:");
        	timer.cancel();
        	timer.purge();
        	timer = new Timer();
        	timer.schedule(new Reminder(), inputTime);
        }
    }
    
    public void cancelSchedule(){
    	if(timer != null){
    		timer.cancel();
    		timer.purge();
    	}
    }
    
    @Override   
    public void run() {
        System.out.println("Reminder於指定時間執行");
    }

}


