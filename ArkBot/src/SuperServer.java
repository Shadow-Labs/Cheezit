import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

public class SuperServer extends Thread {
	private int id = 0;
	private Socket socket = null;
	private ObjectInputStream fromClient = null;
	private ObjectOutputStream toClient = null;
	public ClientStruct clientStruct = new ClientStruct();
	
	public SuperServer(int id, Socket socket) {
		super("SuperServer");
		this.id = id;
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
			Server.serverList.put(id, clientStruct);
		} catch (ClassNotFoundException e) {
			ArkBot.log.WriteLog("SERVER ERROR: Class not found./n" + e);
			ArkBotGUI.GUIText("SERVER ERROR: Class not found./n" + e);
			e.printStackTrace();
		} catch (IOException e) {
			ArkBot.log.WriteLog("SERVER ERROR: Could not recieve client struct./n" + e);
			ArkBotGUI.GUIText("SERVER ERROR: Could not recieve client struct./n" + e);
			e.printStackTrace();
		}
		System.out.println(Server.serverList.get(id).getState().connected);
		while (Server.serverList.get(id).getState().connected) {
			try {
				clientStruct = (ClientStruct)fromClient.readObject();
				System.out.println(Server.serverList.get(id).getState().piloter.pilot + " " + clientStruct.getState().piloter.pilot);
				// Only update when client changes
				if (Server.serverList.get(id).getState() != null && Server.serverList.get(id).getState() != clientStruct.getState()) {
					Server.serverList.put(id, clientStruct);
					ArkBotGUI.GUIText("Client Username: " + Server.serverList.get(id).getUser());
					ArkBotGUI.GUIText("Client IP: " + Server.serverList.get(id).getAddr());
					ArkBotGUI.GUIText("Breed State: " + Server.serverList.get(id).getState().breeder);
					ArkBotGUI.GUIText("Tame State: " + Server.serverList.get(id).getState().tame);
					ArkBotGUI.GUIText("Gather State: " + Server.serverList.get(id).getState().gatherer);
					ArkBotGUI.refreshClientText();
				}
			} catch (ClassNotFoundException e) {
				ArkBot.log.WriteLog("SERVER ERROR: Class not found./n" + e);
				ArkBotGUI.GUIText("SERVER ERROR: Class not found./n" + e);
				e.printStackTrace();
			} catch (IOException e) {
				ArkBot.log.WriteLog("SERVER ERROR: Could not recieve client struct./n" + e);
				ArkBotGUI.GUIText("SERVER ERROR: Could not recieve client struct./n" + e);
				e.printStackTrace();
			}
		}
		
	}
	
		//implement your methods here
}
