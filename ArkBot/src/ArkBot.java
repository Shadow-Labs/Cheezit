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
		ImageIcon icon = new ImageIcon("Files/ArkBotLogoIcon.png");
        JFrame GUI = new JFrame("ArkBot " + version);
        BufferedImage img = null;
		try {
			img = ImageIO.read(new File("Files/ArkBotBackground.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
        GUI.setIconImage(icon.getImage());
        GUI.setSize(480,935);
        GUI.setResizable(false);
        GUI.setLocation(1440, 0);
        GUI.setContentPane(new ImagePanel(img));
        GUI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Mouse Position
        JPanel mousePanel = new JPanel();
        mousePanel.setLayout(new FlowLayout());
        //mousePanel.setLocation(50, 50);
        PointerInfo q = MouseInfo.getPointerInfo();
        p = q.getLocation();
        JLabel xPos = new JLabel("Mouse X: " + p.x);
        JLabel yPos = new JLabel("Mouse Y: " + p.y);
        mousePanel.add(xPos);
        mousePanel.add(yPos);
        GUI.add(mousePanel);
        
        
        MouseObserver mo = new MouseObserver(GUI);
        mo.addMouseMotionListener(new MouseMotionListener() {
                public void mouseMoved(MouseEvent e) {
                    p = e.getPoint();
                    //System.out.println("Mouse: " + p);
                    xPos.setText("Mouse X: " + p.x);
                    yPos.setText("Mouse Y: " + p.y);                    
                }
                public void mouseDragged(MouseEvent e) {
                }
            });
        mo.start();

        
        
        



        GUI.setVisible(true);
        
		new ArkBot();
		

	}
	
	public ArkBot() throws AWTException
	{
		// Initialization
		bot.setAutoDelay(5);
	    bot.setAutoWaitForIdle(true);
	    bot.mouseMove(720, 320);
        CharacterMovement move = new CharacterMovement(bot, p,  PAUSE);
        Setup setup = new Setup(bot, p,  PAUSE);
        
        // Setup Ark
        //setup.Settings();
        
		
	     
	    bot.delay(1000);
	    //setup.FocusARK();
	    
	    bot.delay(PAUSE);
	    //move.MoveForward(1000, true);

	    bot.delay(500);
	    //drag.move(bot, p, 350, 300);
	    //System.exit(0);

	  }
	}
