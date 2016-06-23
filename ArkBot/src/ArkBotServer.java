import java.net.*;
import java.io.*;

public class ArkBotServer {
	public void Start() {
		
		// Setup Server
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(ArkBot.global.PORT);
		} catch (IOException e) {
			ArkBotGUI.GUIText("SERVER ERROR: Could not listen on port " + ArkBot.global.PORT);
		}
		ArkBotGUI.GUIText("SERVER: Now listening on port " + ArkBot.global.PORT);
		Socket clientSocket = null;
		boolean listeningSocket = true;
		while(listeningSocket) {
			try {
				clientSocket = serverSocket.accept();
				ArkBotGUI.GUIText("SERVER: Accepted Client: " + clientSocket.getInetAddress());
				SuperServer ss = new SuperServer(clientSocket);
				ss.start();
			} catch (IOException e) {
				ArkBotGUI.GUIText("SERVER ERROR: Could not accept Client Socket.");
				e.printStackTrace();
			}
		}
		ArkBotGUI.GUIText("SERVER: Client Disconnected: " + clientSocket.getInetAddress());
		try {
			serverSocket.close();
		} catch (IOException e) {
			ArkBotGUI.GUIText("SERVER ERROR: Could not close Server Socket.");
			e.printStackTrace();
		}
	}
}
