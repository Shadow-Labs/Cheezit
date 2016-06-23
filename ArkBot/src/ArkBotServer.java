import java.net.*;
import java.io.*;

public class ArkBotServer {
	public void Start() {
		
		// Setup Server
		try {
			ServerSocket serverSocket = new ServerSocket(ArkBot.global.PORT);
		} catch (IOException e) {
			ArkBotGUI.GUIText("SERVER ERROR: Could not listen on port " + ArkBot.global.PORT);
		}
		
		ServerSocket serverSocket = null;
		boolean listeningSocket = true;
		while(listeningSocket) {
			Socket clientSocket;
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
		try {
			serverSocket.close();
		} catch (IOException e) {
			ArkBotGUI.GUIText("SERVER ERROR: Could not close Server Socket.");
			e.printStackTrace();
		}
	}
}
