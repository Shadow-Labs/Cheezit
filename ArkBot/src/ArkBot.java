import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;




public class ArkBot {
	
	Robot bot = new Robot();
	public static int PAUSE = 50;
	public static Point p;
	
	public static void main(String[] args) throws AWTException
	{
        JFrame main = new JFrame("dummy...");
        main.setSize(480,900);
        //main.setLocation(, y);
        main.setVisible(true);

        MouseObserver mo = new MouseObserver(main);
        mo.addMouseMotionListener(new MouseMotionListener() {
                public void mouseMoved(MouseEvent e) {
                    p = e.getPoint();
                    System.out.println("Mouse: " + p);
                }

                public void mouseDragged(MouseEvent e) {
                    System.out.println("mouse dragged: " + e.getPoint());
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
	    bot.mouseMove(720, 320);
        CharacterMovement move = new CharacterMovement(bot, p,  PAUSE);
        Setup setup = new Setup(bot, p,  PAUSE);
        
        // Setup Ark
        setup.Settings();
        
		
	     
	    bot.delay(1000);
	    //setup.FocusARK();
	    
	    bot.delay(PAUSE);
	    //move.MoveForward(1000, true);

	    bot.delay(500);
	    //drag.move(bot, p, 350, 300);
	    System.exit(0);

	  }
	}
