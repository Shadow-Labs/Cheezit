import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Setup {
	private Robot bot;
	private Point p;
	private int PAUSE;
	public Setup(Robot bot, Point p, int PAUSE)  throws AWTException {
		this.bot = bot;
		this.p = p;
		this.PAUSE = PAUSE;
	}
	
	//-----------------ADMINISTRATIVE FUNCTIONALITY-----------------
	public void Settings() throws AWTException //Resolution must be set to 1440x900
	{
		
		
//		MouseDrag drag = new MouseDrag(bot, p);
//		
//		// Focus ARK
//		bot.delay(PAUSE);
//		bot.mouseMove(720, 160);
//		bot.delay(PAUSE);
//		leftClick();
//		
//		// Click Options
//		bot.delay(PAUSE);
//		drag.move(MouseInfo.getPointerInfo().getLocation(), 720, 420);
//		bot.delay(PAUSE);
//		leftClick();
//		
//		// Click Camera FOV
//		bot.delay(PAUSE);
//		drag.move(MouseInfo.getPointerInfo().getLocation(), 634, 385);
//		bot.delay(PAUSE);
//		leftClick();
//		
//		// Set Horizontal Sensitivity
//		bot.delay(PAUSE);
//		drag.move(MouseInfo.getPointerInfo().getLocation(), 678, 440);
//		bot.delay(PAUSE);
//		leftClick();
//		
//		// Set Vertical Sensitivity
//		bot.delay(PAUSE);
//		drag.move(MouseInfo.getPointerInfo().getLocation(), 678, 475);
//		bot.delay(PAUSE);
//		leftClick();
//		
//		// Click Apply
//		bot.delay(PAUSE);
//		drag.move(MouseInfo.getPointerInfo().getLocation(), 797, 852);
//		bot.delay(500);
//		leftClick();
//		
//		// Click Save
//		bot.delay(PAUSE);
//		drag.move(MouseInfo.getPointerInfo().getLocation(), 582, 852);
//		bot.delay(PAUSE);
//		leftClick();
//		
//		// Press ESC
//		bot.delay(500);
//		type(KeyEvent.VK_ESCAPE);
		
		
		
	}
	public void FocusARK() throws AWTException
	{
		MouseDrag drag = new MouseDrag(bot, p);
		
		bot.delay(PAUSE);
		drag.move(MouseInfo.getPointerInfo().getLocation(), 720, 160);
		
		bot.delay(PAUSE);
		leftClick();
		
		bot.delay(PAUSE);
		drag.move(MouseInfo.getPointerInfo().getLocation(), 720, 320);
		
		bot.delay(PAUSE);
		leftClick();
	}
	
	public void FocusView()
	{
		bot.delay(PAUSE);
		bot.mouseMove(1, 30);
		
		bot.delay(PAUSE);
		leftClick();
		
		bot.delay(PAUSE);
		bot.mouseMove(720, 320);
		
		bot.delay(PAUSE);
		leftClick();
	}
	
	  private void leftClick()
	  {
	    bot.mousePress(InputEvent.BUTTON1_MASK);
	    bot.delay(200);
	    bot.mouseRelease(InputEvent.BUTTON1_MASK);
	    bot.delay(200);
	  }
	  
	  private void type(int i)
	  {
	    bot.delay(40);
	    bot.keyPress(i);
	    bot.keyRelease(i);
	  }
}
