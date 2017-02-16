import java.awt.Color;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class AutoFisher {
	Robot bot;
	public boolean fish;
	public boolean fishing;
	public BufferedImage fprompt;
	private char letter;
	private Point[] points;
	private boolean[] bools;
	
	public AutoFisher() {
		bot = ArkBot.bot;
		fish = false;
		fishing = false;
		points = new Point[14];
		points[0] = new Point(15, 13);
		points[1] = new Point(28, 13);
		points[2] = new Point(58, 13);
		points[3] = new Point(83, 13);
		points[4] = new Point(99, 13);
		points[5] = new Point(28, 47);
		points[6] = new Point(56, 47);
		points[7] = new Point(76, 47);
		points[8] = new Point(88, 47);
		points[9] = new Point(26, 80);
		points[10] = new Point(57, 80);
		points[11] = new Point(85, 80);
		points[12] = new Point(71, 96);
		points[13] = new Point(39, 61);
		bools = new boolean[14];
		bools[0] = false;
		bools[1] = false;
		bools[2] = false;
		bools[3] = false;
		bools[4] = false;
		bools[5] = false;
		bools[6] = false;
		bools[7] = false;
		bools[8] = false;
		bools[9] = false;
		bools[10] = false;
		bools[11] = false;
		bools[12] = false;
		bools[13] = false;
	}
	
	public void Fishin() {
		if (fish && fishing) {
			Fish();
		}
	}
	
	public void Fish() {
        while (fish && fishing) {
        	// Cast
        	//leftClick();
        	
        	fprompt = bot.createScreenCapture(Global.FISHING);
        	while (fish && fishing && !HookedFish(bot.createScreenCapture(Global.FISHHOOKED))) {
        		fprompt = bot.createScreenCapture(Global.FISHING);
        	}
        	
        	while (fish && fishing && HookedFish(fprompt)) {
	        	// Get and Press Letter
	        	char letter = GetLetter(fprompt);
	        	ArkBotGUI.GUIText("Fishing Letter: " + letter);
	        	//PressChar(letter);
	        	
	        	// Next Letter
        		fprompt = bot.createScreenCapture(Global.FISHING);
        	}
        }
        fishing = false;
	}
	
	private boolean HookedFish(BufferedImage image) {
		boolean hooked = true;
		
		// Based on pixels, determine if letter is present
		// boolean needs to be true the entire time the fish is hooked
		
		return hooked;
	}
	
	private void setBools (BufferedImage image) {
		for(int i = 0; i <= 13; i++) {
			int rgba = image.getRGB((int)points[i].getX(),(int)points[i].getY());
			Color c = new Color(rgba, true);
			if (c.getBlue() >= 250 && c.getGreen() >= 250 && c.getRed() >= 250) {
				bools[i] = true;
			} else {
				bools[i] = false;
			}
		}
	}
	private char GetLetter(BufferedImage image) {
		letter = 'f';
		setBools(image);
				
		// Based on pixels, get letter
		// Q
		if (bools[5] && bools[8] && bools[12]) {
			letter = 'q';
			return 'q';
		}
		// W
		if (bools[0] && bools[4] && !bools[10]) {
			letter = 'w';
			return 'w';
		}
		// E
		if (bools[2] && bools[6] && bools[7] && bools[13]) {
			letter = 'e';
			return 'e';
		}
		// A
		if (bools[2] && bools[7] && bools[9] && bools[11]) {
			letter = 'a';
			return 'a';
		}
		// S
		if (bools[2] && bools[6] && bools[7] && !bools[13]) {
			letter = 's';
			return 's';
		}
		// D
		if (bools[1] && bools[2] && bools[5] && bools[8] && bools[10]) {
			letter = 'd';
			return 'd';
		}
		// Z
		if (bools[2] && bools[3] && bools[6] && bools[10]) {
			letter = 'z';
			return 'z';
		}
		// X
		if (bools[1] && bools[3] && bools[6] && bools[9] && bools[11]) {
			letter = 'x';
			return 'x';
		}
		// C
		if (bools[2] && bools[3] && bools[5] && bools[13]) {
			letter = 'c';
			return 'c';
		}
				
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
