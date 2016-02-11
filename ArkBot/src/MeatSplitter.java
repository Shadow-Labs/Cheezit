import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;

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
	public void Split() {
		// WHILE CYAN split,
	}

}
