import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class EmptyFrame{	
	private JFrame jFrame;
	private JDesktopPane desktopPane;
	private JInternalFrame emptyFrame;
	private JButton reminderButton;
	private JButton hideButton;
	private JTextField textField;
	private Reminder reminder;		//設定Reminder為Data Member

	public JInternalFrame getEmptyFrame() {
		return emptyFrame;
	}

	public void setEmptyFrame(JInternalFrame emptyFrame) {
		this.emptyFrame = emptyFrame;
	}
	
	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}
	//constructor
	public EmptyFrame(JDesktopPane desktop){
		emptyFrame = new JInternalFrame("內部框架 ",true,true,true,true);
		emptyFrame.setLayout(new FlowLayout());
		emptyFrame.setSize(300, 150);
				
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
		
		//假設reminderTime為讀檔後得到的時間字串
		String reminderTime = "2016-12-31 22:52";	
		//在自己的constructor中建立Reminder物件 
		//Reminder建構元的參數為:PasteIt的JDesktopPane ,要設定提醒的JInternalFrame ,時間字串 (若時間字串為空或第一次新建物件 請傳入 "")
		reminder = new Reminder(desktop, getEmptyFrame(), reminderTime);
		desktop.add(emptyFrame);
		//System.out.println(reminder.getTimeString());	//若要取得時間字串 請呼叫getTimeString()
	}
	
	private class ButtonHandler implements ActionListener {
		@Override
		public void actionPerformed( ActionEvent event ){
			if(event.getSource() == reminderButton){
				reminder.getSetTimeFrame().setVisible(true);	//按下提醒按鈕後 讓設定時間的視窗顯示
			}
			else if(event.getSource() == hideButton){
				emptyFrame.setVisible(false);
			}	
		}
	}

}
