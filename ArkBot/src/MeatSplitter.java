import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MeatSplitter {
	private Robot bot;
	private Point p;
	private int PAUSE;
	MouseDrag drag;
	CharacterActions action;
	public MeatSplitter()  throws AWTException {
		this.bot = ArkBot.bot;
		this.p = ArkBot.p;
		this.PAUSE = ArkBot.global.PAUSE;
		drag = new MouseDrag(p);
		action = new CharacterActions();
	}
	public void CharInvSearch() {
		
	}
	
//	public void SplitAll() {
//		int stacks = action.InvSearch("Fiber");
//		if (stacks >= 20) {
//			drag.move(Global.CHAR_INV_SCROLL_BOT);
//			leftClick();
//			
//			
//		}
//	}
	
//	public void SplitStack(Point q, int stacks) {
//		if (stacks == 0) {
//			ArkBotGUI.GUIText("[ACTION]: No stacks to split");
//		} else {
//			ArkBotGUI.GUIText("[ACTION]: Splitting stacks");
//			
//			Point k = new Point(q.x - (int) ((Math.random() * 5) + 5), q.y - (int) ((Math.random() * 10) + 20));
//			// WHILE CYAN split,
//			drag.move(q);
//			bot.keyPress(KeyEvent.VK_CONTROL);
//			int pixelRange = 7;
//			System.out.println(action.PixelRange(q, pixelRange) + " Split " + stacks);
//			while (action.PixelRange(q, pixelRange) && stacks > 0) {
//				System.out.println(action.PixelRange(q, pixelRange) + " Split " + stacks);
//				drag.move(q);
//				bot.delay(PAUSE);
//				bot.mousePress(InputEvent.BUTTON1_MASK);
//				drag.move(k);
//				bot.mouseRelease(InputEvent.BUTTON1_MASK);
//				k = new Point(q.x - (int) ((Math.random() * 5) + 5), q.y - (int) ((Math.random() * 10) + 20));
//			}
//			bot.keyRelease(KeyEvent.VK_CONTROL);
//		}
//	}
	
	  private void leftClick()
	  {
	    bot.mousePress(InputEvent.BUTTON1_MASK);
	    bot.delay(200);
	    bot.mouseRelease(InputEvent.BUTTON1_MASK);
	    bot.delay(200);
	  }

}
