import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class CharacterMovement {
	
	private Robot bot;
	private Point p;
	private int PAUSE;
	public CharacterMovement(Robot bot, Point p, int PAUSE)  throws AWTException {
		this.bot = bot;
		this.p = p;
		this.PAUSE = PAUSE;
	}
	// --------------------MOVEMENT FUNCTIONALITY-------------------
	public void MoveForward (int duration, boolean sprint) {
		if (sprint)
		{
			bot.delay(PAUSE);
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
		bot.delay(PAUSE);
		bot.keyRelease(KeyEvent.VK_W);
		bot.delay(PAUSE);
		bot.keyRelease(KeyEvent.VK_SHIFT);
	}
	public void MoveLeft (int duration) {
		bot.delay(PAUSE);
		while (duration > 0)
		{
			bot.keyPress(KeyEvent.VK_A);
			duration-=8;
		}
		bot.delay(PAUSE);
		bot.keyRelease(KeyEvent.VK_A);
	}
	public void MoveRight (int duration) {
		bot.delay(PAUSE);
		while (duration > 0)
		{
			bot.keyPress(KeyEvent.VK_D);
			duration-=8;
		}
		bot.delay(PAUSE);
		bot.keyRelease(KeyEvent.VK_D);
	}
	public void MoveBackward (int duration) {
		bot.delay(PAUSE);
		while (duration > 0)
		{
			bot.keyPress(KeyEvent.VK_S);
			duration-=8;
		}
		bot.delay(PAUSE);
		bot.keyRelease(KeyEvent.VK_S);
	}
	public void LookRight (int pixels) {
		bot.delay(250);
		while (pixels > 0)
		{
			pixels--;
			bot.delay(5);
			bot.mouseMove(p.x + 1, p.y);
		}
	}
	public void LookUp (int pixels) {
		bot.delay(250);
		while (pixels > 0)
		{
			pixels--;
			bot.delay(5);
			bot.mouseMove(p.x, p.y + 1);
		}
	}
	public void LookDown (int pixels) {
		bot.delay(250);
		while (pixels > 0)
		{
			pixels--;
			bot.delay(5);
			bot.mouseMove(p.x, p.y - 1);
		}
	}
	public void LookLeft (int pixels) {
		bot.delay(250);
		while (pixels > 0)
		{
			pixels--;
			bot.delay(5);
			bot.mouseMove(p.x - 1, p.y);
		}
	}
}
