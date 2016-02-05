import java.awt.EventQueue;
import javax.swing.JFrame;


public class ArkBotGUI extends JFrame 
{
	public ArkBotGUI() 
	{
		initializeUI();
		
		EventQueue.invokeLater(new Runnable() 
		{
		    @Override
		    public void run() 
		    {
		        ArkBotGUI gui = new ArkBotGUI();
		        gui.setVisible(true);
		    }
		});
	}
	
	private void initializeUI()
	{
		setTitle("ArkBot");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
