import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
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
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultCaret;


public class ArkBotGUI extends JFrame 
{
	private Point p;
	private String version;
	public static JFrame GUI;
	public static JTextArea textLog;
	public static JLabel xPos;
	public static JLabel yPos;
	
	public ArkBotGUI(String version, Point p) 
	{
		this.p = p;
		this.version = version;
	}
	
	public void Initialize() {
		
        GUI = new JFrame("ArkBot " + version);
        JPanel bgPanel = new BgPanel();
        bgPanel.setLayout(new BorderLayout());
		
        Image icon = new ImageIcon("Files/Images/ArkBotLogoIcon.png").getImage();
        
        GUI.setIconImage(icon);
        GUI.setSize(480,935);
        GUI.setResizable(false);
        GUI.setLocation(1440, 0);
        GUI.setLayout(new FlowLayout());
        //GUI.setContentPane(new ImagePanel(img));
        GUI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Mouse Position
        JPanel mousePanel = new JPanel();
        mousePanel.setLayout(new FlowLayout());
        PointerInfo q = MouseInfo.getPointerInfo();
        p = q.getLocation();
        xPos = new JLabel("Mouse X: " + p.x);
        yPos = new JLabel("Mouse Y: " + p.y);
        mousePanel.add(xPos);
        mousePanel.add(yPos);
        
        
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
        textLog.setOpaque(false);
        JScrollPane scroll = new JScrollPane ( textLog );
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED );

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
        xPos.setText("Mouse X: " + r.x);
        yPos.setText("Mouse Y: " + r.y);
	}
	
	public static void Log (String text) {
		String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		textLog.setText(textLog.getText() + "\n" + timeStamp + ": " + text);		
	}
	
}
