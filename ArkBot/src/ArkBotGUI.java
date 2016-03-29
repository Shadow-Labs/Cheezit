import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultCaret;


public class ArkBotGUI extends JFrame
{
	private Point p;
	private String version;
	public static long start;
	ArkBotLog log;
	InputRecorder recorder;
	WarDrum drum;
	public static JFrame GUI;
	public static JTextArea textLog;
	public static JLabel mousePos;
	public static JLabel Runtime;
	public static Timer timer;
	public static ImageIcon icon;
	public static ImageIcon icon2;
	private static Image img;
	
	public ArkBotGUI(String version, Point p)
	{
		start = System.currentTimeMillis();
		this.p = p;
		this.version = version;
		this.log = ArkBot.log;
		recorder = new InputRecorder();
		drum = new WarDrum();
		timer = new Timer(1000, new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		    	SetMouse();  
		    }
		});
		
		img = new ImageIcon("ArkBotFiles/Images/ArkBotLogo.png").getImage();
        icon = new ImageIcon(img.getScaledInstance(100, 100, 0));
        icon2 = new ImageIcon(new ImageIcon("ArkBotFiles/Images/ShadowLabsSmall.png").getImage().getScaledInstance(100, 100, 0));
		
	}
	
	public void Initialize() {
		LoadingScreen();
        GUI = new JFrame("ArkBot " + version);

        JPanel bgPanel = new BgPanel(new ImageIcon("ArkBotFiles/Images/ArkBotBackground.png").getImage());
        bgPanel.setLayout(new BorderLayout());
        
        GUI.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            	Object[] options = {"Yes", "No" };
                if (JOptionPane.showOptionDialog(GUI, "Are you sure you want to quit ArkBot?", "Quit", 
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE, icon, options, options[1]) == JOptionPane.YES_OPTION){
                	ArkBotGUI.timer.stop();
                	log.CloseLog();
                    System.exit(0);
                }
            }
        });
        
        GUI.setIconImage(img);
        GUI.setSize(480,935);
        GUI.setResizable(false);
        GUI.setLocation(1440, 0);
        GUI.setLayout(new FlowLayout());
        GUI.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        
        // Title Panel
        JPanel Title = new JPanel();
        Title.setLayout(new BorderLayout());
        Title.setOpaque(false);
        JLabel image = new JLabel("", new ImageIcon(img.getScaledInstance(200, 200, 0)), JLabel.CENTER);
        JPanel imgPanel = new JPanel(new BorderLayout());
        imgPanel.setOpaque(false);
        imgPanel.add(image);
        
        // Main Panel
        JPanel Main = new JPanel();
        Main.setLayout(new GridLayout(0,2));
        Main.setOpaque(false);
        Main.setBorder(BorderFactory.createTitledBorder(new EmptyBorder(0,0,0,0), "ArkBot Controls", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
        
        //{{ Input Recording
        JButton inputB = new JButton("Start/Stop Recording");
        inputB.setMnemonic(KeyEvent.VK_MINUS);
        inputB.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		recorder.Record();
        	}
        });
      //}}
        
        //{{ War Drums
        // War Drums - DeathMarch
        JButton drumButton1 = new JButton("Drum - DeathMarch");
        drumButton1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (!WarDrum.play) {
	        		WarDrum.play = true;
	        		WarDrum.playDM = true;
        		} else {
        			WarDrum.play = false;
        			WarDrum.playDM = false;
        		}
        	}
        });
     // War Drums - WarFrenzy
        JButton drumButton2 = new JButton("Drum - WarFrenzy");
        drumButton2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (!WarDrum.play) {
	        		WarDrum.play = true;
	        		WarDrum.playWF = true;
        		} else {
        			WarDrum.play = false;
        			WarDrum.playWF = false;
        		}
        	}
        });
        // War Drums - NativeAmericanChant
        JButton drumButton3 = new JButton("Drum - NativeAmericanChant");
        drumButton3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (!WarDrum.play) {
	        		WarDrum.play = true;
	        		WarDrum.playNAC = true;
        		} else {
        			WarDrum.play = false;
        			WarDrum.playNAC = false;
        		}
        	}
        });
        // War Drums - DarkKnightRises
        JButton drumButton4 = new JButton("Drum - DarkKnightRises");
        drumButton4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (!WarDrum.play) {
	        		WarDrum.play = true;
	        		WarDrum.playDKR = true;
        		} else {
        			WarDrum.play = false;
        			WarDrum.playDKR = false;
        		}
        	}
        });
     // War Drums - Charge
        JButton drumButton5 = new JButton("Drum - Charge");
        drumButton5.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (!WarDrum.play) {
	        		WarDrum.play = true;
	        		WarDrum.playC = true;
        		} else {
        			WarDrum.play = false;
        			WarDrum.playC = false;
        		}
        	}
        });
        // War Drums - IntensityOne
        JButton drumButton6 = new JButton("Drum - IntensityOne");
        drumButton6.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (!WarDrum.play) {
	        		WarDrum.play = true;
	        		WarDrum.playI1 = true;
        		} else {
        			WarDrum.play = false;
        			WarDrum.playI1 = false;
        		}
        	}
        });
        // War Drums - Train
        JButton drumButton7 = new JButton("Drum - Train");
        drumButton7.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (!WarDrum.play) {
	        		WarDrum.play = true;
	        		WarDrum.playT = true;
        		} else {
        			WarDrum.play = false;
        			WarDrum.playT = false;
        		}
        	}
        });
      //}}
       
        //{{ Taming - Panel
        JPanel PTaming = new JPanel();
        PTaming.setLayout(new FlowLayout(FlowLayout.LEFT));
        PTaming.setOpaque(false);
        PTaming.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.ORANGE), "Taming", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
        
        // Taming - Button
        JButton tameButton = new JButton("Start Taming");
        tameButton.setMnemonic(KeyEvent.VK_ASTERISK);
        tameButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (ArkBot.tame.taming) {
        			ArkBot.tame.taming = false;
        			tameButton.setText("Start Taming");
        			tameButton.setBackground(new JButton().getBackground());
        			ArkBotGUI.GUIText("Stopped Taming.");
        		} else {
        			ArkBot.tame.time = Integer.parseInt(JOptionPane.showInputDialog(GUI, "Instructions: \n"
        					+ "1) Bot has dino inventory open.\n"
        					+ "2) Narcotics are the first items in dino's inventory.\n"
        					+ "3) To stop taming, press the taming button between narcotic applications.\n"
        					+ "4) AutoFeed function requires food as top rows in player's inventory.\n\n"
        					+ "Time between narcotics (seconds)"));
        			ArkBotGUI.GUIText("Begin Taming at 1 Narcotic every " + ArkBot.tame.time + " seconds.");
        			
        			ArkBot.tame.foodWait = Integer.parseInt(JOptionPane.showInputDialog(GUI, "AutoFeed Functionality"
        					+ "Time between AutoFeed (seconds), enter 0 for no AutoFeed"));
        			if (ArkBot.tame.foodWait != 0) {
        				ArkBotGUI.GUIText("AutoFeed every " + ArkBot.tame.foodWait + " seconds.");
        			} else {
        				ArkBotGUI.GUIText("No AutoFeed.");
        			}
        			
        			ArkBot.tame.taming = true;
        			tameButton.setText("Stop Taming ");
        			tameButton.setBackground(Color.GREEN);
        		}
        	}
        });
        
        PTaming.add(tameButton);
      //}}
        
        //{{ Breeding - Panel
        JPanel PBreeding = new JPanel();
        PBreeding.setLayout(new FlowLayout(FlowLayout.LEFT));
        PBreeding.setOpaque(false);
        PBreeding.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.MAGENTA), "Breeding", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
        
        // Breeding - Button/Prompt
        JButton breedButton = new JButton("Start Breeding");
        breedButton.setMnemonic(KeyEvent.VK_ASTERISK);
        String[] BreedOptions = {"Carnivore", "Herbivore"};
        breedButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (ArkBot.breed.breeding) {
        			ArkBot.breed.breeding = false;
        			breedButton.setText("Start Breeding");
        			breedButton.setBackground(new JButton().getBackground());
        			ArkBotGUI.GUIText("Stopped Breeding.");
        		} else {
        			int breedNumber = Integer.parseInt(JOptionPane.showInputDialog(GUI, "Instructions: \n"
        					+ "1) Bot has fridge inventory open.\n"
        					+ "2) To stop breeding, press the breeding button while bot is turning.\n"
        					+ "3) AutoFeed function requires food in slot 5 of the hotbar.\n\n"
        					+ "Number of breeds:"));
        			
        			int c = breedNumber;
        			// Add Fridge
        			ArkBot.breed.breedSetup.add(0);
        			while (c != 0) {
        				String breedType = (String)JOptionPane.showInputDialog(GUI,"Proceeding left, breed " + (breedNumber - (c - 1)) + " of " + breedNumber + ".",
        	                    "Breed Types", JOptionPane.PLAIN_MESSAGE, icon, BreedOptions, "Carnivore");
        				if (breedType.equals("Carnivore")) {
        					ArkBot.breed.breedSetup.add(1);
        					ArkBot.breed.carns++;
        				} else {
        					ArkBot.breed.breedSetup.add(2);
        					ArkBot.breed.herbs++;
        				}
        				c--;
        			}
        			
        			ArkBotGUI.GUIText("Begin breeding " + ArkBot.breed.carns + " carnivores and " + ArkBot.breed.herbs + " herbivores.");
        			
        			ArkBot.breed.foodWait = Integer.parseInt(JOptionPane.showInputDialog(GUI, "AutoFeed Functionality"
        					+ "Time between AutoFeed (seconds), enter 0 for no AutoFeed"));
        			if (ArkBot.breed.foodWait != 0) {
        				ArkBotGUI.GUIText("AutoFeed every " + ArkBot.breed.foodWait + " seconds.");
        			} else {
        				ArkBotGUI.GUIText("No AutoFeed.");
        			}
        			
        			ArkBot.breed.breeding = true;
        			breedButton.setText("Stop Breeding");
        			breedButton.setBackground(Color.GREEN);
        		}
        	}
        });
        PBreeding.add(breedButton);
      //}}
        
        //{{ AutoGatherer - Panel
        JPanel PAutoGatherer = new JPanel();
        PAutoGatherer.setLayout(new FlowLayout(FlowLayout.LEFT));
        PAutoGatherer.setOpaque(false);
        PAutoGatherer.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GREEN), "AutoGatherer", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
        
        // AutoGatherer - Button
        JPanel AGBPanel = new JPanel();
        AGBPanel.setLayout(new BoxLayout(AGBPanel, BoxLayout.PAGE_AXIS));
        JButton AutoGatherButton = new JButton("Start AutoGathering");
        AutoGatherButton.setMnemonic(KeyEvent.VK_MINUS);
        JCheckBox AutoDrop = new JCheckBox("AutoDrop Functionality");
		JCheckBox boxes[] = new JCheckBox[ArkBot.gatherer.materials.length];
		int i = 0;
		while (i < ArkBot.gatherer.materials.length) {
			boxes[i] = new JCheckBox(ArkBot.gatherer.materials[i]);
			AGBPanel.add(boxes[i]);
			i++;
		}
        AutoGatherButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (ArkBot.gatherer.gathering) {
        			ArkBot.gatherer.gathering = false;
        			ArkBot.gatherer.clicking = false;
        			ArkBot.gatherer.dropping = false;
        			AutoGatherButton.setText("Start AutoGathering");
        			AutoGatherButton.setBackground(new JButton().getBackground());
        			ArkBotGUI.GUIText("[ACTION] Stopped AutoGatherer.");
        		} else {
        			ArkBot.gatherer.pause = Float.parseFloat(JOptionPane.showInputDialog(GUI, AutoDrop, "AutoClicker Delay (seconds)"));
        			if (AutoDrop.isSelected()) {
        				ArkBot.gatherer.dropping = true;        				
	        			JOptionPane.showConfirmDialog(GUI, AGBPanel,"Material Selection", JOptionPane.PLAIN_MESSAGE);
	        			
	        			int i = 0;
	        			while (i < ArkBot.gatherer.materials.length) {
	        				if (boxes[i].isSelected()) {
	        					ArkBot.gatherer.keepMats.add(ArkBot.gatherer.materials[i]);
	        				}
	        				i++;
	        			}
        			}
        			ArkBot.gatherer.gathering = true;
        			ArkBotGUI.GUIText("Press '-' to begin AutoClicking every " + ArkBot.gatherer.pause + " seconds.");

        			AutoGatherButton.setText("Stop AutoGathering");
        			AutoGatherButton.setBackground(Color.GREEN);
        		}
        	}
        });
        
        PAutoGatherer.add(AutoGatherButton);
      //}}
        
        //{{ Download Zip - Panel
        JPanel PDZip = new JPanel();
        PDZip.setLayout(new FlowLayout(FlowLayout.LEFT));
        PDZip.setOpaque(false);
        PDZip.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Download ArkBot.zip", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
        
        // Download Zip - Button
        JButton zipDButton = new JButton("Download ArkBot" + ArkBot.updt.webVersion + ".zip");
        zipDButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ArkBot.updt.DownloadZip();
        	}
        });

        PDZip.add(zipDButton);
      //}}
        
        //{{ Something1 - Panel
        JPanel Something1 = new JPanel();
        Something1.setLayout(new FlowLayout(FlowLayout.LEFT));
        Something1.setOpaque(false);
        Something1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Something1", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.GRAY));
        
        // Something1 - Button
        JButton Something1Button = new JButton("Something 1");
        Something1Button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Something
        	}
        });

        Something1.add(Something1Button);
        //}}
        
        //{{ Something2 - Panel
        JPanel Something2 = new JPanel();
        Something2.setLayout(new FlowLayout(FlowLayout.LEFT));
        Something2.setOpaque(false);
        Something2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Something2", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.GRAY));
        
        // Something2 - Button
        JButton Something2Button = new JButton("Something 2");
        Something2Button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Something
        	}
        });

        Something2.add(Something2Button);
        //}}
        
        //{{ Something3 - Panel
        JPanel Something3 = new JPanel();
        Something3.setLayout(new FlowLayout(FlowLayout.LEFT));
        Something3.setOpaque(false);
        Something3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Something3", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.GRAY));
        
        // Something3 - Button
        JButton Something3Button = new JButton("Something 3");
        Something3Button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Something
        	}
        });

        Something3.add(Something3Button);
        //}}
        
        //{{ Something4 - Panel
        JPanel Something4 = new JPanel();
        Something4.setLayout(new FlowLayout(FlowLayout.LEFT));
        Something4.setOpaque(false);
        Something4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Something4", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.GRAY));
        
        // Something4 - Button
        JButton Something4Button = new JButton("Something 4");
        Something4Button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Something
        	}
        });

        Something4.add(Something4Button);
        //}}

        //{{ Something5 - Panel
        
        JPanel Something5 = new JPanel();
        Something5.setLayout(new FlowLayout(FlowLayout.LEFT));
        Something5.setOpaque(false);
        Something5.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Something5", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.GRAY));
        
        // Something5 - Button
        JButton Something5Button = new JButton("Something 5");
        Something5Button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Something
        	}
        });

        Something5.add(Something5Button);
        //}}
        
        //{{ Something6 - Panel
        JPanel Something6 = new JPanel();
        Something6.setLayout(new FlowLayout(FlowLayout.LEFT));
        Something6.setOpaque(false);
        Something6.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Something6", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.GRAY));
        
        // Something6 - Button
        JButton Something6Button = new JButton("Something 6");
        Something6Button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Something
        	}
        });

        Something6.add(Something6Button);
        //}}
        
        //{{ Something7 - Panel
        JPanel Something7 = new JPanel();
        Something7.setLayout(new FlowLayout(FlowLayout.LEFT));
        Something7.setOpaque(false);
        Something7.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Something7", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.GRAY));
        
        // Something7 - Button
        JButton Something7Button = new JButton("Something 7");
        Something7Button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Something
        	}
        });

        Something7.add(Something7Button);
      //}}
        
        //{{ Something8 - Panel
        JPanel Something8 = new JPanel();
        Something8.setLayout(new FlowLayout(FlowLayout.LEFT));
        Something8.setOpaque(false);
        Something8.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Something8", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.GRAY));
        
        // Something8 - Button
        JButton Something8Button = new JButton("Something 8");
        Something8Button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Something
        	}
        });

        Something8.add(Something8Button);
        //}}
        
        
//        Main.add(inputB);
//        Main.add(drumButton1);
//        Main.add(drumButton2);
//        Main.add(drumButton3);
//        Main.add(drumButton4);
//        Main.add(drumButton5);
//        Main.add(drumButton6);
//        Main.add(drumButton7);
        Main.add(PTaming);
        Main.add(PBreeding);
        Main.add(PAutoGatherer);
        Main.add(PDZip);
        Main.add(Something1);
        Main.add(Something2);
        Main.add(Something3);
        Main.add(Something4);
        Main.add(Something5);
        Main.add(Something6);
        Main.add(Something7);
        Main.add(Something8);
        
        //{{ textLog
        JPanel Logger = new JPanel();
        Logger.setLayout(new BorderLayout());
        Logger.setOpaque(false);
        Logger.setBorder(BorderFactory.createTitledBorder(new EmptyBorder(0,0,0,0), "ArkBot Log", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
        
        textLog = new JTextArea(14, 10);
        DefaultCaret caret = (DefaultCaret)textLog.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        Font font = new Font("Arial", Font.PLAIN, 12);
        textLog.setFont(font);
        textLog.setForeground(Color.WHITE);
        textLog.setEditable ( false );
        textLog.setLineWrap(true);
        textLog.setOpaque(false);
        JScrollPane scroll = new JScrollPane ( textLog );
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED );
        scroll.setHorizontalScrollBarPolicy ( ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
      //}}
        
        //{{ Mouse Position
        JPanel mousePanel = new JPanel();
        mousePanel.setLayout(new BorderLayout());
        PointerInfo q = MouseInfo.getPointerInfo();
        p = q.getLocation();
        long elapsed = (System.currentTimeMillis() - start);
        String display = String.format("%02d:%02d:%02d", elapsed / 3600000, (elapsed % 3600000) / 60, (elapsed % 3600000));
        Runtime = new JLabel("Runtime: " + display);
        mousePos = new JLabel("Mouse: " + p.x + " " + p.y);
        JLabel currentVersion = new JLabel("ArkBot " + version + "                               ", JLabel.RIGHT);
        mousePanel.add(Runtime, BorderLayout.EAST);
        mousePanel.add(currentVersion, BorderLayout.CENTER);
        mousePanel.add(mousePos, BorderLayout.WEST);
      //}}
        
        Title.add(imgPanel, BorderLayout.CENTER);
        
        Logger.add(scroll, BorderLayout.NORTH);
        Logger.add(mousePanel, BorderLayout.SOUTH);
                
        bgPanel.add(Title, BorderLayout.NORTH);
        bgPanel.add(Main, BorderLayout.CENTER);
        bgPanel.add(Logger, BorderLayout.SOUTH);
        
        GUI.add(bgPanel);
        GUI.setContentPane(bgPanel);
        
        GUI.setVisible(true);
		timer.start();
        GUIText("Welcome to ArkBot " + version +"!");
	}
	
	public static void SetMouse() {
		PointerInfo q = MouseInfo.getPointerInfo();
        Point r = q.getLocation();
        long elapsed = (System.currentTimeMillis() - start);
        String display = String.format("%02d:%02d:%02d", (elapsed / (1000 * 60 * 60)) % 24, (elapsed / (1000 * 60)) % 60, (elapsed / 1000) % 60);
        Runtime.setText("Runtime: " + display);
        mousePos.setText("Mouse: " + r.x + " " + r.y);
	}
	
	public static void GUIText (String text) {
		String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		textLog.setText(textLog.getText() + "\n" + timeStamp + ": " + text);
		ArkBot.log.WriteLog(timeStamp + ": " + text);
	}
	
	public void SetupPrompt() {
		if (ArkBotSettings.CheckSetting("SetupPrompt")) {
			JPanel msgPanel = new JPanel();
			msgPanel.setLayout(new BorderLayout());
			JLabel requirements = new JLabel("<html>Please ensure the following are true:<br>"
					+ "1) Ark Resolution is set to 1440 x 900, Windowed<br>"
				    + "2) Ark window is located in the top left corner of your default monitor<br>"
				    + "3) You are currently logged in/connected to a server<br>"
				    + "4) Inventory is in small mode (press q while in inventory)<br> </html>");
			JCheckBox dontAsk = new JCheckBox("Do not prompt me again");
			dontAsk.setHorizontalAlignment(JCheckBox.RIGHT);
			msgPanel.add(requirements, BorderLayout.NORTH);
			msgPanel.add(dontAsk, BorderLayout.SOUTH);
			JOptionPane.showConfirmDialog(GUI, msgPanel,"Ark Settings", JOptionPane.PLAIN_MESSAGE);
			if (dontAsk.isSelected()) {
				ArkBotSettings.UpdateSetting("SetupPrompt", false);
			}
		}
	}
	
	private void LoadingScreen() {
		JPanel bgPanel = new BgPanel(new ImageIcon("ArkBotFiles/Images/LoadingScreenBackground.png").getImage());
        bgPanel.setLayout(new BorderLayout());
        
		JFrame lScreen = new JFrame("Loading ArkBot " + version);
		lScreen.setIconImage(img);
        lScreen.setSize(360,200);
        lScreen.setResizable(false);
        lScreen.setLocationRelativeTo(null);
        lScreen.setLayout(new GridLayout());
        
        JPanel barPanel = new JPanel();
        barPanel.setOpaque(false);
        JProgressBar bar = new JProgressBar();
        bar.setOpaque(false);
        bar.setForeground(Color.DARK_GRAY);
        int MIN = 0;
        int MAX = 100;
        bar.setMinimum(MIN);
        bar.setMaximum(MAX);
        bar.setStringPainted(true);
        
        barPanel.add(bar);
        bar.setPreferredSize(new Dimension (300,32));
        
        JLabel image1 = new JLabel("", icon, JLabel.CENTER);
        JLabel image2 = new JLabel("", icon2, JLabel.CENTER);
        JPanel imgPanel = new JPanel(new FlowLayout());
        imgPanel.setOpaque(false);
        imgPanel.add(image2);
        imgPanel.add(image1);        
        
        bgPanel.add(imgPanel, BorderLayout.NORTH);
        bgPanel.add(barPanel, BorderLayout.CENTER);
        lScreen.add(bgPanel);
        lScreen.setContentPane(bgPanel);
        lScreen.setVisible(true);
        
        int progress = MIN;
        int load = 0;
        while (progress < MAX) {
			try {
				Thread.sleep((long) (Math.random() * (MAX - progress)));
				bar.setValue(progress);
				if ((progress % 6) == 0) {
					load++;
					if (load % 6 == 0) {
						bar.setString("Loading.  ");
					} else if (load % 3 == 1) {
						bar.setString("Loading.. ");
					} else {
						bar.setString("Loading...");
					}
				}
		    	progress++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        try {
			Thread.sleep(250);
			lScreen.dispose();
	        Thread.sleep(750);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
