import java.awt.Point;

public class Global {
	static int PAUSE;
	static Point[] INV_POINTS;
	static Point INV_SEARCH_BAR;
	static Point FOCUS;
	static Point OPTIONS;
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
			FOCUS = new Point(center.x, 160);
			OPTIONS = new Point(center.x, 385);
			CAMERA_FOV = new Point(634, 385);
			H_SENSITIVITY = new Point(678, 440);
			V_SENSITIVITY = new Point(678, 475);
			APPLY = new Point(797, 852);
			SAVE = new Point(582, 852);
			
			
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
			INV_POINTS = points;
		}
	}
}
