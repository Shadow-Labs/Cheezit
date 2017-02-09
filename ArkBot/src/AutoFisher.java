import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class AutoFisher {
	Robot bot;
	public boolean fish;
	public boolean fishing;
	private BufferedImage fprompt;
	
	public AutoFisher() {
		bot = ArkBot.bot;
		fish = false;
		fishing = false;
	}
	
	public void Fishin() {
		if (fish && fishing) {
			Fish();
		}
	}
	
	public void Fish() {
        while (fish && fishing) {
        	// Cast
        	leftClick();
        	
        	fprompt = bot.createScreenCapture(Global.FISHING);
        	while (fish && fishing && !HookedFish(fprompt)) {
        		fprompt = bot.createScreenCapture(Global.FISHING);
        	}
        	
        	while (fish && fishing && HookedFish(fprompt)) {
	        	// Get and Press Letter
	        	char letter = GetLetter(fprompt);
	        	PressChar(letter);
	        	
	        	// Next Letter
        		fprompt = bot.createScreenCapture(Global.FISHING);
        	}
        }
        fishing = false;
	}
	
	private boolean HookedFish(BufferedImage image) {
		boolean hooked = false;
		
		// Based on pixels, determine if letter is present
		
		return hooked;
	}
	
	private char GetLetter(BufferedImage image) {
		char letter = 0;
		
		// Based on pixels, get letter
		
		return letter;
	}
	
	private void PressChar(char c) {
		int keycode = 0;
		switch (c) {
			case 'q': keycode = KeyEvent.VK_Q;
					  break;
			case 'w': keycode = KeyEvent.VK_W;
			 		  break;
			case 'e': keycode = KeyEvent.VK_E;
					  break;
			case 'a': keycode = KeyEvent.VK_A;
			  	      break;
			case 's': keycode = KeyEvent.VK_S;
			  		  break;
			case 'd': keycode = KeyEvent.VK_D;
			  		  break;
			case 'f': keycode = KeyEvent.VK_F;
			  		  break;
			case 'z': keycode = KeyEvent.VK_Z;
			  		  break;
			case 'x': keycode = KeyEvent.VK_X;
			  		  break;
			case 'c': keycode = KeyEvent.VK_C;
			  		  break;
		}
		
		// Press Key
		bot.keyPress(keycode);
		bot.delay(Global.PAUSE);
		bot.keyRelease(keycode);
		bot.delay(Global.PAUSE);
	}
	
	private void leftClick()
	  {
	    bot.mousePress(InputEvent.BUTTON1_MASK);
	    bot.delay(10);
	    bot.mouseRelease(InputEvent.BUTTON1_MASK);
	    bot.delay(10);
	  }
}
