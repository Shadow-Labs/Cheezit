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
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	
	public ArkBotGUI(String version, Point p, ArkBotLog log) 
	{
		start = System.currentTimeMillis();
		this.p = p;
		this.version = version;
		this.log = log;
		timer = new Timer(1000, new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		    	SetMouse();  
		    }
		});
		
		timer.start();		
	}
	
	public void Initialize() {
        GUI = new JFrame("ArkBot " + version);

        JPanel bgPanel = new BgPanel();
        bgPanel.setLayout(new BorderLayout());
		
        Image icon = new ImageIcon("ArkBotFiles/Images/ArkBotLogoIcon.png").getImage();
        
        GUI.setIconImage(icon);
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
        mousePanel.add(Runtime, BorderLayout.EAST);
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
	}
	
	public static void SetMouse() {
		PointerInfo q = MouseInfo.getPointerInfo();
        Point r = q.getLocation();
        long elapsed = (System.currentTimeMillis() - start);
        String display = String.format("%02d:%02d:%02d", (elapsed / (1000 * 60 * 60)) % 24, (elapsed / (1000 * 60)) % 60, (elapsed / 1000) % 60);
        Runtime.setText("Runtime: " + display);
        mousePos.setText("Mouse: " + r.x + " " + r.y);
	}
	
	public static void GUIText (String text, ArkBotLog log) {
		String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		textLog.setText(textLog.getText() + "\n" + timeStamp + ": " + text);
		log.WriteLog(timeStamp + ": " + text);
	}
	
}
