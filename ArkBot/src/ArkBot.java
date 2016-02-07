import java.awt.AWTException;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

class ImagePanel extends JComponent {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image image;
    public ImagePanel(Image image) {
        this.image = image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}


public class ArkBot {
	
	Robot bot = new Robot();
	public static int PAUSE = 50;
	public static Point p;
	public static String version = "v0.0.1";
	
	public static void main(String[] args) throws AWTException
	{
		ArkBotGUI gui = new ArkBotGUI(version, p);
		gui.Initialize();
		
        MouseObserver mo = new MouseObserver(ArkBotGUI.GUI);
        mo.addMouseMotionListener(new MouseMotionListener() {
                public void mouseMoved(MouseEvent e) {
                    p = e.getPoint();
                    //System.out.println("Mouse: " + p);
                    ArkBotGUI.SetMouse();
                    
                }
                public void mouseDragged(MouseEvent e) {
                }
            });
        mo.start();

		new ArkBot();
		

	}
	
	public ArkBot() throws AWTException
	{
		// Initialization
		bot.setAutoDelay(5);
	    bot.setAutoWaitForIdle(true);
        CharacterMovement move = new CharacterMovement(bot, p,  PAUSE);
        Setup setup = new Setup(bot, p,  PAUSE);
        
        // Setup Ark
        //setup.Settings();
	    //setup.FocusARK();
	    bot.delay(PAUSE);
	    ArkBotGUI.Log("Welcome to ArkBot " + version +"!");
	    
	    //move.MoveForward(1000, true);

	    bot.delay(500);
	    //drag.move(bot, p, 350, 300);
	    //System.exit(0);

	  }
	}
