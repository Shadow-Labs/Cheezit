import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Global {
	
	static String HOST;
	static int PORT;
	
	static int PAUSE;
	static int ResX;
	static int ResY;
	static boolean fullscreen;
	static boolean normFullscreen;
	
	static int DEV;
	
	static boolean nextKey;
	static int lastPressed;
	
	static Rectangle CHATBOX;
	static Rectangle TRIBELOG;
	static Rectangle FISHING;
	static Rectangle PlAYERINVENTORY;
	static Rectangle STATS;
	static Rectangle GPS;
	
	static Point[] INV_POINTS;
	static Point[] PLYR_INV_HEALTH;
	static Point CHAR_INV_SEARCH_BAR;
	static Point CHAR_INV_SCROLL_BOT;
	static Point CHAR_INV_SCROLLTEST_PIXEL;
	static Point CHAR_INV_FIRSTSLOT;
	static Point CHAR_INV_FIRSTSLOT_NAME;
	static Point CHAR_INV_USEITEM;
	static Point CHAR_INV_DROPALL;
	static Point EXT_INV_SEARCHBAR;
	static Point EXT_INV_REMOTEUSE;
	static Point EXT_INV_DROPALL;
	static Point EXT_INV_FIRSTSLOT;
	static Point EXT_INV_CENTERSLOT;
	static Point DROP_ACCEPT;
	static Point FOCUS;
	static Point CENTER;
	static Point OPTIONS;
	static Point MENU_INVENTORY;
	static Point CAMERA_FOV;
	static Point H_SENSITIVITY;
	static Point V_SENSITIVITY;
	static Point APPLY;
	static Point SAVE;
	
	// Shortcuts
	static int AGatherStartStop;
	static int AGatherDrop;
	static int AHealIncOne;
	static int AHealIncTen;
	static int AHealAbort;
	static int BreederStartStop;
	static int MSplitter;
	static int APilot;
	static int AFish;
	
	public Global() {
		HOST = "23.28.220.129";
		PORT = 3132;
		
		nextKey = false;
		lastPressed = -1;
		
		Global.ResX = Integer.parseInt(ArkBotSettings.GetSetting("ResX"));
		Global.ResY = Integer.parseInt(ArkBotSettings.GetSetting("ResY"));
		if (ArkBotSettings.GetSetting("Fullscreen").contains("t")) {
			Global.fullscreen = true;
		} else {
			Global.fullscreen = false;
		}
		if (ArkBotSettings.GetSetting("NormFullscreen").contains("t")) {
			Global.normFullscreen = true;
		} else {
			Global.normFullscreen = false;
		}
		
		Global.DEV = Integer.parseInt(ArkBotSettings.GetSetting("Development"));
		
		Point center = new Point(ResX/2, ResY/2);
		PAUSE = 50;
	
		CHATBOX = new Rectangle(12, 260, 450, 140);
		TRIBELOG = new Rectangle();
		//FISHING = new Rectangle(483, 726, 479, 115); OLD
		//FISHING = new Rectangle(646, 858, 643, 167); NEW
//		FISHING = new Rectangle(775, 912, 540, 187); // Even Newer

		FISHING = new Rectangle(697, 855, 529, 175); // Newest
		PlAYERINVENTORY = new Rectangle(30, 682, 444, 173);
		STATS = new Rectangle();
		GPS = new Rectangle(12, 260, 450, 140); // Placeholder rectangle
		
		//CHAR_INV_SEARCH_BAR = new Point(470, 150);
		CHAR_INV_SEARCH_BAR = new Point(327, 182);
		CHAR_INV_SCROLL_BOT = new Point(481, 563);
		CHAR_INV_SCROLLTEST_PIXEL = new Point(481,232);
		CHAR_INV_FIRSTSLOT = new Point(75, 275);
		CHAR_INV_FIRSTSLOT_NAME = new Point(75, 300);
		CHAR_INV_USEITEM = new Point(184, 582);
		//CHAR_INV_DROPALL = new Point(300,610);
		CHAR_INV_DROPALL = new Point(433,184);
//		EXT_INV_SEARCHBAR = new Point(928, 128);
		EXT_INV_REMOTEUSE = new Point(884, 653);
//		EXT_INV_DROPALL = new Point (630, 675);
		EXT_INV_FIRSTSLOT = new Point(550, 320);
		EXT_INV_CENTERSLOT = new Point(710, 320);
		//DROP_ACCEPT = new Point(600, 530);
//		DROP_ACCEPT = new Point(804, 592);

		//Modified for 2020Ark?
		EXT_INV_SEARCHBAR = new Point(1425,181);
		EXT_INV_DROPALL = new Point(1530,181);
		DROP_ACCEPT = new Point(804,593);
		
		FOCUS = new Point(center.x, 160);
		CENTER = new Point(center);
		OPTIONS = new Point(center.x, 423);
		MENU_INVENTORY = new Point(center.x, 508);
		CAMERA_FOV = new Point(634, 385);
		H_SENSITIVITY = new Point(678, 440);
		V_SENSITIVITY = new Point(678, 475);
		APPLY = new Point(797, 852);
		SAVE = new Point(582, 852);
		
		// Found Via jnativehook
		AGatherStartStop = ArkBotSettings.GetShortcut("AGatherStartStop");
		AGatherDrop = ArkBotSettings.GetShortcut("AGatherDrop");
		AHealIncOne = ArkBotSettings.GetShortcut("AHealIncOne");
		AHealIncTen = ArkBotSettings.GetShortcut("AHealIncTen");
		AHealAbort = ArkBotSettings.GetShortcut("AHealAbort");
		BreederStartStop = ArkBotSettings.GetShortcut("BreederStartStop");
		MSplitter = ArkBotSettings.GetShortcut("MSplit");
		APilot = ArkBotSettings.GetShortcut("APilot");
		AFish = ArkBotSettings.GetShortcut("AFish");
		
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
		INV_POINTS =  new Point[] {
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
		PLYR_INV_HEALTH = new Point[] {
				new Point(42,79),
				new Point(132,79),
				new Point(222,79),
				new Point(312,79),
				new Point(402,79),
				new Point(42,170),
				new Point(132,170),
				new Point(222,170),
				new Point(312,170),
				new Point(402,170)
			};
	}
	
	// Save Relevant Global Values to Settings
	public void saveSett() {
		ArkBotSettings.UpdateSetting("ResX", Integer.toString(ResX));
		ArkBotSettings.UpdateSetting("ResY", Integer.toString(ResY));
		if (fullscreen) {
			ArkBotSettings.UpdateSetting("Fullscreen", "true");
		} else {
			ArkBotSettings.UpdateSetting("Fullscreen", "false");
		}
		
		if (normFullscreen) {
			ArkBotSettings.UpdateSetting("NormFullscreen", "true");
		} else {
			ArkBotSettings.UpdateSetting("NormFullscreen", "false");
		}
	}
	
	// Save Relevant Global Values to Shortcuts
	public void saveShort() {
		ArkBotSettings.UpdateShortcut("AGatherStartStop", AGatherStartStop);
		ArkBotSettings.UpdateShortcut("AGatherDrop", AGatherDrop);
		ArkBotSettings.UpdateShortcut("AHealIncOne", AHealIncOne);
		ArkBotSettings.UpdateShortcut("AHealIncTen", AHealIncTen);
		ArkBotSettings.UpdateShortcut("AHealAbort", AHealAbort);
		ArkBotSettings.UpdateShortcut("BreederStartStop", BreederStartStop);
		ArkBotSettings.UpdateShortcut("MSplit", MSplitter);
		ArkBotSettings.UpdateShortcut("APilot", APilot);
		ArkBotSettings.UpdateShortcut("AFish", AFish);
	}
	
	// Set Resolution
	public void setRes() {
		ArkBotGUI.GUIText("Set ArkBot Resolution to " + ResX + "x" + ResY);
		ResX = Integer.parseInt(ArkBotSettings.GetSetting("ResX"));
		ResY = Integer.parseInt(ArkBotSettings.GetSetting("ResY"));
		fullscreen = ArkBotSettings.GetSetting("Fullscreen").startsWith("t");
		normFullscreen = ArkBotSettings.GetSetting("NormFullscreen").startsWith("t");
		float xScale = (float)ResX / 1440 ;
		float yScale = (float)ResY / 900;
		Point center = new Point(ResX/2, ResY/2);
		
		CHATBOX.setBounds(Math.round(12 * xScale), Math.round(260 * yScale), Math.round(450 * xScale), Math.round(140 * yScale));
		TRIBELOG = new Rectangle();
		//FISHING = new Rectangle(Math.round(847 * xScale), Math.round(726 * yScale), 115, 115);
		//FISHING = new Rectangle(Math.round(847 * xScale), Math.round(726 * yScale), 167, 167);
		STATS = new Rectangle();
		
		if (fullscreen) {
			int yoffset = 10;
			CHAR_INV_SEARCH_BAR.setLocation(Math.round(470 * xScale), Math.round((150 * yScale)-yoffset));
			CHAR_INV_SCROLL_BOT.setLocation(Math.round(481 * xScale), Math.round((563 * yScale)-yoffset));
			CHAR_INV_SCROLLTEST_PIXEL.setLocation(Math.round(481 * xScale), Math.round((232 * yScale)-yoffset));
			CHAR_INV_FIRSTSLOT.setLocation(Math.round(75 * xScale), Math.round((275 * yScale)-yoffset));
			CHAR_INV_FIRSTSLOT_NAME.setLocation(Math.round(75 * xScale), Math.round((300 * yScale)-yoffset));
			CHAR_INV_USEITEM.setLocation(Math.round(184 * xScale), Math.round((582 * yScale)-yoffset));
			CHAR_INV_DROPALL.setLocation(Math.round(300 * xScale), Math.round((610 * yScale)-yoffset));
			EXT_INV_SEARCHBAR.setLocation(Math.round(928 * xScale), Math.round((128 * yScale)-yoffset));
			EXT_INV_REMOTEUSE.setLocation(Math.round(884 * xScale), Math.round((653 * yScale)-yoffset));
			EXT_INV_DROPALL.setLocation(Math.round(630 * xScale), Math.round((675 * yScale)-yoffset));
			EXT_INV_FIRSTSLOT.setLocation(Math.round(550 * xScale), Math.round((320 * yScale)-yoffset));
			EXT_INV_CENTERSLOT.setLocation(Math.round(710 * xScale), Math.round((320 * yScale)-yoffset));
			DROP_ACCEPT.setLocation(Math.round(600 * xScale), Math.round((530 * yScale)-yoffset));
			FOCUS.setLocation(center.x, Math.round((160 * yScale)-yoffset));
			CENTER.setLocation(center);
			OPTIONS.setLocation(center.x, Math.round((423 * yScale)-yoffset));
			MENU_INVENTORY.setLocation(center.x, Math.round((508 * yScale)-yoffset));
			CAMERA_FOV.setLocation(Math.round(634 * xScale), Math.round((385 * yScale)-yoffset));
			H_SENSITIVITY.setLocation(Math.round(678 * xScale), Math.round((440 * yScale)-yoffset));
			V_SENSITIVITY.setLocation(Math.round(678 * xScale), Math.round((475 * yScale)-yoffset));
			APPLY.setLocation(Math.round(797 * xScale), Math.round((852 * yScale)-yoffset));
			SAVE.setLocation(Math.round(582 * xScale), Math.round((852 * yScale)-yoffset));
			
		} else if (normFullscreen) {
			CHAR_INV_SEARCH_BAR.setLocation(640,90);
//			CHAR_INV_SCROLL_BOT.setLocation();
//			CHAR_INV_SCROLLTEST_PIXEL.setLocation();
			CHAR_INV_FIRSTSLOT.setLocation(93,264);
//			CHAR_INV_FIRSTSLOT_NAME.setLocation();
			CHAR_INV_USEITEM.setLocation(160,675);
			CHAR_INV_DROPALL.setLocation(400,720);
			EXT_INV_SEARCHBAR.setLocation(1425,181);
			EXT_INV_REMOTEUSE.setLocation(1120,770);
			EXT_INV_DROPALL.setLocation(1530,181);
			EXT_INV_FIRSTSLOT.setLocation(732,332);
//			EXT_INV_CENTERSLOT.setLocation();
			DROP_ACCEPT.setLocation(804,593);
//			FOCUS.setLocation();
			CENTER.setLocation(center);
//			OPTIONS.setLocation();
//			MENU_INVENTORY.setLocation();
//			CAMERA_FOV.setLocation();
//			H_SENSITIVITY.setLocation();
//			V_SENSITIVITY.setLocation();
//			APPLY.setLocation();
//			SAVE.setLocation();		
			
		} else {
			CHAR_INV_SEARCH_BAR.setLocation(Math.round(470 * xScale), Math.round(150 * yScale));
			CHAR_INV_SCROLL_BOT.setLocation(Math.round(481 * xScale), Math.round(563 * yScale));
			CHAR_INV_SCROLLTEST_PIXEL.setLocation(Math.round(481 * xScale), Math.round(232 * yScale));
			CHAR_INV_FIRSTSLOT.setLocation(Math.round(75 * xScale), Math.round(275 * yScale));
			CHAR_INV_FIRSTSLOT_NAME.setLocation(Math.round(75 * xScale), Math.round(300 * yScale));
			CHAR_INV_USEITEM.setLocation(Math.round(184 * xScale), Math.round(582 * yScale));
			CHAR_INV_DROPALL.setLocation(Math.round(300 * xScale), Math.round((610 * yScale)));
			EXT_INV_SEARCHBAR.setLocation(Math.round(928 * xScale), Math.round(128 * yScale));
			EXT_INV_REMOTEUSE.setLocation(Math.round(884 * xScale), Math.round(653 * yScale));
			EXT_INV_DROPALL.setLocation(Math.round(630 * xScale), Math.round(675 * yScale));
			EXT_INV_FIRSTSLOT.setLocation(Math.round(550 * xScale), Math.round(320 * yScale));
			EXT_INV_CENTERSLOT.setLocation(Math.round(710 * xScale), Math.round(320 * yScale));
			DROP_ACCEPT.setLocation(Math.round(600 * xScale), Math.round(530 * yScale));
			FOCUS.setLocation(center.x, Math.round(160 * yScale));
			CENTER.setLocation(center);
			OPTIONS.setLocation(center.x, Math.round(423 * yScale));
			MENU_INVENTORY.setLocation(center.x, Math.round(508 * yScale));
			CAMERA_FOV.setLocation(Math.round(634 * xScale), Math.round(385 * yScale));
			H_SENSITIVITY.setLocation(Math.round(678 * xScale), Math.round(440 * yScale));
			V_SENSITIVITY.setLocation(Math.round(678 * xScale), Math.round(475 * yScale));
			APPLY.setLocation(Math.round(797 * xScale), Math.round(852 * yScale));
			SAVE.setLocation(Math.round(582 * xScale), Math.round(852 * yScale));
		}
	}
}
