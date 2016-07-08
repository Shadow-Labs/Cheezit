import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClientStruct implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username;
	private String address;
	private ArkBotState state;
	
	public ClientStruct () {
		this.username = ArkBotSettings.GetSetting("Username");
		try {
			this.address = InetAddress.getLocalHost().toString();
		} catch (UnknownHostException e) {
			ArkBotGUI.GUIText("CLIENT ERROR: Could not determine local IP./n" + e);
			e.printStackTrace();
		}
		this.state = ArkBot.state;
	}
	
	// Getters
	public String getUser() { return username; };
	public String getAddr() { return address; };
	public ArkBotState getState() { return state; };
	
	// Setters
	public void setUser(String user) { this.username = user; };
	public void setAddr(String addr) { this.address = addr; };
	public void setState(ArkBotState ste) { this.state = ste; };
}
