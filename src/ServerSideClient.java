import java.net.*;
import java.lang.*;
import java.util.*;
import java.io.*;

public class ServerSideClient implements IClient, Runnable{ //Server-side client instance, handles IP and thread
	private IServer server;
	private boolean running;
	private BufferedReader in;
	private PrintWriter out;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private Socket clientSocket;
	private String IP;

	//Constructor, assigns neccessary values and prepared input and output streams
	public ServerSideClient(IServer server, Socket socket){
		try{
			this.server = server;
			clientSocket = socket;
			IP = clientSocket.getRemoteSocketAddress().toString();
			running = true;
			//ois = new ObjectInputStream(clientSocket.getInputStream());
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			//in.connect();
			//oos = new ObjectOutputStream(clientSocket.getOutputStream());
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			//out.connect();
		}catch(Exception e){System.out.println("ServerSideClient | ERR: " + e.getStackTrace()[1].getLineNumber());}
	}

	public void sendBool(boolean b){
		try{
			//oos.writeBoolean(b);
		}catch(Exception e){System.out.println("SSC Send Bool | ERR: " + e.getStackTrace()[1]);}
	}

	//Uses ObjectOutputStream to write to the socket
	public void send(String data){
		try{
			out.println(data);
		}catch(Exception e){System.out.println("SSC Send | ERR: " + e.getStackTrace()[1]);}
	}

	//The main function of the ServerSideClient. Constantly reads from socket and processes data.
	public void run(){
		while(running){
			try{
				//processUser(ois.readObject());
				process(in.readLine());
			}catch(Exception e){System.out.println("ServerSideClient.run | Socket closed!"); stop();}
		}
	}

	//Halt case, closes server connections and terminates instance
	public void stop(){
		try{
			running = false;
			in.close();
			//ois.close();
			out.close();
			//oos.close();
			clientSocket.close();
		}catch(Exception e){System.out.println("ServerSideClient.stop | ERR: " + e.getStackTrace()[1].getLineNumber());}
	}

	//Returns IP
	public String getIP(){
		return IP;
	}

	public void processUser(Object data){
		if(data instanceof User){
			server.login((User)data, this);
		}
	}

	//Calls broadcast function
	public void process(String data){
		server.broadcast(data);
	}
}
