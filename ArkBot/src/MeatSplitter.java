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
	public MeatSplitter(Robot bot, Point p, int PAUSE)  throws AWTException {
		this.bot = bot;
		this.p = p;
		this.PAUSE = PAUSE;
		drag = new MouseDrag(bot, p);
	}
	public void CharInvSearch() {
		
	}
	public void SplitStack(Point q) {
		Point k = new Point(q.x - (int) ((Math.random() * 5) + 5), q.y - (int) ((Math.random() * 10) + 15));
		// WHILE CYAN split,
		drag.move(q);
		bot.keyPress(KeyEvent.VK_CONTROL);
		System.out.println("q: " + q);
		System.out.println("Blue: " + bot.getPixelColor(q.x, q.y).getBlue());
		System.out.println("Green: " + bot.getPixelColor(q.x, q.y).getGreen());
		System.out.println("Red: " + bot.getPixelColor(q.x, q.y).getRed());
		while (bot.getPixelColor(q.x, q.y).getBlue() >= 250 
				&& bot.getPixelColor(q.x, q.y).getGreen() >= 250 
				&& bot.getPixelColor(q.x, q.y).getRed() == 0) {
			drag.move(q);
			bot.delay(PAUSE);
			bot.mousePress(InputEvent.BUTTON1_MASK);
			drag.move(k);
			bot.mouseRelease(InputEvent.BUTTON1_MASK);
			k = new Point(q.x - (int) ((Math.random() * 5) + 5), q.y - (int) ((Math.random() * 10) + 15));
		}
		bot.keyRelease(KeyEvent.VK_CONTROL);
	}

}
