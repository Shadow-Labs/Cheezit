import java.awt.AWTException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JComponent;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

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
	public static AutoGatherer gatherer;
	public static Point p;
	public static String version;
	public static String ERROR = "";
	
	public static void main(String[] args) throws AWTException
	{
		// Get Version
		try {
			File vfile = new File("ArkBotFiles/Version/CurrentVersion.txt");
			FileInputStream vfis;
			vfis = new FileInputStream(vfile);
			byte[] vdata = new byte[(int) vfile.length()];
			vfis.read(vdata);
			vfis.close();
			version = new String(vdata);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		bot = new Robot();
		log = new ArkBotLog();
		tame = new Tamer();
		breed = new Breeder();
		gatherer = new AutoGatherer();
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
		// Check for Updated Version
		Updater updt = new Updater();
		try {
			updt.download();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// Initialization
		bot.setAutoDelay(5);
	    bot.setAutoWaitForIdle(true);
        Setup setup = new Setup();
        ChatImageGrab g = new ChatImageGrab();
        MeatSplitter ms = new MeatSplitter();
        CharacterActions action = new CharacterActions();
        WarDrum drum = new WarDrum();
        
        

        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(new ShortcutManager());
        
        while (true) {
        	drum.Drumming();
        	tame.Tamin();
        	breed.Breedin();
        	gatherer.Gatherin();
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
