import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.LinkedList;

public class Client_Thread extends Thread {
	Socket connectionSocket;
	int i = 1;
	int ID;
	String clientSentence;
	String capitalizedSentence;
	DataOutputStream outToClient;
	BufferedReader inFromClient;
	static LinkedList<Double> client1 = new LinkedList<Double>();
	static LinkedList<Double> client2 = new LinkedList<Double>();

	// lazm n3ml constructor 3shan ya5od socket bta3 client da
	public Client_Thread(Socket ClientSocket, int ID) {
		connectionSocket = ClientSocket;
		ID = this.ID;
	}

	public static void log(Throwable throwable) {
	}

	@SuppressWarnings("unchecked")
	public void run() {
		try {
			inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}

		try {
			outToClient = new DataOutputStream(connectionSocket.getOutputStream());
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

//		while (true && i < 2) {

			try {
				clientSentence = inFromClient.readLine();
				System.out.print(clientSentence);
//				}
//				 break;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if ((clientSentence.contains("One")) && (clientSentence != null)) {

				String Temperature_1 = clientSentence.substring(17, clientSentence.length());
				double Double_1 = Double.parseDouble(Temperature_1);
				double avg_Double_1 = average(client1);
				System.out.println('\n' + "Average temp from client1: ");
				System.out.println(avg_Double_1);
				client1.add(Double_1);

			}

			if ((clientSentence.contains("Two")) && (!clientSentence.equals(null))) {

				String Temperature_2 = clientSentence.substring(17, clientSentence.length());
				double Double_2 = Double.parseDouble(Temperature_2);
				double avg_Double_2 = average(client2);
				System.out.println('\n' + "Avg client from client2: "); // output in server
				System.out.println(avg_Double_2);
				client2.add(Double_2);
			}
			capitalizedSentence = clientSentence.toUpperCase() + '\n';

			try {
				outToClient.writeBytes("el server aho" + '\n');
//				break;
			} catch (IOException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}

			try {
				if (clientSentence.equalsIgnoreCase("Close Socket")) {
					try {
						connectionSocket.close();
//						break;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Connection Socket is closed @Server");
				}
			} catch (java.lang.NullPointerException exception) {
				Client_Thread.log(exception);
			}
//			break;
//		}
//		try {
//			connectionSocket.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
	}
		

	public double average(LinkedList<Double> r) {
		double sum = 0;
		double average = 0.0;
		int i = 0;
		while (i < r.size()) {
			sum = Double.parseDouble(r.get(i).toString()) + sum;
			average = sum / r.size();
		}
		return average;
	}

}
