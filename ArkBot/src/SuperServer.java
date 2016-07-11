import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

public class SuperServer extends Thread {
	private Socket socket = null;
	private ObjectInputStream fromClient = null;
	private ObjectOutputStream toClient = null;
	public ClientStruct clientStruct = new ClientStruct();
	
	public SuperServer(Socket socket) {
		super("SuperServer");
		this.socket = socket;
		try {
			fromClient = new ObjectInputStream(socket.getInputStream());
			toClient = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			ArkBot.log.WriteLog("SERVER ERROR: Could not establish Object Stream./n" + e);
			ArkBotGUI.GUIText("SERVER ERROR: Could not establish Object Stream./n" + e);
			e.printStackTrace();
		}
	}
	
	public void run() {
		//Client State Test
		try {
			clientStruct = (ClientStruct)fromClient.readObject();
		} catch (ClassNotFoundException e) {
			ArkBot.log.WriteLog("SERVER ERROR: Class not found./n" + e);
			ArkBotGUI.GUIText("SERVER ERROR: Class not found./n" + e);
			e.printStackTrace();
		} catch (IOException e) {
			ArkBot.log.WriteLog("SERVER ERROR: Could not recieve client struct./n" + e);
			ArkBotGUI.GUIText("SERVER ERROR: Could not recieve client struct./n" + e);
			e.printStackTrace();
		}
		
		ArkBotGUI.GUIText("Client Username: " + clientStruct.getUser());
		
		
	}
	
		//implement your methods here
}
