import java.net.*;
import java.util.*;
import java.io.*;

public class Server implements IServer, Runnable{

	private static String DATABASE = "user.dat";
	private int port;
	private boolean running;
	private ServerSocket listener;
	private ArrayList<ServerSideClient> clients;
	private DatabaseHandler db;


	//Constructor, configures server for use.
	public Server(int port){
		this.port = port;
		running = true;
		clients = new ArrayList<ServerSideClient>();
		db = new DatabaseHandler();
	}

	//Sends data to all connected peers
	public void broadcast(String data){
		for(ServerSideClient ssc : clients)
			ssc.send(data);
	}

	public void login(User user, ServerSideClient ssc){
		if(db.validateUser(user)){
			System.out.println("Validated");
			//ssc.send("true");
		}
		else{
			System.out.println("Invalid");
			//ssc.send("false");
		}
	}

	//Runs main function of the server, waits for connection then assigns relevant data and creates new ServerSideClient instance and thread
	public void run(){
		try{
			listener = new ServerSocket(port);
			System.out.println("Server is running!");
			while(running){
				Socket clientSocket = listener.accept();
				ServerSideClient serverSideClient = new ServerSideClient(this, clientSocket);
				clients.add(serverSideClient);
				Thread client = new Thread(serverSideClient);
				client.start();
			}
		}catch(Exception e){System.out.println("Server.run | ERR: " + e.getStackTrace()[1].getLineNumber());}
	}

	//Shuts down server and closes all active connections
	public void stop(){
		try{
			for(ServerSideClient ssc : clients)
				ssc.stop();
			listener.close();
			running = false;
		}catch(Exception e){System.out.println("Server.stop | ERR: " + e.getStackTrace()[1].getLineNumber());}
	}
}
