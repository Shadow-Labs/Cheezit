import java.awt.AWTException;

public class ArkBotState {
	public static CharacterActions act;
	public static Tamer tame;
	public static Breeder breed;
	public static AutoGatherer gatherer;
	
	public ArkBotState() throws AWTException {
		act = new CharacterActions();
		tame = new Tamer();
		breed = new Breeder();
		gatherer = new AutoGatherer();
	}
}
