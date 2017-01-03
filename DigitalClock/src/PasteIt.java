

import java.awt.Color;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;


public class PasteIt extends JFrame {
	private static final long serialVersionUID = 1L;
	private JDesktopPane desktop;

    public PasteIt() {
        super("Paste It!");
        this.desktop = new JDesktopPane();
        
        DigitalClockFrame digitalClockFrame = new DigitalClockFrame(desktop);
        digitalClockFrame.getInternalClockFrame().setVisible(true);
        
        this.setContentPane(desktop);
        this.desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
    }
    public void run() {
        this.setUndecorated(true);
        this.setBackground(new Color(0, 0, 0, 0));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
    }
    public static void main(String[] args) {
        PasteIt pasteIt = new PasteIt();
        pasteIt.run();
    }
}