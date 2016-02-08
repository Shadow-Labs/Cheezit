import java.awt.AWTException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

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
	static ArkBotLog log;
	public static int PAUSE = 50;
	public static Point p;
	public static String version = "v0.0.1";
	public static String ERROR = "";
	
	public static void main(String[] args) throws AWTException
	{
		log = new ArkBotLog(version);
		ArkBotGUI gui = new ArkBotGUI(version, p, log);
		gui.Initialize();
        ArkBotGUI.GUI.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(ArkBotGUI.GUI, "Are you sure you want to quit ArkBot?", "Quit", 
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                	log.CloseLog();
                    System.exit(0);
                }
            }
        });
		
		
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
        Setup setup = new Setup(bot, p,  PAUSE, log);
        
	    bot.delay(PAUSE);
	    ArkBotGUI.GUIText("Welcome to ArkBot " + version +"!", log);
	    
        // Setup Ark
        setup.Begin();
	    
	    //move.MoveForward(1000, true);

	    bot.delay(500);
	    //drag.move(bot, p, 350, 300);
	    //System.exit(0);

	  }
	}
