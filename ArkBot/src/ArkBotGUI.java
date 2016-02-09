import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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
	public static JFrame GUI;
	public static JTextArea textLog;
	public static JLabel mousePos;
	public static JLabel Runtime;
	public static Timer timer;
	public static ImageIcon icon;
	private static Image img;
	
	public ArkBotGUI(String version, Point p) 
	{
		start = System.currentTimeMillis();
		this.p = p;
		this.version = version;
		this.log = ArkBot.log;
		timer = new Timer(1000, new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		    	SetMouse();  
		    }
		});		
		timer.start();
		
		img = new ImageIcon("ArkBotFiles/Images/ArkBotLogo.png").getImage();
        icon = new ImageIcon(img.getScaledInstance(100, 100, 0));
		
	}
	
	public void Initialize() {
        GUI = new JFrame("ArkBot " + version);

        JPanel bgPanel = new BgPanel();
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

        // Mouse Position
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
        
        // textLog
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

        Logger.add(scroll, BorderLayout.NORTH);
        Logger.add(mousePanel, BorderLayout.SOUTH);
        
        bgPanel.add(Logger, BorderLayout.SOUTH);
        
        GUI.add(bgPanel);
        GUI.setContentPane(bgPanel);
        
        GUI.setVisible(true);
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
					+ "1) Ark Resolution is set to 1440 x 900<br>"
				    + "2) Ark window is located in the top left corner of your default monitor<br>"
				    + "3) You are currently logged in/connected to a server<br> </html>");
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
	
}
