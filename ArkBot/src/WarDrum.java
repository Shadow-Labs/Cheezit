import java.awt.Robot;
import java.awt.event.KeyEvent;

public class WarDrum {
	
	static boolean play;
	static boolean playDM;
	static boolean playWF;
	static boolean playNAC;
	static boolean playDKR;
	static boolean playC;
	static boolean playI1;
	static boolean playT;
	private int a;
	private int b;
	private int c;
	private int d;
	private int e;
	
	private Robot bot;
	public WarDrum() {
		this.bot = ArkBot.bot;
		play = false;
		a = KeyEvent.VK_1;
		b = KeyEvent.VK_2;
		c = KeyEvent.VK_3;
		d = KeyEvent.VK_4;
		e = KeyEvent.VK_5;
	}
	public void Drumming() {
		if (playDM) {
			ArkBotGUI.GUIText("Playing WarDrum: DeathMarch");
			DeathMarch();
		} else if (playWF) {
			ArkBotGUI.GUIText("Playing WarDrum: WarFrenzy");
			WarFrenzy();
		} else if (playNAC) {
			ArkBotGUI.GUIText("Playing WarDrum: NativeAmericanChant");
			NativeAmericanChant();
		} else if (playDKR) {
			ArkBotGUI.GUIText("Playing WarDrum: DarkKnightRises");
			DarkKnightRises();
		} else if (playC) {
			ArkBotGUI.GUIText("Playing WarDrum: Charge");
			Charge();
		} else if (playI1) {
			ArkBotGUI.GUIText("Playing WarDrum: IntensityOne");
			IntensityOne();
		} else if (playT) {
			ArkBotGUI.GUIText("Playing WarDrum: Train");
			Train();
		}
	}
	
	private void DeathMarch() {
		int pause = 500;
		while (play) {
			bot.keyPress(a);
			bot.delay(10);
			bot.keyRelease(a);
			
			bot.delay(pause);
			
			bot.keyPress(d);
			bot.keyPress(e);
			bot.delay(10);
			bot.keyRelease(d);
			bot.keyRelease(e);
			
			bot.delay(pause);
		}
		if (playDM) {
			playDM = false;
		}
		ArkBotGUI.GUIText("Stopping WarDrum: DeathMarch");
	}
	
	private void WarFrenzy() {
		int pause1 = 125;
		int pause2 = 150;
		int pause3 = 400;
		while (play) {
			int i = 0;
			while (i < 3 && play) {
				
				bot.keyPress(a);
				bot.keyPress(e);
				bot.delay(10);
				bot.keyRelease(a);
				bot.keyRelease(e);
				
				bot.delay(pause1);
				
				bot.keyPress(d);
				bot.keyPress(e);
				bot.delay(10);
				bot.keyRelease(d);
				bot.keyRelease(e);
				
				bot.delay(pause1);
				
				bot.keyPress(d);
				bot.keyPress(e);
				bot.delay(10);
				bot.keyRelease(d);
				bot.keyRelease(e);
				
				bot.delay(pause2);
				i++;
			}
			
			bot.keyPress(a);
			bot.keyPress(d);
			bot.keyPress(e);
			bot.delay(10);
			bot.keyRelease(a);
			bot.keyRelease(d);
			bot.keyRelease(e);
			
			bot.delay(pause3);
		}
		ArkBotGUI.GUIText("Stopping WarDrum: WarFrenzy");
	}
	private void NativeAmericanChant() {
		while (play) {
		}
		if (playNAC) {
			playNAC = false;
		}
		ArkBotGUI.GUIText("Stopping WarDrum: NativeAmericanChant");
	}
	private void DarkKnightRises() {
		int pause1 = 350;
		int pause2 = 500;
		int pause3 = 500;
		while (play) {
			bot.keyPress(b);
			bot.keyPress(d);
			bot.delay(10);
			bot.keyRelease(b);
			bot.keyRelease(d);
			
			bot.delay(pause1);
			
			bot.keyPress(b);
			bot.keyPress(d);
			bot.delay(10);
			bot.keyRelease(b);
			bot.keyRelease(d);
			
			bot.delay(pause1);
			
			bot.keyPress(a);
			bot.keyPress(b);
			bot.keyPress(d);
			bot.keyPress(e);
			bot.delay(10);
			bot.keyRelease(a);
			bot.keyRelease(b);
			bot.keyRelease(d);
			bot.keyRelease(e);
			
			bot.delay(pause2);
			
			bot.keyPress(a);
			bot.keyPress(b);
			bot.keyPress(d);
			bot.keyPress(e);
			bot.delay(10);
			bot.keyRelease(a);
			bot.keyRelease(b);
			bot.keyRelease(d);
			bot.keyRelease(e);
			
			bot.delay(pause3);
		}
		if (playDKR) {
			playDKR = false;
		}
		ArkBotGUI.GUIText("Stopping WarDrum: DarkKnightRises");
	}
	private void Charge() {
		while (play) {
			int pause = 1000;
			while (pause > 50 && play) {
				bot.keyPress(a);
				bot.keyPress(b);
				bot.keyPress(d);
				bot.keyPress(e);
				bot.delay(10);
				bot.keyRelease(a);
				bot.keyRelease(b);
				bot.keyRelease(d);
				bot.keyRelease(e);
				
				bot.delay(pause);
				
				bot.keyPress(e);
				bot.delay(10);
				bot.keyRelease(e);
				
				bot.delay(pause);
				
				bot.keyPress(e);
				bot.delay(10);
				bot.keyRelease(e);
				
				bot.delay(pause);
				
				bot.keyPress(e);
				bot.delay(10);
				bot.keyRelease(e);
				
				bot.delay(pause);
				pause = pause - (pause/10);
			}
		}
		ArkBotGUI.GUIText("Stopping WarDrum: Charge");
	}
	private void IntensityOne() {
		int pause1 = 200;
		while (play) {
			
			bot.keyPress(a);
			bot.keyPress(c);
			bot.keyPress(d);
			bot.keyPress(e);
			bot.delay(10);
			bot.keyRelease(a);
			bot.keyRelease(c);
			bot.keyRelease(d);
			bot.keyPress(e);
			
			bot.delay(pause1);
			
			bot.keyPress(a);
			bot.delay(10);
			bot.keyRelease(a);
			
			bot.delay(pause1);
			
			bot.keyPress(a);
			bot.delay(10);
			bot.keyRelease(a);
			
			bot.delay(pause1);
			
			bot.keyPress(a);
			bot.keyPress(d);
			bot.keyPress(e);
			bot.delay(10);
			bot.keyRelease(a);
			bot.keyRelease(d);
			bot.keyPress(e);
			
			bot.delay(pause1);
			
			bot.keyPress(a);
			bot.delay(10);
			bot.keyRelease(a);
			
			bot.delay(pause1);
			
			bot.keyPress(a);
			bot.delay(10);
			bot.keyRelease(a);
			
			bot.delay(pause1);
			
			bot.keyPress(a);
			bot.keyPress(d);
			bot.keyPress(e);
			bot.delay(10);
			bot.keyRelease(a);
			bot.keyRelease(d);
			bot.keyPress(e);
			
			bot.delay(pause1);
			
			bot.keyPress(a);
			bot.delay(10);
			bot.keyRelease(a);
			
			bot.delay(pause1);
		}
		ArkBotGUI.GUIText("Stopping WarDrum: IntensityOne");
	}
	private void Train() {
		int pause = 300;
		while (play) {
			bot.keyPress(d);
			bot.keyPress(e);
			bot.delay(10);
			bot.keyRelease(d);
			bot.keyPress(e);
			
			bot.delay(pause);
			
			int i = 0;
			while (i < 3) {
				bot.keyPress(d);
				bot.delay(10);
				bot.keyRelease(d);
				
				bot.delay(pause);
				
				bot.keyPress(e);
				bot.delay(10);
				bot.keyRelease(e);
				
				bot.delay(pause);
				i++;
			}
		}
		ArkBotGUI.GUIText("Stopping WarDrum: Train");
	}
}
