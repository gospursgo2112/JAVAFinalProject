import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class DigitalClockFrame{
	private static final long serialVersionUID = 1L;
	private static final String CLOCK_FONT_RESOURCE = "digital-dream-skew.ttf";
	private JInternalFrame internalClockFrame;
	public JInternalFrame getInternalClockFrame() {
		return internalClockFrame;
	}

	public void setInternalClockFrame(JInternalFrame internalClockFrame) {
		this.internalClockFrame = internalClockFrame;
	}

	private JMenuBar menuBar;
	private JMenu settingMenu;
	private JMenu backgroundColorMenu;	//背景顏色
	private JMenuItem blueItem;
	private JMenuItem babyPowderItem;
	private JMenuItem roseItem;
	private JMenuItem yellowItem;
	private JMenu timeZoneMenu;		//時區
	private JMenuItem losAngeles;	//utc-8
	private JMenuItem mexicoCity;	//utc-6
	private JMenuItem newYork;		//utc-5 America/New_York
	private JMenuItem brazil;		//utc-4 America/Santiago
	private JMenuItem london;		//UTC (GMT格林威治標準時間) Europe/London
	private JMenuItem berlin;		//utc+1 Europe/Berlin
	private JMenuItem athens;		//UTC+2	Europe/Athens
	private JMenuItem taipei;		//UTC+8 Asia/Taipei
	private JMenuItem tokyo;		//UTC+9 Asia/Tokyo
	private JMenuItem australia;	//UTC+10 Australia/Brisbane
	private JMenuItem auckland;		//UTC+12 Pacific/Auckland
	private JMenuItem exitItem;	
	private JPanel titleBarPanel;
	private DigitalClockPanel digitalClockPanel;

	public DigitalClockFrame(JDesktopPane desktop){
		internalClockFrame = new JInternalFrame("Digital Clock", false, false, false, false);
		internalClockFrame.setBorder(null);
		BasicInternalFrameUI basicInternalFrameUI = (BasicInternalFrameUI)internalClockFrame.getUI();
        basicInternalFrameUI.setNorthPane(null);
		// Loads the custom font for the clock.
		Font clockFont = null;
		Font dateFont = null;
		try {
			clockFont = loadFont(CLOCK_FONT_RESOURCE, 50);
			dateFont = loadFont(CLOCK_FONT_RESOURCE, 20);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Creates the digital clock panel and sets all the specific properties.
		digitalClockPanel = new DigitalClockPanel();
		digitalClockPanel.setClockBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		digitalClockPanel.setClockForeground(new Color(46, 194, 179));
		digitalClockPanel.setClockBackground(new Color(1, 22, 40));
		digitalClockPanel.setClockFont(clockFont, dateFont);

		internalClockFrame.getContentPane().add(digitalClockPanel, BorderLayout.CENTER);

		// Setups the frame.
		menuBar = new JMenuBar();

		settingMenu = new JMenu();
		settingMenu.setIcon(new ImageIcon("images/time_setting.png"));
		
		backgroundColorMenu = new JMenu();
		backgroundColorMenu.setIcon(new ImageIcon("images/timecolor.png"));
		
		blueItem = new JMenuItem("Maastright blue");
		blueItem.setBackground(new Color(1, 22, 40));
		blueItem.setFont(new Font("微軟正黑體", Font.BOLD, 12));
    	blueItem.setEnabled(false);
		backgroundColorMenu.add(blueItem);

		babyPowderItem = new JMenuItem("Baby powder");
		babyPowderItem.setBackground(new Color(252, 255, 250));
		babyPowderItem.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		backgroundColorMenu.add(babyPowderItem);

		roseItem = new JMenuItem("Rose madder");
		roseItem.setBackground(new Color(231, 29, 52));
		roseItem.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		backgroundColorMenu.add(roseItem);

		yellowItem = new JMenuItem("Bright yellow");
		yellowItem.setBackground(new Color(255, 159, 26));
		yellowItem.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		backgroundColorMenu.add(yellowItem);
		
    	settingMenu.add(backgroundColorMenu);
    	//時區
    	timeZoneMenu = new JMenu("");
    	timeZoneMenu.setIcon(new ImageIcon("images/timezone.png"));
    	
		losAngeles = new JMenuItem("洛杉磯 (UTC-8)");
		losAngeles.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		timeZoneMenu.add(losAngeles);
    	
		mexicoCity = new JMenuItem("墨西哥 (UTC-6)");
		mexicoCity.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		timeZoneMenu.add(mexicoCity);
		
		newYork = new JMenuItem("紐約 (UTC-5)");
		newYork.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		timeZoneMenu.add(newYork);
		
		brazil = new JMenuItem("巴西 (UTC-4)");
		brazil.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		timeZoneMenu.add(brazil);
		
		london = new JMenuItem("倫敦	(UTC)");
		london.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		timeZoneMenu.add(london);
		
		berlin = new JMenuItem("柏林 (UTC+1)");
		berlin.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		timeZoneMenu.add(berlin);
		
		athens = new JMenuItem("雅典 (UTC+2)");
		athens.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		timeZoneMenu.add(athens);
		
		taipei = new JMenuItem("台北 (UTC+8)");
		taipei.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		timeZoneMenu.add(taipei);
		taipei.setEnabled(false);
		
		tokyo = new JMenuItem("東京 (UTC+9)");
		tokyo.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		timeZoneMenu.add(tokyo);
		
		australia = new JMenuItem("澳洲 (UTC+10)");
		australia.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		timeZoneMenu.add(australia);
		
		auckland = new JMenuItem("奧克蘭 (UTC+12)");
		auckland.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		timeZoneMenu.add(auckland);
		
		settingMenu.add(timeZoneMenu);
		
		exitItem = new JMenuItem();
		exitItem.setIcon(new ImageIcon("images/time_exit.png"));
		settingMenu.add(exitItem);
		
    	menuBar.add(settingMenu);
    	
    	titleBarPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
    	//titleBarPanel.setBackground(new Color(1, 22, 40));
    	titleBarPanel.setPreferredSize(new Dimension(250, 20));
    	titleBarPanel.add(menuBar);
    	internalClockFrame.add(titleBarPanel, BorderLayout.NORTH);

    	
		ComponentMover componentMover = new ComponentMover(internalClockFrame, internalClockFrame);
		internalClockFrame.pack();
		desktop.add(internalClockFrame);
		//
		MenuItemHandler handler = new MenuItemHandler();
		blueItem.addActionListener( handler );
		babyPowderItem.addActionListener( handler );
		roseItem.addActionListener( handler );
		yellowItem.addActionListener( handler );
		losAngeles.addActionListener( handler );
		mexicoCity.addActionListener( handler );
		newYork.addActionListener( handler );
		brazil.addActionListener( handler );
		london.addActionListener( handler );
		berlin.addActionListener( handler );
		athens.addActionListener( handler );
		taipei.addActionListener( handler );
		tokyo.addActionListener( handler );
		australia.addActionListener( handler );
		auckland.addActionListener( handler );
		exitItem.addActionListener( handler );
	}
	
	private class MenuItemHandler implements ActionListener {
		@Override
		public void actionPerformed( ActionEvent event ){
			if(event.getSource() == blueItem){
				digitalClockPanel.setClockBackground(new Color(1, 22, 40));
				setColorEnabled();
				blueItem.setEnabled(false);
			}
			if(event.getSource() == babyPowderItem){
				digitalClockPanel.setClockBackground(new Color(252, 255, 250));
				setColorEnabled();
				babyPowderItem.setEnabled(false);
			}
			if(event.getSource() == roseItem){
				digitalClockPanel.setClockBackground(new Color(231, 29, 52));
				setColorEnabled();
				roseItem.setEnabled(false);
			}
			if(event.getSource() == yellowItem){
				digitalClockPanel.setClockBackground(new Color(255, 159, 26));
				setColorEnabled();
				yellowItem.setEnabled(false);
			}
			if(event.getSource() == losAngeles){
				digitalClockPanel.setDateFormat("America/Los_Angeles");
				setTimeZoneEnabled();
				losAngeles.setEnabled(false);
			}
			if(event.getSource() == mexicoCity){
				digitalClockPanel.setDateFormat("America/Mexico_City");
				setTimeZoneEnabled();
				mexicoCity.setEnabled(false);
			}
			if(event.getSource() == newYork){
				digitalClockPanel.setDateFormat("America/New_York");
				setTimeZoneEnabled();
				newYork.setEnabled(false);
			}
			if(event.getSource() == brazil){
				digitalClockPanel.setDateFormat("America/Santiago");
				setTimeZoneEnabled();
				brazil.setEnabled(false);
			}
			if(event.getSource() == london){
				digitalClockPanel.setDateFormat("Europe/London");
				setTimeZoneEnabled();
				london.setEnabled(false);
			}
			if(event.getSource() == berlin){
				digitalClockPanel.setDateFormat("Europe/Berlin");
				setTimeZoneEnabled();
				berlin.setEnabled(false);
			}
			if(event.getSource() == athens){
				digitalClockPanel.setDateFormat("Europe/Athens");
				setTimeZoneEnabled();
				athens.setEnabled(false);
			}
			if(event.getSource() == taipei){
				digitalClockPanel.setDateFormat("Asia/Taipei");
				setTimeZoneEnabled();
				taipei.setEnabled(false);
			}
			if(event.getSource() == tokyo){
				digitalClockPanel.setDateFormat("Asia/Tokyo");
				setTimeZoneEnabled();
				tokyo.setEnabled(false);
			}
			if(event.getSource() == australia){
				digitalClockPanel.setDateFormat("Australia/Brisbane");
				setTimeZoneEnabled();
				australia.setEnabled(false);
			}
			if(event.getSource() == auckland){
				digitalClockPanel.setDateFormat("Pacific/Auckland");
				setTimeZoneEnabled();
				auckland.setEnabled(false);
			}
			if(event.getSource() == exitItem){
				internalClockFrame.setVisible(false);
			}
		}
	}
	
	public void setColorEnabled(){
		blueItem.setEnabled(true);
		babyPowderItem.setEnabled(true);
		roseItem.setEnabled(true);
		yellowItem.setEnabled(true);
	}
	
	public void setTimeZoneEnabled(){
		losAngeles.setEnabled(true);
		mexicoCity.setEnabled(true);
		newYork.setEnabled(true);
		brazil.setEnabled(true);
		london.setEnabled(true);
		berlin.setEnabled(true);
		athens.setEnabled(true);
		taipei.setEnabled(true);
		tokyo.setEnabled(true);
		australia.setEnabled(true);
		auckland.setEnabled(true);
	}
	
	
	private Font loadFont(String name, float fontSize) throws FontFormatException, IOException {
		try {
			return FontResources.loadFont(name, fontSize);
		} catch (FontFormatException e) {
			showErrorMessage(internalClockFrame, "Error loading font '" + name + "': invalid format.");
			throw e;
		} catch (FileNotFoundException e) {
			showErrorMessage(internalClockFrame, "Error loading font '" + name + "': resource not found.");
			throw e;
		} catch (IOException e) {
			showErrorMessage(internalClockFrame, "Error loading font '" + name + "': I/O error.");
			throw e;
		}
	}

	private void showErrorMessage(Component parentComponent, Object message) {
		JOptionPane.showMessageDialog(parentComponent, message,
				"ERROR", JOptionPane.ERROR_MESSAGE);
	}

}