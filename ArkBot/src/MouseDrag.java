import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;

public class MouseDrag{
	private Robot bot;
	public MouseDrag()  throws AWTException	{
		this.bot = ArkBot.bot;
	}
	public void set(Point q) {
		bot.delay(200);
		bot.mouseMove(q.x, q.y);
	}
	
	public void move(Point q) {
		int x = q.x;
		int y = q.y;
		Point p = MouseInfo.getPointerInfo().getLocation();
		bot.setAutoDelay(2);
		if (p.x < x) {
			if (p.y < y) {
				if ((y-p.y) > (x-p.x)) {
					while (p.x < x) {
						bot.mouseMove(p.x++, p.y++);
					}
					while (p.y <= y) {
						bot.mouseMove(p.x, p.y++);
					}
				} else {
					while (p.y < y) {
						bot.mouseMove(p.x++, p.y++);
					}
					while (p.x <= x) {
						bot.mouseMove(p.x++, p.y);
					}
				}
			} else {
				if ((p.y-y) > (x-p.x)) {
					while (p.x < x) {
						bot.mouseMove(p.x++, p.y--);
					}
					while (p.y >= y) {
						bot.mouseMove(p.x, p.y--);
					}
				} else {
					while (p.y > y) {
						bot.mouseMove(p.x++, p.y--);
					}
					while (p.x <= x) {
						bot.mouseMove(p.x++, p.y);
					}
				}
			}
		} else {
			if (p.y < y) {
				if ((y-p.y) > (p.x - x)) {
					while (p.x > x) {
						bot.mouseMove(p.x--, p.y++);
					}
					while (p.y <= y) {
						bot.mouseMove(p.x, p.y++);
					}
				} else {
					while (p.y < y) {
						bot.mouseMove(p.x--, p.y++);
					}
					while (p.x >= x) {
						bot.mouseMove(p.x--, p.y);
					}
				}
			} else {
				if ((p.y-y) > (p.x-x)) {
					while (p.x > x) {
						bot.mouseMove(p.x--, p.y--);
					}
					while (p.y >= y) {
						bot.mouseMove(p.x, p.y--);
					}
				} else {
					while (p.y > y) {
						bot.mouseMove(p.x--, p.y--);
					}
					while (p.x >= x) {
						bot.mouseMove(p.x--, p.y);
					}
				}
			}
		}
		bot.setAutoDelay(5);
	}
}
