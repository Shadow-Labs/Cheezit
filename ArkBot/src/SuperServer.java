import java.net.*;

public class SuperServer extends Thread {
	private Socket socket = null;
	
	public SuperServer(Socket socket) {
		super("SuperServer");
		this.socket = socket;
	}
	
	public void run() {
		//Read input and process here
	}
	
		//implement your methods here
}
