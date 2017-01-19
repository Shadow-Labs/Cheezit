import java.io.*;
import java.net.*;

public class ArkBotClient implements Runnable{

	@Override
	public void run() {
    	String hostName = ArkBot.global.HOST;
    	int portNumber = ArkBot.global.PORT;
    	boolean run = true;
		while (run) {
			if (Thread.currentThread().isInterrupted()) {
				ArkBot.connect = false;
		    	ArkBot.connection = false;
		    	run = false;
		    	
		    	ArkBot.log.WriteLog("CLIENT: Disconnected from ArkBotServer.");
		    	ArkBotGUI.GUIText("CLIENT: Disconnected from ArkBotServer.");
			}
			if (!ArkBot.connection && ArkBot.connect) {
			    try (	  

			    		Socket sSocket = new Socket(hostName, portNumber);
			//	        PrintWriter out = new PrintWriter(sSocket.getOutputStream(), true);
			//	        BufferedReader in = new BufferedReader(new InputStreamReader(sSocket.getInputStream()));
				    		
				    	ObjectOutputStream toServer = new ObjectOutputStream(sSocket.getOutputStream());
				    	ObjectInputStream fromServer = new ObjectInputStream(sSocket.getInputStream());
				    ) {
				    	ArkBot.connection = true;
				    	ArkBot.log.WriteLog("CLIENT: Connection Established with ArkBotServer.");
				    	ArkBotGUI.GUIText("CLIENT: Connection Established with ArkBotServer.");
			//	        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			//	        String fromServer;
			//	        String fromUser;
			//
			//	        while ((fromServer = in.readLine()) != null) {
			//	            System.out.println("Server: " + fromServer);
			//	            if (fromServer.equals("Bye."))
			//	                break;
			//	             
			//	            fromUser = stdIn.readLine();
			//	            if (fromUser != null) {
			//	                System.out.println("Client: " + fromUser);
			//	                out.println(fromUser);
			//	            }
			//	        }
				    	
				    	while (ArkBot.connect && ArkBot.connection) {
							// Send Client State
					    	toServer.writeObject(ArkBot.cStruct);
						}
				    	
				    } catch (UnknownHostException e) {
				    	ArkBot.log.WriteLog("CLIENT: Unknown Host " + hostName);
				        ArkBotGUI.GUIText("CLIENT: Unknown Host " + hostName);
				    } catch (IOException e) {
				    	ArkBot.log.WriteLog("CLIENT: Unable to create I/O Connection " + hostName);
				        ArkBotGUI.GUIText("CLIENT: Unable to create I/O Connection " + hostName);
				    }
			}
		}
		
	}
}
