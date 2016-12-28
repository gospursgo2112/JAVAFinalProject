import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class EmptyFrame{
	public static void main(String[] arguments) {
		EmptyFrame emptyFrame = new EmptyFrame();
	}
	//attribute
	private JFrame emptyFrame;
	private JButton reminderButton;
	private JButton hideButton;
	private JTextField textField;
	private Reminder reminder = new Reminder();

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
				if(reminder == null)
				reminder = new Reminder();
				reminder.setTimeWindow(getEmptyFrame());	//把要在設定時間顯示的Jframe傳進去
			}
			else if(event.getSource() == hideButton){
				System.out.println("EmptyFrame隱藏");
				emptyFrame.setVisible(false);
			}
			
		} // end method actionPerformed
	}

}
