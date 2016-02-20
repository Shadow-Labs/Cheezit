import java.awt.AWTException;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class CharacterActions {
	
	private Robot bot;
	private Point p;
	private int PAUSE;
	MouseDrag drag;
	RobotType robtype;
	public CharacterActions(Point p, int PAUSE)  throws AWTException {
		this.bot = ArkBot.bot;
		this.p = p;
		this.PAUSE = PAUSE;
		drag = new MouseDrag(p);
		robtype = new RobotType(bot);
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
		ArkBotGUI.GUIText("[ACTION]: Searching inventory");
		int stackCount = 0;
		p = MouseInfo.getPointerInfo().getLocation();
		drag.move(ArkBot.global.INV_SEARCH_BAR);
		leftClick();
		bot.delay(Global.PAUSE);
		leftClick();
		robtype.type(type);

		// Stack Count
		ArkBotGUI.GUIText("[ACTION]: Counting stacks");
		int p = 0;
		int ymod = -18;
		int pixelA = bot.getPixelColor(Global.INV_POINTS[p].x,Global.INV_POINTS[p].y + ymod).getRGB();
		int pixelB = bot.getPixelColor(Global.INV_POINTS[p+1].x, Global.INV_POINTS[p+1].y + ymod).getRGB();
		int pixelC = bot.getPixelColor(Global.INV_POINTS[p].x - 60, Global.INV_POINTS[p].y).getRGB();
		System.out.println("PA: " + pixelA);
		System.out.println("PB: " + pixelB);
		System.out.println("PC: " + pixelC);
		if (pixelA-pixelC > 1000 || pixelA-pixelC < -1000 ) {
			System.out.println("Pass");
			while (p <= 19 && (pixelA-pixelB < 1000 && pixelA-pixelB > -1000)) {
				stackCount++;
				p++;
				if (p == 20) {
					// Scroll Down -- Check Scroll Bar
					p = 15;
				}
				pixelA = bot.getPixelColor(Global.INV_POINTS[p].x,Global.INV_POINTS[p].y + ymod).getRGB();
				pixelB = bot.getPixelColor(Global.INV_POINTS[p+1].x, Global.INV_POINTS[p+1].y + ymod).getRGB();
				System.out.println("P1: " + p + " " + bot.getPixelColor(Global.INV_POINTS[p].x,Global.INV_POINTS[p].y + ymod).getRGB());
				System.out.println("P2: " + p + " " + bot.getPixelColor(Global.INV_POINTS[p+1].x, Global.INV_POINTS[p+1].y + ymod).getRGB());
			}
			stackCount++;
		}
		ArkBotGUI.GUIText("[RESULT]: Stack count: " + stackCount);
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
			ArkBotGUI.GUIText("[ACTION]: Opening inventory");
			bot.keyPress(KeyEvent.VK_I);
			bot.delay(PAUSE);
			bot.keyRelease(KeyEvent.VK_I);
			bot.delay(500);			
		}
	}
	
	public void InvRemoveType(String type) {
		
	}
	
	public boolean PixelRange(Point q) {
		boolean found = false;
		int i = 0;
		int s = 7;
		
		q.setLocation(q.x, invYLocator());
		
		Point[] points = {
				new Point(q.x,q.y),
				new Point(q.x - s,q.y),
				new Point(q.x - s,q.y - s),
				new Point(q.x,q.y - s),
				new Point(q.x + s,q.y - s),
				new Point(q.x + s,q.y),
				new Point(q.x + s,q.y + s),
				new Point(q.x,q.y + s),
				new Point(q.x - s,q.y + s),
				};
		while (!found && i < 9) {
			if (bot.getPixelColor(points[i].x, points[i].y).getBlue() >= 250
					&& bot.getPixelColor(points[i].x, points[i].y).getGreen() >= 250
					&& bot.getPixelColor(points[i].x, points[i].y).getRed() == 0) {
				found = true;
			}
			i++;
		}
		return found;
	}
	
	public int invYLocator() {
		int Y = 227;
		boolean found;
		while ((bot.getPixelColor(40, Y).getBlue() < 250
				&& bot.getPixelColor(40, Y).getGreen() < 250
				&& bot.getPixelColor(40, Y).getRed() != 0)
				&& Y <= 560){
			Y--;
		}
		return Y;
	}
	
	  private void leftClick()
	  {
	    bot.mousePress(InputEvent.BUTTON1_MASK);
	    bot.delay(200);
	    bot.mouseRelease(InputEvent.BUTTON1_MASK);
	    bot.delay(200);
	  }
}
