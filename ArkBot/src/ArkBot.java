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
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

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
	public static boolean serverRun;
	public static Server server;
	public static ArkBotClient client;
	public static Robot bot;
	public static MouseDrag drag;
	public static Global global;
	public static ArkBotLog log;
	public static ArkBotState state;
	public static ClientStruct cStruct;
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
		

		ArkBotSettings settings = new ArkBotSettings();
		connect = false;
		connection = false;
		serverStart = false;
		serverRun = false;
		server = new Server();
		client = new ArkBotClient();
		bot = new Robot();
		drag = new MouseDrag();
		log = new ArkBotLog();
		updt = new Updater();
		screenReader = new ScreenReader();
		global = new Global();
		ArkBotGUI gui = new ArkBotGUI(version, p);

		state = new ArkBotState();
		cStruct = new ClientStruct();
		
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
         // Clear previous logging configurations.
            LogManager.getLogManager().reset();

            // Get the logger for "org.jnativehook" and set the level to off.
            Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
            logger.setLevel(Level.OFF);
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(new ShortcutManager());
        
        Thread clientThread = null;
        int keepRunning = 0;
        while (true) {
        	//drum.Drumming();
        	state.tame.Tamin();
        	state.breeder.Breedin();
        	state.gatherer.Gatherin();
        	state.healer.Healin();
        	state.splitter.Splittin();
        	state.piloter.Pilotin();
        	
        	// Server Start - Password: asdf
        	if (serverStart) {
        		if (JOptionPane.showInputDialog(ArkBotGUI.GUI, "Server Password:").equals("asdf")) {
            		while (serverRun) {
            			System.out.println("SERVER");
            			ArkBotGUI.Serverize();
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
    			ArkBot.state.connected = true;
                clientThread = new Thread(new ArkBotClient());
        		clientThread.start();
        		//Allow thread to make connection
        		bot.delay(500);
        	} else if (!connect && connection) {
        		ArkBot.state.connected = false;
        		clientThread.interrupt();
        		//Allow thread to disconnect
        		bot.delay(500);
        	}
        	keepRunning++;
        	if (keepRunning%32 == 0) {
        		System.out.println("CLIENT");
        	}
        	
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
