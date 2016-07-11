import java.net.*;
import java.io.*;

public class Server {
	
	
	
	public void Start() {
		
		// Setup Server
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(ArkBot.global.PORT);
		} catch (IOException e) {
			ArkBot.log.WriteLog("SERVER ERROR: Could not listen on port " + ArkBot.global.PORT);
			ArkBotGUI.GUIText("SERVER ERROR: Could not listen on port " + ArkBot.global.PORT);
		}
		ArkBotGUI.GUIText("SERVER: Now listening on port " + ArkBot.global.PORT);
		Socket clientSocket = null;
		boolean listeningSocket = true;
		while(listeningSocket && ArkBot.serverRun) {
			try {
				clientSocket = serverSocket.accept();
				ArkBot.log.WriteLog("SERVER: Accepted Client: " + clientSocket.getInetAddress());
				ArkBotGUI.GUIText("SERVER: Accepted Client: " + clientSocket.getInetAddress());
				SuperServer ss = new SuperServer(clientSocket);
				ss.start();
			} catch (IOException e) {
				ArkBot.log.WriteLog("SERVER ERROR: Could not accept Client Socket.");
				ArkBotGUI.GUIText("SERVER ERROR: Could not accept Client Socket.");
				e.printStackTrace();
			}
		}
		ArkBot.log.WriteLog("SERVER: Client Disconnected: " + clientSocket.getInetAddress());
		ArkBotGUI.GUIText("SERVER: Client Disconnected: " + clientSocket.getInetAddress());
		try {
			serverSocket.close();
		} catch (IOException e) {
			ArkBot.log.WriteLog("SERVER ERROR: Could not close Server Socket.");
			ArkBotGUI.GUIText("SERVER ERROR: Could not close Server Socket.");
			e.printStackTrace();
		}
	}
}
