import java.net.*;
import java.io.*;

public class ArkBotServer {
	public void Start() {
		try (ServerSocket serverSocket = new ServerSocket(ArkBot.global.PORT);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));)
		{
	        
            String inputLine, outputLine;
            
            // Initiate conversation with client
            ServerClientProtocol SCP = new ServerClientProtocol();
            outputLine = SCP.processInput(null);
            out.println(outputLine);

            while ((inputLine = in.readLine()) != null) {
                outputLine = SCP.processInput(inputLine);
                out.println(outputLine);
                if (outputLine.equals("Bye."))
                    break;
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + ArkBot.global.PORT + " or listening for a connection");
            System.out.println(e.getMessage());
        }
	}
}
