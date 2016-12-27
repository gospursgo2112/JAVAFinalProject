import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class EmptyFrame extends Reminder{
	public static void main(String[] arguments) {
		EmptyFrame emptyFrame = new EmptyFrame();
	}
	//attribute
	private JFrame emptyFrame;
	private JButton reminderButton;
	private JButton hideButton;
	private JTextField textField;

	private Timer timer;

	public JFrame getEmptyFrame() {
		return emptyFrame;
	}

	public void setEmptyFrame(JFrame emptyFrame) {
		this.emptyFrame = emptyFrame;
	}
	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}
	//constructor
	public EmptyFrame(){
		emptyFrame = new JFrame();
		emptyFrame.setLayout(new FlowLayout());
		emptyFrame.setSize(400, 300);
		emptyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		emptyFrame.setLocationRelativeTo(null);
		
		textField = new JTextField(20);
		emptyFrame.add(textField);
		
		reminderButton = new JButton("提醒");
		emptyFrame.add(reminderButton);
		
		hideButton = new JButton("隱藏");
		emptyFrame.add(hideButton);
		
		
		emptyFrame.setVisible(true);
		
		ButtonHandler handler = new ButtonHandler();
		reminderButton.addActionListener( handler );
		hideButton.addActionListener( handler );
		
	}
	//copy constuctor
	public EmptyFrame(EmptyFrame original){
		emptyFrame = original.getEmptyFrame();
		emptyFrame.setSize(400, 300);
		emptyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		emptyFrame.setVisible(true);
	}
	
	private class ButtonHandler implements ActionListener {
	      // handle button event
		@Override
		public void actionPerformed( ActionEvent event ){
			if(event.getSource() == reminderButton){
				getTimeFrame();
				//setSchedule(inputTime);
			}
			else if(event.getSource() == hideButton){
				System.out.println("EmptyFrame隱藏");
				emptyFrame.setVisible(false);
			}
			
		} // end method actionPerformed
	}
	@Override
	public void setSchedule(Date inputTime){
        System.out.println("EmptyFrame的設定時間: " + inputTime);
        if(timer == null){
	        timer = new Timer();
	        timer.schedule(new TimerTask(){
				public void run() { 
					getEmptyFrame().setVisible(true);
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
					getEmptyFrame().setVisible(true);
				} 
			}
		, inputTime);
        }
        
    }
	@Override
	public void cancelSchedule(){
    	if(timer != null){
    		timer.cancel();
    		timer.purge();
    	}
    }
	
	
}
