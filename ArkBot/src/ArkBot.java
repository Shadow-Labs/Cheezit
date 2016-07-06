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
import javax.swing.JOptionPane;

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
	
	public static boolean connect;
	public static boolean connection;
	public static boolean serverStart;
	public static ArkBotServer server;
	public static ArkBotClient client;
	public static Robot bot;
	public static MouseDrag drag;
	public static Global global;
	public static ArkBotLog log;
	public static CharacterActions act;
	public static Tamer tame;
	public static Breeder breed;
	public static AutoGatherer gatherer;
	public static Updater updt;
	public static ScreenReader screenReader;
	public static Point p;
	public static String version;
	public static String ERROR = "";
	
	public static void main(String[] args) throws AWTException
	{
		//{{ Get Version
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
		//}}
		
		connect = false;
		connection = false;
		serverStart = false;
		server = new ArkBotServer();
		client = new ArkBotClient();
		bot = new Robot();
		drag = new MouseDrag();
		log = new ArkBotLog();
		act = new CharacterActions();
		tame = new Tamer();
		breed = new Breeder();
		gatherer = new AutoGatherer();
		updt = new Updater();
		screenReader = new ScreenReader();
		global = new Global(1440, 900);
		ArkBotGUI gui = new ArkBotGUI(version, p);
		ArkBotSettings settings = new ArkBotSettings();
		gui.Initialize();
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
        ImageGrab g = new ImageGrab();
        MeatSplitter ms = new MeatSplitter();
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

        Thread clientThread = new Thread(new ArkBotClient());
        
        //Screen Reader loop management
        int i = 0;
        
        while (true) {
        	//drum.Drumming();
        	tame.Tamin();
        	breed.Breedin();
        	gatherer.Gatherin();
        	
        	// Server Start - Password: ArkBotFTW
        	if (serverStart) {
        		if (JOptionPane.showInputDialog(ArkBotGUI.GUI, "Server Password:").equals("asdf")) {
            		while (true) {
            			System.out.println("SERVER");
            			server.Start();
            		}
        		} else {
        			Object[] options = { "OK", "CANCEL" };
        			JOptionPane.showOptionDialog(ArkBotGUI.GUI, "Incorrect Password.", "Bad Password",
        			JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
        			null, options, options[0]);
            		serverStart = false;
        		}
        	}
        	
        	// Client Connection
        	if (connect && !connection) {
        		clientThread.start();
        	} else if (!connect && connection) {
        		clientThread.stop();
        	}
        	//System.out.println("CLIENT");
        	
        	
//        	if (i == 100) {
//        		screenReader.Grab(0);
//        		i = 0;
//        	}
//        	i++;
        }
        
//        int stacks = action.InvSearch("fiber");
//        ms.SplitStack(global.INV_POINTS[0], stacks);
        
	    // bot.delay(global.PAUSE);

//        new TesseractExample();
        // Setup Ark
        //setup.Begin();
	    

	  }
	}
