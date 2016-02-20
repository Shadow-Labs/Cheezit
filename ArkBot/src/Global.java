import java.awt.Point;

public class Global {
	static int PAUSE;
	static Point[] INV_POINTS;
	static Point INV_SEARCH_BAR;
	static Point INV_SCROLL_BOT;
	static Point INV_SCROLLTEST_PIXEL;
	static Point EXT_INV_SEARCHBAR;
	static Point EXT_INV_REMOTEUSE;
	static Point EXT_INV_FIRSTSLOT;
	static Point FOCUS;
	static Point OPTIONS;
	static Point MENU_INVENTORY;
	static Point CAMERA_FOV;
	static Point H_SENSITIVITY;
	static Point V_SENSITIVITY;
	static Point APPLY;
	static Point SAVE;
	
	public Global(int ResX, int ResY) {
		Point center = new Point(ResX/2, ResY/2);
		PAUSE = 50;
		
		if (ResX == 1440 && ResY == 900) {
			INV_SEARCH_BAR = new Point(470, 150);
			INV_SCROLL_BOT = new Point(481, 563);
			INV_SCROLLTEST_PIXEL = new Point(481,232);
			EXT_INV_SEARCHBAR = new Point(928, 128);
			EXT_INV_REMOTEUSE = new Point(884, 653);
			EXT_INV_FIRSTSLOT = new Point(550, 320);
			FOCUS = new Point(center.x, 160);
			OPTIONS = new Point(center.x, 423);
			MENU_INVENTORY = new Point(center.x, 508);
			CAMERA_FOV = new Point(634, 385);
			H_SENSITIVITY = new Point(678, 440);
			V_SENSITIVITY = new Point(678, 475);
			APPLY = new Point(797, 852);
			SAVE = new Point(582, 852);
			
			
//			Point[] points = {
//					new Point(82,280),
//					new Point(172,280),
//					new Point(262,280),
//					new Point(352,280),
//					new Point(442,280),
//					new Point(82,370),
//					new Point(172,370),
//					new Point(262,370),
//					new Point(352,370),
//					new Point(442,370),
//					new Point(82,460),
//					new Point(172,460),
//					new Point(262,460),
//					new Point(352,460),
//					new Point(442,460),
//					new Point(82,550),
//					new Point(172,550),
//					new Point(262,550),
//					new Point(352,550),
//					new Point(442,550),
//					new Point(82,640),
//					new Point(172,640),
//					new Point(262,640),
//					new Point(352,640),
//					new Point(442,640)
//					};
			Point[] points = {
					new Point(78,280),
					new Point(168,280),
					new Point(258,280),
					new Point(348,280),
					new Point(438,280),
					new Point(78,370),
					new Point(168,370),
					new Point(258,370),
					new Point(348,370),
					new Point(438,370),
					new Point(78,460),
					new Point(168,460),
					new Point(258,460),
					new Point(348,460),
					new Point(438,460),
					new Point(78,550),
					new Point(168,550),
					new Point(258,550),
					new Point(348,550),
					new Point(438,550),
					new Point(78,640),
					new Point(168,640),
					new Point(258,640),
					new Point(348,640),
					new Point(438,640)
					};
			INV_POINTS = points;
		}
	}
}
