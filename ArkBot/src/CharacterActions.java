import java.awt.AWTException;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class CharacterActions {
	
	private Robot bot;
	private Point p;
	private int PAUSE;
	public static Point[] invPoints;
	MouseDrag drag;
	RobotType robtype;
	public CharacterActions(Robot bot, Point p, int PAUSE)  throws AWTException {
		this.bot = bot;
		this.p = p;
		this.PAUSE = PAUSE;
		drag = new MouseDrag(bot, p);
		robtype = new RobotType(bot);
		invPoints = invPointSet();
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
	
	
	
	
	
	public int InvSearch(String type) {
		int stackCount = 0;
		p = MouseInfo.getPointerInfo().getLocation();
		drag.move(p, 470, 150);
		bot.mousePress(MouseEvent.BUTTON1_MASK);
		bot.delay(PAUSE);
		bot.mouseRelease(MouseEvent.BUTTON1_MASK);
		robtype.type(type);
		
		
		return stackCount;
	}
	public void InvScreen() {
		Color p1 = bot.getPixelColor(41,211);
		Color p2 = bot.getPixelColor(34,224);
		Color p3 = bot.getPixelColor(973,474);
		if (p1.getBlue() >= 250 && p1.getGreen() >= 250 && p1.getRed() == 0
				&& p2.getBlue() >= 250 && p2.getGreen() >= 250 && p2.getRed() == 0
				&& p3.getBlue() >= 250 && p3.getGreen() >= 250 && p3.getRed() == 0) {
		} else {
			ArkBotGUI.GUIText("[ACTION]:Opening Inventory.");
			bot.keyPress(KeyEvent.VK_I);
			bot.delay(PAUSE);
			bot.keyRelease(KeyEvent.VK_I);
			bot.delay(500);			
		}
	}
	
	public void InvRemoveType(String type) {
		
	}
	
	
	
	
	private Point[] invPointSet() {
		Point[] points = {
			new Point(82,280),
			new Point(172,280),
			new Point(262,280),
			new Point(352,280),
			new Point(442,280),
			new Point(82,370),
			new Point(172,370),
			new Point(262,370),
			new Point(352,370),
			new Point(442,370),
			new Point(82,460),
			new Point(172,460),
			new Point(262,460),
			new Point(352,460),
			new Point(442,460),
			new Point(82,550),
			new Point(172,550),
			new Point(262,550),
			new Point(352,550),
			new Point(442,550),
			new Point(82,640),
			new Point(172,640),
			new Point(262,640),
			new Point(352,640),
			new Point(442,640)
			};
		return points;
	}
}
