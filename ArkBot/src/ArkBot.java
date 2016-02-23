import java.awt.AWTException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

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
	
	public static Robot bot;
	public static Global global;
	public static ArkBotLog log;
	public static Tamer tame;
	public static Breeder breed;
	public static AutoClicker clicker;
	public static Point p;
	public static String version = "v0.1.2";
	public static String ERROR = "";
	
	public static void main(String[] args) throws AWTException
	{
		bot = new Robot();
		log = new ArkBotLog(version);
		tame = new Tamer();
		breed = new Breeder();
		clicker = new AutoClicker();
		global = new Global(1440, 900);
		ArkBotGUI gui = new ArkBotGUI(version, p);
		gui.Initialize();
		ArkBotSettings settings = new ArkBotSettings();
		gui.SetupPrompt();

		
		
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
        Setup setup = new Setup(bot, p,  global.PAUSE);
        ChatImageGrab g = new ChatImageGrab(bot);
        MeatSplitter ms = new MeatSplitter(bot, p, global.PAUSE);
        CharacterActions action = new CharacterActions(p, global.PAUSE);
        WarDrum drum = new WarDrum();
        
        
//        drum.DeathMarch();

        //setup.Begin();
        
        while (true) {
        	drum.Drumming();
        	tame.Tamin();
        	breed.Breedin();
        	clicker.Clickin();
        	System.out.println("Do I really have to do this?");
        }
        
//        int stacks = action.InvSearch("fiber");
//        ms.SplitStack(global.INV_POINTS[0], stacks);
        
	    // bot.delay(global.PAUSE);
//	    g.Grab();
//	    g.InvertGrab();
//        new TesseractExample();
        // Setup Ark
        //setup.Begin();
	    
		// OCR Initialization/Test
		//OCRScannerDemo demo = new OCRScannerDemo();
		//ArkBotGUI.GUIText("OCR: " + demo.OCRSomething("ArkBotFiles\\OCRImages\\ChatImageGrab\\grabInvert.png"));
	    

	  }
	}
