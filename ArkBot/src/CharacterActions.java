import java.awt.AWTException;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class CharacterActions {
	
	private Robot bot;
	private Point p;
	private int PAUSE;
	public CharacterActions(Robot bot, Point p, int PAUSE)  throws AWTException {
		this.bot = bot;
		this.p = p;
		this.PAUSE = PAUSE;
	}
	
	//--------------------Character Action Functionality-------------------
	public int Pickup() {
		int success = 0;
		while(bot.getPixelColor(720, 440).getGreen() <= 250 && bot.getPixelColor(720, 440).getBlue() <= 250) {
			LookDown(3);
		}
		while(bot.getPixelColor(720, 440).getGreen() >= 250 && bot.getPixelColor(720, 440).getBlue() >= 250) {
			bot.delay(PAUSE);
			bot.keyPress(KeyEvent.VK_E);
			bot.delay(PAUSE);
			bot.keyRelease(KeyEvent.VK_E);
		}
		success = 1;
		return success;
	}
	
	public int ObjectInventory() {
		int success = 0;
		Color pixel = bot.getPixelColor(720,440);
		while(pixel.getGreen() <= 250 && pixel.getBlue() <= 250 && pixel.getRed() == 0) {
			LookDown(3);
			pixel = bot.getPixelColor(720,440);
		}
		while(pixel.getGreen() <= 250 && pixel.getBlue() <= 250 && pixel.getRed() == 0) {
			bot.delay(PAUSE);
			bot.keyPress(KeyEvent.VK_F);
			bot.delay(PAUSE);
			bot.keyRelease(KeyEvent.VK_F);
			pixel = bot.getPixelColor(720,440);
		}
		success = 1;
		return success;
	}
	
	public void Facedown() {
		
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
			bot.mouseMove(p.x, p.y - 1);
		}
	}
	public void LookDown (int pixels) {
		bot.delay(250);
		while (pixels > 0)
		{
			pixels--;
			bot.mouseMove(MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y + 1);
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
