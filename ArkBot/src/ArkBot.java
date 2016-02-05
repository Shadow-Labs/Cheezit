import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;




public class ArkBot {
	
	Robot bot = new Robot();
	int pause = 50;
	
	public static void main(String[] args) throws AWTException
	{
		new ArkBot();		
	}
	
	public ArkBot() throws AWTException
	{
		bot.setAutoDelay(10);
	    bot.setAutoWaitForIdle(true);
	     
	    bot.delay(1000);
	    FocusARK();
	    
	    bot.delay(pause);
	    //MoveForward(1000, true);
	    
	    //type(KeyEvent.VK_W);
	    bot.delay(pause);
	    LookLeft(90);
	    
	    bot.delay(500);
	    System.exit(0);

	  }
	
	//-----------------ADMINISTRATIVE FUNCTIONALITY-----------------
	private void FocusARK()
	{
		bot.delay(pause);
		bot.mouseMove(720, 160);
		
		bot.delay(pause);
		leftClick();
		
		bot.delay(pause);
		bot.mouseMove(720, 320);
		
		bot.delay(pause);
		leftClick();
	}
	
	private void MouseDrag()
	{
		
	}
	
	// --------------------MOVEMENT FUNCTIONALITY-------------------
	private void MoveForward (int duration, boolean sprint) {
		if (sprint)
		{
			bot.delay(pause);
			while (duration > 0)
			{
				bot.keyPress(KeyEvent.VK_SHIFT);
				bot.keyPress(KeyEvent.VK_W);
				duration-=8;
			}
		}
		while (duration > 0)
		{
			bot.keyPress(KeyEvent.VK_W);
			duration-=8;
		}
		bot.delay(pause);
		bot.keyRelease(KeyEvent.VK_W);
		bot.delay(pause);
		bot.keyRelease(KeyEvent.VK_SHIFT);
	}
	private void MoveLeft (int duration) {
		bot.delay(pause);
		bot.keyPress(KeyEvent.VK_A);
		while (duration > 0)
		{
			duration--;
			bot.delay(10);
		}
		bot.delay(pause);
		bot.keyRelease(KeyEvent.VK_A);
	}
	private void MoveRight (int duration) {
		bot.delay(pause);
		bot.keyPress(KeyEvent.VK_D);
		while (duration > 0)
		{
			duration--;
			bot.delay(10);
		}
		bot.delay(pause);
		bot.keyRelease(KeyEvent.VK_D);
	}
	private void MoveBackward (int duration) {
		bot.delay(pause);
		bot.keyPress(KeyEvent.VK_S);
		while (duration > 0)
		{
			duration--;
			bot.delay(10);
		}
		bot.delay(pause);
		bot.keyRelease(KeyEvent.VK_S);
	}
	private void LookLeft (int duration) {
		bot.mouseMove(720, 450);
//		while (duration > 0)
//		{
//			duration-=80;
//			bot.mouseMove(720-(720-duration), 450);
//		}
	}
	
	  // -----------------CHAT FUNCTIONALITY------------------------
	  private void GlobalChat(String text) 
	  {
		  bot.delay(pause);
		  type(KeyEvent.VK_ENTER);
		    
		  bot.delay(pause);
		  type(text);
		    
		  bot.delay(pause);
		  type(KeyEvent.VK_ENTER);
	  }
	
	  private void TribeChat(String text) 
	  {
		  bot.delay(pause);
		  type(KeyEvent.VK_SLASH);
		    
		  bot.delay(pause);
		  type(text);
		    
		  bot.delay(pause);
		  type(KeyEvent.VK_ENTER);
	  }
	  
	  private void LocalChat(String text) 
	  {
		  bot.delay(pause);
		  type(KeyEvent.VK_INSERT);
		    
		  bot.delay(pause);
		  type(text);
		    
		  bot.delay(pause);
		  type(KeyEvent.VK_ENTER);
	  }
	  
	  //-----------------------BASIC FUNCTIONALITY------------------------
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
	 
	  private void type(String s)
	  {
	    byte[] bytes = s.getBytes();
	    for (byte b : bytes)
	    {
	      int code = b;
	      // keycode only handles [A-Z] (which is ASCII decimal [65-90])
	      if (code > 96 && code < 123) code = code - 32;
	      bot.delay(40);
	      bot.keyPress(code);
	      bot.keyRelease(code);
	    }
	  }
	}
