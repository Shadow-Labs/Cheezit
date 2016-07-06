
public class ServerStruct {
	private String username;
	private String address;
	private ArkBotState state;
	
	public ServerStruct (String user, String addr, ArkBotState ste) {
		this.username = user;
		this.address = addr;
		this.state = ste;
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
