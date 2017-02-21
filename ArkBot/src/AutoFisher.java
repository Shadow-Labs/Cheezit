import java.awt.Color;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.KeyStroke;

public class AutoFisher {
	Robot bot;
	private RobotStuff r;
	public boolean fish;
	public boolean fishing;
	public boolean poleSwitch;
	public boolean meatDrop;
	public BufferedImage fprompt;
	private int poleCount;
	private boolean pole;
	private char letter;
	private Point[] points;
	private boolean[] bools;
	
	public AutoFisher() {
		bot = ArkBot.bot;
		r = new RobotStuff(bot);
		poleCount = 1;
		fish = false;
		fishing = false;
		poleSwitch = false;
		meatDrop = false;
		points = new Point[17];
		points[0] = new Point(379, 13);		// Letter vvv
		points[1] = new Point(392, 13);
		points[2] = new Point(422, 13);
		points[3] = new Point(447, 13);
		points[4] = new Point(463, 13);
		points[5] = new Point(392, 47);
		points[6] = new Point(420, 47);
		points[7] = new Point(440, 47);
		points[8] = new Point(452, 47);
		points[9] = new Point(390, 80);
		points[10] = new Point(421, 80);
		points[11] = new Point(449, 80);
		points[12] = new Point(435, 96);
		points[13] = new Point(403, 61);	// Letter ^^^
		points[14] = new Point(45, 14);		// Hooked vvv
		points[15] = new Point(210, 58);
		points[16] = new Point(277, 58);	// Hooked ^^^
		bools = new boolean[17];
		for (int i = 0; i < bools.length; i++) {
			bools[i] = false;
		}
	}
	
	public void Fishin() {
		if (fish && fishing) {
			Fish();
		}
	}
	
	public void Fish() {
        while (fish && fishing) {
        	// Cast
        	ArkBotGUI.GUIText("[AUTOFISHER] Casting");
        	leftClick();
        	bot.delay(3000);
        	
    		// Open Inv
//    		bot.keyPress(KeyEvent.VK_I);
//    		bot.delay(Global.PAUSE);
//    		bot.keyRelease(KeyEvent.VK_I);
//    		bot.delay(2000);
        	
        	// Drop Meat
        	if (meatDrop) {
        		dropMeat();
        	}
        	
        	// Check for Pole Break
        	if (poleSwitch) {
	        	pole = poleExists(poleCount);
	        	if (!pole) {
	        		ArkBotGUI.GUIText("[AUTOFISHER] Pole Status: Bad");
	        		poleCount++;
	        	} else {
	        		ArkBotGUI.GUIText("[AUTOFISHER] Pole Status: Good");
	        	}
        	}
        	
        	// Exit Ext Inv
//    		bot.keyPress(KeyEvent.VK_ESCAPE);
//    		bot.delay(Global.PAUSE);
//    		bot.keyRelease(KeyEvent.VK_ESCAPE);
//    		bot.delay(700);
    		
    		// Switch Poles
    		if (poleSwitch) {
	    		if (!pole) {
	    			bot.delay(500);
	    			if (poleCount != 10) {
	    				r.type((char)(poleCount + '0'));
	    	    		ArkBotGUI.GUIText("[AUTOFISHER] Switched to pole " + poleCount);
	    			} else if (poleCount == 10){
	    				r.type((char)(0 + '0'));
	    	    		ArkBotGUI.GUIText("[AUTOFISHER] Switched to pole " + poleCount);
	    			} else {
	    				poleCount = 1;
	    				ArkBot.state.fisher.fish = false;
	    				ArkBotGUI.GUIText("[AUTOFISHER] Uh Ohh! You've run out of poles!");
	    			}
		    		bot.delay(3000);
		    		ArkBotGUI.GUIText("[AUTOFISHER] Casting");
		    		leftClick();
	    		}
    		}
    		
        	fprompt = bot.createScreenCapture(Global.FISHING);
        	setBools(fprompt);
        	while (fish && fishing && !HookedFish()) {
        		fprompt = bot.createScreenCapture(Global.FISHING);
        		setBools(fprompt);
        	}
        	
        	while (fish && fishing && HookedFish()) {
	        	// Get and Press Letter
	        	char letter = GetLetter(fprompt);
	        	ArkBotGUI.GUIText("[FISHER] Letter: " + letter);
	        	PressChar(letter);
	        	// Pause for game to prompt next letter/randomize
	        	bot.delay(r.randomPause(750, 1250));
	        	
	        	// Next Letter
        		fprompt = bot.createScreenCapture(Global.FISHING);
        	}
        }
        fishing = false;
	}
	
	private boolean HookedFish() {
		boolean hooked = false;
		// Determine if fish is hooked
		if (bools[14] && bools[15] && bools[16]) {
			hooked = true;
		}
		return hooked;
	}
	
	private void setBools (BufferedImage image) {
		for(int i = 0; i < bools.length; i++) {
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
		letter = ' ';
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
			case 'z': keycode = KeyEvent.VK_Z;
			  		  break;
			case 'x': keycode = KeyEvent.VK_X;
			  		  break;
			case 'c': keycode = KeyEvent.VK_C;
			  		  break;
		}
		
		// Press Key
		if (keycode != 0) {
			bot.keyPress(keycode);
			bot.delay(Global.PAUSE);
			bot.keyRelease(keycode);
			bot.delay(Global.PAUSE);
		}
	}
	
	// Drop Meat
	private void dropMeat() {
		ArkBotGUI.GUIText("[AUTOFISHER] Dropping Meat.");		
		ArkBot.state.act.CharInvSearch("Meat");
		ArkBot.state.act.CharDropAll();
	}
	
	private boolean poleExists(int hotbar) {
		boolean pole = false;
		BufferedImage image = bot.createScreenCapture(Global.PlAYERINVENTORY);
		
		int rgba = image.getRGB((int)Global.PLYR_INV_HEALTH[hotbar-1].getX(),(int)Global.PLYR_INV_HEALTH[hotbar-1].getY());
		Color c = new Color(rgba, true);
		// Green Health
		if (c.getRed() >= 85 && c.getRed() <= 105 
				&& c.getGreen() >= 165 && c.getGreen() <= 185
				&& c.getBlue() >= 65 && c.getBlue() <= 85) {
			pole = true;
		// Red Health
		} else if (c.getRed() >= 145 && c.getRed() <= 165 
					&& c.getGreen() >= 85 && c.getGreen() <= 95
					&& c.getBlue() >= 60 && c.getBlue() <= 80) {
			pole = true;
		}
		return pole;
	}
	
	private void leftClick()
	  {
	    bot.mousePress(InputEvent.BUTTON1_MASK);
	    bot.delay(10);
	    bot.mouseRelease(InputEvent.BUTTON1_MASK);
	    bot.delay(10);
	  }
}
