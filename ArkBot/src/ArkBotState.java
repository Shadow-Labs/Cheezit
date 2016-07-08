import java.awt.AWTException;
import java.io.Serializable;

public class ArkBotState implements Serializable {
	private static final long serialVersionUID = 1L;
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
