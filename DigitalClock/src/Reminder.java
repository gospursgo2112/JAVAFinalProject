import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Icon;
import javax.swing.ImageIcon;
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
	private JFrame frame;
	
	public static void main(String[] args) {
		Reminder reminder = new Reminder();
		JFrame frame = new JFrame();
		reminder.setTimeWindow(frame);
    }
	
	
	//設定時間
    public void setTimeWindow(JFrame targetFrame){
    	frame = targetFrame;
    	Color backgroundColor = new Color(39, 76, 119);
    	Color textColor = new Color(231, 236, 239);
    	
    	getTimeFrame = new JFrame("設定提醒時間:");
    	getTimeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	getTimeFrame.setUndecorated(true);
    	getTimeFrame.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
    	//getTimeFrame.setLayout(new GridLayout(5,0, 40, 15));   	
    	getTimeFrame.setSize(240, 220);
    	getTimeFrame.setLocationRelativeTo(null);
    	getTimeFrame.getContentPane().setBackground(backgroundColor);
    	
    	//選擇日期 :
    	JLabel dateLable = new JLabel("選擇日期 :");
    	dateLable.setFont(new Font("微軟正黑體", Font.BOLD, 15));
    	dateLable.setForeground(textColor);
    	getTimeFrame.add(dateLable);
    	
    	datePicker = new DatePicker();
    	getTimeFrame.add(datePicker);
    	
        //選擇時間 :
    	JLabel timeLable = new JLabel("選擇時間 :");
    	timeLable.setFont(new Font("微軟正黑體", Font.BOLD, 15));
    	timeLable.setForeground(textColor);
    	getTimeFrame.add(timeLable);
    	
    	TimePickerSettings timeSettings = new TimePickerSettings();
        timeSettings.setColor(TimeArea.TimePickerTextValidTime, new Color(55, 154, 225));
        timeSettings.initialTime = LocalTime.now();
        timePicker = new TimePicker(timeSettings);
        getTimeFrame.add(timePicker);
    	
        //中間按鈕區塊
        JPanel centerButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 50, 0));
        centerButtonPanel.setPreferredSize(new Dimension(250, 40)); 
        centerButtonPanel.setBackground(backgroundColor);
        
		ImageIcon checkIcon=new ImageIcon("check.png");
		ImageIcon checkRolloverIcon=new ImageIcon("check_Rollover.png");
		ImageIcon cancelIcon=new ImageIcon("cancel.png");
		ImageIcon cancelRolloverIcon=new ImageIcon("cancel_Rollover.png");		
		ImageIcon exitIcon = new ImageIcon("exit.png");
		ImageIcon exitRolloverIcon = new ImageIcon("exit_Rollover.png");
		
		checkButton =new JButton(checkIcon);
		checkButton.setRolloverIcon( checkRolloverIcon );
		checkButton.setContentAreaFilled(false);
		checkButton.setFocusPainted(false);
		checkButton.setBorderPainted(false);
		checkButton.setBorder(null);
		checkButton.setToolTipText( "新增提醒時間" );
		centerButtonPanel.add(checkButton);
        
        cancelButton = new JButton(cancelIcon);
        cancelButton.setRolloverIcon( cancelRolloverIcon );
        cancelButton.setContentAreaFilled(false);
        cancelButton.setFocusPainted(false);
        cancelButton.setBorderPainted(false);
        cancelButton.setBorder(null);
        cancelButton.setToolTipText( "清除已設定的提醒" );
        centerButtonPanel.add(cancelButton);
        
        getTimeFrame.add(centerButtonPanel);

        //底部文字區塊
        JPanel buttomTextPanel = new JPanel(new GridLayout(2, 1));
        buttomTextPanel.setPreferredSize(new Dimension(240, 30));
        buttomTextPanel.setBackground(backgroundColor);
        
        setTimeTitle = new JLabel("已設定的提醒時間:");
        setTimeTitle.setFont(new Font("微軟正黑體", Font.BOLD, 15));
        setTimeTitle.setForeground(textColor);
        buttomTextPanel.add(setTimeTitle);
        
        setTimeLable = new JLabel("無");
        setTimeLable.setFont(new Font("微軟正黑體", Font.BOLD, 15));
        setTimeLable.setForeground(textColor);
        buttomTextPanel.add(setTimeLable);
        
        getTimeFrame.add(buttomTextPanel);
        
        exitButton = new JButton(exitIcon);
        exitButton.setRolloverIcon( exitRolloverIcon );
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);
        exitButton.setBorderPainted(false);
        exitButton.setBorder(null);
        exitButton.setToolTipText( "離開" );
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
		    		setTimeLable.setText(
		    			datePicker.getDateStringOrEmptyString()+ " " + timePicker.getTimeStringOrEmptyString());
		    	setSchedule(frame ,inputTime);
				} catch (ParseException e) {
					//e.printStackTrace();
					setTimeLable.setText("輸入日期格式有誤");
				}
		    	
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
    
    public void setSchedule(JFrame frame, Date inputTime){
        System.out.println("Reminder的設定時間: " + inputTime);
        /*
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
        */
        if(timer == null){
	        timer = new Timer();
	        timer.schedule(new TimerTask(){
				public void run() { 
					frame.setVisible(true);
				} 
	        }
	        , inputTime);
        }
        else{
        	System.out.println("修改EmptyFrame的設定時間:");
        	timer.cancel();
        	timer.purge();
        	timer = new Timer();
        	timer.schedule(new TimerTask(){
				public void run() { 
					frame.setVisible(true);
				} 
			}
		, inputTime);
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


