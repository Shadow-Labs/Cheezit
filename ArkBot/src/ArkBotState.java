import java.awt.AWTException;
import java.io.Serializable;

public class ArkBotState implements Serializable {
	private static final long serialVersionUID = 1L;
	public static CharacterActions act;
	public static Tamer tame;
	public static Breeder breeder;
	public static AutoGatherer gatherer;
	public static AutoHealer healer;
	public static MeatSplitter splitter;
	public static Autopilot piloter;
	public static AutoFisher fisher;
	public static boolean connected;
	
	public ArkBotState() throws AWTException {
		act = new CharacterActions();
		tame = new Tamer();
		breeder = new Breeder();
		gatherer = new AutoGatherer();
		healer = new AutoHealer();
		splitter = new MeatSplitter();
		piloter = new Autopilot();
		fisher = new AutoFisher();
		connected = false;
	}
}
