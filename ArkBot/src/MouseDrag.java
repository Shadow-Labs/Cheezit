import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;

public class MouseDrag{
	private Robot bot;
	private Point p;
	public MouseDrag(Robot bot, Point p)  throws AWTException	{
		this.bot = bot;
		this.p = p;
	}
	public void move(Point p, int x, int y) {
		p.setLocation(p.x, p.y);
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
	}
}
