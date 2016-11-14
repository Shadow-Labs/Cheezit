import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultCaret;

import org.jnativehook.keyboard.NativeKeyEvent;


public class ArkBotGUI extends JFrame
{
	private Point p;
	private String version;
	public static long start;
	ArkBotLog log;
	InputRecorder recorder;
	WarDrum drum;
	
	// Hotkey Values
	int tempAGStartStop = Global.AGatherStartStop;
	int tempAGDrop = Global.AGatherDrop;
	int tempAHIncOne = Global.AHealIncOne;
	int tempAHIncTen = Global.AHealIncTen;
	int tempAHAbort = Global.AHealAbort;
	int tempMSplit = Global.MSplitter;
	
	public static JFrame GUI;
	public static JTextArea textLog;
	public static JTextArea clientText;
	public static JTextArea chatText;
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
        GUI.setLayout(new GridLayout());
        GUI.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        //{{ Menu
        JMenuBar menuBar = new JMenuBar();
        
        JMenu mFile = new JMenu("File");
        JMenu mClient = new JMenu("Client");
        JMenu mServer = new JMenu("Server");
        JMenu mSettings = new JMenu("Settings");
        
        // File
        JMenuItem iAbout = new JMenuItem("About");
        iAbout.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Object[] options = {"Awesome!" };
        		//{{ About Message
        		String message = "Hey There!\n "
        				+ "So you've gotten a hold of my attempt to autonomize Ark which I have taken to \n"
        				+ "calling ArkBot. This bot is my most ambitious programming endeavor and I hope that \n"
        				+ "you'll find it useful in one way or another. My Goals with this bot are as follows\n\n"
        				+ "GOALS\n-------\n"
        				+ "1. Undetectable.\n"
        				+ "By utilizing only the mouse and keyboard controls I hope to emulate\n"
        				+ "real player behaviors, thus making it hard to detect. As of now, ArkBot has been \n"
        				+ "undetected on BattleEye'd Official Ark Servers. (knock on wood).\n\n"
        				+ "2. Reliable.\n"
        				+ "Always a work in progress, I try to incorporate redundancies into the \n"
        				+ "\tbot so that when you setup a tame overnight, you can sleep easy knowing the tame\n"
        				+ "\twill go uninhibited.\n\n"
        				+ "3. Open Communication.\n"
        				+ "I always look forward to hearing ideas from users based on \n"
        				+ "\twhat they want or need from ArkBot. By using ArkBot, you're part of the ArkBot\n"
        				+ "\tdevelopment team. Most of the features on ArkBot were suggested by its users.\n\n"
        				+ "4. Ease of Use.\n"
        				+ "While I readily admit the GUI isn't too snazzy (I'm only a one man\n"
        				+ "\tteam, cut me some slack!), I am particularly prideful of the updating feature\n"
        				+ "\timplemented in ArkBot. Once installed, ArkBot will look for updates and prompt\n"
        				+ "\tyou when a new update is avaliable where it will automattically download and\n"
        				+ "\tupdate itself with the newest patches/features.\n\n"
        				+ "If you yourself knows some java programming or know someone who does, put me in touch!\n\n"
        				+ "Thanks for using ArkBot! Let me know what you think,\n"
        				+ "\t-RedLeeder\n\n";
        		//}}
        		JOptionPane.showOptionDialog(null, message, "About ArkBot", 
                        JOptionPane.OK_OPTION,
                        JOptionPane.PLAIN_MESSAGE, icon, options, options[0]);
        	}
        });
        JMenuItem iFeatures = new JMenuItem("Version History");
        iFeatures.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Get History File
        		FileReader in;
        		String data = ArkBot.updt.getData("https://raw.githubusercontent.com/Shadow-Labs/Cheezit/master/ArkBot/ArkBotFiles/Version/ArkBotVersionHistory.txt");

        		JTextArea verArea = new JTextArea(data);
        		verArea.setEditable(false);
        		JScrollPane scrollPane = new JScrollPane(verArea);  
        		verArea.setLineWrap(true);  
        		verArea.setWrapStyleWord(true); 
        		scrollPane.setPreferredSize( new Dimension( 400, 500 ) );
        		Object[] options = {"Great Stuff!" };
        		JOptionPane.showOptionDialog(null, scrollPane, "ArkBot Version History", 
                        JOptionPane.OK_OPTION,
                        JOptionPane.PLAIN_MESSAGE, icon, options, options[0]);
        	};
        });
        JMenuItem iQuit = new JMenuItem("Quit");
        iQuit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
            	ArkBotGUI.timer.stop();
            	log.CloseLog();
                System.exit(0);
        	}
        });
        // Client
        JMenuItem iConnect = new JMenuItem("Connect");
        iConnect.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Object[] options = {"Can't Wait!" };
        		JOptionPane.showOptionDialog(GUI, "Work In Progress...", "Slow down there!", 
                        JOptionPane.OK_OPTION,
                        JOptionPane.PLAIN_MESSAGE, icon, options, options[0]);
        	}
        });
        JMenuItem iDisconnect = new JMenuItem("Disconnect");
        iDisconnect.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Object[] options = {"Can't Wait!" };
        		JOptionPane.showOptionDialog(GUI, "Work In Progress...", "Slow down there!", 
                        JOptionPane.OK_OPTION,
                        JOptionPane.PLAIN_MESSAGE, icon, options, options[0]);
        	}
        });
        JMenuItem iControl = new JMenuItem("Control");
        iControl.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Object[] options = {"Can't Wait!" };
        		JOptionPane.showOptionDialog(GUI, "Work In Progress...", "Slow down there!", 
                        JOptionPane.OK_OPTION,
                        JOptionPane.PLAIN_MESSAGE, icon, options, options[0]);
        	}
        });
        // Server
        JMenuItem iStart = new JMenuItem("Start");
        iStart.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
            	ArkBot.serverStart = true;
            	ArkBot.serverRun = true;
        	}
        });
        JMenuItem iStop = new JMenuItem("Stop");
        iStop.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ArkBot.serverRun = false;
        		ArkBot.serverStart = false;
        	}
        });
        // Edit Settings
        JMenuItem iEditSett = new JMenuItem("Edit Settings");
        iEditSett.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//{{ Server Settings Frame
        		JFrame sframe = new JFrame("Settings");
        		sframe.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    	Object[] options = {"Yes", "No", "Cancel" };
                    	int ans = JOptionPane.showOptionDialog(null, "Would you like to save changes?", "Save", 
                                JOptionPane.YES_NO_CANCEL_OPTION,
                                JOptionPane.PLAIN_MESSAGE, icon, options, options[1]);
                        if (ans == JOptionPane.YES_OPTION){
                        	// Save
                        	ArkBot.global.saveSett();
                        	ArkBot.global.setRes();
                        	// Quit Window
                        	sframe.setVisible(false);
                        	sframe.disable();
                        } else if (ans == JOptionPane.NO_OPTION) {
                        	// Quit Window
                        	sframe.setVisible(false);
                        	sframe.disable();
                        } else {
                        	// Do Nothing
                        }
                    }
                });
        		sframe.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        		sframe.setLayout(new GridLayout());
        		
        		//{{ Window Settings Panel
        		JPanel wSett = new JPanel();
        		wSett.setLayout(new GridLayout(4,3));
        		
        		JLabel LwTitle = new JLabel("Set Resolution");
        		JLabel LxRes = new JLabel("X:");
        		JLabel LyRes = new JLabel("Y:");
        		JTextField TAxRes = new JTextField("" + Global.ResX);
        		TAxRes.setForeground(Color.GRAY);
        		JTextField TAyRes = new JTextField("" + Global.ResY);
        		TAyRes.setForeground(Color.GRAY);
        		
        		// Add Listener to remove default values from text fields
        		LwTitle.requestFocusInWindow();
    			TAxRes.addFocusListener(new FocusListener() {
    				public void focusGained(FocusEvent e) {
    					TAxRes.setText("");
    					TAxRes.setForeground(Color.BLACK);
    				}
    				public void focusLost(FocusEvent e) {
    					//Nothing
    				}
    			});
    			TAyRes.addFocusListener(new FocusListener() {
    				public void focusGained(FocusEvent e) {
    					TAyRes.setText("");
    					TAyRes.setForeground(Color.BLACK);
    				}
    				public void focusLost(FocusEvent e) {
    					//Nothing
    				}
    			});
    			
        		JButton BWSet = new JButton("Set");
        		BWSet.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// Get values from text field
						int newX = ArkBot.global.ResX;
						int newY = ArkBot.global.ResY;
						boolean err = false;
						if (InputCheck.isInt(TAxRes.getText())) {
							newX = Integer.parseInt(TAxRes.getText());
						} else {
							JOptionPane.showMessageDialog(sframe, "Invalid x Resolution.");
							err = true;
						} 
						if (InputCheck.isInt(TAyRes.getText())) {
							newY = Integer.parseInt(TAyRes.getText());
						} else {
							JOptionPane.showMessageDialog(sframe, "Invalid y Resolution.");
							err = true;
						}
						
						if (!err) {
							if (newX <= 500 || newY <= 500) {
								JOptionPane.showMessageDialog(sframe, "Resolution must be greater than 500x500.");
							} else {
								ArkBot.global.ResX = newX;
								ArkBot.global.ResY = newY;
								ArkBot.global.saveSett();
								ArkBot.global.setRes();
							}
						}
						
					}
        			
        		});
        		
        		wSett.add(LwTitle);
        		wSett.add(Box.createHorizontalStrut(1));
        		wSett.add(LxRes);
        		wSett.add(TAxRes);
        		wSett.add(LyRes);
        		wSett.add(TAyRes);
        		wSett.add(Box.createHorizontalStrut(1));
        		wSett.add(BWSet);
        		
        		//}}
        		
        		sframe.add(wSett);
        		int width = 300;
        		int height = 125;
        		sframe.pack();
        		sframe.setLocationRelativeTo(null);
        		sframe.setSize(width, height);
        		sframe.setVisible(true);
        		sframe.requestFocusInWindow();
        		//}}
        	}
        });
        // Edit Shortcuts
        JMenuItem iEditShort = new JMenuItem("Edit Shortcuts");
        iEditShort.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//{{ Server Shortcut Frame
        		JFrame sframe = new JFrame("Shortcuts");
        		sframe.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    	Object[] options = {"Yes", "No", "Cancel" };
                    	int ans = JOptionPane.showOptionDialog(null, "Would you like to save changes?", "Save", 
                                JOptionPane.YES_NO_CANCEL_OPTION,
                                JOptionPane.PLAIN_MESSAGE, icon, options, options[1]);
                        if (ans == JOptionPane.YES_OPTION){
                        	// Save
                        	// Set new hotkeys
                        	if (tempAGStartStop == tempAGDrop) {
    							JOptionPane.showMessageDialog(sframe, "Identical AutoGather Hotkeys.");
    						} else if (tempAHIncOne == tempAHIncTen || tempAHIncOne == tempAHAbort || tempAHIncTen == tempAHAbort) {
    							JOptionPane.showMessageDialog(sframe, "Identical AutoHeal Hotkeys.");
    						} else {
	    						Global.AGatherStartStop = tempAGStartStop;
	    						Global.AGatherDrop = tempAGDrop;
	    						Global.AHealIncOne = tempAHIncOne;
	    						Global.AHealIncTen = tempAHIncTen;
	    						Global.AHealAbort = tempAHAbort;
	                        	ArkBot.global.saveShort();
	                        	// Quit Window
	                        	sframe.setVisible(false);
	                        	sframe.disable();
    						}
                        } else if (ans == JOptionPane.NO_OPTION) {
                        	// Quit Window
                        	sframe.setVisible(false);
                        	sframe.disable();
                        } else {
                        	// Do Nothing
                        }
                    }
                });
        		sframe.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        		sframe.setLayout(new GridLayout());
        		
        		//{{ Window Shortcuts Panel
        		JPanel wShort = new JPanel();
        		wShort.setLayout(new GridLayout(10,3));
        		
        		JLabel LAGTitle = new JLabel("AutoGather");
        		JLabel LAGStartStop = new JLabel("Start/Stop:");
        		JButton BAGStartStop = new JButton(NativeKeyEvent.getKeyText(Global.AGatherStartStop));
        		JLabel LAGDrop = new JLabel("Drop:");
        		JButton BAGDrop = new JButton(NativeKeyEvent.getKeyText(Global.AGatherDrop));
        		JLabel LAHTitle = new JLabel("AutoHeal");
        		JLabel LAHIncOne = new JLabel("Increment by 1:");
        		JButton BAHIncOne = new JButton(NativeKeyEvent.getKeyText(Global.AHealIncOne));
        		JLabel LAHIncTen = new JLabel("Increment by 10:");
        		JButton BAHIncTen = new JButton(NativeKeyEvent.getKeyText(Global.AHealIncTen));
        		JLabel LAHAbort = new JLabel("Abort:");
        		JButton BAHAbort = new JButton(NativeKeyEvent.getKeyText(Global.AHealAbort));
        		JLabel LMSTitle = new JLabel("Meat Splitter");
        		JLabel LMSplit = new JLabel("Meat Splitter Start/Stop:");
        		JButton BMSplit = new JButton(NativeKeyEvent.getKeyText(Global.MSplitter));
        		
        		//{{ Button Listeners
        		BAGStartStop.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						BAGStartStop.setText("Press New Hotkey");
						Global.nextKey = true;
						while (Global.lastPressed < 0) {
							System.out.println("Waiting For Pressed");
						}
						tempAGStartStop = Global.lastPressed;
						Global.lastPressed = -1;
						Global.nextKey = false;
						BAGStartStop.setText(NativeKeyEvent.getKeyText(tempAGStartStop));
					}
        		});
        		BAGDrop.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						BAGDrop.setText("Press New Hotkey");
						Global.nextKey = true;
						while (Global.lastPressed < 0) {
							System.out.println("Waiting For Pressed");
						}
						tempAGDrop = Global.lastPressed;
						Global.lastPressed = -1;
						Global.nextKey = false;
						BAGDrop.setText(NativeKeyEvent.getKeyText(tempAGDrop));
					}
        		});
        		BAHIncOne.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						BAHIncOne.setText("Press New Hotkey");
						Global.nextKey = true;
						while (Global.lastPressed < 0) {
							System.out.println("Waiting For Pressed");
						}
						tempAHIncOne = Global.lastPressed;
						Global.lastPressed = -1;
						Global.nextKey = false;
						BAHIncOne.setText(NativeKeyEvent.getKeyText(tempAHIncOne));
					}
        		});
        		BAHIncTen.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						BAHIncTen.setText("Press New Hotkey");
						Global.nextKey = true;
						while (Global.lastPressed < 0) {
							System.out.println("Waiting For Pressed");
						}
						tempAHIncTen = Global.lastPressed;
						Global.lastPressed = -1;
						Global.nextKey = false;
						BAHIncTen.setText(NativeKeyEvent.getKeyText(tempAHIncTen));
					}
        		});
        		BAHAbort.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						BAHAbort.setText("Press New Hotkey");
						Global.nextKey = true;
						while (Global.lastPressed < 0) {
							System.out.println("Waiting For Pressed");
						}
						tempAHAbort = Global.lastPressed;
						Global.lastPressed = -1;
						Global.nextKey = false;
						BAHAbort.setText(NativeKeyEvent.getKeyText(tempAHAbort));
					}
        		});
        		BMSplit.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						BMSplit.setText("Press New Hotkey");
						Global.nextKey = true;
						while (Global.lastPressed < 0) {
							System.out.println("Waiting For Pressed");
						}
						tempMSplit = Global.lastPressed;
						Global.lastPressed = -1;
						Global.nextKey = false;
						BMSplit.setText(NativeKeyEvent.getKeyText(tempMSplit));
					}
        		});
        		
        		//}}
    			
        		JButton BWSet = new JButton("Set");
        		BWSet.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// Set new hotkeys
						if (tempAGStartStop == tempAGDrop) {
							JOptionPane.showMessageDialog(sframe, "Identical AutoGather Hotkeys.");
						} else if (tempAHIncOne == tempAHIncTen || tempAHIncOne == tempAHAbort || tempAHIncTen == tempAHAbort) {
							JOptionPane.showMessageDialog(sframe, "Identical AutoHeal Hotkeys.");
						} else {
							Global.AGatherStartStop = tempAGStartStop;
							Global.AGatherDrop = tempAGDrop;
							Global.AHealIncOne = tempAHIncOne;
							Global.AHealIncTen = tempAHIncTen;
							Global.AHealAbort = tempAHAbort;
							ArkBotSettings.UpdateShortcut("AGatherStartStop", tempAGStartStop);
							ArkBotSettings.UpdateShortcut("AGatherDrop", tempAGDrop);
							ArkBotSettings.UpdateShortcut("AHealIncOne", tempAHIncOne);
							ArkBotSettings.UpdateShortcut("AHealIncTen", tempAHIncTen);
							ArkBotSettings.UpdateShortcut("AHealAbort", tempAHAbort);
							ArkBotSettings.UpdateShortcut("MSplit", tempMSplit);
						}
					}
        			
        		});

        		wShort.add(Box.createHorizontalStrut(1));
        		wShort.add(LAGTitle);
        		wShort.add(LAGStartStop);
        		wShort.add(BAGStartStop);
        		wShort.add(LAGDrop);
        		wShort.add(BAGDrop);
        		wShort.add(Box.createHorizontalStrut(1));
        		wShort.add(LAHTitle);
        		wShort.add(LAHIncOne);
        		wShort.add(BAHIncOne);
        		wShort.add(LAHIncTen);
        		wShort.add(BAHIncTen);
        		wShort.add(LAHAbort);
        		wShort.add(BAHAbort);
        		wShort.add(Box.createHorizontalStrut(1));
        		wShort.add(LMSTitle);
        		wShort.add(LMSplit);
        		wShort.add(BMSplit);
        		wShort.add(Box.createHorizontalStrut(1));
        		wShort.add(BWSet);
        		
        		//}}
        		
        		sframe.add(wShort);
        		int width = 300;
        		int height = 300;
        		sframe.pack();
        		sframe.setLocationRelativeTo(null);
        		sframe.setSize(width, height);
        		sframe.setVisible(true);
        		sframe.requestFocusInWindow();
        		//}}
        	}
        	
        });
        JMenuItem iSetDefault = new JMenuItem("Set Default");
        iSetDefault.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Object[] options = {"Yes", "No"};
            	int ans = JOptionPane.showOptionDialog(GUI, "Would you like to reset settings?", "Reset Settings", 
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.PLAIN_MESSAGE, icon, options, options[1]);
                if (ans == JOptionPane.YES_OPTION){
                	// Reset Settings
                	ArkBotGUI.GUIText("ArkBot Settings have been reset to default values.\n"
                			+ "Please Restart ArkBot for changes to take effect.");
                	ArkBotSettings.ResetSettings();
                } else if (ans == JOptionPane.NO_OPTION) {
                	// Do Nothing
                }
        	}
        });
        
        mFile.add(iAbout);
        mFile.add(iFeatures);
        mFile.add(iQuit);
        mClient.add(iConnect);
        mClient.add(iDisconnect);
        mClient.add(iControl);
        mServer.add(iStart);
        mServer.add(iStop);
        mSettings.add(iEditSett);
        mSettings.add(iEditShort);
        mSettings.add(iSetDefault);
        
        menuBar.add(mFile);
        menuBar.add(mClient);
        menuBar.add(mServer);
        menuBar.add(mSettings);
        
        GUI.setJMenuBar(menuBar);
        
        
        //}}
        
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
        PTaming.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.ORANGE), "Taming v1.1", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
        
        // Taming - Button
        JButton tameButton = new JButton("Start Taming");
        tameButton.setMnemonic(KeyEvent.VK_ASTERISK);
        tameButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (ArkBot.state.tame.taming) {
        			ArkBot.state.tame.taming = false;
        			tameButton.setText("Start Taming");
        			tameButton.setBackground(new JButton().getBackground());
        			ArkBotGUI.GUIText("Stopped Taming.");
        		} else {
        			ArkBot.state.tame.time = Float.parseFloat(JOptionPane.showInputDialog(GUI, "Instructions: \n"
        					+ "1) Bot has dino inventory open.\n"
        					+ "2) Narcotics are the first items in dino's inventory.\n"
        					+ "3) To stop taming, press the taming button between narcotic applications.\n"
        					+ "4) AutoFeed function requires food as top rows in player's inventory.\n"
        					+ "5) AutoDrink function requires canteen in the hotbar starting from 1.\n\n"
        					+ "Time between narcotics (seconds)"));
        			
        			JTextField autoFeed = new JTextField(5);
        			JTextField autoDrink = new JTextField(5);
        			autoFeed.setText("0");
        			autoDrink.setText("0");
        			
        			// Add Listener to remove "0" from text fields
        			autoFeed.addFocusListener(new FocusListener() {
        				public void focusGained(FocusEvent e) {
        					autoFeed.setText("");
        				}
        				public void focusLost(FocusEvent e) {
        					//Nothing
        				}
        			});
        			autoDrink.addFocusListener(new FocusListener() {
        				public void focusGained(FocusEvent e) {
        					autoDrink.setText("");
        				}
        				public void focusLost(FocusEvent e) {
        					//Nothing
        				}
        			});
        			
        			JPanel autoValuesP = new JPanel();
        			autoValuesP.add(new JLabel("AutoFeed (sec):"));
        			autoValuesP.add(autoFeed);
        			autoValuesP.add(new JLabel("AutoDrink (min):"));
        			autoValuesP.add(autoDrink);
        			int result = JOptionPane.showConfirmDialog(GUI, autoValuesP, 
        		               "AutoFeed/Drink Values", JOptionPane.OK_CANCEL_OPTION);
        		    if (result == JOptionPane.OK_OPTION) {
        		       ArkBot.state.tame.foodWait = Integer.parseInt(autoFeed.getText());
        		       ArkBot.state.tame.waterWait = Integer.parseInt(autoDrink.getText());
	           			ArkBotGUI.GUIText("Begin Taming at 1 Narcotic every " + ArkBot.state.tame.time + " seconds.");
	           			if (ArkBot.state.tame.foodWait != 0) {
	           				ArkBotGUI.GUIText("AutoFeed every " + ArkBot.state.tame.foodWait + " seconds.");
	           			} else {
	           				ArkBotGUI.GUIText("No AutoFeed.");
	           			}
	           			if (ArkBot.state.tame.waterWait != 0) {
	           				ArkBotGUI.GUIText("AutoDrink every " + ArkBot.state.tame.waterWait + " minutes.");
	           			} else {
	           				ArkBotGUI.GUIText("No AutoDrink.");
	           			}
	           			
	           			ArkBot.state.tame.taming = true;
	           			tameButton.setText("Stop Taming ");
	           			tameButton.setBackground(Color.GREEN);
        		    } else if (result == JOptionPane.CANCEL_OPTION) {
        		    	// Do nothing
        		    }
        		}
        	}
        });
        
        PTaming.add(tameButton);
      //}}
        
        //{{ Breeding - Panel
        JPanel PBreeding = new JPanel();
        PBreeding.setLayout(new FlowLayout(FlowLayout.LEFT));
        PBreeding.setOpaque(false);
        PBreeding.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.MAGENTA), "Breeding v0.1", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
        
        // Breeding - Button/Prompt
        JButton breedButton = new JButton("Start Breeding");
        breedButton.setMnemonic(KeyEvent.VK_ASTERISK);
        String[] BreedOptions = {"Carnivore", "Herbivore"};
        breedButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (ArkBot.state.breed.breeding) {
        			ArkBot.state.breed.breeding = false;
        			breedButton.setText("Start Breeding");
        			breedButton.setBackground(new JButton().getBackground());
        			ArkBotGUI.GUIText("Stopped Breeding.");
        		} else {
        			int result = JOptionPane.showConfirmDialog(GUI, "NOTE: Breeding is currently unstable and will likely mess up your breeds. Use at your own risk.","WARNING", JOptionPane.OK_CANCEL_OPTION);
        			if (result == JOptionPane.OK_OPTION) {
	        			int breedNumber = Integer.parseInt(JOptionPane.showInputDialog(GUI, "Instructions: \n"
	        					+ "1) Bot has fridge inventory open.\n"
	        					+ "2) To stop breeding, press the breeding button while bot is turning.\n"
	        					+ "3) AutoFeed function requires food in slot 5 of the hotbar.\n\n"
	        					+ "Number of breeds:"));
	        			
	        			int c = breedNumber;
	        			// Add Fridge
	        			ArkBot.state.breed.breedSetup.add(0);
	        			while (c != 0) {
	        				String breedType = (String)JOptionPane.showInputDialog(GUI,"Proceeding left, breed " + (breedNumber - (c - 1)) + " of " + breedNumber + ".",
	        	                    "Breed Types", JOptionPane.PLAIN_MESSAGE, icon, BreedOptions, "Carnivore");
	        				if (breedType.equals("Carnivore")) {
	        					ArkBot.state.breed.breedSetup.add(1);
	        					ArkBot.state.breed.carns++;
	        				} else {
	        					ArkBot.state.breed.breedSetup.add(2);
	        					ArkBot.state.breed.herbs++;
	        				}
	        				c--;
	        			}
	        			
	        			ArkBotGUI.GUIText("Begin breeding " + ArkBot.state.breed.carns + " carnivores and " + ArkBot.state.breed.herbs + " herbivores.");
	        			
	        			ArkBot.state.breed.foodWait = Integer.parseInt(JOptionPane.showInputDialog(GUI, "AutoFeed Functionality"
	        					+ "Time between AutoFeed (seconds), enter 0 for no AutoFeed"));
	        			if (ArkBot.state.breed.foodWait != 0) {
	        				ArkBotGUI.GUIText("AutoFeed every " + ArkBot.state.breed.foodWait + " seconds.");
	        			} else {
	        				ArkBotGUI.GUIText("No AutoFeed.");
	        			}
	        			
	        			ArkBot.state.breed.breeding = true;
	        			breedButton.setText("Stop Breeding");
	        			breedButton.setBackground(Color.GREEN);
        			}
        		}
        	}
        });
        PBreeding.add(breedButton);
      //}}
        
        //{{ AutoGatherer - Panel
        JPanel PAutoGatherer = new JPanel();
        PAutoGatherer.setLayout(new FlowLayout(FlowLayout.LEFT));
        PAutoGatherer.setOpaque(false);
        PAutoGatherer.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GREEN), "AutoGatherer v1.0", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
        
        // AutoGatherer - Button
        JPanel AGBPanel = new JPanel();
        AGBPanel.setLayout(new BoxLayout(AGBPanel, BoxLayout.PAGE_AXIS));
        
        int i = 3;
        int j = 9;
        JPanel[][] panelHolder = new JPanel[i][j];    
        setLayout(new GridLayout(i,j));

        for(int m = 0; m < i; m++) {
           for(int n = 0; n < j; n++) {
              panelHolder[m][n] = new JPanel();
              add(panelHolder[m][n]);
           }
        }
        JButton AutoGatherButton = new JButton("Enable AutoGather");
        AutoGatherButton.setMnemonic(KeyEvent.VK_MINUS);
        JCheckBox AutoDrop = new JCheckBox("AutoDrop Functionality");
        JCheckBox boxes[] = new JCheckBox[ArkBot.state.gatherer.materials.length];
		i = 0;
		int m = 0;
		int n = 0;
		while (i < ArkBot.state.gatherer.materials.length) {
			boxes[i] = new JCheckBox(ArkBot.state.gatherer.materials[i]);
			panelHolder[m][n].add(boxes[i]);
			AGBPanel.add(boxes[i]);
			i++;
			n++;
			if (i == 9) {
				m = 1;
				n = 0;
			} else if (i == 16) {
				m = 2;
				n = 0;
			}
			System.out.println(i + " " + m + " " + n);
		}
        AutoGatherButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (ArkBot.state.gatherer.gathering) {
        			ArkBot.state.gatherer.gathering = false;
        			ArkBot.state.gatherer.clicking = false;
        			ArkBot.state.gatherer.dropping = false;
        			AutoGatherButton.setText("Enable AutoGather");
        			AutoGatherButton.setBackground(new JButton().getBackground());
        			ArkBotGUI.GUIText("[ACTION] Stopped AutoGatherer.");
        		} else {
        			ArkBot.state.gatherer.pause = Float.parseFloat(JOptionPane.showInputDialog(GUI, AutoDrop, "AutoClicker Delay (seconds)"));
        			if (AutoDrop.isSelected()) {
        				ArkBot.state.gatherer.dropping = true;        				
	        			JOptionPane.showConfirmDialog(GUI, AGBPanel, "Material Selection", JOptionPane.PLAIN_MESSAGE);
	        			
	        			int i = 0;
	        			ArkBot.state.gatherer.keepMats.clear();
	        			while (i < ArkBot.state.gatherer.materials.length) {
	        				if (boxes[i].isSelected()) {
	        					ArkBot.state.gatherer.keepMats.add(ArkBot.state.gatherer.materials[i]);
	        				}
	        				i++;
	        			}
        			}
        			ArkBot.state.gatherer.gathering = true;
        			ArkBotGUI.GUIText("Press '-' to begin AutoClicking every " + ArkBot.state.gatherer.pause + " seconds.");

        			AutoGatherButton.setText("Disable AutoGather");
        			AutoGatherButton.setBackground(Color.GREEN);
        		}
        	}
        });
        
        PAutoGatherer.add(AutoGatherButton);
      //}}
        
        //{{ AuotHealer - Panel
        JPanel PAutoHealer = new JPanel();
        PAutoHealer.setLayout(new FlowLayout(FlowLayout.LEFT));
        PAutoHealer.setOpaque(false);
        PAutoHealer.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.RED), "AutoHealer v0.1", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
        
        // AutoHealer - Button
        JButton AutoHealButton = new JButton("Enable AutoHeal");
        AutoHealButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (ArkBot.state.healer.heal) {
        			ArkBotGUI.GUIText("Disabled AutoHeal.");
        			AutoHealButton.setText("Enable AutoHeal");
            		AutoHealButton.setBackground(new JButton().getBackground());
            		
            		ArkBot.state.healer.abort = false;
            		ArkBot.state.healer.heal = false;
        			
        		} else {
        			ArkBotGUI.GUIText("Enabled AutoHealer\n\t'-' = 100 Health\n\t'*' = 1000 Health\n\t'/' = Abort.");
        			
            		AutoHealButton.setText("Disable AutoHeal");
            		AutoHealButton.setBackground(Color.GREEN);
            		
            		ArkBot.state.healer.heal = true;
        		}
        	}
        });

        PAutoHealer.add(AutoHealButton);
        PAutoHealer.setVisible(true);
        //}}        

        //{{ MeatSplitter - Panel
        JPanel PMeatSplitter = new JPanel();
        PMeatSplitter.setLayout(new FlowLayout(FlowLayout.LEFT));
        PMeatSplitter.setOpaque(false);
        PMeatSplitter.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.PINK), "Meat Splitter v0.1", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
        
        // Something3 - Button
        JButton BMeatSplitter = new JButton("Enable MeatSplitter");
        BMeatSplitter.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (ArkBot.state.splitter.split) {
        			ArkBotGUI.GUIText("[SPLITTER]: Disabled");
        			BMeatSplitter.setText("Enable MeatSplitter");
        			BMeatSplitter.setBackground(new JButton().getBackground());
            		ArkBot.state.splitter.split = false;
        			
        		} else {
        			ArkBotGUI.GUIText("[SPLITTER]: Enabled");
        			BMeatSplitter.setText("Disable MeatSplitter");
        			BMeatSplitter.setBackground(Color.GREEN);
            		ArkBot.state.splitter.split = true;
        		}
        	}
        });

        PMeatSplitter.add(BMeatSplitter);
        PMeatSplitter.setVisible(true);
        //}}
        
        //{{ Download Zip - Panel
        JPanel PDZip = new JPanel();
        PDZip.setLayout(new FlowLayout(FlowLayout.LEFT));
        PDZip.setOpaque(false);
        PDZip.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Download ArkBot.zip v0.8", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
        
        // Download Zip - Button
        JButton zipDButton = new JButton("Download ArkBot" + ArkBot.updt.webVersion + ".zip");
        zipDButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ArkBot.updt.DownloadZip();
        	}
        });

        PDZip.add(zipDButton);
      //}}
        
        //{{ ClientConnect - Panel
        JPanel ClientConnect = new JPanel();
        ClientConnect.setLayout(new FlowLayout(FlowLayout.LEFT));
        ClientConnect.setOpaque(false);
        ClientConnect.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Connect to ArkBot Server v0.1", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.GRAY));
        
        // ClientConnect - Button
        JButton ClientButton = new JButton("Connect");
        ClientButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Object[] options = {"Can't Wait!" };
        		JOptionPane.showOptionDialog(GUI, "Work In Progress...", "Slow down there!", 
                        JOptionPane.OK_OPTION,
                        JOptionPane.PLAIN_MESSAGE, icon, options, options[0]);
        		if (ArkBot.connection) {
        			ClientButton.setText("Connect");
        			ArkBot.connect = false;
        		} else {
        			ClientButton.setText("Disconnect");
        			ArkBot.connect = true;;
        		}
        	}
        });

        ClientConnect.add(ClientButton);
        ClientConnect.setVisible(true);
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
        Something4.setVisible(false);
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
        Something5.setVisible(false);
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
        Something6.setVisible(false);
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
        Something7.setVisible(false);        
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
        Main.add(PAutoHealer);
        Main.add(PMeatSplitter);
        Main.add(PDZip);
        Main.add(ClientConnect);
        Main.add(Something4);
        Main.add(Something5);
        Main.add(Something6);
        Main.add(Something7);
        
        
        // Text Display Panel
        JPanel textDisplay = new JPanel();
        textDisplay.setLayout(new GridLayout(0,1));
        textDisplay.setOpaque(false);
        
        //{{ chatLog
        JPanel chatLogger = new JPanel();
        chatLogger.setLayout(new BorderLayout());
        chatLogger.setOpaque(false);
        chatLogger.setBorder(BorderFactory.createTitledBorder(new EmptyBorder(0,0,0,0), "Chat Text", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
        
        chatText = new JTextArea(14, 10);
        DefaultCaret caret = (DefaultCaret)chatText.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        Font font = new Font("Arial", Font.PLAIN, 12);
        chatText.setFont(font);
        chatText.setForeground(Color.WHITE);
        chatText.setEditable ( false );
        chatText.setLineWrap(false);
        chatText.setOpaque(false);
        JScrollPane scrollc = new JScrollPane ( chatText );
        scrollc.setOpaque(false);
        scrollc.getViewport().setOpaque(false);
        scrollc.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED );
        scrollc.setHorizontalScrollBarPolicy ( ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
        
        //}}
        
        
        //{{ textLog
        JPanel Logger = new JPanel();
        Logger.setLayout(new BorderLayout());
        Logger.setOpaque(false);
        Logger.setBorder(BorderFactory.createTitledBorder(new EmptyBorder(0,0,0,0), "ArkBot Log", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
        
        textLog = new JTextArea(14, 10);
        caret = (DefaultCaret)textLog.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        font = new Font("Arial", Font.PLAIN, 12);
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
        mousePanel.setLayout(new GridLayout(1,3));
        PointerInfo q = MouseInfo.getPointerInfo();
        p = q.getLocation();
        long elapsed = (System.currentTimeMillis() - start);
        String display = String.format("%02d:%02d:%02d", elapsed / 3600000, (elapsed % 3600000) / 60, (elapsed % 3600000));
        Runtime = new JLabel("Runtime: " + display, JLabel.RIGHT);
        mousePos = new JLabel("Mouse: " + p.x + " " + p.y + "                        ");
        JLabel currentVersion = new JLabel("          ArkBot " + version + "                ", JLabel.RIGHT);
        mousePanel.add(mousePos);
        mousePanel.add(currentVersion);
        mousePanel.add(Runtime);
      //}}
        
        Title.add(imgPanel, BorderLayout.CENTER);
        
        Logger.add(scroll, BorderLayout.NORTH);
        Logger.add(mousePanel, BorderLayout.SOUTH);
        
        //textDisplay.add(chatLogger, BorderLayout.NORTH);
        textDisplay.add(Logger, BorderLayout.SOUTH);
                
        bgPanel.add(Title, BorderLayout.NORTH);
        bgPanel.add(Main, BorderLayout.CENTER);
        bgPanel.add(textDisplay, BorderLayout.SOUTH);
        
        GUI.add(bgPanel);
        GUI.setContentPane(bgPanel); 
        
        GUI.setVisible(true);
		timer.start();
        GUIText("Welcome to ArkBot " + version + " " + ArkBotSettings.GetSetting("Username") +"!");
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
		ArkBot.log.WriteLog("Console: " + timeStamp + ": " + text);
	}
	
	public static void refreshClientText() {
		ArkBotGUI.GUIText("Running refresh");
		String clients = "";
		String id = "default";
		String user = "default";
		String cntrlByUser = "default";
		Map.Entry<Integer,ClientStruct> elem = new AbstractMap.SimpleEntry<Integer, ClientStruct>(0, ArkBot.cStruct);
		ClientStruct cStruct;
		Iterator it = Server.serverList.entrySet().iterator();
		while (it.hasNext()) {
			elem = (Map.Entry)it.next();
			id = elem.getKey().toString();
			cStruct = (ClientStruct)elem.getValue();
			user = cStruct.getUser();
			cntrlByUser = cStruct.getCntrlUser();
			clients += "\n" + id + ":" + user + " " + cntrlByUser;
			ArkBotGUI.GUIText("Clients: " + clients);
		}
		clientText.setText(clients);
	}
	
	public static void ChatText (String text) {
		String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		chatText.setText(chatText.getText() + "\n" + timeStamp + ": " + text);
		ArkBot.log.WriteLog("Chat: " + timeStamp + ": " + text);
	}
	
	public void SetupPrompt() {
		if (ArkBotSettings.GetSetting("Username").equals("default")) {
			String user = JOptionPane.showInputDialog(GUI, "Welcome to Arkbot!\nPlease enter your username:");
			ArkBotSettings.UpdateSetting("Username", user);
		}
		if (ArkBotSettings.GetSetting("SetupPrompt").equals("true")) {
			JPanel msgPanel = new JPanel();
			msgPanel.setLayout(new BorderLayout());
			JLabel requirements = new JLabel("<html>Please ensure the following are true:<br>"
					+ "1) Ark Resolution is set via Settings<br>"
				    + "2) Ark window is located in the top left corner of your default monitor<br>"
				    + "3) You are currently logged in/connected to a server<br>"
				    + "4) Inventory is in small mode (press q while in inventory)<br> </html>");
			JCheckBox dontAsk = new JCheckBox("Do not prompt me again");
			dontAsk.setHorizontalAlignment(JCheckBox.RIGHT);
			msgPanel.add(requirements, BorderLayout.NORTH);
			msgPanel.add(dontAsk, BorderLayout.SOUTH);
			JOptionPane.showConfirmDialog(GUI, msgPanel,"Ark Settings", JOptionPane.PLAIN_MESSAGE);
			if (dontAsk.isSelected()) {
				ArkBotSettings.UpdateSetting("SetupPrompt", "false");
			}
		}
	}
	
	private void ServerClientSelector() {
		
	}
	
	public static void Serverize() {
		// Remove Non Server Essential Panels/Frames
        ArkBotGUI.GUI.getContentPane().removeAll();
        
        JPanel bgPanel = new BgPanel(new ImageIcon("ArkBotFiles/Images/ArkBotBackground.png").getImage());
        bgPanel.setLayout(new BorderLayout());
        
        // Add Server GUI Components
        // Title Panel
        JPanel Title = new JPanel();
        Title.setLayout(new BorderLayout());
        Title.setOpaque(false);
        JLabel image = new JLabel("", new ImageIcon(img.getScaledInstance(200, 200, 0)), JLabel.CENTER);
        JPanel imgPanel = new JPanel(new BorderLayout());
        imgPanel.setOpaque(false);
        imgPanel.add(image);
        
        // Client Display Panel
        JPanel clientDisplay = new JPanel();
        clientDisplay.setLayout(new GridLayout(0,1));
        clientDisplay.setOpaque(false);
        
        //{{ clientDisplay
        JPanel clientListP = new JPanel();
        clientListP.setLayout(new BorderLayout());
        clientListP.setOpaque(false);
        clientListP.setBorder(BorderFactory.createTitledBorder(new EmptyBorder(0,0,0,0), "ArkBot Clients", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
        
        clientText = new JTextArea(24, 10);
        DefaultCaret cCaret = (DefaultCaret)textLog.getCaret();
        cCaret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        Font cFont = new Font("Arial", Font.PLAIN, 12);
        clientText.setFont(cFont);
        clientText.setForeground(Color.WHITE);
        clientText.setEditable ( false );
        clientText.setLineWrap(true);
        clientText.setOpaque(false);
        JScrollPane cScroll = new JScrollPane ( clientText );
        cScroll.setOpaque(false);
        cScroll.getViewport().setOpaque(false);
        cScroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED );
        cScroll.setHorizontalScrollBarPolicy ( ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
        //}}
        
        // Text Display Panel
        JPanel textDisplay = new JPanel();
        textDisplay.setLayout(new GridLayout(0,1));
        textDisplay.setOpaque(false);
        
        //{{ textLog
        JPanel Logger = new JPanel();
        Logger.setLayout(new BorderLayout());
        Logger.setOpaque(false);
        Logger.setBorder(BorderFactory.createTitledBorder(new EmptyBorder(0,0,0,0), "ArkBot Log", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
        
        textLog = new JTextArea(14, 10);
        DefaultCaret tCaret = (DefaultCaret)textLog.getCaret();
        tCaret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        Font tFont = new Font("Arial", Font.PLAIN, 12);
        textLog.setFont(tFont);
        textLog.setForeground(Color.WHITE);
        textLog.setEditable ( false );
        textLog.setLineWrap(true);
        textLog.setOpaque(false);
        JScrollPane tScroll = new JScrollPane ( textLog );
        tScroll.setOpaque(false);
        tScroll.getViewport().setOpaque(false);
        tScroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED );
        tScroll.setHorizontalScrollBarPolicy ( ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
        //}}
        
        //{{ Mouse Position
        JPanel mousePanel = new JPanel();
        mousePanel.setLayout(new GridLayout(1,3));
        PointerInfo q = MouseInfo.getPointerInfo();
        Point p = q.getLocation();
        long elapsed = (System.currentTimeMillis() - start);
        String display = String.format("%02d:%02d:%02d", elapsed / 3600000, (elapsed % 3600000) / 60, (elapsed % 3600000));
        Runtime = new JLabel("Runtime: " + display, JLabel.RIGHT);
        mousePos = new JLabel("Mouse: " + p.x + " " + p.y + "                        ");
        JLabel currentVersion = new JLabel("          ArkBot " + ArkBot.version + "                ", JLabel.RIGHT);
        mousePanel.add(mousePos);
        mousePanel.add(currentVersion);
        mousePanel.add(Runtime);
        //}}
        
        Title.add(imgPanel, BorderLayout.CENTER);
        
        clientListP.add(cScroll, BorderLayout.NORTH);
        
        Logger.add(tScroll, BorderLayout.NORTH);
        Logger.add(mousePanel, BorderLayout.SOUTH);
        
        clientDisplay.add(clientListP, BorderLayout.NORTH);
        textDisplay.add(Logger, BorderLayout.SOUTH);
                
        bgPanel.add(Title, BorderLayout.NORTH);
        bgPanel.add(clientDisplay, BorderLayout.CENTER);
        bgPanel.add(textDisplay, BorderLayout.SOUTH);
        
        GUI.add(bgPanel);
        GUI.setContentPane(bgPanel); 
        
        ArkBotGUI.GUI.revalidate();
        ArkBotGUI.GUI.repaint();
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
