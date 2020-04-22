
import java.net.*;
import java.util.LinkedList;
//import io.airbrake.utility.Logging;

class TCP_Server {
	@SuppressWarnings("rawtypes")
	static LinkedList clients = new LinkedList();
//	static int i = 1;

	@SuppressWarnings({ "unchecked", "resource" })
	public static void main(String argv[]) throws Exception {
		System.out.println("Server is ON......");
		int ID = 1;

		ServerSocket welcomeSocket = new ServerSocket(3000);
//	try {
		while (true ) {//&& i < 3
			System.out.println("Waiting for a new connection......");

			Socket connectionSocket = welcomeSocket.accept();

			System.out.println("Server is now connected to the Client " + ID);

			// here we need to make a thread
			Client_Thread client_thread = new Client_Thread(connectionSocket, ID);
			clients.add(client_thread);
			
			ID++;
			client_thread.start();
//			client_thread.average();
			System.out.println("Client is added.");
			System.out.println("==================================================");
//			i++;
		}
//	}	catch(SocketException exception) {
//		Logging.log(exception);
//		exception.printStackTrace();
//	}
	
	}
}
