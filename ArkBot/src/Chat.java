import java.awt.Robot;
import java.awt.event.KeyEvent;

public class Chat {
	private Robot bot;
	private int PAUSE;
	public Chat (Robot bot, int PAUSE) {
		this.bot = bot;
		this.PAUSE = PAUSE;
	}
		  // -----------------CHAT FUNCTIONALITY------------------------
		  public void GlobalChat(String text) 
		  {
			  bot.delay(PAUSE);
			  type(KeyEvent.VK_ENTER);
			  
			  bot.delay(PAUSE);
			  type(text);
			    
			  bot.delay(PAUSE);
			  type(KeyEvent.VK_ENTER);
		  }
		
		  public void TribeChat(String text) 
		  {
			  bot.delay(PAUSE);
			  type(KeyEvent.VK_SLASH);
			    
			  bot.delay(PAUSE);
			  type(text);
			    
			  bot.delay(PAUSE);
			  type(KeyEvent.VK_ENTER);
		  }
		  
		  public void LocalChat(String text) 
		  {
			  bot.delay(PAUSE);
			  type(KeyEvent.VK_INSERT);
			    
			  bot.delay(PAUSE);
			  type(text);
			    
			  bot.delay(PAUSE);
			  type(KeyEvent.VK_ENTER);
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
